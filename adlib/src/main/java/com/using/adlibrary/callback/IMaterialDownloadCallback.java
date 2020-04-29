package com.using.adlibrary.callback;

public interface IMaterialDownloadCallback {
  void downloadFailure(long paramLong);
  
  void downloadError(long paramLong);
  
  void downloading(String paramString);
  
  void downloadSuccess(long paramLong);
  
  void downloadSuccess(String paramString);
}


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\callback\IMaterialDownloadCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */