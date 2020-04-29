/*    */ package com.using.adlibrary.ui.adapter;
/*    */ 
/*    */ import android.support.v4.app.Fragment;
/*    */ import android.support.v4.app.FragmentManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HomePagerAdapter
/*    */   extends FixedPagerAdapter<Object>
/*    */ {
/*    */   private String[] TITLES;
/*    */   private Fragment[] fragments;
/*    */   
/*    */   public HomePagerAdapter(FragmentManager fm) {
/* 16 */     super(fm);
/*    */   }
/*    */   
/*    */   public HomePagerAdapter(FragmentManager fm, Fragment[] fs) {
/* 20 */     super(fm);
/*    */     
/* 22 */     this.fragments = fs;
/* 23 */     this.TITLES = new String[fs.length];
/*    */     
/* 25 */     for (int i = 0; i < fs.length; i++) {
/* 26 */       this.TITLES[i] = String.valueOf(i);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopPlay(int a) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void startPlay(int a) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public Fragment getItem(int position) {
/* 42 */     return this.fragments[position];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object oldD, Object newD) {
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 52 */     return this.fragments.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getItemData(int position) {
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDataPosition(Object o) {
/* 62 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\adapter\HomePagerAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */