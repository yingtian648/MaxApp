/*     */ package com.using.adlibrary.bean.phone;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.PhoneMainProgramListBeanDao;
/*     */ import com.using.adlibrary.dao.PhoneRootListBeanDao;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PhoneRootListBean
/*     */ {
/*     */   private Long id;
/*     */   private long play_list_id;
/*     */   private String play_list_name;
/*     */   private String play_list_type;
/*     */   private long start_time;
/*     */   private long play_time;
/*     */   private long fail_time;
/*     */   private long download_time;
/*     */   private String subtitlePlayTime;
/*     */   private List<PhoneMainProgramListBean> mainProgramList;
/*     */   private transient DaoSession daoSession;
/*     */   private transient PhoneRootListBeanDao myDao;
/*     */   
/*     */   public PhoneRootListBean(Long id, long play_list_id, String play_list_name, String play_list_type, long start_time, long play_time, long fail_time, long download_time, String subtitlePlayTime) {
/*  51 */     this.id = id;
/*  52 */     this.play_list_id = play_list_id;
/*  53 */     this.play_list_name = play_list_name;
/*  54 */     this.play_list_type = play_list_type;
/*  55 */     this.start_time = start_time;
/*  56 */     this.play_time = play_time;
/*  57 */     this.fail_time = fail_time;
/*  58 */     this.download_time = download_time;
/*  59 */     this.subtitlePlayTime = subtitlePlayTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public PhoneRootListBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  67 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  71 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlay_list_id() {
/*  75 */     return this.play_list_id;
/*     */   }
/*     */   
/*     */   public void setPlay_list_id(long play_list_id) {
/*  79 */     this.play_list_id = play_list_id;
/*     */   }
/*     */   
/*     */   public String getPlay_list_name() {
/*  83 */     return this.play_list_name;
/*     */   }
/*     */   
/*     */   public void setPlay_list_name(String play_list_name) {
/*  87 */     this.play_list_name = play_list_name;
/*     */   }
/*     */   
/*     */   public String getPlay_list_type() {
/*  91 */     return this.play_list_type;
/*     */   }
/*     */   
/*     */   public void setPlay_list_type(String play_list_type) {
/*  95 */     this.play_list_type = play_list_type;
/*     */   }
/*     */   
/*     */   public long getStart_time() {
/*  99 */     return this.start_time;
/*     */   }
/*     */   
/*     */   public void setStart_time(long start_time) {
/* 103 */     this.start_time = start_time;
/*     */   }
/*     */   
/*     */   public long getPlay_time() {
/* 107 */     return this.play_time;
/*     */   }
/*     */   
/*     */   public void setPlay_time(long play_time) {
/* 111 */     this.play_time = play_time;
/*     */   }
/*     */   
/*     */   public long getFail_time() {
/* 115 */     return this.fail_time;
/*     */   }
/*     */   
/*     */   public void setFail_time(long fail_time) {
/* 119 */     this.fail_time = fail_time;
/*     */   }
/*     */   
/*     */   public long getDownload_time() {
/* 123 */     return this.download_time;
/*     */   }
/*     */   
/*     */   public void setDownload_time(long download_time) {
/* 127 */     this.download_time = download_time;
/*     */   }
/*     */   
/*     */   public String getSubtitlePlayTime() {
/* 131 */     return this.subtitlePlayTime;
/*     */   }
/*     */   
/*     */   public void setSubtitlePlayTime(String subtitlePlayTime) {
/* 135 */     this.subtitlePlayTime = subtitlePlayTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PhoneMainProgramListBean> getMainProgramList() {
/* 144 */     if (this.mainProgramList == null) {
/* 145 */       DaoSession daoSession = this.daoSession;
/* 146 */       if (daoSession == null) {
/* 147 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 150 */       PhoneMainProgramListBeanDao targetDao = daoSession.getPhoneMainProgramListBeanDao();
/*     */       
/* 152 */       List<PhoneMainProgramListBean> mainProgramListNew = targetDao._queryPhoneRootListBean_MainProgramList(this.id.longValue());
/* 153 */       synchronized (this) {
/* 154 */         if (this.mainProgramList == null) {
/* 155 */           this.mainProgramList = mainProgramListNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 159 */     return this.mainProgramList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetMainProgramList() {
/* 165 */     this.mainProgramList = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 174 */     if (this.myDao == null) {
/* 175 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 177 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 186 */     if (this.myDao == null) {
/* 187 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 189 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 198 */     if (this.myDao == null) {
/* 199 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 201 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 207 */     this.daoSession = daoSession;
/* 208 */     this.myDao = (daoSession != null) ? daoSession.getPhoneRootListBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\phone\PhoneRootListBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */