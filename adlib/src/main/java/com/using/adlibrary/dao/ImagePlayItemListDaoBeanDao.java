/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.ImagePlayItemListDaoBean;
/*     */ import java.util.List;
/*     */ import org.greenrobot.greendao.AbstractDao;
/*     */ import org.greenrobot.greendao.Property;
/*     */ import org.greenrobot.greendao.database.Database;
/*     */ import org.greenrobot.greendao.database.DatabaseStatement;
/*     */ import org.greenrobot.greendao.internal.DaoConfig;
/*     */ import org.greenrobot.greendao.query.Query;
/*     */ import org.greenrobot.greendao.query.QueryBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImagePlayItemListDaoBeanDao
/*     */   extends AbstractDao<ImagePlayItemListDaoBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "IMAGE_PLAY_ITEM_LIST_DAO_BEAN";
/*     */   private Query<ImagePlayItemListDaoBean> playAreaListDaoBean_ImagePlayItemListQuery;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  30 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  31 */     public static final Property Item_id = new Property(1, long.class, "item_id", false, "ITEM_ID");
/*  32 */     public static final Property Link_id = new Property(2, long.class, "link_id", false, "LINK_ID");
/*  33 */     public static final Property Play_item_name = new Property(3, String.class, "play_item_name", false, "PLAY_ITEM_NAME");
/*  34 */     public static final Property Play_item_type = new Property(4, String.class, "play_item_type", false, "PLAY_ITEM_TYPE");
/*  35 */     public static final Property Size = new Property(5, int.class, "size", false, "SIZE");
/*  36 */     public static final Property Checksum = new Property(6, String.class, "checksum", false, "CHECKSUM");
/*  37 */     public static final Property File_name = new Property(7, String.class, "file_name", false, "FILE_NAME");
/*  38 */     public static final Property RunningTime = new Property(8, int.class, "runningTime", false, "RUNNING_TIME");
/*  39 */     public static final Property FileMd5 = new Property(9, String.class, "fileMd5", false, "FILE_MD5");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ImagePlayItemListDaoBeanDao(DaoConfig config) {
/*  45 */     super(config);
/*     */   }
/*     */   
/*     */   public ImagePlayItemListDaoBeanDao(DaoConfig config, DaoSession daoSession) {
/*  49 */     super(config, daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  54 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  55 */     db.execSQL("CREATE TABLE " + constraint + "\"IMAGE_PLAY_ITEM_LIST_DAO_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ITEM_ID\" INTEGER NOT NULL ,\"LINK_ID\" INTEGER NOT NULL ,\"PLAY_ITEM_NAME\" TEXT,\"PLAY_ITEM_TYPE\" TEXT,\"SIZE\" INTEGER NOT NULL ,\"CHECKSUM\" TEXT,\"FILE_NAME\" TEXT,\"RUNNING_TIME\" INTEGER NOT NULL ,\"FILE_MD5\" TEXT);");
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
/*  70 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IMAGE_PLAY_ITEM_LIST_DAO_BEAN\"";
/*  71 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, ImagePlayItemListDaoBean entity) {
/*  76 */     stmt.clearBindings();
/*     */     
/*  78 */     Long id = entity.getId();
/*  79 */     if (id != null) {
/*  80 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  82 */     stmt.bindLong(2, entity.getItem_id());
/*  83 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/*  85 */     String play_item_name = entity.getPlay_item_name();
/*  86 */     if (play_item_name != null) {
/*  87 */       stmt.bindString(4, play_item_name);
/*     */     }
/*     */     
/*  90 */     String play_item_type = entity.getPlay_item_type();
/*  91 */     if (play_item_type != null) {
/*  92 */       stmt.bindString(5, play_item_type);
/*     */     }
/*  94 */     stmt.bindLong(6, entity.getSize());
/*     */     
/*  96 */     String checksum = entity.getChecksum();
/*  97 */     if (checksum != null) {
/*  98 */       stmt.bindString(7, checksum);
/*     */     }
/*     */     
/* 101 */     String file_name = entity.getFile_name();
/* 102 */     if (file_name != null) {
/* 103 */       stmt.bindString(8, file_name);
/*     */     }
/* 105 */     stmt.bindLong(9, entity.getRunningTime());
/*     */     
/* 107 */     String fileMd5 = entity.getFileMd5();
/* 108 */     if (fileMd5 != null) {
/* 109 */       stmt.bindString(10, fileMd5);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, ImagePlayItemListDaoBean entity) {
/* 115 */     stmt.clearBindings();
/*     */     
/* 117 */     Long id = entity.getId();
/* 118 */     if (id != null) {
/* 119 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 121 */     stmt.bindLong(2, entity.getItem_id());
/* 122 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/* 124 */     String play_item_name = entity.getPlay_item_name();
/* 125 */     if (play_item_name != null) {
/* 126 */       stmt.bindString(4, play_item_name);
/*     */     }
/*     */     
/* 129 */     String play_item_type = entity.getPlay_item_type();
/* 130 */     if (play_item_type != null) {
/* 131 */       stmt.bindString(5, play_item_type);
/*     */     }
/* 133 */     stmt.bindLong(6, entity.getSize());
/*     */     
/* 135 */     String checksum = entity.getChecksum();
/* 136 */     if (checksum != null) {
/* 137 */       stmt.bindString(7, checksum);
/*     */     }
/*     */     
/* 140 */     String file_name = entity.getFile_name();
/* 141 */     if (file_name != null) {
/* 142 */       stmt.bindString(8, file_name);
/*     */     }
/* 144 */     stmt.bindLong(9, entity.getRunningTime());
/*     */     
/* 146 */     String fileMd5 = entity.getFileMd5();
/* 147 */     if (fileMd5 != null) {
/* 148 */       stmt.bindString(10, fileMd5);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 154 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
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
/*     */   public ImagePlayItemListDaoBean readEntity(Cursor cursor, int offset) {
/* 169 */     ImagePlayItemListDaoBean entity = new ImagePlayItemListDaoBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.getLong(offset + 2), cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), cursor.getInt(offset + 5), cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), cursor.getInt(offset + 8), cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */     
/* 171 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, ImagePlayItemListDaoBean entity, int offset) {
/* 176 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 177 */     entity.setItem_id(cursor.getLong(offset + 1));
/* 178 */     entity.setLink_id(cursor.getLong(offset + 2));
/* 179 */     entity.setPlay_item_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
/* 180 */     entity.setPlay_item_type(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
/* 181 */     entity.setSize(cursor.getInt(offset + 5));
/* 182 */     entity.setChecksum(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
/* 183 */     entity.setFile_name(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
/* 184 */     entity.setRunningTime(cursor.getInt(offset + 8));
/* 185 */     entity.setFileMd5(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(ImagePlayItemListDaoBean entity, long rowId) {
/* 190 */     entity.setId(Long.valueOf(rowId));
/* 191 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(ImagePlayItemListDaoBean entity) {
/* 196 */     if (entity != null) {
/* 197 */       return entity.getId();
/*     */     }
/* 199 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(ImagePlayItemListDaoBean entity) {
/* 205 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ImagePlayItemListDaoBean> _queryPlayAreaListDaoBean_ImagePlayItemList(long link_id) {
/* 215 */     synchronized (this) {
/* 216 */       if (this.playAreaListDaoBean_ImagePlayItemListQuery == null) {
/* 217 */         QueryBuilder<ImagePlayItemListDaoBean> queryBuilder = queryBuilder();
/* 218 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 219 */         this.playAreaListDaoBean_ImagePlayItemListQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 222 */     Query<ImagePlayItemListDaoBean> query = this.playAreaListDaoBean_ImagePlayItemListQuery.forCurrentThread();
/* 223 */     query.setParameter(0, Long.valueOf(link_id));
/* 224 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\ImagePlayItemListDaoBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */