/*     */ package com.using.adlibrary.model;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.bean.data.IssuedRecord;
/*     */ import com.using.adlibrary.bean.data.LogoMapping;
/*     */ import com.using.adlibrary.bean.data.RootBean;
/*     */ import com.using.adlibrary.bean.data.UpdateBean;
/*     */ import com.using.adlibrary.callback.IComputerMaterialDownloadCallback;
/*     */ import com.using.adlibrary.callback.IComputerNetCallback;
/*     */ import com.using.adlibrary.callback.IComputerStringCallback;
/*     */ import com.using.adlibrary.callback.IDownloadCallback;
/*     */ import com.using.adlibrary.callback.IHeartBeatCallback;
/*     */ import com.using.adlibrary.callback.IModelCallback;
/*     */ import com.using.adlibrary.callback.INetCallback;
/*     */ import com.using.adlibrary.callback.IPhoneMaterialDownloadCallback;
/*     */ import com.using.adlibrary.callback.IPhoneNetCallback;
/*     */ import com.using.adlibrary.callback.IPhoneStringCallback;
/*     */ import com.using.adlibrary.callback.IPresenter;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.service.OSSDownloadUrlManger;
/*     */ import com.using.adlibrary.utils.FileUtils;
/*     */ import com.zhy.http.okhttp.OkHttpUtils;
/*     */ import com.zhy.http.okhttp.builder.GetBuilder;
/*     */ import com.zhy.http.okhttp.builder.PostFormBuilder;
/*     */ import com.zhy.http.okhttp.builder.PostStringBuilder;
/*     */ import com.zhy.http.okhttp.callback.Callback;
/*     */ import com.zhy.http.okhttp.callback.FileCallBack;
/*     */ import com.zhy.http.okhttp.callback.StringCallback;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Type;
/*     */ import okhttp3.Call;
/*     */ import okhttp3.MediaType;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.xutils.common.Callback;
/*     */ import org.xutils.http.RequestParams;
/*     */ import org.xutils.x;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConnectRequestModel
/*     */ {
/*  46 */   private String TAG = "TAG";
/*  47 */   private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
/*     */ 
/*     */   
/*     */   private Gson gson;
/*     */ 
/*     */   
/*     */   private MediaType mediaJson;
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceRegister(String deviceInfoData, final INetCallback callback) {
/*     */     try {
/*  59 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/register";
/*  60 */       Logger.e("设备注册的url： " + url + "  ----------  " + deviceInfoData, new Object[0]);
/*  61 */       ((PostStringBuilder)OkHttpUtils.postString()
/*  62 */         .url(url))
/*  63 */         .mediaType(this.JSON)
/*  64 */         .content(deviceInfoData)
/*  65 */         .build()
/*  66 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/*  69 */               e.printStackTrace();
/*  70 */               Logger.e("注册请求出现错误", new Object[0]);
/*  71 */               callback.deviceNetException("注册错误");
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/*  76 */               Logger.e("注册返还的信息为： " + s, new Object[0]);
/*  77 */               callback.deviceRegisterCallback(s);
/*     */             }
/*     */           });
/*  80 */     } catch (Exception e) {
/*  81 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceHeartBeat(String uuid, final IHeartBeatCallback callback) {
/*     */     try {
/*  90 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/heartBeat";
/*  91 */       Logger.e("设备心跳的url： " + url, new Object[0]);
/*     */       
/*  93 */       ((PostFormBuilder)OkHttpUtils.post()
/*  94 */         .addParams("uuid", uuid)
/*  95 */         .url(url))
/*  96 */         .build()
/*  97 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 100 */               e.printStackTrace();
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 105 */               Logger.e("设备心跳得到的响应字符串为： " + s, new Object[0]);
/*     */               try {
/* 107 */                 JSONObject jsonObject = new JSONObject(s);
/* 108 */                 if (jsonObject.getInt("code") == 200) {
/* 109 */                   JSONObject data = jsonObject.getJSONObject("data");
/*     */                   
/* 111 */                   JSONArray cmdList = data.getJSONArray("cmdList");
/* 112 */                   for (int n = 0; n < cmdList.length(); n++) {
/* 113 */                     JSONObject terminal = cmdList.getJSONObject(n);
/* 114 */                     callback.deviceCmdCallback(terminal.getInt("commandType"), 100);
/*     */                   } 
/*     */                   
/* 117 */                   String subtitleList = data.getString("subtitleList");
/* 118 */                   callback.deviceRequestTitleListCallback(subtitleList);
/*     */ 
/*     */                   
/* 121 */                   int isScreen = data.getInt("isScreen");
/* 122 */                   if (2 == isScreen)
/*     */                   {
/* 124 */                     callback.deviceCmdCallback(99, 100);
/*     */                   }
/*     */                   
/* 127 */                   JSONObject changeVolumeInfo = data.getJSONObject("changeVolumeInfo");
/* 128 */                   int isChange = changeVolumeInfo.getInt("isChange");
/*     */ 
/*     */                   
/* 131 */                   if (isChange == 1) {
/* 132 */                     callback.deviceCmdCallback(111, changeVolumeInfo.getInt("volumeValue"));
/*     */                   }
/*     */                 } 
/* 135 */               } catch (JSONException e) {
/* 136 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */           });
/* 140 */     } catch (Exception e) {
/* 141 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceLogout(String uuid) {
/*     */     try {
/* 150 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/logOut";
/* 151 */       Logger.e("登出的url的地址为： " + url, new Object[0]);
/*     */       
/* 153 */       ((GetBuilder)OkHttpUtils.get()
/* 154 */         .url(url))
/* 155 */         .addParams("uuid", uuid)
/* 156 */         .build()
/* 157 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 160 */               Logger.e("登出请求出现错误", new Object[0]);
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 165 */               Logger.e("登出返回的信息" + s, new Object[0]);
/*     */             }
/*     */           });
/*     */     }
/* 169 */     catch (Exception e) {
/* 170 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceHeartBeatV2(String uuid, String playListId, final IComputerNetCallback callback) {
/*     */     try {
/* 179 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/heartBeatV2";
/* 180 */       Logger.e("deviceHeartBeatV2  传递的playListId    逗号分隔的:      " + playListId, new Object[0]);
/* 181 */       Logger.e("请求节目单地址： " + url, new Object[0]);
/* 182 */       ((GetBuilder)OkHttpUtils.get()
/* 183 */         .url(url))
/* 184 */         .addParams("uuid", uuid)
/* 185 */         .addParams("playListId", playListId)
/* 186 */         .build()
/* 187 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 190 */               callback.onError("获取当前设备相应的播放列表错误");
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 196 */               Logger.e("电脑端节目单列表心跳数据:" + s, new Object[0]);
/*     */               try {
/* 198 */                 JSONObject jsonObject = new JSONObject(s);
/* 199 */                 if (jsonObject.getInt("code") == 200) {
/*     */                   
/* 201 */                   JSONObject data = jsonObject.getJSONObject("data");
/* 202 */                   if (data != null) {
/* 203 */                     String jsonPlayListArray = data.getString("jsonPlayListArray");
/* 204 */                     if (jsonPlayListArray != null && !jsonPlayListArray.isEmpty()) {
/* 205 */                       callback.onSuccess(jsonPlayListArray);
/*     */                     } else {
/* 207 */                       callback.onSuccess("");
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 211 */               } catch (JSONException e) {
/* 212 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */           });
/* 216 */     } catch (Exception e) {
/* 217 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceRequestList(String uuid, final long playListId, final IComputerStringCallback callback) {
/*     */     try {
/* 227 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/getPlaylist";
/* 228 */       Logger.e("请求节目单地址： " + url, new Object[0]);
/* 229 */       ((GetBuilder)OkHttpUtils.get()
/* 230 */         .url(url))
/* 231 */         .addParams("uuid", uuid)
/* 232 */         .addParams("playlistId", String.valueOf(playListId))
/* 233 */         .build()
/* 234 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 237 */               e.printStackTrace();
/* 238 */               callback.onError("getPlaylist  获取当前设备相应的播放列表错误");
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/*     */               try {
/* 244 */                 JSONObject jsonObject = new JSONObject(s);
/* 245 */                 if (jsonObject.getInt("code") == 200 && 
/* 246 */                   jsonObject.getString("data") != null) {
/* 247 */                   callback.onSuccess(String.valueOf(playListId), jsonObject.getString("data"));
/*     */                 }
/*     */               }
/* 250 */               catch (JSONException e) {
/* 251 */                 e.printStackTrace();
/*     */               } 
/*     */             }
/*     */           });
/* 255 */     } catch (Exception e) {
/* 256 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceRequestTitle(String param, final IModelCallback<String> callback) {
/*     */     try {
/* 266 */       String titleUrl = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/downloadTitle";
/* 267 */       ((GetBuilder)OkHttpUtils.get()
/* 268 */         .addParams("ids", param)
/* 269 */         .url(titleUrl))
/* 270 */         .build()
/* 271 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 274 */               callback.onError("请求字幕错误");
/* 275 */               e.printStackTrace();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 281 */               Logger.e("返回的字幕数据字符串为： " + s, new Object[0]);
/*     */               try {
/* 283 */                 JSONObject jsonObject = new JSONObject(s);
/* 284 */                 if (200 == jsonObject.getInt("code")) {
/* 285 */                   String data = jsonObject.getString("data");
/* 286 */                   callback.onSuccess(data);
/*     */                 }
/*     */               
/* 289 */               } catch (Exception e) {
/* 290 */                 e.printStackTrace();
/* 291 */                 callback.onFailure("字幕数据解析异常！");
/*     */               } 
/*     */             }
/*     */           });
/* 295 */     } catch (Exception e) {
/* 296 */       callback.onError("字幕数据请求异常！");
/* 297 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deviceUploadScreenFile(String uuid, File file) {
/* 306 */     String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/generateImage";
/*     */     try {
/* 308 */       ((PostFormBuilder)OkHttpUtils.post()
/* 309 */         .url(url))
/* 310 */         .addParams("uuid", uuid)
/* 311 */         .addFile("file", uuid + ".png", file)
/* 312 */         .build()
/* 313 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 316 */               e.printStackTrace();
/* 317 */               Logger.e("传输错误", new Object[0]);
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 322 */               Logger.e("传输返回 ： " + s, new Object[0]);
/*     */             }
/*     */           });
/* 325 */     } catch (Exception e) {
/* 326 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceProgramState(String uuid, long programId, Integer hashCode, String describe) {
/*     */     try {
/* 336 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/programState";
/* 337 */       Logger.e("执行上报节目单信息请求地址: " + url + "上报的节目单id为：  " + programId, new Object[0]);
/*     */       
/* 339 */       IssuedRecord issuedRecord = new IssuedRecord();
/* 340 */       issuedRecord.setDeviceId(uuid);
/* 341 */       issuedRecord.setDescribeNote(describe);
/* 342 */       issuedRecord.setProgramId(programId);
/* 343 */       issuedRecord.setHashCode(hashCode);
/*     */       
/* 345 */       ((PostStringBuilder)OkHttpUtils.postString()
/* 346 */         .url(url))
/* 347 */         .mediaType(this.JSON)
/* 348 */         .content(this.gson.toJson(issuedRecord))
/* 349 */         .build()
/* 350 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 353 */               Logger.e("节目单回调失败: ", new Object[0]);
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 358 */               Logger.e("节目单回调成功 : " + s, new Object[0]);
/*     */             }
/*     */           });
/* 361 */     } catch (Exception e) {
/* 362 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceRequestLogo(String uuid, final IModelCallback<LogoMapping> callback) {
/* 371 */     String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/getLogoFileForDevice";
/*     */     try {
/* 373 */       ((PostFormBuilder)OkHttpUtils.post()
/* 374 */         .url(url))
/* 375 */         .addParams("uuid", uuid)
/* 376 */         .build()
/* 377 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 380 */               Logger.e("getLogoFileForDevice ：错误 ", new Object[0]);
/* 381 */               e.printStackTrace();
/* 382 */               callback.onError("getLogoFileForDevice ：错误");
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 387 */               Logger.e("getLogoFileForDevice ： 成功" + s, new Object[0]);
/*     */               
/* 389 */               Type type = (new TypeToken<RootBean<LogoMapping>>() {  }).getType();
/* 390 */               if (s != null) {
/* 391 */                 RootBean<LogoMapping> rootBean = (RootBean<LogoMapping>)ConnectRequestModel.this.gson.fromJson(s, type);
/* 392 */                 if ("200".equals(rootBean.getCode())) {
/* 393 */                   LogoMapping logoMapping = (LogoMapping)rootBean.getData();
/*     */                   
/* 395 */                   callback.onSuccess(logoMapping);
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           });
/* 400 */     } catch (Exception e) {
/* 401 */       callback.onError("请求logo异常！");
/* 402 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceDownloadLogoFile(String logoFile, final IPresenter iPresenter) {
/*     */     try {
/* 412 */       if (!FileUtils.fileExists(ConfigManger.getInstance().getLogoPath() + logoFile)) {
/* 413 */         String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/adcloud/resourcefile/logofile_get/" + logoFile;
/* 414 */         ((GetBuilder)OkHttpUtils.get()
/* 415 */           .url(url))
/* 416 */           .build()
/* 417 */           .execute((Callback)new FileCallBack(ConfigManger.getInstance().getLogoPath(), logoFile)
/*     */             {
/*     */               public void onError(Call call, Exception e, int id)
/*     */               {
/* 421 */                 Logger.e("Logo下载onError :" + e.getMessage(), new Object[0]);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void inProgress(float progress, long total, int id) {
/* 427 */                 Logger.e("logo下载进度：inProgress" + (int)(100.0F * progress), new Object[0]);
/*     */               }
/*     */ 
/*     */               
/*     */               public void onResponse(File file, int id) {
/* 432 */                 Logger.e("logo文件下载完成！路径为 :" + file.getAbsolutePath(), new Object[0]);
/* 433 */                 iPresenter.logoShow(file.getAbsolutePath());
/*     */               }
/*     */             });
/*     */       } else {
/* 437 */         iPresenter.logoShow(ConfigManger.getInstance().getLogoPath() + logoFile);
/*     */       } 
/* 439 */     } catch (Exception e) {
/* 440 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceApkVersionDetection(final String apkType, String deviceId, final IModelCallback<UpdateBean> callback) {
/*     */     try {
/* 450 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/apk/getNewApkVersion";
/* 451 */       Logger.e("apk 版本检测接口  " + url, new Object[0]);
/*     */       
/* 453 */       ((PostFormBuilder)OkHttpUtils.post()
/* 454 */         .url(url))
/* 455 */         .addParams("apkType", apkType)
/* 456 */         .addParams("deviceId", deviceId)
/* 457 */         .build()
/* 458 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 461 */               Logger.e("apk版本检测 onError :" + e.getMessage(), new Object[0]);
/* 462 */               callback.onError("error");
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 467 */               Logger.e("apk版本检测 onResponse" + s, new Object[0]);
/*     */               
/* 469 */               Type type = (new TypeToken<UpdateBean>() {  }).getType();
/* 470 */               if (s != null) {
/* 471 */                 UpdateBean updateBean = (UpdateBean)ConnectRequestModel.this.gson.fromJson(s, type);
/* 472 */                 updateBean.setApkType(apkType);
/* 473 */                 callback.onSuccess(updateBean);
/*     */               } 
/*     */             }
/*     */           });
/* 477 */     } catch (Exception e) {
/* 478 */       callback.onError("error");
/* 479 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceDownloadApkFile(String fileName, final String md5, final IDownloadCallback<File> callback) {
/*     */     try {
/* 490 */       String url = OSSDownloadUrlManger.getInstance().getApkFileDownloadUrl(fileName);
/* 491 */       Logger.e("apk的下载地址" + url, new Object[0]);
/*     */       
/* 493 */       ((GetBuilder)OkHttpUtils.get()
/* 494 */         .url(url))
/* 495 */         .build()
/* 496 */         .execute((Callback)new FileCallBack(ConfigManger.getInstance().getApkPath(), fileName)
/*     */           {
/*     */             public void onError(Call call, Exception e, int id)
/*     */             {
/* 500 */               Logger.e("apk下载onError :" + e.getMessage(), new Object[0]);
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void inProgress(float progress, long total, int id) {
/* 507 */               callback.onCommence("" + (100.0F * progress));
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(File file, int id) {
/* 512 */               Logger.e("apk onResponse :" + file.getAbsolutePath(), new Object[0]);
/* 513 */               String dmd5 = FileUtils.getFileMD5(file);
/* 514 */               if (dmd5 != null) {
/* 515 */                 if (dmd5.equals(md5)) {
/* 516 */                   callback.onDownSuccess(file);
/*     */                 } else {
/* 518 */                   file.delete();
/*     */                 }
/*     */               
/*     */               }
/*     */             }
/*     */           });
/* 524 */     } catch (Exception e) {
/* 525 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceCheckMaterialIsExist(String fileName, String downloadType, StringCallback callback) {
/*     */     try {
/* 535 */       String objectKey, url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/material/materialIsExist";
/*     */ 
/*     */ 
/*     */       
/* 539 */       if ("1".equals(downloadType)) {
/* 540 */         objectKey = ConfigManger.getInstance().getOssObjectKeyComputerImage() + "/" + fileName;
/* 541 */       } else if ("2".equals(downloadType)) {
/* 542 */         objectKey = ConfigManger.getInstance().getOssObjectKeyComputerVideo() + "/" + fileName;
/*     */       } else {
/* 544 */         objectKey = null;
/*     */       } 
/*     */       
/* 547 */       ((PostFormBuilder)OkHttpUtils.post()
/* 548 */         .url(url))
/* 549 */         .addParams("path", objectKey)
/* 550 */         .addParams("downloadType", downloadType)
/* 551 */         .build()
/* 552 */         .execute((Callback)callback);
/* 553 */     } catch (Exception e) {
/* 554 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceDownloadComputerFile(final String fileName, final String md5, String downloadType, final IComputerMaterialDownloadCallback callback) {
/*     */     try {
/* 566 */       String url = OSSDownloadUrlManger.getInstance().getFileDownloadUrl(fileName, downloadType);
/* 567 */       Logger.e(url, new Object[0]);
/* 568 */       RequestParams params = new RequestParams(url);
/* 569 */       params.setAutoResume(true);
/* 570 */       params.setAutoRename(false);
/*     */       
/* 572 */       if (FileUtils.fileExists(ConfigManger.getInstance().getMaterialPath() + "/" + fileName) && md5
/* 573 */         .equals(FileUtils.getFileMD5(new File(ConfigManger.getInstance().getMaterialPath() + "/" + fileName)))) {
/* 574 */         callback.downloadSuccess(fileName);
/*     */       } else {
/* 576 */         params.setSaveFilePath(ConfigManger.getInstance().getMaterialPath() + "/" + fileName);
/* 577 */         params.setCancelFast(true);
/* 578 */         x.http().get(params, (Callback.CommonCallback)new Callback.ProgressCallback<File>()
/*     */             {
/*     */               public void onWaiting() {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onStarted() {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onLoading(long total, long current, boolean isDownloading) {
/* 591 */                 Logger.e("onLoading。。。。。总数：" + total + "  进度：" + (current * 100L / total), new Object[0]);
/* 592 */                 callback.downloading(fileName + "下载进度： " + (current * 100L / total));
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void onSuccess(File result) {
/* 598 */                 Logger.e("downloadMaterial onResponse :" + result.getAbsolutePath(), new Object[0]);
/* 599 */                 Logger.e("得到的素材文件大小：" + result.length(), new Object[0]);
/* 600 */                 Logger.e("文件MD5：  " + FileUtils.getFileMD5(result), new Object[0]);
/*     */                 
/* 602 */                 if (result.length() > 0L && md5.equals(FileUtils.getFileMD5(result))) {
/* 603 */                   callback.downloadSuccess(fileName);
/*     */                 } else {
/* 605 */                   FileUtils.deleteFile(result.getAbsolutePath());
/* 606 */                   callback.downloadFailure(fileName);
/*     */                 } 
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void onError(Throwable ex, boolean isOnCallback) {
/* 613 */                 Logger.e("file下载onError :" + fileName, new Object[0]);
/* 614 */                 callback.downloadFailure(fileName);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onCancelled(Callback.CancelledException cex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onFinished() {}
/*     */             });
/*     */       } 
/* 628 */     } catch (Exception e) {
/* 629 */       callback.downloadFailure(fileName);
/* 630 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectRequestModel() {
/* 637 */     this.mediaJson = MediaType.parse("application/json; charset=utf-8");
/*     */     this.gson = new Gson();
/*     */   } public void devicePushMessage(String uuid, String message, String type) {
/*     */     try {
/* 641 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/pushMessage";
/*     */       
/* 643 */       JSONObject msg = new JSONObject();
/*     */       try {
/* 645 */         msg.put("message", message);
/* 646 */         msg.put("type", type);
/* 647 */         msg.put("deviceUuid", uuid);
/* 648 */         Logger.e("devicePushMessage的数据：" + msg.toString(), new Object[0]);
/* 649 */       } catch (JSONException e) {
/* 650 */         e.printStackTrace();
/*     */       } 
/*     */       
/* 653 */       ((PostStringBuilder)OkHttpUtils.postString()
/* 654 */         .url(url))
/* 655 */         .mediaType(this.mediaJson)
/* 656 */         .content(msg.toString())
/* 657 */         .build()
/* 658 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 661 */               e.printStackTrace();
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/* 666 */               Logger.e("终端推送消息接口 pushMessage " + s, new Object[0]);
/*     */             }
/*     */           });
/* 669 */     } catch (Exception e) {
/* 670 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceHeartBeatPhone(String uuid, String h5PlayListId, final IPhoneNetCallback callback) {
/*     */     try {
/* 679 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/heartBeatV3";
/* 680 */       Logger.e("deviceHeartBeatV3  传递的h5PlayListId  逗号分隔的:      " + h5PlayListId, new Object[0]);
/* 681 */       Logger.e("deviceHeartBeatPhone 请求节目单地址： " + url, new Object[0]);
/* 682 */       ((GetBuilder)OkHttpUtils.get()
/* 683 */         .url(url))
/* 684 */         .addParams("uuid", uuid)
/* 685 */         .addParams("propertyPlayListId", h5PlayListId)
/* 686 */         .build()
/* 687 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 690 */               callback.deviceNetError("Phone获取当前设备相应的播放列表错误");
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/*     */               try {
/* 696 */                 Logger.e("物业列表数据：" + s, new Object[0]);
/* 697 */                 JSONObject jsonObject = new JSONObject(s);
/* 698 */                 if (jsonObject.getInt("code") == 200) {
/*     */                   
/* 700 */                   JSONObject data = jsonObject.getJSONObject("data");
/* 701 */                   if (data != null) {
/* 702 */                     String jsonPlayListArray = data.getString("jsonPlayListArray");
/* 703 */                     if (jsonPlayListArray != null && !jsonPlayListArray.isEmpty()) {
/* 704 */                       callback.deviceHeartBeatCallback(jsonPlayListArray);
/*     */                     }
/*     */                   } 
/* 707 */                 } else if (jsonObject.getInt("code") == 202) {
/*     */                   
/* 709 */                   callback.deviceHeartBeatCallback("[]");
/*     */                 } 
/* 711 */               } catch (JSONException e) {
/* 712 */                 e.printStackTrace();
/* 713 */                 callback.deviceNetError("error");
/*     */               } 
/*     */             }
/*     */           });
/* 717 */     } catch (Exception e) {
/* 718 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceGetPlaylistForH5(String uuid, final String h5PlayListId, final IPhoneStringCallback callback) {
/*     */     try {
/* 728 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/api/v1/getPlaylistForProperty";
/* 729 */       Logger.e("请求节目单地址： " + url, new Object[0]);
/* 730 */       ((GetBuilder)OkHttpUtils.get()
/* 731 */         .url(url))
/* 732 */         .addParams("uuid", uuid)
/* 733 */         .addParams("propertyPlayListId", h5PlayListId)
/* 734 */         .build()
/* 735 */         .execute((Callback)new StringCallback()
/*     */           {
/*     */             public void onError(Call call, Exception e, int i) {
/* 738 */               e.printStackTrace();
/* 739 */               callback.onError(h5PlayListId);
/*     */             }
/*     */ 
/*     */             
/*     */             public void onResponse(String s, int i) {
/*     */               try {
/* 745 */                 JSONObject jsonObject = new JSONObject(s);
/* 746 */                 if (jsonObject.getInt("code") == 200) {
/* 747 */                   if (jsonObject.getString("data") != null) {
/* 748 */                     callback.onSuccess(h5PlayListId, jsonObject.getString("data"));
/*     */                   } else {
/* 750 */                     callback.onError(h5PlayListId);
/*     */                   } 
/*     */                 }
/* 753 */               } catch (JSONException e) {
/* 754 */                 e.printStackTrace();
/* 755 */                 callback.onError(h5PlayListId);
/*     */               } 
/*     */             }
/*     */           });
/* 759 */     } catch (Exception e) {
/* 760 */       callback.onError(h5PlayListId);
/* 761 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deviceCheckH5MaterialIsExist(String fileName, String downloadType, StringCallback callback) {
/*     */     try {
/* 768 */       String objectKey, url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/property/resource/resourceIsExist";
/*     */ 
/*     */ 
/*     */       
/* 772 */       if ("1".equals(downloadType)) {
/* 773 */         objectKey = ConfigManger.getInstance().getOssObjectKeyPropertyImage() + "/" + fileName;
/* 774 */       } else if ("2".equals(downloadType)) {
/* 775 */         objectKey = ConfigManger.getInstance().getOssObjectKeyPropertyVideo() + "/" + fileName;
/*     */       } else {
/* 777 */         objectKey = null;
/*     */       } 
/*     */       
/* 780 */       ((PostFormBuilder)OkHttpUtils.post()
/* 781 */         .url(url))
/* 782 */         .addParams("fileName", objectKey)
/* 783 */         .addParams("downloadType", downloadType)
/* 784 */         .build()
/* 785 */         .execute((Callback)callback);
/* 786 */     } catch (Exception e) {
/* 787 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deviceDownloadFileH5(final String fileName, final String md5, String downloadType, final IPhoneMaterialDownloadCallback callback) {
/*     */     try {
/* 798 */       String url = OSSDownloadUrlManger.getInstance().getPhoneFileDownloadUrl(fileName, downloadType);
/* 799 */       Logger.e(url, new Object[0]);
/* 800 */       RequestParams params = new RequestParams(url);
/* 801 */       params.setAutoResume(true);
/* 802 */       params.setAutoRename(false);
/*     */       
/* 804 */       if (FileUtils.fileExists(ConfigManger.getInstance().getMaterialPath() + "/" + fileName) && md5
/* 805 */         .equals(FileUtils.getFileMD5(new File(ConfigManger.getInstance().getMaterialPath() + "/" + fileName)))) {
/* 806 */         callback.downloadSuccess(fileName);
/*     */       } else {
/* 808 */         params.setSaveFilePath(ConfigManger.getInstance().getMaterialPath() + "/" + fileName);
/* 809 */         params.setCancelFast(true);
/* 810 */         x.http().get(params, (Callback.CommonCallback)new Callback.ProgressCallback<File>()
/*     */             {
/*     */               public void onWaiting() {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onStarted() {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onLoading(long total, long current, boolean isDownloading) {
/* 823 */                 Logger.e("onLoading。。。。。总数：" + total + "  进度：" + (current * 100L / total), new Object[0]);
/* 824 */                 callback.downloading(fileName + "下载进度： " + (current * 100L / total));
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void onSuccess(File result) {
/* 830 */                 Logger.e("downloadMaterial onResponse :" + result.getAbsolutePath(), new Object[0]);
/* 831 */                 Logger.e("得到的素材文件大小：" + result.length(), new Object[0]);
/* 832 */                 Logger.e("文件MD5：  " + FileUtils.getFileMD5(result), new Object[0]);
/*     */                 
/* 834 */                 if (result.length() > 0L && md5.equals(FileUtils.getFileMD5(result))) {
/* 835 */                   callback.downloadSuccess(fileName);
/*     */                 } else {
/* 837 */                   FileUtils.deleteFile(result.getAbsolutePath());
/* 838 */                   callback.downloadFailure(fileName);
/*     */                 } 
/*     */               }
/*     */ 
/*     */ 
/*     */               
/*     */               public void onError(Throwable ex, boolean isOnCallback) {
/* 845 */                 Logger.e("file下载onError :", new Object[0]);
/* 846 */                 callback.downloadFailure(fileName);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onCancelled(Callback.CancelledException cex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               public void onFinished() {}
/*     */             });
/*     */       } 
/* 860 */     } catch (Exception e) {
/* 861 */       callback.downloadFailure(fileName);
/* 862 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void devicePushDevicePlayListNum(String deviceId, long playListId, String sendDate, int num, StringCallback callback) {
/*     */     try {
/* 869 */       String url = ConfigManger.getInstance().getBaseUrl() + "/AdCloud/a/adcloud/playListNum/pushDevicePlayListNum";
/*     */       
/* 871 */       Logger.e("devicePushDevicePlayListNum ----------地址  ： " + url, new Object[0]);
/*     */ 
/*     */       
/* 874 */       ((PostFormBuilder)OkHttpUtils.post()
/* 875 */         .url(url))
/* 876 */         .addParams("deviceId", deviceId)
/* 877 */         .addParams("playListId", String.valueOf(playListId))
/* 878 */         .addParams("sendDate", sendDate)
/* 879 */         .addParams("num", String.valueOf(num))
/* 880 */         .build()
/* 881 */         .execute((Callback)callback);
/* 882 */     } catch (Exception e) {
/* 883 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\model\ConnectRequestModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */