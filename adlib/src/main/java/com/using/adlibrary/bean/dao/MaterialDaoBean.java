/*     */ package com.using.adlibrary.bean.dao;
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
/*     */ public class MaterialDaoBean
/*     */ {
/*     */   private long item_id;
/*     */   private String play_item_name;
/*     */   private String play_item_type;
/*     */   private int size;
/*     */   private String checksum;
/*     */   private String file_name;
/*     */   private int runningTime;
/*     */   private String fileMd5;
/*     */   private boolean isDownLoad;
/*     */   private boolean downloadSuceess;
/*     */   
/*     */   public MaterialDaoBean(long item_id, String play_item_name, String play_item_type, int size, String checksum, String file_name, int runningTime, String fileMd5, boolean isDownLoad, boolean downloadSuceess) {
/*  30 */     this.item_id = item_id;
/*  31 */     this.play_item_name = play_item_name;
/*  32 */     this.play_item_type = play_item_type;
/*  33 */     this.size = size;
/*  34 */     this.checksum = checksum;
/*  35 */     this.file_name = file_name;
/*  36 */     this.runningTime = runningTime;
/*  37 */     this.fileMd5 = fileMd5;
/*  38 */     this.isDownLoad = isDownLoad;
/*  39 */     this.downloadSuceess = downloadSuceess;
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialDaoBean() {}
/*     */ 
/*     */   
/*     */   public long getItem_id() {
/*  47 */     return this.item_id;
/*     */   }
/*     */   
/*     */   public void setItem_id(long item_id) {
/*  51 */     this.item_id = item_id;
/*     */   }
/*     */   
/*     */   public String getPlay_item_name() {
/*  55 */     return this.play_item_name;
/*     */   }
/*     */   
/*     */   public void setPlay_item_name(String play_item_name) {
/*  59 */     this.play_item_name = play_item_name;
/*     */   }
/*     */   
/*     */   public String getPlay_item_type() {
/*  63 */     return this.play_item_type;
/*     */   }
/*     */   
/*     */   public void setPlay_item_type(String play_item_type) {
/*  67 */     this.play_item_type = play_item_type;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  71 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(int size) {
/*  75 */     this.size = size;
/*     */   }
/*     */   
/*     */   public String getChecksum() {
/*  79 */     return this.checksum;
/*     */   }
/*     */   
/*     */   public void setChecksum(String checksum) {
/*  83 */     this.checksum = checksum;
/*     */   }
/*     */   
/*     */   public String getFile_name() {
/*  87 */     return this.file_name;
/*     */   }
/*     */   
/*     */   public void setFile_name(String file_name) {
/*  91 */     this.file_name = file_name;
/*     */   }
/*     */   
/*     */   public int getRunningTime() {
/*  95 */     return this.runningTime;
/*     */   }
/*     */   
/*     */   public void setRunningTime(int runningTime) {
/*  99 */     this.runningTime = runningTime;
/*     */   }
/*     */   
/*     */   public String getFileMd5() {
/* 103 */     return this.fileMd5;
/*     */   }
/*     */   
/*     */   public void setFileMd5(String fileMd5) {
/* 107 */     this.fileMd5 = fileMd5;
/*     */   }
/*     */   
/*     */   public boolean getIsDownLoad() {
/* 111 */     return this.isDownLoad;
/*     */   }
/*     */   
/*     */   public void setIsDownLoad(boolean isDownLoad) {
/* 115 */     this.isDownLoad = isDownLoad;
/*     */   }
/*     */   
/*     */   public boolean getDownloadSuceess() {
/* 119 */     return this.downloadSuceess;
/*     */   }
/*     */   
/*     */   public void setDownloadSuceess(boolean downloadSuceess) {
/* 123 */     this.downloadSuceess = downloadSuceess;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\MaterialDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */