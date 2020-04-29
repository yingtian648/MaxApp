package com.using.adlibrary.callback;

import org.json.JSONException;

public interface IPhoneNetCallback {
  int deviceNetError(String paramString);
  
  int deviceHeartBeatCallback(String paramString) throws JSONException;
}


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrary\callback\IPhoneNetCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */