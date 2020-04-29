/*    */ package com.using.adlibrary.factory;
/*    */ 
/*    */ import android.support.test.espresso.core.internal.deps.guava.util.concurrent.ThreadFactoryBuilder;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThreadPollFactory
/*    */ {
/*    */   private ScheduledExecutorService mScheduledExecutorService;
/*    */   private static ThreadPollFactory threadPollFactory;
/*    */   
/*    */   private ThreadPollFactory() {
/* 22 */     ThreadFactory namedThreadFactory = (new ThreadFactoryBuilder()).setNameFormat("demo-pool-%d").build();
/*    */     
/* 24 */     this.mScheduledExecutorService = new ScheduledThreadPoolExecutor(20, namedThreadFactory);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ThreadPollFactory getInstance() {
/* 33 */     if (threadPollFactory == null) {
/* 34 */       synchronized (ThreadPollFactory.class) {
/* 35 */         if (threadPollFactory == null) {
/* 36 */           threadPollFactory = new ThreadPollFactory();
/*    */         }
/*    */       } 
/*    */     }
/*    */     
/* 41 */     return threadPollFactory;
/*    */   }
/*    */   
/*    */   public ScheduledExecutorService getScheduledExecutorService() {
/* 45 */     return this.mScheduledExecutorService;
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\factory\ThreadPollFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */