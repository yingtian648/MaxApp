/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Base64;
/*     */ import android.util.Log;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
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
/*     */ public class ImageBase64Utils
/*     */ {
/*     */   public static String imageToBase64(String path) {
/*  29 */     if (TextUtils.isEmpty(path)) {
/*  30 */       return null;
/*     */     }
/*  32 */     InputStream is = null;
/*  33 */     byte[] data = null;
/*  34 */     String result = null;
/*     */     try {
/*  36 */       is = new FileInputStream(path);
/*     */       
/*  38 */       data = new byte[is.available()];
/*     */       
/*  40 */       is.read(data);
/*     */       
/*  42 */       result = Base64.encodeToString(data, 0);
/*  43 */     } catch (IOException e) {
/*  44 */       e.printStackTrace();
/*     */     } finally {
/*  46 */       if (null != is) {
/*     */         try {
/*  48 */           is.close();
/*  49 */         } catch (IOException e) {
/*  50 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  55 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean base64ToFile(String base64Str, String path) {
/*  66 */     byte[] data = Base64.decode(base64Str, 0);
/*  67 */     for (int i = 0; i < data.length; i++) {
/*  68 */       if (data[i] < 0)
/*     */       {
/*  70 */         data[i] = (byte)(data[i] + 256);
/*     */       }
/*     */     } 
/*  73 */     OutputStream os = null;
/*     */     try {
/*  75 */       os = new FileOutputStream(path);
/*  76 */       os.write(data);
/*  77 */       os.flush();
/*  78 */       os.close();
/*  79 */       return true;
/*  80 */     } catch (FileNotFoundException e) {
/*  81 */       e.printStackTrace();
/*  82 */       return false;
/*  83 */     } catch (IOException e) {
/*  84 */       e.printStackTrace();
/*  85 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String bitmapToBase64(Bitmap bitmap) {
/*  98 */     String result = null;
/*  99 */     ByteArrayOutputStream baos = null;
/*     */     try {
/* 101 */       if (bitmap != null) {
/* 102 */         baos = new ByteArrayOutputStream();
/* 103 */         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
/*     */         
/* 105 */         baos.flush();
/* 106 */         baos.close();
/*     */         
/* 108 */         byte[] bitmapBytes = baos.toByteArray();
/* 109 */         result = Base64.encodeToString(bitmapBytes, 0);
/*     */       } 
/* 111 */     } catch (IOException e) {
/* 112 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 115 */         if (baos != null) {
/* 116 */           baos.flush();
/* 117 */           baos.close();
/*     */         } 
/* 119 */       } catch (IOException e) {
/* 120 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bitmap base64ToBitmap(String base64Data) {
/* 133 */     byte[] bytes = Base64.decode(base64Data, 0);
/* 134 */     return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBase64StrForNet(Bitmap bm) {
/* 153 */     byte[] data = bitmap2Bytes(bm);
/* 154 */     return "data:image/jpeg;base64," + Base64.encodeToString(data, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] bitmap2Bytes(Bitmap bm) {
/* 164 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 165 */     bm.compress(Bitmap.CompressFormat.JPEG, 90, baos);
/* 166 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bitmap getSmallBitmap(String filePath) {
/* 172 */     BitmapFactory.Options options = new BitmapFactory.Options();
/* 173 */     options.inJustDecodeBounds = true;
/* 174 */     BitmapFactory.decodeFile(filePath, options);
/*     */ 
/*     */     
/* 177 */     options.inSampleSize = calculateInSampleSize(options, 480, 800);
/*     */ 
/*     */     
/* 180 */     options.inJustDecodeBounds = false;
/*     */     
/* 182 */     return BitmapFactory.decodeFile(filePath, options);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
/* 187 */     int height = options.outHeight;
/* 188 */     int width = options.outWidth;
/* 189 */     int inSampleSize = 1;
/*     */     
/* 191 */     if (height > reqHeight || width > reqWidth) {
/* 192 */       int heightRatio = Math.round(height / reqHeight);
/* 193 */       int widthRatio = Math.round(width / reqWidth);
/* 194 */       inSampleSize = (heightRatio < widthRatio) ? heightRatio : widthRatio;
/*     */     } 
/* 196 */     return inSampleSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String bitmapToString(String filePath) {
/* 201 */     Bitmap bm = getSmallBitmap(filePath);
/* 202 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */ 
/*     */ 
/*     */     
/* 206 */     bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
/* 207 */     byte[] b = baos.toByteArray();
/* 208 */     Log.d("d", "压缩后的大小=" + b.length);
/* 209 */     return Base64.encodeToString(b, 0);
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\ImageBase64Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */