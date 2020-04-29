/*     */ package com.using.adlibrary.bean.dao;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.PlayAreaListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.TimingProgramListDaoBeanDao;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimingProgramListDaoBean
/*     */ {
/*     */   private Long id;
/*     */   private long play_list_id;
/*     */   private long link_id;
/*     */   private String play_list_name;
/*     */   private String resolution;
/*     */   private String hororver;
/*     */   private String beginTime;
/*     */   private String endTime;
/*     */   private long playDays;
/*     */   private List<PlayAreaListDaoBean> playarea_list;
/*     */   private transient DaoSession daoSession;
/*     */   private transient TimingProgramListDaoBeanDao myDao;
/*     */   
/*     */   public TimingProgramListDaoBean(Long id, long play_list_id, long link_id, String play_list_name, String resolution, String hororver, String beginTime, String endTime, long playDays) {
/*  57 */     this.id = id;
/*  58 */     this.play_list_id = play_list_id;
/*  59 */     this.link_id = link_id;
/*  60 */     this.play_list_name = play_list_name;
/*  61 */     this.resolution = resolution;
/*  62 */     this.hororver = hororver;
/*  63 */     this.beginTime = beginTime;
/*  64 */     this.endTime = endTime;
/*  65 */     this.playDays = playDays;
/*     */   }
/*     */ 
/*     */   
/*     */   public TimingProgramListDaoBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  73 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  77 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlay_list_id() {
/*  81 */     return this.play_list_id;
/*     */   }
/*     */   
/*     */   public void setPlay_list_id(long play_list_id) {
/*  85 */     this.play_list_id = play_list_id;
/*     */   }
/*     */   
/*     */   public long getLink_id() {
/*  89 */     return this.link_id;
/*     */   }
/*     */   
/*     */   public void setLink_id(long link_id) {
/*  93 */     this.link_id = link_id;
/*     */   }
/*     */   
/*     */   public String getPlay_list_name() {
/*  97 */     return this.play_list_name;
/*     */   }
/*     */   
/*     */   public void setPlay_list_name(String play_list_name) {
/* 101 */     this.play_list_name = play_list_name;
/*     */   }
/*     */   
/*     */   public String getResolution() {
/* 105 */     return this.resolution;
/*     */   }
/*     */   
/*     */   public void setResolution(String resolution) {
/* 109 */     this.resolution = resolution;
/*     */   }
/*     */   
/*     */   public String getHororver() {
/* 113 */     return this.hororver;
/*     */   }
/*     */   
/*     */   public void setHororver(String hororver) {
/* 117 */     this.hororver = hororver;
/*     */   }
/*     */   
/*     */   public String getBeginTime() {
/* 121 */     return this.beginTime;
/*     */   }
/*     */   
/*     */   public void setBeginTime(String beginTime) {
/* 125 */     this.beginTime = beginTime;
/*     */   }
/*     */   
/*     */   public String getEndTime() {
/* 129 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(String endTime) {
/* 133 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public long getPlayDays() {
/* 137 */     return this.playDays;
/*     */   }
/*     */   
/*     */   public void setPlayDays(long playDays) {
/* 141 */     this.playDays = playDays;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PlayAreaListDaoBean> getPlayarea_list() {
/* 150 */     if (this.playarea_list == null) {
/* 151 */       DaoSession daoSession = this.daoSession;
/* 152 */       if (daoSession == null) {
/* 153 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 156 */       PlayAreaListDaoBeanDao targetDao = daoSession.getPlayAreaListDaoBeanDao();
/*     */       
/* 158 */       List<PlayAreaListDaoBean> playarea_listNew = targetDao._queryTimingProgramListDaoBean_Playarea_list(this.id.longValue());
/* 159 */       synchronized (this) {
/* 160 */         if (this.playarea_list == null) {
/* 161 */           this.playarea_list = playarea_listNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 165 */     return this.playarea_list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetPlayarea_list() {
/* 171 */     this.playarea_list = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 180 */     if (this.myDao == null) {
/* 181 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 183 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 192 */     if (this.myDao == null) {
/* 193 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 195 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 204 */     if (this.myDao == null) {
/* 205 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 207 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 213 */     this.daoSession = daoSession;
/* 214 */     this.myDao = (daoSession != null) ? daoSession.getTimingProgramListDaoBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\TimingProgramListDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */