/*    */ package com.using.adlibrary.receiver;
/*    */ 
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
/*    */ import java.util.TimerTask;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpdateReceiver
/*    */   extends BroadcastReceiver
/*    */ {
/*    */   private boolean isUpdate = false;
/*    */   
/*    */   public void onReceive(Context context, Intent intent) {}
/*    */   
/*    */   public class RebootTimeTask
/*    */     extends TimerTask
/*    */   {
/*    */     public void run() {
/* 62 */       HwInterfaceManager.getInstance().reboot();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\receiver\UpdateReceiver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */