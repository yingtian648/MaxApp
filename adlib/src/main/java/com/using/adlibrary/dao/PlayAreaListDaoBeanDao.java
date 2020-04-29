/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.database.Cursor;
/*     */ import android.database.sqlite.SQLiteStatement;
/*     */ import com.using.adlibrary.bean.dao.PlayAreaListDaoBean;
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
/*     */ public class PlayAreaListDaoBeanDao
/*     */   extends AbstractDao<PlayAreaListDaoBean, Long>
/*     */ {
/*     */   public static final String TABLENAME = "PLAY_AREA_LIST_DAO_BEAN";
/*     */   private DaoSession daoSession;
/*     */   private Query<PlayAreaListDaoBean> immediateProgramListDaoBean_Playarea_listQuery;
/*     */   private Query<PlayAreaListDaoBean> mainProgramListDaoBean_Playarea_listQuery;
/*     */   private Query<PlayAreaListDaoBean> timingProgramListDaoBean_Playarea_listQuery;
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
/*     */ 
/*     */   
/*     */   public PlayAreaListDaoBeanDao(DaoConfig config) {
/*  49 */     super(config);
/*     */   }
/*     */   
/*     */   public PlayAreaListDaoBeanDao(DaoConfig config, DaoSession daoSession) {
/*  53 */     super(config, daoSession);
/*  54 */     this.daoSession = daoSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void createTable(Database db, boolean ifNotExists) {
/*  59 */     String constraint = ifNotExists ? "IF NOT EXISTS " : "";
/*  60 */     db.execSQL("CREATE TABLE " + constraint + "\"PLAY_AREA_LIST_DAO_BEAN\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"AREA_ID\" INTEGER NOT NULL ,\"AREA_NAME\" TEXT,\"LINK_ID\" INTEGER NOT NULL ,\"START_X\" INTEGER NOT NULL ,\"START_Y\" INTEGER NOT NULL ,\"WIDTH\" INTEGER NOT NULL ,\"HEIGHT\" INTEGER NOT NULL ,\"LAYER\" INTEGER NOT NULL ,\"AREA_TYPE\" TEXT);");
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
/*  75 */     String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAY_AREA_LIST_DAO_BEAN\"";
/*  76 */     db.execSQL(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(DatabaseStatement stmt, PlayAreaListDaoBean entity) {
/*  81 */     stmt.clearBindings();
/*     */     
/*  83 */     Long id = entity.getId();
/*  84 */     if (id != null) {
/*  85 */       stmt.bindLong(1, id.longValue());
/*     */     }
/*  87 */     stmt.bindLong(2, entity.getArea_id());
/*     */     
/*  89 */     String area_name = entity.getArea_name();
/*  90 */     if (area_name != null) {
/*  91 */       stmt.bindString(3, area_name);
/*     */     }
/*  93 */     stmt.bindLong(4, entity.getLink_id());
/*  94 */     stmt.bindLong(5, entity.getStartX());
/*  95 */     stmt.bindLong(6, entity.getStartY());
/*  96 */     stmt.bindLong(7, entity.getWidth());
/*  97 */     stmt.bindLong(8, entity.getHeight());
/*  98 */     stmt.bindLong(9, entity.getLayer());
/*     */     
/* 100 */     String area_type = entity.getArea_type();
/* 101 */     if (area_type != null) {
/* 102 */       stmt.bindString(10, area_type);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void bindValues(SQLiteStatement stmt, PlayAreaListDaoBean entity) {
/* 108 */     stmt.clearBindings();
/*     */     
/* 110 */     Long id = entity.getId();
/* 111 */     if (id != null) {
/* 112 */       stmt.bindLong(1, id.longValue());
/*     */     }
/* 114 */     stmt.bindLong(2, entity.getArea_id());
/*     */     
/* 116 */     String area_name = entity.getArea_name();
/* 117 */     if (area_name != null) {
/* 118 */       stmt.bindString(3, area_name);
/*     */     }
/* 120 */     stmt.bindLong(4, entity.getLink_id());
/* 121 */     stmt.bindLong(5, entity.getStartX());
/* 122 */     stmt.bindLong(6, entity.getStartY());
/* 123 */     stmt.bindLong(7, entity.getWidth());
/* 124 */     stmt.bindLong(8, entity.getHeight());
/* 125 */     stmt.bindLong(9, entity.getLayer());
/*     */     
/* 127 */     String area_type = entity.getArea_type();
/* 128 */     if (area_type != null) {
/* 129 */       stmt.bindString(10, area_type);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void attachEntity(PlayAreaListDaoBean entity) {
/* 135 */     super.attachEntity(entity);
/* 136 */     entity.__setDaoSession(this.daoSession);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long readKey(Cursor cursor, int offset) {
/* 141 */     return cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0));
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
/*     */   public PlayAreaListDaoBean readEntity(Cursor cursor, int offset) {
/* 156 */     PlayAreaListDaoBean entity = new PlayAreaListDaoBean(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)), cursor.getLong(offset + 1), cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), cursor.getLong(offset + 3), cursor.getInt(offset + 4), cursor.getInt(offset + 5), cursor.getInt(offset + 6), cursor.getInt(offset + 7), cursor.getInt(offset + 8), cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */     
/* 158 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntity(Cursor cursor, PlayAreaListDaoBean entity, int offset) {
/* 163 */     entity.setId(cursor.isNull(offset + 0) ? null : Long.valueOf(cursor.getLong(offset + 0)));
/* 164 */     entity.setArea_id(cursor.getLong(offset + 1));
/* 165 */     entity.setArea_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
/* 166 */     entity.setLink_id(cursor.getLong(offset + 3));
/* 167 */     entity.setStartX(cursor.getInt(offset + 4));
/* 168 */     entity.setStartY(cursor.getInt(offset + 5));
/* 169 */     entity.setWidth(cursor.getInt(offset + 6));
/* 170 */     entity.setHeight(cursor.getInt(offset + 7));
/* 171 */     entity.setLayer(cursor.getInt(offset + 8));
/* 172 */     entity.setArea_type(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
/*     */   }
/*     */ 
/*     */   
/*     */   protected final Long updateKeyAfterInsert(PlayAreaListDaoBean entity, long rowId) {
/* 177 */     entity.setId(Long.valueOf(rowId));
/* 178 */     return Long.valueOf(rowId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Long getKey(PlayAreaListDaoBean entity) {
/* 183 */     if (entity != null) {
/* 184 */       return entity.getId();
/*     */     }
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(PlayAreaListDaoBean entity) {
/* 192 */     return (entity.getId() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isEntityUpdateable() {
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PlayAreaListDaoBean> _queryImmediateProgramListDaoBean_Playarea_list(long link_id) {
/* 202 */     synchronized (this) {
/* 203 */       if (this.immediateProgramListDaoBean_Playarea_listQuery == null) {
/* 204 */         QueryBuilder<PlayAreaListDaoBean> queryBuilder = queryBuilder();
/* 205 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 206 */         this.immediateProgramListDaoBean_Playarea_listQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 209 */     Query<PlayAreaListDaoBean> query = this.immediateProgramListDaoBean_Playarea_listQuery.forCurrentThread();
/* 210 */     query.setParameter(0, Long.valueOf(link_id));
/* 211 */     return query.list();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PlayAreaListDaoBean> _queryMainProgramListDaoBean_Playarea_list(long link_id) {
/* 216 */     synchronized (this) {
/* 217 */       if (this.mainProgramListDaoBean_Playarea_listQuery == null) {
/* 218 */         QueryBuilder<PlayAreaListDaoBean> queryBuilder = queryBuilder();
/* 219 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 220 */         this.mainProgramListDaoBean_Playarea_listQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 223 */     Query<PlayAreaListDaoBean> query = this.mainProgramListDaoBean_Playarea_listQuery.forCurrentThread();
/* 224 */     query.setParameter(0, Long.valueOf(link_id));
/* 225 */     return query.list();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PlayAreaListDaoBean> _queryTimingProgramListDaoBean_Playarea_list(long link_id) {
/* 230 */     synchronized (this) {
/* 231 */       if (this.timingProgramListDaoBean_Playarea_listQuery == null) {
/* 232 */         QueryBuilder<PlayAreaListDaoBean> queryBuilder = queryBuilder();
/* 233 */         queryBuilder.where(Properties.Link_id.eq(null), new org.greenrobot.greendao.query.WhereCondition[0]);
/* 234 */         this.timingProgramListDaoBean_Playarea_listQuery = queryBuilder.build();
/*     */       } 
/*     */     } 
/* 237 */     Query<PlayAreaListDaoBean> query = this.timingProgramListDaoBean_Playarea_listQuery.forCurrentThread();
/* 238 */     query.setParameter(0, Long.valueOf(link_id));
/* 239 */     return query.list();
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\PlayAreaListDaoBeanDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */