/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.app.ActivityManager;
/*     */ import android.app.AlarmManager;
/*     */ import android.app.PendingIntent;
/*     */ import android.content.ComponentName;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.ApplicationInfo;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.pm.ResolveInfo;
/*     */ import android.os.Process;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AppUtils
/*     */ {
/*     */   public static boolean isForeground(Context context, String className) {
/*  28 */     if (context == null || TextUtils.isEmpty(className)) {
/*  29 */       return false;
/*     */     }
/*     */     
/*  32 */     ActivityManager am = (ActivityManager)context.getSystemService("activity");
/*  33 */     List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
/*  34 */     if (list != null && list.size() > 0) {
/*  35 */       ComponentName cpn = ((ActivityManager.RunningTaskInfo)list.get(0)).topActivity;
/*  36 */       if (className.equals(cpn.getClassName())) {
/*  37 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  41 */     return false;
/*     */   }
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
/*     */   public static boolean isAppRunning(Context context, String packageName) {
/*  54 */     ActivityManager am = (ActivityManager)context.getSystemService("activity");
/*  55 */     List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
/*  56 */     if (list.size() <= 0) {
/*  57 */       return false;
/*     */     }
/*  59 */     for (ActivityManager.RunningTaskInfo info : list) {
/*  60 */       if (info.baseActivity.getPackageName().equals(packageName)) {
/*  61 */         return true;
/*     */       }
/*     */     } 
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getPackageUid(Context context, String packageName) {
/*     */     try {
/*  71 */       ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
/*  72 */       if (applicationInfo != null) {
/*  73 */         Log.e("TAG", String.valueOf(applicationInfo.uid));
/*  74 */         return applicationInfo.uid;
/*     */       } 
/*  76 */     } catch (Exception e) {
/*  77 */       return -1;
/*     */     } 
/*  79 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isProcessRunning(Context context, int uid) {
/*  91 */     ActivityManager am = (ActivityManager)context.getSystemService("activity");
/*  92 */     List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
/*  93 */     if (runningServiceInfos.size() > 0) {
/*  94 */       for (ActivityManager.RunningServiceInfo appProcess : runningServiceInfos) {
/*  95 */         if (uid == appProcess.uid) {
/*  96 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void doStartApplicationWithPackageName(Context context, String packagename) {
/* 106 */     PackageInfo packageinfo = null;
/*     */     try {
/* 108 */       packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
/* 109 */     } catch (PackageManager.NameNotFoundException e) {
/* 110 */       e.printStackTrace();
/*     */     } 
/* 112 */     if (packageinfo == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 117 */     Intent resolveIntent = new Intent("android.intent.action.MAIN", null);
/* 118 */     resolveIntent.addCategory("android.intent.category.LAUNCHER");
/* 119 */     resolveIntent.setPackage(packageinfo.packageName);
/*     */ 
/*     */ 
/*     */     
/* 123 */     List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
/*     */     
/* 125 */     ResolveInfo resolveinfo = resolveinfoList.iterator().next();
/* 126 */     if (resolveinfo != null) {
/*     */       
/* 128 */       String packageName = resolveinfo.activityInfo.packageName;
/*     */       
/* 130 */       String className = resolveinfo.activityInfo.name;
/*     */       
/* 132 */       Intent intent = new Intent("android.intent.action.MAIN");
/* 133 */       intent.addCategory("android.intent.category.LAUNCHER");
/*     */ 
/*     */       
/* 136 */       ComponentName cn = new ComponentName(packageName, className);
/*     */       
/* 138 */       intent.setComponent(cn);
/* 139 */       intent.setFlags(268435456);
/* 140 */       context.startActivity(intent);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SuppressLint({"WrongConstant"})
/*     */   public static void restartApp(Context context, Class c) {
/* 146 */     Intent intent = new Intent(context, c);
/* 147 */     PendingIntent restartIntent = PendingIntent.getActivity(context, 0, intent, 268435456);
/*     */ 
/*     */     
/* 150 */     AlarmManager mgr = (AlarmManager)context.getSystemService("alarm");
/* 151 */     if (mgr != null) {
/*     */       
/* 153 */       mgr.set(1, System.currentTimeMillis() + 10L, restartIntent);
/*     */       
/* 155 */       Process.killProcess(Process.myPid());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\AppUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */