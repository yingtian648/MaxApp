/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.net.ConnectivityManager;
/*    */ import android.net.NetworkInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NetWorkUtils
/*    */ {
/*    */   public static boolean networkState = true;
/*    */   
/*    */   public static boolean isNetworkConnected(Context context) {
/* 19 */     if (context != null) {
/*    */       
/* 21 */       ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
/*    */       
/* 23 */       NetworkInfo networkInfo = manager.getActiveNetworkInfo();
/*    */       
/* 25 */       if (networkInfo != null)
/* 26 */         return networkInfo.isAvailable(); 
/*    */     } 
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isMobileConnected(Context context) {
/* 39 */     if (context != null) {
/*    */       
/* 41 */       ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
/*    */       
/* 43 */       NetworkInfo networkInfo = manager.getActiveNetworkInfo();
/*    */       
/* 45 */       if (networkInfo != null && networkInfo.getType() == 0)
/* 46 */         return networkInfo.isAvailable(); 
/*    */     } 
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getConnectedType(Context context) {
/* 59 */     if (context != null) {
/*    */       
/* 61 */       ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
/*    */       
/* 63 */       NetworkInfo networkInfo = manager.getActiveNetworkInfo();
/* 64 */       if (networkInfo != null && networkInfo.isAvailable())
/*    */       {
/* 66 */         return networkInfo.getType();
/*    */       }
/*    */     } 
/* 69 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getAPNType(Context context) {
/* 80 */     int netType = 0;
/*    */     
/* 82 */     ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
/*    */     
/* 84 */     NetworkInfo networkInfo = manager.getActiveNetworkInfo();
/*    */     
/* 86 */     if (networkInfo == null) {
/* 87 */       return netType;
/*    */     }
/*    */     
/* 90 */     int nType = networkInfo.getType();
/* 91 */     if (nType == 1) {
/*    */       
/* 93 */       netType = 1;
/* 94 */     } else if (nType == 9) {
/* 95 */       netType = 2;
/*    */     } 
/* 97 */     return netType;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\NetWorkUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */