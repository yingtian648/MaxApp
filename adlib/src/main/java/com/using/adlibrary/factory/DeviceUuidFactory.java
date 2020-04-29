/*    */ package com.using.adlibrary.factory;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.SharedPreferences;
/*    */ import com.orhanobut.logger.Logger;
/*    */ import com.using.adlibrary.AdEngine;
/*    */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
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
/*    */ public class DeviceUuidFactory
/*    */ {
/* 20 */   private String uuid = null;
/*    */   
/*    */   private static DeviceUuidFactory instance;
/*    */   
/*    */   public static DeviceUuidFactory getInstance() {
/* 25 */     if (instance == null) {
/* 26 */       instance = new DeviceUuidFactory(AdEngine.getInstances().getApplicationContext());
/*    */     }
/* 28 */     return instance;
/*    */   }
/*    */   
/*    */   public DeviceUuidFactory(Context context) {
/* 32 */     if (this.uuid == null) {
/* 33 */       synchronized (DeviceUuidFactory.class) {
/* 34 */         if (this.uuid == null) {
/* 35 */           String PREFS_FILE = "device_id.xml";
/* 36 */           SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
/* 37 */           String PREFS_DEVICE_ID = "device_id";
/* 38 */           String id = prefs.getString(PREFS_DEVICE_ID, null);
/*    */           
/* 40 */           if (id != null) {
/*    */             
/* 42 */             this.uuid = id;
/* 43 */             Logger.e("DeviceUuidFactory的存储在pres文件中的id" + this.uuid, new Object[0]);
/*    */           } else {
/*    */             
/* 46 */             String serialNumber = HwInterfaceManager.getInstance().getdeviceSN();
/* 47 */             if (serialNumber.length() != 0 && !serialNumber.equals("unknown")) {
/* 48 */               this.uuid = serialNumber;
/* 49 */               Logger.e("DeviceUuidFactory的设备序列号 :" + this.uuid, new Object[0]);
/*    */             } 
/*    */             
/* 52 */             prefs.edit().putString(PREFS_DEVICE_ID, this.uuid).apply();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public String getDeviceUuid() {
/* 60 */     if (this.uuid != null) {
/* 61 */       return this.uuid.toUpperCase();
/*    */     }
/*    */     
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\factory\DeviceUuidFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */