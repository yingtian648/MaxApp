/*    */ package com.using.adlibrary.model;
/*    */ 
/*    */ import android.util.Log;
/*    */ import com.orhanobut.logger.Logger;
/*    */ import com.using.adlibrary.config.ConfigManger;
/*    */ import com.zhy.http.okhttp.OkHttpUtils;
/*    */ import com.zhy.http.okhttp.builder.PostFormBuilder;
/*    */ import com.zhy.http.okhttp.callback.Callback;
/*    */ import com.zhy.http.okhttp.callback.StringCallback;
/*    */ import okhttp3.Call;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReportModel
/*    */ {
/*    */   public static void reportVolume(String uuid, int volume) {
/* 20 */     ((PostFormBuilder)OkHttpUtils.post()
/* 21 */       .url(ConfigManger.getInstance().getBaseUrl() + "/adcloud/api/v1/volumeControl"))
/* 22 */       .addParams("uuid", uuid)
/* 23 */       .addParams("volumeValue", String.valueOf(volume))
/* 24 */       .build()
/* 25 */       .execute((Callback)new StringCallback()
/*    */         {
/*    */           public void onError(Call call, Exception e, int id) {
/* 28 */             e.printStackTrace();
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void onResponse(String response, int id) {
/*    */             try {
/* 35 */               if (response != null) {
/* 36 */                 Logger.e("音量上报return:   " + response, new Object[0]);
/* 37 */                 JSONObject jsonObject = new JSONObject(response);
/* 38 */                 int code = jsonObject.getInt("code");
/*    */ 
/*    */                 
/* 41 */                 if (code == 200) {
/* 42 */                   Log.e("TAG", "音量上报成功");
/*    */                 }
/*    */               } 
/* 45 */             } catch (Exception e) {
/* 46 */               e.printStackTrace();
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\model\ReportModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */