/*     */ package com.using.adlibrary.presenter;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.bean.dao.ImagePlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MainProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MaterialDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayAreaListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayListMapDaoBean;
/*     */ import com.using.adlibrary.bean.dao.RootListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.VideoPlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.utils.DaoOperation;
/*     */ import com.using.adlibrary.callback.IComputerMaterialDownloadCallback;
/*     */ import com.using.adlibrary.callback.IComputerNetCallback;
/*     */ import com.using.adlibrary.callback.IComputerStringCallback;
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
/*     */ public class ConnectOptimizationPresenter
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
/*     */   private TimerComputerRunnable timerComputerRunnable;
/*     */   private ScheduledFuture scheduledFuture;
/*     */   
/*     */   public ConnectOptimizationPresenter(String uuid, IPresenter presenter) {
/*  84 */     this.uuid = uuid;
/*  85 */     this.uiCallback = presenter;
/*  86 */     this.mRequest = new ConnectRequestModel();
/*  87 */     this.scheduledExecutorService = ThreadPollFactory.getInstance().getScheduledExecutorService();
/*     */     
/*  89 */     Logger.e("初始化  ConnectOptimizationPresenter", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/*  94 */     Logger.e("调用ConnectOptimizationPresenter   start方法", new Object[0]);
/*  95 */     if (this.timerComputerRunnable == null) {
/*     */       
/*  97 */       this.timerComputerRunnable = new TimerComputerRunnable(this.uuid, this.mRequest, new TimerComputerRunnable.TimerRunnableCallback()
/*     */           {
/*     */             
/*     */             public void onNeedUpdatePlayList(List<PlayListMapDaoBean> list, final String playListString)
/*     */             {
/* 102 */               ConnectOptimizationPresenter.this.scheduledExecutorService.execute(new ObtainPlayListJsonRunnable(ConnectOptimizationPresenter.this
/* 103 */                     .uuid, list, ConnectOptimizationPresenter.this.mRequest, new ObtainPlayListJsonRunnable.ObtainPlayListJsonCallback()
/*     */                     {
/*     */                       public void onSuccess(List<RootListDaoBean> listBeans)
/*     */                       {
/* 107 */                         ConnectOptimizationPresenter.this.scheduledExecutorService.execute(new DownloadMaterialRunnable(ConnectOptimizationPresenter.this
/* 108 */                               .mRequest, listBeans, new DownloadMaterialRunnable.DownloadCallback()
/*     */                               {
/*     */                                 public void onAllDownloadSuccess(List<RootListDaoBean> rootListDaoBeans)
/*     */                                 {
/* 112 */                                   DaoOperation.deleteAll();
/*     */                                   
/* 114 */                                   if (rootListDaoBeans != null && rootListDaoBeans.size() > 0) {
/* 115 */                                     for (RootListDaoBean rootListBean : rootListDaoBeans) {
/*     */                                       
/* 117 */                                       DaoOperation.rootListInsert(rootListBean);
/*     */ 
/*     */                                       
/* 120 */                                       DaoOperation.programInsert(rootListBean);
/*     */                                     } 
/*     */                                     
/* 123 */                                     if (ConnectOptimizationPresenter.this.uiCallback != null) {
/* 124 */                                       ConnectOptimizationPresenter.this.uiCallback.updateProgramList("Phone");
/* 125 */                                       ConnectOptimizationPresenter.this.uiCallback.uiInfo("电脑端素材全部下载完成！更新界面");
/* 126 */                                       SPUtils.put(AdEngine.getInstances().getApplicationContext(), "playListArrayComputer", playListString);
/*     */                                     } 
/*     */                                   } 
/*     */                                 }
/*     */ 
/*     */                                 
/*     */                                 public void onDownloadError(String code) {
/* 133 */                                   ConnectOptimizationPresenter.this.timerComputerRunnable.setPlayListArrayString("0");
/* 134 */                                   Logger.e("下载素材出现错误", new Object[0]);
/*     */                                 }
/*     */                               }));
/*     */                       }
/*     */ 
/*     */ 
/*     */                       
/*     */                       public void onError(String playListId) {
/* 142 */                         ConnectOptimizationPresenter.this.timerComputerRunnable.setPlayListArrayString("0");
/* 143 */                         Logger.e("请求节目单json数据出现错误", new Object[0]);
/*     */                       }
/*     */                     }));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void onNullPlayList() {
/* 152 */               DaoOperation.deleteAll();
/* 153 */               ConnectOptimizationPresenter.this.uiCallback.updateProgramList("电脑端没有节目单");
/* 154 */               ConnectOptimizationPresenter.this.timerComputerRunnable.setPlayListArrayString("0");
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void onPlayListError(String code) {
/* 160 */               ConnectOptimizationPresenter.this.timerComputerRunnable.setPlayListArrayString("0");
/*     */             }
/*     */           });
/*     */       
/*     */       try {
/* 165 */         if (this.scheduledFuture != null && !this.scheduledFuture.isCancelled()) {
/* 166 */           Logger.e("任务存在，不重复添加", new Object[0]);
/*     */         } else {
/* 168 */           Logger.e("任务不存在，添加定时任务", new Object[0]);
/* 169 */           this.scheduledFuture = this.scheduledExecutorService.scheduleAtFixedRate(this.timerComputerRunnable, 
/*     */               
/* 171 */               ConfigManger.getInstance().getProgramHeartBeatDelayTime(), 
/* 172 */               ConfigManger.getInstance().getProgramHeartBeatIntervalTime(), TimeUnit.MILLISECONDS);
/*     */         }
/*     */       
/* 175 */       } catch (RejectedExecutionException e) {
/* 176 */         Logger.e("电脑端无法添加任务", new Object[0]);
/* 177 */       } catch (NullPointerException e) {
/* 178 */         Logger.e("任务异常", new Object[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void straightwayStart() {
/* 185 */     Logger.e("开始直接执行更新电脑端节目单任务", new Object[0]);
/* 186 */     if (this.scheduledExecutorService != null && this.timerComputerRunnable != null) {
/* 187 */       this.scheduledExecutorService.execute(this.timerComputerRunnable);
/* 188 */       Logger.e("立即执行电脑端节目单更新任务", new Object[0]);
/*     */     } 
/*     */   } public static interface TimerRunnableCallback {
/*     */     void onNeedUpdatePlayList(List<PlayListMapDaoBean> param1List, String param1String); void onNullPlayList();
/*     */     void onPlayListError(String param1String); }
/*     */   public void close() {
/* 194 */     this.scheduledExecutorService = null;
/* 195 */     if (this.scheduledFuture != null) {
/* 196 */       this.scheduledFuture.cancel(true);
/*     */     }
/*     */     
/* 199 */     if (this.timerComputerRunnable != null) {
/* 200 */       this.timerComputerRunnable = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class TimerComputerRunnable
/*     */     implements Runnable, IComputerNetCallback
/*     */   {
/*     */     private Gson gson;
/*     */     
/*     */     private String uuidT;
/*     */     private String playListArrayString;
/*     */     private ConnectRequestModel requestModel;
/*     */     private TimerRunnableCallback callback;
/*     */     private boolean alreadyUpdate = false;
/*     */     
/*     */     private TimerComputerRunnable(String uuid, ConnectRequestModel requestModel, TimerRunnableCallback callback) {
/* 217 */       this.uuidT = uuid;
/* 218 */       this.gson = new Gson();
/* 219 */       this.requestModel = requestModel;
/* 220 */       this.callback = callback;
/* 221 */       this.playListArrayString = (String)SPUtils.get(AdEngine.getInstances().getApplicationContext(), "playListArrayComputer", "0");
/*     */     }
/*     */     
/*     */     public void setPlayListArrayString(String playListArrayString) {
/* 225 */       this.playListArrayString = playListArrayString;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 230 */       if (this.requestModel != null) {
/* 231 */         Logger.e("请求电脑端节目单，当前电脑端节目单：" + this.playListArrayString, new Object[0]);
/* 232 */         this.requestModel.deviceHeartBeatV2(this.uuidT, this.playListArrayString, this);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int onError(String playListId) {
/* 239 */       Logger.e("电脑端心跳请求异常： ", new Object[0]);
/* 240 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int onSuccess(String jsonPlayListArray) {
/* 246 */       Logger.e("电脑端心跳数据为： " + jsonPlayListArray, new Object[0]);
/* 247 */       if (jsonPlayListArray != null && jsonPlayListArray.length() > 0 && !jsonPlayListArray.equals("[]")) {
/*     */ 
/*     */         
/* 250 */         Type type = (new TypeToken<List<PlayListMapDaoBean>>() {  }).getType();
/*     */         
/* 252 */         List<PlayListMapDaoBean> netPlayListMaps = (List<PlayListMapDaoBean>)this.gson.fromJson(jsonPlayListArray, type);
/* 253 */         String netPlayListArray = idListToString(mapsToIds(netPlayListMaps));
/* 254 */         if (netPlayListArray != null) {
/* 255 */           if (!netPlayListArray.equals(this.playListArrayString))
/*     */           {
/* 257 */             this.callback.onNeedUpdatePlayList(netPlayListMaps, netPlayListArray);
/*     */             
/* 259 */             this.playListArrayString = netPlayListArray;
/* 260 */             this.alreadyUpdate = false;
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 266 */           this.callback.onPlayListError("PLAY_LIST_ERROR");
/* 267 */           this.alreadyUpdate = false;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 272 */       else if (!this.alreadyUpdate) {
/* 273 */         this.callback.onNullPlayList();
/* 274 */         this.alreadyUpdate = true;
/*     */       } 
/*     */ 
/*     */       
/* 278 */       return 0;
/*     */     }
/*     */     public static interface TimerRunnableCallback {
/*     */       void onNeedUpdatePlayList(List<PlayListMapDaoBean> param2List, String param2String);
/*     */       void onNullPlayList();
/*     */       void onPlayListError(String param2String); }
/*     */     private List<Long> mapsToIds(List<PlayListMapDaoBean> playListMapDaoBeans) {
/* 285 */       List<Long> idss = new ArrayList<>();
/* 286 */       if (playListMapDaoBeans != null && playListMapDaoBeans.size() > 0) {
/* 287 */         for (PlayListMapDaoBean mapDaoBean : playListMapDaoBeans) {
/* 288 */           idss.add(Long.valueOf(mapDaoBean.getPlaylist_id()));
/*     */         }
/*     */       }
/* 291 */       return idss;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String idListToString(List<Long> playIdLists) {
/* 298 */       String str = "";
/* 299 */       StringBuilder stringBuilder = new StringBuilder();
/* 300 */       if (playIdLists != null && playIdLists.size() > 0) {
/* 301 */         for (Iterator<Long> iterator = playIdLists.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 302 */           stringBuilder = stringBuilder.append(id).append(","); }
/*     */         
/* 304 */         str = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
/*     */       } 
/* 306 */       return str;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class ObtainPlayListJsonRunnable
/*     */     implements Runnable, IComputerStringCallback
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
/*     */     
/* 330 */     private List<RootListDaoBean> rootListDaoBeans = new ArrayList<>();
/*     */     
/*     */     private boolean isRun = true;
/* 333 */     private int timeOutCount = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     private ObtainPlayListJsonRunnable(String uuid, List<PlayListMapDaoBean> list, ConnectRequestModel requestModel, ObtainPlayListJsonCallback callback) {
/* 338 */       this.list = list;
/* 339 */       this.uuidO = uuid;
/* 340 */       this.gson = new Gson();
/* 341 */       this.callback = callback;
/* 342 */       this.iterator = list.iterator();
/* 343 */       this.requestModel = requestModel;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 349 */       while (this.isRun) {
/* 350 */         if (this.iterator.hasNext() && !this.errorState) {
/* 351 */           if (!this.alreadyRequest) {
/* 352 */             this.alreadyRequest = true;
/* 353 */             long playListId = ((PlayListMapDaoBean)this.iterator.next()).getPlaylist_id();
/* 354 */             this.requestModel.deviceRequestList(this.uuidO, playListId, this);
/*     */           }  continue;
/* 356 */         }  if (this.errorState)
/*     */           break; 
/* 358 */         if (!this.iterator.hasNext()) {
/* 359 */           if (this.rootListDaoBeans.size() >= this.list.size()) {
/* 360 */             this.callback.onSuccess(this.rootListDaoBeans);
/*     */             break;
/*     */           } 
/*     */           try {
/* 364 */             Thread.sleep(100L);
/* 365 */             if (++this.timeOutCount >= 10) {
/* 366 */               this.callback.onError("PLAY_LIST_JSON_TIMEOUT");
/*     */               break;
/*     */             } 
/* 369 */           } catch (InterruptedException e) {
/* 370 */             e.printStackTrace();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int onSuccess(String h5PlayListId, String msg) {
/* 380 */       Logger.e("电脑节目单的json数据为： " + msg, new Object[0]);
/* 381 */       this.requestModel.deviceProgramState(this.uuidO, Long.parseLong(h5PlayListId), Integer.valueOf(0), "已接收");
/* 382 */       if (msg != null) {
/*     */         try {
/* 384 */           JSONObject jsonObject = new JSONObject(msg);
/* 385 */           String json = jsonObject.getString("json");
/* 386 */           RootListDaoBean rootList = (RootListDaoBean)this.gson.fromJson(json, RootListDaoBean.class);
/* 387 */           if (rootList != null) {
/* 388 */             this.alreadyRequest = false;
/* 389 */             this.rootListDaoBeans.add(rootList);
/*     */           } else {
/* 391 */             this.callback.onError("PLAY_LIST_ERROR");
/*     */           }
/*     */         
/* 394 */         } catch (JSONException e) {
/* 395 */           e.printStackTrace();
/* 396 */           this.callback.onError("PLAY_LIST_ERROR");
/*     */         } 
/*     */       }
/* 399 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int onError(String playListId) {
/* 404 */       this.errorState = true;
/* 405 */       this.alreadyRequest = false;
/* 406 */       this.callback.onError("PLAY_LIST_ERROR");
/* 407 */       this.isRun = false;
/* 408 */       return 0;
/*     */     }
/*     */     
/*     */     public static interface ObtainPlayListJsonCallback
/*     */     {
/*     */       void onSuccess(List<RootListDaoBean> param2List);
/*     */       
/*     */       void onError(String param2String);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class DownloadMaterialRunnable
/*     */     implements Runnable, IComputerMaterialDownloadCallback {
/*     */     private ConnectRequestModel requestModel;
/*     */     private List<RootListDaoBean> rootListBeans;
/* 423 */     private List<MaterialDaoBean> materialDaoBeans = new ArrayList<>();
/*     */     
/*     */     private Iterator<MaterialDaoBean> iterator;
/*     */     private boolean alreadyRequest = false;
/*     */     private boolean errorState = false;
/*     */     private DownloadCallback callback;
/*     */     private boolean isRun = true;
/* 430 */     private int timeOutCount = 0;
/* 431 */     private int downloadFileCount = 0;
/*     */ 
/*     */     
/*     */     private DownloadMaterialRunnable(ConnectRequestModel requestModel, List<RootListDaoBean> listBeans, DownloadCallback callback) {
/* 435 */       this.requestModel = requestModel;
/* 436 */       this.rootListBeans = listBeans;
/* 437 */       this.callback = callback;
/*     */       
/* 439 */       if (listBeans != null && listBeans.size() > 0) {
/* 440 */         for (RootListDaoBean rootListDaoBean : listBeans) {
/* 441 */           for (MainProgramListDaoBean mainProgramListDaoBean : rootListDaoBean.getMainProgramList()) {
/* 442 */             for (PlayAreaListDaoBean areaListBean : mainProgramListDaoBean.getPlayarea_list()) {
/* 443 */               for (ImagePlayItemListDaoBean imagePlayItemListDaoBean : areaListBean.getImagePlayItemList()) {
/*     */                 
/* 445 */                 MaterialDaoBean materialDaoBean = new MaterialDaoBean();
/* 446 */                 materialDaoBean.setItem_id(imagePlayItemListDaoBean.getItem_id());
/* 447 */                 materialDaoBean.setPlay_item_type("1");
/* 448 */                 materialDaoBean.setFile_name(imagePlayItemListDaoBean.getFile_name());
/* 449 */                 materialDaoBean.setFileMd5(imagePlayItemListDaoBean.getFileMd5());
/* 450 */                 materialDaoBean.setDownloadSuceess(false);
/* 451 */                 materialDaoBean.setIsDownLoad(false);
/*     */                 
/* 453 */                 this.materialDaoBeans.add(materialDaoBean);
/*     */               } 
/*     */               
/* 456 */               for (VideoPlayItemListDaoBean videoPlayItemListDaoBean : areaListBean.getVideoPlayItemList()) {
/*     */                 
/* 458 */                 MaterialDaoBean materialDaoBean = new MaterialDaoBean();
/* 459 */                 materialDaoBean.setItem_id(videoPlayItemListDaoBean.getItem_id());
/* 460 */                 materialDaoBean.setPlay_item_type("2");
/* 461 */                 materialDaoBean.setFile_name(videoPlayItemListDaoBean.getFile_name());
/* 462 */                 materialDaoBean.setFileMd5(videoPlayItemListDaoBean.getFileMd5());
/* 463 */                 materialDaoBean.setDownloadSuceess(false);
/* 464 */                 materialDaoBean.setIsDownLoad(false);
/*     */                 
/* 466 */                 this.materialDaoBeans.add(materialDaoBean);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 474 */       this.iterator = this.materialDaoBeans.iterator();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 480 */       while (this.isRun) {
/* 481 */         if (this.iterator.hasNext() && !this.errorState) {
/* 482 */           if (!this.alreadyRequest) {
/* 483 */             this.alreadyRequest = true;
/* 484 */             final MaterialDaoBean material = this.iterator.next();
/*     */             
/* 486 */             this.requestModel.deviceCheckMaterialIsExist(material.getFile_name(), material.getPlay_item_type(), new StringCallback()
/*     */                 {
/*     */                   public void onError(Call call, Exception e, int i) {
/* 489 */                     DownloadMaterialRunnable.this.errorState = true;
/* 490 */                     DownloadMaterialRunnable.this.callback.onDownloadError("MATERIAL_CHECK_ERROR");
/*     */                   }
/*     */                   
/*     */                   public void onResponse(String s, int i)
/*     */                   {
/* 495 */                     Logger.e("电脑端检查要下载的文件    " + s + material.getFile_name(), new Object[0]);
/* 496 */                     if (!s.isEmpty())
/*     */                       try {
/* 498 */                         JSONObject jsonObject = new JSONObject(s);
/* 499 */                         if (jsonObject.getBoolean("isSuccess")) {
/*     */                           
/* 501 */                           DownloadMaterialRunnable.this.requestModel.deviceDownloadComputerFile(material.getFile_name(), material
/* 502 */                               .getFileMd5(), material.getPlay_item_type(), DownloadMaterialRunnable.this);
/*     */                         } else {
/* 504 */                           DownloadMaterialRunnable.this.errorState = true;
/* 505 */                           DownloadMaterialRunnable.this.callback.onDownloadError("MATERIAL_NOT_EXIST");
/*     */                         } 
/* 507 */                       } catch (JSONException e) {
/* 508 */                         e.printStackTrace();
/* 509 */                         DownloadMaterialRunnable.this.errorState = true;
/* 510 */                         DownloadMaterialRunnable.this.callback.onDownloadError("MATERIAL_CHECK_ERROR");
/*     */                       }   }
/*     */                 });
/*     */           } 
/*     */           continue;
/*     */         } 
/* 516 */         if (this.errorState)
/*     */           break; 
/* 518 */         if (!this.iterator.hasNext())
/*     */           try {
/* 520 */             if (this.downloadFileCount >= this.materialDaoBeans.size()) {
/* 521 */               this.callback.onAllDownloadSuccess(this.rootListBeans); break;
/*     */             } 
/* 523 */             if (++this.timeOutCount >= 1000) {
/* 524 */               this.callback.onDownloadError("MATERIAL_DOWNLOAD_TIMEOUT");
/*     */               break;
/*     */             } 
/* 527 */             Thread.sleep(1000L);
/* 528 */           } catch (InterruptedException e) {
/* 529 */             e.printStackTrace();
/*     */           }  
/*     */       } 
/*     */     }
/*     */     
/*     */     public static interface DownloadCallback
/*     */     {
/*     */       void onAllDownloadSuccess(List<RootListDaoBean> param2List);
/*     */       
/*     */       void onDownloadError(String param2String);
/*     */     }
/*     */     
/*     */     public void downloadFailure(String fileName) {
/* 542 */       this.errorState = true;
/* 543 */       this.alreadyRequest = false;
/* 544 */       this.isRun = false;
/* 545 */       this.callback.onDownloadError("MATERIAL_DOWNLOAD_ERROR");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void downloadError(String fileName) {
/* 555 */       this.errorState = true;
/* 556 */       this.alreadyRequest = false;
/* 557 */       this.isRun = false;
/* 558 */       this.callback.onDownloadError("MATERIAL_DOWNLOAD_ERROR");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void downloading(String progress) {
/* 566 */       this.errorState = false;
/* 567 */       this.alreadyRequest = true;
/* 568 */       Logger.i(progress, new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void downloadSuccess(String name) {
/* 578 */       this.errorState = false;
/* 579 */       this.alreadyRequest = false;
/* 580 */       this.downloadFileCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\presenter\ConnectOptimizationPresenter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */