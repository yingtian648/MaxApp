/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.media.AudioManager;
/*     */ import com.orhanobut.logger.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VoiceManager
/*     */ {
/*     */   private static final String TAG = "VoiceManager";
/*     */   Context context;
/*     */   AudioManager mAudioManager;
/*     */   
/*     */   public VoiceManager(Context context) {
/*  20 */     this.context = context;
/*  21 */     if (this.mAudioManager == null) {
/*  22 */       this.mAudioManager = (AudioManager)context.getApplicationContext().getSystemService("audio");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystemVoice(int size) {
/*  32 */     AudioManager mAudioManager = (AudioManager)this.context.getApplicationContext().getSystemService("audio");
/*     */     try {
/*  34 */       if (mAudioManager != null) {
/*  35 */         int max = mAudioManager.getStreamMaxVolume(3);
/*  36 */         int a = max * size / 100;
/*  37 */         Logger.e("音量----------" + a, new Object[0]);
/*  38 */         mAudioManager.setStreamVolume(3, a, 4);
/*     */         
/*  40 */         int a1 = mAudioManager.getStreamVolume(3);
/*  41 */         Logger.e("MAX  " + max + "当前音量为： " + a1, new Object[0]);
/*     */       } 
/*  43 */     } catch (Exception e) {
/*  44 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMediaVoice(int size) {
/*  50 */     AudioManager mAudioManager = (AudioManager)this.context.getApplicationContext().getSystemService("audio");
/*     */     try {
/*  52 */       if (mAudioManager != null) {
/*  53 */         int max = mAudioManager.getStreamMaxVolume(3);
/*  54 */         int a = max * size / 100;
/*  55 */         Logger.e("音量----------" + a, new Object[0]);
/*  56 */         mAudioManager.setStreamVolume(3, a, 4);
/*     */         
/*  58 */         int a1 = mAudioManager.getStreamVolume(3);
/*  59 */         Logger.e("MAX  " + max + "当前音量为： " + a1, new Object[0]);
/*     */       } 
/*  61 */     } catch (Exception e) {
/*  62 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVoide() {
/*  70 */     dealCallVoice(true);
/*  71 */     dealSystemVoice(true);
/*  72 */     dealRingVoice(true);
/*  73 */     dealMediaVoice(true);
/*  74 */     dealMessageVoice(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reduceVoide() {
/*  82 */     dealCallVoice(false);
/*  83 */     dealSystemVoice(false);
/*  84 */     dealRingVoice(false);
/*  85 */     dealMediaVoice(false);
/*  86 */     dealMessageVoice(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dealMessageVoice(boolean b) {
/*  95 */     if (this.mAudioManager == null) {
/*  96 */       this.mAudioManager = (AudioManager)this.context.getSystemService("audio");
/*     */     }
/*  98 */     int max = this.mAudioManager.getStreamMaxVolume(4);
/*  99 */     int current = this.mAudioManager.getStreamVolume(4);
/* 100 */     Logger.i("===处理前提示音音量====" + current + "  /" + max, new Object[0]);
/* 101 */     if (b) {
/* 102 */       if (current == max) {
/*     */         return;
/*     */       }
/* 105 */       this.mAudioManager.setStreamVolume(4, current + 1, 0);
/*     */     } else {
/* 107 */       if (current < 1) {
/*     */         return;
/*     */       }
/* 110 */       this.mAudioManager.setStreamVolume(4, current - 1, 0);
/*     */     } 
/* 112 */     int maxdeal = this.mAudioManager.getStreamMaxVolume(4);
/* 113 */     int currentdeal = this.mAudioManager.getStreamVolume(4);
/* 114 */     Logger.e("===处理前后提示音音量====" + currentdeal + "  /" + maxdeal, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dealMediaVoice(boolean b) {
/* 122 */     if (this.mAudioManager == null) {
/* 123 */       this.mAudioManager = (AudioManager)this.context.getSystemService("audio");
/*     */     }
/* 125 */     int mediamax = this.mAudioManager.getStreamMaxVolume(3);
/* 126 */     int mediacurrent = this.mAudioManager.getStreamVolume(3);
/* 127 */     Logger.i("===处理前媒体音量====" + mediacurrent + "  /" + mediamax, new Object[0]);
/* 128 */     if (b) {
/* 129 */       if (mediacurrent == mediamax) {
/*     */         return;
/*     */       }
/* 132 */       this.mAudioManager.setStreamVolume(3, mediacurrent + 1, 0);
/*     */     } else {
/* 134 */       if (mediacurrent < 1) {
/*     */         return;
/*     */       }
/* 137 */       this.mAudioManager.setStreamVolume(3, mediacurrent - 1, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dealRingVoice(boolean b) {
/* 146 */     if (this.mAudioManager == null) {
/* 147 */       this.mAudioManager = (AudioManager)this.context.getSystemService("audio");
/*     */     }
/* 149 */     int ringmax = this.mAudioManager.getStreamMaxVolume(2);
/* 150 */     int ringcurrent = this.mAudioManager.getStreamVolume(2);
/* 151 */     Logger.i("===处理前铃声音量====" + ringcurrent + "  /" + ringmax, new Object[0]);
/* 152 */     if (b) {
/* 153 */       if (ringcurrent == ringmax) {
/*     */         return;
/*     */       }
/* 156 */       this.mAudioManager.setStreamVolume(2, ringcurrent + 1, 0);
/*     */     } else {
/* 158 */       if (ringcurrent < 1) {
/*     */         return;
/*     */       }
/* 161 */       this.mAudioManager.setStreamVolume(2, ringcurrent - 1, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dealSystemVoice(boolean b) {
/* 170 */     if (this.mAudioManager == null) {
/* 171 */       this.mAudioManager = (AudioManager)this.context.getSystemService("audio");
/*     */     }
/* 173 */     int sysmax = this.mAudioManager.getStreamMaxVolume(1);
/* 174 */     int syscurrent = this.mAudioManager.getStreamVolume(1);
/* 175 */     Logger.i("===设置前系统音量====" + syscurrent + "  /" + sysmax, new Object[0]);
/* 176 */     if (b) {
/* 177 */       if (syscurrent == sysmax) {
/*     */         return;
/*     */       }
/* 180 */       this.mAudioManager.setStreamVolume(1, syscurrent + 1, 0);
/*     */     } else {
/* 182 */       if (syscurrent < 1) {
/*     */         return;
/*     */       }
/* 185 */       this.mAudioManager.setStreamVolume(1, syscurrent - 1, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dealCallVoice(boolean isAdd) {
/* 196 */     if (this.mAudioManager == null) {
/* 197 */       this.mAudioManager = (AudioManager)this.context.getSystemService("audio");
/*     */     }
/* 199 */     int callmax = this.mAudioManager.getStreamMaxVolume(0);
/* 200 */     int callcurrent = this.mAudioManager.getStreamVolume(0);
/* 201 */     Logger.e("=====设置前通话音量==" + callcurrent + "/" + callmax, new Object[0]);
/* 202 */     if (isAdd) {
/* 203 */       if (callcurrent == callmax) {
/*     */         return;
/*     */       }
/* 206 */       this.mAudioManager.setStreamVolume(0, callcurrent + 1, 0);
/*     */     } else {
/* 208 */       if (callcurrent < 1) {
/*     */         return;
/*     */       }
/* 211 */       this.mAudioManager.setStreamVolume(0, callcurrent - 1, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopMediaVoice() {
/* 220 */     if (this.mAudioManager == null) {
/* 221 */       this.mAudioManager = (AudioManager)this.context.getSystemService("audio");
/*     */     }
/* 223 */     this.mAudioManager.setStreamVolume(3, 0, 0);
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\VoiceManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */