/*     */ package com.using.adlibrary.model;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.AssetFileDescriptor;
/*     */ import android.media.AudioAttributes;
/*     */ import android.media.MediaPlayer;
/*     */ import android.media.SoundPool;
/*     */ import android.os.Build;
/*     */ import java.io.IOException;
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
/*     */ public class PlaySoundManager
/*     */ {
/*  23 */   private MediaPlayer mediaPlayer = new MediaPlayer();
/*     */   private static PlaySoundManager instance;
/*     */   
/*     */   public static PlaySoundManager getInstance() {
/*  27 */     if (instance == null) {
/*  28 */       synchronized (PlaySoundManager.class) {
/*  29 */         if (instance == null) {
/*  30 */           instance = new PlaySoundManager();
/*     */         }
/*     */       } 
/*     */     }
/*  34 */     return instance;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  38 */     if (this.mediaPlayer != null) {
/*  39 */       this.mediaPlayer.reset();
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
/*     */   public void playSoundByMedia(Context context, int rawId, MediaPlayer.OnCompletionListener completionListener) {
/*     */     try {
/*  52 */       this.mediaPlayer.setAudioStreamType(3);
/*  53 */       this.mediaPlayer.setOnCompletionListener(completionListener);
/*     */       try {
/*  55 */         AssetFileDescriptor file = context.getResources().openRawResourceFd(rawId);
/*     */         
/*  57 */         this.mediaPlayer.setDataSource(file.getFileDescriptor(), file
/*  58 */             .getStartOffset(), file.getLength());
/*  59 */         file.close();
/*  60 */         this.mediaPlayer.setVolume(0.9F, 0.9F);
/*  61 */         this.mediaPlayer.prepare();
/*  62 */         this.mediaPlayer.start();
/*  63 */       } catch (IOException e) {
/*  64 */         this.mediaPlayer = null;
/*     */       } 
/*  66 */     } catch (Exception e) {
/*  67 */       e.printStackTrace();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(Context context, int rawId) {
/*     */     SoundPool soundPool;
/*  86 */     if (Build.VERSION.SDK_INT >= 21) {
/*  87 */       SoundPool.Builder builder = new SoundPool.Builder();
/*     */       
/*  89 */       builder.setMaxStreams(1);
/*     */       
/*  91 */       AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
/*     */       
/*  93 */       attrBuilder.setLegacyStreamType(3);
/*  94 */       builder.setAudioAttributes(attrBuilder.build());
/*  95 */       soundPool = builder.build();
/*     */     } else {
/*     */       
/*  98 */       soundPool = new SoundPool(1, 1, 5);
/*     */     } 
/*     */     
/* 101 */     soundPool.load(context, rawId, 1);
/* 102 */     soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
/*     */         {
/*     */           public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
/* 105 */             soundPool.play(1, 1.0F, 1.0F, 0, 0, 1.0F);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\model\PlaySoundManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */