/*     */ package com.using.adlibrary.bean.phone;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.PhoneMainProgramListBeanDao;
/*     */ import com.using.adlibrary.dao.PhonePlayAreaListBeanDao;
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
/*     */ public class PhoneMainProgramListBean
/*     */ {
/*     */   private Long id;
/*     */   private long play_list_id;
/*     */   private long link_id;
/*     */   private String play_list_name;
/*     */   private String resolution;
/*     */   private String hororver;
/*     */   private List<PhonePlayAreaListBean> playarea_list;
/*     */   private transient DaoSession daoSession;
/*     */   private transient PhoneMainProgramListBeanDao myDao;
/*     */   
/*     */   public PhoneMainProgramListBean(Long id, long play_list_id, long link_id, String play_list_name, String resolution, String hororver) {
/*  51 */     this.id = id;
/*  52 */     this.play_list_id = play_list_id;
/*  53 */     this.link_id = link_id;
/*  54 */     this.play_list_name = play_list_name;
/*  55 */     this.resolution = resolution;
/*  56 */     this.hororver = hororver;
/*     */   }
/*     */ 
/*     */   
/*     */   public PhoneMainProgramListBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  64 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  68 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlay_list_id() {
/*  72 */     return this.play_list_id;
/*     */   }
/*     */   
/*     */   public void setPlay_list_id(long play_list_id) {
/*  76 */     this.play_list_id = play_list_id;
/*     */   }
/*     */   
/*     */   public long getLink_id() {
/*  80 */     return this.link_id;
/*     */   }
/*     */   
/*     */   public void setLink_id(long link_id) {
/*  84 */     this.link_id = link_id;
/*     */   }
/*     */   
/*     */   public String getPlay_list_name() {
/*  88 */     return this.play_list_name;
/*     */   }
/*     */   
/*     */   public void setPlay_list_name(String play_list_name) {
/*  92 */     this.play_list_name = play_list_name;
/*     */   }
/*     */   
/*     */   public String getResolution() {
/*  96 */     return this.resolution;
/*     */   }
/*     */   
/*     */   public void setResolution(String resolution) {
/* 100 */     this.resolution = resolution;
/*     */   }
/*     */   
/*     */   public String getHororver() {
/* 104 */     return this.hororver;
/*     */   }
/*     */   
/*     */   public void setHororver(String hororver) {
/* 108 */     this.hororver = hororver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PhonePlayAreaListBean> getPlayarea_list() {
/* 117 */     if (this.playarea_list == null) {
/* 118 */       DaoSession daoSession = this.daoSession;
/* 119 */       if (daoSession == null) {
/* 120 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 123 */       PhonePlayAreaListBeanDao targetDao = daoSession.getPhonePlayAreaListBeanDao();
/*     */       
/* 125 */       List<PhonePlayAreaListBean> playarea_listNew = targetDao._queryPhoneMainProgramListBean_Playarea_list(this.id.longValue());
/* 126 */       synchronized (this) {
/* 127 */         if (this.playarea_list == null) {
/* 128 */           this.playarea_list = playarea_listNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 132 */     return this.playarea_list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetPlayarea_list() {
/* 138 */     this.playarea_list = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 147 */     if (this.myDao == null) {
/* 148 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 150 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 159 */     if (this.myDao == null) {
/* 160 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 162 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 171 */     if (this.myDao == null) {
/* 172 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 174 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 180 */     this.daoSession = daoSession;
/* 181 */     this.myDao = (daoSession != null) ? daoSession.getPhoneMainProgramListBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\phone\PhoneMainProgramListBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */