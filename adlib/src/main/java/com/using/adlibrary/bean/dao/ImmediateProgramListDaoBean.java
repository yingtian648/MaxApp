/*     */ package com.using.adlibrary.bean.dao;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.ImmediateProgramListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.PlayAreaListDaoBeanDao;
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
/*     */ public class ImmediateProgramListDaoBean
/*     */ {
/*     */   private Long id;
/*     */   private long play_list_id;
/*     */   private long link_id;
/*     */   private String play_list_name;
/*     */   private String resolution;
/*     */   private String hororver;
/*     */   private List<PlayAreaListDaoBean> playarea_list;
/*     */   private transient DaoSession daoSession;
/*     */   private transient ImmediateProgramListDaoBeanDao myDao;
/*     */   
/*     */   public ImmediateProgramListDaoBean(Long id, long play_list_id, long link_id, String play_list_name, String resolution, String hororver) {
/*  50 */     this.id = id;
/*  51 */     this.play_list_id = play_list_id;
/*  52 */     this.link_id = link_id;
/*  53 */     this.play_list_name = play_list_name;
/*  54 */     this.resolution = resolution;
/*  55 */     this.hororver = hororver;
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmediateProgramListDaoBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  63 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  67 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlay_list_id() {
/*  71 */     return this.play_list_id;
/*     */   }
/*     */   
/*     */   public void setPlay_list_id(long play_list_id) {
/*  75 */     this.play_list_id = play_list_id;
/*     */   }
/*     */   
/*     */   public long getLink_id() {
/*  79 */     return this.link_id;
/*     */   }
/*     */   
/*     */   public void setLink_id(long link_id) {
/*  83 */     this.link_id = link_id;
/*     */   }
/*     */   
/*     */   public String getPlay_list_name() {
/*  87 */     return this.play_list_name;
/*     */   }
/*     */   
/*     */   public void setPlay_list_name(String play_list_name) {
/*  91 */     this.play_list_name = play_list_name;
/*     */   }
/*     */   
/*     */   public String getResolution() {
/*  95 */     return this.resolution;
/*     */   }
/*     */   
/*     */   public void setResolution(String resolution) {
/*  99 */     this.resolution = resolution;
/*     */   }
/*     */   
/*     */   public String getHororver() {
/* 103 */     return this.hororver;
/*     */   }
/*     */   
/*     */   public void setHororver(String hororver) {
/* 107 */     this.hororver = hororver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PlayAreaListDaoBean> getPlayarea_list() {
/* 116 */     if (this.playarea_list == null) {
/* 117 */       DaoSession daoSession = this.daoSession;
/* 118 */       if (daoSession == null) {
/* 119 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 122 */       PlayAreaListDaoBeanDao targetDao = daoSession.getPlayAreaListDaoBeanDao();
/*     */       
/* 124 */       List<PlayAreaListDaoBean> playarea_listNew = targetDao._queryImmediateProgramListDaoBean_Playarea_list(this.id.longValue());
/* 125 */       synchronized (this) {
/* 126 */         if (this.playarea_list == null) {
/* 127 */           this.playarea_list = playarea_listNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 131 */     return this.playarea_list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetPlayarea_list() {
/* 137 */     this.playarea_list = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 146 */     if (this.myDao == null) {
/* 147 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 149 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 158 */     if (this.myDao == null) {
/* 159 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 161 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 170 */     if (this.myDao == null) {
/* 171 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 173 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 179 */     this.daoSession = daoSession;
/* 180 */     this.myDao = (daoSession != null) ? daoSession.getImmediateProgramListDaoBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\ImmediateProgramListDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */