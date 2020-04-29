/*     */ package com.using.adlibrary.presenter;
/*     */ 
/*     */ import android.util.Log;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.bean.data.UpdateBean;
/*     */ import com.using.adlibrary.bean.event.UIMessageEvent;
/*     */ import com.using.adlibrary.callback.IDownloadCallback;
/*     */ import com.using.adlibrary.callback.IModelCallback;
/*     */ import com.using.adlibrary.factory.ThreadPollFactory;
/*     */ import com.using.adlibrary.model.ConnectRequestModel;
/*     */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
/*     */ import java.io.File;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.greenrobot.eventbus.EventBus;
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
/*     */ public class ApkUpdatePresenter
/*     */ {
/*     */   private UpdateRunnable updateRunnable;
/*     */   private ScheduledExecutorService mScheduledExecutorService;
/*     */   private String uuid;
/*     */   private ConnectRequestModel request;
/*     */   private int currentVersion;
/*     */   
/*     */   public ApkUpdatePresenter(int currentVersion, String uuid, ConnectRequestModel request) {
/*  39 */     this.uuid = uuid;
/*  40 */     this.currentVersion = currentVersion;
/*  41 */     this.request = request;
/*     */     
/*  43 */     this.mScheduledExecutorService = ThreadPollFactory.getInstance().getScheduledExecutorService();
/*  44 */     this.updateRunnable = new UpdateRunnable(uuid, request, currentVersion, this.mScheduledExecutorService);
/*     */     
/*  46 */     this.mScheduledExecutorService.schedule(this.updateRunnable, 10000L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startUpdateApk() {
/*  52 */     if (this.mScheduledExecutorService != null && this.updateRunnable != null) {
/*  53 */       this.updateRunnable.setRun(true);
/*  54 */       this.mScheduledExecutorService.execute(this.updateRunnable);
/*  55 */       Logger.e("执行手动升级apk", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public class InstallRunnable
/*     */     implements Runnable {
/*     */     private File file;
/*     */     
/*     */     public InstallRunnable(File file) {
/*  64 */       this.file = file;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*  70 */       Logger.e("apk下载完成，开始执行静默安装！", new Object[0]);
/*  71 */       HwInterfaceManager.getInstance().silentInstall(this.file.getAbsolutePath());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class UpdateRunnable
/*     */     implements Runnable, IModelCallback<UpdateBean>, IDownloadCallback<File>
/*     */   {
/*     */     private String uuid;
/*     */     
/*  82 */     private int timeOutCount = 0;
/*     */     private boolean isRun = true;
/*     */     private int currentAdCloudVersion;
/*     */     private ConnectRequestModel requestModel;
/*     */     private ScheduledExecutorService executorService;
/*  87 */     private int progressCount = 0;
/*     */     
/*     */     public boolean isRun() {
/*  90 */       return this.isRun;
/*     */     }
/*     */     
/*     */     public void setRun(boolean run) {
/*  94 */       this.isRun = run;
/*     */     }
/*     */     
/*     */     public UpdateRunnable(String uuid, ConnectRequestModel requestModel, int currentAdCloudVersion, ScheduledExecutorService scheduledExecutorService) {
/*  98 */       this.uuid = uuid;
/*  99 */       this.requestModel = requestModel;
/* 100 */       this.executorService = scheduledExecutorService;
/* 101 */       this.currentAdCloudVersion = currentAdCloudVersion;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 106 */       Log.e("TAG", "执行apk升级任务");
/* 107 */       EventBus.getDefault().post(new UIMessageEvent("检测软件版本"));
/* 108 */       while (isRun()) {
/* 109 */         if (this.requestModel != null) {
/* 110 */           if (this.timeOutCount++ <= 10) {
/* 111 */             this.requestModel.deviceApkVersionDetection("1", this.uuid, this);
/*     */           } else {
/* 113 */             this.timeOutCount = 0;
/*     */             
/*     */             break;
/*     */           } 
/*     */         }
/*     */         try {
/* 119 */           Thread.sleep(2000L);
/* 120 */         } catch (InterruptedException e) {
/* 121 */           e.printStackTrace();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void onDownSuccess(File data) {
/* 135 */       setRun(false);
/* 136 */       InstallRunnable installRunnable = new InstallRunnable(data);
/* 137 */       this.executorService.execute(installRunnable);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void onCommence(String msg) {
/* 149 */       if (++this.progressCount > 50) {
/* 150 */         EventBus.getDefault().post(new UIMessageEvent("设备更新中，请耐心等待 " + msg + "%"));
/* 151 */         this.progressCount = 0;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void onSuccess(UpdateBean data) {
/* 162 */       setRun(false);
/* 163 */       this.timeOutCount = 0;
/* 164 */       if (data != null)
/* 165 */         if (data.isSuccess()) {
/* 166 */           int version = Integer.parseInt(data.getVersion());
/* 167 */           switch (data.getApkType()) {
/*     */             
/*     */             case "1":
/* 170 */               if (this.currentAdCloudVersion != 0 && version > this.currentAdCloudVersion) {
/* 171 */                 this.requestModel.deviceDownloadApkFile(data.getApkVersion(), data.getApkMd5(), this);
/*     */               }
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/*     */         } else {
/* 178 */           Logger.e("没有新版本apk更新", new Object[0]);
/* 179 */           EventBus.getDefault().post(new UIMessageEvent("当前软件为最新版本"));
/*     */         }  
/*     */     }
/*     */     
/*     */     public void onFailure(String msg) {}
/*     */     
/*     */     public void onError(String error) {}
/*     */     
/*     */     public void onComplete(String msg) {}
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\presenter\ApkUpdatePresenter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */