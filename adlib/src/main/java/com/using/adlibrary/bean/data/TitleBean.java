/*    */ package com.using.adlibrary.bean.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitleBean
/*    */   implements Serializable
/*    */ {
/*    */   private String titles;
/*    */   private String content;
/*    */   private int onOff;
/*    */   private long startTime;
/*    */   private long failureTime;
/*    */   private String color;
/*    */   
/*    */   public long getStartTime() {
/* 20 */     return this.startTime;
/*    */   }
/*    */   
/*    */   public void setStartTime(long startTime) {
/* 24 */     this.startTime = startTime;
/*    */   }
/*    */   
/*    */   public long getFailureTime() {
/* 28 */     return this.failureTime;
/*    */   }
/*    */   
/*    */   public void setFailureTime(long failureTime) {
/* 32 */     this.failureTime = failureTime;
/*    */   }
/*    */   
/*    */   public String getColor() {
/* 36 */     return this.color;
/*    */   }
/*    */   
/*    */   public void setColor(String color) {
/* 40 */     this.color = color;
/*    */   }
/*    */   
/*    */   public String getContent() {
/* 44 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(String content) {
/* 48 */     this.content = content;
/*    */   }
/*    */   
/*    */   public int getOnOff() {
/* 52 */     return this.onOff;
/*    */   }
/*    */   
/*    */   public void setOnOff(int onOff) {
/* 56 */     this.onOff = onOff;
/*    */   }
/*    */   
/*    */   public String getTitles() {
/* 60 */     return this.titles;
/*    */   }
/*    */   
/*    */   public void setTitles(String titles) {
/* 64 */     this.titles = titles;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\data\TitleBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */