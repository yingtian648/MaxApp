/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.graphics.Bitmap;
/*    */ import android.graphics.Matrix;
/*    */ import java.io.BufferedOutputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
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
/*    */ public class ImageCompressUtils
/*    */ {
/*    */   public static int getCompressQuality(Bitmap bitmap, int size) {
/* 27 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 28 */     if (bitmap == null) {
/* 29 */       return 100;
/*    */     }
/* 31 */     bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
/* 32 */     int quality = 100;
/* 33 */     while ((baos.toByteArray()).length / 1024 > size) {
/* 34 */       baos.reset();
/* 35 */       quality -= 2;
/* 36 */       if (quality <= 0) return 0; 
/* 37 */       bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
/*    */     } 
/* 39 */     return quality;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Bitmap rotateBitmap(Bitmap bm, int angle) {
/* 44 */     Matrix m = new Matrix();
/* 45 */     m.setRotate(angle, bm.getWidth() / 2.0F, bm.getHeight() / 2.0F);
/*    */     
/*    */     try {
/* 48 */       Bitmap bm1 = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
/* 49 */       return bm1;
/* 50 */     } catch (OutOfMemoryError ex) {
/* 51 */       ex.fillInStackTrace();
/*    */       
/* 53 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static File convertBmpToFile(Bitmap bm, String fullFileName) throws IOException {
/* 64 */     File dirFile = new File(fullFileName);
/* 65 */     if (!dirFile.exists()) {
/* 66 */       dirFile.mkdir();
/*    */     }
/* 68 */     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));
/* 69 */     bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
/* 70 */     bos.flush();
/* 71 */     bos.close();
/* 72 */     return dirFile;
/*    */   }
/*    */   
/*    */   public static void recyleBitmap(Bitmap bitmap) {
/* 76 */     bitmap.recycle();
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\ImageCompressUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */