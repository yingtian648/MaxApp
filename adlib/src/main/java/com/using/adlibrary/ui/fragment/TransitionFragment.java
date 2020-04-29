/*     */ package com.using.adlibrary.ui.fragment;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Bundle;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.v4.app.Fragment;
/*     */ import android.support.v4.view.PagerAdapter;
/*     */ import android.support.v4.view.ViewPager;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.R;
/*     */ import com.using.adlibrary.bean.dao.MainProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.RootListDaoBean;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.ui.adapter.HomePagerAdapter;
/*     */ import com.using.adlibrary.ui.viewutils.ViewUtil;
/*     */ import com.using.adlibrary.utils.FileUtils;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransitionFragment
/*     */   extends Fragment
/*     */   implements ArbitrarilyFragment.OnFragmentDisplayFinishListener
/*     */ {
/*     */   private Gson gson;
/*  39 */   private int change = 0;
/*     */   private Context context;
/*     */   private ViewPager viewPager;
/*     */   private RelativeLayout layout;
/*     */   private HomePagerAdapter adapter;
/*     */   
/*     */   public TransitionFragment() {
/*  46 */     this.gson = new Gson();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
/*  52 */     View view = inflater.inflate(R.layout.fragment_transition, container, false);
/*  53 */     this.layout = (RelativeLayout)view.findViewById(R.id.id_transtion_layout);
/*  54 */     this.viewPager = (ViewPager)view.findViewById(R.id.id_viewPager_transition);
/*  55 */     this.context = view.getContext();
/*  56 */     return view;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onStart() {
/*  61 */     super.onStart();
/*     */     try {
/*  63 */       init();
/*  64 */     } catch (Exception e) {
/*  65 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onStop() {
/*  71 */     super.onStop();
/*  72 */     this.change = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fragmentDisplayFinish(long pid, int type) {
/*  78 */     Logger.e("切换fragment", new Object[0]);
/*  79 */     this.change++;
/*  80 */     if (this.change < this.adapter.getCount()) {
/*  81 */       this.viewPager.setCurrentItem(this.change);
/*  82 */       Logger.e("切换到第 " + this.change + " fragment", new Object[0]);
/*     */     } else {
/*  84 */       this.change = 0;
/*  85 */       this.viewPager.setCurrentItem(0);
/*  86 */       Logger.e("切换到第 " + this.change + " fragment", new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void init() {
/*     */     try {
/*  92 */       String jsonData = FileUtils.readToString(ConfigManger.getInstance().getMaterialPath() + "playList.txt");
/*  93 */       if (jsonData != null && !jsonData.isEmpty()) {
/*     */         
/*  95 */         Type type = (new TypeToken<RootListDaoBean>() {  }).getType();
/*  96 */         RootListDaoBean b = (RootListDaoBean)this.gson.fromJson(jsonData, type);
/*  97 */         addToViewPager(readDataFromJson(b));
/*     */       } else {
/*  99 */         Logger.e("本地没有导入节目单！", new Object[0]);
/*     */         
/* 101 */         ImageView imageView = setDefaultViewImage(this.context);
/*     */         
/* 103 */         if (this.layout != null) {
/* 104 */           this.layout.removeAllViews();
/* 105 */           this.layout.addView((View)imageView);
/*     */         } 
/*     */       } 
/* 108 */     } catch (Exception e) {
/* 109 */       e.printStackTrace();
/* 110 */       Logger.e("没找到本地文件", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private TextView setDefaultViewText(Context contextT) {
/* 116 */     RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
/*     */ 
/*     */     
/* 119 */     lp.addRule(15);
/* 120 */     lp.addRule(14);
/*     */     
/* 122 */     TextView textView = new TextView(contextT);
/* 123 */     textView.setText("当前没有节目单播放，请等待。。。");
/* 124 */     textView.setLayoutParams((ViewGroup.LayoutParams)lp);
/* 125 */     return textView;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ImageView setDefaultViewImage(Context contextD) {
/* 131 */     RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
/*     */ 
/*     */     
/* 134 */     ImageView imageView = new ImageView(contextD);
/*     */     
/* 136 */     imageView.setImageResource(R.drawable.ettda_logo);
/* 137 */     imageView.setLayoutParams((ViewGroup.LayoutParams)lp);
/* 138 */     imageView.setScaleType(ImageView.ScaleType.FIT_XY);
/* 139 */     return imageView;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ArbitrarilyFragment> readDataFromJson(RootListDaoBean r) {
/* 146 */     List<ArbitrarilyFragment> allFragments = new ArrayList<>();
/*     */     
/* 148 */     List<MainProgramListDaoBean> mains = r.getMainProgramList();
/*     */     
/* 150 */     if (mains != null && mains.size() > 0) {
/* 151 */       allFragments.addAll(ViewUtil.getMainFragments(mains, this.context));
/*     */     }
/* 153 */     return allFragments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToViewPager(List<ArbitrarilyFragment> fragmentList) {
/* 160 */     Logger.e("添加fragment到viewPager--------", new Object[0]);
/* 161 */     Logger.e("-----------------fragment的个数为：" + fragmentList.size(), new Object[0]);
/* 162 */     Fragment[] fragments = new Fragment[fragmentList.size()];
/* 163 */     fragmentList.toArray(fragments);
/*     */     
/* 165 */     for (ArbitrarilyFragment f : fragmentList) {
/* 166 */       f.setListener(this);
/*     */     }
/*     */     
/* 169 */     if (fragments.length > 0) {
/* 170 */       this.viewPager.removeAllViews();
/* 171 */       this.adapter = new HomePagerAdapter(getChildFragmentManager(), fragments);
/*     */       
/* 173 */       this.viewPager.setAdapter((PagerAdapter)this.adapter);
/* 174 */       this.viewPager.setOffscreenPageLimit(fragments.length);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\fragment\TransitionFragment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */