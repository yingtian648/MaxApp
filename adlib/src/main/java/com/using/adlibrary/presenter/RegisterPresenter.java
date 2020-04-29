/*     */ package com.using.adlibrary.presenter;
/*     */ 
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.callback.IHeartBeatCallback;
/*     */ import com.using.adlibrary.callback.INetCallback;
/*     */ import com.using.adlibrary.callback.IPresenter;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.factory.DeviceInfoFactory;
/*     */ import com.using.adlibrary.factory.DeviceUuidFactory;
/*     */ import com.using.adlibrary.factory.ThreadPollFactory;
/*     */ import com.using.adlibrary.model.ConnectRequestModel;
/*     */ import com.using.adlibrary.utils.NetWorkUtils;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegisterPresenter
/*     */   implements INetCallback
/*     */ {
/*     */   private String mUuid;
/*     */   private String mDeviceJsonInfo;
/*     */   private String mCurrentTitleListArray;
/*     */   private boolean mRegisterSuccess = false;
/*     */   private IPresenter mIPresenter;
/*     */   private ConnectRequestModel mRequest;
/*     */   private RegisterRunnable registerRunnable;
/*     */   private ScheduledExecutorService scheduledExecutorService;
/*     */   private ScheduledFuture scheduledFuture;
/*     */   
/*     */   public RegisterPresenter(IPresenter iPresenter, ConnectRequestModel requestModel) {
/*  52 */     this.mRequest = requestModel;
/*  53 */     this.mIPresenter = iPresenter;
/*  54 */     this.scheduledExecutorService = ThreadPollFactory.getInstance().getScheduledExecutorService();
/*  55 */     this.mUuid = DeviceUuidFactory.getInstance().getDeviceUuid();
/*  56 */     this.mDeviceJsonInfo = DeviceInfoFactory.getInstance().getJsonDeviceInfo();
/*     */     
/*  58 */     this.mRequest.devicePushMessage(this.mUuid, "设备已启动,请注意维护", "1");
/*     */     
/*  60 */     this.registerRunnable = new RegisterRunnable();
/*     */   }
/*     */   
/*     */   public void start() {
/*     */     try {
/*  65 */       if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled()) {
/*  66 */         Logger.e("注册与心跳任务存在，不重复添加", new Object[0]);
/*     */       } else {
/*  68 */         Logger.e("注册与心跳任务不存在，添加定时任务 心跳间隔时间： " + 
/*  69 */             ConfigManger.getInstance().getDeviceHeartIntervalTime(), new Object[0]);
/*     */         
/*  71 */         this.scheduledFuture = this.scheduledExecutorService.scheduleAtFixedRate(this.registerRunnable, 
/*     */             
/*  73 */             ConfigManger.getInstance().getDeviceHeartBeatDelayTime(), 
/*  74 */             ConfigManger.getInstance().getDeviceHeartIntervalTime(), TimeUnit.MILLISECONDS);
/*     */       }
/*     */     
/*  77 */     } catch (RejectedExecutionException e) {
/*  78 */       Logger.e("注册与心跳无法添加任务", new Object[0]);
/*  79 */     } catch (NullPointerException e) {
/*  80 */       Logger.e("注册与心跳任务异常", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*  85 */     if (this.scheduledFuture != null) {
/*  86 */       this.scheduledFuture.cancel(true);
/*     */     }
/*     */     
/*  89 */     if (this.registerRunnable != null)
/*  90 */       this.registerRunnable = null; 
/*     */   }
/*     */   
/*     */   private class RegisterRunnable
/*     */     implements Runnable {
/*     */     private RegisterRunnable() {}
/*     */     
/*     */     public void run() {
/*  98 */       Logger.e("当前设备注册状态： " + RegisterPresenter.this.ismRegisterSuccess(), new Object[0]);
/*  99 */       Logger.e("当前注册时网络状态: " + NetWorkUtils.networkState, new Object[0]);
/*     */       
/* 101 */       if (!RegisterPresenter.this.ismRegisterSuccess()) {
/* 102 */         if (NetWorkUtils.networkState) {
/* 103 */           RegisterPresenter.this.registerAction();
/*     */         }
/*     */       } else {
/* 106 */         RegisterPresenter.this.heartBeat();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void registerAction() {
/* 116 */     if (this.mRequest != null) {
/* 117 */       this.mRequest.deviceRegister(this.mDeviceJsonInfo, this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void heartBeat() {
/* 125 */     if (this.mRequest != null) {
/* 126 */       this.mRequest.deviceHeartBeat(this.mUuid, new IHeartBeatCallback()
/*     */           {
/*     */             public void deviceCmdCallback(int cmd, int value) {
/* 129 */               if (RegisterPresenter.this.mIPresenter != null)
/*     */               {
/* 131 */                 RegisterPresenter.this.mIPresenter.cmd(cmd, value);
/*     */               }
/*     */             }
/*     */ 
/*     */             
/*     */             public void deviceRequestTitleListCallback(String subtitleList) {
/* 137 */               Logger.e("心跳字幕列表回调  " + subtitleList, new Object[0]);
/*     */               try {
/* 139 */                 if (RegisterPresenter.this.mRequest != null) {
/* 140 */                   String ss = subtitleList.replaceAll("[\\[\\]]", "");
/* 141 */                   Logger.e("--------字幕列表 ： " + ss, new Object[0]);
/* 142 */                   if (!ss.equals(RegisterPresenter.this.mCurrentTitleListArray)) {
/* 143 */                     if (RegisterPresenter.this.mIPresenter != null) {
/* 144 */                       RegisterPresenter.this.mIPresenter.currentTitleList(ss);
/*     */                     }
/*     */                     
/* 147 */                     RegisterPresenter.this.mCurrentTitleListArray = ss;
/*     */                   } 
/*     */                 } 
/* 150 */               } catch (Exception e) {
/* 151 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logoutAction() {
/* 162 */     if (this.mRequest != null) {
/* 163 */       this.mRequest.deviceLogout(this.mUuid);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void requestPushMessageAction(String content, String type) {
/* 172 */     if (this.mRequest != null) {
/* 173 */       this.mRequest.devicePushMessage(this.mUuid, content, type);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deviceRegisterCallback(String msg) {
/* 182 */     Logger.e("RegisterRunnable: 注册回调   " + msg, new Object[0]);
/*     */     
/*     */     try {
/* 185 */       JSONObject jsonObject = new JSONObject(msg);
/* 186 */       if (jsonObject.getInt("code") == 200) {
/* 187 */         this.mIPresenter.registerSuccess();
/* 188 */         this.mRegisterSuccess = true;
/*     */         
/* 190 */         JSONObject dataJsonObj = jsonObject.getJSONObject("data");
/*     */ 
/*     */         
/* 193 */         int intervalV1 = dataJsonObj.getInt("intervalV1");
/*     */         
/* 195 */         int randomV1 = dataJsonObj.getInt("randomV1");
/*     */         
/* 197 */         ConfigManger.getInstance().getConfigure().setDeviceHeartIntervalTime(intervalV1 * 1000);
/* 198 */         ConfigManger.getInstance().getConfigure().setDeviceHeartBeatDelayTime(randomV1 * 1000);
/*     */ 
/*     */         
/* 201 */         int intervalV2 = dataJsonObj.getInt("intervalV2");
/*     */         
/* 203 */         int randomV2 = dataJsonObj.getInt("randomV2");
/*     */         
/* 205 */         ConfigManger.getInstance().getConfigure().setProgramHeartBeatIntervalTime(intervalV2 * 1000);
/* 206 */         ConfigManger.getInstance().getConfigure().setProgramHeartBeatDelayTime(randomV2 * 1000);
/*     */ 
/*     */         
/* 209 */         int intervalV3 = dataJsonObj.getInt("intervalV3");
/*     */         
/* 211 */         int randomV3 = dataJsonObj.getInt("randomV3");
/* 212 */         ConfigManger.getInstance().getConfigure().setPhoneProgramHeartBeatDelayTime(intervalV3 * 1000);
/* 213 */         ConfigManger.getInstance().getConfigure().setPhoneProgramHeartBeatIntervalTime(randomV3 * 1000);
/*     */ 
/*     */         
/* 216 */         int rebootHour = dataJsonObj.getInt("rebootTime");
/*     */         
/* 218 */         int localRebootHour = ConfigManger.getInstance().getRebootHour();
/* 219 */         Logger.e("本地重启时间: " + localRebootHour + "服务器重启时间: " + rebootHour, new Object[0]);
/* 220 */         if (rebootHour != localRebootHour) {
/*     */           
/* 222 */           Logger.e("服务器重启时间有变更，保存设置  并重启app", new Object[0]);
/* 223 */           ConfigManger.getInstance().getConfigure().setRebootHour(rebootHour);
/*     */ 
/*     */           
/* 226 */           AdEngine.getInstances().restartApp();
/*     */         } else {
/* 228 */           ConfigManger.getInstance().updateConfig();
/*     */         } 
/*     */       } else {
/*     */         
/* 232 */         this.mRegisterSuccess = false;
/*     */       }
/*     */     
/* 235 */     } catch (JSONException e) {
/* 236 */       e.printStackTrace();
/*     */     } 
/* 238 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int deviceNetException(String msg) {
/* 246 */     Logger.e("RegisterRunnable: deviceNetException   " + msg, new Object[0]);
/* 247 */     if (this.mIPresenter != null) {
/* 248 */       this.mIPresenter.uiInfo(msg);
/*     */     }
/* 250 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean ismRegisterSuccess() {
/* 254 */     return this.mRegisterSuccess;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\presenter\RegisterPresenter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */