package com.using.adlibrary.callback;

import android.content.Context;

public interface IBaseViewCallback {
  void showLoading();
  
  void hideLoading();
  
  void showToast(String paramString);
  
  void showErr();
  
  Context getContext();
}


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\callback\IBaseViewCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */