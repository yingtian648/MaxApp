/*     */ package com.using.adlibrary.ui.fragment;
/*     */
/*     */ import android.content.Context;
/*     */ import android.os.Bundle;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.v4.app.Fragment;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.RelativeLayout;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.R;
/*     */ import com.using.adlibrary.ui.api.OnDisplayFinishListener;
/*     */ import com.using.adlibrary.ui.view.CustomImageView;
/*     */ import com.using.adlibrary.ui.view.CustomVideoView;
/*     */ import java.util.List;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class ArbitrarilyFragment
/*     */   extends Fragment
/*     */   implements OnDisplayFinishListener
/*     */ {
/*  28 */   private String TAG = "ArbitrarilyFragment";
/*     */   private long taskId;
/*  30 */   private int type = 0;
/*     */
/*     */   private RelativeLayout mRootLayout;
/*     */
/*     */   private List<View> mListView;
/*     */   protected boolean isVisible;
/*     */   private OnFragmentDisplayFinishListener listener;
/*     */   private int mImageViewNumber;
/*     */   private int mVideoViewNumber;
/*  39 */   private int mImageCount = 0;
/*  40 */   private int mVideoCount = 0;
/*     */
/*     */   public void addListView(List<View> listView) {
/*  43 */     this.mListView = listView;
/*  44 */     for (View v : this.mListView) {
/*  45 */       if (v instanceof CustomVideoView) {
/*  46 */         this.mVideoViewNumber++; continue;
/*  47 */       }  if (v instanceof CustomImageView) {
/*  48 */         this.mImageViewNumber++;
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void setUserVisibleHint(boolean isVisibleToUser) {
/*  56 */     super.setUserVisibleHint(isVisibleToUser);
/*  57 */     if (getUserVisibleHint()) {
/*  58 */       this.isVisible = true;
/*  59 */       onVisible();
/*     */     } else {
/*  61 */       this.isVisible = false;
/*  62 */       onInvisible();
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void onAttach(Context context) {
/*  68 */     super.onAttach(context);
/*  69 */     Logger.e("执行了onAttach()方法, 当Fragment与Activity发生关联时调用", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */   public void onCreate(@Nullable Bundle savedInstanceState) {
/*  74 */     super.onCreate(savedInstanceState);
/*  75 */     Logger.e("调用了ArbitrarilyFragment的 onCreate()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */   @Nullable
/*     */   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
/*  81 */     View view = inflater.inflate(R.layout.fragment_layout_arbitrarily, null);
/*  82 */     this.mRootLayout = (RelativeLayout)view.findViewById(R.id.id_arbitrarily_layout);
/*     */
/*     */     try {
/*  85 */       this.mRootLayout.removeAllViews();
/*  86 */       if (this.mListView != null && this.mListView.size() > 0) {
/*  87 */         Logger.e("当前fragment的view个数为： " + this.mListView.size(), new Object[0]);
                  int vi = -1;
/*  88 */         for (View v : this.mListView) {
/*     */           if (v instanceof CustomVideoView) {
/*  90 */               this.mRootLayout.addView(v);
                    }else {
                        this.mRootLayout.addView(v,0);
                    }
/*  91 */           if (v instanceof CustomVideoView) {
/*  92 */             ((CustomVideoView)v).setDisplayFinishListener(this); continue;
/*  93 */           }  if (v instanceof CustomImageView) {
/*  94 */             ((CustomImageView)v).setDisplayFinishListener(this);
/*     */           }
/*     */         }
/*  97 */       } else if (this.listener != null) {
/*  98 */         Logger.e("没有view在ArbitrarilyFragment上----------------", new Object[0]);
/*  99 */         this.listener.fragmentDisplayFinish(getTaskId(), getType());
/*     */       }
/* 101 */       Logger.e("执行了onCreateView()方法, 创建该Fragment的视图的时候调用", new Object[0]);
/* 102 */     } catch (Exception e) {
/* 103 */       e.printStackTrace();
/* 104 */       Logger.e("ArbitrarilyFragment onCreateView 创建异常", new Object[0]);
/*     */     }
/* 106 */     return view;
/*     */   }
/*     */
/*     */
/*     */   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
/* 111 */     super.onActivityCreated(savedInstanceState);
/* 112 */     Logger.e("执行了onActivityCreated()， 当Activity的onCreate方法返回时调用", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onStart() {
/* 118 */     super.onStart();
/* 119 */     Logger.e("执行了 ArbitrarilyFragment    onStart()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
/* 125 */     super.onViewStateRestored(savedInstanceState);
/* 126 */     Logger.e("执行了 ArbitrarilyFragment   onViewStateRestored()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onSaveInstanceState(Bundle outState) {
/* 132 */     super.onSaveInstanceState(outState);
/* 133 */     Logger.e("执行了 ArbitrarilyFragment    onSaveInstanceState()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onResume() {
/* 139 */     super.onResume();
/* 140 */     Logger.e("执行了ArbitrarilyFragment    onResume()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onPause() {
/* 146 */     super.onPause();
/* 147 */     Logger.e("执行了ArbitrarilyFragment   onPause()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onStop() {
/* 153 */     super.onStop();
/* 154 */     for (View v : this.mListView) {
/* 155 */       if (v instanceof CustomVideoView) {
/* 156 */         ((CustomVideoView)v).stopPlayback();
/* 157 */         Logger.e("调用 CustomVideoView stopPlayback", new Object[0]); continue;
/* 158 */       }  if (v instanceof CustomImageView) {
/* 159 */         ((CustomImageView)v).stopLoopImage();
/* 160 */         Logger.e("调用CustomImageView stopLoopImage", new Object[0]);
/*     */       }
/*     */     }
/* 163 */     Logger.e("执行了ArbitrarilyFragment   onStop()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */   public void onDestroyView() {
/* 168 */     super.onDestroyView();
/* 169 */     Logger.e("ArbitrarilyFragment   onDestroyView()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */   public void onDetach() {
/* 174 */     super.onDetach();
/* 175 */     Logger.e("ArbitrarilyFragment   执行了onDetach()方法，当Fragment与Activity关联被取消时调用", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */   public void onDestroy() {
/* 180 */     super.onDestroy();
/* 181 */     Logger.e("ArbitrarilyFragment onDestroy()方法", new Object[0]);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public void onVisible() {
/* 188 */     Logger.e("ArbitrarilyFragment可见", new Object[0]);
			  if(this.mListView==null || this.mListView.size()==0){
				  return;
			  }
/* 189 */     for (View v : this.mListView) {
/* 190 */       if (v instanceof CustomVideoView) {
/* 191 */         if (!((CustomVideoView)v).isPlaying()) {
/* 192 */           ((CustomVideoView)v).startS();
/*     */         }
/* 194 */         Logger.e("ArbitrarilyFragment可见,调用了CustomVideoView start", new Object[0]); continue;
/* 195 */       }  if (v instanceof CustomImageView) {
/* 196 */         ((CustomImageView)v).startLoopImage();
/* 197 */         Logger.e("ArbitrarilyFragment可见，调用了CustomImageView startLoopImage", new Object[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public void onInvisible() {
/* 206 */     Logger.e("ArbitrarilyFragment不可见", new Object[0]);
/* 207 */     for (View v : this.mListView) {
/* 208 */       if (v instanceof CustomVideoView) {
/* 209 */         ((CustomVideoView)v).pause();
/* 210 */         Logger.e("调用 CustomVideoView pause", new Object[0]); continue;
/* 211 */       }  if (v instanceof CustomImageView) {
/* 212 */         ((CustomImageView)v).stopLoopImage();
/* 213 */         Logger.e("调用CustomImageView stopLoopImage", new Object[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean displayFinish(String types, int loopCount) {
/* 220 */     Logger.e("有一个控件执行完成了一次循环展示 ", new Object[0]);
/* 221 */     Logger.e("图片控件的数量 " + this.mImageViewNumber, new Object[0]);
/* 222 */     Logger.e("视频控件的数量 " + this.mVideoViewNumber, new Object[0]);
/*     */
/* 224 */     Logger.e("应该有 " + this.mListView.size() + "个控件完成循环才切换fragment", new Object[0]);
/* 225 */     if (this.isVisible) {
/* 226 */       if (types.equals("视频") && loopCount == 1) {
/* 227 */         this.mVideoCount++;
/*     */
/* 229 */         if (this.mVideoCount >= this.mVideoViewNumber) {
/* 230 */           Logger.e("有视频控件，对视频控件循环计数，当前有" + this.mVideoCount + "个视频控件完成一次循环", new Object[0]);
/* 231 */           this.mImageCount = 0;
/* 232 */           this.mVideoCount = 0;
/* 233 */           this.listener.fragmentDisplayFinish(getTaskId(), getType());
/*     */         }
/*     */
/* 236 */         Logger.e("有视频控件，则以视频为准，视频结束则切换fragment", new Object[0]);
/* 237 */       } else if (types.equals("图片") && loopCount == 1) {
/* 238 */         this.mImageCount++;
/* 239 */         Logger.e("有图片控件，对图片控件循环计数，当前有" + this.mImageCount + "个图片控件完成一次循环", new Object[0]);
/* 240 */         if (this.mImageCount >= this.mListView.size()) {
/* 241 */           this.mImageCount = 0;
/* 242 */           this.mVideoCount = 0;
/* 243 */           this.listener.fragmentDisplayFinish(getTaskId(), getType());
/*     */         }
/*     */       }
/*     */     }
/*     */
/* 248 */     return false;
/*     */   }
/*     */
/*     */   public void setListener(OnFragmentDisplayFinishListener listener) {
/* 252 */     this.listener = listener;
/*     */   }
/*     */
/*     */   public long getTaskId() {
/* 256 */     return this.taskId;
/*     */   }
/*     */
/*     */   public void setpId(long pId) {
/* 260 */     this.taskId = pId;
/*     */   }
/*     */
/*     */   public int getType() {
/* 264 */     return this.type;
/*     */   }
/*     */
/*     */   public void setType(int type) {
/* 268 */     this.type = type;
/*     */   }
/*     */
/*     */   public static interface OnFragmentDisplayFinishListener {
/*     */     void fragmentDisplayFinish(long param1Long, int param1Int);
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\fragment\ArbitrarilyFragment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */