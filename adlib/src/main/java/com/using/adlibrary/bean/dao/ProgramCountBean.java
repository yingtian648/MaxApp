/*     */ package com.using.adlibrary.bean.dao;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.ProgramCountBeanDao;
/*     */ import com.using.adlibrary.dao.TaskPackageCountBeanDao;
/*     */ import java.util.List;
/*     */ import org.greenrobot.greendao.DaoException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProgramCountBean
/*     */ {
/*     */   private Long id;
/*     */   private long programId;
/*     */   private String sendDate;
/*     */   private int num;
/*     */   private List<TaskPackageCountBean> taskCounts;
/*     */   private transient DaoSession daoSession;
/*     */   private transient ProgramCountBeanDao myDao;
/*     */   
/*     */   public ProgramCountBean(Long id, long programId, String sendDate, int num) {
/*  39 */     this.id = id;
/*  40 */     this.programId = programId;
/*  41 */     this.sendDate = sendDate;
/*  42 */     this.num = num;
/*     */   }
/*     */   
/*     */   public ProgramCountBean() {}
/*     */   
/*     */   public Long getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   public void setId(Long id) {
/*  51 */     this.id = id;
/*     */   }
/*     */   public String getSendDate() {
/*  54 */     return this.sendDate;
/*     */   }
/*     */   public void setSendDate(String sendDate) {
/*  57 */     this.sendDate = sendDate;
/*     */   }
/*     */   public int getNum() {
/*  60 */     return this.num;
/*     */   }
/*     */   public void setNum(int num) {
/*  63 */     this.num = num;
/*     */   }
/*     */   public long getProgramId() {
/*  66 */     return this.programId;
/*     */   }
/*     */   public void setProgramId(long programId) {
/*  69 */     this.programId = programId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TaskPackageCountBean> getTaskCounts() {
/*  77 */     if (this.taskCounts == null) {
/*  78 */       DaoSession daoSession = this.daoSession;
/*  79 */       if (daoSession == null) {
/*  80 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/*  83 */       TaskPackageCountBeanDao targetDao = daoSession.getTaskPackageCountBeanDao();
/*     */       
/*  85 */       List<TaskPackageCountBean> taskCountsNew = targetDao._queryProgramCountBean_TaskCounts(this.id);
/*  86 */       synchronized (this) {
/*  87 */         if (this.taskCounts == null) {
/*  88 */           this.taskCounts = taskCountsNew;
/*     */         }
/*     */       } 
/*     */     } 
/*  92 */     return this.taskCounts;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void resetTaskCounts() {
/*  97 */     this.taskCounts = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 105 */     if (this.myDao == null) {
/* 106 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 108 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 116 */     if (this.myDao == null) {
/* 117 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 119 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 127 */     if (this.myDao == null) {
/* 128 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 130 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 135 */     this.daoSession = daoSession;
/* 136 */     this.myDao = (daoSession != null) ? daoSession.getProgramCountBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\ProgramCountBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */