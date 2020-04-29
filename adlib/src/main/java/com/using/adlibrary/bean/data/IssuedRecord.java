/*    */ package com.using.adlibrary.bean.data;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IssuedRecord
/*    */   implements Serializable
/*    */ {
/*    */   private String deviceId;
/*    */   private long programId;
/*    */   private String describeNote;
/*    */   private Integer hashCode;
/*    */   
/*    */   public String getDeviceId() {
/* 19 */     return this.deviceId;
/*    */   }
/*    */   
/*    */   public void setDeviceId(String deviceId) {
/* 23 */     this.deviceId = deviceId;
/*    */   }
/*    */   
/*    */   public long getProgramId() {
/* 27 */     return this.programId;
/*    */   }
/*    */   
/*    */   public void setProgramId(long programId) {
/* 31 */     this.programId = programId;
/*    */   }
/*    */   
/*    */   public String getDescribeNote() {
/* 35 */     return this.describeNote;
/*    */   }
/*    */   
/*    */   public void setDescribeNote(String describeNote) {
/* 39 */     this.describeNote = describeNote;
/*    */   }
/*    */   
/*    */   public Integer getHashCode() {
/* 43 */     return this.hashCode;
/*    */   }
/*    */   
/*    */   public void setHashCode(Integer hashCode) {
/* 47 */     this.hashCode = hashCode;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\data\IssuedRecord.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */