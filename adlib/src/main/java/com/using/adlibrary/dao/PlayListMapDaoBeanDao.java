/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.PlayListMapDaoBean;
/*     */ import org.greenrobot.greendao.AbstractDao;
/*     */ import org.greenrobot.greendao.Property;
/*     */ import org.greenrobot.greendao.database.Database;
/*     */ import org.greenrobot.greendao.database.DatabaseStatement;
/*     */ import org.greenrobot.greendao.internal.DaoConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayListMapDaoBeanDao
/*     */   extends AbstractDao<PlayListMapDaoBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PLAY_LIST_MAP_DAO_BEAN";
/*     */   
/*     */   public static class Properties
/*     */   {
/*  27 */     public static final Property Playlist_id = new Property(0, long.class, "playlist_id", true, "_id");
/*  28 */     public static final Property Playlist_md5 = new Property(1, String.class, "playlist_md5", false, "PLAYLIST_MD5");
/*  29 */     public static final Property CurState = new Property(2, int.class, "curState", false, "CUR_STATE");
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayListMapDaoBeanDao(DaoConfig config) {
/*  34 */     super(config);
/*     */   }
/*     */   
/*     */   public PlayListMapDaoBeanDao(DaoConfig config, DaoSession daoSession) {
/*  38 */     super(config, daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  43 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  44 */     db.execSQL("CREATE TABLE " + constraint + "\"PLAY_LIST_MAP_DAO_BEAN\" (\"_id\" INTEGER PRIMARY KEY NOT NULL ,\"PLAYLIST_MD5\" TEXT,\"CUR_STATE\" INTEGER NOT NULL );");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropTable(Database db, boolean ifExists) {
/*  52 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAY_LIST_MAP_DAO_BEAN\"";
/*  53 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, PlayListMapDaoBean entity) {
/*  58 */     stmt.clearBindings();
/*  59 */     stmt.bindLong(1, entity.getPlaylist_id());
/*     */     
/*  61 */     String playlist_md5 = entity.getPlaylist_md5();
/*  62 */     if (playlist_md5 != null) {
/*  63 */       stmt.bindString(2, playlist_md5);
/*     */     }
/*  65 */     stmt.bindLong(3, entity.getCurState());
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, PlayListMapDaoBean entity) {
/*  70 */     stmt.clearBindings();
/*  71 */     stmt.bindLong(1, entity.getPlaylist_id());
/*     */     
/*  73 */     String playlist_md5 = entity.getPlaylist_md5();
/*  74 */     if (playlist_md5 != null) {
/*  75 */       stmt.bindString(2, playlist_md5);
/*     */     }
/*  77 */     stmt.bindLong(3, entity.getCurState());
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/*  82 */     return Long.valueOf(cursor.getLong(offset + 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayListMapDaoBean readEntity(Cursor cursor, int offset) {
/*  90 */     PlayListMapDaoBean entity = new PlayListMapDaoBean(cursor.getLong(offset + 0), cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), cursor.getInt(offset + 2));
/*     */     
/*  92 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, PlayListMapDaoBean entity, int offset) {
/*  97 */     entity.setPlaylist_id(cursor.getLong(offset + 0));
/*  98 */     entity.setPlaylist_md5(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
/*  99 */     entity.setCurState(cursor.getInt(offset + 2));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(PlayListMapDaoBean entity, long rowId) {
/* 104 */     entity.setPlaylist_id(rowId);
/* 105 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(PlayListMapDaoBean entity) {
/* 110 */     if (entity != null) {
/* 111 */       return Long.valueOf(entity.getPlaylist_id());
/*     */     }
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(PlayListMapDaoBean entity) {
/* 119 */     throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 124 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\PlayListMapDaoBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */