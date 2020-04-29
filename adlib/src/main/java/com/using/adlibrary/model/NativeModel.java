/*    */ package com.using.adlibrary.model;
/*    */ 
/*    */ import com.orhanobut.logger.Logger;
/*    */ import com.using.adlibrary.config.ConfigManger;
/*    */ import com.using.adlibrary.utils.FileUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NativeModel
/*    */ {
/* 18 */   private static String TAG = "NativeModel";
/* 19 */   private static List<String> list = new ArrayList<>(Arrays.asList(new String[] { ".MP4", ".mp4", ".3gp", "3GP", ".jpg", ".JPG", ".png", ".PNG", ".txt" }));
/*    */   
/*    */   public static boolean copyMaterial(String path) {
/*    */     try {
/* 23 */       String resourcePath = path + "/play_list";
/* 24 */       String playlistStr = FileUtils.readToString(resourcePath + "/playList.txt");
/*    */       
/* 26 */       if (playlistStr != null && !playlistStr.isEmpty())
/*    */       {
/* 28 */         for (String s : list) {
/* 29 */           if (FileUtils.copySpecifyTypeFile(s, resourcePath, ConfigManger.getInstance().getMaterialPath()) == 0) {
/* 30 */             Logger.e(s + " :文件拷贝成功", new Object[0]);
/*    */           }
/*    */         } 
/*    */       }
/*    */       
/* 35 */       return true;
/* 36 */     } catch (Exception e) {
/* 37 */       e.printStackTrace();
/* 38 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\model\NativeModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */