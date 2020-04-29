/*    */ package com.using.adlibrary.bean;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HelpBean
/*    */   implements Serializable
/*    */ {
/*    */   private String sn;
/*    */   private String date;
/*    */   private String photo;
/*    */   
/*    */   public String getSn() {
/* 15 */     return this.sn;
/*    */   }
/*    */   
/*    */   public void setSn(String sn) {
/* 19 */     this.sn = sn;
/*    */   }
/*    */   
/*    */   public String getDate() {
/* 23 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setDate(String date) {
/* 27 */     this.date = date;
/*    */   }
/*    */   
/*    */   public String getPhoto() {
/* 31 */     return this.photo;
/*    */   }
/*    */   
/*    */   public void setPhoto(String photo) {
/* 35 */     this.photo = photo;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     return "HelpBean{sn='" + this.sn + '\'' + ", date='" + this.date + '\'' + ", photo='" + this.photo + '\'' + '}';
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\HelpBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */