/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.phone.PhoneVideoPlayItemListBean;
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
/*     */ public class PhoneVideoPlayItemListBeanDao
/*     */   extends AbstractDao<PhoneVideoPlayItemListBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PHONE_VIDEO_PLAY_ITEM_LIST_BEAN";
/*     */   private Query<PhoneVideoPlayItemListBean> phonePlayAreaListBean_VideoPlayItemListQuery;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  30 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  31 */     public static final Property Item_id = new Property(1, long.class, "item_id", false, "ITEM_ID");
/*  32 */     public static final Property Link_id = new Property(2, long.class, "link_id", false, "LINK_ID");
/*  33 */     public static final Property File_name = new Property(3, String.class, "file_name", false, "FILE_NAME");
/*  34 */     public static final Property Play_item_name = new Property(4, String.class, "play_item_name", false, "PLAY_ITEM_NAME");
/*  35 */     public static final Property Play_item_type = new Property(5, String.class, "play_item_type", false, "PLAY_ITEM_TYPE");
/*  36 */     public static final Property Resolution = new Property(6, String.class, "resolution", false, "RESOLUTION");
/*  37 */     public static final Property Size = new Property(7, int.class, "size", false, "SIZE");
/*  38 */     public static final Property Checksum = new Property(8, String.class, "checksum", false, "CHECKSUM");
/*  39 */     public static final Property File_md5 = new Property(9, String.class, "file_md5", false, "FILE_MD5");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PhoneVideoPlayItemListBeanDao(DaoConfig config) {
/*  45 */     super(config);
/*     */   }
/*     */   
/*     */   public PhoneVideoPlayItemListBeanDao(DaoConfig config, DaoSession daoSession) {
/*  49 */     super(config, daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  54 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  55 */     db.execSQL("CREATE TABLE " + constraint + "\"PHONE_VIDEO_PLAY_ITEM_LIST_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"ITEM_ID\" INTEGER NOT NULL ,\"LINK_ID\" INTEGER NOT NULL ,\"FILE_NAME\" TEXT,\"PLAY_ITEM_NAME\" TEXT,\"PLAY_ITEM_TYPE\" TEXT,\"RESOLUTION\" TEXT,\"SIZE\" INTEGER NOT NULL ,\"CHECKSUM\" TEXT,\"FILE_MD5\" TEXT);");
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
/*  70 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_VIDEO_PLAY_ITEM_LIST_BEAN\"";
/*  71 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, PhoneVideoPlayItemListBean entity) {
/*  76 */     stmt.clearBindings();
/*     */     
/*  78 */     Long id = entity.getId();
/*  79 */     if (id != null) {
/*  80 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  82 */     stmt.bindLong(2, entity.getItem_id());
/*  83 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/*  85 */     String file_name = entity.getFile_name();
/*  86 */     if (file_name != null) {
/*  87 */       stmt.bindString(4, file_name);
/*     */     }
/*     */     
/*  90 */     String play_item_name = entity.getPlay_item_name();
/*  91 */     if (play_item_name != null) {
/*  92 */       stmt.bindString(5, play_item_name);
/*     */     }
/*     */     
/*  95 */     String play_item_type = entity.getPlay_item_type();
/*  96 */     if (play_item_type != null) {
/*  97 */       stmt.bindString(6, play_item_type);
/*     */     }
/*     */     
/* 100 */     String resolution = entity.getResolution();
/* 101 */     if (resolution != null) {
/* 102 */       stmt.bindString(7, resolution);
/*     */     }
/* 104 */     stmt.bindLong(8, entity.getSize());
/*     */     
/* 106 */     String checksum = entity.getChecksum();
/* 107 */     if (checksum != null) {
/* 108 */       stmt.bindString(9, checksum);
/*     */     }
/*     */     
/* 111 */     String file_md5 = entity.getFile_md5();
/* 112 */     if (file_md5 != null) {
/* 113 */       stmt.bindString(10, file_md5);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, PhoneVideoPlayItemListBean entity) {
/* 119 */     stmt.clearBindings();
/*     */     
/* 121 */     Long id = entity.getId();
/* 122 */     if (id != null) {
/* 123 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 125 */     stmt.bindLong(2, entity.getItem_id());
/* 126 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/* 128 */     String file_name = entity.getFile_name();
/* 129 */     if (file_name != null) {
/* 130 */       stmt.bindString(4, file_name);
/*     */     }
/*     */     
/* 133 */     String play_item_name = entity.getPlay_item_name();
/* 134 */     if (play_item_name != null) {
/* 135 */       stmt.bindString(5, play_item_name);
/*     */     }
/*     */     
/* 138 */     String play_item_type = entity.getPlay_item_type();
/* 139 */     if (play_item_type != null) {
/* 140 */       stmt.bindString(6, play_item_type);
/*     */     }
/*     */     
/* 143 */     String resolution = entity.getResolution();
/* 144 */     if (resolution != null) {
/* 145 */       stmt.bindString(7, resolution);
/*     */     }
/* 147 */     stmt.bindLong(8, entity.getSize());
/*     */     
/* 149 */     String checksum = entity.getChecksum();
/* 150 */     if (checksum != null) {
/* 151 */       stmt.bindString(9, checksum);
/*     */     }
/*     */     
/* 154 */     String file_md5 = entity.getFile_md5();
/* 155 */     if (file_md5 != null) {
/* 156 */       stmt.bindString(10, file_md5);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 162 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
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
/*     */   public PhoneVideoPlayItemListBean readEntity(Cursor cursor, int offset) {
/* 177 */     PhoneVideoPlayItemListBean entity = new PhoneVideoPlayItemListBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.getLong(offset + 2), cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), cursor.getInt(offset + 7), cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */     
/* 179 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, PhoneVideoPlayItemListBean entity, int offset) {
/* 184 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 185 */     entity.setItem_id(cursor.getLong(offset + 1));
/* 186 */     entity.setLink_id(cursor.getLong(offset + 2));
/* 187 */     entity.setFile_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
/* 188 */     entity.setPlay_item_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
/* 189 */     entity.setPlay_item_type(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
/* 190 */     entity.setResolution(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
/* 191 */     entity.setSize(cursor.getInt(offset + 7));
/* 192 */     entity.setChecksum(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
/* 193 */     entity.setFile_md5(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(PhoneVideoPlayItemListBean entity, long rowId) {
/* 198 */     entity.setId(Long.valueOf(rowId));
/* 199 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(PhoneVideoPlayItemListBean entity) {
/* 204 */     if (entity != null) {
/* 205 */       return entity.getId();
/*     */     }
/* 207 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(PhoneVideoPlayItemListBean entity) {
/* 213 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 218 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PhoneVideoPlayItemListBean> _queryPhonePlayAreaListBean_VideoPlayItemList(long link_id) {
/* 223 */     synchronized (this) {
/* 224 */       if (this.phonePlayAreaListBean_VideoPlayItemListQuery == null) {
/* 225 */         QueryBuilder<PhoneVideoPlayItemListBean> queryBuilder = queryBuilder();
/* 226 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 227 */         this.phonePlayAreaListBean_VideoPlayItemListQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 230 */     Query<PhoneVideoPlayItemListBean> query = this.phonePlayAreaListBean_VideoPlayItemListQuery.forCurrentThread();
/* 231 */     query.setParameter(0, Long.valueOf(link_id));
/* 232 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\PhoneVideoPlayItemListBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */