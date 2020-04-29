package com.using.adlibrary.callback;

public interface IComputerMaterialDownloadCallback {
  void downloadFailure(String paramString);
  
  void downloadError(String paramString);
  
  void downloading(String paramString);
  
  void downloadSuccess(String paramString);
}


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\callback\IComputerMaterialDownloadCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */