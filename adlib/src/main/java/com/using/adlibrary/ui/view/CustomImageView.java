/*     */ package com.using.adlibrary.ui.view;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.Canvas;
/*     */ import android.os.Handler;
/*     */ import android.util.AttributeSet;
/*     */ import android.widget.ImageView;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.config.ConfigManger;
/*     */ import com.using.adlibrary.ui.api.OnDisplayFinishListener;
/*     */ import com.using.adlibrary.utils.FileUtils;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressLint({"AppCompatCustomView"})
/*     */ public class CustomImageView
/*     */   extends ImageView
/*     */ {
/*  31 */   private Bitmap bitmap = null;
/*     */   private boolean mVisibility = false;
/*     */   private boolean isStart = false;
/*  34 */   private int image_index = 0;
/*     */   private List<String> imagePathList;
/*     */   private OnDisplayFinishListener displayFinishListener;
/*  37 */   private int loopCount = 0;
/*  38 */   private Handler handler = new Handler();
/*     */   
/*  40 */   private Runnable runnable = new Runnable()
/*     */     {
/*     */       public void run() {
/*     */         try {
/*  44 */           if (CustomImageView.this.mVisibility && CustomImageView.this.isStart) {
/*  45 */             if (CustomImageView.this.imagePathList != null && CustomImageView.this.imagePathList.size() > 0) {
/*  46 */               if (CustomImageView.this.image_index < CustomImageView.this.imagePathList.size()) {
/*     */                 
/*  48 */                 if (FileUtils.fileExists(CustomImageView.this.imagePathList.get(CustomImageView.this.image_index))) {
/*     */ 
/*     */ 
/*     */                   
/*  52 */                   Bitmap bitmap = CustomImageView.this.getPictureBitmap(CustomImageView.this.imagePathList.get(CustomImageView.this.image_index));
/*  53 */                   CustomImageView.this.setImageBitmap(bitmap);
/*     */                   
/*  55 */                   if (bitmap != null && !bitmap.isRecycled()) {
/*  56 */                     bitmap = null;
/*  57 */                     System.gc();
/*     */                   } 
/*  59 */                   Logger.e("当前展示第 " + CustomImageView.this.image_index + "张图片", new Object[0]);
/*     */                 } else {
/*  61 */                   Logger.e("此图片资源文件不存在" + (String)CustomImageView.this.imagePathList.get(CustomImageView.this.image_index), new Object[0]);
/*     */                 } 
/*  63 */                 CustomImageView.this.image_index++;
/*     */               } else {
/*  65 */                 CustomImageView.this.image_index = 0;
/*  66 */                 ++CustomImageView.this.loopCount;
/*  67 */                 CustomImageView.this.displayFinishListener.displayFinish("图片", CustomImageView.this.loopCount);
/*  68 */                 Logger.e("图片控件循环次数 " + CustomImageView.this.loopCount, new Object[0]);
/*     */               } 
/*  70 */               CustomImageView.this.handler.postDelayed(CustomImageView.this.runnable, (1000 * ConfigManger.getInstance().getImageDisplayIntervalTime()));
/*     */             } 
/*     */           } else {
/*     */             
/*  74 */             CustomImageView.this.loopCount = 0;
/*     */           } 
/*  76 */         } catch (Exception e) {
/*     */           
/*  78 */           CustomImageView.this.loopCount = 0;
/*  79 */           CustomImageView.this.image_index = 0;
/*  80 */           CustomImageView.this.handler.postDelayed(CustomImageView.this.runnable, (1000 * ConfigManger.getInstance().getImageDisplayIntervalTime()));
/*     */         } 
/*     */       }
/*     */     };
/*     */   
/*     */   public CustomImageView(Context context) {
/*  86 */     this(context, (AttributeSet)null);
/*  87 */     Logger.e("调用了CustomImageView构造方法", new Object[0]);
/*     */   }
/*     */   
/*     */   public CustomImageView(Context context, AttributeSet attrs) {
/*  91 */     this(context, attrs, 0);
/*     */   }
/*     */   
/*     */   public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
/*  95 */     super(context, attrs, defStyle);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startLoopImage() {
/* 100 */     this.isStart = true;
/* 101 */     if (this.bitmap != null) {
/* 102 */       setImageBitmap(this.bitmap);
/*     */     }
/*     */     
/* 105 */     this.handler.postDelayed(this.runnable, 0L);
/*     */   }
/*     */   
/*     */   public void stopLoopImage() {
/* 109 */     this.isStart = false;
/* 110 */     this.loopCount = 0;
/* 111 */     this.handler.removeCallbacks(this.runnable);
/*     */ 
/*     */     
/* 114 */     if (this.bitmap != null) {
/* 115 */       this.bitmap = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 121 */     super.onDraw(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onWindowVisibilityChanged(int visibility) {
/* 126 */     super.onWindowVisibilityChanged(visibility);
/* 127 */     if (visibility == 0) {
/* 128 */       this.mVisibility = true;
/*     */       
/* 130 */       Logger.e("CustomImageView   可见", new Object[0]);
/* 131 */     } else if (visibility == 4 || visibility == 8) {
/* 132 */       this.mVisibility = false;
/* 133 */       Logger.e("CustomImageView  不可见", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDetachedFromWindow() {
/* 140 */     super.onDetachedFromWindow();
/* 141 */     Logger.e("onDetachedFromWindow  当view离开附着的窗口时触发", new Object[0]);
/*     */   }
/*     */   
/*     */   public void addImagePathList(List<String> imagePathList) {
/* 145 */     this.imagePathList = imagePathList;
/*     */     try {
/* 147 */       Logger.e("imagePathList:   size:   " + imagePathList.size(), new Object[0]);
/*     */       
/* 149 */       if (imagePathList.size() > 0) {
/* 150 */         BitmapFactory.Options options = new BitmapFactory.Options();
/* 151 */         options.inSampleSize = 8;
/* 152 */         options.inPreferredConfig = Bitmap.Config.ALPHA_8;
/* 153 */         options.inPurgeable = true;
/* 154 */         options.inInputShareable = true;
/*     */         try {
/* 156 */           InputStream inputStream = new FileInputStream(imagePathList.get(0));
/* 157 */           this.bitmap = BitmapFactory.decodeStream(inputStream, null, options);
/* 158 */         } catch (FileNotFoundException e) {
/* 159 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/* 162 */     } catch (Exception e) {
/* 163 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Bitmap getPictureBitmap(String path) {
/*     */     try {
/* 170 */       InputStream inputStream = new FileInputStream(path);
/*     */       
/* 172 */       BitmapFactory.Options options = new BitmapFactory.Options();
/* 173 */       byte[] bytes = readStream(inputStream);
/* 174 */       options.inSampleSize = 2;
/* 175 */       options.inJustDecodeBounds = false;
/* 176 */       Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
/* 177 */       bytes = null;
/* 178 */       return bitmap;
/* 179 */     } catch (Exception e) {
/* 180 */       e.printStackTrace();
/*     */ 
/*     */       
/* 183 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] readStream(InputStream inStream) throws Exception {
/* 191 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 192 */     byte[] buffer = new byte[1024];
/* 193 */     int len = 0;
/* 194 */     while ((len = inStream.read(buffer)) != -1) {
/* 195 */       outStream.write(buffer, 0, len);
/*     */     }
/* 197 */     outStream.close();
/* 198 */     inStream.close();
/* 199 */     return outStream.toByteArray();
/*     */   }
/*     */   
/*     */   public void setDisplayFinishListener(OnDisplayFinishListener listener) {
/* 203 */     this.displayFinishListener = listener;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\view\CustomImageView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */