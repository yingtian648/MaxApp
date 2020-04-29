package com.using.adlibrary.callback;

public interface IPhoneMaterialDownloadCallback {
  void downloadFailure(String paramString);
  
  void downloadError(String paramString);
  
  void downloading(String paramString);
  
  void downloadSuccess(String paramString);
}


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\callback\IPhoneMaterialDownloadCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */