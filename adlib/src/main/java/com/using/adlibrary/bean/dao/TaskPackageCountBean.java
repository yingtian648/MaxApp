/*    */ package com.using.adlibrary.bean.dao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskPackageCountBean
/*    */ {
/*    */   private Long id;
/*    */   private Long link_id;
/*    */   private long taskId;
/*    */   private int type;
/*    */   private int num;
/*    */   
/*    */   public TaskPackageCountBean(Long id, Long link_id, long taskId, int type, int num) {
/* 25 */     this.id = id;
/* 26 */     this.link_id = link_id;
/* 27 */     this.taskId = taskId;
/* 28 */     this.type = type;
/* 29 */     this.num = num;
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskPackageCountBean() {}
/*    */ 
/*    */   
/*    */   public Long getId() {
/* 37 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 41 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getTaskId() {
/* 46 */     return this.taskId;
/*    */   }
/*    */   
/*    */   public void setTaskId(long taskId) {
/* 50 */     this.taskId = taskId;
/*    */   }
/*    */   
/*    */   public int getNum() {
/* 54 */     return this.num;
/*    */   }
/*    */   
/*    */   public void setNum(int num) {
/* 58 */     this.num = num;
/*    */   }
/*    */   
/*    */   public void subNum() {
/* 62 */     this.num++;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 66 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(int type) {
/* 70 */     this.type = type;
/*    */   }
/*    */   
/*    */   public Long getLink_id() {
/* 74 */     return this.link_id;
/*    */   }
/*    */   
/*    */   public void setLink_id(Long link_id) {
/* 78 */     this.link_id = link_id;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\TaskPackageCountBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */