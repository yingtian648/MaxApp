package com.using.adlibrary.callback;

public interface IModelCallback<T> {
  void onCommence(String paramString);
  
  void onSuccess(T paramT);
  
  void onFailure(String paramString);
  
  void onError(String paramString);
  
  void onComplete(String paramString);
}


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\callback\IModelCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */