/*    */ package com.using.adlibrary.config;
/*    */ 
/*    */ import android.content.Context;
/*    */ import com.using.adlibrary.utils.SPUtils;
/*    */ 
/*    */ 
/*    */ public class TestConfigureImpl
/*    */   extends ProduceConfigureImpl
/*    */ {
/*    */   public TestConfigureImpl(Context context) {
/* 11 */     super(context);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBaseUrl() {
/* 16 */     return "http://47.100.138.12:8081";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOssObjectKeyApk() {
/* 21 */     return "adcloud_test/apk";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRebootHour() {
/* 26 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.rebootHourKey, Integer.valueOf(5))).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRebootMinute() {
/* 31 */     return 35;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCloseLcdBackLightTime() {
/* 36 */     return ((Integer)SPUtils.get(this.contextWeakReference.get(), Constant.closeLcdBackLightTimeKey, Integer.valueOf(2))).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOssObjectKeyComputerImage() {
/* 41 */     return "adcloud_test/image";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOssObjectKeyComputerVideo() {
/* 46 */     return "adcloud_test/video";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOssObjectKeyPropertyImage() {
/* 51 */     return "adcloud_property_test/image";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOssObjectKeyPropertyVideo() {
/* 56 */     return "adcloud_property_test/video";
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\config\TestConfigureImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */