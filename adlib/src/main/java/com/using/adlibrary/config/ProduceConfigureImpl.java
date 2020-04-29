/*     */ package com.using.adlibrary.config;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.media.AudioManager;
/*     */ import com.using.adlibrary.utils.SPUtils;
/*     */ import java.lang.ref.WeakReference;
/*     */ 
/*     */ 
/*     */ public class ProduceConfigureImpl
/*     */   extends BaseConfigure
/*     */ {
/*     */   public WeakReference<Context> contextWeakReference;
/*     */   
/*     */   public ProduceConfigureImpl(Context context) {
/*  15 */     this.contextWeakReference = new WeakReference<>(context);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOssObjectKeyApk() {
/*  20 */     return "adcloud/apk";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOssObjectKeyComputerImage() {
/*  25 */     return "adcloud/image";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOssObjectKeyComputerVideo() {
/*  30 */     return "adcloud/video";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOssObjectKeyPropertyImage() {
/*  35 */     return "adcloud_property/image";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOssObjectKeyPropertyVideo() {
/*  40 */     return "adcloud_property/video";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRebootHour() {
/*  48 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.rebootHourKey, Integer.valueOf(5))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRebootMinute() {
/*  56 */     return (int)(1.0D + Math.random() * 30.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCloseLcdBackLightTime() {
/*  64 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.closeLcdBackLightTimeKey, Integer.valueOf(2))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUploadProgramStatisticsTime() {
/*  72 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.uploadProgramStatisticsTimeKey, Integer.valueOf(22))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getImageDisplayIntervalTime() {
/*  80 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.imageDisplayIntervalTimeKey, Integer.valueOf(12))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDeviceHeartIntervalTime() {
/*  88 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.deviceHeartIntervalTimeKey, Integer.valueOf(30000))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProgramHeartBeatIntervalTime() {
/*  96 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.programHeartBeatIntervalTimeKey, Integer.valueOf(30000))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDeviceHeartBeatDelayTime() {
/* 104 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.deviceHeartBeatDelayTimeKey, Integer.valueOf(5))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProgramHeartBeatDelayTime() {
/* 112 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.programHeartBeatDelayTimeKey, Integer.valueOf(100))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPhoneProgramHeartBeatDelayTime() {
/* 120 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.programHeartBeatDelayTimeKey, Integer.valueOf(100))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPhoneProgramHeartBeatIntervalTime() {
/* 128 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.programHeartBeatDelayTimeKey, Integer.valueOf(30000))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDeviceVolume() {
/* 137 */     int volume = ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.programHeartBeatDelayTimeKey, Integer.valueOf(10))).intValue();
/* 138 */     if (volume == 10) {
/* 139 */       AudioManager audioManager = (AudioManager)((Context)this.contextWeakReference.get()).getSystemService("audio");
/* 140 */       double maxMusic = audioManager.getStreamMaxVolume(3);
/* 141 */       double curM = audioManager.getStreamVolume(3);
/*     */       
/* 143 */       volume = Double.valueOf(Math.ceil(curM / maxMusic * 100.0D)).intValue();
/*     */     } 
/*     */     
/* 146 */     return volume;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaseUrl(String baseUrl) {
/* 151 */     SPUtils.put(this.contextWeakReference.get(), Constant.baseUrlKey, baseUrl);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRebootHour(int rebootHour) {
/* 156 */     SPUtils.put(this.contextWeakReference.get(), Constant.rebootHourKey, Integer.valueOf(rebootHour));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCloseLcdBackLightTime(int closeLcdBackLightTime) {
/* 161 */     SPUtils.put(this.contextWeakReference.get(), Constant.closeLcdBackLightTimeKey, Integer.valueOf(closeLcdBackLightTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUploadProgramStatisticsTime(int uploadProgramStatisticsTime) {
/* 166 */     SPUtils.put(this.contextWeakReference.get(), Constant.uploadProgramStatisticsTimeKey, Integer.valueOf(uploadProgramStatisticsTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImageDisplayIntervalTime(int imageDisplayIntervalTime) {
/* 171 */     SPUtils.put(this.contextWeakReference.get(), Constant.imageDisplayIntervalTimeKey, Integer.valueOf(imageDisplayIntervalTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeviceHeartIntervalTime(int deviceHeartIntervalTime) {
/* 176 */     SPUtils.put(this.contextWeakReference.get(), Constant.deviceHeartIntervalTimeKey, Integer.valueOf(deviceHeartIntervalTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgramHeartBeatIntervalTime(int programHeartBeatIntervalTime) {
/* 181 */     SPUtils.put(this.contextWeakReference.get(), Constant.programHeartBeatIntervalTimeKey, Integer.valueOf(programHeartBeatIntervalTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeviceHeartBeatDelayTime(int deviceHeartBeatDelayTime) {
/* 186 */     SPUtils.put(this.contextWeakReference.get(), Constant.deviceHeartBeatDelayTimeKey, Integer.valueOf(deviceHeartBeatDelayTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgramHeartBeatDelayTime(int programHeartBeatDelayTime) {
/* 191 */     SPUtils.put(this.contextWeakReference.get(), Constant.programHeartBeatDelayTimeKey, Integer.valueOf(programHeartBeatDelayTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhoneProgramHeartBeatDelayTime(int phoneProgramHeartBeatDelayTime) {
/* 196 */     SPUtils.put(this.contextWeakReference.get(), Constant.phoneProgramHeartBeatDelayTimeKey, Integer.valueOf(phoneProgramHeartBeatDelayTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPhoneProgramHeartBeatIntervalTime(int phoneProgramHeartBeatIntervalTime) {
/* 201 */     SPUtils.put(this.contextWeakReference.get(), Constant.phoneProgramHeartBeatIntervalTimeKey, Integer.valueOf(phoneProgramHeartBeatIntervalTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVideoPath(String videoPath) {
/* 206 */     SPUtils.put(this.contextWeakReference.get(), Constant.videoPathKey, videoPath);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImagePath(String imagePath) {
/* 211 */     SPUtils.put(this.contextWeakReference.get(), Constant.imagePathKey, imagePath);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLogoPath(String logoPath) {
/* 216 */     SPUtils.put(this.contextWeakReference.get(), Constant.logoPathKey, logoPath);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScreenFilePath(String screenFilePath) {
/* 221 */     SPUtils.put(this.contextWeakReference.get(), Constant.screenFilePathKey, screenFilePath);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeviceVolume(int deviceVolume) {
/* 226 */     SPUtils.put(this.contextWeakReference.get(), Constant.deviceVolume, Integer.valueOf(deviceVolume));
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\config\ProduceConfigureImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */