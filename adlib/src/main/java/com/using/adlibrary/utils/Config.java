/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import android.os.Environment;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Config
/*     */ {
/*     */   public static final String default_adds = "47.100.138.12:8081";
/*  16 */   public static long TIMER_1 = AlarmManagerUtils.getTimedTimeInMillisV2(2, 0, 0);
/*     */   
/*  18 */   public static long TIMER_2 = AlarmManagerUtils.getTimedTimeInMillisV2(5, getRandomValue(), 0);
/*     */   
/*  20 */   public static long TIMER_3 = AlarmManagerUtils.getTimedTimeInMillisV2(22, 0, 0);
/*     */   
/*     */   public static final int IMAGE_LOOP_TIME = 12;
/*     */   public static final int BASE_TIME = 1000;
/*     */   
/*     */   private static int getRandomValue() {
/*  26 */     return (int)(1.0D + Math.random() * 30.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public static final int HEART_BEAT_LOOP_TIME = getIntervalV1() * 1000;
/*     */   
/*  37 */   public static final int HEART_BEAT_LOOP_TIME_V2 = getIntervalV2() * 1000;
/*     */ 
/*     */   
/*  40 */   public static final int RANDOM_V1 = getRandomV1() * 1000;
/*     */   
/*  42 */   public static final int RANDOM_V2 = getRandomV2() * 1000;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getIntervalV1() {
/*  48 */     int intervalV1 = 30;
/*  49 */     if (SPUtils.contains(AdEngine.getInstances().getApplicationContext(), "intervalV1")) {
/*  50 */       intervalV1 = ((Integer)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "intervalV1", Integer.valueOf(0))).intValue();
/*     */     }
/*  52 */     return intervalV1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getIntervalV2() {
/*  59 */     int intervalV2 = 30;
/*  60 */     if (SPUtils.contains(AdEngine.getInstances().getApplicationContext(), "intervalV2")) {
/*  61 */       intervalV2 = ((Integer)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "intervalV2", Integer.valueOf(0))).intValue();
/*     */     }
/*  63 */     return intervalV2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getRandomV1() {
/*  70 */     int randomV1 = 30;
/*  71 */     if (SPUtils.contains(AdEngine.getInstances().getApplicationContext(), "randomV1")) {
/*  72 */       randomV1 = ((Integer)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "randomV1", Integer.valueOf(0))).intValue();
/*     */     }
/*  74 */     return randomV1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getRandomV2() {
/*  81 */     int randomV2 = 30;
/*  82 */     if (SPUtils.contains(AdEngine.getInstances().getApplicationContext(), "randomV2")) {
/*  83 */       randomV2 = ((Integer)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "randomV2", Integer.valueOf(0))).intValue();
/*     */     }
/*  85 */     return randomV2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getServicesIp() {
/*     */     String ip;
/*     */     try {
/*  94 */       String adds = (String)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "adds", "");
/*  95 */       if (adds != null && !adds.isEmpty()) {
/*  96 */         ip = adds;
/*     */       } else {
/*  98 */         ip = "47.100.138.12:8081";
/*     */       } 
/* 100 */     } catch (Exception e) {
/* 101 */       ip = "47.100.138.12:8081";
/* 102 */       e.printStackTrace();
/*     */     } 
/* 104 */     return ip;
/*     */   }
/*     */ 
/*     */   
/* 108 */   public static String video_path = Environment.getExternalStorageDirectory().toString() + "/material/";
/* 109 */   public static String image_path = Environment.getExternalStorageDirectory().toString() + "/material/";
/*     */   
/* 111 */   public static String normal = "normal";
/* 112 */   public static String immediate = "immediate";
/* 113 */   public static String subtitle = "subtitle";
/*     */ 
/*     */ 
/*     */   
/* 117 */   public static String areaTypeVideo = "video";
/* 118 */   public static String areaTypeImage = "image";
/* 119 */   public static String areaTypeFlowimage = "flowimage";
/* 120 */   public static String areaTypeText = "text";
/* 121 */   public static String areaTypeDynamic_html = "dynamic_html";
/* 122 */   public static String areaTypeStatic_html = "static_html";
/* 123 */   public static String areaTypeTime = "time";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public static String HAVE_NEW_DATA = "have_new_data";
/* 129 */   public static String SHOULD_DELETE_IMM = "should_delete_imm";
/* 130 */   public static String THE_DATA_DELETE_EXCEPTION = "data_delete_exception";
/* 131 */   public static String PROGRAM_STATE = "program_state";
/*     */   
/* 133 */   public static String FRAGMENT_HAVE_NEW_DATA = "fragment_have_new_data";
/* 134 */   public static String FRAGMENT_SHOULD_CHANGE = "change_data";
/* 135 */   public static String FRAGMENT_SHOULD_CHANGE_VIDEO_ERR = "change_data_video_err";
/*     */ 
/*     */   
/*     */   public static final String REGISTER_SUCCESS = "注册成功";
/*     */ 
/*     */   
/*     */   public static final String REGISTER_FAILURE = "注册失败";
/*     */ 
/*     */   
/*     */   public static final String REGISTER_ERROR = "注册错误";
/*     */ 
/*     */   
/*     */   public static final String REGISTER_EXCEPTION = "注册异常";
/*     */ 
/*     */   
/*     */   public static final String HEART_BEART_SUCCESS = "请求心跳成功";
/*     */ 
/*     */   
/*     */   public static final String HEART_BEART_FAILURE = "请求心跳失败";
/*     */ 
/*     */   
/*     */   public static final String HEART_BEART_ERROR = "请求心跳错误";
/*     */ 
/*     */   
/*     */   public static final String HEART_BEART_EXCEPTION = "请求心跳异常";
/*     */ 
/*     */   
/*     */   public static final String REQUEST_LIST_SUCCESS = "请求节目单列表成功";
/*     */ 
/*     */   
/*     */   public static final String REQUEST_LIST_FAILURE = "请求节目单列表失败";
/*     */   
/*     */   public static final String REQUEST_LIST_ERROR = "请求节目单列表错误";
/*     */   
/*     */   public static final String REQUEST_LIST_EXCEPTION = "请求节目单列表异常";
/*     */   
/*     */   public static final String REQUEST_TITLE_SUCCESS = "请求字幕成功";
/*     */   
/*     */   public static final String REQUEST_TITLE_FAILURE = "请求字幕失败";
/*     */   
/*     */   public static final String REQUEST_TITLE_ERROR = "请求字幕错误";
/*     */   
/*     */   public static final String REQUEST_TITLE_EXCEPTION = "请求字幕异常";
/*     */   
/*     */   public static final String LOGOUT_SUCCESS = "登出成功";
/*     */   
/*     */   public static final String LOGOUT_FAILURE = "登出失败";
/*     */   
/*     */   public static final String LOGOUT_ERROR = "登出错误";
/*     */   
/*     */   public static final String LOGOUT_EXCEPTION = "登出异常";
/*     */   
/*     */   public static final String PLAY_LIST_DATA_ACTION = "PLAY_LIST_DATA";
/*     */   
/*     */   public static final String LOGO_ACTION = "LOGO_ACTION";
/*     */   
/*     */   public static final String LOGO_EXTRA_KEY_ACTION = "LOGO_EXTRA_KEY_ACTION";
/*     */   
/*     */   public static final String TOAST_ACTION = "TOAST_ACTION";
/*     */   
/*     */   public static final String TITLE_ACTION = "TITLE_ACTION";
/*     */   
/*     */   public static final String UI_INFO_ACTION = "UI_INFO_ACTION";
/*     */   
/*     */   public static final int DEVICES_PLAYLIST_UPDATED = 100;
/*     */   
/*     */   public static final int DEVICES_REBOOT = 101;
/*     */   
/*     */   public static final int DEVICES_UPDATE_PAYMENT_APK = 102;
/*     */   
/*     */   public static final int DEVICES_EMERGENCY_STOP = 103;
/*     */   
/*     */   public static final int DEVICES_RESUME_PLAYBACK = 104;
/*     */   
/*     */   public static final int DEVICES_SLEEP = 105;
/*     */   
/*     */   public static final int DEIVCES_WAKE_UP = 106;
/*     */   
/*     */   public static final int DEVICES_DELETE_APK = 107;
/*     */   
/*     */   public static final int DEVICES_UPDATE_ADCLOUD_APK = 108;
/*     */   
/*     */   public static final int DEVICES_UPDATE_USED_TRAFFIC = 109;
/*     */   
/*     */   public static final int DEVICES_H5_PLAYLIST_UPDATED = 110;
/*     */   
/*     */   public static final int DEVICES_CAPTURE_SCREEN = 99;
/*     */   
/*     */   public static final int DEVICES_VOLUME_CONTROL = 111;
/*     */   
/*     */   public static final int CMD_WITH_PARAMS = 65536;
/*     */   
/*     */   public static final String ADCLOUD_APK_TYPE = "1";
/*     */   
/*     */   public static final String ZFT_APK_TYPE = "2";
/*     */   
/*     */   public static final String NORMAL_MESSAGE = "1";
/*     */   
/*     */   public static final String ERROR_MESSAGE = "2";
/*     */   
/*     */   public static final String WARNING_MESSAGE = "3";
/*     */   
/*     */   public static final String PLAY_LIST_MAP_KEY = "playListMapList_key";
/*     */   
/*     */   public static final String PLAY_LIST_MAP_DEFAULT = "0";
/*     */   
/* 241 */   public static final String PLAY_LIST_TEXT = Environment.getExternalStorageDirectory().getAbsolutePath() + "/play_list/";
/*     */ 
/*     */   
/* 244 */   public static final String LOGO_SAVE_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/logo/";
/*     */ 
/*     */   
/* 247 */   public static final String APK_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apk/";
/*     */   
/* 249 */   public static final String MATERIAL_DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath() + "/material/";
/*     */   
/* 251 */   public static final String SCREEN_DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/screenImage/";
/*     */   
/* 253 */   public static final String ERRORLOG_DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/errorlog/";
/*     */   public static final int MAIN_TYPE = 0;
/*     */   public static final int TIMMING_TYPE = 1;
/*     */   public static final int IMMEDIATE_TYPE = 2;
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\Config.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */