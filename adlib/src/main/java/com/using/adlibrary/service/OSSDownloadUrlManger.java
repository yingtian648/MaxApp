/*     */ package com.using.adlibrary.service;
/*     */ 
/*     */ import android.content.Context;
/*     */ import com.alibaba.sdk.android.oss.ClientConfiguration;
/*     */ import com.alibaba.sdk.android.oss.ClientException;
/*     */ import com.alibaba.sdk.android.oss.OSSClient;
/*     */ import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
/*     */ import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
/*     */ import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OSSDownloadUrlManger
/*     */ {
/*     */   private OSSClient ossClient;
/*     */   private static OSSDownloadUrlManger instance;
/*     */   
/*     */   private OSSDownloadUrlManger() {
/*  24 */     initOSSClient(AdEngine.getInstances().getApplicationContext());
/*     */   }
/*     */   
/*     */   public static OSSDownloadUrlManger getInstance() {
/*  28 */     if (instance == null) {
/*  29 */       synchronized (OSSDownloadUrlManger.class) {
/*  30 */         if (instance == null) {
/*  31 */           instance = new OSSDownloadUrlManger();
/*     */         }
/*     */       } 
/*     */     }
/*  35 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   private void initOSSClient(Context context) {
/*  40 */     ClientConfiguration conf = new ClientConfiguration();
/*     */     
/*  42 */     conf.setConnectionTimeout(15000);
/*     */     
/*  44 */     conf.setSocketTimeout(15000);
/*     */     
/*  46 */     conf.setMaxConcurrentRequest(5);
/*     */     
/*  48 */     conf.setMaxErrorRetry(2);
/*     */     
/*  50 */     OSSCustomSignerCredentialProvider provider = new OSSCustomSignerCredentialProvider()
/*     */       {
/*     */         public String signContent(String content) {
/*  53 */           return OSSUtils.sign(ConfigManger.getInstance().getOssAccessKeyId(), 
/*  54 */               ConfigManger.getInstance().getOssAccessKeySecret(), content);
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*  59 */     this.ossClient = new OSSClient(context, ConfigManger.getInstance().getOssBaseUrl(), (OSSCredentialProvider)provider, conf);
/*     */   }
/*     */   
/*     */   public OSSClient getOssClient() {
/*  63 */     return this.ossClient;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileDownloadUrl(String fileName, String fileType) {
/*  71 */     String signedURLString, objectKey, bucketName = ConfigManger.getInstance().getOssBucketName();
/*  72 */     long expiredTimeInSeconds = 60L;
/*     */ 
/*     */     
/*  75 */     if ("1".equals(fileType)) {
/*  76 */       objectKey = ConfigManger.getInstance().getOssObjectKeyComputerImage() + "/" + fileName;
/*  77 */     } else if ("2".equals(fileType)) {
/*  78 */       objectKey = ConfigManger.getInstance().getOssObjectKeyComputerVideo() + "/" + fileName;
/*     */     } else {
/*  80 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/*  84 */       signedURLString = this.ossClient.presignConstrainedObjectURL(bucketName, objectKey, expiredTimeInSeconds);
/*  85 */     } catch (ClientException e) {
/*  86 */       e.printStackTrace();
/*  87 */       return null;
/*     */     } 
/*  89 */     return signedURLString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPhoneFileDownloadUrl(String fileName, String fileType) {
/*  97 */     String signedURLString, objectKey, bucketName = ConfigManger.getInstance().getOssBucketName();
/*  98 */     long expiredTimeInSeconds = 60L;
/*     */ 
/*     */     
/* 101 */     if ("1".equals(fileType)) {
/* 102 */       objectKey = ConfigManger.getInstance().getOssObjectKeyPropertyImage() + "/" + fileName;
/* 103 */     } else if ("2".equals(fileType)) {
/* 104 */       objectKey = ConfigManger.getInstance().getOssObjectKeyPropertyVideo() + "/" + fileName;
/*     */     } else {
/* 106 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 110 */       signedURLString = this.ossClient.presignConstrainedObjectURL(bucketName, objectKey, expiredTimeInSeconds);
/* 111 */     } catch (ClientException e) {
/* 112 */       e.printStackTrace();
/* 113 */       return null;
/*     */     } 
/* 115 */     return signedURLString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApkFileDownloadUrl(String fileName) {
/* 123 */     String signedURLString, bucketName = ConfigManger.getInstance().getOssBucketName();
/* 124 */     long expiredTimeInSeconds = 60L;
/*     */     
/* 126 */     String objectKey = ConfigManger.getInstance().getOssObjectKeyApk() + "/" + fileName;
/*     */     
/*     */     try {
/* 129 */       signedURLString = this.ossClient.presignConstrainedObjectURL(bucketName, objectKey, expiredTimeInSeconds);
/* 130 */     } catch (ClientException e) {
/* 131 */       e.printStackTrace();
/* 132 */       return null;
/*     */     } 
/* 134 */     return signedURLString;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\service\OSSDownloadUrlManger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */