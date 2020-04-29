/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class PropertyUtils
/*    */ {
/*  7 */   private static volatile Method set = null;
/*  8 */   private static volatile Method get = null;
/*    */ 
/*    */   
/*    */   public static void set(String prop, String value) {
/*    */     try {
/* 13 */       if (null == set) {
/* 14 */         synchronized (PropertyUtils.class) {
/* 15 */           if (null == set) {
/* 16 */             Class<?> cls = Class.forName("android.os.SystemProperties");
/* 17 */             set = cls.getDeclaredMethod("set", new Class[] { String.class, String.class });
/*    */           } 
/*    */         } 
/*    */       }
/* 21 */       set.invoke(null, new Object[] { prop, value });
/* 22 */     } catch (Throwable e) {
/* 23 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static String get(String prop, String defaultvalue) {
/* 29 */     String value = defaultvalue;
/*    */     try {
/* 31 */       if (null == get) {
/* 32 */         synchronized (PropertyUtils.class) {
/* 33 */           if (null == get) {
/* 34 */             Class<?> cls = Class.forName("android.os.SystemProperties");
/* 35 */             get = cls.getDeclaredMethod("get", new Class[] { String.class, String.class });
/*    */           } 
/*    */         } 
/*    */       }
/* 39 */       value = (String)get.invoke(null, new Object[] { prop, defaultvalue });
/* 40 */     } catch (Throwable e) {
/* 41 */       e.printStackTrace();
/*    */     } 
/* 43 */     return value;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\PropertyUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */