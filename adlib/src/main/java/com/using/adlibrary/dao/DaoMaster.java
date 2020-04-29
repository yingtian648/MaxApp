/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.database.sqlite.SQLiteDatabase;
/*     */ import android.util.Log;
/*     */ import org.greenrobot.greendao.AbstractDaoMaster;
/*     */ import org.greenrobot.greendao.AbstractDaoSession;
/*     */ import org.greenrobot.greendao.database.Database;
/*     */ import org.greenrobot.greendao.database.DatabaseOpenHelper;
/*     */ import org.greenrobot.greendao.database.StandardDatabase;
/*     */ import org.greenrobot.greendao.identityscope.IdentityScopeType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DaoMaster
/*     */   extends AbstractDaoMaster
/*     */ {
/*     */   public static final int SCHEMA_VERSION = 5;
/*     */   
/*     */   public static void createAllTables(Database db, boolean ifNotExists) {
/*  24 */     ImagePlayItemListDaoBeanDao.createTable(db, ifNotExists);
/*  25 */     ImmediateProgramListDaoBeanDao.createTable(db, ifNotExists);
/*  26 */     MainProgramListDaoBeanDao.createTable(db, ifNotExists);
/*  27 */     MaterialDaoBeanDao.createTable(db, ifNotExists);
/*  28 */     PlayAreaListDaoBeanDao.createTable(db, ifNotExists);
/*  29 */     PlayListMapDaoBeanDao.createTable(db, ifNotExists);
/*  30 */     ProgramCountBeanDao.createTable(db, ifNotExists);
/*  31 */     RootListDaoBeanDao.createTable(db, ifNotExists);
/*  32 */     TaskPackageCountBeanDao.createTable(db, ifNotExists);
/*  33 */     TimingProgramListDaoBeanDao.createTable(db, ifNotExists);
/*  34 */     VideoPlayItemListDaoBeanDao.createTable(db, ifNotExists);
/*  35 */     PhoneImagePlayItemListBeanDao.createTable(db, ifNotExists);
/*  36 */     PhoneMainProgramListBeanDao.createTable(db, ifNotExists);
/*  37 */     PhonePlayAreaListBeanDao.createTable(db, ifNotExists);
/*  38 */     PhoneRootListBeanDao.createTable(db, ifNotExists);
/*  39 */     PhoneVideoPlayItemListBeanDao.createTable(db, ifNotExists);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropAllTables(Database db, boolean ifExists) {
/*  44 */     ImagePlayItemListDaoBeanDao.dropTable(db, ifExists);
/*  45 */     ImmediateProgramListDaoBeanDao.dropTable(db, ifExists);
/*  46 */     MainProgramListDaoBeanDao.dropTable(db, ifExists);
/*  47 */     MaterialDaoBeanDao.dropTable(db, ifExists);
/*  48 */     PlayAreaListDaoBeanDao.dropTable(db, ifExists);
/*  49 */     PlayListMapDaoBeanDao.dropTable(db, ifExists);
/*  50 */     ProgramCountBeanDao.dropTable(db, ifExists);
/*  51 */     RootListDaoBeanDao.dropTable(db, ifExists);
/*  52 */     TaskPackageCountBeanDao.dropTable(db, ifExists);
/*  53 */     TimingProgramListDaoBeanDao.dropTable(db, ifExists);
/*  54 */     VideoPlayItemListDaoBeanDao.dropTable(db, ifExists);
/*  55 */     PhoneImagePlayItemListBeanDao.dropTable(db, ifExists);
/*  56 */     PhoneMainProgramListBeanDao.dropTable(db, ifExists);
/*  57 */     PhonePlayAreaListBeanDao.dropTable(db, ifExists);
/*  58 */     PhoneRootListBeanDao.dropTable(db, ifExists);
/*  59 */     PhoneVideoPlayItemListBeanDao.dropTable(db, ifExists);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DaoSession newDevSession(Context context, String name) {
/*  67 */     Database db = (new DevOpenHelper(context, name)).getWritableDb();
/*  68 */     DaoMaster daoMaster = new DaoMaster(db);
/*  69 */     return daoMaster.newSession();
/*     */   }
/*     */   
/*     */   public DaoMaster(SQLiteDatabase db) {
/*  73 */     this((Database)new StandardDatabase(db));
/*     */   }
/*     */   
/*     */   public DaoMaster(Database db) {
/*  77 */     super(db, 5);
/*  78 */     registerDaoClass(ImagePlayItemListDaoBeanDao.class);
/*  79 */     registerDaoClass(ImmediateProgramListDaoBeanDao.class);
/*  80 */     registerDaoClass(MainProgramListDaoBeanDao.class);
/*  81 */     registerDaoClass(MaterialDaoBeanDao.class);
/*  82 */     registerDaoClass(PlayAreaListDaoBeanDao.class);
/*  83 */     registerDaoClass(PlayListMapDaoBeanDao.class);
/*  84 */     registerDaoClass(ProgramCountBeanDao.class);
/*  85 */     registerDaoClass(RootListDaoBeanDao.class);
/*  86 */     registerDaoClass(TaskPackageCountBeanDao.class);
/*  87 */     registerDaoClass(TimingProgramListDaoBeanDao.class);
/*  88 */     registerDaoClass(VideoPlayItemListDaoBeanDao.class);
/*  89 */     registerDaoClass(PhoneImagePlayItemListBeanDao.class);
/*  90 */     registerDaoClass(PhoneMainProgramListBeanDao.class);
/*  91 */     registerDaoClass(PhonePlayAreaListBeanDao.class);
/*  92 */     registerDaoClass(PhoneRootListBeanDao.class);
/*  93 */     registerDaoClass(PhoneVideoPlayItemListBeanDao.class);
/*     */   }
/*     */   
/*     */   public DaoSession newSession() {
/*  97 */     return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
/*     */   }
/*     */   
/*     */   public DaoSession newSession(IdentityScopeType type) {
/* 101 */     return new DaoSession(this.db, type, this.daoConfigMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public static abstract class OpenHelper
/*     */     extends DatabaseOpenHelper
/*     */   {
/*     */     public OpenHelper(Context context, String name) {
/* 109 */       super(context, name, 5);
/*     */     }
/*     */     
/*     */     public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
/* 113 */       super(context, name, factory, 5);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onCreate(Database db) {
/* 118 */       Log.i("greenDAO", "Creating tables for schema version 5");
/* 119 */       DaoMaster.createAllTables(db, false);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DevOpenHelper
/*     */     extends OpenHelper {
/*     */     public DevOpenHelper(Context context, String name) {
/* 126 */       super(context, name);
/*     */     }
/*     */     
/*     */     public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
/* 130 */       super(context, name, factory);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpgrade(Database db, int oldVersion, int newVersion) {
/* 135 */       Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
/* 136 */       DaoMaster.dropAllTables(db, true);
/* 137 */       onCreate(db);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\DaoMaster.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */