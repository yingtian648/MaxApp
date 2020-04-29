/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.pm.PackageInfo;
/*    */ import android.content.pm.PackageManager;
/*    */ import android.util.Log;
/*    */ import com.using.adlibrary.AdEngine;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AppControlUtils
/*    */ {
/*    */   public static void clear() {
/* 21 */     List<PackageInfo> app = getAllApps(AdEngine.getInstances().getApplicationContext());
/* 22 */     if (app.size() > 0) {
/* 23 */       for (PackageInfo packageInfo : app) {
/* 24 */         deleteApp(packageInfo.packageName);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static List<PackageInfo> getAllApps(Context context) {
/* 33 */     List<PackageInfo> apps = new ArrayList<>();
/* 34 */     PackageManager packageManager = context.getPackageManager();
/*    */     
/* 36 */     List<PackageInfo> paklist = packageManager.getInstalledPackages(0);
/* 37 */     for (int i = 0; i < paklist.size(); i++) {
/* 38 */       PackageInfo pak = paklist.get(i);
/*    */       
/* 40 */       if ((pak.applicationInfo.flags & 0x1) <= 0)
/* 41 */         Log.e("main", "1111APP名字：" + pak.applicationInfo.loadLabel(packageManager).toString() + "      " + pak.packageName); 
/* 42 */       if ((pak.applicationInfo.flags & 0x1) <= 0 && 
/* 43 */         !pak.packageName.equals("com.example.usw.surveillance") && 
/* 44 */         !pak.packageName.equals("com.cootek.smartinputv5") && 
/* 45 */         !pak.packageName.equals("com.cootek.smartinputv5.language.chs") && 
/* 46 */         !pak.packageName.equals("com.example.usw.safetytest") && 
/* 47 */         !pak.packageName.equals("com.using.wifiadb") && 
/* 48 */         !pak.packageName.equals("com.lenovo.anyshare") && 
/* 49 */         !pak.packageName.equals("com.usw.adplayer") && 
/* 50 */         !pak.packageName.equals("com.example.usw.safetytest") && 
/* 51 */         !pak.packageName.equals("com.example.administrator.ewandwritedemo") && 
/* 52 */         !pak.packageName.equals("com.example.administrator.powerpayment.activity") && 
/* 53 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("EWandWriteDemo") && 
/* 54 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("SafetyTest") && 
/* 55 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("触宝输入法") && 
/* 56 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("AdCloud") && 
/* 57 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("触宝(TouchPal)拼音/笔画语言包") && 
/* 58 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("WIFIADB") && 
/* 59 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("茄子快传") && 
/* 60 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("梯视达") && 
/* 61 */         !pak.applicationInfo.loadLabel(packageManager).toString().equals("市民驿站")) {
/* 62 */         apps.add(pak);
/* 63 */         Log.e("main", "APP名删除:" + pak.packageName);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 68 */     return apps;
/*    */   }
/*    */   
/*    */   private static void deleteApp(final String packageName) {
/* 72 */     (new Thread(new Runnable()
/*    */         {
/*    */           public void run() {
/* 75 */             Log.e("main", "包名开始卸载" + packageName);
/*    */             
/* 77 */             ShellUtils.CommandResult reboot = ShellUtils.execCommand("pm uninstall " + packageName, true);
/*    */           }
/* 79 */         })).start();
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\AppControlUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */