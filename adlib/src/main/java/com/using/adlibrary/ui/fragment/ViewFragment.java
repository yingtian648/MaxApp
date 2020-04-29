/*     */ package com.using.adlibrary.ui.fragment;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.os.Bundle;
/*     */ import android.os.Handler;
/*     */ import android.os.Message;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.v4.app.Fragment;
/*     */ import android.support.v4.view.PagerAdapter;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.R;
/*     */ import com.using.adlibrary.bean.dao.ImmediateProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MainProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.TaskPackageCountBean;
/*     */ import com.using.adlibrary.bean.dao.TimingProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.event.ViewFragmentEvent;
/*     */ import com.using.adlibrary.bean.phone.PhoneMainProgramListBean;
/*     */ import com.using.adlibrary.dao.TaskPackageCountBeanDao;
/*     */ import com.using.adlibrary.ui.adapter.HomePagerAdapter;
/*     */ import com.using.adlibrary.ui.pager.RootViewPager;
/*     */ import com.using.adlibrary.ui.viewutils.ViewUtil;
/*     */ import com.using.adlibrary.utils.TimeUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import org.greenrobot.eventbus.EventBus;
/*     */ import org.greenrobot.eventbus.Subscribe;
/*     */ import org.greenrobot.eventbus.ThreadMode;
/*     */ import org.greenrobot.greendao.query.WhereCondition;
/*     */ 
/*     */ 
/*     */ public class ViewFragment
/*     */   extends Fragment
/*     */   implements ArbitrarilyFragment.OnFragmentDisplayFinishListener
/*     */ {
/*     */   private Context context;
/*     */   private RootViewPager viewPager;
/*     */   private HomePagerAdapter adapter;
/*  45 */   private final int PROGRAM_FIRST_LOOP_END = 7070;
/*  46 */   private final int READ_DATABASE_DATA = 7071;
/*  47 */   private final int A_Task_PLAY_END = 7072;
/*     */   
/*  49 */   private List<Long> mainCache = new ArrayList<>();
/*  50 */   private List<Long> timeCache = new ArrayList<>();
/*  51 */   private List<ImmediateProgramListDaoBean> immediateCache = new ArrayList<>();
/*     */   
/*  53 */   private List<ArbitrarilyFragment> allFragments = new ArrayList<>();
/*  54 */   private Handler mHandler = new Handler(new Handler.Callback()
/*     */       {
/*     */         public boolean handleMessage(Message msg) {
/*  57 */           Logger.e("Fragment收到的handler消息：", new Object[0]);
/*  58 */           switch (msg.what) {
/*     */             
/*     */             case 7072:
/*     */               
/*     */               try {
/*  63 */                 long taskId = ((Long)msg.obj).longValue();
/*  64 */                 int type = msg.arg1;
/*  65 */                 Logger.e("当前任务包 taskId=  " + taskId + "任务包类型0为主任务包 ： " + type, new Object[0]);
/*     */ 
/*     */ 
/*     */                 
/*  69 */                 TaskPackageCountBean taskPackageCountBean = (TaskPackageCountBean)AdEngine.getInstances().getDaoSession().getTaskPackageCountBeanDao().queryBuilder().where(TaskPackageCountBeanDao.Properties.TaskId.eq(Long.valueOf(taskId)), new WhereCondition[] { TaskPackageCountBeanDao.Properties.Type.eq(Integer.valueOf(type)) }).unique();
/*     */                 
/*  71 */                 if (taskPackageCountBean != null) {
/*  72 */                   taskPackageCountBean.setNum(taskPackageCountBean.getNum() + 1);
/*  73 */                   Logger.e("当前节目单统计： " + taskPackageCountBean.getTaskId() + " " + taskPackageCountBean.getNum(), new Object[0]);
/*  74 */                   AdEngine.getInstances().getDaoSession().getTaskPackageCountBeanDao().insertOrReplace(taskPackageCountBean);
/*     */                   break;
/*     */                 } 
/*  77 */                 Logger.e("插入初始计数数据------------", new Object[0]);
/*  78 */                 TaskPackageCountBean b = new TaskPackageCountBean();
/*  79 */                 b.setTaskId(taskId);
/*  80 */                 b.setType(type);
/*  81 */                 b.setNum(b.getNum() + 1);
/*  82 */                 AdEngine.getInstances().getDaoSession().getTaskPackageCountBeanDao().insertOrReplace(b);
/*     */               }
/*  84 */               catch (Exception e) {
/*  85 */                 e.printStackTrace();
/*     */               } 
/*     */               break;
/*     */ 
/*     */             
/*     */             case 7070:
/*  91 */               if (ViewFragment.this.immediateCache != null && ViewFragment.this.immediateCache.size() > 0) {
/*  92 */                 ViewFragment.this.immediateCache.clear();
/*  93 */                 ViewFragment.this.mHandler.sendEmptyMessage(7071);
/*     */               } 
/*     */               
/*  96 */               if (ViewFragment.this.isDataWhetherToChange(ViewFragment.this.timeCache, ViewFragment.this.mainCache)) {
/*  97 */                 Logger.e("isDataWhetherToChange 数据库数据与当前使用的数据发生了变化，更新UI", new Object[0]);
/*  98 */                 ViewFragment.this.mHandler.sendEmptyMessage(7071); break;
/*     */               } 
/* 100 */               Logger.e("数据库数据与当前使用的数据一致，不刷新界面", new Object[0]);
/*     */               break;
/*     */ 
/*     */             
/*     */             case 7071:
/* 105 */               Logger.e("读取数据，更新界面=============", new Object[0]);
/*     */               try {
/* 107 */                 if (ViewFragment.this.allFragments != null) {
/* 108 */                   ViewFragment.this.allFragments.clear();
/*     */                 } else {
/* 110 */                   ViewFragment.this.allFragments = new ArrayList();
/*     */                 } 
/*     */                 
/* 113 */                 List<ArbitrarilyFragment> tempComputer = ViewFragment.this.readDataFromDatabase2();
/* 114 */                 if (tempComputer != null && tempComputer.size() > 0) {
/* 115 */                   ViewFragment.this.allFragments.addAll(tempComputer);
/*     */                 }
/*     */                 
/* 118 */                 List<ArbitrarilyFragment> temp = ViewFragment.this.readDataFromDatabase1();
/* 119 */                 if (temp != null && temp.size() > 0) {
/* 120 */                   ViewFragment.this.allFragments.addAll(temp);
/*     */                 }
/*     */                 
/* 123 */                 if (ViewFragment.this.allFragments != null && ViewFragment.this.allFragments.size() > 0) {
/* 124 */                   ViewFragment.this.change = 0;
/* 125 */                   ViewFragment.this.addToViewPager(ViewFragment.this.allFragments);
/* 126 */                   Logger.e("有节目单数据，显示节目单数据。。。。。。。", new Object[0]); break;
/*     */                 } 
/* 128 */                 Logger.e("没有节目单,显示垫片。。。。。。。", new Object[0]);
/* 129 */                 ViewFragment.this.translationShow();
/*     */               }
/* 131 */               catch (Exception e) {
/* 132 */                 e.printStackTrace();
/*     */               } 
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 140 */           return false;
/*     */         }
/*     */       });
/*     */   
/*     */   public static ViewFragment newInstance() {
/* 145 */     return new ViewFragment();
/*     */   }
/*     */   
/*     */   @Subscribe(threadMode = ThreadMode.MAIN)
/*     */   public void updateEvent(ViewFragmentEvent event) {
/* 150 */     this.mHandler.sendEmptyMessage(7071);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onAttach(Context context) {
/* 155 */     super.onAttach(context);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     Logger.e("执行了ViewFragment的  onAttach()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCreate(Bundle savedInstanceState) {
/* 166 */     super.onCreate(savedInstanceState);
/* 167 */     Logger.e("执行了ViewFragment的  onCreate()方法", new Object[0]);
/*     */     
/* 169 */     EventBus.getDefault().register(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
/* 174 */     super.onRequestPermissionsResult(requestCode, permissions, grantResults);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"ClickableViewAccessibility"})
/*     */   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/* 181 */     View mView = inflater.inflate(R.layout.fragment_view, container, false);
/* 182 */     this.viewPager = (RootViewPager)mView.findViewById(R.id.id_viewPager);
/* 183 */     this.context = mView.getContext();
/* 184 */     Logger.e("执行了ViewFragment的  onCreateView()方法", new Object[0]);
/* 185 */     this.mHandler.sendEmptyMessageDelayed(7071, 10L);
/* 186 */     return mView;
/*     */   }
/*     */ 
/*     */   
/*     */   private void translationShow() {
/* 191 */     Fragment[] fragments = { new TransitionFragment() };
/* 192 */     this.adapter = new HomePagerAdapter(getChildFragmentManager(), fragments);
/*     */     
/* 194 */     this.viewPager.setAdapter((PagerAdapter)this.adapter);
/* 195 */     this.viewPager.setOffscreenPageLimit(fragments.length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStart() {
/* 201 */     super.onStart();
/*     */     
/* 203 */     Logger.e("执行了ViewFragment的    onStart()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
/* 210 */     super.onActivityCreated(savedInstanceState);
/*     */     
/* 212 */     Logger.e("执行了ViewFragment的    onActivityCreated方法", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSaveInstanceState(Bundle outState) {
/* 218 */     super.onSaveInstanceState(outState);
/*     */     
/* 220 */     Logger.e("执行了ViewFragment的   onSaveInstanceState()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onResume() {
/* 227 */     super.onResume();
/*     */     
/* 229 */     this.mHandler.sendEmptyMessageDelayed(7071, 0L);
/* 230 */     Logger.e("执行了ViewFragment的  onResume()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPause() {
/* 235 */     super.onPause();
/*     */     
/* 237 */     Logger.e("执行了ViewFragment的   onPause()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStop() {
/* 244 */     super.onStop();
/* 245 */     this.change = 0;
/* 246 */     Logger.e("执行了ViewFragment的   onStop()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDestroyView() {
/* 251 */     super.onDestroyView();
/* 252 */     Logger.e("执行了ViewFragment的   onDestroyView()方法", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDetach() {
/* 257 */     super.onDetach();
/* 258 */     Logger.e("执行了ViewFragment的   执行了onDetach()方法，当Fragment与Activity关联被取消时调用", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDestroy() {
/* 263 */     super.onDestroy();
/* 264 */     this.change = 0;
/* 265 */     EventBus.getDefault().unregister(this);
/* 266 */     Logger.e("执行了ViewFragment的  onDestroy()方法", new Object[0]);
/*     */   }
/*     */   
/* 269 */   private int change = 0;
/*     */ 
/*     */   
/*     */   public void fragmentDisplayFinish(long pid, int type) {
/*     */     try {
/* 274 */       Logger.e("切换fragment" + this.adapter.getCount(), new Object[0]);
/* 275 */       this.change++;
/* 276 */       if (this.change < this.adapter.getCount()) {
/* 277 */         this.viewPager.setCurrentItem(this.change);
/* 278 */         Logger.e("切换到第 " + this.change + " fragment", new Object[0]);
/*     */       } else {
/* 280 */         this.change = 0;
/* 281 */         this.mHandler.sendEmptyMessage(7070);
/* 282 */         this.viewPager.setCurrentItem(0);
/* 283 */         Logger.e("切换到第 " + this.change + " fragment", new Object[0]);
/*     */       } 
/* 285 */       Message msg = Message.obtain();
/* 286 */       msg.what = 7072;
/* 287 */       msg.obj = Long.valueOf(pid);
/* 288 */       msg.arg1 = type;
/* 289 */       this.mHandler.sendMessage(msg);
/* 290 */     } catch (Exception e) {
/* 291 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stopAdCloud() {
/* 296 */     if (this.adapter.getCount() >= 1) {
/* 297 */       if (this.change >= this.adapter.getCount()) {
/* 298 */         this.change = 0;
/*     */       }
/* 300 */       this.viewPager.setCurrentItem(this.change);
/* 301 */       this.adapter.stopPlay(this.change);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startAdCloud() {
/* 306 */     if (this.adapter.getCount() >= 1) {
/* 307 */       if (this.change >= this.adapter.getCount()) {
/* 308 */         this.change = 0;
/*     */       }
/* 310 */       this.viewPager.setCurrentItem(this.change);
/* 311 */       this.adapter.startPlay(this.change);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDataWhetherToChange(List<Long> timeCa, List<Long> mainCa) {
/* 321 */     Calendar c = Calendar.getInstance();
/* 322 */     long mWay = (c.get(7) - 1);
/*     */     
/* 324 */     List<TimingProgramListDaoBean> times = AdEngine.getInstances().getDaoSession().getTimingProgramListDaoBeanDao().loadAll();
/*     */     
/* 326 */     List<Long> timesToUse1 = new ArrayList<>();
/*     */     
/* 328 */     if (times.size() > 0) {
/* 329 */       for (TimingProgramListDaoBean timeBean : times) {
/* 330 */         if (timeBean.getPlayDays() == mWay) {
/* 331 */           if (TimeUtils.netTimeToSecond(timeBean.getBeginTime()) <= TimeUtils.currentTimeToSencond() && TimeUtils.currentTimeToSencond() <= TimeUtils.netTimeToSecond(timeBean.getEndTime()))
/*     */           {
/* 333 */             timesToUse1.add(timeBean.getId()); } 
/*     */           continue;
/*     */         } 
/* 336 */         Logger.e("数据对比，不是在当前星期播放 ", new Object[0]);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 342 */     List<Long> mainsToUse1 = new ArrayList<>();
/* 343 */     List<MainProgramListDaoBean> mainTemp = AdEngine.getInstances().getDaoSession().getMainProgramListDaoBeanDao().loadAll();
/* 344 */     if (mainTemp != null && mainTemp.size() > 0) {
/* 345 */       for (MainProgramListDaoBean mainBean : mainTemp) {
/* 346 */         mainsToUse1.add(mainBean.getId());
/*     */       }
/*     */     }
/* 349 */     Logger.e("isDataWhetherToChange 当前定时节目单个数为： " + timeCa.size(), new Object[0]);
/* 350 */     Logger.e("isDataWhetherToChange 从数据库提取的定时节目单个数为： " + timesToUse1.size(), new Object[0]);
/*     */     
/* 352 */     Logger.e("isDataWhetherToChange 当前主节目单个数为： " + mainCa.size(), new Object[0]);
/* 353 */     Logger.e("isDataWhetherToChange 从数据库提取的主节目单个数为： " + mainsToUse1.size(), new Object[0]);
/*     */     
/* 355 */     Logger.e("isDataWhetherToChange 主节目单 " + (!isListEqual(mainsToUse1, mainCa) ? 1 : 0), new Object[0]);
/* 356 */     Logger.e("isDataWhetherToChange 定时节目单 " + (!isListEqual(timesToUse1, timeCa) ? 1 : 0), new Object[0]);
/*     */     
/* 358 */     return (!isListEqual(timesToUse1, timeCa) || !isListEqual(mainsToUse1, mainCa));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isListEqual(List l0, List l1) {
/* 363 */     if (l0 == l1) {
/* 364 */       return true;
/*     */     }
/*     */     
/* 367 */     if (l0 == null || l1 == null) {
/* 368 */       return false;
/*     */     }
/*     */     
/* 371 */     if (l0.size() != l1.size()) {
/* 372 */       return false;
/*     */     }
/*     */     
/* 375 */     for (Object o : l0) {
/* 376 */       if (!l1.contains(o)) {
/* 377 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 381 */     for (Object o : l1) {
/* 382 */       if (!l0.contains(o)) {
/* 383 */         return false;
/*     */       }
/*     */     } 
/* 386 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ArbitrarilyFragment> readDataFromDatabase1() {
/* 396 */     List<PhoneMainProgramListBean> phoneMains = AdEngine.getInstances().getDaoSession().getPhoneMainProgramListBeanDao().loadAll();
/*     */     
/* 398 */     if (phoneMains != null && phoneMains.size() > 0) {
/* 399 */       return ViewUtil.getPhoneMainFragments(phoneMains, this.context);
/*     */     }
/*     */     
/* 402 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ArbitrarilyFragment> readDataFromDatabase2() {
/* 409 */     List<ArbitrarilyFragment> allFragments = new ArrayList<>();
/*     */     
/* 411 */     if (this.timeCache != null) {
/* 412 */       this.timeCache.clear();
/*     */     }
/*     */     
/* 415 */     if (this.mainCache != null) {
/* 416 */       this.mainCache.clear();
/*     */     }
/*     */ 
/*     */     
/* 420 */     Calendar c = Calendar.getInstance();
/* 421 */     long mWay = (c.get(7) - 1);
/*     */ 
/*     */ 
/*     */     
/* 425 */     List<ImmediateProgramListDaoBean> immediates = AdEngine.getInstances().getDaoSession().getImmediateProgramListDaoBeanDao().loadAll();
/*     */     
/* 427 */     if (immediates.size() > 0) {
/* 428 */       Logger.e("有即时节目单需要播放，有 " + immediates.size() + "  个", new Object[0]);
/* 429 */       this.immediateCache = immediates;
/* 430 */       List<ArbitrarilyFragment> immediateArbitrarilyes = ViewUtil.getImmediatesFragments(immediates, this.context);
/* 431 */       AdEngine.getInstances().getDaoSession().getImmediateProgramListDaoBeanDao().deleteAll();
/*     */       
/* 433 */       if (immediateArbitrarilyes != null && immediateArbitrarilyes.size() > 0) {
/* 434 */         return immediateArbitrarilyes;
/*     */       }
/*     */     } else {
/* 437 */       Logger.e("即时节目单数据为空了，即时节目单全部播放完了,读取定时节目单数据", new Object[0]);
/*     */ 
/*     */       
/* 440 */       List<TimingProgramListDaoBean> times = AdEngine.getInstances().getDaoSession().getTimingProgramListDaoBeanDao().loadAll();
/* 441 */       List<TimingProgramListDaoBean> timesToUse = new ArrayList<>();
/*     */ 
/*     */       
/* 444 */       if (times != null && times.size() > 0) {
/* 445 */         for (TimingProgramListDaoBean timeBean : times) {
/* 446 */           if (timeBean.getPlayDays() == mWay) {
/* 447 */             if (TimeUtils.netTimeToSecond(timeBean.getBeginTime()) <= TimeUtils.currentTimeToSencond() && 
/* 448 */               TimeUtils.currentTimeToSencond() <= TimeUtils.netTimeToSecond(timeBean.getEndTime())) {
/*     */               
/* 450 */               timesToUse.add(timeBean);
/* 451 */               this.timeCache.add(timeBean.getId());
/*     */             }  continue;
/*     */           } 
/* 454 */           Logger.e("不是在当前星期播放 ", new Object[0]);
/*     */         } 
/*     */       } else {
/*     */         
/* 458 */         this.timeCache.clear();
/*     */       } 
/*     */       
/* 461 */       if (timesToUse.size() > 0) {
/* 462 */         List<ArbitrarilyFragment> timesFragments = ViewUtil.getTimesFragments(timesToUse, this.context);
/* 463 */         if (timesFragments != null && timesFragments.size() > 0) {
/* 464 */           allFragments.addAll(timesFragments);
/*     */         }
/*     */       } else {
/* 467 */         Logger.e("没有有效的定时节目单", new Object[0]);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 473 */       List<MainProgramListDaoBean> mains = AdEngine.getInstances().getDaoSession().getMainProgramListDaoBeanDao().loadAll();
/*     */       
/* 475 */       if (mains != null && mains.size() > 0) {
/*     */         
/* 477 */         for (MainProgramListDaoBean mBean : mains) {
/* 478 */           this.mainCache.add(mBean.getId());
/*     */         }
/*     */         
/* 481 */         Logger.e("有主节目单， 创建主节目单的fragment ", new Object[0]);
/* 482 */         List<ArbitrarilyFragment> mainFragments = ViewUtil.getMainFragments(mains, this.context);
/* 483 */         if (mainFragments != null && mainFragments.size() > 0) {
/* 484 */           allFragments.addAll(mainFragments);
/*     */         }
/*     */       } else {
/* 487 */         this.mainCache.clear();
/*     */       } 
/*     */       
/* 490 */       return allFragments;
/*     */     } 
/*     */     
/* 493 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToViewPager(List<ArbitrarilyFragment> fragmentList) {
/* 500 */     Logger.e("添加fragment到viewPager--------", new Object[0]);
/* 501 */     Logger.e("-----------------fragment的个数为：" + fragmentList.size(), new Object[0]);
/* 502 */     Fragment[] fragments = new Fragment[fragmentList.size()];
/* 503 */     fragmentList.toArray(fragments);
/*     */     
/* 505 */     for (ArbitrarilyFragment f : fragmentList) {
/* 506 */       f.setListener(this);
/*     */     }
/*     */     
/* 509 */     if (fragments.length > 0) {
/* 510 */       this.viewPager.removeAllViews();
/* 511 */       this.adapter = new HomePagerAdapter(getChildFragmentManager(), fragments);
/* 512 */       this.viewPager.setAdapter((PagerAdapter)this.adapter);
/* 513 */       this.viewPager.setOffscreenPageLimit(fragments.length);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\fragment\ViewFragment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */