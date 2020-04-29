/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.MaterialDaoBean;
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
/*     */ public class MaterialDaoBeanDao
/*     */   extends AbstractDao<MaterialDaoBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "MATERIAL_DAO_BEAN";
/*     */   
/*     */   public static class Properties
/*     */   {
/*  27 */     public static final Property Item_id = new Property(0, long.class, "item_id", true, "_id");
/*  28 */     public static final Property Play_item_name = new Property(1, String.class, "play_item_name", false, "PLAY_ITEM_NAME");
/*  29 */     public static final Property Play_item_type = new Property(2, String.class, "play_item_type", false, "PLAY_ITEM_TYPE");
/*  30 */     public static final Property Size = new Property(3, int.class, "size", false, "SIZE");
/*  31 */     public static final Property Checksum = new Property(4, String.class, "checksum", false, "CHECKSUM");
/*  32 */     public static final Property File_name = new Property(5, String.class, "file_name", false, "FILE_NAME");
/*  33 */     public static final Property RunningTime = new Property(6, int.class, "runningTime", false, "RUNNING_TIME");
/*  34 */     public static final Property FileMd5 = new Property(7, String.class, "fileMd5", false, "FILE_MD5");
/*  35 */     public static final Property IsDownLoad = new Property(8, boolean.class, "isDownLoad", false, "IS_DOWN_LOAD");
/*  36 */     public static final Property DownloadSuceess = new Property(9, boolean.class, "downloadSuceess", false, "DOWNLOAD_SUCEESS");
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialDaoBeanDao(DaoConfig config) {
/*  41 */     super(config);
/*     */   }
/*     */   
/*     */   public MaterialDaoBeanDao(DaoConfig config, DaoSession daoSession) {
/*  45 */     super(config, daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  50 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  51 */     db.execSQL("CREATE TABLE " + constraint + "\"MATERIAL_DAO_BEAN\" (\"_id\" INTEGER PRIMARY KEY NOT NULL ,\"PLAY_ITEM_NAME\" TEXT,\"PLAY_ITEM_TYPE\" TEXT,\"SIZE\" INTEGER NOT NULL ,\"CHECKSUM\" TEXT,\"FILE_NAME\" TEXT,\"RUNNING_TIME\" INTEGER NOT NULL ,\"FILE_MD5\" TEXT,\"IS_DOWN_LOAD\" INTEGER NOT NULL ,\"DOWNLOAD_SUCEESS\" INTEGER NOT NULL );");
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
/*     */   public static void dropTable(Database db, boolean ifExists) {
/*  66 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MATERIAL_DAO_BEAN\"";
/*  67 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, MaterialDaoBean entity) {
/*  72 */     stmt.clearBindings();
/*  73 */     stmt.bindLong(1, entity.getItem_id());
/*     */     
/*  75 */     String play_item_name = entity.getPlay_item_name();
/*  76 */     if (play_item_name != null) {
/*  77 */       stmt.bindString(2, play_item_name);
/*     */     }
/*     */     
/*  80 */     String play_item_type = entity.getPlay_item_type();
/*  81 */     if (play_item_type != null) {
/*  82 */       stmt.bindString(3, play_item_type);
/*     */     }
/*  84 */     stmt.bindLong(4, entity.getSize());
/*     */     
/*  86 */     String checksum = entity.getChecksum();
/*  87 */     if (checksum != null) {
/*  88 */       stmt.bindString(5, checksum);
/*     */     }
/*     */     
/*  91 */     String file_name = entity.getFile_name();
/*  92 */     if (file_name != null) {
/*  93 */       stmt.bindString(6, file_name);
/*     */     }
/*  95 */     stmt.bindLong(7, entity.getRunningTime());
/*     */     
/*  97 */     String fileMd5 = entity.getFileMd5();
/*  98 */     if (fileMd5 != null) {
/*  99 */       stmt.bindString(8, fileMd5);
/*     */     }
/* 101 */     stmt.bindLong(9, entity.getIsDownLoad() ? 1L : 0L);
/* 102 */     stmt.bindLong(10, entity.getDownloadSuceess() ? 1L : 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, MaterialDaoBean entity) {
/* 107 */     stmt.clearBindings();
/* 108 */     stmt.bindLong(1, entity.getItem_id());
/*     */     
/* 110 */     String play_item_name = entity.getPlay_item_name();
/* 111 */     if (play_item_name != null) {
/* 112 */       stmt.bindString(2, play_item_name);
/*     */     }
/*     */     
/* 115 */     String play_item_type = entity.getPlay_item_type();
/* 116 */     if (play_item_type != null) {
/* 117 */       stmt.bindString(3, play_item_type);
/*     */     }
/* 119 */     stmt.bindLong(4, entity.getSize());
/*     */     
/* 121 */     String checksum = entity.getChecksum();
/* 122 */     if (checksum != null) {
/* 123 */       stmt.bindString(5, checksum);
/*     */     }
/*     */     
/* 126 */     String file_name = entity.getFile_name();
/* 127 */     if (file_name != null) {
/* 128 */       stmt.bindString(6, file_name);
/*     */     }
/* 130 */     stmt.bindLong(7, entity.getRunningTime());
/*     */     
/* 132 */     String fileMd5 = entity.getFileMd5();
/* 133 */     if (fileMd5 != null) {
/* 134 */       stmt.bindString(8, fileMd5);
/*     */     }
/* 136 */     stmt.bindLong(9, entity.getIsDownLoad() ? 1L : 0L);
/* 137 */     stmt.bindLong(10, entity.getDownloadSuceess() ? 1L : 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 142 */     return Long.valueOf(cursor.getLong(offset + 0));
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
/*     */   public MaterialDaoBean readEntity(Cursor cursor, int offset) {
/* 157 */     MaterialDaoBean entity = new MaterialDaoBean(cursor.getLong(offset + 0), cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), cursor.getInt(offset + 3), cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), cursor.getInt(offset + 6), cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), (cursor.getShort(offset + 8) != 0), (cursor.getShort(offset + 9) != 0));
/*     */     
/* 159 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, MaterialDaoBean entity, int offset) {
/* 164 */     entity.setItem_id(cursor.getLong(offset + 0));
/* 165 */     entity.setPlay_item_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
/* 166 */     entity.setPlay_item_type(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
/* 167 */     entity.setSize(cursor.getInt(offset + 3));
/* 168 */     entity.setChecksum(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
/* 169 */     entity.setFile_name(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
/* 170 */     entity.setRunningTime(cursor.getInt(offset + 6));
/* 171 */     entity.setFileMd5(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
/* 172 */     entity.setIsDownLoad((cursor.getShort(offset + 8) != 0));
/* 173 */     entity.setDownloadSuceess((cursor.getShort(offset + 9) != 0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(MaterialDaoBean entity, long rowId) {
/* 178 */     entity.setItem_id(rowId);
/* 179 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(MaterialDaoBean entity) {
/* 184 */     if (entity != null) {
/* 185 */       return Long.valueOf(entity.getItem_id());
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(MaterialDaoBean entity) {
/* 193 */     throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 198 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\MaterialDaoBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */