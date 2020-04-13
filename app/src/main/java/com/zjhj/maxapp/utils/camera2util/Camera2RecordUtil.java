package com.zjhj.maxapp.utils.camera2util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Surface;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.zjhj.maxapp.utils.L;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * CreateTime 2019/12/10 09:45
 * Author LiuShiHua
 * Description：
 */
public class Camera2RecordUtil {
    private Activity activity;
    private CameraCallBack cameraCallBack;
    private CameraManager cameraManager;
    private int CAMERA_ID = CameraCharacteristics.LENS_FACING_FRONT;//默认相机id是0
    private CameraDevice mCameraDevice;
    private String savePath;

    private TextureView textureView;
    private SurfaceTexture mSurfaceTexture;
    private Surface mSurface, recordSurface;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private CameraCaptureSession mCameraCaptureSession;
    private CaptureRequest request;
    private MediaRecorder mRecorder;

    private int needSetOrientation = 0;//设置默认的拍照方向
    private boolean isSessionClosed = true;//captureSession是否被关闭
    private boolean isRecordTimeout = false;//是否超时
    private final long TAKE_PHOTO_TIME_OUT_LONG = 8 * 1000;//预览超时时长

    private final int HANDLER_RECORD_TIMEOUT = 1;
    private final int HANDLER_RECORD_ERR = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_RECORD_TIMEOUT:
                    if (isRecordTimeout)
                        cameraCallBack.onRecordErr("视频录制失败，打开相机超时");
                    break;
                case HANDLER_RECORD_ERR:
                    cameraCallBack.onRecordErr("视频录制失败," + msg.obj);
                    break;
            }
        }
    };

    /**
     * 当相机设备的状态发生改变的时候，将会回调。
     */
    protected final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        /**
         * 当相机打开的时候，调用
         * @param cameraDevice
         */
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            L.Companion.d("onOpened");
            mCameraDevice = cameraDevice;
            startRecordPreView();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            L.Companion.d("onDisconnected");
            cameraDevice.close();
            mCameraDevice = null;
        }

        /**
         * 发生异常的时候调用
         *
         * 这里释放资源，然后关闭界面
         * @param cameraDevice
         * @param error
         */
        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int error) {
            L.Companion.d("onError");
            cameraDevice.close();
            mCameraDevice = null;
        }

        /**
         *当相机被关闭的时候
         */
        @Override
        public void onClosed(@NonNull CameraDevice camera) {
            super.onClosed(camera);
            L.Companion.d("onClosed");
            mCameraDevice = null;
        }
    };

    private CameraManager.AvailabilityCallback callback = new CameraManager.AvailabilityCallback() {
        @Override
        public void onCameraAvailable(@NonNull String cameraId) {//相机可用
            super.onCameraAvailable(cameraId);
            L.Companion.d("相机可用");
        }

        @Override
        public void onCameraUnavailable(@NonNull String cameraId) {//相机不可用
            super.onCameraUnavailable(cameraId);
            L.Companion.d("相机不可用");
        }
    };

    public Camera2RecordUtil(Activity activity, @NonNull CameraCallBack cameraCallBack) {
        this.activity = activity;
        this.cameraCallBack = cameraCallBack;
        cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        //对于静态图片，使用可用的最大值来拍摄。
        if (cameraManager != null) {
            cameraManager.registerAvailabilityCallback(callback, null);
        }
        getPreviewOrientation();
    }


    //设置预览视图去预览
    public void startRecord(final TextureView textureView, final String savePath) {
        this.isRecordTimeout = true;
        this.textureView = textureView;
        this.savePath = savePath;
        this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                L.Companion.d("record onSurfaceTextureAvailable");
                mSurfaceTexture = surfaceTexture;
                //设置TextureView的缓冲区大小
                mSurfaceTexture.setDefaultBufferSize(640, 480);
                //获取Surface显示预览数据
                mSurface = new Surface(mSurfaceTexture);
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    L.Companion.e("权限被拒");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0以上才需要获取权限
                        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.CAMERA",
                                "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE",
                                "android.permission.RECORD_AUDIO"}, 1);
                    }
                    return;
                }
                try {
                    cameraManager.openCamera(Integer.toString(CAMERA_ID), stateCallback, null);
                    L.Companion.d("打开相机成功！");
                } catch (Exception e) {
                    L.Companion.e("打开相机失败 Exception:" + e.getMessage());
                    e.printStackTrace();
                    Message messagef = new Message();
                    messagef.what = HANDLER_RECORD_ERR;
                    messagef.obj = "打开相机失败";
                    handler.sendMessage(messagef);
                }
                handler.sendEmptyMessageDelayed(HANDLER_RECORD_TIMEOUT, TAKE_PHOTO_TIME_OUT_LONG);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                L.Companion.d("record onSurfaceTextureDestroyed");
                stopRecord();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });
    }

    /**
     * 开始视频录制预览
     */
    private void startRecordPreView() {
        L.Companion.d("开始预览");
        //CaptureRequest添加imageReaderSurface，不加的话就会导致ImageReader的onImageAvailable()方法不会回调
        //创建CaptureSession时加上imageReaderSurface，如下，这样预览数据就会同时输出到previewSurface和imageReaderSurface了
        try {
            //创建CaptureRequestBuilder，TEMPLATE_PREVIEW比表示预览请求
            setUpMediaRecorder(savePath);
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
            //设置Surface作为预览数据的显示界面
            mPreviewRequestBuilder.addTarget(mSurface);
            mPreviewRequestBuilder.addTarget(recordSurface);//将视频通过recordSurface记录到MediaRecorder中
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            mCameraDevice.createCaptureSession(Arrays.asList(mSurface, recordSurface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        mCameraCaptureSession = session;
                        isSessionClosed = false;
                        request = mPreviewRequestBuilder.build();
                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                        mCameraCaptureSession.setRepeatingRequest(request, null, null);
                        mRecorder.start();
                        isRecordTimeout = false;
                        cameraCallBack.onStartRecord();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Message messagef = new Message();
                        messagef.what = HANDLER_RECORD_ERR;
                        messagef.obj = "Camera获取成功，开启录制预览失败";
                        handler.sendMessage(messagef);
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                    L.Companion.d("onConfigureFailed");
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Message messagef = new Message();
            messagef.what = HANDLER_RECORD_ERR;
            messagef.obj = "Camera获取成功，创建录制请求或捕获Session失败";
            handler.sendMessage(messagef);
        }
    }

    /**
     * 停止预览，释放资源
     */
    public void stopRecord() {
        L.Companion.d("停止预览，释放资源");
        try {
            if (mCameraCaptureSession != null && !isSessionClosed) {
                mCameraCaptureSession.stopRepeating();
                mCameraCaptureSession.abortCaptures();
                mCameraCaptureSession.close();
                isSessionClosed = true;
            }
            // 停止录制
            if (mRecorder != null) {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
            }
            if (mCameraDevice != null)
                mCameraDevice.close();
            if (this.textureView != null) {
                this.textureView.setSurfaceTextureListener(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            L.Companion.e("stopRecord Exception:" + e.getMessage());
        }
    }


    /**
     * 设置媒体录制器的配置参数
     * <p>
     * 音频，视频格式，文件路径，频率，编码格式等等
     *
     * @throws IOException
     */
    private void setUpMediaRecorder(String savePath) {
        mRecorder = new MediaRecorder();
        try {
            File file = new File(savePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            mRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
                @Override
                public void onError(MediaRecorder mediaRecorder, int i, int i1) {
                    L.Companion.d("MediaRecorder.onError");
                }
            });
            mRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE); // 设置从摄像头采集图像
            mRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT); // 设置从麦克风采集声音
            mRecorder.setOrientationHint(this.needSetOrientation);
            CamcorderProfile mProfile = CamcorderProfile.get(CAMERA_ID, CamcorderProfile.QUALITY_HIGH);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 设置视频的输出格式 为MP4
            mRecorder.setAudioEncoder(mProfile.audioCodec);// 设置音频的编码格式
            mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);// 设置视频的编码格式
            mRecorder.setOutputFile(savePath);
            mRecorder.setVideoSize(640, 480);
            //设置录制的视频帧率,必须放在设置编码和格式的后面，否则报错
            mRecorder.setVideoFrameRate(mProfile.videoFrameRate);
            mRecorder.setVideoEncodingBitRate(800 * 1024);//视频码率
            mRecorder.setAudioEncodingBitRate(mProfile.audioBitRate);
            mRecorder.setAudioChannels(1);//设置录制的音频通道数
            mRecorder.setAudioSamplingRate(44100);
            mRecorder.prepare();
            recordSurface = mRecorder.getSurface();
        } catch (IOException e) {
            e.printStackTrace();
            L.Companion.d("setUpMediaRecorder IOException:" + e.getMessage());
        }
    }

    public void onDestroy() {
        if (cameraManager != null)
            cameraManager.unregisterAvailabilityCallback(callback);
    }

    /**
     * 获取摄像头方向
     */
    private void getPreviewOrientation() {
        try {
            int phoneDegree = 0;
            int result = 0;
            //获得手机方向
            int phoneRotate = activity.getWindowManager().getDefaultDisplay().getOrientation();
            //得到手机的角度
            switch (phoneRotate) {
                case Surface.ROTATION_0:
                    phoneDegree = 0;
                    break;        //0
                case Surface.ROTATION_90:
                    phoneDegree = 90;
                    break;      //90
                case Surface.ROTATION_180:
                    phoneDegree = 180;
                    break;    //180
                case Surface.ROTATION_270:
                    phoneDegree = 270;
                    break;    //270
            }
            //分别计算前后置摄像头需要旋转的角度
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            boolean isFrontCamera = false;
            if (isFrontCamera) {
                Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_FRONT, cameraInfo);
                result = (cameraInfo.orientation + phoneDegree) % 360;
                result = (360 - result) % 360;
            } else {
                Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, cameraInfo);
                result = (cameraInfo.orientation - phoneDegree + 360) % 360;
            }
            this.needSetOrientation = result;
            L.Companion.d("相机方向应设置角度：" + result);
        } catch (Exception e) {
            e.printStackTrace();
            L.Companion.e("获取摄像头方向失败");
        }
    }
}
