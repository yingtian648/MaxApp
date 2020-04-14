package com.zjhj.maxapp.screensame;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import androidx.annotation.Nullable;
import com.zjhj.maxapp.screensame.util.Constants;
import com.zjhj.maxapp.screensame.util.EventBean;
import com.zjhj.maxapp.utils.FileUtil;
import com.zjhj.maxapp.utils.L;
import com.zjhj.maxapp.utils.Tools;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * CreateTime 2020/1/20 09:48
 * Author LiuShiHua
 * Description：
 */
public class RecordScreenService extends IntentService {

    private static final String TYPE_FLAG_NAME = Constants.TYPE_FLAG_NAME;//传递类型名
    private static final int TYPE_SHOTSCREEN = Constants.TYPE_SHOTSCREEN;//[返回]类型-截屏
    private static final int TYPE_RECORDSCREEN = Constants.TYPE_RECORDSCREEN;//[返回]类型-录屏

    private static final int TYPE_SHOTSCREEN_FAILURE = Constants.TYPE_SHOTSCREEN_FAILURE;//返回类型-截屏-失败
    private static final int TYPE_RECORDSCREEN_FAILURE = Constants.TYPE_RECORDSCREEN_FAILURE;//返回类型-录屏-失败

    private int mResultCode, mScreenWidth, mScreenHeight;
    private int mScreenDensity;
    private Intent mResultData;
    private Context context;
    private MediaProjection mMediaProjection;
    private MediaRecorder mMediaRecorder;
    private VirtualDisplay mVirtualDisplay;
    private String savePath;
    private Bitmap mBitmap;
    private EventBus bus;
    private final String screenShotsPath = Environment.getExternalStorageDirectory()+"/AMaxApp/screenshots.jpg";
    private final String screenRecordPath = Environment.getExternalStorageDirectory()+"/AMaxApp/screenrecordpath.mp4";

    private final long DELAY_GETCAPTURE = 2000;//延时2秒截屏
    private final long DELAY_GETCAPTURE_STOPRECORD = 2500;//延时2秒截屏后停止录制
    private final long DELAY_STOPRECORD = 6000;//延时5秒停止录制-录屏时长
    private final long DELAY_STOPRECORD_CALLBACK = 1000;//录屏结束后延时1秒返回消息

    private final int HANDLER_STOP_RECORD = 1;//停止录制
    private final int HANDLER_GETCAPTURE = 2;//截取图片
    private final int HANDLER_STOP_RECORD_CALLBACK = 3;//停止录制_返回消息
    private final int HANDLER_RECORD_FAILURE = 4;//录制失败
    private final int HANDLER_GETCAPTURE_SUCCESS = FileUtil.SAVE_FILE_SUCCESS;//截屏成功
    private final int HANDLER_GETCAPTURE_FAILURE = FileUtil.SAVE_FILE_FAILURE;//截屏失败

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_GETCAPTURE_SUCCESS:
                    bus.post(new EventBean("截屏完成", (String) msg.obj, TYPE_SHOTSCREEN));
                    break;
                case HANDLER_GETCAPTURE_FAILURE:
                    bus.post(new EventBean("截屏失败", (String) msg.obj, TYPE_SHOTSCREEN_FAILURE));
                    break;
                case HANDLER_GETCAPTURE:
                    getScreenCapture();
                    break;
                case HANDLER_STOP_RECORD:
                    stopRecord();
                    break;
                case HANDLER_STOP_RECORD_CALLBACK:
                    bus.post(new EventBean("录屏完成", (String) msg.obj, TYPE_RECORDSCREEN));
                    break;
                case HANDLER_RECORD_FAILURE:
                    bus.post(new EventBean("录屏失败", (String) msg.obj, TYPE_RECORDSCREEN_FAILURE));
                    break;
            }
            RecordScreenService.this.stopSelf();//结束service
        }
    };
    private int typeFlag;

    public RecordScreenService() {
        super("RecordScreenService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        bus = EventBus.getDefault();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mResultCode = intent.getIntExtra("code", -1);
        mResultData = intent.getParcelableExtra("data");
        typeFlag = intent.getIntExtra(TYPE_FLAG_NAME, TYPE_SHOTSCREEN);
        mScreenWidth = Tools.getScreenW(context);
        mScreenHeight = Tools.getScreenH(context);
        mScreenDensity = (int) Tools.getDensity(context);
        mMediaProjection = ((MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE))
                .getMediaProjection(Activity.RESULT_OK, mResultData);
        if (typeFlag == TYPE_SHOTSCREEN) {//截屏
            setupImageReader();
            mVirtualDisplay = mMediaProjection.createVirtualDisplay("screen-mirror",
                    mScreenWidth, mScreenHeight, mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    imageReaderSurface, null, null);
            handler.sendEmptyMessageDelayed(HANDLER_GETCAPTURE, DELAY_GETCAPTURE);
            handler.sendEmptyMessageDelayed(HANDLER_STOP_RECORD, DELAY_GETCAPTURE_STOPRECORD);
        } else {//录屏
            mMediaRecorder = createMediaRecorder();
            if (mMediaRecorder == null) return;
            mVirtualDisplay = mMediaProjection.createVirtualDisplay("screen-mirror",
                    mScreenWidth, mScreenHeight, mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    mMediaRecorder.getSurface(), null, null);
            mMediaRecorder.start();
            handler.sendEmptyMessageDelayed(HANDLER_STOP_RECORD, DELAY_STOPRECORD);
        }
    }

    private MediaRecorder createMediaRecorder() {
        savePath = screenRecordPath;
        File file = new File(savePath);
        if (file.exists()) {
            file.delete();
        } else {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            L.Companion.e("录制屏幕失败，创建文件失败");
            Message message = new Message();
            message.what = HANDLER_RECORD_FAILURE;
            message.obj = "创建文件失败";
            handler.sendMessage(message);
            return null;
        }
        MediaRecorder mediaRecorder = new MediaRecorder();
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(savePath);
        mediaRecorder.setVideoSize(mScreenWidth, mScreenHeight);  //after setVideoSource(), setOutFormat()
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);  //after setOutputFormat()
        mediaRecorder.setVideoFrameRate(30);
        mediaRecorder.setVideoEncodingBitRate(2000 * 1024);
        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            mediaRecorder = null;
            Message message = new Message();
            message.what = HANDLER_RECORD_FAILURE;
            message.obj = "MediaRecorder创建失败";
            handler.sendMessage(message);
        }
        return mediaRecorder;
    }

    private ImageReader imageReader;
    private Surface imageReaderSurface;

    private void setupImageReader() {
        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据，本例的3代表ImageReader中最多可以获取2帧图像流
        imageReader = ImageReader.newInstance(mScreenWidth, mScreenHeight, PixelFormat.RGBA_8888, 2);
        //获取ImageReader的Surface
        imageReaderSurface = imageReader.getSurface();
    }

    private void getScreenCapture() {
        Image image = imageReader.acquireLatestImage();
        if (image != null) {
            int width = image.getWidth();
            int height = image.getHeight();
            final Image.Plane[] planes = image.getPlanes();
            final ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride();
            int rowPadding = rowStride - pixelStride * width;
            if (mBitmap == null) {
                mBitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
            }
            mBitmap.copyPixelsFromBuffer(buffer);
            if (mBitmap != null) {
                FileUtil.Companion.saveBitmapToImage(mBitmap, screenShotsPath, handler);
            } else {
                L.Companion.e("获取图片失败");
                Message message = new Message();
                message.what = HANDLER_GETCAPTURE_FAILURE;
                message.obj = "获取图片失败";
                handler.sendMessage(message);
            }
            image.close();
        }
    }

    private void stopRecord() {
        if (mVirtualDisplay != null) {
            mVirtualDisplay.release();
            mVirtualDisplay = null;
        }
        if (mMediaRecorder != null) {
            mMediaRecorder.setOnErrorListener(null);
            mMediaProjection.stop();
            mMediaRecorder.reset();
        }
        if (mMediaProjection != null) {
            mMediaProjection.stop();
            mMediaProjection = null;
        }
        if (typeFlag == TYPE_RECORDSCREEN) {
            L.Companion.e("录制屏幕结束：" + savePath);
            Message message = new Message();
            message.what = HANDLER_STOP_RECORD_CALLBACK;
            message.obj = savePath;
            handler.sendMessageDelayed(message, DELAY_STOPRECORD_CALLBACK);
        }
    }
}

//  调用方式
//    private MediaProjectionManager mMediaProjectionManager;
//
//    private void startRecordScreen() {
//        L.d("触发录屏服务");
//        mMediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
//        startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(),
//                REQUEST_SYS_SCREENRECORD);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case REQUEST_SYS_SCREENRECORD:
//                if (resultCode == RESULT_OK && data != null) {
//                    L.d("启动录屏服务");
//                    Intent service = new Intent(this, RecordScreenService.class);
//                    service.putExtra("code", resultCode);
//                    service.putExtra("data", data);
//                    service.putExtra(Constants.TYPE_FLAG_NAME, screenGetType);
//                    startService(service);
//                }
//                break;
//        }
//    }