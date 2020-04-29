/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.ProgramCountBean;
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
/*     */ public class ProgramCountBeanDao
/*     */   extends AbstractDao<ProgramCountBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PROGRAM_COUNT_BEAN";
/*     */   private DaoSession daoSession;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  27 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  28 */     public static final Property ProgramId = new Property(1, long.class, "programId", false, "PROGRAM_ID");
/*  29 */     public static final Property SendDate = new Property(2, String.class, "sendDate", false, "SEND_DATE");
/*  30 */     public static final Property Num = new Property(3, int.class, "num", false, "NUM");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProgramCountBeanDao(DaoConfig config) {
/*  37 */     super(config);
/*     */   }
/*     */   
/*     */   public ProgramCountBeanDao(DaoConfig config, DaoSession daoSession) {
/*  41 */     super(config, daoSession);
/*  42 */     this.daoSession = daoSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  47 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  48 */     db.execSQL("CREATE TABLE " + constraint + "\"PROGRAM_COUNT_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PROGRAM_ID\" INTEGER NOT NULL ,\"SEND_DATE\" TEXT,\"NUM\" INTEGER NOT NULL );");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropTable(Database db, boolean ifExists) {
/*  57 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROGRAM_COUNT_BEAN\"";
/*  58 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, ProgramCountBean entity) {
/*  63 */     stmt.clearBindings();
/*     */     
/*  65 */     Long id = entity.getId();
/*  66 */     if (id != null) {
/*  67 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  69 */     stmt.bindLong(2, entity.getProgramId());
/*     */     
/*  71 */     String sendDate = entity.getSendDate();
/*  72 */     if (sendDate != null) {
/*  73 */       stmt.bindString(3, sendDate);
/*     */     }
/*  75 */     stmt.bindLong(4, entity.getNum());
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, ProgramCountBean entity) {
/*  80 */     stmt.clearBindings();
/*     */     
/*  82 */     Long id = entity.getId();
/*  83 */     if (id != null) {
/*  84 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  86 */     stmt.bindLong(2, entity.getProgramId());
/*     */     
/*  88 */     String sendDate = entity.getSendDate();
/*  89 */     if (sendDate != null) {
/*  90 */       stmt.bindString(3, sendDate);
/*     */     }
/*  92 */     stmt.bindLong(4, entity.getNum());
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void attachEntity(ProgramCountBean entity) {
/*  97 */     super.attachEntity(entity);
/*  98 */     entity.__setDaoSession(this.daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 103 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProgramCountBean readEntity(Cursor cursor, int offset) {
/* 112 */     ProgramCountBean entity = new ProgramCountBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), cursor.getInt(offset + 3));
/*     */     
/* 114 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, ProgramCountBean entity, int offset) {
/* 119 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 120 */     entity.setProgramId(cursor.getLong(offset + 1));
/* 121 */     entity.setSendDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
/* 122 */     entity.setNum(cursor.getInt(offset + 3));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(ProgramCountBean entity, long rowId) {
/* 127 */     entity.setId(Long.valueOf(rowId));
/* 128 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(ProgramCountBean entity) {
/* 133 */     if (entity != null) {
/* 134 */       return entity.getId();
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(ProgramCountBean entity) {
/* 142 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 147 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\ProgramCountBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */