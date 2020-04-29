/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.phone.PhonePlayAreaListBean;
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
/*     */ public class PhonePlayAreaListBeanDao
/*     */   extends AbstractDao<PhonePlayAreaListBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PHONE_PLAY_AREA_LIST_BEAN";
/*     */   private DaoSession daoSession;
/*     */   private Query<PhonePlayAreaListBean> phoneMainProgramListBean_Playarea_listQuery;
/*     */   
/*     */   public static class Properties
/*     */   {
/*  30 */     public static final Property Id = new Property(0, Long.class, "id", true, "_id");
/*  31 */     public static final Property Area_id = new Property(1, long.class, "area_id", false, "AREA_ID");
/*  32 */     public static final Property Area_name = new Property(2, String.class, "area_name", false, "AREA_NAME");
/*  33 */     public static final Property Link_id = new Property(3, long.class, "link_id", false, "LINK_ID");
/*  34 */     public static final Property StartX = new Property(4, int.class, "startX", false, "START_X");
/*  35 */     public static final Property StartY = new Property(5, int.class, "startY", false, "START_Y");
/*  36 */     public static final Property Width = new Property(6, int.class, "width", false, "WIDTH");
/*  37 */     public static final Property Height = new Property(7, int.class, "height", false, "HEIGHT");
/*  38 */     public static final Property Layer = new Property(8, int.class, "layer", false, "LAYER");
/*  39 */     public static final Property Area_type = new Property(9, String.class, "area_type", false, "AREA_TYPE");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PhonePlayAreaListBeanDao(DaoConfig config) {
/*  47 */     super(config);
/*     */   }
/*     */   
/*     */   public PhonePlayAreaListBeanDao(DaoConfig config, DaoSession daoSession) {
/*  51 */     super(config, daoSession);
/*  52 */     this.daoSession = daoSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  57 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  58 */     db.execSQL("CREATE TABLE " + constraint + "\"PHONE_PLAY_AREA_LIST_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"AREA_ID\" INTEGER NOT NULL ,\"AREA_NAME\" TEXT,\"LINK_ID\" INTEGER NOT NULL ,\"START_X\" INTEGER NOT NULL ,\"START_Y\" INTEGER NOT NULL ,\"WIDTH\" INTEGER NOT NULL ,\"HEIGHT\" INTEGER NOT NULL ,\"LAYER\" INTEGER NOT NULL ,\"AREA_TYPE\" TEXT);");
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
/*  73 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PHONE_PLAY_AREA_LIST_BEAN\"";
/*  74 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, PhonePlayAreaListBean entity) {
/*  79 */     stmt.clearBindings();
/*     */     
/*  81 */     Long id = entity.getId();
/*  82 */     if (id != null) {
/*  83 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  85 */     stmt.bindLong(2, entity.getArea_id());
/*     */     
/*  87 */     String area_name = entity.getArea_name();
/*  88 */     if (area_name != null) {
/*  89 */       stmt.bindString(3, area_name);
/*     */     }
/*  91 */     stmt.bindLong(4, entity.getLink_id());
/*  92 */     stmt.bindLong(5, entity.getStartX());
/*  93 */     stmt.bindLong(6, entity.getStartY());
/*  94 */     stmt.bindLong(7, entity.getWidth());
/*  95 */     stmt.bindLong(8, entity.getHeight());
/*  96 */     stmt.bindLong(9, entity.getLayer());
/*     */     
/*  98 */     String area_type = entity.getArea_type();
/*  99 */     if (area_type != null) {
/* 100 */       stmt.bindString(10, area_type);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, PhonePlayAreaListBean entity) {
/* 106 */     stmt.clearBindings();
/*     */     
/* 108 */     Long id = entity.getId();
/* 109 */     if (id != null) {
/* 110 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 112 */     stmt.bindLong(2, entity.getArea_id());
/*     */     
/* 114 */     String area_name = entity.getArea_name();
/* 115 */     if (area_name != null) {
/* 116 */       stmt.bindString(3, area_name);
/*     */     }
/* 118 */     stmt.bindLong(4, entity.getLink_id());
/* 119 */     stmt.bindLong(5, entity.getStartX());
/* 120 */     stmt.bindLong(6, entity.getStartY());
/* 121 */     stmt.bindLong(7, entity.getWidth());
/* 122 */     stmt.bindLong(8, entity.getHeight());
/* 123 */     stmt.bindLong(9, entity.getLayer());
/*     */     
/* 125 */     String area_type = entity.getArea_type();
/* 126 */     if (area_type != null) {
/* 127 */       stmt.bindString(10, area_type);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void attachEntity(PhonePlayAreaListBean entity) {
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
/*     */   
/*     */   public PhonePlayAreaListBean readEntity(Cursor cursor, int offset) {
/* 154 */     PhonePlayAreaListBean entity = new PhonePlayAreaListBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), cursor.getLong(offset + 3), cursor.getInt(offset + 4), cursor.getInt(offset + 5), cursor.getInt(offset + 6), cursor.getInt(offset + 7), cursor.getInt(offset + 8), cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */     
/* 156 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, PhonePlayAreaListBean entity, int offset) {
/* 161 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 162 */     entity.setArea_id(cursor.getLong(offset + 1));
/* 163 */     entity.setArea_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
/* 164 */     entity.setLink_id(cursor.getLong(offset + 3));
/* 165 */     entity.setStartX(cursor.getInt(offset + 4));
/* 166 */     entity.setStartY(cursor.getInt(offset + 5));
/* 167 */     entity.setWidth(cursor.getInt(offset + 6));
/* 168 */     entity.setHeight(cursor.getInt(offset + 7));
/* 169 */     entity.setLayer(cursor.getInt(offset + 8));
/* 170 */     entity.setArea_type(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(PhonePlayAreaListBean entity, long rowId) {
/* 175 */     entity.setId(Long.valueOf(rowId));
/* 176 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(PhonePlayAreaListBean entity) {
/* 181 */     if (entity != null) {
/* 182 */       return entity.getId();
/*     */     }
/* 184 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(PhonePlayAreaListBean entity) {
/* 190 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PhonePlayAreaListBean> _queryPhoneMainProgramListBean_Playarea_list(long link_id) {
/* 200 */     synchronized (this) {
/* 201 */       if (this.phoneMainProgramListBean_Playarea_listQuery == null) {
/* 202 */         QueryBuilder<PhonePlayAreaListBean> queryBuilder = queryBuilder();
/* 203 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 204 */         this.phoneMainProgramListBean_Playarea_listQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 207 */     Query<PhonePlayAreaListBean> query = this.phoneMainProgramListBean_Playarea_listQuery.forCurrentThread();
/* 208 */     query.setParameter(0, Long.valueOf(link_id));
/* 209 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\PhonePlayAreaListBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */