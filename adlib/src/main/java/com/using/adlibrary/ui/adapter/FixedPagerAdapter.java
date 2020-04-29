/*    */ package com.using.adlibrary.ui.adapter;
/*    */ 
/*    */ import android.support.v4.app.Fragment;
/*    */ import android.support.v4.app.FragmentManager;
/*    */ import android.support.v4.app.FragmentStatePagerAdapter;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FixedPagerAdapter<T>
/*    */   extends FragmentStatePagerAdapter
/*    */ {
/* 18 */   private List<ItemObject> mCurrentItems = new ArrayList<>();
/*    */   
/*    */   public FixedPagerAdapter(FragmentManager fragmentManager) {
/* 21 */     super(fragmentManager);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object instantiateItem(ViewGroup container, int position) {
/* 26 */     while (this.mCurrentItems.size() <= position) {
/* 27 */       this.mCurrentItems.add(null);
/*    */     }
/* 29 */     Fragment fragment = (Fragment)super.instantiateItem(container, position);
/* 30 */     ItemObject object = new ItemObject(fragment, getItemData(position));
/* 31 */     this.mCurrentItems.set(position, object);
/* 32 */     return object;
/*    */   }
/*    */ 
/*    */   
/*    */   public void destroyItem(ViewGroup container, int position, Object object) {
/* 37 */     this.mCurrentItems.set(position, null);
/*    */     
/* 39 */     super.destroyItem(container, position, ((ItemObject)object).fragment);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemPosition(Object object) {
/* 44 */     ItemObject itemObject = (ItemObject)object;
/* 45 */     if (this.mCurrentItems.contains(itemObject)) {
/* 46 */       T oldData = itemObject.t;
/* 47 */       int oldPosition = this.mCurrentItems.indexOf(itemObject);
/* 48 */       T newData = getItemData(oldPosition);
/* 49 */       if (equals(oldData, newData)) {
/* 50 */         return -1;
/*    */       }
/* 52 */       int newPosition = getDataPosition(oldData);
/* 53 */       return (newPosition >= 0) ? newPosition : -2;
/*    */     } 
/*    */     
/* 56 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPrimaryItem(ViewGroup container, int position, Object object) {
/* 61 */     super.setPrimaryItem(container, position, ((ItemObject)object).fragment);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isViewFromObject(View view, Object object) {
/* 66 */     return super.isViewFromObject(view, ((ItemObject)object).fragment);
/*    */   }
/*    */   
/*    */   public abstract T getItemData(int paramInt);
/*    */   
/*    */   public abstract int getDataPosition(T paramT);
/*    */   
/*    */   public abstract boolean equals(T paramT1, T paramT2);
/*    */   
/*    */   public class ItemObject
/*    */   {
/*    */     public Fragment fragment;
/*    */     public T t;
/*    */     
/*    */     public ItemObject(Fragment fragment, T t) {
/* 81 */       this.fragment = fragment;
/* 82 */       this.t = t;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\adapter\FixedPagerAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */