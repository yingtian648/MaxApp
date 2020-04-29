/*     */ package com.using.adlibrary.bean.phone;
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
/*     */ 
/*     */ public class PhoneVideoPlayItemListBean
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
/*     */   private String file_md5;
/*     */   
/*     */   public PhoneVideoPlayItemListBean(Long id, long item_id, long link_id, String file_name, String play_item_name, String play_item_type, String resolution, int size, String checksum, String file_md5) {
/*  39 */     this.id = id;
/*  40 */     this.item_id = item_id;
/*  41 */     this.link_id = link_id;
/*  42 */     this.file_name = file_name;
/*  43 */     this.play_item_name = play_item_name;
/*  44 */     this.play_item_type = play_item_type;
/*  45 */     this.resolution = resolution;
/*  46 */     this.size = size;
/*  47 */     this.checksum = checksum;
/*  48 */     this.file_md5 = file_md5;
/*     */   }
/*     */ 
/*     */   
/*     */   public PhoneVideoPlayItemListBean() {}
/*     */ 
/*     */   
/*     */   public Long getId() {
/*  56 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  60 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getItem_id() {
/*  64 */     return this.item_id;
/*     */   }
/*     */   
/*     */   public void setItem_id(long item_id) {
/*  68 */     this.item_id = item_id;
/*     */   }
/*     */   
/*     */   public long getLink_id() {
/*  72 */     return this.link_id;
/*     */   }
/*     */   
/*     */   public void setLink_id(long link_id) {
/*  76 */     this.link_id = link_id;
/*     */   }
/*     */   
/*     */   public String getFile_name() {
/*  80 */     return this.file_name;
/*     */   }
/*     */   
/*     */   public void setFile_name(String file_name) {
/*  84 */     this.file_name = file_name;
/*     */   }
/*     */   
/*     */   public String getPlay_item_name() {
/*  88 */     return this.play_item_name;
/*     */   }
/*     */   
/*     */   public void setPlay_item_name(String play_item_name) {
/*  92 */     this.play_item_name = play_item_name;
/*     */   }
/*     */   
/*     */   public String getPlay_item_type() {
/*  96 */     return this.play_item_type;
/*     */   }
/*     */   
/*     */   public void setPlay_item_type(String play_item_type) {
/* 100 */     this.play_item_type = play_item_type;
/*     */   }
/*     */   
/*     */   public String getResolution() {
/* 104 */     return this.resolution;
/*     */   }
/*     */   
/*     */   public void setResolution(String resolution) {
/* 108 */     this.resolution = resolution;
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 112 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(int size) {
/* 116 */     this.size = size;
/*     */   }
/*     */   
/*     */   public String getChecksum() {
/* 120 */     return this.checksum;
/*     */   }
/*     */   
/*     */   public void setChecksum(String checksum) {
/* 124 */     this.checksum = checksum;
/*     */   }
/*     */   
/*     */   public String getFile_md5() {
/* 128 */     return this.file_md5;
/*     */   }
/*     */   
/*     */   public void setFile_md5(String file_md5) {
/* 132 */     this.file_md5 = file_md5;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\phone\PhoneVideoPlayItemListBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */