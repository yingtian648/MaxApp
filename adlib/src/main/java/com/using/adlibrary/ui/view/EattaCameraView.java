/*     */ package com.using.adlibrary.ui.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.hardware.Camera;
/*     */ import android.media.MediaRecorder;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.Log;
/*     */ import android.view.SurfaceHolder;
/*     */ import android.view.SurfaceView;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import com.using.adlibrary.utils.ImageBase64Utils;
/*     */ import com.using.adlibrary.utils.ImageCompressUtils;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EattaCameraView
/*     */   extends SurfaceView
/*     */   implements SurfaceHolder.Callback, Camera.PictureCallback, Camera.ErrorCallback, Camera.PreviewCallback
/*     */ {
/*     */   public static final int CAMERA_ERROR_ORIGINAL = 1;
/*     */   public static final int CAMERA_ERROR_CAMERA_NULL = 2;
/*     */   public static final int CAMERA_ERROR_TAKE_PICTURE_ERROR = 3;
/*     */   private boolean safeToTakePicture = false;
/*  33 */   private Camera mCamera = null;
/*     */   private SurfaceHolder mHolder;
/*     */   private ICameraDataCallback cameraDataCallback;
/*     */   private MediaRecorder mMediaRecorder;
/*     */   private ICameraPreviewCallback cameraPreviewCallback;
/*     */   
/*     */   public EattaCameraView(Context context) {
/*  40 */     this(context, null);
/*     */   }
/*     */   
/*     */   public EattaCameraView(Context context, AttributeSet attrs) {
/*  44 */     this(context, attrs, 0);
/*     */   }
/*     */   
/*     */   public EattaCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
/*  48 */     super(context, attrs, defStyleAttr);
/*     */     
/*  50 */     this.mHolder = getHolder();
/*  51 */     this.mHolder.addCallback(this);
/*  52 */     this.mHolder.setType(3);
/*     */     
/*  54 */     this.mMediaRecorder = new MediaRecorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startTakePicture() {
/*  59 */     Log.e("TAGSOS", "相机开始拍照");
/*  60 */     if (this.mCamera != null) {
/*     */       try {
/*  62 */         Log.e("TAGSOS", "调用拍照方法");
/*  63 */         if (!this.safeToTakePicture) {
/*  64 */           this.mCamera.startPreview();
/*  65 */           this.safeToTakePicture = true;
/*     */         } 
/*     */         
/*  68 */         this.mCamera.takePicture(null, null, this);
/*  69 */         this.safeToTakePicture = false;
/*     */       }
/*  71 */       catch (Exception e) {
/*  72 */         Log.e("TAGSOS", "调用拍照方法  Exception" + e.getMessage());
/*  73 */         e.printStackTrace();
/*  74 */         this.cameraDataCallback.onCameraError(3);
/*     */       } 
/*     */     } else {
/*  77 */       Log.e("TAGSOS", "调用拍照方法 相机为空");
/*  78 */       this.cameraDataCallback.onCameraError(2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPictureTaken(byte[] data, Camera camera) {
/*  85 */     if (this.mCamera != null) {
/*  86 */       this.mCamera.startPreview();
/*  87 */       this.safeToTakePicture = true;
/*     */     } 
/*     */     
/*  90 */     long beginTime = System.currentTimeMillis();
/*     */     
/*  92 */     Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
/*     */     
/*  94 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */ 
/*     */     
/*  97 */     bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
/*  98 */     int optionNum = 20;
/*     */ 
/*     */     
/* 101 */     while ((baos.toByteArray()).length / 1024 > 80) {
/*     */       
/* 103 */       optionNum -= 10;
/* 104 */       if (optionNum < 10) {
/*     */         break;
/*     */       }
/* 107 */       baos.reset();
/*     */       
/* 109 */       bitmap.compress(Bitmap.CompressFormat.JPEG, optionNum, baos);
/*     */     } 
/*     */     
/* 112 */     Bitmap b = ImageCompressUtils.rotateBitmap(bitmap, 270);
/* 113 */     String base64Str = ImageBase64Utils.getBase64StrForNet(b);
/* 114 */     Log.e("TAGSOS", "picture---   " + base64Str);
/* 115 */     if (this.cameraDataCallback != null) {
/* 116 */       this.cameraDataCallback.onPictureBase64DataCallback(base64Str);
/*     */     }
/* 118 */     bitmap.recycle();
/*     */     
/* 120 */     if (b != null) {
/* 121 */       b.recycle();
/*     */     }
/*     */     
/* 124 */     Log.e("TAGSOS", "压缩耗时： " + (System.currentTimeMillis() - beginTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public void surfaceCreated(SurfaceHolder holder) {
/* 129 */     Log.e("TAGSOS", "surfaceCreated 相机初始化");
/* 130 */     holder.addCallback(this);
/* 131 */     this.mHolder = getHolder();
/* 132 */     this.mHolder.addCallback(this);
/*     */     
/*     */     try {
/* 135 */       this.mCamera = Camera.open();
/*     */       
/* 137 */       this.mCamera.setDisplayOrientation(90);
/* 138 */     } catch (Exception e) {
/* 139 */       Log.e("TAGSOS", "打开相机失败" + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
/* 145 */     Log.e("TAGSOS", "相机   surfaceChanged ");
/*     */     
/*     */     try {
/* 148 */       if (this.mCamera != null) {
/*     */         
/* 150 */         this.mCamera.setPreviewDisplay(this.mHolder);
/* 151 */         Camera.Parameters parameters = this.mCamera.getParameters();
/* 152 */         parameters.setPreviewSize(640, 480);
/* 153 */         parameters.setPictureSize(640, 480);
/* 154 */         this.mCamera.setErrorCallback(this);
/* 155 */         this.mCamera.setParameters(parameters);
/*     */         
/* 157 */         this.mCamera.setDisplayOrientation(180);
/*     */         
/* 159 */         this.mCamera.startPreview();
/* 160 */         this.safeToTakePicture = true;
/*     */         
/* 162 */         this.mCamera.setPreviewCallback(this);
/*     */       } else {
/* 164 */         Log.e("TAGSOS", "surfaceChanged 相机为空 ");
/*     */       } 
/* 166 */     } catch (Exception e) {
/* 167 */       Logger.d("预览失败");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void surfaceDestroyed(SurfaceHolder holder) {
/* 173 */     Log.e("TAGSOS", "surfaceDestroyed  释放相机");
/* 174 */     if (this.mCamera != null) {
/* 175 */       this.mCamera.setPreviewCallback(null);
/* 176 */       this.mCamera.stopPreview();
/* 177 */       this.mCamera.release();
/* 178 */       this.mCamera = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onError(int error, Camera camera) {
/* 185 */     if (this.cameraDataCallback != null) {
/* 186 */       this.cameraDataCallback.onCameraError(1);
/* 187 */       Log.e("TAGSOS", "相机  onError -----------");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCameraDataCallback(ICameraDataCallback cameraDataCallback) {
/* 192 */     this.cameraDataCallback = cameraDataCallback;
/*     */   }
/*     */   
/*     */   public void setCameraPreviewCallback(ICameraPreviewCallback cameraPreviewCallback) {
/* 196 */     this.cameraPreviewCallback = cameraPreviewCallback;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPreviewFrame(byte[] data, Camera camera) {
/* 202 */     if (this.cameraPreviewCallback != null) {
/* 203 */       this.cameraPreviewCallback.onCameraPreviewData(data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startRecorderVideo(String path) {
/*     */     try {
/* 220 */       if (this.mMediaRecorder == null) {
/* 221 */         this.mMediaRecorder = new MediaRecorder();
/*     */       } else {
/* 223 */         this.mMediaRecorder.reset();
/*     */       } 
/*     */ 
/*     */       
/* 227 */       this.mCamera.unlock();
/* 228 */       this.mMediaRecorder.setCamera(this.mCamera);
/* 229 */       this.mMediaRecorder.setPreviewDisplay(this.mHolder.getSurface());
/*     */ 
/*     */       
/* 232 */       this.mMediaRecorder.setVideoSource(0);
/*     */ 
/*     */       
/* 235 */       this.mMediaRecorder.setAudioSource(0);
/*     */ 
/*     */       
/* 238 */       this.mMediaRecorder.setOutputFormat(2);
/*     */       
/* 240 */       this.mMediaRecorder.setVideoEncoder(2);
/*     */       
/* 242 */       this.mMediaRecorder.setAudioEncoder(0);
/* 243 */       this.mMediaRecorder.setOrientationHint(180);
/*     */       
/* 245 */       this.mMediaRecorder.setVideoSize(320, 240);
/*     */       
/* 247 */       this.mMediaRecorder.setOutputFile(path);
/* 248 */       this.mMediaRecorder.prepare();
/* 249 */       this.mMediaRecorder.start();
/*     */     }
/* 251 */     catch (Exception e) {
/* 252 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stopRecorderVideo() {
/* 257 */     if (this.mMediaRecorder != null) {
/* 258 */       this.mMediaRecorder.setPreviewDisplay(null);
/*     */       try {
/* 260 */         this.mMediaRecorder.stop();
/* 261 */         this.mMediaRecorder.reset();
/* 262 */         this.mMediaRecorder.release();
/* 263 */         this.mMediaRecorder = null;
/*     */       }
/* 265 */       catch (Exception e) {
/* 266 */         e.printStackTrace();
/*     */       } 
/*     */       
/* 269 */       if (this.mCamera != null) {
/* 270 */         this.mCamera.lock();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void releaseCamera() {
/* 276 */     if (this.mCamera != null) {
/* 277 */       this.mCamera.setPreviewCallback(null);
/* 278 */       this.mCamera.startPreview();
/* 279 */       this.mCamera.release();
/* 280 */       this.mCamera = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface ICameraDataCallback {
/*     */     void onPictureBase64DataCallback(String param1String);
/*     */     
/*     */     void onCameraError(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface ICameraPreviewCallback {
/*     */     void onCameraPreviewData(byte[] param1ArrayOfbyte);
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\ui\view\EattaCameraView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */