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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VideoPlayItemListDaoBean
/*     */ {
/*     */   private Long id;
/*     */   private long item_id;
/*     */   private long link_id;
/*     */   private String file_name;
/*     */   private String play_item_name;
/*     */   private String play_item_type;
/*     */   private String resolution;
/*     */   private int size;
/*     */   private String checksum;
/*     */   private String fileMd5;
/*     */   
/*     */   public VideoPlayItemListDaoBean(Long id, long item_id, long link_id, String file_name, String play_item_name, String play_item_type, String resolution, int size, String checksum, String fileMd5) {
/*  38 */     this.id = id;
/*  39 */     this.item_id = item_id;
/*  40 */     this.link_id = link_id;
/*  41 */     this.file_name = file_name;
/*  42 */     this.play_item_name = play_item_name;
/*  43 */     this.play_item_type = play_item_type;
/*  44 */     this.resolution = resolution;
/*  45 */     this.size = size;
/*  46 */     this.checksum = checksum;
/*  47 */     this.fileMd5 = fileMd5;
/*     */   }
/*     */ 
/*     */   
/*     */   public VideoPlayItemListDaoBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  55 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  59 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getItem_id() {
/*  63 */     return this.item_id;
/*     */   }
/*     */   
/*     */   public void setItem_id(long item_id) {
/*  67 */     this.item_id = item_id;
/*     */   }
/*     */   
/*     */   public long getLink_id() {
/*  71 */     return this.link_id;
/*     */   }
/*     */   
/*     */   public void setLink_id(long link_id) {
/*  75 */     this.link_id = link_id;
/*     */   }
/*     */   
/*     */   public String getFile_name() {
/*  79 */     return this.file_name;
/*     */   }
/*     */   
/*     */   public void setFile_name(String file_name) {
/*  83 */     this.file_name = file_name;
/*     */   }
/*     */   
/*     */   public String getPlay_item_name() {
/*  87 */     return this.play_item_name;
/*     */   }
/*     */   
/*     */   public void setPlay_item_name(String play_item_name) {
/*  91 */     this.play_item_name = play_item_name;
/*     */   }
/*     */   
/*     */   public String getPlay_item_type() {
/*  95 */     return this.play_item_type;
/*     */   }
/*     */   
/*     */   public void setPlay_item_type(String play_item_type) {
/*  99 */     this.play_item_type = play_item_type;
/*     */   }
/*     */   
/*     */   public String getResolution() {
/* 103 */     return this.resolution;
/*     */   }
/*     */   
/*     */   public void setResolution(String resolution) {
/* 107 */     this.resolution = resolution;
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 111 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(int size) {
/* 115 */     this.size = size;
/*     */   }
/*     */   
/*     */   public String getChecksum() {
/* 119 */     return this.checksum;
/*     */   }
/*     */   
/*     */   public void setChecksum(String checksum) {
/* 123 */     this.checksum = checksum;
/*     */   }
/*     */   
/*     */   public String getFileMd5() {
/* 127 */     return this.fileMd5;
/*     */   }
/*     */   
/*     */   public void setFileMd5(String fileMd5) {
/* 131 */     this.fileMd5 = fileMd5;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\VideoPlayItemListDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */