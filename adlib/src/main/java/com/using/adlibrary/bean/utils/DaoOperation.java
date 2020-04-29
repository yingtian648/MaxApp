/*     */ package com.using.adlibrary.bean.utils;
/*     */ 
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.bean.dao.ImagePlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.ImmediateProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MainProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MaterialDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayAreaListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayListMapDaoBean;
/*     */ import com.using.adlibrary.bean.dao.ProgramCountBean;
/*     */ import com.using.adlibrary.bean.dao.RootListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.TaskPackageCountBean;
/*     */ import com.using.adlibrary.bean.dao.TimingProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.VideoPlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneImagePlayItemListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneMainProgramListBean;
/*     */ import com.using.adlibrary.bean.phone.PhonePlayAreaListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneRootListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneVideoPlayItemListBean;
/*     */ import com.using.adlibrary.dao.ImagePlayItemListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.ImmediateProgramListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.MainProgramListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.MaterialDaoBeanDao;
/*     */ import com.using.adlibrary.dao.PlayAreaListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.PlayListMapDaoBeanDao;
/*     */ import com.using.adlibrary.dao.RootListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.TimingProgramListDaoBeanDao;
/*     */ import com.using.adlibrary.dao.VideoPlayItemListDaoBeanDao;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DaoOperation
/*     */ {
/*  40 */   private static PlayListMapDaoBeanDao listMapDaoBeanDao = AdEngine.getInstances().getDaoSession().getPlayListMapDaoBeanDao();
/*  41 */   private static RootListDaoBeanDao rootListDaoBeanDao = AdEngine.getInstances().getDaoSession().getRootListDaoBeanDao();
/*  42 */   private static MainProgramListDaoBeanDao mainProgramListDaoBeanDao = AdEngine.getInstances().getDaoSession().getMainProgramListDaoBeanDao();
/*  43 */   private static TimingProgramListDaoBeanDao timingProgramListDaoBeanDao = AdEngine.getInstances().getDaoSession().getTimingProgramListDaoBeanDao();
/*  44 */   private static ImmediateProgramListDaoBeanDao immediateProgramListDaoBeanDao = AdEngine.getInstances().getDaoSession().getImmediateProgramListDaoBeanDao();
/*  45 */   private static PlayAreaListDaoBeanDao playAreaListDaoBeanDao = AdEngine.getInstances().getDaoSession().getPlayAreaListDaoBeanDao();
/*  46 */   private static VideoPlayItemListDaoBeanDao videoPlayItemListDaoBeanDao = AdEngine.getInstances().getDaoSession().getVideoPlayItemListDaoBeanDao();
/*  47 */   private static ImagePlayItemListDaoBeanDao imagePlayItemListDaoBeanDao = AdEngine.getInstances().getDaoSession().getImagePlayItemListDaoBeanDao();
/*  48 */   private static MaterialDaoBeanDao materialDaoBeanDao = AdEngine.getInstances().getDaoSession().getMaterialDaoBeanDao();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void playListMapInsert(PlayListMapDaoBean entity) {
/*  54 */     listMapDaoBeanDao.insertOrReplace(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void playListMapDelete() {
/*  61 */     listMapDaoBeanDao.deleteAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<PlayListMapDaoBean> playListMapCheckAll() {
/*  68 */     return listMapDaoBeanDao.loadAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void phoneRootListInsert(PhoneRootListBean phoneRootListBean) {
/*  75 */     AdEngine.getInstances().getDaoSession().getPhoneRootListBeanDao().insertOrReplace(phoneRootListBean);
/*     */     
/*  77 */     for (PhoneMainProgramListBean phoneMainProgramListBean : phoneRootListBean.getMainProgramList()) {
/*     */ 
/*     */       
/*  80 */       phoneMainProgramListBean.setLink_id(phoneRootListBean.getId().longValue());
/*  81 */       AdEngine.getInstances().getDaoSession().getPhoneMainProgramListBeanDao().insertOrReplace(phoneMainProgramListBean);
/*     */       
/*  83 */       for (PhonePlayAreaListBean phonePlayAreaListBean : phoneMainProgramListBean.getPlayarea_list()) {
/*     */ 
/*     */         
/*  86 */         phonePlayAreaListBean.setLink_id(phoneMainProgramListBean.getId().longValue());
/*  87 */         AdEngine.getInstances().getDaoSession().getPhonePlayAreaListBeanDao().insertOrReplace(phonePlayAreaListBean);
/*     */         
/*  89 */         for (PhoneImagePlayItemListBean phoneImagePlayItemListBean : phonePlayAreaListBean.getImagePlayItemList()) {
/*     */ 
/*     */           
/*  92 */           phoneImagePlayItemListBean.setLink_id(phonePlayAreaListBean.getId().longValue());
/*  93 */           AdEngine.getInstances().getDaoSession().getPhoneImagePlayItemListBeanDao().insertOrReplace(phoneImagePlayItemListBean);
/*     */         } 
/*     */         
/*  96 */         for (PhoneVideoPlayItemListBean phoneVideoPlayItemListBean : phonePlayAreaListBean.getVideoPlayItemList()) {
/*     */ 
/*     */           
/*  99 */           phoneVideoPlayItemListBean.setLink_id(phonePlayAreaListBean.getId().longValue());
/* 100 */           AdEngine.getInstances().getDaoSession().getPhoneVideoPlayItemListBeanDao().insertOrReplace(phoneVideoPlayItemListBean);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void rootListInsert(RootListDaoBean rootListDaoBean) {
/* 110 */     rootListDaoBeanDao.insertOrReplace(rootListDaoBean);
/*     */     
/* 112 */     Logger.e("向数据库插入数据 ------播放节目单的名称： " + rootListDaoBean.getPlay_list_name(), new Object[0]);
/* 113 */     Logger.e("向数据库插入数据 ------播放节目单的ID： " + rootListDaoBean.getPlay_list_id(), new Object[0]);
/*     */     
/* 115 */     Long rootListDaoBeanId = rootListDaoBean.getId();
/*     */     
/* 117 */     List<MainProgramListDaoBean> mainProgramListDaoBeans = rootListDaoBean.getMainProgramList();
/* 118 */     for (MainProgramListDaoBean programListDaoBean : mainProgramListDaoBeans) {
/*     */       
/* 120 */       programListDaoBean.setLink_id(rootListDaoBeanId.longValue());
/*     */       
/* 122 */       mainProgramListDaoBeanDao.insertOrReplace(programListDaoBean);
/*     */       
/* 124 */       List<PlayAreaListDaoBean> playAreaListDaoBeans = programListDaoBean.getPlayarea_list();
/* 125 */       for (PlayAreaListDaoBean areaListDaoBean : playAreaListDaoBeans) {
/*     */         
/* 127 */         areaListDaoBean.setLink_id(programListDaoBean.getId().longValue());
/* 128 */         playAreaListDaoBeanDao.insertOrReplace(areaListDaoBean);
/*     */         
/* 130 */         List<ImagePlayItemListDaoBean> itemListDaoBeans = areaListDaoBean.getImagePlayItemList();
/* 131 */         for (ImagePlayItemListDaoBean imagePlayItemListDaoBean : itemListDaoBeans) {
/*     */           
/* 133 */           imagePlayItemListDaoBean.setLink_id(areaListDaoBean.getId().longValue());
/* 134 */           imagePlayItemListDaoBeanDao.insertOrReplace(imagePlayItemListDaoBean);
/*     */         } 
/*     */         
/* 137 */         List<VideoPlayItemListDaoBean> videoPlayItemListDaoBeans = areaListDaoBean.getVideoPlayItemList();
/* 138 */         for (VideoPlayItemListDaoBean videoPlayItemListDaoBean : videoPlayItemListDaoBeans) {
/*     */           
/* 140 */           videoPlayItemListDaoBean.setLink_id(areaListDaoBean.getId().longValue());
/* 141 */           videoPlayItemListDaoBeanDao.insertOrReplace(videoPlayItemListDaoBean);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 148 */     List<TimingProgramListDaoBean> timingProgramListDaoBeans = rootListDaoBean.getTimingProgramList();
/* 149 */     for (TimingProgramListDaoBean timingProgramListDaoBean : timingProgramListDaoBeans) {
/*     */       
/* 151 */       timingProgramListDaoBean.setLink_id(rootListDaoBeanId.longValue());
/*     */       
/* 153 */       timingProgramListDaoBeanDao.insertOrReplace(timingProgramListDaoBean);
/*     */       
/* 155 */       List<PlayAreaListDaoBean> playAreaListDaoBeans = timingProgramListDaoBean.getPlayarea_list();
/* 156 */       for (PlayAreaListDaoBean areaListDaoBean : playAreaListDaoBeans) {
/*     */         
/* 158 */         areaListDaoBean.setLink_id(timingProgramListDaoBean.getId().longValue());
/* 159 */         playAreaListDaoBeanDao.insertOrReplace(areaListDaoBean);
/*     */         
/* 161 */         List<ImagePlayItemListDaoBean> itemListDaoBeans = areaListDaoBean.getImagePlayItemList();
/* 162 */         for (ImagePlayItemListDaoBean imagePlayItemListDaoBean : itemListDaoBeans) {
/*     */           
/* 164 */           imagePlayItemListDaoBean.setLink_id(areaListDaoBean.getId().longValue());
/* 165 */           imagePlayItemListDaoBeanDao.insertOrReplace(imagePlayItemListDaoBean);
/*     */         } 
/*     */         
/* 168 */         List<VideoPlayItemListDaoBean> videoPlayItemListDaoBeans = areaListDaoBean.getVideoPlayItemList();
/* 169 */         for (VideoPlayItemListDaoBean videoPlayItemListDaoBean : videoPlayItemListDaoBeans) {
/*     */           
/* 171 */           videoPlayItemListDaoBean.setLink_id(areaListDaoBean.getId().longValue());
/* 172 */           videoPlayItemListDaoBeanDao.insertOrReplace(videoPlayItemListDaoBean);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 178 */     List<ImmediateProgramListDaoBean> immediateProgramListDaoBeans = rootListDaoBean.getImmediateProgramList();
/* 179 */     for (ImmediateProgramListDaoBean immediateProgramListDaoBean : immediateProgramListDaoBeans) {
/*     */       
/* 181 */       immediateProgramListDaoBean.setLink_id(rootListDaoBeanId.longValue());
/*     */       
/* 183 */       immediateProgramListDaoBeanDao.insertOrReplace(immediateProgramListDaoBean);
/*     */       
/* 185 */       List<PlayAreaListDaoBean> playAreaListDaoBeans = immediateProgramListDaoBean.getPlayarea_list();
/* 186 */       for (PlayAreaListDaoBean areaListDaoBean : playAreaListDaoBeans) {
/*     */         
/* 188 */         areaListDaoBean.setLink_id(immediateProgramListDaoBean.getId().longValue());
/* 189 */         playAreaListDaoBeanDao.insertOrReplace(areaListDaoBean);
/*     */         
/* 191 */         List<ImagePlayItemListDaoBean> itemListDaoBeans = areaListDaoBean.getImagePlayItemList();
/* 192 */         for (ImagePlayItemListDaoBean imagePlayItemListDaoBean : itemListDaoBeans) {
/*     */           
/* 194 */           imagePlayItemListDaoBean.setLink_id(areaListDaoBean.getId().longValue());
/* 195 */           imagePlayItemListDaoBeanDao.insertOrReplace(imagePlayItemListDaoBean);
/*     */         } 
/*     */         
/* 198 */         List<VideoPlayItemListDaoBean> videoPlayItemListDaoBeans = areaListDaoBean.getVideoPlayItemList();
/* 199 */         for (VideoPlayItemListDaoBean videoPlayItemListDaoBean : videoPlayItemListDaoBeans) {
/*     */           
/* 201 */           videoPlayItemListDaoBean.setLink_id(areaListDaoBean.getId().longValue());
/* 202 */           videoPlayItemListDaoBeanDao.insertOrReplace(videoPlayItemListDaoBean);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deleteAll() {
/* 214 */     rootListDaoBeanDao.deleteAll();
/* 215 */     playAreaListDaoBeanDao.deleteAll();
/*     */     
/* 217 */     mainProgramListDaoBeanDao.deleteAll();
/* 218 */     immediateProgramListDaoBeanDao.deleteAll();
/* 219 */     timingProgramListDaoBeanDao.deleteAll();
/*     */     
/* 221 */     imagePlayItemListDaoBeanDao.deleteAll();
/* 222 */     videoPlayItemListDaoBeanDao.deleteAll();
/*     */     
/* 224 */     listMapDaoBeanDao.deleteAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deletePhoneAll() {
/* 233 */     AdEngine.getInstances().getDaoSession().getPhoneRootListBeanDao().deleteAll();
/* 234 */     AdEngine.getInstances().getDaoSession().getPhoneMainProgramListBeanDao().deleteAll();
/* 235 */     AdEngine.getInstances().getDaoSession().getPhonePlayAreaListBeanDao().deleteAll();
/* 236 */     AdEngine.getInstances().getDaoSession().getPhoneMainProgramListBeanDao().deleteAll();
/* 237 */     AdEngine.getInstances().getDaoSession().getPhoneImagePlayItemListBeanDao().deleteAll();
/* 238 */     AdEngine.getInstances().getDaoSession().getPhoneVideoPlayItemListBeanDao().deleteAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void RootListDeleteNotIn(List<Long> ids) {
/* 246 */     String idss = "";
/* 247 */     for (Iterator<Long> iterator = ids.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 248 */       idss = idss + id + ","; }
/*     */     
/* 250 */     idss = idss.substring(0, idss.length() - 1);
/*     */     
/* 252 */     rootListDaoBeanDao.getDatabase().execSQL("delete from ROOT_LIST_DAO_BEAN where _id not in (" + idss + " )");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<RootListDaoBean> RootListCheckAll() {
/* 259 */     return rootListDaoBeanDao.loadAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void materialInsert(MaterialDaoBean materialDaoBean) {
/* 268 */     materialDaoBeanDao.insertOrReplace(materialDaoBean);
/*     */   }
/*     */   
/*     */   public static void materialDeleteAll() {
/* 272 */     materialDaoBeanDao.deleteAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void materialDeleteById(long id) {
/* 279 */     materialDaoBeanDao.deleteByKey(Long.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void materialDeleteByName(String fileName) {
/* 286 */     materialDaoBeanDao.getDatabase().execSQL("delete from MATERIAL_DAO_BEAN where FILE_NAME='" + fileName + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void materialChangeById(long id, boolean isDownload) {
/* 297 */     MaterialDaoBean materialDaoBean = (MaterialDaoBean)materialDaoBeanDao.queryBuilder().where(MaterialDaoBeanDao.Properties.Item_id.eq(Long.valueOf(id)), new org.greenrobot.greendao.query.WhereCondition[0]).unique();
/* 298 */     materialDaoBean.setIsDownLoad(isDownload);
/* 299 */     materialDaoBeanDao.update(materialDaoBean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void materialChangeByName(String name, boolean isDownload) {
/* 307 */     int i = isDownload ? 1 : 0;
/*     */     
/* 309 */     materialDaoBeanDao.getDatabase().execSQL("update MATERIAL_DAO_BEAN set IS_DOWN_LOAD=" + i + " where FILE_NAME='" + name + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<MaterialDaoBean> materialCheckAll() {
/* 320 */     return materialDaoBeanDao.loadAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void programDelete() {
/* 327 */     AdEngine.getInstances().getDaoSession().getProgramCountBeanDao().deleteAll();
/* 328 */     AdEngine.getInstances().getDaoSession().getTaskPackageCountBeanDao().deleteAll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void programInsert(RootListDaoBean rootListDaoBean) {
/* 337 */     ProgramCountBean programCountBean = new ProgramCountBean();
/* 338 */     programCountBean.setProgramId(rootListDaoBean.getPlay_list_id());
/* 339 */     AdEngine.getInstances().getDaoSession().getProgramCountBeanDao().insertOrReplace(programCountBean);
/*     */ 
/*     */     
/* 342 */     List<MainProgramListDaoBean> mainProgramListDaoBeans = rootListDaoBean.getMainProgramList();
/* 343 */     for (MainProgramListDaoBean programListDaoBean : mainProgramListDaoBeans) {
/*     */ 
/*     */       
/* 346 */       TaskPackageCountBean taskPackageCountBean = new TaskPackageCountBean();
/* 347 */       taskPackageCountBean.setLink_id(programCountBean.getId());
/* 348 */       taskPackageCountBean.setTaskId(programListDaoBean.getPlay_list_id());
/* 349 */       taskPackageCountBean.setType(0);
/* 350 */       AdEngine.getInstances().getDaoSession().getTaskPackageCountBeanDao().insertOrReplace(taskPackageCountBean);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 355 */     List<TimingProgramListDaoBean> timingProgramListDaoBeans = rootListDaoBean.getTimingProgramList();
/* 356 */     for (TimingProgramListDaoBean timingProgramListDaoBean : timingProgramListDaoBeans) {
/*     */ 
/*     */       
/* 359 */       TaskPackageCountBean taskPackageCountBean = new TaskPackageCountBean();
/* 360 */       taskPackageCountBean.setLink_id(programCountBean.getId());
/* 361 */       taskPackageCountBean.setTaskId(timingProgramListDaoBean.getPlay_list_id());
/* 362 */       taskPackageCountBean.setType(1);
/* 363 */       AdEngine.getInstances().getDaoSession().getTaskPackageCountBeanDao().insertOrReplace(taskPackageCountBean);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bea\\utils\DaoOperation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */