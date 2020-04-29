/*     */ package com.using.adlibrary.ui.view;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.media.MediaPlayer;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.SurfaceHolder;
/*     */ import android.view.SurfaceView;
/*     */ import android.view.View;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ @SuppressLint({"ViewConstructor"})
/*     */ public class CustomSurfaceView
/*     */   extends SurfaceView
/*     */ {
/*     */   private int surfaceId;
/*     */   private SurfaceHolder surfaceHolder;
/*     */   private MediaPlayer player;
/*     */   private List<String> videoPathList;
/*  23 */   private int video_index = 0;
/*     */   
/*  25 */   private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener()
/*     */     {
/*     */       public void onCompletion(MediaPlayer mediaPlayer) {
/*  28 */         Logger.e("一个视频播放完毕", new Object[0]);
/*     */         
/*     */         try {
/*  31 */           Logger.e("当前播放完成的视频的索引：" + CustomSurfaceView.this.video_index, new Object[0]);
/*  32 */           CustomSurfaceView.this.video_index++;
/*  33 */           Logger.e("下一个视频的索引：" + CustomSurfaceView.this.video_index, new Object[0]);
/*  34 */           if (CustomSurfaceView.this.video_index < CustomSurfaceView.this.videoPathList.size()) {
/*  35 */             Logger.e("下一个视频存在，开始播放,地址为：" + (String)CustomSurfaceView.this.videoPathList.get(CustomSurfaceView.this.video_index), new Object[0]);
/*  36 */             CustomSurfaceView.this.setPlayer(CustomSurfaceView.this.videoPathList.get(CustomSurfaceView.this.video_index));
/*     */           } else {
/*  38 */             CustomSurfaceView.this.video_index = 0;
/*  39 */             if (CustomSurfaceView.this.videoListener != null) {
/*  40 */               Logger.e("下一个视频不存在,一轮视频播放完毕", new Object[0]);
/*  41 */               CustomSurfaceView.this.videoListener.playFinish("一轮视频播放完毕，播放的视频个数： " + CustomSurfaceView.this.video_index);
/*     */             } else {
/*     */               
/*  44 */               Logger.e("没有设置监听，循环播放视频", new Object[0]);
/*  45 */               CustomSurfaceView.this.setPlayer(CustomSurfaceView.this.videoPathList.get(CustomSurfaceView.this.video_index));
/*     */             } 
/*     */           } 
/*  48 */         } catch (IOException e) {
/*  49 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     };
/*     */   private OnVideoListener videoListener;
/*     */   
/*     */   public CustomSurfaceView(Context context, List<String> videoPathList) {
/*  56 */     super(context);
/*  57 */     this.videoPathList = videoPathList;
/*     */ 
/*     */     
/*  60 */     this.surfaceHolder = getHolder();
/*  61 */     this.surfaceHolder.addCallback(new MediaCallback());
/*  62 */     this.surfaceHolder.setFixedSize(1920, 1080);
/*     */     
/*  64 */     setFocusable(true);
/*  65 */     setFocusableInTouchMode(true);
/*  66 */     setKeepScreenOn(true);
/*  67 */     Logger.e(" CustomSurfaceView----构造函数1-----执行完毕", new Object[0]);
/*  68 */     setVisibility(8);
/*     */   }
/*     */   
/*     */   public CustomSurfaceView(Context context) {
/*  72 */     super(context, null);
/*     */   }
/*     */   
/*     */   public CustomSurfaceView(Context context, AttributeSet attrs) {
/*  76 */     this(context, attrs, 0);
/*     */   }
/*     */   
/*     */   public CustomSurfaceView(Context context, AttributeSet attrs, int defStyle) {
/*  80 */     super(context, attrs, defStyle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/*  86 */     Logger.e("onMeasure surfaceView需要重新测量绘制", new Object[0]);
/*     */     
/*  88 */     int width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, 1073741824);
/*     */     
/*  90 */     int height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, 1073741824);
/*     */ 
/*     */     
/*  93 */     super.onMeasure(width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setPlayer(String videoPath) throws IOException {
/*  98 */     Logger.e("视频路径：  " + videoPath, new Object[0]);
/*  99 */     if (this.player == null) {
/* 100 */       this.player = new MediaPlayer();
/*     */     }
/* 102 */     this.player.reset();
/* 103 */     this.player.setDisplay(this.surfaceHolder);
/* 104 */     this.player.setAudioStreamType(3);
/* 105 */     this.player.setDataSource(videoPath);
/* 106 */     this.player.prepareAsync();
/*     */     
/* 108 */     this.player.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
/*     */         {
/*     */           public void onPrepared(MediaPlayer mediaPlayer) {
/* 111 */             Logger.e("player准备完毕，开始播放 player.start()", new Object[0]);
/* 112 */             CustomSurfaceView.this.player.start();
/*     */           }
/*     */         });
/* 115 */     this.player.setOnCompletionListener(this.completionListener);
/*     */   }
/*     */   
/*     */   private void start() {
/*     */     try {
/* 120 */       setPlayer(this.videoPathList.get(0));
/* 121 */     } catch (IOException e) {
/* 122 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private class MediaCallback
/*     */     implements SurfaceHolder.Callback {
/*     */     private MediaCallback() {}
/*     */     
/*     */     public void surfaceCreated(SurfaceHolder surfaceHolder) {
/* 131 */       CustomSurfaceView.this.player = new MediaPlayer();
/* 132 */       CustomSurfaceView.this.start();
/* 133 */       Logger.e("启动了surfaceCreated", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
/* 138 */       Logger.e("调用了surfaceChanged surface发生了改变", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
/* 143 */       if (CustomSurfaceView.this.player != null) {
/* 144 */         if (CustomSurfaceView.this.player.isPlaying()) {
/* 145 */           CustomSurfaceView.this.player.stop();
/*     */         }
/* 147 */         CustomSurfaceView.this.player.release();
/*     */       } 
/* 149 */       Logger.e("调用了 surfaceDestroyed", new Object[0]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVideoListener(OnVideoListener listener) {
/* 155 */     this.videoListener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSurfaceId() {
/* 163 */     return this.surfaceId;
/*     */   }
/*     */   
/*     */   public void setSurfaceId(int surfaceId) {
/* 167 */     this.surfaceId = surfaceId;
/*     */   }
/*     */   
/*     */   public static interface OnVideoListener {
/*     */     void playFinish(String param1String);
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\view\CustomSurfaceView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */