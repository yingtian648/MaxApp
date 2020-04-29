/*    */ package com.using.adlibrary.ui.layout;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.widget.RelativeLayout;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomRelativeLayout
/*    */   extends RelativeLayout
/*    */ {
/* 19 */   private List<Integer> lists = new ArrayList<>();
/*    */   
/*    */   public CustomRelativeLayout(Context context) {
/* 22 */     super(context);
/*    */   }
/*    */   
/*    */   public List<Integer> getLists() {
/* 26 */     return this.lists;
/*    */   }
/*    */   
/*    */   public void addId(int id) {
/* 30 */     this.lists.add(Integer.valueOf(id));
/*    */   }
/*    */   
/*    */   public static interface OnLayoutPlayFinish {}
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\layout\CustomRelativeLayout.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */