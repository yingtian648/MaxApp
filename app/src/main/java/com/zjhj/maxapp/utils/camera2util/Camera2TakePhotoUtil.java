package com.zjhj.maxapp.utils.camera2util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Surface;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.zjhj.maxapp.utils.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * CreateTime 2019/12/10 09:45
 * Author LiuShiHua
 * Description：
 */
public class Camera2TakePhotoUtil {
    private Activity activity;
    private CameraCallBack cameraCallBack;
    private CameraManager cameraManager;
    private int CAMERA_ID = CameraCharacteristics.LENS_FACING_FRONT;//默认相机id是0[LENS_FACING_FRONT,LENS_FACING_BACK]
    private CameraDevice mCameraDevice;
    private String savePath;

    private int needSetOrientation = 0;//设置默认的拍照方向
    private boolean isSessionClosed = true;//captureSession是否被关闭
    private boolean isTakePhotoBack = false;//是否已经返回拍照结论
    private boolean isTakePhotoTimeout = false;//是否超时
    private final long TAKE_PHOTO_TIME_OUT_LONG = 12 * 1000;//预览超时时长
    private final long TAKE_PHOTO_DELAY = 4 * 1000;//延时拍照
    private int takePhotoType = -1;//拍照类型

    public static final int TAKE_PHOTO_TYPE_NORMAL = 1;//拍照类型_自检拍照
    public static final int TAKE_PHOTO_TYPE_ALARM = 2;//拍照类型_报警拍照

    private final int HANDLER_TAKE_PHOTO = 1;
    private final int HANDLER_TAKE_PHOTO_TIMEOUT = 2;
    private final int HANDLER_TAKE_PHOTO_ERR = 3;
    private final int HANDLER_TAKE_PHOTO_SUCCESS = 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_TAKE_PHOTO:
                    capturePhoto();
                    break;
                case HANDLER_TAKE_PHOTO_TIMEOUT:
                    if (isTakePhotoTimeout && !isTakePhotoBack)
                        cameraCallBack.onTakePhotoFailure("拍照失败，打开相机超时", takePhotoType);
                    break;
                case HANDLER_TAKE_PHOTO_ERR:
                    isTakePhotoBack = true;
                    cameraCallBack.onTakePhotoFailure(
                            (String) msg.obj, takePhotoType);
                    break;
                case HANDLER_TAKE_PHOTO_SUCCESS:
                    isTakePhotoBack = true;
                    cameraCallBack.onTakePhotoSuccess(savePath, takePhotoType);
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
            L.Companion.d("takephoto onOpened");
            mCameraDevice = cameraDevice;
            startPreView();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            L.Companion.d("takephoto onDisconnected");
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
            L.Companion.d("takephoto onError");
            cameraDevice.close();
            mCameraDevice = null;
        }

        /**
         *当相机被关闭的时候
         */
        @Override
        public void onClosed(@NonNull CameraDevice camera) {
            super.onClosed(camera);
            L.Companion.d("takephoto onClosed");
            mCameraDevice = null;
        }
    };

    private CameraManager.AvailabilityCallback callback = new CameraManager.AvailabilityCallback() {
        @Override
        public void onCameraAvailable(@NonNull String cameraId) {//相机可用
            super.onCameraAvailable(cameraId);
            L.Companion.d("takephoto onCameraAvailable");
        }

        @Override
        public void onCameraUnavailable(@NonNull String cameraId) {//相机不可用
            super.onCameraUnavailable(cameraId);
            L.Companion.d("takephoto onCameraUnavailable");
        }
    };
    private TextureView textureView;
    private SurfaceTexture mSurfaceTexture;
    private Surface imageReaderSurface;
    private Surface mSurface;

    public Camera2TakePhotoUtil(Activity activity, CameraCallBack cameraCallBack) {
        this.activity = activity;
        this.cameraCallBack = cameraCallBack;
        cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        //对于静态图片，使用可用的最大值来拍摄。
        if (cameraManager != null) {
            cameraManager.registerAvailabilityCallback(callback, null);
        }
        setupImageReader();
        getPreviewOrientation();
    }

    private CameraCaptureSession mCameraCaptureSession;

    //设置预览视图去预览
    public void takePhoto(final TextureView textureView, final String savePath, int takePhotoType) {
        this.isTakePhotoTimeout = true;
        this.isTakePhotoBack = false;
        this.textureView = textureView;
        this.takePhotoType = takePhotoType;
        this.savePath = savePath;
        this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                L.Companion.d("takephoto onSurfaceTextureAvailable");
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
                    L.Companion.e("打开相机失败 Exception：" + e.getMessage());
                    e.printStackTrace();
                    Message messagef = new Message();
                    messagef.what = HANDLER_TAKE_PHOTO_ERR;
                    messagef.obj = "打开相机失败";
                    handler.sendMessage(messagef);
                }
                handler.sendEmptyMessageDelayed(HANDLER_TAKE_PHOTO_TIMEOUT, TAKE_PHOTO_TIME_OUT_LONG);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                L.Companion.d("takephoto onSurfaceTextureDestroyed");
                stopPreview();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }


        });
    }

    /**
     * 捕获来自摄像头的图片
     * ,当在CaptureCallback监听器响应的时候调用该方法。
     * <p>
     * 当数字调焦缩放的时候，在写入图片数中也要设置。
     */
    private void capturePhoto() {
        try {
            if (mCameraDevice == null) return;
            //首先我们创建请求拍照的CaptureRequest
            final CaptureRequest.Builder mCaptureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            //设置CaptureRequest输出到mImageReader
            mCaptureBuilder.addTarget(imageReader.getSurface());
            //设置拍照方向
            mCaptureBuilder.set(CaptureRequest.JPEG_ORIENTATION, this.needSetOrientation);
            //这个回调接口用于拍照结束时重启预览，因为拍照会导致预览停止
            CaptureCallback mImageSavedCallback = new CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    L.Companion.d("拍照完成");
                    stopPreview();
                }
            };
            //开始拍照，然后回调上面的接口重启预览，因为mCaptureBuilder设置ImageReader作为target，所以会自动回调ImageReader的onImageAvailable()方法保存图片
            mCameraCaptureSession.capture(mCaptureBuilder.build(), mImageSavedCallback, null);
        } catch (CameraAccessException e) {
            L.Companion.d("takePhoto CameraAccessException:" + e.getMessage());
            e.printStackTrace();
            Message messagef = new Message();
            messagef.what = HANDLER_TAKE_PHOTO_ERR;
            messagef.obj = "捕获图片报错";
            handler.sendMessage(messagef);
        }
    }

    private CaptureRequest.Builder mPreviewRequestBuilder;
    /**
     * 处理静态图片的输出
     */
    private ImageReader imageReader;
    private CaptureRequest request;

    /**
     * 开始预览
     */
    private void startPreView() {
        L.Companion.d("开始预览");
        //CaptureRequest添加imageReaderSurface，不加的话就会导致ImageReader的onImageAvailable()方法不会回调
        //创建CaptureSession时加上imageReaderSurface，如下，这样预览数据就会同时输出到previewSurface和imageReaderSurface了
        try {
            //创建CaptureRequestBuilder，TEMPLATE_PREVIEW比表示预览请求
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //设置Surface作为预览数据的显示界面
            mPreviewRequestBuilder.addTarget(mSurface);
            //设置对焦
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            mCameraDevice.createCaptureSession(Arrays.asList(mSurface, imageReaderSurface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    L.Companion.d("onConfigured");
                    try {
                        //创建捕获请求
                        mCameraCaptureSession = session;
                        isSessionClosed = false;
                        request = mPreviewRequestBuilder.build();
                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                        mCameraCaptureSession.setRepeatingRequest(request, null, null);
                        handler.sendEmptyMessageDelayed(HANDLER_TAKE_PHOTO, TAKE_PHOTO_DELAY);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                    L.Companion.d("onConfigureFailed");
                }
            }, null);
        } catch (Exception e) {
            e.printStackTrace();
            L.Companion.d("startPreView CameraAccessException:" + e.getMessage());
        }
    }

    /**
     * 停止预览，释放资源
     */
    private void stopPreview() {
        L.Companion.d("停止预览，释放资源");
        try {
            if (mCameraCaptureSession != null && !isSessionClosed) {
                mCameraCaptureSession.stopRepeating();
                mCameraCaptureSession.abortCaptures();
                mCameraCaptureSession.close();
                isSessionClosed = true;
            }
            if (mCameraDevice != null)
                mCameraDevice.close();
            if (this.textureView != null) {
                this.textureView.setSurfaceTextureListener(null);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
            L.Companion.e("stopPreview CameraAccessException:" + e.getMessage());
        }
    }

    private void setupImageReader() {
        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据，本例的3代表ImageReader中最多可以获取2帧图像流
        imageReader = ImageReader.newInstance(640, 480, ImageFormat.JPEG, 2);
        //监听ImageReader的事件，当有图像流数据可用时会回调onImageAvailable方法，它的参数就是预览帧数据，可以对这帧数据进行处理
        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                isTakePhotoTimeout = false;
                Image image = reader.acquireLatestImage();
                //我们可以将这帧数据转成字节数组，类似于Camera1的PreviewCallback回调的预览帧数据
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                image.close();
                saveFile(data, savePath);
            }
        }, null);
        //获取ImageReader的Surface
        imageReaderSurface = imageReader.getSurface();
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

    //覆盖性保存
    private void saveFile(final byte[] data, final String savePath) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (data == null || data.length == 0) return;
                File file = new File(savePath);
                if (file.exists()) {
                    file.delete();
                } else {
                    File parent = file.getParentFile();
                    parent.mkdirs();
                }
                try {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data);
                    fos.flush();
                    fos.close();
                    Message message = new Message();
                    message.what = HANDLER_TAKE_PHOTO_SUCCESS;
                    message.obj = savePath;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message messagef = new Message();
                    messagef.what = HANDLER_TAKE_PHOTO_ERR;
                    messagef.obj = "图片保存失败";
                    handler.sendMessage(messagef);
                }
            }
        }.start();
    }
}
