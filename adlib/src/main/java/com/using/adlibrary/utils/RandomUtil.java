/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ public class RandomUtil
/*     */ {
/*     */   public static int getNum(int endNum) {
/*  17 */     if (endNum > 0) {
/*     */       
/*  19 */       Random random = new Random();
/*  20 */       return random.nextInt(endNum);
/*     */     } 
/*     */     
/*  23 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNum(int startNum, int endNum) {
/*  34 */     if (endNum > startNum) {
/*     */       
/*  36 */       Random random = new Random();
/*  37 */       return random.nextInt(endNum - startNum) + startNum;
/*     */     } 
/*     */     
/*  40 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getLargeLetter() {
/*  49 */     Random random = new Random();
/*     */     
/*  51 */     return String.valueOf((char)(random.nextInt(27) + 65));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getLargeLetter(int size) {
/*  60 */     StringBuffer buffer = new StringBuffer();
/*  61 */     Random random = new Random();
/*     */     
/*  63 */     for (int i = 0; i < size; i++)
/*     */     {
/*  65 */       buffer.append((char)(random.nextInt(27) + 65));
/*     */     }
/*     */     
/*  68 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getSmallLetter() {
/*  77 */     Random random = new Random();
/*     */     
/*  79 */     return String.valueOf((char)(random.nextInt(27) + 97));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getSmallLetter(int size) {
/*  88 */     StringBuffer buffer = new StringBuffer();
/*  89 */     Random random = new Random();
/*  90 */     for (int i = 0; i < size; i++)
/*     */     {
/*  92 */       buffer.append((char)(random.nextInt(27) + 97));
/*     */     }
/*     */     
/*  95 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNumSmallLetter(int size) {
/* 105 */     StringBuffer buffer = new StringBuffer();
/* 106 */     Random random = new Random();
/* 107 */     for (int i = 0; i < size; i++) {
/*     */       
/* 109 */       if (random.nextInt(2) % 2 == 0) {
/*     */ 
/*     */         
/* 112 */         buffer.append((char)(random.nextInt(27) + 97));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 117 */         buffer.append(random.nextInt(10));
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNumLargeLetter(int size) {
/* 131 */     StringBuffer buffer = new StringBuffer();
/* 132 */     Random random = new Random();
/*     */     
/* 134 */     for (int i = 0; i < size; i++) {
/*     */       
/* 136 */       if (random.nextInt(2) % 2 == 0) {
/*     */ 
/*     */         
/* 139 */         buffer.append((char)(random.nextInt(27) + 65));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 144 */         buffer.append(random.nextInt(10));
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getNumLargeSmallLetter(int size) {
/* 158 */     StringBuffer buffer = new StringBuffer();
/* 159 */     Random random = new Random();
/*     */     
/* 161 */     for (int i = 0; i < size; i++) {
/*     */       
/* 163 */       if (random.nextInt(2) % 2 == 0) {
/*     */ 
/*     */         
/* 166 */         if (random.nextInt(2) % 2 == 0) {
/*     */           
/* 168 */           buffer.append((char)(random.nextInt(27) + 65));
/*     */         } else {
/*     */           
/* 171 */           buffer.append((char)(random.nextInt(27) + 97));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 176 */         buffer.append(random.nextInt(10));
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\RandomUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */