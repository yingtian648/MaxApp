/*    */ package com.using.adlibrary.ui.view;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ import android.os.Handler;
/*    */ import android.os.Message;
/*    */ import android.widget.TextView;
/*    */ import com.orhanobut.logger.Logger;
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
/*    */ @SuppressLint({"AppCompatCustomView"})
/*    */ public class CustomTextView
/*    */   extends TextView
/*    */   implements Handler.Callback
/*    */ {
/*    */   private int textId;
/*    */   private List<String> textContentList;
/* 26 */   private Handler handler = new Handler(this);
/* 27 */   private int textIndex = 0;
/*    */   
/*    */   public CustomTextView(Context context) {
/* 30 */     super(context);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void start() {
/* 52 */     this.handler.sendEmptyMessageDelayed(1, 0L);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean handleMessage(Message msg) {
/* 57 */     if (this.textIndex < this.textContentList.size()) {
/* 58 */       setText(this.textContentList.get(this.textIndex));
/* 59 */       Logger.e("CustomImageView  切换图片" + this.textIndex, new Object[0]);
/*    */       
/* 61 */       this.textIndex++;
/*    */     } else {
/* 63 */       this.textIndex = 0;
/*    */     } 
/* 65 */     this.handler.sendEmptyMessageDelayed(1, 6000L);
/* 66 */     return false;
/*    */   }
/*    */   
/*    */   public int getTextId() {
/* 70 */     return this.textId;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\view\CustomTextView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */