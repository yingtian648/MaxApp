/*     */ package com.using.adlibrary.bean.dao;
/*     */ 
/*     */ import com.using.adlibrary.dao.DaoSession;
/*     */ import com.using.adlibrary.dao.ImagePlayItemListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.PlayAreaListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.VideoPlayItemListDaoBeanDao;
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
/*     */ 
/*     */ public class PlayAreaListDaoBean
/*     */ {
/*     */   private Long id;
/*     */   private long area_id;
/*     */   private String area_name;
/*     */   private long link_id;
/*     */   private int startX;
/*     */   private int startY;
/*     */   private int width;
/*     */   private int height;
/*     */   private int layer;
/*     */   private String area_type;
/*     */   private List<VideoPlayItemListDaoBean> videoPlayItemList;
/*     */   private List<ImagePlayItemListDaoBean> imagePlayItemList;
/*     */   private transient DaoSession daoSession;
/*     */   private transient PlayAreaListDaoBeanDao myDao;
/*     */   
/*     */   public PlayAreaListDaoBean(Long id, long area_id, String area_name, long link_id, int startX, int startY, int width, int height, int layer, String area_type) {
/*  61 */     this.id = id;
/*  62 */     this.area_id = area_id;
/*  63 */     this.area_name = area_name;
/*  64 */     this.link_id = link_id;
/*  65 */     this.startX = startX;
/*  66 */     this.startY = startY;
/*  67 */     this.width = width;
/*  68 */     this.height = height;
/*  69 */     this.layer = layer;
/*  70 */     this.area_type = area_type;
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayAreaListDaoBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  78 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  82 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getArea_id() {
/*  86 */     return this.area_id;
/*     */   }
/*     */   
/*     */   public void setArea_id(long area_id) {
/*  90 */     this.area_id = area_id;
/*     */   }
/*     */   
/*     */   public String getArea_name() {
/*  94 */     return this.area_name;
/*     */   }
/*     */   
/*     */   public void setArea_name(String area_name) {
/*  98 */     this.area_name = area_name;
/*     */   }
/*     */   
/*     */   public long getLink_id() {
/* 102 */     return this.link_id;
/*     */   }
/*     */   
/*     */   public void setLink_id(long link_id) {
/* 106 */     this.link_id = link_id;
/*     */   }
/*     */   
/*     */   public int getStartX() {
/* 110 */     return this.startX;
/*     */   }
/*     */   
/*     */   public void setStartX(int startX) {
/* 114 */     this.startX = startX;
/*     */   }
/*     */   
/*     */   public int getStartY() {
/* 118 */     return this.startY;
/*     */   }
/*     */   
/*     */   public void setStartY(int startY) {
/* 122 */     this.startY = startY;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 126 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(int width) {
/* 130 */     this.width = width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 134 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(int height) {
/* 138 */     this.height = height;
/*     */   }
/*     */   
/*     */   public int getLayer() {
/* 142 */     return this.layer;
/*     */   }
/*     */   
/*     */   public void setLayer(int layer) {
/* 146 */     this.layer = layer;
/*     */   }
/*     */   
/*     */   public String getArea_type() {
/* 150 */     return this.area_type;
/*     */   }
/*     */   
/*     */   public void setArea_type(String area_type) {
/* 154 */     this.area_type = area_type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<VideoPlayItemListDaoBean> getVideoPlayItemList() {
/* 163 */     if (this.videoPlayItemList == null) {
/* 164 */       DaoSession daoSession = this.daoSession;
/* 165 */       if (daoSession == null) {
/* 166 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 169 */       VideoPlayItemListDaoBeanDao targetDao = daoSession.getVideoPlayItemListDaoBeanDao();
/*     */       
/* 171 */       List<VideoPlayItemListDaoBean> videoPlayItemListNew = targetDao._queryPlayAreaListDaoBean_VideoPlayItemList(this.id.longValue());
/* 172 */       synchronized (this) {
/* 173 */         if (this.videoPlayItemList == null) {
/* 174 */           this.videoPlayItemList = videoPlayItemListNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 178 */     return this.videoPlayItemList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetVideoPlayItemList() {
/* 184 */     this.videoPlayItemList = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ImagePlayItemListDaoBean> getImagePlayItemList() {
/* 193 */     if (this.imagePlayItemList == null) {
/* 194 */       DaoSession daoSession = this.daoSession;
/* 195 */       if (daoSession == null) {
/* 196 */         throw new DaoException("Entity is detached from DAO context");
/*     */       }
/*     */       
/* 199 */       ImagePlayItemListDaoBeanDao targetDao = daoSession.getImagePlayItemListDaoBeanDao();
/*     */       
/* 201 */       List<ImagePlayItemListDaoBean> imagePlayItemListNew = targetDao._queryPlayAreaListDaoBean_ImagePlayItemList(this.id.longValue());
/* 202 */       synchronized (this) {
/* 203 */         if (this.imagePlayItemList == null) {
/* 204 */           this.imagePlayItemList = imagePlayItemListNew;
/*     */         }
/*     */       } 
/*     */     } 
/* 208 */     return this.imagePlayItemList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void resetImagePlayItemList() {
/* 214 */     this.imagePlayItemList = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void delete() {
/* 223 */     if (this.myDao == null) {
/* 224 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 226 */     this.myDao.delete(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refresh() {
/* 235 */     if (this.myDao == null) {
/* 236 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 238 */     this.myDao.refresh(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 247 */     if (this.myDao == null) {
/* 248 */       throw new DaoException("Entity is detached from DAO context");
/*     */     }
/* 250 */     this.myDao.update(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void __setDaoSession(DaoSession daoSession) {
/* 256 */     this.daoSession = daoSession;
/* 257 */     this.myDao = (daoSession != null) ? daoSession.getPlayAreaListDaoBeanDao() : null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\PlayAreaListDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */