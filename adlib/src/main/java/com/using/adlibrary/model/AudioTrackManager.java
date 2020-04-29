/*     */ package com.using.adlibrary.model;
/*     */ 
/*     */ import android.media.AudioTrack;
/*     */ import android.os.Process;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
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
/*     */ 
/*     */ public class AudioTrackManager
/*     */ {
/*     */   private AudioTrack mAudioTrack;
/*     */   private DataInputStream mDis;
/*     */   private Thread mRecordThread;
/*     */   private boolean isStart = false;
/*     */   private static volatile AudioTrackManager mInstance;
/*     */   private static final int mStreamType = 3;
/*     */   private static final int mSampleRateInHz = 44100;
/*     */   private static final int mChannelConfig = 2;
/*     */   private static final int mAudioFormat = 2;
/*     */   private int mMinBufferSize;
/*  37 */   private static int mMode = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   Runnable recordRunnable;
/*     */ 
/*     */ 
/*     */   
/*     */   private void initData() {
/*  46 */     this.mMinBufferSize = AudioTrack.getMinBufferSize(44100, 2, 2);
/*     */ 
/*     */     
/*  49 */     this.mAudioTrack = new AudioTrack(3, 44100, 2, 2, this.mMinBufferSize, mMode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AudioTrackManager getInstance() {
/*  60 */     if (mInstance == null) {
/*  61 */       synchronized (AudioTrackManager.class) {
/*  62 */         if (mInstance == null) {
/*  63 */           mInstance = new AudioTrackManager();
/*     */         }
/*     */       } 
/*     */     }
/*  67 */     return mInstance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void destroyThread() {
/*     */     try {
/*  75 */       this.isStart = false;
/*  76 */       if (null != this.mRecordThread && Thread.State.RUNNABLE == this.mRecordThread.getState()) {
/*     */         try {
/*  78 */           Thread.sleep(500L);
/*  79 */           this.mRecordThread.interrupt();
/*  80 */         } catch (Exception e) {
/*  81 */           this.mRecordThread = null;
/*     */         } 
/*     */       }
/*  84 */       this.mRecordThread = null;
/*  85 */     } catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */     } finally {
/*  88 */       this.mRecordThread = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startThread() {
/*  96 */     destroyThread();
/*  97 */     this.isStart = true;
/*  98 */     if (this.mRecordThread == null) {
/*  99 */       this.mRecordThread = new Thread(this.recordRunnable);
/* 100 */       this.mRecordThread.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AudioTrackManager() {
/* 107 */     this.recordRunnable = new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/*     */           try {
/* 112 */             Process.setThreadPriority(-19);
/* 113 */             byte[] tempBuffer = new byte[AudioTrackManager.this.mMinBufferSize];
/* 114 */             int readCount = 0;
/* 115 */             while (AudioTrackManager.this.mDis.available() > 0) {
/* 116 */               readCount = AudioTrackManager.this.mDis.read(tempBuffer);
/* 117 */               if (readCount == -3 || readCount == -2) {
/*     */                 continue;
/*     */               }
/* 120 */               if (readCount != 0 && readCount != -1) {
/*     */                 
/* 122 */                 if (AudioTrackManager.this.mAudioTrack.getState() == 0) {
/* 123 */                   AudioTrackManager.this.initData();
/*     */                 }
/* 125 */                 AudioTrackManager.this.mAudioTrack.play();
/* 126 */                 AudioTrackManager.this.mAudioTrack.write(tempBuffer, 0, readCount);
/*     */               } 
/*     */             } 
/* 129 */             AudioTrackManager.this.stopPlay();
/* 130 */           } catch (Exception e) {
/* 131 */             e.printStackTrace();
/*     */           } 
/*     */         }
/*     */       };
/*     */     initData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setPath(String path) throws Exception {
/* 144 */     File file = new File(path);
/* 145 */     this.mDis = new DataInputStream(new FileInputStream(file));
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
/*     */   public void startPlay(String path) {
/*     */     try {
/* 162 */       setPath(path);
/* 163 */       startThread();
/*     */     
/*     */     }
/* 166 */     catch (Exception e) {
/* 167 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopPlay() {
/*     */     try {
/* 176 */       destroyThread();
/* 177 */       if (this.mAudioTrack != null) {
/* 178 */         if (this.mAudioTrack.getState() == 1) {
/* 179 */           this.mAudioTrack.stop();
/*     */         }
/* 181 */         if (this.mAudioTrack != null) {
/* 182 */           this.mAudioTrack.release();
/*     */         }
/*     */       } 
/* 185 */       if (this.mDis != null) {
/* 186 */         this.mDis.close();
/*     */       }
/* 188 */     } catch (Exception e) {
/* 189 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\model\AudioTrackManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */