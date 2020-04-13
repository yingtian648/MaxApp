package com.zjhj.maxapp.utils.camera2util;

/**
 * CreateTime 2019/12/10 09:48
 * Author LiuShiHua
 * Description：
 */
public interface CameraCallBack {
    void onTakePhotoSuccess(String path, int takePhotoType);

    void onTakePhotoFailure(String message, int takePhotoType);

    void onRecordErr(String message);

    void onStartRecord();
}
