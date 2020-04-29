/*     */ package com.using.adlibrary.receiver;
/*     */ 
/*     */ import android.content.BroadcastReceiver;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.hardware.display.DisplayManager;
/*     */ import android.os.AsyncTask;
/*     */ import android.os.Build;
/*     */ import android.text.format.DateFormat;
/*     */ import android.view.Display;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.bean.dao.ProgramCountBean;
/*     */ import com.using.adlibrary.bean.dao.RootListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.TaskPackageCountBean;
/*     */ import com.using.adlibrary.bean.data.TalkServiceBean;
/*     */ import com.using.adlibrary.bean.utils.DaoOperation;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.factory.DeviceUuidFactory;
/*     */ import com.using.adlibrary.model.ConnectRequestModel;
/*     */ import com.using.adlibrary.utils.AlarmManagerUtils;
/*     */ import com.using.adlibrary.utils.TimeUtils;
/*     */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
/*     */ import com.zhy.http.okhttp.callback.StringCallback;
/*     */ import java.util.List;
/*     */ import okhttp3.Call;
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
/*     */ public class AlarmReceiver
/*     */   extends BroadcastReceiver
/*     */ {
/*     */   public static final String ACTION_ALARM_1 = "ACTION_ALARM_1";
/*     */   public static final String ACTION_ALARM_2 = "ACTION_ALARM_2";
/*     */   public static final String ACTION_ALARM_3 = "ACTION_ALARM_3";
/*     */   
/*     */   public void onReceive(Context context, Intent intent) {
/*  46 */     String action = intent.getAction();
/*  47 */     if (action != null) {
/*  48 */       long systemTime; long timerCloseLed; long dv; CharSequence sysTimeStr; long systemTime1; long timerReboot; long dv1; CharSequence sysTimeStr1; switch (action) {
/*     */         case "ACTION_ALARM_1":
/*  50 */           Logger.e("定时任务1到了。。。", new Object[0]);
/*     */           
/*  52 */           systemTime = System.currentTimeMillis();
/*  53 */           timerCloseLed = AlarmManagerUtils.getTimedTimeInMillisV2(
/*  54 */               ConfigManger.getInstance().getCloseLcdBackLightTime(), 0, 0);
/*     */ 
/*     */           
/*  57 */           dv = StrictMath.abs(systemTime - timerCloseLed);
/*  58 */           Logger.e("息屏任务系统时间与预设时间的差值为： " + dv, new Object[0]);
/*  59 */           sysTimeStr = DateFormat.format("kk:mm:ss", systemTime);
/*  60 */           Logger.e("系统时间" + sysTimeStr, new Object[0]);
/*  61 */           if (dv >= 0L && dv <= 600000L) {
/*     */             
/*  63 */             Logger.e("满足条件一" + dv, new Object[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  70 */             if (HwInterfaceManager.getInstance().hasLcdBackLight()) {
/*  71 */               HwInterfaceManager.getInstance().setLcdBackLight(false);
/*     */             }
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case "ACTION_ALARM_2":
/*  79 */           Logger.e("定时任务2到了。。。", new Object[0]);
/*     */           
/*  81 */           systemTime1 = System.currentTimeMillis();
/*  82 */           timerReboot = AlarmManagerUtils.getTimedTimeInMillisV2(
/*  83 */               ConfigManger.getInstance().getRebootHour(), 
/*  84 */               ConfigManger.getInstance().getRebootMinute(), 0);
/*     */           
/*  86 */           dv1 = StrictMath.abs(systemTime1 - timerReboot);
/*  87 */           Logger.e("定时重启任务系统时间与预设时间的差值为： " + dv1, new Object[0]);
/*  88 */           sysTimeStr1 = DateFormat.format("kk:mm:ss", systemTime1);
/*  89 */           Logger.e("系统时间" + sysTimeStr1, new Object[0]);
/*     */           
/*  91 */           if (dv1 >= 0L && dv1 <= 600000L) {
/*  92 */             HwInterfaceManager.getInstance().reboot();
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case "ACTION_ALARM_3":
/*  98 */           Logger.e("定时任务3到了。。。", new Object[0]);
/*     */           try {
/* 100 */             List<ProgramCountBean> lists = AdEngine.getInstances().getDaoSession().getProgramCountBeanDao().queryBuilder().list();
/* 101 */             for (ProgramCountBean b : lists) {
/*     */               
/* 103 */               int maxCum = 0;
/* 104 */               for (TaskPackageCountBean tb : b.getTaskCounts()) {
/*     */ 
/*     */ 
/*     */                 
/* 108 */                 if (maxCum < tb.getNum()) {
/* 109 */                   maxCum = tb.getNum();
/*     */                 }
/*     */               } 
/*     */               
/* 113 */               if (maxCum > 0) {
/* 114 */                 b.setSendDate(TimeUtils.getYMD());
/* 115 */                 b.setNum(maxCum);
/*     */                 
/* 117 */                 TalkServiceBean talkServiceBean = new TalkServiceBean();
/* 118 */                 talkServiceBean.setDeviceId(DeviceUuidFactory.getInstance().getDeviceUuid());
/* 119 */                 talkServiceBean.setPlayListId((int)b.getProgramId());
/* 120 */                 talkServiceBean.setSendDate(TimeUtils.getYMD());
/* 121 */                 talkServiceBean.setNum(b.getNum());
/*     */                 
/* 123 */                 RequestAsyncTask requestAsyncTask = new RequestAsyncTask();
/* 124 */                 requestAsyncTask.execute((Object[])new TalkServiceBean[] { talkServiceBean });
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 129 */             DaoOperation.programDelete();
/* 130 */             List<RootListDaoBean> rootListDaoBeans = AdEngine.getInstances().getDaoSession().getRootListDaoBeanDao().loadAll();
/*     */             
/* 132 */             if (rootListDaoBeans != null && rootListDaoBeans.size() > 0) {
/* 133 */               for (RootListDaoBean r : rootListDaoBeans) {
/* 134 */                 DaoOperation.programInsert(r);
/*     */               }
/*     */             }
/* 137 */           } catch (Exception e) {
/* 138 */             e.printStackTrace();
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isScreenOn(Context context) {
/* 151 */     if (Build.VERSION.SDK_INT >= 20) {
/*     */ 
/*     */       
/* 154 */       DisplayManager dm = (DisplayManager)context.getSystemService("display");
/* 155 */       if (dm != null) {
/* 156 */         Display[] displays = dm.getDisplays();
/* 157 */         for (Display display : displays) {
/* 158 */           if (display.getState() == 2 || display
/* 159 */             .getState() == 0) {
/* 160 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static class RequestAsyncTask
/*     */     extends AsyncTask<TalkServiceBean, String, String>
/*     */   {
/*     */     private boolean success = false;
/* 173 */     private int requestCount = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     protected String doInBackground(TalkServiceBean... talkServiceBeans) {
/* 178 */       while (!this.success && ++this.requestCount < 10) {
/*     */         
/* 180 */         for (TalkServiceBean bean : talkServiceBeans) {
/* 181 */           ConnectRequestModel.devicePushDevicePlayListNum(bean.getDeviceId(), bean
/* 182 */               .getPlayListId(), bean.getSendDate(), bean.getNum(), new StringCallback()
/*     */               {
/*     */                 public void onError(Call call, Exception e, int i) {}
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void onResponse(String s, int i) {
/* 190 */                   if (s != null) {
/* 191 */                     Logger.e("devicePushDevicePlayListNum返回信息：   " + s, new Object[0]);
/*     */                     try {
/* 193 */                       JSONObject jsonObject = new JSONObject(s);
/* 194 */                       RequestAsyncTask.this.success = jsonObject.getBoolean("isSuccess");
/* 195 */                     } catch (JSONException e) {
/* 196 */                       e.printStackTrace();
/*     */                     } 
/*     */                   } 
/*     */                 }
/*     */               });
/*     */         } 
/*     */ 
/*     */         
/*     */         try {
/* 205 */           Thread.sleep(5000L);
/* 206 */         } catch (InterruptedException e) {
/* 207 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/* 211 */       this.success = false;
/* 212 */       this.requestCount = 0;
/*     */       
/* 214 */       return null;
/*     */     }
/*     */     
/*     */     private RequestAsyncTask() {}
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\receiver\AlarmReceiver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */