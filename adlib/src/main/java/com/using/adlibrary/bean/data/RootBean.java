/*    */ package com.using.adlibrary.bean.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RootBean<E>
/*    */   implements Serializable
/*    */ {
/*    */   private String code;
/*    */   private String message;
/*    */   private String jsonType;
/*    */   private E data;
/*    */   
/*    */   public String getJsonType() {
/* 17 */     return this.jsonType;
/*    */   }
/*    */   
/*    */   public void setJsonType(String jsonType) {
/* 21 */     this.jsonType = jsonType;
/*    */   }
/*    */   
/*    */   public E getData() {
/* 25 */     return this.data;
/*    */   }
/*    */   
/*    */   public void setData(E data) {
/* 29 */     this.data = data;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 33 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 37 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 41 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(String message) {
/* 45 */     this.message = message;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\data\RootBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */