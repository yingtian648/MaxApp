/*     */ package com.using.adlibrary.config;
/*     */ 
/*     */ import android.content.Context;
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
/*     */ public class ConfigManger
/*     */ {
/*     */   private String baseUrl;
/*     */   private int rebootHour;
/*     */   private int rebootMinute;
/*     */   private int closeLcdBackLightTime;
/*     */   private int uploadProgramStatisticsTime;
/*     */   private int imageDisplayIntervalTime;
/*     */   private int deviceHeartIntervalTime;
/*     */   private int programHeartBeatIntervalTime;
/*     */   private int deviceHeartBeatDelayTime;
/*     */   private int programHeartBeatDelayTime;
/*     */   private int phoneProgramHeartBeatDelayTime;
/*     */   private int phoneProgramHeartBeatIntervalTime;
/*     */   private String materialPath;
/*     */   private String videoPath;
/*     */   private String imagePath;
/*     */   private String logoPath;
/*     */   private String screenFilePath;
/*     */   private String apkPath;
/*     */   private int deviceVolume;
/*     */   private String ossBaseUrl;
/*     */   private String ossBucketName;
/*     */   private String ossAccessKeyId;
/*     */   private String ossAccessKeySecret;
/*     */   private String ossObjectKeyApk;
/*     */   private String ossObjectKeyComputerImage;
/*     */   private String ossObjectKeyComputerVideo;
/*     */   private String ossObjectKeyPropertyImage;
/*     */   private String ossObjectKeyPropertyVideo;
/*     */   private static BaseConfigure nativeConfigure;
/*     */   private static ConfigManger configManger;
/*     */   
/*     */   public String getBaseUrl() {
/* 126 */     return this.baseUrl;
/*     */   }
/*     */   
/*     */   public int getRebootHour() {
/* 130 */     return this.rebootHour;
/*     */   }
/*     */   
/*     */   public int getRebootMinute() {
/* 134 */     return this.rebootMinute;
/*     */   }
/*     */   
/*     */   public int getCloseLcdBackLightTime() {
/* 138 */     return this.closeLcdBackLightTime;
/*     */   }
/*     */   
/*     */   public int getUploadProgramStatisticsTime() {
/* 142 */     return this.uploadProgramStatisticsTime;
/*     */   }
/*     */   
/*     */   public int getImageDisplayIntervalTime() {
/* 146 */     return this.imageDisplayIntervalTime;
/*     */   }
/*     */   
/*     */   public int getDeviceHeartIntervalTime() {
/* 150 */     return this.deviceHeartIntervalTime;
/*     */   }
/*     */   
/*     */   public int getProgramHeartBeatIntervalTime() {
/* 154 */     return this.programHeartBeatIntervalTime;
/*     */   }
/*     */   
/*     */   public int getDeviceHeartBeatDelayTime() {
/* 158 */     return this.deviceHeartBeatDelayTime;
/*     */   }
/*     */   
/*     */   public int getProgramHeartBeatDelayTime() {
/* 162 */     return this.programHeartBeatDelayTime;
/*     */   }
/*     */   
/*     */   public int getPhoneProgramHeartBeatDelayTime() {
/* 166 */     return this.phoneProgramHeartBeatDelayTime;
/*     */   }
/*     */   
/*     */   public int getPhoneProgramHeartBeatIntervalTime() {
/* 170 */     return this.phoneProgramHeartBeatIntervalTime;
/*     */   }
/*     */   
/*     */   public String getVideoPath() {
/* 174 */     return this.videoPath;
/*     */   }
/*     */   
/*     */   public String getImagePath() {
/* 178 */     return this.imagePath;
/*     */   }
/*     */   
/*     */   public String getLogoPath() {
/* 182 */     return this.logoPath;
/*     */   }
/*     */   
/*     */   public String getApkPath() {
/* 186 */     return this.apkPath;
/*     */   }
/*     */   
/*     */   public String getMaterialPath() {
/* 190 */     return this.materialPath;
/*     */   }
/*     */   
/*     */   public String getScreenFilePath() {
/* 194 */     return this.screenFilePath;
/*     */   }
/*     */   
/*     */   public int getDeviceVolume() {
/* 198 */     return this.deviceVolume;
/*     */   }
/*     */   
/*     */   public String getOssBucketName() {
/* 202 */     return this.ossBucketName;
/*     */   }
/*     */   
/*     */   public String getOssAccessKeyId() {
/* 206 */     return this.ossAccessKeyId;
/*     */   }
/*     */   
/*     */   public String getOssAccessKeySecret() {
/* 210 */     return this.ossAccessKeySecret;
/*     */   }
/*     */   
/*     */   public String getOssObjectKeyApk() {
/* 214 */     return this.ossObjectKeyApk;
/*     */   }
/*     */   
/*     */   public String getOssObjectKeyComputerImage() {
/* 218 */     return this.ossObjectKeyComputerImage;
/*     */   }
/*     */   
/*     */   public String getOssObjectKeyComputerVideo() {
/* 222 */     return this.ossObjectKeyComputerVideo;
/*     */   }
/*     */   
/*     */   public String getOssObjectKeyPropertyImage() {
/* 226 */     return this.ossObjectKeyPropertyImage;
/*     */   }
/*     */   
/*     */   public String getOssObjectKeyPropertyVideo() {
/* 230 */     return this.ossObjectKeyPropertyVideo;
/*     */   }
/*     */   
/*     */   public String getOssBaseUrl() {
/* 234 */     return this.ossBaseUrl;
/*     */   }
/*     */   
/*     */   public static ConfigManger getInstance() {
/* 238 */     if (configManger == null) {
/* 239 */       synchronized (ConfigManger.class) {
/* 240 */         if (configManger == null) {
/* 241 */           configManger = new ConfigManger();
/*     */         }
/*     */       } 
/*     */     }
/* 245 */     return configManger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init(Context context, int env) {
/* 250 */     if (env == 1) {
/* 251 */       nativeConfigure = new ProduceConfigureImpl(context);
/* 252 */     } else if (env == -1) {
/* 253 */       nativeConfigure = new TestConfigureImpl(context);
/*     */     } 
/*     */     
/* 256 */     updateConfig();
/*     */   }
/*     */   
/*     */   public BaseConfigure getConfigure() {
/* 260 */     return nativeConfigure;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateConfig() {
/* 265 */     this.baseUrl = nativeConfigure.getBaseUrl();
/* 266 */     this.rebootHour = nativeConfigure.getRebootHour();
/* 267 */     this.rebootMinute = nativeConfigure.getRebootMinute();
/* 268 */     this.closeLcdBackLightTime = nativeConfigure.getCloseLcdBackLightTime();
/* 269 */     this.uploadProgramStatisticsTime = nativeConfigure.getUploadProgramStatisticsTime();
/* 270 */     this.imageDisplayIntervalTime = nativeConfigure.getImageDisplayIntervalTime();
/* 271 */     this.deviceHeartIntervalTime = nativeConfigure.getDeviceHeartIntervalTime();
/* 272 */     this.programHeartBeatIntervalTime = nativeConfigure.getProgramHeartBeatIntervalTime();
/* 273 */     this.deviceHeartBeatDelayTime = nativeConfigure.getDeviceHeartBeatDelayTime();
/* 274 */     this.programHeartBeatDelayTime = nativeConfigure.getProgramHeartBeatDelayTime();
/* 275 */     this.phoneProgramHeartBeatDelayTime = nativeConfigure.getPhoneProgramHeartBeatDelayTime();
/* 276 */     this.phoneProgramHeartBeatIntervalTime = nativeConfigure.getPhoneProgramHeartBeatIntervalTime();
/*     */     
/* 278 */     this.videoPath = nativeConfigure.getVideoPath();
/* 279 */     this.apkPath = nativeConfigure.getApkPath();
/* 280 */     this.imagePath = nativeConfigure.getImagePath();
/* 281 */     this.logoPath = nativeConfigure.getLogoPath();
/* 282 */     this.screenFilePath = nativeConfigure.getScreenFilePath();
/* 283 */     this.deviceVolume = nativeConfigure.getDeviceVolume();
/* 284 */     this.materialPath = nativeConfigure.getMaterialPath();
/*     */     
/* 286 */     this.ossBaseUrl = nativeConfigure.getOssBaseUrl();
/* 287 */     this.ossBucketName = nativeConfigure.getOssBucketName();
/* 288 */     this.ossAccessKeyId = nativeConfigure.getOssAccessKeyId();
/* 289 */     this.ossAccessKeySecret = nativeConfigure.getOssAccessKeySecret();
/* 290 */     this.ossObjectKeyApk = nativeConfigure.getOssObjectKeyApk();
/*     */     
/* 292 */     this.ossObjectKeyComputerImage = nativeConfigure.getOssObjectKeyComputerImage();
/* 293 */     this.ossObjectKeyComputerVideo = nativeConfigure.getOssObjectKeyComputerVideo();
/*     */     
/* 295 */     this.ossObjectKeyPropertyImage = nativeConfigure.getOssObjectKeyPropertyImage();
/* 296 */     this.ossObjectKeyPropertyVideo = nativeConfigure.getOssObjectKeyPropertyVideo();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\config\ConfigManger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */