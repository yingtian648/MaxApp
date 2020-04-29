/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.SharedPreferences;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SPUtils
/*    */ {
/*    */   private static final String FILE_NAME = "my_sp";
/*    */   
/*    */   public static void put(Context context, String key, Object obj) {
/* 18 */     SharedPreferences sp = context.getSharedPreferences("my_sp", 0);
/* 19 */     SharedPreferences.Editor editor = sp.edit();
/* 20 */     if (obj instanceof Boolean) {
/* 21 */       editor.putBoolean(key, ((Boolean)obj).booleanValue());
/* 22 */     } else if (obj instanceof Float) {
/* 23 */       editor.putFloat(key, ((Float)obj).floatValue());
/* 24 */     } else if (obj instanceof Integer) {
/* 25 */       editor.putInt(key, ((Integer)obj).intValue());
/* 26 */     } else if (obj instanceof Long) {
/* 27 */       editor.putLong(key, ((Long)obj).longValue());
/*    */     } else {
/* 29 */       editor.putString(key, (String)obj);
/*    */     } 
/* 31 */     editor.commit();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Object get(Context context, String key, Object defaultObj) {
/* 39 */     SharedPreferences sp = context.getSharedPreferences("my_sp", 0);
/* 40 */     if (defaultObj instanceof Boolean)
/* 41 */       return Boolean.valueOf(sp.getBoolean(key, ((Boolean)defaultObj).booleanValue())); 
/* 42 */     if (defaultObj instanceof Float)
/* 43 */       return Float.valueOf(sp.getFloat(key, ((Float)defaultObj).floatValue())); 
/* 44 */     if (defaultObj instanceof Integer)
/* 45 */       return Integer.valueOf(sp.getInt(key, ((Integer)defaultObj).intValue())); 
/* 46 */     if (defaultObj instanceof Long)
/* 47 */       return Long.valueOf(sp.getLong(key, ((Long)defaultObj).longValue())); 
/* 48 */     if (defaultObj instanceof String) {
/* 49 */       return sp.getString(key, (String)defaultObj);
/*    */     }
/* 51 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void remove(Context context, String key) {
/* 58 */     SharedPreferences sp = context.getSharedPreferences("my_sp", 0);
/* 59 */     SharedPreferences.Editor editor = sp.edit();
/* 60 */     editor.remove(key);
/* 61 */     editor.apply();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Map<String, ?> getAll(Context context) {
/* 69 */     SharedPreferences sp = context.getSharedPreferences("my_sp", 0);
/* 70 */     Map<String, ?> map = sp.getAll();
/* 71 */     return map;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void clear(Context context) {
/* 78 */     SharedPreferences sp = context.getSharedPreferences("my_sp", 0);
/* 79 */     SharedPreferences.Editor editor = sp.edit();
/* 80 */     editor.clear();
/* 81 */     editor.apply();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean contains(Context context, String key) {
/* 88 */     SharedPreferences sp = context.getSharedPreferences("my_sp", 0);
/* 89 */     return sp.contains(key);
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\SPUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */