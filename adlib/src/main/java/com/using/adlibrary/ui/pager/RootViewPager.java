/*    */ package com.using.adlibrary.ui.pager;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.support.annotation.NonNull;
/*    */ import android.support.annotation.Nullable;
/*    */ import android.support.v4.view.ViewPager;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.MotionEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RootViewPager
/*    */   extends ViewPager
/*    */ {
/*    */   public RootViewPager(@NonNull Context context) {
/* 16 */     super(context);
/*    */   }
/*    */   
/*    */   public RootViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
/* 20 */     super(context, attrs);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onTouchEvent(MotionEvent ev) {
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean dispatchTouchEvent(MotionEvent ev) {
/* 30 */     return super.dispatchTouchEvent(ev);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onInterceptTouchEvent(MotionEvent ev) {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
/* 41 */     super.onPageScrolled(position, positionOffset, positionOffsetPixels);
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\pager\RootViewPager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */