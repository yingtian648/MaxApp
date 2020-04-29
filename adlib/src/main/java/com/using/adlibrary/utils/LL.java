/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.util.Log;
/*    */ import com.orhanobut.logger.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LL
/*    */ {
/*    */   private static boolean DEBUG = true;
/*    */   
/*    */   public static void setDEBUG(boolean DEBUG) {
/* 16 */     LL.DEBUG = DEBUG;
/*    */   }
/*    */   
/*    */   public static void d(String tag, String msg) {
/* 20 */     if (DEBUG) {
/* 21 */       Log.d(tag, msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void v(String tag, String msg) {
/* 26 */     if (DEBUG) {
/* 27 */       Log.v(tag, msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void i(String tag, String msg) {
/* 32 */     if (DEBUG) {
/* 33 */       Log.i(tag, msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void w(String tag, String msg) {
/* 38 */     if (DEBUG) {
/* 39 */       Log.w(tag, msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void e(String tag, String msg) {
/* 44 */     if (DEBUG) {
/* 45 */       Log.e(tag, msg);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void showLogCompletion(String log, int showCount) {
/* 56 */     if (log.length() > showCount) {
/* 57 */       String show = log.substring(0, showCount);
/* 58 */       Logger.e(show + "", new Object[0]);
/* 59 */       if (log.length() - showCount > showCount) {
/* 60 */         String partLog = log.substring(showCount, log.length());
/* 61 */         showLogCompletion(partLog, showCount);
/*    */       } else {
/* 63 */         String surplusLog = log.substring(showCount, log.length());
/* 64 */         Logger.e(surplusLog + "", new Object[0]);
/*    */       } 
/*    */     } else {
/* 67 */       Logger.e(log + "", new Object[0]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\LL.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */