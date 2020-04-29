/*     */ package com.using.adlibrary.factory;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.media.AudioManager;
/*     */ import android.net.ConnectivityManager;
/*     */ import android.net.NetworkInfo;
/*     */ import android.net.wifi.WifiInfo;
/*     */ import android.net.wifi.WifiManager;
/*     */ import android.os.Build;
/*     */ import android.telephony.TelephonyManager;
/*     */ import android.util.DisplayMetrics;
/*     */ import com.google.gson.Gson;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.AdEngine;
/*     */ import com.using.adlibrary.bean.data.DMDeviceRegData;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.usw.hardwarelibrary.hardware.HwInterfaceManager;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeviceInfoFactory
/*     */ {
/*  40 */   private DMDeviceRegData deviceInfo = new DMDeviceRegData();
/*     */   private Context context;
/*     */   
/*     */   private DeviceInfoFactory(Context context) {
/*  44 */     this.context = context;
/*     */     
/*  46 */     String deviceId = HwInterfaceManager.getInstance().getdeviceSN();
/*  47 */     Logger.e("DeviceInfoFactory", new Object[] { "DeviceInfoFactory 的设备序列号:" + deviceId });
/*  48 */     this.deviceInfo.setDeviceId(deviceId);
/*  49 */     this.deviceInfo.setDeviceIdS(deviceId);
/*     */   }
/*     */   
/*     */   public static DeviceInfoFactory getInstance() {
/*  53 */     return new DeviceInfoFactory(AdEngine.getInstances().getApplicationContext());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJsonDeviceInfo() {
/*     */     try {
/*  59 */       String mac = getWifiMac(this.context);
/*  60 */       this.deviceInfo.setMacAddress(mac);
/*     */       
/*  62 */       String[] cpuInfo = getCpuInfo();
/*  63 */       this.deviceInfo.setCpuModel(cpuInfo[0]);
/*     */       
/*  65 */       String screenResolution = getScreenResolution();
/*  66 */       this.deviceInfo.setScreenResolution(screenResolution);
/*     */       
/*  68 */       String ip = getIPAddress(this.context);
/*  69 */       this.deviceInfo.setIp(ip);
/*     */       
/*  71 */       this.deviceInfo.setBoardModel(Build.BOARD);
/*  72 */       this.deviceInfo.setCompany("unknow");
/*  73 */       this.deviceInfo.setCpuNum("unknow");
/*  74 */       this.deviceInfo.setDeviceName("unknow");
/*  75 */       this.deviceInfo.setModel(Build.MODEL);
/*  76 */       this.deviceInfo.setPort("8080");
/*  77 */       this.deviceInfo.setScreenSize("unknow");
/*  78 */       this.deviceInfo.setRamSize("unknow");
/*     */ 
/*     */       
/*  81 */       int rebootHour = ConfigManger.getInstance().getRebootHour();
/*  82 */       this.deviceInfo.setVoltage(String.valueOf(rebootHour));
/*     */       
/*  84 */       int volume = ConfigManger.getInstance().getDeviceVolume();
/*  85 */       this.deviceInfo.setRamModel(String.valueOf(volume));
/*     */       
/*  87 */       getApkVersion();
/*  88 */       getIccid();
/*     */       
/*  90 */       Gson gson = new Gson();
/*  91 */       String jsonString = gson.toJson(this.deviceInfo);
/*  92 */       Logger.e("DeviceInfoFactory", new Object[] { "deviceInfo信息json数据： " + jsonString });
/*  93 */       return jsonString;
/*  94 */     } catch (Exception e) {
/*  95 */       e.printStackTrace();
/*     */       
/*  97 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void getApkVersion() {
/* 104 */     String ADversion = "";
/* 105 */     int a = 0;
/*     */     try {
/* 107 */       ADversion = getVersionName(this.context, "com.usw.adplayer");
/*     */       
/* 109 */       AudioManager audioManager = (AudioManager)this.context.getSystemService("audio");
/*     */ 
/*     */       
/* 112 */       int max = audioManager.getStreamMaxVolume(3);
/* 113 */       int num = audioManager.getStreamVolume(3);
/*     */       
/* 115 */       a = num * 100 / max;
/* 116 */     } catch (Exception e) {
/*     */       
/* 118 */       if (ADversion.isEmpty()) {
/* 119 */         ADversion = "";
/*     */       }
/*     */     } 
/*     */     
/* 123 */     this.deviceInfo.setScreenAngle(ADversion);
/* 124 */     this.deviceInfo.setRamModel(a + "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getIccid() {
/* 131 */     String iccidcat = null;
/*     */     try {
/* 133 */       TelephonyManager telManager = (TelephonyManager)this.context.getSystemService("phone");
/* 134 */       if (telManager != null) {
/* 135 */         String iccid = telManager.getSimSerialNumber();
/*     */         
/* 137 */         if (iccid != null) {
/* 138 */           iccidcat = iccid.substring(0, iccid.length() - 1);
/*     */         } else {
/* 140 */           iccidcat = "";
/*     */         } 
/*     */       } 
/* 143 */     } catch (Exception e) {
/* 144 */       iccidcat = "";
/* 145 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 148 */     this.deviceInfo.setPowerConsumption(iccidcat);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getWifiMac(Context ctx) {
/* 153 */     WifiManager wifi = (WifiManager)ctx.getApplicationContext().getSystemService("wifi");
/* 154 */     WifiInfo info = wifi.getConnectionInfo();
/* 155 */     String str = info.getMacAddress();
/* 156 */     if (str == null) {
/* 157 */       str = "";
/*     */     }
/* 159 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] getCpuInfo() {
/* 164 */     String str1 = "/proc/cpuinfo";
/* 165 */     String str2 = "";
/* 166 */     String[] cpuInfo = { "", "" };
/*     */     
/*     */     try {
/* 169 */       FileReader fr = new FileReader(str1);
/* 170 */       BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
/* 171 */       str2 = localBufferedReader.readLine();
/* 172 */       String[] arrayOfString = str2.split("\\s+");
/* 173 */       for (int i = 2; i < arrayOfString.length; i++) {
/* 174 */         cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
/*     */       }
/* 176 */       str2 = localBufferedReader.readLine();
/* 177 */       arrayOfString = str2.split("\\s+");
/* 178 */       cpuInfo[1] = cpuInfo[1] + arrayOfString[2];
/* 179 */       localBufferedReader.close();
/* 180 */     } catch (IOException e) {
/* 181 */       e.printStackTrace();
/*     */     } 
/* 183 */     return cpuInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getScreenResolution() {
/* 189 */     DisplayMetrics dm2 = this.context.getResources().getDisplayMetrics();
/* 190 */     return dm2.widthPixels + "*" + dm2.heightPixels;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getIPAddress(Context context) {
/* 196 */     NetworkInfo info = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
/* 197 */     if (info != null && info.isConnected()) {
/* 198 */       if (info.getType() == 0) {
/*     */         try {
/* 200 */           for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
/* 201 */             NetworkInterface intf = en.nextElement();
/* 202 */             for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
/* 203 */               InetAddress inetAddress = enumIpAddr.nextElement();
/* 204 */               if (!inetAddress.isLoopbackAddress() && inetAddress instanceof java.net.Inet4Address) {
/* 205 */                 return inetAddress.getHostAddress();
/*     */               }
/*     */             } 
/*     */           } 
/* 209 */         } catch (SocketException e) {
/* 210 */           e.printStackTrace();
/*     */         }
/*     */       
/* 213 */       } else if (info.getType() == 1) {
/* 214 */         WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
/* 215 */         WifiInfo wifiInfo = wifiManager.getConnectionInfo();
/* 216 */         String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
/* 217 */         return ipAddress;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 222 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String intIP2StringIP(int ip) {
/* 229 */     return (ip & 0xFF) + "." + (ip >> 8 & 0xFF) + "." + (ip >> 16 & 0xFF) + "." + (ip >> 24 & 0xFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getVersionName(Context context, String packageName) throws Exception {
/* 237 */     PackageManager packageManager = context.getPackageManager();
/* 238 */     PackageInfo packInfo = packageManager.getPackageInfo(packageName, 0);
/* 239 */     String version = packInfo.versionName;
/* 240 */     return version;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\factory\DeviceInfoFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */