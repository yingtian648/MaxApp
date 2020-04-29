/*     */ package com.using.adlibrary.bean.dao;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.ImmediateProgramListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.MainProgramListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.RootListDaoBeanDao;
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
/*     */ public class RootListDaoBean
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
/*     */   private List<MainProgramListDaoBean> mainProgramList;
/*     */   private List<TimingProgramListDaoBean> timingProgramList;
/*     */   private List<ImmediateProgramListDaoBean> immediateProgramList;
/*     */   private transient DaoSession daoSession;
/*     */   private transient RootListDaoBeanDao myDao;
/*     */   
/*     */   public RootListDaoBean(Long id, long play_list_id, String play_list_name, String play_list_type, long start_time, long play_time, long fail_time, long download_time, String subtitlePlayTime) {
/*  59 */     this.id = id;
/*  60 */     this.play_list_id = play_list_id;
/*  61 */     this.play_list_name = play_list_name;
/*  62 */     this.play_list_type = play_list_type;
/*  63 */     this.start_time = start_time;
/*  64 */     this.play_time = play_time;
/*  65 */     this.fail_time = fail_time;
/*  66 */     this.download_time = download_time;
/*  67 */     this.subtitlePlayTime = subtitlePlayTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public RootListDaoBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  75 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  79 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getPlay_list_id() {
/*  83 */     return this.play_list_id;
/*     */   }
/*     */   
/*     */   public void setPlay_list_id(long play_list_id) {
/*  87 */     this.play_list_id = play_list_id;
/*     */   }
/*     */   
/*     */   public String getPlay_list_name() {
/*  91 */     return this.play_list_name;
/*     */   }
/*     */   
/*     */   public void setPlay_list_name(String play_list_name) {
/*  95 */     this.play_list_name = play_list_name;
/*     */   }
/*     */   
/*     */   public String getPlay_list_type() {
/*  99 */     return this.play_list_type;
/*     */   }
/*     */   
/*     */   public void setPlay_list_type(String play_list_type) {
/* 103 */     this.play_list_type = play_list_type;
/*     */   }
/*     */   
/*     */   public long getStart_time() {
/* 107 */     return this.start_time;
/*     */   }
/*     */   
/*     */   public void setStart_time(long start_time) {
/* 111 */     this.start_time = start_time;
/*     */   }
/*     */   
/*     */   public long getPlay_time() {
/* 115 */     return this.play_time;
/*     */   }
/*     */   
/*     */   public void setPlay_time(long play_time) {
/* 119 */     this.play_time = play_time;
/*     */   }
/*     */   
/*     */   public long getFail_time() {
/* 123 */     return this.fail_time;
/*     */   }
/*     */   
/*     */   public void setFail_time(long fail_time) {
/* 127 */     this.fail_time = fail_time;
/*     */   }
/*     */   
/*     */   public long getDownload_time() {
/* 131 */     return this.download_time;
/*     */   }
/*     */   
/*     */   public void setDownload_time(long download_time) {
/* 135 */     this.download_time = download_time;
/*     */   }
/*     */   
/*     */   public String getSubtitlePlayTime() {
/* 139 */     return this.subtitlePlayTime;
/*     */   }
/*     */   
/*     */   public void setSubtitlePlayTime(String subtitlePlayTime) {
/* 143 */     this.subtitlePlayTime = subtitlePlayTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MainProgramListDaoBean> getMainProgramList() {
/* 152 */     if (this.mainProgramList == null) {
/* 153 */       DaoSession daoSession = this.daoSession;
/* 154 */       if (daoSession == null) {
/* 155 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 158 */       MainProgramListDaoBeanDao targetDao = daoSession.getMainProgramListDaoBeanDao();
/*     */       
/* 160 */       List<MainProgramListDaoBean> mainProgramListNew = targetDao._queryRootListDaoBean_MainProgramList(this.id.longValue());
/* 161 */       synchronized (this) {
/* 162 */         if (this.mainProgramList == null) {
/* 163 */           this.mainProgramList = mainProgramListNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 167 */     return this.mainProgramList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetMainProgramList() {
/* 173 */     this.mainProgramList = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TimingProgramListDaoBean> getTimingProgramList() {
/* 182 */     if (this.timingProgramList == null) {
/* 183 */       DaoSession daoSession = this.daoSession;
/* 184 */       if (daoSession == null) {
/* 185 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 188 */       TimingProgramListDaoBeanDao targetDao = daoSession.getTimingProgramListDaoBeanDao();
/*     */       
/* 190 */       List<TimingProgramListDaoBean> timingProgramListNew = targetDao._queryRootListDaoBean_TimingProgramList(this.id.longValue());
/* 191 */       synchronized (this) {
/* 192 */         if (this.timingProgramList == null) {
/* 193 */           this.timingProgramList = timingProgramListNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 197 */     return this.timingProgramList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetTimingProgramList() {
/* 203 */     this.timingProgramList = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ImmediateProgramListDaoBean> getImmediateProgramList() {
/* 212 */     if (this.immediateProgramList == null) {
/* 213 */       DaoSession daoSession = this.daoSession;
/* 214 */       if (daoSession == null) {
/* 215 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 218 */       ImmediateProgramListDaoBeanDao targetDao = daoSession.getImmediateProgramListDaoBeanDao();
/*     */       
/* 220 */       List<ImmediateProgramListDaoBean> immediateProgramListNew = targetDao._queryRootListDaoBean_ImmediateProgramList(this.id.longValue());
/* 221 */       synchronized (this) {
/* 222 */         if (this.immediateProgramList == null) {
/* 223 */           this.immediateProgramList = immediateProgramListNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 227 */     return this.immediateProgramList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetImmediateProgramList() {
/* 233 */     this.immediateProgramList = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 242 */     if (this.myDao == null) {
/* 243 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 245 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 254 */     if (this.myDao == null) {
/* 255 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 257 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 266 */     if (this.myDao == null) {
/* 267 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 269 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 275 */     this.daoSession = daoSession;
/* 276 */     this.myDao = (daoSession != null) ? daoSession.getRootListDaoBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\RootListDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */