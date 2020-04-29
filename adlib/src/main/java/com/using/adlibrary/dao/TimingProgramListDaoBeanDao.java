/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.TimingProgramListDaoBean;
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
/*     */ public class TimingProgramListDaoBeanDao
/*     */   extends AbstractDao<TimingProgramListDaoBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "TIMING_PROGRAM_LIST_DAO_BEAN";
/*     */   private DaoSession daoSession;
/*     */   private Query<TimingProgramListDaoBean> rootListDaoBean_TimingProgramListQuery;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  30 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  31 */     public static final Property Play_list_id = new Property(1, long.class, "play_list_id", false, "PLAY_LIST_ID");
/*  32 */     public static final Property Link_id = new Property(2, long.class, "link_id", false, "LINK_ID");
/*  33 */     public static final Property Play_list_name = new Property(3, String.class, "play_list_name", false, "PLAY_LIST_NAME");
/*  34 */     public static final Property Resolution = new Property(4, String.class, "resolution", false, "RESOLUTION");
/*  35 */     public static final Property Hororver = new Property(5, String.class, "hororver", false, "HORORVER");
/*  36 */     public static final Property BeginTime = new Property(6, String.class, "beginTime", false, "BEGIN_TIME");
/*  37 */     public static final Property EndTime = new Property(7, String.class, "endTime", false, "END_TIME");
/*  38 */     public static final Property PlayDays = new Property(8, long.class, "playDays", false, "PLAY_DAYS");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimingProgramListDaoBeanDao(DaoConfig config) {
/*  46 */     super(config);
/*     */   }
/*     */   
/*     */   public TimingProgramListDaoBeanDao(DaoConfig config, DaoSession daoSession) {
/*  50 */     super(config, daoSession);
/*  51 */     this.daoSession = daoSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  56 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  57 */     db.execSQL("CREATE TABLE " + constraint + "\"TIMING_PROGRAM_LIST_DAO_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PLAY_LIST_ID\" INTEGER NOT NULL ,\"LINK_ID\" INTEGER NOT NULL ,\"PLAY_LIST_NAME\" TEXT,\"RESOLUTION\" TEXT,\"HORORVER\" TEXT,\"BEGIN_TIME\" TEXT,\"END_TIME\" TEXT,\"PLAY_DAYS\" INTEGER NOT NULL );");
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
/*  71 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TIMING_PROGRAM_LIST_DAO_BEAN\"";
/*  72 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, TimingProgramListDaoBean entity) {
/*  77 */     stmt.clearBindings();
/*     */     
/*  79 */     Long id = entity.getId();
/*  80 */     if (id != null) {
/*  81 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  83 */     stmt.bindLong(2, entity.getPlay_list_id());
/*  84 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/*  86 */     String play_list_name = entity.getPlay_list_name();
/*  87 */     if (play_list_name != null) {
/*  88 */       stmt.bindString(4, play_list_name);
/*     */     }
/*     */     
/*  91 */     String resolution = entity.getResolution();
/*  92 */     if (resolution != null) {
/*  93 */       stmt.bindString(5, resolution);
/*     */     }
/*     */     
/*  96 */     String hororver = entity.getHororver();
/*  97 */     if (hororver != null) {
/*  98 */       stmt.bindString(6, hororver);
/*     */     }
/*     */     
/* 101 */     String beginTime = entity.getBeginTime();
/* 102 */     if (beginTime != null) {
/* 103 */       stmt.bindString(7, beginTime);
/*     */     }
/*     */     
/* 106 */     String endTime = entity.getEndTime();
/* 107 */     if (endTime != null) {
/* 108 */       stmt.bindString(8, endTime);
/*     */     }
/* 110 */     stmt.bindLong(9, entity.getPlayDays());
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, TimingProgramListDaoBean entity) {
/* 115 */     stmt.clearBindings();
/*     */     
/* 117 */     Long id = entity.getId();
/* 118 */     if (id != null) {
/* 119 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 121 */     stmt.bindLong(2, entity.getPlay_list_id());
/* 122 */     stmt.bindLong(3, entity.getLink_id());
/*     */     
/* 124 */     String play_list_name = entity.getPlay_list_name();
/* 125 */     if (play_list_name != null) {
/* 126 */       stmt.bindString(4, play_list_name);
/*     */     }
/*     */     
/* 129 */     String resolution = entity.getResolution();
/* 130 */     if (resolution != null) {
/* 131 */       stmt.bindString(5, resolution);
/*     */     }
/*     */     
/* 134 */     String hororver = entity.getHororver();
/* 135 */     if (hororver != null) {
/* 136 */       stmt.bindString(6, hororver);
/*     */     }
/*     */     
/* 139 */     String beginTime = entity.getBeginTime();
/* 140 */     if (beginTime != null) {
/* 141 */       stmt.bindString(7, beginTime);
/*     */     }
/*     */     
/* 144 */     String endTime = entity.getEndTime();
/* 145 */     if (endTime != null) {
/* 146 */       stmt.bindString(8, endTime);
/*     */     }
/* 148 */     stmt.bindLong(9, entity.getPlayDays());
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void attachEntity(TimingProgramListDaoBean entity) {
/* 153 */     super.attachEntity(entity);
/* 154 */     entity.__setDaoSession(this.daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 159 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
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
/*     */   public TimingProgramListDaoBean readEntity(Cursor cursor, int offset) {
/* 173 */     TimingProgramListDaoBean entity = new TimingProgramListDaoBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.getLong(offset + 2), cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), cursor.getLong(offset + 8));
/*     */     
/* 175 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, TimingProgramListDaoBean entity, int offset) {
/* 180 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 181 */     entity.setPlay_list_id(cursor.getLong(offset + 1));
/* 182 */     entity.setLink_id(cursor.getLong(offset + 2));
/* 183 */     entity.setPlay_list_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
/* 184 */     entity.setResolution(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
/* 185 */     entity.setHororver(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
/* 186 */     entity.setBeginTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
/* 187 */     entity.setEndTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
/* 188 */     entity.setPlayDays(cursor.getLong(offset + 8));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(TimingProgramListDaoBean entity, long rowId) {
/* 193 */     entity.setId(Long.valueOf(rowId));
/* 194 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(TimingProgramListDaoBean entity) {
/* 199 */     if (entity != null) {
/* 200 */       return entity.getId();
/*     */     }
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(TimingProgramListDaoBean entity) {
/* 208 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 213 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TimingProgramListDaoBean> _queryRootListDaoBean_TimingProgramList(long link_id) {
/* 218 */     synchronized (this) {
/* 219 */       if (this.rootListDaoBean_TimingProgramListQuery == null) {
/* 220 */         QueryBuilder<TimingProgramListDaoBean> queryBuilder = queryBuilder();
/* 221 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 222 */         this.rootListDaoBean_TimingProgramListQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 225 */     Query<TimingProgramListDaoBean> query = this.rootListDaoBean_TimingProgramListQuery.forCurrentThread();
/* 226 */     query.setParameter(0, Long.valueOf(link_id));
/* 227 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\TimingProgramListDaoBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */