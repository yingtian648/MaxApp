/*     */ package com.using.adlibrary.config;
/*     */ 
/*     */ import android.os.Environment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseConfigure
/*     */ {
/*     */   public String getBaseUrl() {
/*  11 */     return "http://ad.mwteck.com";
/*     */   }
/*     */   
/*     */   public String getOssBaseUrl() {
/*  15 */     return "http://oss-cn-shanghai.aliyuncs.com";
/*     */   }
/*     */   
/*     */   public String getOssBucketName() {
/*  19 */     return "mwteckad";
/*     */   }
/*     */   
/*     */   public String getOssAccessKeyId() {
/*  23 */     return "LTAIZcN4S8ogkMrR";
/*     */   }
/*     */   
/*     */   public String getOssAccessKeySecret() {
/*  27 */     return "K03UmjZAWGxJD7mJLPrpuc8wVdgpp6";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getOssObjectKeyApk();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getOssObjectKeyComputerImage();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getOssObjectKeyComputerVideo();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getOssObjectKeyPropertyImage();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getOssObjectKeyPropertyVideo();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getRebootHour();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getRebootMinute();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getCloseLcdBackLightTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getUploadProgramStatisticsTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getImageDisplayIntervalTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getDeviceHeartIntervalTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getProgramHeartBeatIntervalTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getDeviceHeartBeatDelayTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getProgramHeartBeatDelayTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getPhoneProgramHeartBeatDelayTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getPhoneProgramHeartBeatIntervalTime();
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVideoPath() {
/* 105 */     return Environment.getExternalStorageDirectory().getAbsolutePath() + "/material/";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getImagePath() {
/* 112 */     return Environment.getExternalStorageDirectory().getAbsolutePath() + "/material/";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLogoPath() {
/* 119 */     return Environment.getExternalStorageDirectory().getAbsolutePath() + "/logo/";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScreenFilePath() {
/* 126 */     return Environment.getExternalStorageDirectory().getAbsolutePath() + "/screenImage/";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApkPath() {
/* 133 */     return Environment.getExternalStorageDirectory().getAbsolutePath() + "/apk/";
/*     */   }
/*     */   
/*     */   public String getMaterialPath() {
/* 137 */     return Environment.getExternalStorageDirectory().getAbsolutePath() + "/material/";
/*     */   }
/*     */   
/*     */   public abstract int getDeviceVolume();
/*     */   
/*     */   public abstract void setBaseUrl(String paramString);
/*     */   
/*     */   public abstract void setRebootHour(int paramInt);
/*     */   
/*     */   public abstract void setCloseLcdBackLightTime(int paramInt);
/*     */   
/*     */   public abstract void setUploadProgramStatisticsTime(int paramInt);
/*     */   
/*     */   public abstract void setImageDisplayIntervalTime(int paramInt);
/*     */   
/*     */   public abstract void setDeviceHeartIntervalTime(int paramInt);
/*     */   
/*     */   public abstract void setProgramHeartBeatIntervalTime(int paramInt);
/*     */   
/*     */   public abstract void setDeviceHeartBeatDelayTime(int paramInt);
/*     */   
/*     */   public abstract void setProgramHeartBeatDelayTime(int paramInt);
/*     */   
/*     */   public abstract void setPhoneProgramHeartBeatDelayTime(int paramInt);
/*     */   
/*     */   public abstract void setPhoneProgramHeartBeatIntervalTime(int paramInt);
/*     */   
/*     */   public abstract void setVideoPath(String paramString);
/*     */   
/*     */   public abstract void setImagePath(String paramString);
/*     */   
/*     */   public abstract void setLogoPath(String paramString);
/*     */   
/*     */   public abstract void setScreenFilePath(String paramString);
/*     */   
/*     */   public abstract void setDeviceVolume(int paramInt);
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\config\BaseConfigure.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */