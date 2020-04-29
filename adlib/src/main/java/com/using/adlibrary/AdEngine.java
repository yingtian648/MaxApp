/*     */ package com.using.adlibrary;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.app.AlarmManager;
/*     */ import android.app.Application;
/*     */ import android.app.PendingIntent;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.database.sqlite.SQLiteDatabase;
/*     */ import android.os.Process;
/*     */ import android.support.v4.app.Fragment;
/*     */ import android.support.v4.app.FragmentTransaction;
/*     */ import android.support.v7.app.AppCompatActivity;
/*     */ import com.orhanobut.logger.AndroidLogAdapter;
/*     */ import com.orhanobut.logger.DiskLogAdapter;
/*     */ import com.orhanobut.logger.LogAdapter;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.dao.DaoMaster;
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.model.ReportModel;
/*     */ import com.using.adlibrary.service.AdService;
/*     */ import com.using.adlibrary.ui.fragment.ViewFragment;
/*     */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
/*     */ import java.lang.ref.WeakReference;
/*     */ import org.greenrobot.greendao.identityscope.IdentityScopeType;
/*     */ import org.xutils.x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdEngine
/*     */ {
/*     */   private WeakReference<Context> contextWeakReference;
/*     */   private SQLiteDatabase db;
/*     */   private DaoSession mDaoSession;
/*     */   private static AdEngine adEngine;
/*     */   private WeakReference<AppCompatActivity> appCompatActivityWeakReference;
/*     */   
/*     */   public static AdEngine getInstances() {
/*  46 */     if (adEngine == null) {
/*  47 */       synchronized (AdEngine.class) {
/*  48 */         if (adEngine == null) {
/*  49 */           adEngine = new AdEngine();
/*     */         }
/*     */       } 
/*     */     }
/*  53 */     return adEngine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Context context, Application application, int env) {
/*  64 */     this.contextWeakReference = new WeakReference<>(context);
/*  65 */     HwInterfaceManager.getInstance().setupContext(context);
/*  66 */     ConfigManger.getInstance().init(context, env);
/*  67 */     x.Ext.init(application);
/*  68 */     Logger.addLogAdapter((LogAdapter)new AndroidLogAdapter());
/*     */     
/*  70 */     setDatabase();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableLogSaveToLocal() {
/*  77 */     Logger.addLogAdapter((LogAdapter)new DiskLogAdapter());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startService() {
/*  84 */     Intent intent = new Intent(this.contextWeakReference.get(), AdService.class);
/*  85 */     ((Context)this.contextWeakReference.get()).startService(intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {
/*  92 */     Intent intent = new Intent(this.contextWeakReference.get(), AdService.class);
/*  93 */     ((Context)this.contextWeakReference.get()).stopService(intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadFragment(AppCompatActivity appCompatActivity, int id) {
/* 103 */     this.appCompatActivityWeakReference = new WeakReference<>(appCompatActivity);
/*     */     
/* 105 */     FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
/* 106 */     ViewFragment viewFragment = new ViewFragment();
/*     */     
/* 108 */     if (viewFragment.isAdded()) {
/* 109 */       transaction.remove((Fragment)viewFragment);
/*     */     }
/*     */ 
/*     */     
/* 113 */     transaction.replace(id, (Fragment)viewFragment)
/* 114 */       .show((Fragment)viewFragment);
/*     */     
/* 116 */     transaction.commitAllowingStateLoss();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"WrongConstant"})
/*     */   public void restartApp() {
/* 124 */     Intent intent = new Intent(this.contextWeakReference.get(), ((AppCompatActivity)this.appCompatActivityWeakReference.get()).getClass());
/* 125 */     PendingIntent restartIntent = PendingIntent.getActivity(this.contextWeakReference
/* 126 */         .get(), 0, intent, 268435456);
/*     */     
/* 128 */     AlarmManager mgr = (AlarmManager)((Context)this.contextWeakReference.get()).getSystemService("alarm");
/* 129 */     if (mgr != null) {
/*     */       
/* 131 */       mgr.set(1, System.currentTimeMillis() + 2000L, restartIntent);
/*     */       
/* 133 */       Process.killProcess(Process.myPid());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"WrongConstant"})
/*     */   public void startApp() {
/* 142 */     Intent intent = new Intent(this.contextWeakReference.get(), ((AppCompatActivity)this.appCompatActivityWeakReference.get()).getClass());
/* 143 */     intent.addFlags(268435456);
/* 144 */     ((Context)this.contextWeakReference.get()).startActivity(intent);
/*     */   }
/*     */   
/*     */   public Context getApplicationContext() {
/* 148 */     return this.contextWeakReference.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void uploadVolume(String uuid, int volume) {
/* 158 */     ConfigManger.getInstance().getConfigure().setDeviceVolume(volume);
/* 159 */     ReportModel.reportVolume(uuid, volume);
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
/*     */   private void setDatabase() {
/* 171 */     DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "program.db", null);
/*     */     
/* 173 */     this.db = mHelper.getWritableDatabase();
/*     */ 
/*     */     
/* 176 */     DaoMaster mDaoMaster = new DaoMaster(this.db);
/*     */     
/* 178 */     this.mDaoSession = mDaoMaster.newSession(IdentityScopeType.None);
/*     */   }
/*     */   
/*     */   public DaoSession getDaoSession() {
/* 182 */     return this.mDaoSession;
/*     */   }
/*     */   
/*     */   public SQLiteDatabase getDb() {
/* 186 */     return this.db;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\AdEngine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */