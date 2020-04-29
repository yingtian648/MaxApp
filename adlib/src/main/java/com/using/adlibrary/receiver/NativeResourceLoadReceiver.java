/*    */ package com.using.adlibrary.receiver;
/*    */ 
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.text.TextUtils;
/*    */ import android.widget.Toast;
/*    */ import com.orhanobut.logger.Logger;
/*    */ import com.using.adlibrary.model.NativeModel;
/*    */ import com.using.adlibrary.utils.ToastUtils;
/*    */ 
/*    */ 
/*    */ public class NativeResourceLoadReceiver
/*    */   extends BroadcastReceiver
/*    */ {
/* 16 */   private String TAG = "NativeResourceLoad";
/*    */ 
/*    */   
/*    */   public void onReceive(Context context, Intent intent) {
/* 20 */     Logger.i("有广播", new Object[0]);
/*    */     try {
/* 22 */       String path = null;
/* 23 */       String action = intent.getAction();
/* 24 */       if (intent.getData() != null) {
/* 25 */         path = intent.getData().getPath();
/*    */       }
/* 27 */       if (!TextUtils.isEmpty(path)) {
/* 28 */         if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
/* 29 */           Logger.e("usb", new Object[] { "usb设备已卸载：" + path });
/* 30 */           ToastUtils.showToast(context, "usb设备已卸载");
/* 31 */         } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
/* 32 */           Logger.e("usb设备已加载：" + path, new Object[0]);
/* 33 */           ToastUtils.showToast(context, "usb设备已加载");
/* 34 */           NativeModel.copyMaterial(path);
/* 35 */           Toast.makeText(context, "数据拷贝完成", 0).show();
/*    */           
/* 37 */           Intent intent2 = new Intent("PLAY_LIST_DATA");
/* 38 */           intent2.putExtra("PLAY_LIST_DATA", "");
/* 39 */           context.sendBroadcast(intent2);
/* 40 */           Logger.e("数据拷贝完成", new Object[0]);
/*    */         } 
/*    */       }
/* 43 */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\receiver\NativeResourceLoadReceiver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */