/*    */ package com.using.adlibrary.receiver;
/*    */ 
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.content.IntentFilter;
/*    */ import com.orhanobut.logger.Logger;
/*    */ import com.using.adlibrary.utils.LL;
/*    */ import com.using.adlibrary.utils.NetWorkUtils;
/*    */ 
/*    */ 
/*    */ public class NetStateBroadcastReceiver
/*    */   extends BroadcastReceiver
/*    */ {
/*    */   private IntentFilter mIntentFilter;
/*    */   
/*    */   public NetStateBroadcastReceiver() {
/* 18 */     setIntentFilter();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onReceive(Context context, Intent intent) {
/*    */     try {
/* 24 */       String action = intent.getAction();
/* 25 */       LL.i("TeAG", "广播的action： " + action);
/* 26 */       if (action != null) {
/* 27 */         switch (action) {
/*    */           case "android.net.conn.CONNECTIVITY_CHANGE":
/* 29 */             Logger.e("NetStateBroadcastReceiver   AdCloud  网络发生了变化。。。", new Object[0]);
/* 30 */             if (NetWorkUtils.isNetworkConnected(context)) {
/* 31 */               Logger.e("NetStateBroadcastReceiver   AdCloud  网络发生了变化   网络可用。。。", new Object[0]);
/* 32 */               NetWorkUtils.networkState = true; break;
/*    */             } 
/* 34 */             Logger.e("NetStateBroadcastReceiver   AdCloud   网络发生了变化    网络不可用。。。", new Object[0]);
/* 35 */             NetWorkUtils.networkState = false;
/*    */             break;
/*    */         } 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       }
/* 43 */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setIntentFilter() {
/* 52 */     this.mIntentFilter = new IntentFilter();
/* 53 */     this.mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
/*    */   }
/*    */   
/*    */   public IntentFilter getIntentFilter() {
/* 57 */     return this.mIntentFilter;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\receiver\NetStateBroadcastReceiver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */