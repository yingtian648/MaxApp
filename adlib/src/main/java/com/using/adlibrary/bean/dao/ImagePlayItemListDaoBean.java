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
/*     */ public class ImagePlayItemListDaoBean
/*     */ {
/*     */   private Long id;
/*     */   private long item_id;
/*     */   private long link_id;
/*     */   private String play_item_name;
/*     */   private String play_item_type;
/*     */   private int size;
/*     */   private String checksum;
/*     */   private String file_name;
/*     */   private int runningTime;
/*     */   private String fileMd5;
/*     */   
/*     */   public ImagePlayItemListDaoBean(Long id, long item_id, long link_id, String play_item_name, String play_item_type, int size, String checksum, String file_name, int runningTime, String fileMd5) {
/*  29 */     this.id = id;
/*  30 */     this.item_id = item_id;
/*  31 */     this.link_id = link_id;
/*  32 */     this.play_item_name = play_item_name;
/*  33 */     this.play_item_type = play_item_type;
/*  34 */     this.size = size;
/*  35 */     this.checksum = checksum;
/*  36 */     this.file_name = file_name;
/*  37 */     this.runningTime = runningTime;
/*  38 */     this.fileMd5 = fileMd5;
/*     */   }
/*     */   
/*     */   public ImagePlayItemListDaoBean() {}
/*     */   
/*     */   public Long getId() {
/*  44 */     return this.id;
/*     */   }
/*     */   public void setId(Long id) {
/*  47 */     this.id = id;
/*     */   }
/*     */   public long getItem_id() {
/*  50 */     return this.item_id;
/*     */   }
/*     */   public void setItem_id(long item_id) {
/*  53 */     this.item_id = item_id;
/*     */   }
/*     */   public long getLink_id() {
/*  56 */     return this.link_id;
/*     */   }
/*     */   public void setLink_id(long link_id) {
/*  59 */     this.link_id = link_id;
/*     */   }
/*     */   public String getPlay_item_name() {
/*  62 */     return this.play_item_name;
/*     */   }
/*     */   public void setPlay_item_name(String play_item_name) {
/*  65 */     this.play_item_name = play_item_name;
/*     */   }
/*     */   public String getPlay_item_type() {
/*  68 */     return this.play_item_type;
/*     */   }
/*     */   public void setPlay_item_type(String play_item_type) {
/*  71 */     this.play_item_type = play_item_type;
/*     */   }
/*     */   public int getSize() {
/*  74 */     return this.size;
/*     */   }
/*     */   public void setSize(int size) {
/*  77 */     this.size = size;
/*     */   }
/*     */   public String getChecksum() {
/*  80 */     return this.checksum;
/*     */   }
/*     */   public void setChecksum(String checksum) {
/*  83 */     this.checksum = checksum;
/*     */   }
/*     */   public String getFile_name() {
/*  86 */     return this.file_name;
/*     */   }
/*     */   public void setFile_name(String file_name) {
/*  89 */     this.file_name = file_name;
/*     */   }
/*     */   public int getRunningTime() {
/*  92 */     return this.runningTime;
/*     */   }
/*     */   public void setRunningTime(int runningTime) {
/*  95 */     this.runningTime = runningTime;
/*     */   }
/*     */   public String getFileMd5() {
/*  98 */     return this.fileMd5;
/*     */   }
/*     */   public void setFileMd5(String fileMd5) {
/* 101 */     this.fileMd5 = fileMd5;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\ImagePlayItemListDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */