/*     */ package com.using.adlibrary.ui.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.media.MediaMetadataRetriever;
/*     */ import android.media.MediaPlayer;
/*     */ import android.util.AttributeSet;
/*     */ import android.widget.VideoView;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.ui.api.OnDisplayFinishListener;
/*     */ import com.using.adlibrary.utils.FileUtils;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomVideoView
/*     */   extends VideoView
/*     */ {
/*  26 */   private int mNextVideo = 0;
/*  27 */   private int loopCount = 0;
/*     */   
/*     */   private boolean mVisibility = false;
/*     */   
/*     */   private boolean isStart = false;
/*     */   private MediaMetadataRetriever retriever;
/*     */   private List<String> mListVideoPath;
/*     */   private OnDisplayFinishListener displayFinishListener;
/*     */   
/*     */   public CustomVideoView(Context context) {
/*  37 */     super(context);
/*  38 */     Logger.e("CustomVideoView创建", new Object[0]);
/*     */     
/*  40 */     MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener()
/*     */       {
/*     */         public void onCompletion(MediaPlayer mp) {
/*     */           try {
/*  44 */             if (CustomVideoView.this.mVisibility && CustomVideoView.this.isStart) {
/*  45 */               ++CustomVideoView.this.mNextVideo;
/*  46 */               if (CustomVideoView.this.mListVideoPath != null && CustomVideoView.this.mListVideoPath.size() > 0) {
/*  47 */                 if (CustomVideoView.this.mNextVideo < CustomVideoView.this.mListVideoPath.size()) {
/*     */                   
/*  49 */                   if (FileUtils.fileExists(CustomVideoView.this.mListVideoPath.get(CustomVideoView.this.mNextVideo))) {
/*  50 */                     CustomVideoView.this.setVideoPath(CustomVideoView.this.mListVideoPath.get(CustomVideoView.this.mNextVideo));
/*  51 */                     Logger.e("当前播放的视频地址为： " + (String)CustomVideoView.this.mListVideoPath.get(CustomVideoView.this.mNextVideo), new Object[0]);
/*  52 */                     CustomVideoView.this.start();
/*     */                   } 
/*     */                 } else {
/*  55 */                   CustomVideoView.this.mNextVideo = 0;
/*  56 */                   if (CustomVideoView.this.displayFinishListener != null) {
/*  57 */                     CustomVideoView.this.displayFinishListener.displayFinish("视频", ++CustomVideoView.this.loopCount);
/*  58 */                     Logger.e("视频循环次数 " + CustomVideoView.this.loopCount, new Object[0]);
/*  59 */                     CustomVideoView.this.loopCount = 0;
/*     */                   } 
/*     */ 
/*     */                   
/*  63 */                   if (CustomVideoView.this.mVisibility && CustomVideoView.this.isStart) {
/*  64 */                     CustomVideoView.this.setVideoPath(CustomVideoView.this.mListVideoPath.get(CustomVideoView.this.mNextVideo));
/*  65 */                     CustomVideoView.this.start();
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             } 
/*  70 */           } catch (Exception e) {
/*  71 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/*  76 */     setOnCompletionListener(listener);
/*  77 */     MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener()
/*     */       {
/*     */         public boolean onError(MediaPlayer mp, int what, int extra) {
/*  80 */           mp.stop();
/*  81 */           Logger.e("播放异常！停止播放 what   " + what + " extra: " + extra, new Object[0]);
/*  82 */           if (CustomVideoView.this.displayFinishListener != null) {
/*  83 */             CustomVideoView.this.displayFinishListener.displayFinish("视频", 1);
/*     */           }
/*  85 */           return true;
/*     */         }
/*     */       };
/*  88 */     setOnErrorListener(errorListener);
/*  89 */     MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener()
/*     */       {
/*     */         public void onPrepared(MediaPlayer mp) {
/*  92 */           mp.setOnInfoListener(new MediaPlayer.OnInfoListener()
/*     */               {
/*     */                 public boolean onInfo(MediaPlayer mp, int what, int extra)
/*     */                 {
/*  96 */                   if (what == 3) {
/*  97 */                     CustomVideoView.this.setBackgroundColor(0);
/*     */                   }
/*  99 */                   return false;
/*     */                 }
/*     */               });
/*     */         }
/*     */       };
/* 104 */     setOnPreparedListener(preparedListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomVideoView(Context context, AttributeSet attrs) {
/* 109 */     super(context, attrs);
/*     */   }
/*     */   
/*     */   public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
/* 113 */     super(context, attrs, defStyleAttr);
/*     */   }
/*     */   
/*     */   public void setVideoPathList(List<String> listVideoPath) {
/* 117 */     this.mListVideoPath = listVideoPath;
/*     */     try {
/* 119 */       if (this.mListVideoPath != null && this.mListVideoPath.size() > 0) {
/* 120 */         setVideoPath(this.mListVideoPath.get(0));
/* 121 */         this.retriever = new MediaMetadataRetriever();
/* 122 */         this.retriever.setDataSource(this.mListVideoPath.get(0), new HashMap<>());
/* 123 */         Bitmap bitmap = this.retriever.getFrameAtTime(0L, 2);
/* 124 */         BitmapDrawable drawable = new BitmapDrawable(bitmap);
/* 125 */         setBackground((Drawable)drawable);
/*     */       } 
/* 127 */     } catch (Exception e) {
/* 128 */       e.printStackTrace();
/*     */     } finally {
/* 130 */       this.retriever.release();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onWindowVisibilityChanged(int visibility) {
/* 137 */     super.onWindowVisibilityChanged(visibility);
/* 138 */     Logger.e("CustomVideoView onWindowVisibilityChanged", new Object[0]);
/* 139 */     if (visibility == 0) {
/* 140 */       this.mVisibility = true;
/* 141 */       Logger.e("CustomVideoView   可见", new Object[0]);
/* 142 */     } else if (visibility == 4 || visibility == 8) {
/* 143 */       this.mVisibility = false;
/* 144 */       Logger.e("CustomVideoView  不可见", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startS() {
/*     */     try {
/* 151 */       if (this.mListVideoPath != null && this.mListVideoPath.size() > 0 && this.mNextVideo < this.mListVideoPath.size()) {
/* 152 */         Logger.e("当前 mNextVideo的值为：" + this.mNextVideo + "当前的地址为：" + (String)this.mListVideoPath.get(this.mNextVideo), new Object[0]);
/* 153 */         setVideoPath(this.mListVideoPath.get(this.mNextVideo));
/* 154 */         setZOrderOnTop(true);
/* 155 */         start();
/*     */       } 
/* 157 */     } catch (Exception e) {
/* 158 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void start() {
/* 164 */     super.start();
/* 165 */     this.isStart = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resume() {
/* 170 */     super.resume();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause() {
/* 175 */     super.pause();
/* 176 */     this.isStart = false;
/* 177 */     this.loopCount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopPlayback() {
/* 182 */     super.stopPlayback();
/* 183 */     this.loopCount = 0;
/* 184 */     this.mNextVideo = 0;
/*     */   }
/*     */   
/*     */   public void setDisplayFinishListener(OnDisplayFinishListener listener) {
/* 188 */     this.displayFinishListener = listener;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\view\CustomVideoView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */