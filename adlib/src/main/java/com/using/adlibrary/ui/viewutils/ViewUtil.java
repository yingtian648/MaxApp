/*     */ package com.using.adlibrary.ui.viewutils;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ImageView;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.bean.dao.ImagePlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.ImmediateProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MainProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayAreaListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.TimingProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.VideoPlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneImagePlayItemListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneMainProgramListBean;
/*     */ import com.using.adlibrary.bean.phone.PhonePlayAreaListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneVideoPlayItemListBean;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.config.Constant;
/*     */ import com.using.adlibrary.ui.fragment.ArbitrarilyFragment;
/*     */ import com.using.adlibrary.ui.view.CustomImageView;
/*     */ import com.using.adlibrary.ui.view.CustomVideoView;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class ViewUtil
/*     */ {
/*     */   public static List<ArbitrarilyFragment> getTimesFragments(List<TimingProgramListDaoBean> times, Context context) {
/*  40 */     Logger.e("定时节目单主任务包的个数为： " + times.size(), new Object[0]);
/*  41 */     List<ArbitrarilyFragment> listFragment = new ArrayList<>();
/*     */ 
/*     */     
/*  44 */     for (TimingProgramListDaoBean m : times) {
/*     */       
/*  46 */       List<PlayAreaListDaoBean> playarea_lists = m.getPlayarea_list();
/*  47 */       Logger.e("定时节目单区域列表个数 Playarea_list:  " + playarea_lists.size(), new Object[0]);
/*     */       
/*  49 */       List<View> listView = new ArrayList<>();
/*  50 */       for (PlayAreaListDaoBean p : playarea_lists) {
/*  51 */         String areaType = p.getArea_type();
/*  52 */         Logger.e("定时节目单Playarea_list 区域类型为：" + areaType + "    id为   " + p.getArea_id(), new Object[0]);
/*  53 */         View view = getView(p, context);
/*  54 */         if (view == null) {
/*  55 */           return null;
/*     */         }
/*  57 */         listView.add(view);
/*     */       } 
/*     */       
/*  60 */       ArbitrarilyFragment fragment = new ArbitrarilyFragment();
/*  61 */       fragment.addListView(listView);
/*     */ 
/*     */       
/*  64 */       fragment.setpId(m.getPlay_list_id());
/*  65 */       fragment.setType(1);
/*     */       
/*  67 */       listFragment.add(fragment);
/*     */     } 
/*  69 */     return listFragment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<ArbitrarilyFragment> getMainFragments(List<MainProgramListDaoBean> mainProgramLists, Context context) {
/*  76 */     Logger.e("定时节目单主任务包的个数为： " + mainProgramLists.size(), new Object[0]);
/*  77 */     List<ArbitrarilyFragment> listFragment = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*  81 */     for (MainProgramListDaoBean m : mainProgramLists) {
/*     */       
/*  83 */       List<PlayAreaListDaoBean> playarea_lists = m.getPlayarea_list();
/*  84 */       Logger.e("定时节目单区域列表个数 Playarea_list:  " + playarea_lists.size(), new Object[0]);
/*     */       
/*  86 */       List<View> listView = new ArrayList<>();
/*  87 */       for (PlayAreaListDaoBean p : playarea_lists) {
/*  88 */         String areaType = p.getArea_type();
/*  89 */         Logger.e("定时节目单Playarea_list 区域类型为：" + areaType + "    id为   " + p.getArea_id(), new Object[0]);
/*  90 */         View view = getView(p, context);
/*  91 */         listView.add(view);
/*     */         
/*  93 */         if (view == null) {
/*  94 */           return null;
/*     */         }
/*     */       } 
/*     */       
/*  98 */       ArbitrarilyFragment fragment = new ArbitrarilyFragment();
/*  99 */       fragment.addListView(listView);
/*     */ 
/*     */       
/* 102 */       fragment.setpId(m.getPlay_list_id());
/* 103 */       fragment.setType(0);
/*     */       
/* 105 */       listFragment.add(fragment);
/*     */     } 
/* 107 */     return listFragment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<ArbitrarilyFragment> getImmediatesFragments(List<ImmediateProgramListDaoBean> immediateProgramListDaoBeans, Context context) {
/* 114 */     Logger.e("即时节目单主任务包的个数为： " + immediateProgramListDaoBeans.size(), new Object[0]);
/* 115 */     List<ArbitrarilyFragment> listFragment = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/* 119 */     for (ImmediateProgramListDaoBean m : immediateProgramListDaoBeans) {
/*     */       
/* 121 */       List<PlayAreaListDaoBean> playarea_lists = m.getPlayarea_list();
/* 122 */       Logger.e("即时节目单区域列表个数 Playarea_list:  " + playarea_lists.size(), new Object[0]);
/*     */       
/* 124 */       List<View> listView = new ArrayList<>();
/* 125 */       for (PlayAreaListDaoBean p : playarea_lists) {
/* 126 */         String areaType = p.getArea_type();
/* 127 */         Logger.e("即时节目单Playarea_list 区域类型为：" + areaType + "    id为   " + p.getArea_id(), new Object[0]);
/* 128 */         View view = getView(p, context);
/* 129 */         listView.add(view);
/*     */         
/* 131 */         if (view == null) {
/* 132 */           return null;
/*     */         }
/*     */       } 
/*     */       
/* 136 */       ArbitrarilyFragment fragment = new ArbitrarilyFragment();
/* 137 */       fragment.addListView(listView);
/*     */ 
/*     */       
/* 140 */       fragment.setpId(m.getPlay_list_id());
/* 141 */       fragment.setType(2);
/*     */       
/* 143 */       listFragment.add(fragment);
/*     */     } 
/* 145 */     return listFragment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static View getView(PlayAreaListDaoBean playarea_list_bean, Context context) {
/* 152 */     if (playarea_list_bean != null) {
/* 153 */       if (Constant.areaTypeVideo.equals(playarea_list_bean.getArea_type())) {
/*     */         
/* 155 */         List<VideoPlayItemListDaoBean> videoPlayItemLists = playarea_list_bean.getVideoPlayItemList();
/* 156 */         List<String> videoPathList = new ArrayList<>();
/*     */         
/* 158 */         for (VideoPlayItemListDaoBean v : videoPlayItemLists) {
/* 159 */           Logger.e("视频播放的本地地址为：" + ConfigManger.getInstance().getVideoPath() + v.getFile_name(), new Object[0]);
/* 160 */           videoPathList.add(ConfigManger.getInstance().getVideoPath() + v.getFile_name());
/*     */         } 
/*     */         
/* 163 */         CustomVideoView videoView = new CustomVideoView(context);
/* 164 */         ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(playarea_list_bean.getWidth(), playarea_list_bean.getHeight());
/*     */         
/* 166 */         Logger.e("视频区域id： " + playarea_list_bean.getArea_id(), new Object[0]);
/*     */         
/* 168 */         videoView.setVideoPathList(videoPathList);
/* 169 */         videoView.setId((int)playarea_list_bean.getArea_id());
/* 170 */         videoView.setX(playarea_list_bean.getStartX());
/* 171 */         videoView.setY(playarea_list_bean.getStartY());
/* 172 */         videoView.setLayoutParams(layoutParams);
/*     */         
/* 174 */         return (View)videoView;
/* 175 */       }  if (Constant.areaTypeImage.equals(playarea_list_bean.getArea_type())) {
/*     */         
/* 177 */         List<ImagePlayItemListDaoBean> imagePlayItemLists = playarea_list_bean.getImagePlayItemList();
/* 178 */         List<String> imagePathList = new ArrayList<>();
/* 179 */         int index = 0;
/* 180 */         for (ImagePlayItemListDaoBean v : imagePlayItemLists) {
/* 181 */           Logger.e("图片播放的本地地址为：" + ConfigManger.getInstance().getImagePath() + v.getFile_name(), new Object[0]);
/* 182 */           imagePathList.add(index++, ConfigManger.getInstance().getImagePath() + v.getFile_name());
/*     */         } 
/*     */         
/* 185 */         CustomImageView imageView = new CustomImageView(context);
/* 186 */         ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(playarea_list_bean.getWidth(), playarea_list_bean.getHeight());
/*     */         
/* 188 */         Logger.e("图片区域id： " + playarea_list_bean.getArea_id(), new Object[0]);
/* 189 */         Logger.e("开始getStartX：" + playarea_list_bean.getStartX(), new Object[0]);
/* 190 */         Logger.e("开始getStartY：" + playarea_list_bean.getStartY(), new Object[0]);
/*     */         
/* 192 */         imageView.addImagePathList(imagePathList);
/* 193 */         imageView.setId((int)playarea_list_bean.getArea_id());
/* 194 */         imageView.setX(playarea_list_bean.getStartX());
/* 195 */         imageView.setY(playarea_list_bean.getStartY());
/* 196 */         imageView.setScaleType(ImageView.ScaleType.FIT_XY);
/* 197 */         imageView.setLayoutParams(layoutParams);
/*     */         
/* 199 */         Logger.e("获取每个区域的view    返回image view", new Object[0]);
/* 200 */         return (View)imageView;
/*     */       } 
/*     */     } 
/* 203 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<ArbitrarilyFragment> getPhoneMainFragments(List<PhoneMainProgramListBean> mainProgramLists, Context context) {
/* 209 */     Logger.e("手机端的主节目单主任务包的个数为： " + mainProgramLists.size(), new Object[0]);
/* 210 */     List<ArbitrarilyFragment> listFragment = new ArrayList<>();
/*     */ 
/*     */     
/* 213 */     for (PhoneMainProgramListBean m : mainProgramLists) {
/*     */       
/* 215 */       List<PhonePlayAreaListBean> playarea_lists = m.getPlayarea_list();
/* 216 */       Logger.e("手机端的主节目单区域列表个数 Playarea_list:  " + playarea_lists.size(), new Object[0]);
/*     */       
/* 218 */       List<View> listView = new ArrayList<>();
/* 219 */       for (PhonePlayAreaListBean p : playarea_lists) {
/* 220 */         String areaType = p.getArea_type();
/* 221 */         Logger.e("手机端的主节目单Playarea_list 区域类型为：" + areaType + "    id为   " + p.getArea_id(), new Object[0]);
/* 222 */         View view = getPhoneView(p, context);
/* 223 */         listView.add(view);
/* 224 */         if (view == null) {
/* 225 */           return null;
/*     */         }
/*     */       } 
/* 228 */       ArbitrarilyFragment fragment = new ArbitrarilyFragment();
/* 229 */       fragment.addListView(listView);
/*     */       
/* 231 */       listFragment.add(fragment);
/*     */     } 
/* 233 */     return listFragment;
/*     */   }
/*     */ 
/*     */   
/*     */   private static View getPhoneView(PhonePlayAreaListBean areaListBean, Context context) {
/* 238 */     if (areaListBean != null) {
/* 239 */       if (Constant.areaTypeVideo.equals(areaListBean.getArea_type())) {
/*     */         
/* 241 */         List<PhoneVideoPlayItemListBean> videoPlayItemLists = areaListBean.getVideoPlayItemList();
/* 242 */         List<String> videoPathList = new ArrayList<>();
/*     */         
/* 244 */         for (PhoneVideoPlayItemListBean v : videoPlayItemLists) {
/* 245 */           Logger.e("视频播放的本地地址为：" + ConfigManger.getInstance().getVideoPath() + v.getFile_name(), new Object[0]);
/* 246 */           videoPathList.add(ConfigManger.getInstance().getVideoPath() + v.getFile_name());
/*     */         } 
/*     */         
/* 249 */         CustomVideoView videoView = new CustomVideoView(context);
/* 250 */         ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
/* 251 */         Logger.e("视频区域id： " + areaListBean.getArea_id(), new Object[0]);
/*     */         
/* 253 */         videoView.setVideoPathList(videoPathList);
/* 254 */         videoView.setId((int)areaListBean.getArea_id());
/*     */ 
/*     */         
/* 257 */         videoView.setLayoutParams(layoutParams);
/*     */         
/* 259 */         return (View)videoView;
/* 260 */       }  if (Constant.areaTypeImage.equals(areaListBean.getArea_type())) {
/*     */         
/* 262 */         List<PhoneImagePlayItemListBean> imagePlayItemLists = areaListBean.getImagePlayItemList();
/* 263 */         List<String> imagePathList = new ArrayList<>();
/* 264 */         int index = 0;
/* 265 */         for (PhoneImagePlayItemListBean v : imagePlayItemLists) {
/* 266 */           Logger.e("图片播放的本地地址为：" + ConfigManger.getInstance().getImagePath() + v.getFile_name(), new Object[0]);
/* 267 */           imagePathList.add(index++, ConfigManger.getInstance().getImagePath() + v.getFile_name());
/*     */         } 
/*     */         
/* 270 */         CustomImageView imageView = new CustomImageView(context);
/* 271 */         ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
/*     */         
/* 273 */         Logger.e("图片区域id： " + areaListBean.getArea_id(), new Object[0]);
/* 274 */         Logger.e("开始getStartX：" + areaListBean.getStartX(), new Object[0]);
/* 275 */         Logger.e("开始getStartY：" + areaListBean.getStartY(), new Object[0]);
/*     */         
/* 277 */         imageView.addImagePathList(imagePathList);
/* 278 */         imageView.setId((int)areaListBean.getArea_id());
/*     */ 
/*     */ 
/*     */         
/* 282 */         imageView.setLayoutParams(layoutParams);
/*     */         
/* 284 */         Logger.e("获取每个区域的view    返回image view", new Object[0]);
/* 285 */         return (View)imageView;
/*     */       } 
/*     */     } 
/* 288 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\viewutils\ViewUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */