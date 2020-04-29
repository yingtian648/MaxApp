/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeUtils
/*    */ {
/*    */   public static String getTime() {
/* 19 */     long time = System.currentTimeMillis();
/* 20 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 21 */     Date d1 = new Date(time);
/* 22 */     return format.format(d1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long currentTimeToSencond() {
/* 29 */     Calendar c_cur = Calendar.getInstance();
/* 30 */     int hour = c_cur.get(11);
/* 31 */     int minute = c_cur.get(12);
/* 32 */     int second = c_cur.get(13);
/* 33 */     return (hour * 60 * 60 + minute * 60 + second);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getYMD() {
/* 42 */     long l = System.currentTimeMillis();
/*    */     
/* 44 */     Date date = new Date(l);
/*    */     
/* 46 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
/* 47 */     String nyr = dateFormat.format(date);
/*    */     
/* 49 */     return nyr;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String dateToWeek(String datetime) {
/* 60 */     SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
/* 61 */     String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
/* 62 */     Calendar cal = Calendar.getInstance();
/* 63 */     Date datet = null;
/*    */     try {
/* 65 */       datet = f.parse(datetime);
/* 66 */       cal.setTime(datet);
/* 67 */     } catch (ParseException e) {
/* 68 */       e.printStackTrace();
/*    */     } 
/* 70 */     int w = cal.get(7) - 1;
/* 71 */     if (w < 0)
/* 72 */       w = 0; 
/* 73 */     return weekDays[w];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long netTimeToSecond(String times) {
/*    */     try {
/* 84 */       String[] strs = times.split(":");
/* 85 */       long hour = Long.parseLong(strs[0]);
/* 86 */       long minute = Long.parseLong(strs[1]);
/* 87 */       long second = Long.parseLong(strs[2]);
/* 88 */       return hour * 60L * 60L + minute * 60L + second;
/* 89 */     } catch (Exception e) {
/* 90 */       e.printStackTrace();
/* 91 */       return 0L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\TimeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */