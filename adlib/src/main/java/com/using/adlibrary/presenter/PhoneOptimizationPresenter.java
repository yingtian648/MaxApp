/*     */ package com.using.adlibrary.presenter;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.bean.dao.MaterialDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayListMapDaoBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneImagePlayItemListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneMainProgramListBean;
/*     */ import com.using.adlibrary.bean.phone.PhonePlayAreaListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneRootListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneVideoPlayItemListBean;
/*     */ import com.using.adlibrary.bean.utils.DaoOperation;
/*     */ import com.using.adlibrary.callback.IPhoneMaterialDownloadCallback;
/*     */ import com.using.adlibrary.callback.IPhoneNetCallback;
/*     */ import com.using.adlibrary.callback.IPhoneStringCallback;
/*     */ import com.using.adlibrary.callback.IPresenter;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.factory.ThreadPollFactory;
/*     */ import com.using.adlibrary.model.ConnectRequestModel;
/*     */ import com.using.adlibrary.utils.SPUtils;
/*     */ import com.zhy.http.okhttp.callback.StringCallback;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import okhttp3.Call;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
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
/*     */ public class PhoneOptimizationPresenter
/*     */ {
/*     */   public static final String PLAY_LIST_ERROR = "PLAY_LIST_ERROR";
/*     */   public static final String PLAY_LIST_JSON_TIMEOUT = "PLAY_LIST_JSON_TIMEOUT";
/*     */   public static final String MATERIAL_NOT_EXIST = "MATERIAL_NOT_EXIST";
/*     */   public static final String MATERIAL_DOWNLOAD_ERROR = "MATERIAL_DOWNLOAD_ERROR";
/*     */   public static final String MATERIAL_DOWNLOAD_TIMEOUT = "MATERIAL_DOWNLOAD_TIMEOUT";
/*     */   public static final String MATERIAL_CHECK_ERROR = "MATERIAL_CHECK_ERROR";
/*     */   private String uuid;
/*     */   private IPresenter uiCallback;
/*     */   private ConnectRequestModel mRequest;
/*     */   private ScheduledExecutorService scheduledExecutorService;
/*     */   private TimerPhoneRunnable timerPhoneRunnable;
/*     */   private ScheduledFuture scheduledFuture;
/*     */   
/*     */   public PhoneOptimizationPresenter(String uuid, IPresenter presenter) {
/*  69 */     this.uuid = uuid;
/*  70 */     this.uiCallback = presenter;
/*  71 */     this.mRequest = new ConnectRequestModel();
/*  72 */     this.scheduledExecutorService = ThreadPollFactory.getInstance().getScheduledExecutorService();
/*     */   }
/*     */   
/*     */   public void start() {
/*  76 */     Logger.e("调用PhoneOptimizationPresenter   start方法", new Object[0]);
/*     */     
/*  78 */     if (this.timerPhoneRunnable == null) {
/*     */       
/*  80 */       this.timerPhoneRunnable = new TimerPhoneRunnable(this.uuid, this.mRequest, new TimerPhoneRunnable.TimerRunnableCallback()
/*     */           {
/*     */             public void onNeedUpdatePlayList(List<PlayListMapDaoBean> list, final String playListString)
/*     */             {
/*  84 */               Logger.e("有更新，物业节目单开始更新", new Object[0]);
/*  85 */               PhoneOptimizationPresenter.this.scheduledExecutorService.execute(new ObtainPlayListJsonRunnable(PhoneOptimizationPresenter.this.uuid, list, PhoneOptimizationPresenter.this.mRequest, new ObtainPlayListJsonRunnable.ObtainPlayListJsonCallback()
/*     */                     {
/*     */                       public void onSuccess(List<PhoneRootListBean> listBeans)
/*     */                       {
/*  89 */                         Logger.e("有更新，物业节目单开始下载", new Object[0]);
/*  90 */                         PhoneOptimizationPresenter.this.scheduledExecutorService.execute(new DownloadMaterialRunnable(PhoneOptimizationPresenter.this.mRequest, listBeans, new DownloadMaterialRunnable.DownloadCallback()
/*     */                               {
/*     */                                 public void onAllDownloadSuccess(List<PhoneRootListBean> phoneRootListBeans)
/*     */                                 {
/*  94 */                                   DaoOperation.deletePhoneAll();
/*     */                                   
/*  96 */                                   Logger.e("PhoneOptimizationPresenter 将节目单数据保存到数据库，更新UI", new Object[0]);
/*  97 */                                   if (phoneRootListBeans != null && phoneRootListBeans.size() > 0) {
/*  98 */                                     for (PhoneRootListBean rootListBean : phoneRootListBeans) {
/*  99 */                                       DaoOperation.phoneRootListInsert(rootListBean);
/*     */                                     }
/*     */                                     
/* 102 */                                     if (PhoneOptimizationPresenter.this.uiCallback != null) {
/* 103 */                                       PhoneOptimizationPresenter.this.uiCallback.updateProgramList("Phone");
/* 104 */                                       PhoneOptimizationPresenter.this.uiCallback.uiInfo("Phone素材全部下载完成！更新界面");
/* 105 */                                       SPUtils.put(AdEngine.getInstances().getApplicationContext(), "playListArray", playListString);
/*     */                                     } 
/*     */                                   } 
/*     */                                 }
/*     */ 
/*     */                                 
/*     */                                 public void onDownloadError(String code) {
/* 112 */                                   PhoneOptimizationPresenter.this.timerPhoneRunnable.setPlayListArrayString("0");
/* 113 */                                   Logger.e("下载素材出现错误", new Object[0]);
/*     */                                 }
/*     */                               }));
/*     */                       }
/*     */ 
/*     */ 
/*     */                       
/*     */                       public void onError(String playListId) {
/* 121 */                         PhoneOptimizationPresenter.this.timerPhoneRunnable.setPlayListArrayString("0");
/* 122 */                         Logger.e("请求节目单json数据出现错误", new Object[0]);
/*     */                       }
/*     */                     }));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void onNullPlayList() {
/* 131 */               DaoOperation.deletePhoneAll();
/* 132 */               PhoneOptimizationPresenter.this.uiCallback.updateProgramList("phone没有节目单");
/* 133 */               PhoneOptimizationPresenter.this.timerPhoneRunnable.setPlayListArrayString("0");
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void onPlayListError(String code) {
/* 139 */               PhoneOptimizationPresenter.this.timerPhoneRunnable.setPlayListArrayString("0");
/*     */             }
/*     */           });
/*     */ 
/*     */       
/*     */       try {
/* 145 */         if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled()) {
/* 146 */           Logger.e("手机端定时任务存在，不重复添加", new Object[0]);
/*     */         } else {
/* 148 */           Logger.e("手机端定时任务不存在，添加定时任务", new Object[0]);
/* 149 */           this.scheduledFuture = this.scheduledExecutorService.scheduleAtFixedRate(this.timerPhoneRunnable, 
/*     */               
/* 151 */               ConfigManger.getInstance().getPhoneProgramHeartBeatDelayTime(), 
/* 152 */               ConfigManger.getInstance().getPhoneProgramHeartBeatIntervalTime(), TimeUnit.MILLISECONDS);
/*     */         }
/*     */       
/* 155 */       } catch (RejectedExecutionException e) {
/* 156 */         Logger.e("手机端无法添加任务", new Object[0]);
/* 157 */       } catch (NullPointerException e) {
/* 158 */         Logger.e("空指针异常", new Object[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void straightwayStart() {
/* 164 */     Logger.e("开始直接执行更新手机端节目单任务", new Object[0]);
/* 165 */     if (this.scheduledExecutorService != null && this.timerPhoneRunnable != null) {
/* 166 */       Logger.e("立即执行手机端节目单更新任务", new Object[0]);
/* 167 */       this.scheduledExecutorService.execute(this.timerPhoneRunnable);
/*     */     } 
/*     */   } public static interface TimerRunnableCallback {
/*     */     void onNeedUpdatePlayList(List<PlayListMapDaoBean> param1List, String param1String); void onNullPlayList(); void onPlayListError(String param1String); }
/*     */   public void close() {
/* 172 */     this.scheduledExecutorService = null;
/*     */     
/* 174 */     if (this.scheduledFuture != null) {
/* 175 */       this.scheduledFuture.cancel(true);
/*     */     }
/*     */     
/* 178 */     if (this.timerPhoneRunnable != null) {
/* 179 */       this.timerPhoneRunnable = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class TimerPhoneRunnable
/*     */     implements Runnable, IPhoneNetCallback
/*     */   {
/*     */     private Gson gson;
/*     */     private String uuidT;
/*     */     private String playListArrayString;
/*     */     private ConnectRequestModel requestModel;
/*     */     private TimerRunnableCallback callback;
/*     */     private boolean alreadyUpdate = false;
/*     */     
/*     */     private TimerPhoneRunnable(String uuid, ConnectRequestModel requestModel, TimerRunnableCallback callback) {
/* 194 */       this.uuidT = uuid;
/* 195 */       this.gson = new Gson();
/* 196 */       this.requestModel = requestModel;
/* 197 */       this.callback = callback;
/* 198 */       this.playListArrayString = (String)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "playListArray", "0");
/*     */     }
/*     */     
/*     */     public void setPlayListArrayString(String playListArrayString) {
/* 202 */       this.playListArrayString = playListArrayString;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 207 */       if (this.requestModel != null) {
/* 208 */         Logger.e("请求phone节目单", new Object[0]);
/* 209 */         this.requestModel.deviceHeartBeatPhone(this.uuidT, this.playListArrayString, this);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int deviceNetError(String msg) {
/* 220 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int deviceHeartBeatCallback(String jsonPlayListArray) {
/* 230 */       Logger.e("phone手机H5 心跳数据为： " + jsonPlayListArray, new Object[0]);
/* 231 */       if (jsonPlayListArray != null && jsonPlayListArray.length() > 0 && !jsonPlayListArray.equals("[]")) {
/*     */ 
/*     */         
/* 234 */         Type type = (new TypeToken<List<PlayListMapDaoBean>>() {  }).getType();
/*     */         
/* 236 */         List<PlayListMapDaoBean> netPlayListMaps = (List<PlayListMapDaoBean>)this.gson.fromJson(jsonPlayListArray, type);
/* 237 */         String netPlayListArray = idListToString(mapsToIds(netPlayListMaps));
/* 238 */         if (netPlayListArray != null) {
/* 239 */           if (!netPlayListArray.equals(this.playListArrayString)) {
/*     */             
/* 241 */             this.callback.onNeedUpdatePlayList(netPlayListMaps, netPlayListArray);
/* 242 */             Logger.e("更新物业节目单", new Object[0]);
/* 243 */             this.playListArrayString = netPlayListArray;
/* 244 */             this.alreadyUpdate = false;
/*     */           } else {
/*     */             
/* 247 */             Logger.e("本地与后端一致，不更新物业节目单", new Object[0]);
/*     */           } 
/*     */         } else {
/*     */           
/* 251 */           this.callback.onPlayListError("PLAY_LIST_ERROR");
/* 252 */           this.alreadyUpdate = false;
/* 253 */           Logger.e("物业节目单获取数据错误", new Object[0]);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 258 */       else if (!this.alreadyUpdate) {
/* 259 */         this.callback.onNullPlayList();
/* 260 */         this.alreadyUpdate = true;
/*     */       } 
/*     */       
/* 263 */       return 0;
/*     */     }
/*     */     public static interface TimerRunnableCallback {
/*     */       void onNeedUpdatePlayList(List<PlayListMapDaoBean> param2List, String param2String);
/*     */       void onNullPlayList();
/*     */       void onPlayListError(String param2String); }
/*     */     private List<Long> mapsToIds(List<PlayListMapDaoBean> playListMapDaoBeans) {
/* 270 */       List<Long> idss = new ArrayList<>();
/* 271 */       if (playListMapDaoBeans != null && playListMapDaoBeans.size() > 0) {
/* 272 */         for (PlayListMapDaoBean mapDaoBean : playListMapDaoBeans) {
/* 273 */           idss.add(Long.valueOf(mapDaoBean.getPlaylist_id()));
/*     */         }
/*     */       }
/* 276 */       return idss;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String idListToString(List<Long> playIdLists) {
/* 283 */       String str = "";
/* 284 */       StringBuilder stringBuilder = new StringBuilder();
/* 285 */       if (playIdLists != null && playIdLists.size() > 0) {
/* 286 */         for (Iterator<Long> iterator = playIdLists.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 287 */           stringBuilder = stringBuilder.append(id).append(","); }
/*     */         
/* 289 */         str = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
/*     */       } 
/* 291 */       return str;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ObtainPlayListJsonRunnable
/*     */     implements Runnable, IPhoneStringCallback
/*     */   {
/*     */     private Gson gson;
/*     */     
/*     */     private String uuidO;
/*     */     
/*     */     private List<PlayListMapDaoBean> list;
/*     */     
/*     */     private Iterator<PlayListMapDaoBean> iterator;
/*     */     
/*     */     private ConnectRequestModel requestModel;
/*     */     
/*     */     private ObtainPlayListJsonCallback callback;
/*     */     
/*     */     private boolean alreadyRequest = false;
/*     */     
/*     */     private boolean errorState = false;
/* 314 */     private List<PhoneRootListBean> phoneRootListBeans = new ArrayList<>();
/*     */     
/*     */     private boolean isRun = true;
/* 317 */     private int timeOutCount = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     private ObtainPlayListJsonRunnable(String uuid, List<PlayListMapDaoBean> list, ConnectRequestModel requestModel, ObtainPlayListJsonCallback callback) {
/* 322 */       this.list = list;
/* 323 */       this.uuidO = uuid;
/* 324 */       this.gson = new Gson();
/* 325 */       this.callback = callback;
/* 326 */       this.iterator = list.iterator();
/* 327 */       this.requestModel = requestModel;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 333 */       while (this.isRun) {
/* 334 */         if (this.iterator.hasNext() && !this.errorState) {
/* 335 */           if (!this.alreadyRequest) {
/* 336 */             this.alreadyRequest = true;
/* 337 */             long playListId = ((PlayListMapDaoBean)this.iterator.next()).getPlaylist_id();
/* 338 */             this.requestModel.deviceGetPlaylistForH5(this.uuidO, String.valueOf(playListId), this);
/*     */           }  continue;
/* 340 */         }  if (this.errorState)
/*     */           break; 
/* 342 */         if (!this.iterator.hasNext()) {
/* 343 */           if (this.phoneRootListBeans.size() >= this.list.size()) {
/* 344 */             this.callback.onSuccess(this.phoneRootListBeans);
/*     */             break;
/*     */           } 
/*     */           try {
/* 348 */             Thread.sleep(100L);
/* 349 */             if (++this.timeOutCount >= 10) {
/* 350 */               this.callback.onError("PLAY_LIST_JSON_TIMEOUT");
/*     */               break;
/*     */             } 
/* 353 */           } catch (InterruptedException e) {
/* 354 */             e.printStackTrace();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int onSuccess(String h5PlayListId, String msg) {
/* 364 */       Logger.e("phone节目单的json数据为： " + msg, new Object[0]);
/* 365 */       if (msg != null) {
/*     */         try {
/* 367 */           JSONObject jsonObject = new JSONObject(msg);
/* 368 */           String json = jsonObject.getString("json");
/*     */           
/* 370 */           PhoneRootListBean rootList = (PhoneRootListBean)this.gson.fromJson(json, PhoneRootListBean.class);
/* 371 */           if (rootList != null) {
/* 372 */             this.alreadyRequest = false;
/* 373 */             this.phoneRootListBeans.add(rootList);
/*     */           } else {
/* 375 */             this.callback.onError("PLAY_LIST_ERROR");
/*     */           }
/*     */         
/* 378 */         } catch (JSONException e) {
/* 379 */           e.printStackTrace();
/* 380 */           this.callback.onError("PLAY_LIST_ERROR");
/*     */         } 
/*     */       }
/* 383 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int onError(String playListId) {
/* 388 */       this.errorState = true;
/* 389 */       this.alreadyRequest = false;
/* 390 */       this.callback.onError("PLAY_LIST_ERROR");
/* 391 */       this.isRun = false;
/* 392 */       return 0;
/*     */     }
/*     */     
/*     */     public static interface ObtainPlayListJsonCallback {
/*     */       void onSuccess(List<PhoneRootListBean> param2List);
/*     */       
/*     */       void onError(String param2String);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class DownloadMaterialRunnable
/*     */     implements Runnable, IPhoneMaterialDownloadCallback {
/*     */     private ConnectRequestModel requestModel;
/*     */     private List<PhoneRootListBean> rootListBeans;
/* 406 */     private List<MaterialDaoBean> materialDaoBeans = new ArrayList<>();
/*     */     
/*     */     private Iterator<MaterialDaoBean> iterator;
/*     */     private boolean alreadyRequest = false;
/*     */     private boolean errorState = false;
/*     */     private DownloadCallback callback;
/*     */     private boolean isRun = true;
/* 413 */     private int timeOutCount = 0;
/* 414 */     private int downloadFileCount = 0;
/*     */     
/*     */     private DownloadMaterialRunnable(ConnectRequestModel requestModel, List<PhoneRootListBean> listBeans, DownloadCallback callback) {
/* 417 */       this.requestModel = requestModel;
/* 418 */       this.rootListBeans = listBeans;
/* 419 */       this.callback = callback;
/*     */       
/* 421 */       if (listBeans != null && listBeans.size() > 0) {
/* 422 */         for (PhoneRootListBean phoneRootListBean : listBeans) {
/* 423 */           for (PhoneMainProgramListBean phoneMainProgramListBean : phoneRootListBean.getMainProgramList()) {
/* 424 */             for (PhonePlayAreaListBean areaListBean : phoneMainProgramListBean.getPlayarea_list()) {
/*     */               
/* 426 */               List<PhoneImagePlayItemListBean> phoneImagePlayItemListBeans = areaListBean.getImagePlayItemList();
/*     */               
/* 428 */               if (phoneImagePlayItemListBeans != null && phoneImagePlayItemListBeans.size() > 0) {
/* 429 */                 for (PhoneImagePlayItemListBean phoneImagePlayItemListBean : areaListBean.getImagePlayItemList()) {
/*     */                   
/* 431 */                   MaterialDaoBean materialDaoBean = new MaterialDaoBean();
/* 432 */                   materialDaoBean.setItem_id(phoneImagePlayItemListBean.getItem_id());
/* 433 */                   materialDaoBean.setPlay_item_type("1");
/* 434 */                   materialDaoBean.setFile_name(phoneImagePlayItemListBean.getFile_name());
/* 435 */                   materialDaoBean.setFileMd5(phoneImagePlayItemListBean.getFile_md5());
/* 436 */                   materialDaoBean.setDownloadSuceess(false);
/* 437 */                   materialDaoBean.setIsDownLoad(false);
/*     */                   
/* 439 */                   this.materialDaoBeans.add(materialDaoBean);
/*     */                 } 
/*     */               }
/*     */               
/* 443 */               List<PhoneVideoPlayItemListBean> phoneVideoPlayItemListBeans = areaListBean.getVideoPlayItemList();
/*     */               
/* 445 */               if (phoneVideoPlayItemListBeans != null && phoneVideoPlayItemListBeans.size() > 0) {
/* 446 */                 for (PhoneVideoPlayItemListBean phoneVideoPlayItemListBean : areaListBean.getVideoPlayItemList()) {
/*     */                   
/* 448 */                   MaterialDaoBean materialDaoBean = new MaterialDaoBean();
/* 449 */                   materialDaoBean.setItem_id(phoneVideoPlayItemListBean.getItem_id());
/* 450 */                   materialDaoBean.setPlay_item_type("2");
/* 451 */                   materialDaoBean.setFile_name(phoneVideoPlayItemListBean.getFile_name());
/* 452 */                   materialDaoBean.setFileMd5(phoneVideoPlayItemListBean.getFile_md5());
/* 453 */                   materialDaoBean.setDownloadSuceess(false);
/* 454 */                   materialDaoBean.setIsDownLoad(false);
/*     */                   
/* 456 */                   this.materialDaoBeans.add(materialDaoBean);
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 463 */       Logger.e("开始物业执行下载任务初始化", new Object[0]);
/* 464 */       this.iterator = this.materialDaoBeans.iterator();
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 469 */       Logger.e("开始物业执行下载任务", new Object[0]);
/* 470 */       while (this.isRun) {
/* 471 */         if (this.iterator.hasNext() && !this.errorState) {
/* 472 */           if (!this.alreadyRequest) {
/* 473 */             this.alreadyRequest = true;
/* 474 */             final MaterialDaoBean material = this.iterator.next();
/*     */             
/* 476 */             Logger.e("开始下载", new Object[0]);
/* 477 */             this.requestModel.deviceCheckH5MaterialIsExist(material.getFile_name(), material.getPlay_item_type(), new StringCallback()
/*     */                 {
/*     */                   public void onError(Call call, Exception e, int i) {
/* 480 */                     DownloadMaterialRunnable.this.errorState = true;
/* 481 */                     DownloadMaterialRunnable.this.callback.onDownloadError("MATERIAL_CHECK_ERROR");
/*     */                   }
/*     */                   
/*     */                   public void onResponse(String s, int i)
/*     */                   {
/* 486 */                     Logger.e("Phone检查要下载的文件    " + s, new Object[0]);
/* 487 */                     if (!s.isEmpty())
/*     */                       try {
/* 489 */                         JSONObject jsonObject = new JSONObject(s);
/* 490 */                         if (jsonObject.getBoolean("isSuccess")) {
/* 491 */                           DownloadMaterialRunnable.this.requestModel.deviceDownloadFileH5(material.getFile_name(), material
/* 492 */                               .getFileMd5(), material.getPlay_item_type(), DownloadMaterialRunnable.this);
/*     */                         } else {
/* 494 */                           DownloadMaterialRunnable.this.errorState = true;
/* 495 */                           DownloadMaterialRunnable.this.callback.onDownloadError("MATERIAL_NOT_EXIST");
/*     */                         } 
/* 497 */                       } catch (JSONException e) {
/* 498 */                         e.printStackTrace();
/* 499 */                         DownloadMaterialRunnable.this.errorState = true;
/* 500 */                         DownloadMaterialRunnable.this.callback.onDownloadError("MATERIAL_CHECK_ERROR");
/*     */                       }   }
/*     */                 });
/*     */           } 
/*     */           continue;
/*     */         } 
/* 506 */         if (this.errorState)
/*     */           break; 
/* 508 */         if (!this.iterator.hasNext())
/*     */           try {
/* 510 */             if (this.downloadFileCount >= this.materialDaoBeans.size()) {
/* 511 */               this.callback.onAllDownloadSuccess(this.rootListBeans); break;
/*     */             } 
/* 513 */             if (++this.timeOutCount >= 1000) {
/* 514 */               this.callback.onDownloadError("MATERIAL_DOWNLOAD_TIMEOUT");
/*     */               break;
/*     */             } 
/* 517 */             Thread.sleep(1000L);
/* 518 */           } catch (InterruptedException e) {
/* 519 */             e.printStackTrace();
/*     */           }  
/*     */       } 
/*     */     }
/*     */     
/*     */     public static interface DownloadCallback
/*     */     {
/*     */       void onAllDownloadSuccess(List<PhoneRootListBean> param2List);
/*     */       
/*     */       void onDownloadError(String param2String);
/*     */     }
/*     */     
/*     */     public void downloadFailure(String fileName) {
/* 532 */       this.errorState = true;
/* 533 */       this.alreadyRequest = false;
/* 534 */       this.isRun = false;
/* 535 */       this.callback.onDownloadError("MATERIAL_DOWNLOAD_ERROR");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void downloadError(String fileName) {
/* 545 */       this.errorState = true;
/* 546 */       this.alreadyRequest = false;
/* 547 */       this.isRun = false;
/* 548 */       this.callback.onDownloadError("MATERIAL_DOWNLOAD_ERROR");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void downloading(String progress) {
/* 556 */       this.errorState = false;
/* 557 */       this.alreadyRequest = true;
/* 558 */       Logger.i(progress, new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void downloadSuccess(String name) {
/* 568 */       this.errorState = false;
/* 569 */       this.alreadyRequest = false;
/* 570 */       this.downloadFileCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\presenter\PhoneOptimizationPresenter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */