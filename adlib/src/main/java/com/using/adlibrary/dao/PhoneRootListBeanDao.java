/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.phone.PhoneRootListBean;
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
/*     */ public class PhoneRootListBeanDao
/*     */   extends AbstractDao<PhoneRootListBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PHONE_ROOT_LIST_BEAN";
/*     */   private DaoSession daoSession;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  27 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  28 */     public static final Property Play_list_id = new Property(1, long.class, "play_list_id", false, "PLAY_LIST_ID");
/*  29 */     public static final Property Play_list_name = new Property(2, String.class, "play_list_name", false, "PLAY_LIST_NAME");
/*  30 */     public static final Property Play_list_type = new Property(3, String.class, "play_list_type", false, "PLAY_LIST_TYPE");
/*  31 */     public static final Property Start_time = new Property(4, long.class, "start_time", false, "START_TIME");
/*  32 */     public static final Property Play_time = new Property(5, long.class, "play_time", false, "PLAY_TIME");
/*  33 */     public static final Property Fail_time = new Property(6, long.class, "fail_time", false, "FAIL_TIME");
/*  34 */     public static final Property Download_time = new Property(7, long.class, "download_time", false, "DOWNLOAD_TIME");
/*  35 */     public static final Property SubtitlePlayTime = new Property(8, String.class, "subtitlePlayTime", false, "SUBTITLE_PLAY_TIME");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PhoneRootListBeanDao(DaoConfig config) {
/*  42 */     super(config);
/*     */   }
/*     */   
/*     */   public PhoneRootListBeanDao(DaoConfig config, DaoSession daoSession) {
/*  46 */     super(config, daoSession);
/*  47 */     this.daoSession = daoSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  52 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  53 */     db.execSQL("CREATE TABLE " + constraint + "\"PHONE_ROOT_LIST_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PLAY_LIST_ID\" INTEGER NOT NULL ,\"PLAY_LIST_NAME\" TEXT,\"PLAY_LIST_TYPE\" TEXT,\"START_TIME\" INTEGER NOT NULL ,\"PLAY_TIME\" INTEGER NOT NULL ,\"FAIL_TIME\" INTEGER NOT NULL ,\"DOWNLOAD_TIME\" INTEGER NOT NULL ,\"SUBTITLE_PLAY_TIME\" TEXT);");
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
/*     */   public static void dropTable(Database db, boolean ifExists) {
/*  67 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_ROOT_LIST_BEAN\"";
/*  68 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, PhoneRootListBean entity) {
/*  73 */     stmt.clearBindings();
/*     */     
/*  75 */     Long id = entity.getId();
/*  76 */     if (id != null) {
/*  77 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  79 */     stmt.bindLong(2, entity.getPlay_list_id());
/*     */     
/*  81 */     String play_list_name = entity.getPlay_list_name();
/*  82 */     if (play_list_name != null) {
/*  83 */       stmt.bindString(3, play_list_name);
/*     */     }
/*     */     
/*  86 */     String play_list_type = entity.getPlay_list_type();
/*  87 */     if (play_list_type != null) {
/*  88 */       stmt.bindString(4, play_list_type);
/*     */     }
/*  90 */     stmt.bindLong(5, entity.getStart_time());
/*  91 */     stmt.bindLong(6, entity.getPlay_time());
/*  92 */     stmt.bindLong(7, entity.getFail_time());
/*  93 */     stmt.bindLong(8, entity.getDownload_time());
/*     */     
/*  95 */     String subtitlePlayTime = entity.getSubtitlePlayTime();
/*  96 */     if (subtitlePlayTime != null) {
/*  97 */       stmt.bindString(9, subtitlePlayTime);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, PhoneRootListBean entity) {
/* 103 */     stmt.clearBindings();
/*     */     
/* 105 */     Long id = entity.getId();
/* 106 */     if (id != null) {
/* 107 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 109 */     stmt.bindLong(2, entity.getPlay_list_id());
/*     */     
/* 111 */     String play_list_name = entity.getPlay_list_name();
/* 112 */     if (play_list_name != null) {
/* 113 */       stmt.bindString(3, play_list_name);
/*     */     }
/*     */     
/* 116 */     String play_list_type = entity.getPlay_list_type();
/* 117 */     if (play_list_type != null) {
/* 118 */       stmt.bindString(4, play_list_type);
/*     */     }
/* 120 */     stmt.bindLong(5, entity.getStart_time());
/* 121 */     stmt.bindLong(6, entity.getPlay_time());
/* 122 */     stmt.bindLong(7, entity.getFail_time());
/* 123 */     stmt.bindLong(8, entity.getDownload_time());
/*     */     
/* 125 */     String subtitlePlayTime = entity.getSubtitlePlayTime();
/* 126 */     if (subtitlePlayTime != null) {
/* 127 */       stmt.bindString(9, subtitlePlayTime);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void attachEntity(PhoneRootListBean entity) {
/* 133 */     super.attachEntity(entity);
/* 134 */     entity.__setDaoSession(this.daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 139 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
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
/*     */   public PhoneRootListBean readEntity(Cursor cursor, int offset) {
/* 153 */     PhoneRootListBean entity = new PhoneRootListBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), cursor.getLong(offset + 4), cursor.getLong(offset + 5), cursor.getLong(offset + 6), cursor.getLong(offset + 7), cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
/*     */     
/* 155 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, PhoneRootListBean entity, int offset) {
/* 160 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 161 */     entity.setPlay_list_id(cursor.getLong(offset + 1));
/* 162 */     entity.setPlay_list_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
/* 163 */     entity.setPlay_list_type(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
/* 164 */     entity.setStart_time(cursor.getLong(offset + 4));
/* 165 */     entity.setPlay_time(cursor.getLong(offset + 5));
/* 166 */     entity.setFail_time(cursor.getLong(offset + 6));
/* 167 */     entity.setDownload_time(cursor.getLong(offset + 7));
/* 168 */     entity.setSubtitlePlayTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(PhoneRootListBean entity, long rowId) {
/* 173 */     entity.setId(Long.valueOf(rowId));
/* 174 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(PhoneRootListBean entity) {
/* 179 */     if (entity != null) {
/* 180 */       return entity.getId();
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(PhoneRootListBean entity) {
/* 188 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 193 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\PhoneRootListBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */