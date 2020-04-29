/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.os.Build;
/*    */ import java.text.SimpleDateFormat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UuidUtil
/*    */ {
/*    */   public static String getDeviceSN() {
/* 13 */     return Build.SERIAL;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getCurrentTime() {
/* 24 */     SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SS");
/*    */     
/* 26 */     long now = System.currentTimeMillis();
/*    */     
/* 28 */     String strCurrentTime = aDate.format(Long.valueOf(now));
/*    */     
/* 30 */     return strCurrentTime;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\UuidUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */