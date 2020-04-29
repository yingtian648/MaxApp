/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.TaskPackageCountBean;
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
/*     */ public class TaskPackageCountBeanDao
/*     */   extends AbstractDao<TaskPackageCountBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "TASK_PACKAGE_COUNT_BEAN";
/*     */   private Query<TaskPackageCountBean> programCountBean_TaskCountsQuery;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  30 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  31 */     public static final Property Link_id = new Property(1, Long.class, "link_id", false, "LINK_ID");
/*  32 */     public static final Property TaskId = new Property(2, long.class, "taskId", false, "TASK_ID");
/*  33 */     public static final Property Type = new Property(3, int.class, "type", false, "TYPE");
/*  34 */     public static final Property Num = new Property(4, int.class, "num", false, "NUM");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TaskPackageCountBeanDao(DaoConfig config) {
/*  40 */     super(config);
/*     */   }
/*     */   
/*     */   public TaskPackageCountBeanDao(DaoConfig config, DaoSession daoSession) {
/*  44 */     super(config, daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  49 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  50 */     db.execSQL("CREATE TABLE " + constraint + "\"TASK_PACKAGE_COUNT_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"LINK_ID\" INTEGER,\"TASK_ID\" INTEGER NOT NULL ,\"TYPE\" INTEGER NOT NULL ,\"NUM\" INTEGER NOT NULL );");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dropTable(Database db, boolean ifExists) {
/*  60 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TASK_PACKAGE_COUNT_BEAN\"";
/*  61 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, TaskPackageCountBean entity) {
/*  66 */     stmt.clearBindings();
/*     */     
/*  68 */     Long id = entity.getId();
/*  69 */     if (id != null) {
/*  70 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*     */     
/*  73 */     Long link_id = entity.getLink_id();
/*  74 */     if (link_id != null) {
/*  75 */       stmt.bindLong(2, link_id.longValue());
/*     */     }
/*  77 */     stmt.bindLong(3, entity.getTaskId());
/*  78 */     stmt.bindLong(4, entity.getType());
/*  79 */     stmt.bindLong(5, entity.getNum());
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, TaskPackageCountBean entity) {
/*  84 */     stmt.clearBindings();
/*     */     
/*  86 */     Long id = entity.getId();
/*  87 */     if (id != null) {
/*  88 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*     */     
/*  91 */     Long link_id = entity.getLink_id();
/*  92 */     if (link_id != null) {
/*  93 */       stmt.bindLong(2, link_id.longValue());
/*     */     }
/*  95 */     stmt.bindLong(3, entity.getTaskId());
/*  96 */     stmt.bindLong(4, entity.getType());
/*  97 */     stmt.bindLong(5, entity.getNum());
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 102 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TaskPackageCountBean readEntity(Cursor cursor, int offset) {
/* 112 */     TaskPackageCountBean entity = new TaskPackageCountBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.isNull(offset + 1) ? null : Long.valueOf(cursor.getLong(offset + 1)), cursor.getLong(offset + 2), cursor.getInt(offset + 3), cursor.getInt(offset + 4));
/*     */     
/* 114 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, TaskPackageCountBean entity, int offset) {
/* 119 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 120 */     entity.setLink_id(cursor.isNull(offset + 1) ? null : Long.valueOf(cursor.getLong(offset + 1)));
/* 121 */     entity.setTaskId(cursor.getLong(offset + 2));
/* 122 */     entity.setType(cursor.getInt(offset + 3));
/* 123 */     entity.setNum(cursor.getInt(offset + 4));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(TaskPackageCountBean entity, long rowId) {
/* 128 */     entity.setId(Long.valueOf(rowId));
/* 129 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(TaskPackageCountBean entity) {
/* 134 */     if (entity != null) {
/* 135 */       return entity.getId();
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(TaskPackageCountBean entity) {
/* 143 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<TaskPackageCountBean> _queryProgramCountBean_TaskCounts(Long link_id) {
/* 153 */     synchronized (this) {
/* 154 */       if (this.programCountBean_TaskCountsQuery == null) {
/* 155 */         QueryBuilder<TaskPackageCountBean> queryBuilder = queryBuilder();
/* 156 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 157 */         this.programCountBean_TaskCountsQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 160 */     Query<TaskPackageCountBean> query = this.programCountBean_TaskCountsQuery.forCurrentThread();
/* 161 */     query.setParameter(0, link_id);
/* 162 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\TaskPackageCountBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */