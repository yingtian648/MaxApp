/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.app.AlarmManager;
/*    */ import android.app.PendingIntent;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.os.Build;
/*    */ import android.text.format.DateFormat;
/*    */ import com.orhanobut.logger.Logger;
/*    */ import java.util.Calendar;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlarmManagerUtils
/*    */ {
/*    */   public static void setAlarmTime(Context context, long timeInMillis, Intent intent) {
/* 22 */     AlarmManager am = (AlarmManager)context.getSystemService("alarm");
/*    */     
/* 24 */     PendingIntent pendingIntent = PendingIntent.getBroadcast(context, intent.getIntExtra("id", 0), intent, 134217728);
/*    */ 
/*    */     
/* 27 */     if (Build.VERSION.SDK_INT >= 19) {
/*    */ 
/*    */       
/* 30 */       am.setExact(0, timeInMillis, pendingIntent);
/* 31 */       Logger.e("setWindow方法", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setAlarmTime19(Context context, long timeInMillis, Intent intent) {
/* 37 */     AlarmManager am = (AlarmManager)context.getSystemService("alarm");
/*    */     
/* 39 */     PendingIntent pendingIntent = PendingIntent.getBroadcast(context, intent.getIntExtra("id", 0), intent, 134217728);
/*    */ 
/*    */     
/* 42 */     int interval = 1000;
/*    */     
/* 44 */     am.setRepeating(0, timeInMillis, interval, pendingIntent);
/* 45 */     Logger.e("setRepeating方法", new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long getTimedTimeInMillisV2(int hour, int minute, int sencond) {
/* 52 */     Calendar c_cur = Calendar.getInstance();
/* 53 */     long cur_time = c_cur.getTimeInMillis();
/*    */     
/* 55 */     int year = c_cur.get(1);
/* 56 */     Logger.e("YEAR:   " + year, new Object[0]);
/*    */     
/* 58 */     int month = c_cur.get(2);
/* 59 */     Logger.e("MONTH:   " + month, new Object[0]);
/*    */     
/* 61 */     int day = c_cur.get(5);
/* 62 */     Logger.e("DAY_OF_MONTH:    " + day, new Object[0]);
/*    */     
/* 64 */     int time = c_cur.get(6);
/* 65 */     Logger.e("一年中的哪天：DAY_OF_YEAR     " + time, new Object[0]);
/*    */     
/* 67 */     int sss = c_cur.get(11);
/* 68 */     Logger.e("一天中的哪个小时：HOUR_OF_DAY    " + sss, new Object[0]);
/*    */     
/* 70 */     CharSequence sysTimeStr1 = DateFormat.format("kk:mm:ss", cur_time);
/* 71 */     Logger.e("当前系统的时间为：" + sysTimeStr1, new Object[0]);
/*    */ 
/*    */     
/* 74 */     Calendar c_set = Calendar.getInstance();
/* 75 */     c_set.set(6, c_set.get(6));
/* 76 */     c_set.set(11, hour);
/* 77 */     c_set.set(12, minute);
/* 78 */     c_set.set(13, sencond);
/* 79 */     long c_time = c_set.getTimeInMillis();
/*    */ 
/*    */     
/* 82 */     if (cur_time > c_time) {
/* 83 */       c_set.set(6, c_set.get(6) + 1);
/*    */     }
/*    */     
/* 86 */     long cc_time = c_set.getTimeInMillis();
/* 87 */     return cc_time;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void cancelAlarm(Context context, Intent intent) {
/* 92 */     Logger.e("cancelAlarm 取消定时闹钟", new Object[0]);
/* 93 */     PendingIntent pi = PendingIntent.getBroadcast(context, intent.getIntExtra("id", 0), intent, 268435456);
/*    */     
/* 95 */     AlarmManager am = (AlarmManager)context.getSystemService("alarm");
/* 96 */     am.cancel(pi);
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\AlarmManagerUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */