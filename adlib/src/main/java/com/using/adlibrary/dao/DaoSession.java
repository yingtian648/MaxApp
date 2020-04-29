/*     */ package com.using.adlibrary.dao;
/*     */ 
/*     */ import com.using.adlibrary.bean.dao.ImagePlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.ImmediateProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MainProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.MaterialDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayAreaListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.PlayListMapDaoBean;
/*     */ import com.using.adlibrary.bean.dao.ProgramCountBean;
/*     */ import com.using.adlibrary.bean.dao.RootListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.TaskPackageCountBean;
/*     */ import com.using.adlibrary.bean.dao.TimingProgramListDaoBean;
/*     */ import com.using.adlibrary.bean.dao.VideoPlayItemListDaoBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneImagePlayItemListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneMainProgramListBean;
/*     */ import com.using.adlibrary.bean.phone.PhonePlayAreaListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneRootListBean;
/*     */ import com.using.adlibrary.bean.phone.PhoneVideoPlayItemListBean;
/*     */ import java.util.Map;
/*     */ import org.greenrobot.greendao.AbstractDao;
/*     */ import org.greenrobot.greendao.AbstractDaoSession;
/*     */ import org.greenrobot.greendao.database.Database;
/*     */ import org.greenrobot.greendao.identityscope.IdentityScopeType;
/*     */ import org.greenrobot.greendao.internal.DaoConfig;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DaoSession
/*     */   extends AbstractDaoSession
/*     */ {
/*     */   private final DaoConfig imagePlayItemListDaoBeanDaoConfig;
/*     */   private final DaoConfig immediateProgramListDaoBeanDaoConfig;
/*     */   private final DaoConfig mainProgramListDaoBeanDaoConfig;
/*     */   private final DaoConfig materialDaoBeanDaoConfig;
/*     */   private final DaoConfig playAreaListDaoBeanDaoConfig;
/*     */   private final DaoConfig playListMapDaoBeanDaoConfig;
/*     */   private final DaoConfig programCountBeanDaoConfig;
/*     */   private final DaoConfig rootListDaoBeanDaoConfig;
/*     */   private final DaoConfig taskPackageCountBeanDaoConfig;
/*     */   private final DaoConfig timingProgramListDaoBeanDaoConfig;
/*     */   private final DaoConfig videoPlayItemListDaoBeanDaoConfig;
/*     */   private final DaoConfig phoneImagePlayItemListBeanDaoConfig;
/*     */   private final DaoConfig phoneMainProgramListBeanDaoConfig;
/*     */   private final DaoConfig phonePlayAreaListBeanDaoConfig;
/*     */   private final DaoConfig phoneRootListBeanDaoConfig;
/*     */   private final DaoConfig phoneVideoPlayItemListBeanDaoConfig;
/*     */   private final ImagePlayItemListDaoBeanDao imagePlayItemListDaoBeanDao;
/*     */   private final ImmediateProgramListDaoBeanDao immediateProgramListDaoBeanDao;
/*     */   private final MainProgramListDaoBeanDao mainProgramListDaoBeanDao;
/*     */   private final MaterialDaoBeanDao materialDaoBeanDao;
/*     */   private final PlayAreaListDaoBeanDao playAreaListDaoBeanDao;
/*     */   private final PlayListMapDaoBeanDao playListMapDaoBeanDao;
/*     */   private final ProgramCountBeanDao programCountBeanDao;
/*     */   private final RootListDaoBeanDao rootListDaoBeanDao;
/*     */   private final TaskPackageCountBeanDao taskPackageCountBeanDao;
/*     */   private final TimingProgramListDaoBeanDao timingProgramListDaoBeanDao;
/*     */   private final VideoPlayItemListDaoBeanDao videoPlayItemListDaoBeanDao;
/*     */   private final PhoneImagePlayItemListBeanDao phoneImagePlayItemListBeanDao;
/*     */   private final PhoneMainProgramListBeanDao phoneMainProgramListBeanDao;
/*     */   private final PhonePlayAreaListBeanDao phonePlayAreaListBeanDao;
/*     */   private final PhoneRootListBeanDao phoneRootListBeanDao;
/*     */   private final PhoneVideoPlayItemListBeanDao phoneVideoPlayItemListBeanDao;
/*     */   
/*     */   public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap) {
/*  90 */     super(db);
/*     */     
/*  92 */     this.imagePlayItemListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(ImagePlayItemListDaoBeanDao.class)).clone();
/*  93 */     this.imagePlayItemListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/*  95 */     this.immediateProgramListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(ImmediateProgramListDaoBeanDao.class)).clone();
/*  96 */     this.immediateProgramListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/*  98 */     this.mainProgramListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(MainProgramListDaoBeanDao.class)).clone();
/*  99 */     this.mainProgramListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 101 */     this.materialDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(MaterialDaoBeanDao.class)).clone();
/* 102 */     this.materialDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 104 */     this.playAreaListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PlayAreaListDaoBeanDao.class)).clone();
/* 105 */     this.playAreaListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 107 */     this.playListMapDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PlayListMapDaoBeanDao.class)).clone();
/* 108 */     this.playListMapDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 110 */     this.programCountBeanDaoConfig = ((DaoConfig)daoConfigMap.get(ProgramCountBeanDao.class)).clone();
/* 111 */     this.programCountBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 113 */     this.rootListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(RootListDaoBeanDao.class)).clone();
/* 114 */     this.rootListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 116 */     this.taskPackageCountBeanDaoConfig = ((DaoConfig)daoConfigMap.get(TaskPackageCountBeanDao.class)).clone();
/* 117 */     this.taskPackageCountBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 119 */     this.timingProgramListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(TimingProgramListDaoBeanDao.class)).clone();
/* 120 */     this.timingProgramListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 122 */     this.videoPlayItemListDaoBeanDaoConfig = ((DaoConfig)daoConfigMap.get(VideoPlayItemListDaoBeanDao.class)).clone();
/* 123 */     this.videoPlayItemListDaoBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 125 */     this.phoneImagePlayItemListBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PhoneImagePlayItemListBeanDao.class)).clone();
/* 126 */     this.phoneImagePlayItemListBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 128 */     this.phoneMainProgramListBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PhoneMainProgramListBeanDao.class)).clone();
/* 129 */     this.phoneMainProgramListBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 131 */     this.phonePlayAreaListBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PhonePlayAreaListBeanDao.class)).clone();
/* 132 */     this.phonePlayAreaListBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 134 */     this.phoneRootListBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PhoneRootListBeanDao.class)).clone();
/* 135 */     this.phoneRootListBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 137 */     this.phoneVideoPlayItemListBeanDaoConfig = ((DaoConfig)daoConfigMap.get(PhoneVideoPlayItemListBeanDao.class)).clone();
/* 138 */     this.phoneVideoPlayItemListBeanDaoConfig.initIdentityScope(type);
/*     */     
/* 140 */     this.imagePlayItemListDaoBeanDao = new ImagePlayItemListDaoBeanDao(this.imagePlayItemListDaoBeanDaoConfig, this);
/* 141 */     this.immediateProgramListDaoBeanDao = new ImmediateProgramListDaoBeanDao(this.immediateProgramListDaoBeanDaoConfig, this);
/* 142 */     this.mainProgramListDaoBeanDao = new MainProgramListDaoBeanDao(this.mainProgramListDaoBeanDaoConfig, this);
/* 143 */     this.materialDaoBeanDao = new MaterialDaoBeanDao(this.materialDaoBeanDaoConfig, this);
/* 144 */     this.playAreaListDaoBeanDao = new PlayAreaListDaoBeanDao(this.playAreaListDaoBeanDaoConfig, this);
/* 145 */     this.playListMapDaoBeanDao = new PlayListMapDaoBeanDao(this.playListMapDaoBeanDaoConfig, this);
/* 146 */     this.programCountBeanDao = new ProgramCountBeanDao(this.programCountBeanDaoConfig, this);
/* 147 */     this.rootListDaoBeanDao = new RootListDaoBeanDao(this.rootListDaoBeanDaoConfig, this);
/* 148 */     this.taskPackageCountBeanDao = new TaskPackageCountBeanDao(this.taskPackageCountBeanDaoConfig, this);
/* 149 */     this.timingProgramListDaoBeanDao = new TimingProgramListDaoBeanDao(this.timingProgramListDaoBeanDaoConfig, this);
/* 150 */     this.videoPlayItemListDaoBeanDao = new VideoPlayItemListDaoBeanDao(this.videoPlayItemListDaoBeanDaoConfig, this);
/* 151 */     this.phoneImagePlayItemListBeanDao = new PhoneImagePlayItemListBeanDao(this.phoneImagePlayItemListBeanDaoConfig, this);
/* 152 */     this.phoneMainProgramListBeanDao = new PhoneMainProgramListBeanDao(this.phoneMainProgramListBeanDaoConfig, this);
/* 153 */     this.phonePlayAreaListBeanDao = new PhonePlayAreaListBeanDao(this.phonePlayAreaListBeanDaoConfig, this);
/* 154 */     this.phoneRootListBeanDao = new PhoneRootListBeanDao(this.phoneRootListBeanDaoConfig, this);
/* 155 */     this.phoneVideoPlayItemListBeanDao = new PhoneVideoPlayItemListBeanDao(this.phoneVideoPlayItemListBeanDaoConfig, this);
/*     */     
/* 157 */     registerDao(ImagePlayItemListDaoBean.class, this.imagePlayItemListDaoBeanDao);
/* 158 */     registerDao(ImmediateProgramListDaoBean.class, this.immediateProgramListDaoBeanDao);
/* 159 */     registerDao(MainProgramListDaoBean.class, this.mainProgramListDaoBeanDao);
/* 160 */     registerDao(MaterialDaoBean.class, this.materialDaoBeanDao);
/* 161 */     registerDao(PlayAreaListDaoBean.class, this.playAreaListDaoBeanDao);
/* 162 */     registerDao(PlayListMapDaoBean.class, this.playListMapDaoBeanDao);
/* 163 */     registerDao(ProgramCountBean.class, this.programCountBeanDao);
/* 164 */     registerDao(RootListDaoBean.class, this.rootListDaoBeanDao);
/* 165 */     registerDao(TaskPackageCountBean.class, this.taskPackageCountBeanDao);
/* 166 */     registerDao(TimingProgramListDaoBean.class, this.timingProgramListDaoBeanDao);
/* 167 */     registerDao(VideoPlayItemListDaoBean.class, this.videoPlayItemListDaoBeanDao);
/* 168 */     registerDao(PhoneImagePlayItemListBean.class, this.phoneImagePlayItemListBeanDao);
/* 169 */     registerDao(PhoneMainProgramListBean.class, this.phoneMainProgramListBeanDao);
/* 170 */     registerDao(PhonePlayAreaListBean.class, this.phonePlayAreaListBeanDao);
/* 171 */     registerDao(PhoneRootListBean.class, this.phoneRootListBeanDao);
/* 172 */     registerDao(PhoneVideoPlayItemListBean.class, this.phoneVideoPlayItemListBeanDao);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 176 */     this.imagePlayItemListDaoBeanDaoConfig.clearIdentityScope();
/* 177 */     this.immediateProgramListDaoBeanDaoConfig.clearIdentityScope();
/* 178 */     this.mainProgramListDaoBeanDaoConfig.clearIdentityScope();
/* 179 */     this.materialDaoBeanDaoConfig.clearIdentityScope();
/* 180 */     this.playAreaListDaoBeanDaoConfig.clearIdentityScope();
/* 181 */     this.playListMapDaoBeanDaoConfig.clearIdentityScope();
/* 182 */     this.programCountBeanDaoConfig.clearIdentityScope();
/* 183 */     this.rootListDaoBeanDaoConfig.clearIdentityScope();
/* 184 */     this.taskPackageCountBeanDaoConfig.clearIdentityScope();
/* 185 */     this.timingProgramListDaoBeanDaoConfig.clearIdentityScope();
/* 186 */     this.videoPlayItemListDaoBeanDaoConfig.clearIdentityScope();
/* 187 */     this.phoneImagePlayItemListBeanDaoConfig.clearIdentityScope();
/* 188 */     this.phoneMainProgramListBeanDaoConfig.clearIdentityScope();
/* 189 */     this.phonePlayAreaListBeanDaoConfig.clearIdentityScope();
/* 190 */     this.phoneRootListBeanDaoConfig.clearIdentityScope();
/* 191 */     this.phoneVideoPlayItemListBeanDaoConfig.clearIdentityScope();
/*     */   }
/*     */   
/*     */   public ImagePlayItemListDaoBeanDao getImagePlayItemListDaoBeanDao() {
/* 195 */     return this.imagePlayItemListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public ImmediateProgramListDaoBeanDao getImmediateProgramListDaoBeanDao() {
/* 199 */     return this.immediateProgramListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public MainProgramListDaoBeanDao getMainProgramListDaoBeanDao() {
/* 203 */     return this.mainProgramListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public MaterialDaoBeanDao getMaterialDaoBeanDao() {
/* 207 */     return this.materialDaoBeanDao;
/*     */   }
/*     */   
/*     */   public PlayAreaListDaoBeanDao getPlayAreaListDaoBeanDao() {
/* 211 */     return this.playAreaListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public PlayListMapDaoBeanDao getPlayListMapDaoBeanDao() {
/* 215 */     return this.playListMapDaoBeanDao;
/*     */   }
/*     */   
/*     */   public ProgramCountBeanDao getProgramCountBeanDao() {
/* 219 */     return this.programCountBeanDao;
/*     */   }
/*     */   
/*     */   public RootListDaoBeanDao getRootListDaoBeanDao() {
/* 223 */     return this.rootListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public TaskPackageCountBeanDao getTaskPackageCountBeanDao() {
/* 227 */     return this.taskPackageCountBeanDao;
/*     */   }
/*     */   
/*     */   public TimingProgramListDaoBeanDao getTimingProgramListDaoBeanDao() {
/* 231 */     return this.timingProgramListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public VideoPlayItemListDaoBeanDao getVideoPlayItemListDaoBeanDao() {
/* 235 */     return this.videoPlayItemListDaoBeanDao;
/*     */   }
/*     */   
/*     */   public PhoneImagePlayItemListBeanDao getPhoneImagePlayItemListBeanDao() {
/* 239 */     return this.phoneImagePlayItemListBeanDao;
/*     */   }
/*     */   
/*     */   public PhoneMainProgramListBeanDao getPhoneMainProgramListBeanDao() {
/* 243 */     return this.phoneMainProgramListBeanDao;
/*     */   }
/*     */   
/*     */   public PhonePlayAreaListBeanDao getPhonePlayAreaListBeanDao() {
/* 247 */     return this.phonePlayAreaListBeanDao;
/*     */   }
/*     */   
/*     */   public PhoneRootListBeanDao getPhoneRootListBeanDao() {
/* 251 */     return this.phoneRootListBeanDao;
/*     */   }
/*     */   
/*     */   public PhoneVideoPlayItemListBeanDao getPhoneVideoPlayItemListBeanDao() {
/* 255 */     return this.phoneVideoPlayItemListBeanDao;
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\dao\DaoSession.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */