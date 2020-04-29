/*     */ package com.using.adlibrary.service;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Service;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.hardware.display.DisplayManager;
/*     */ import android.os.Build;
/*     */ import android.os.Environment;
/*     */ import android.os.Handler;
/*     */ import android.os.IBinder;
/*     */ import android.os.PowerManager;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.text.format.DateFormat;
/*     */ import android.util.Log;
/*     */ import android.view.Display;
/*     */ import android.widget.Toast;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.bean.event.ViewFragmentEvent;
/*     */ import com.using.adlibrary.bean.utils.DaoOperation;
/*     */ import com.using.adlibrary.callback.IPresenter;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.factory.DeviceUuidFactory;
/*     */ import com.using.adlibrary.model.ConnectRequestModel;
/*     */ import com.using.adlibrary.presenter.ConnectOptimizationPresenter;
/*     */ import com.using.adlibrary.presenter.PhoneOptimizationPresenter;
/*     */ import com.using.adlibrary.presenter.RegisterPresenter;
/*     */ import com.using.adlibrary.receiver.AlarmReceiver;
/*     */ import com.using.adlibrary.utils.AlarmManagerUtils;
/*     */ import com.using.adlibrary.utils.FileUtils;
/*     */ import com.using.adlibrary.utils.SPUtils;
/*     */ import com.using.adlibrary.utils.ShellUtils;
/*     */ import com.using.adlibrary.utils.VoiceManager;
/*     */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
/*     */ import id.zelory.compressor.Compressor;
/*     */ import java.io.File;
/*     */ import org.greenrobot.eventbus.EventBus;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdService
/*     */   extends Service
/*     */   implements IPresenter
/*     */ {
/*     */   private String mUuid;
/*     */   private Handler mHandler;
/*     */   private VoiceManager voiceManager;
/*     */   private RegisterPresenter registerPresenter;
/*     */   private PhoneOptimizationPresenter phoneOptimizationPresenter;
/*     */   private ConnectOptimizationPresenter connectOptimizationPresenter;
/*     */   
/*     */   @Nullable
/*     */   public IBinder onBind(Intent intent) {
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCreate() {
/*  74 */     super.onCreate();
/*  75 */     this.mHandler = new Handler();
/*  76 */     this.mUuid = DeviceUuidFactory.getInstance().getDeviceUuid();
/*     */     
/*  78 */     ConnectRequestModel request = new ConnectRequestModel();
/*     */     
/*  80 */     this.registerPresenter = new RegisterPresenter(this, request);
/*  81 */     this.registerPresenter.start();
/*     */ 
/*     */     
/*  84 */     this.phoneOptimizationPresenter = new PhoneOptimizationPresenter(this.mUuid, this);
/*  85 */     this.connectOptimizationPresenter = new ConnectOptimizationPresenter(this.mUuid, this);
/*     */     
/*  87 */     this.voiceManager = new VoiceManager((Context)this);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     releaseStorage();
/*  93 */     releaseLoggerStorage();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int onStartCommand(Intent intent, int flags, int startId) {
/*  99 */     Logger.e("调用了 Service onStartCommand()方法", new Object[0]);
/* 100 */     return super.onStartCommand(intent, flags, startId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDestroy() {
/*     */     try {
/* 106 */       if (this.registerPresenter != null) {
/* 107 */         this.registerPresenter.close();
/* 108 */         this.registerPresenter.logoutAction();
/*     */       } 
/* 110 */       if (this.phoneOptimizationPresenter != null) {
/* 111 */         this.phoneOptimizationPresenter.close();
/*     */       }
/*     */       
/* 114 */       if (this.connectOptimizationPresenter != null) {
/* 115 */         this.connectOptimizationPresenter.close();
/*     */       
/*     */       }
/*     */     }
/* 119 */     catch (Exception e) {
/* 120 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 123 */     Logger.e("调用了 Service onDestroy()方法", new Object[0]);
/* 124 */     super.onDestroy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void uiInfo(String msg) {
/* 132 */     Intent intent = new Intent("UI_INFO_ACTION");
/* 133 */     intent.putExtra("UI_INFO_ACTION", msg);
/* 134 */     sendBroadcast(intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logoShow(String imagePath) {
/* 143 */     Intent intent = new Intent("LOGO_ACTION");
/* 144 */     intent.putExtra("LOGO_ACTION", imagePath);
/* 145 */     sendBroadcast(intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void titleShow(String title) {
/* 154 */     Intent intent = new Intent("TITLE_ACTION");
/* 155 */     intent.putExtra("TITLE_ACTION", title);
/* 156 */     sendBroadcast(intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateProgramList(String s) {
/* 165 */     Intent intent = new Intent("PLAY_LIST_DATA");
/* 166 */     intent.putExtra("PLAY_LIST_DATA", s);
/* 167 */     sendBroadcast(intent);
/*     */     
/* 169 */     EventBus.getDefault().post(new ViewFragmentEvent("updateui"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void currentTitleList(String jsonPlayListArray) {
/* 175 */     if (jsonPlayListArray != null && 
/* 176 */       jsonPlayListArray.equals("0")) {
/* 177 */       titleShow("0");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cmd(int cmd, int value) {
/* 185 */     Logger.e("当前接收到的命令为： " + cmd, new Object[0]);
/*     */     try {
/* 187 */       switch (cmd) {
/*     */         
/*     */         case 100:
/* 190 */           this.registerPresenter.requestPushMessageAction("执行更新节目单列表命令", "3");
/*     */ 
/*     */           
/* 193 */           this.connectOptimizationPresenter.straightwayStart();
/*     */           break;
/*     */ 
/*     */         
/*     */         case 101:
/* 198 */           Logger.e(" 重启设备", new Object[0]);
/* 199 */           this.registerPresenter.requestPushMessageAction("执行了设备重启命令", "3");
/*     */           
/* 201 */           this.mHandler.postDelayed(new Runnable()
/*     */               {
/*     */                 public void run() {
/* 204 */                   HwInterfaceManager.getInstance().reboot();
/*     */                 }
/*     */               },  2000L);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 103:
/* 211 */           DaoOperation.deleteAll();
/* 212 */           DaoOperation.deletePhoneAll();
/* 213 */           SPUtils.clear(getApplicationContext());
/* 214 */           FileUtils.deleteFileUnderFolders(new File(ConfigManger.getInstance().getMaterialPath()));
/*     */           
/* 216 */           updateProgramList("");
/* 217 */           Logger.e(" 执行了设备清空数据库和删除素材资源", new Object[0]);
/* 218 */           this.registerPresenter.requestPushMessageAction("执行了设备清空数据库和删除素材资源", "3");
/*     */           
/* 220 */           this.mHandler.postDelayed(new Runnable()
/*     */               {
/*     */                 public void run() {
/* 223 */                   HwInterfaceManager.getInstance().reboot();
/*     */                 }
/*     */               },  2000L);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 104:
/* 230 */           Logger.e(" 恢复播放", new Object[0]);
/* 231 */           this.registerPresenter.requestPushMessageAction("执行了设备恢复播放命令(暂未使用)", "3");
/*     */           break;
/*     */ 
/*     */         
/*     */         case 105:
/* 236 */           if (isScreenOn()) {
/* 237 */             HwInterfaceManager.getInstance().setLcdBackLight(false);
/* 238 */             this.registerPresenter.requestPushMessageAction("执行了设备休眠命令", "3");
/*     */           } 
/* 240 */           Logger.e("休眠终端", new Object[0]);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 106:
/* 245 */           if (!isScreenOn()) {
/* 246 */             HwInterfaceManager.getInstance().reboot();
/* 247 */             this.registerPresenter.requestPushMessageAction("执行了设备唤醒命令", "3");
/*     */           } 
/* 249 */           Logger.e(" 唤醒终端", new Object[0]);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 107:
/* 254 */           FileUtils.deleteFileUnderFolders(new File(ConfigManger.getInstance().getApkPath()));
/* 255 */           this.registerPresenter.requestPushMessageAction("执行了删除冗余apk命令", "3");
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 108:
/* 261 */           this.registerPresenter.requestPushMessageAction("执行了升级AdCloud apk命令", "3");
/*     */           break;
/*     */ 
/*     */         
/*     */         case 109:
/* 266 */           this.registerPresenter.requestPushMessageAction("执行了更新已使用流量命令", "3");
/*     */           break;
/*     */ 
/*     */         
/*     */         case 110:
/* 271 */           this.phoneOptimizationPresenter.straightwayStart();
/*     */           break;
/*     */         
/*     */         case 99:
/* 275 */           screenCap();
/*     */           break;
/*     */         
/*     */         case 111:
/* 279 */           Logger.e("设置当前音量为： " + value, new Object[0]);
/* 280 */           SPUtils.put(getApplicationContext(), "voice", Integer.valueOf(value));
/* 281 */           this.voiceManager.setSystemVoice(value);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/* 286 */     } catch (Exception e) {
/* 287 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void restartActivity(Activity activity) {
/* 292 */     if (Build.VERSION.SDK_INT >= 11) {
/* 293 */       activity.recreate();
/*     */     } else {
/* 295 */       activity.finish();
/* 296 */       activity.startActivity(activity.getIntent());
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void screenCap() {
/*     */     try {
/* 316 */       String screenFileName = this.mUuid + ".png";
/* 317 */       String path = ConfigManger.getInstance().getScreenFilePath();
/*     */       
/* 319 */       Log.e("TAG", "截屏目录" + path);
/* 320 */       FileUtils.fileDirExists(path);
/* 321 */       HwInterfaceManager.getInstance().takeScreenshot(path, screenFileName);
/*     */       
/* 323 */       File file = new File(path + screenFileName);
/* 324 */       if (file.exists()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 329 */         File compressorFile = (new Compressor((Context)this)).setMaxHeight(420).setMaxWidth(520).compressToFile(file);
/*     */         
/* 331 */         ConnectRequestModel.deviceUploadScreenFile(DeviceUuidFactory.getInstance().getDeviceUuid(), compressorFile);
/*     */       } 
/* 333 */     } catch (Exception e) {
/* 334 */       e.printStackTrace();
/* 335 */       Log.e("TAG", e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isAlreadyRegisterSuccess = false;
/*     */ 
/*     */   
/*     */   public void registerSuccess() {
/* 345 */     Logger.e("注册成功", new Object[0]);
/*     */     
/* 347 */     if (!this.isAlreadyRegisterSuccess) {
/*     */       
/* 349 */       if (this.connectOptimizationPresenter != null) {
/* 350 */         this.connectOptimizationPresenter.start();
/*     */       }
/*     */       
/* 353 */       if (this.phoneOptimizationPresenter != null) {
/* 354 */         this.phoneOptimizationPresenter.start();
/*     */       }
/* 356 */       Logger.e("执行定时请求任务", new Object[0]);
/* 357 */       this.isAlreadyRegisterSuccess = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private File screenCapMethod(String uuid) {
/* 363 */     String path = ConfigManger.getInstance().getScreenFilePath();
/*     */     
/* 365 */     String filePath = path + uuid + ".png";
/*     */     try {
/* 367 */       FileUtils.fileDirExists(path);
/*     */       
/* 369 */       Logger.e("截图命令：      screencap -p " + filePath, new Object[0]);
/* 370 */       if (ShellUtils.checkRootPermission()) {
/* 371 */         ShellUtils.execCommand("screencap -p " + filePath, true);
/*     */       }
/* 373 */       return (new Compressor((Context)this))
/* 374 */         .setMaxHeight(420)
/* 375 */         .setMaxWidth(520)
/* 376 */         .compressToFile(new File(filePath));
/*     */     }
/* 378 */     catch (Exception e) {
/* 379 */       e.printStackTrace();
/*     */ 
/*     */       
/* 382 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getNativeVersion(Context context) {
/* 387 */     int versionCode = 0;
/*     */ 
/*     */     
/*     */     try {
/* 391 */       PackageManager packageManager = context.getPackageManager();
/*     */       
/* 393 */       PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
/*     */       
/* 395 */       if (packInfo != null) {
/* 396 */         versionCode = packInfo.versionCode;
/*     */       }
/* 398 */     } catch (PackageManager.NameNotFoundException e) {
/* 399 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 402 */     return versionCode;
/*     */   }
/*     */   
/*     */   private boolean isScreenOn() {
/* 406 */     if (Build.VERSION.SDK_INT >= 20) {
/*     */ 
/*     */       
/* 409 */       DisplayManager dm = (DisplayManager)getSystemService("display");
/* 410 */       if (dm != null) {
/* 411 */         Display[] displays = dm.getDisplays();
/* 412 */         for (Display display : displays) {
/* 413 */           if (display.getState() == 2 || display
/* 414 */             .getState() == 0) {
/* 415 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/* 420 */       PowerManager powerManager = (PowerManager)AdEngine.getInstances().getApplicationContext().getSystemService("power");
/* 421 */       if (powerManager != null) {
/* 422 */         return powerManager.isScreenOn();
/*     */       }
/*     */     } 
/* 425 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void scheduledTask() {
/* 432 */     long timerCloseLed = AlarmManagerUtils.getTimedTimeInMillisV2(
/* 433 */         ConfigManger.getInstance().getCloseLcdBackLightTime(), 0, 0);
/*     */ 
/*     */     
/* 436 */     CharSequence sysTimeStr1 = DateFormat.format("yyyy-MM-dd E a HH:mm:ss", timerCloseLed);
/* 437 */     Logger.e("定时熄屏时间为：" + sysTimeStr1, new Object[0]);
/* 438 */     Intent intentCloseLed = new Intent((Context)this, AlarmReceiver.class);
/* 439 */     intentCloseLed.setAction("ACTION_ALARM_1");
/* 440 */     intentCloseLed.putExtra("id", 1111);
/* 441 */     intentCloseLed.putExtra("intervalMillis", timerCloseLed);
/* 442 */     AlarmManagerUtils.setAlarmTime((Context)this, timerCloseLed, intentCloseLed);
/*     */ 
/*     */     
/* 445 */     long timerReboot = AlarmManagerUtils.getTimedTimeInMillisV2(
/* 446 */         ConfigManger.getInstance().getRebootHour(), 
/* 447 */         ConfigManger.getInstance().getRebootMinute(), 0);
/*     */     
/* 449 */     CharSequence sysTimeStr2 = DateFormat.format("yyyy-MM-dd E a HH:mm:ss", timerReboot);
/* 450 */     Logger.e("定时重启（唤醒）时间为:" + sysTimeStr2, new Object[0]);
/* 451 */     Intent intentReboot = new Intent((Context)this, AlarmReceiver.class);
/* 452 */     intentReboot.setAction("ACTION_ALARM_2");
/* 453 */     intentReboot.putExtra("id", 1112);
/* 454 */     intentReboot.putExtra("intervalMillis", timerReboot);
/* 455 */     AlarmManagerUtils.setAlarmTime((Context)this, timerReboot, intentReboot);
/*     */ 
/*     */     
/* 458 */     long timerUploadProgram = AlarmManagerUtils.getTimedTimeInMillisV2(
/* 459 */         ConfigManger.getInstance().getUploadProgramStatisticsTime(), 0, 0);
/*     */ 
/*     */     
/* 462 */     CharSequence sysTimeStr3 = DateFormat.format("yyyy-MM-dd E a HH:mm:ss", timerUploadProgram);
/* 463 */     Logger.e("节目单统计定时上报时间为：" + sysTimeStr3, new Object[0]);
/* 464 */     Intent intentUploadProgram = new Intent((Context)this, AlarmReceiver.class);
/* 465 */     intentUploadProgram.setAction("ACTION_ALARM_3");
/* 466 */     intentUploadProgram.putExtra("id", 1113);
/* 467 */     intentUploadProgram.putExtra("intervalMillis", timerUploadProgram);
/* 468 */     AlarmManagerUtils.setAlarmTime((Context)this, timerUploadProgram, intentUploadProgram);
/*     */   }
/*     */ 
/*     */   
/*     */   private void cancelTimingTask() {
/* 473 */     Intent intentCloseLed = new Intent((Context)this, AlarmReceiver.class);
/* 474 */     intentCloseLed.setAction("ACTION_ALARM_1");
/* 475 */     AlarmManagerUtils.cancelAlarm((Context)this, intentCloseLed);
/*     */     
/* 477 */     Intent intentReboot = new Intent((Context)this, AlarmReceiver.class);
/* 478 */     intentReboot.setAction("ACTION_ALARM_2");
/* 479 */     AlarmManagerUtils.cancelAlarm((Context)this, intentReboot);
/*     */     
/* 481 */     Intent intent3 = new Intent((Context)this, AlarmReceiver.class);
/* 482 */     intent3.setAction("ACTION_ALARM_3");
/* 483 */     AlarmManagerUtils.cancelAlarm((Context)this, intent3);
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
/*     */   private int getVersion(Context context, String packageName) throws Exception {
/* 496 */     PackageManager packageManager = context.getPackageManager();
/* 497 */     PackageInfo packInfo = packageManager.getPackageInfo(packageName, 0);
/* 498 */     int version = packInfo.versionCode;
/* 499 */     return version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void releaseStorage() {
/* 508 */     long size = FileUtils.getFileSizes(new File(ConfigManger.getInstance().getMaterialPath()));
/* 509 */     Logger.e("素材文件夹的大小" + size, new Object[0]);
/* 510 */     if (size >= 2147483648L) {
/* 511 */       FileUtils.deleteFileUnderFolders(new File(ConfigManger.getInstance().getMaterialPath()));
/* 512 */       Logger.e("删除本地资源文件夹下的文件。。。。", new Object[0]);
/*     */       
/* 514 */       DaoOperation.deleteAll();
/* 515 */       DaoOperation.deletePhoneAll();
/*     */       
/* 517 */       Toast.makeText((Context)this, "素材文件夹已满!清除数据库与素材文件!重启", 1).show();
/*     */       
/* 519 */       this.mHandler.postDelayed(new Runnable()
/*     */           {
/*     */             public void run() {
/* 522 */               if (ShellUtils.checkRootPermission()) {
/* 523 */                 ShellUtils.execCommand("reboot", true);
/*     */               }
/*     */             }
/*     */           }1000L);
/*     */       
/* 528 */       if (this.registerPresenter != null) {
/* 529 */         this.registerPresenter.requestPushMessageAction("素材文件夹已满!清除数据库与素材文件！", "3");
/* 530 */         uiInfo("素材文件夹已满!清除数据库与素材文件！");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void releaseLoggerStorage() {
/* 541 */     String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/logger";
/* 542 */     long size = FileUtils.getFileSizes(new File(ConfigManger.getInstance().getMaterialPath()));
/* 543 */     Logger.e("logger文件夹的大小" + size, new Object[0]);
/*     */     
/* 545 */     if (size >= 104857600L) {
/* 546 */       FileUtils.deleteFileUnderFolders(new File(ConfigManger.getInstance().getMaterialPath()));
/* 547 */       Logger.e("删除logger文件。。。。", new Object[0]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\service\AdService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */