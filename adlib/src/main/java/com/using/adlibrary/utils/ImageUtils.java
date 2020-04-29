/*    */ package com.using.adlibrary.utils;
/*    */ 
/*    */ import android.graphics.Bitmap;
/*    */ import android.graphics.BitmapFactory;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageUtils
/*    */ {
/*    */   public static Bitmap fileToBitmap(String path) {
/* 17 */     Bitmap bitmap = null;
/* 18 */     BitmapFactory.Options options = new BitmapFactory.Options();
/* 19 */     options.inSampleSize = 8;
/* 20 */     options.inPreferredConfig = Bitmap.Config.RGB_565;
/* 21 */     options.inPurgeable = true;
/* 22 */     options.inInputShareable = true;
/*    */     try {
/* 24 */       InputStream inputStream = new FileInputStream(path);
/* 25 */       bitmap = BitmapFactory.decodeStream(inputStream, null, options);
/* 26 */     } catch (FileNotFoundException e) {
/* 27 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 30 */     return bitmap;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\ImageUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */