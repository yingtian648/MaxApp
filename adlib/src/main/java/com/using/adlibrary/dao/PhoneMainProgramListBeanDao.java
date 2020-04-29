/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.phone.PhoneMainProgramListBean;
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
/*     */ public class PhoneMainProgramListBeanDao
/*     */   extends AbstractDao<PhoneMainProgramListBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PHONE_MAIN_PROGRAM_LIST_BEAN";
/*     */   private DaoSession daoSession;
/*     */   private Query<PhoneMainProgramListBean> phoneRootListBean_MainProgramListQuery;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  30 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  31 */     public static final Property Play_list_id = new Property(1, long.class, "play_list_id", false, "PLAY_LIST_ID");
/*  32 */     public static final Property Link_id = new Property(2, long.class, "link_id", false, "LINK_ID");
/*  33 */     public static final Property Play_list_name = new Property(3, String.class, "play_list_name", false, "PLAY_LIST_NAME");
/*  34 */     public static final Property Resolution = new Property(4, String.class, "resolution", false, "RESOLUTION");
/*  35 */     public static final Property Hororver = new Property(5, String.class, "hororver", false, "HORORVER");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PhoneMainProgramListBeanDao(DaoConfig config) {
/*  43 */     super(config);
/*     */   }
/*     */   
/*     */   public PhoneMainProgramListBeanDao(DaoConfig config, DaoSession daoSession) {
/*  47 */     super(config, daoSession);
/*  48 */     this.daoSession = daoSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  53 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  54 */     db.execSQL("CREATE TABLE " + constraint + "\"PHONE_MAIN_PROGRAM_LIST_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PLAY_LIST_ID\" INTEGER NOT NULL ,\"LINK_ID\" INTEGER NOT NULL ,\"PLAY_LIST_NAME\" TEXT,\"RESOLUTION\" TEXT,\"HORORVER\" TEXT);");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropTable(Database db, boolean ifExists) {
/*  65 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_MAIN_PROGRAM_LIST_BEAN\"";
/*  66 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, PhoneMainProgramListBean entity) {
/*  71 */     stmt.clearBindings();
/*     */     
/*  73 */     Long id = entity.getId();
/*  74 */     if (id != null) {
/*  75 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  77 */     stmt.bindLong(2, entity.getPlay_list_id());
/*  78 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/*  80 */     String play_list_name = entity.getPlay_list_name();
/*  81 */     if (play_list_name != null) {
/*  82 */       stmt.bindString(4, play_list_name);
/*     */     }
/*     */     
/*  85 */     String resolution = entity.getResolution();
/*  86 */     if (resolution != null) {
/*  87 */       stmt.bindString(5, resolution);
/*     */     }
/*     */     
/*  90 */     String hororver = entity.getHororver();
/*  91 */     if (hororver != null) {
/*  92 */       stmt.bindString(6, hororver);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, PhoneMainProgramListBean entity) {
/*  98 */     stmt.clearBindings();
/*     */     
/* 100 */     Long id = entity.getId();
/* 101 */     if (id != null) {
/* 102 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 104 */     stmt.bindLong(2, entity.getPlay_list_id());
/* 105 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/* 107 */     String play_list_name = entity.getPlay_list_name();
/* 108 */     if (play_list_name != null) {
/* 109 */       stmt.bindString(4, play_list_name);
/*     */     }
/*     */     
/* 112 */     String resolution = entity.getResolution();
/* 113 */     if (resolution != null) {
/* 114 */       stmt.bindString(5, resolution);
/*     */     }
/*     */     
/* 117 */     String hororver = entity.getHororver();
/* 118 */     if (hororver != null) {
/* 119 */       stmt.bindString(6, hororver);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void attachEntity(PhoneMainProgramListBean entity) {
/* 125 */     super.attachEntity(entity);
/* 126 */     entity.__setDaoSession(this.daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 131 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PhoneMainProgramListBean readEntity(Cursor cursor, int offset) {
/* 142 */     PhoneMainProgramListBean entity = new PhoneMainProgramListBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.getLong(offset + 2), cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
/*     */     
/* 144 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, PhoneMainProgramListBean entity, int offset) {
/* 149 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 150 */     entity.setPlay_list_id(cursor.getLong(offset + 1));
/* 151 */     entity.setLink_id(cursor.getLong(offset + 2));
/* 152 */     entity.setPlay_list_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
/* 153 */     entity.setResolution(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
/* 154 */     entity.setHororver(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(PhoneMainProgramListBean entity, long rowId) {
/* 159 */     entity.setId(Long.valueOf(rowId));
/* 160 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(PhoneMainProgramListBean entity) {
/* 165 */     if (entity != null) {
/* 166 */       return entity.getId();
/*     */     }
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(PhoneMainProgramListBean entity) {
/* 174 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PhoneMainProgramListBean> _queryPhoneRootListBean_MainProgramList(long link_id) {
/* 184 */     synchronized (this) {
/* 185 */       if (this.phoneRootListBean_MainProgramListQuery == null) {
/* 186 */         QueryBuilder<PhoneMainProgramListBean> queryBuilder = queryBuilder();
/* 187 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 188 */         this.phoneRootListBean_MainProgramListQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 191 */     Query<PhoneMainProgramListBean> query = this.phoneRootListBean_MainProgramListQuery.forCurrentThread();
/* 192 */     query.setParameter(0, Long.valueOf(link_id));
/* 193 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\PhoneMainProgramListBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */