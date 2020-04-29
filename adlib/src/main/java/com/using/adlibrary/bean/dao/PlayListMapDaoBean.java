/*    */ package com.using.adlibrary.bean.dao;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayListMapDaoBean
/*    */ {
/*    */   private long playlist_id;
/*    */   private String playlist_md5;
/*    */   private int curState;
/*    */   
/*    */   public PlayListMapDaoBean(long playlist_id, String playlist_md5, int curState) {
/* 19 */     this.playlist_id = playlist_id;
/* 20 */     this.playlist_md5 = playlist_md5;
/* 21 */     this.curState = curState;
/*    */   }
/*    */   
/*    */   public PlayListMapDaoBean() {}
/*    */   
/*    */   public long getPlaylist_id() {
/* 27 */     return this.playlist_id;
/*    */   }
/*    */   public void setPlaylist_id(long playlist_id) {
/* 30 */     this.playlist_id = playlist_id;
/*    */   }
/*    */   public String getPlaylist_md5() {
/* 33 */     return this.playlist_md5;
/*    */   }
/*    */   public void setPlaylist_md5(String playlist_md5) {
/* 36 */     this.playlist_md5 = playlist_md5;
/*    */   }
/*    */   public int getCurState() {
/* 39 */     return this.curState;
/*    */   }
/*    */   public void setCurState(int curState) {
/* 42 */     this.curState = curState;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\bean\dao\PlayListMapDaoBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */