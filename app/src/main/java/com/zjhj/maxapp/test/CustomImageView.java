package com.zjhj.maxapp.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.*;


import java.util.List;


@SuppressLint({"AppCompatCustomView"})
public class CustomImageView
        extends ImageView {
      private Bitmap bitmap = null;
    private boolean mVisibility = false;
    private boolean isStart = false;
       private int image_index = 0;
    private List<String> imagePathList;
    private OnDisplayFinishListener displayFinishListener;
      private int loopCount = 0;
       private Handler handler = new Handler();

     private Runnable runnable = new Runnable() {
        public void run() {
            try {
                if (CustomImageView.this.mVisibility && CustomImageView.this.isStart) {
                    if (CustomImageView.this.imagePathList != null && CustomImageView.this.imagePathList.size() > 0) {
                        if (CustomImageView.this.image_index < CustomImageView.this.imagePathList.size()) {
                            if (new File(CustomImageView.this.imagePathList.get(CustomImageView.this.image_index)).exists()) {
                                Bitmap bitmap = CustomImageView.this.getPictureBitmap(CustomImageView.this.imagePathList.get(CustomImageView.this.image_index));
                                CustomImageView.this.setImageBitmap(bitmap);
                                if (bitmap != null && !bitmap.isRecycled()) {
                                    bitmap = null;
                                    System.gc();
                                }
                            } else {
                            }
                            CustomImageView.this.image_index++;
                        } else {
                            CustomImageView.this.image_index = 0;
                            ++CustomImageView.this.loopCount;
                            CustomImageView.this.displayFinishListener.displayFinish("图片", CustomImageView.this.loopCount);
                        }
                        CustomImageView.this.handler.postDelayed(CustomImageView.this.runnable, (1000 * 8));
                    }
                } else {
                    CustomImageView.this.loopCount = 0;
                }
            } catch (Exception e) {


                CustomImageView.this.loopCount = 0;

                CustomImageView.this.image_index = 0;

                CustomImageView.this.handler.postDelayed(CustomImageView.this.runnable, (1000 * 8));
            }
        }
    };

    public CustomImageView(Context context) {

        this(context, (AttributeSet) null);

    }

    public CustomImageView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
    }


    public void startLoopImage() {
        this.isStart = true;
        if (this.bitmap != null) {
            setImageBitmap(this.bitmap);
        }
        this.handler.postDelayed(this.runnable, 0L);
    }

    public void stopLoopImage() {
        this.isStart = false;
        this.loopCount = 0;
        this.handler.removeCallbacks(this.runnable);

        if (this.bitmap != null) {
            this.bitmap = null;
        }
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == 0) {
            this.mVisibility = true;
        } else if (visibility == 4 || visibility == 8) {
            this.mVisibility = false;
        }
    }


    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void addImagePathList(List<String> imagePathList) {
        this.imagePathList = imagePathList;
        try {
            if (imagePathList.size() > 0) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                options.inPreferredConfig = Bitmap.Config.ALPHA_8;
                options.inPurgeable = true;
                options.inInputShareable = true;
                try {
                    InputStream inputStream = new FileInputStream(imagePathList.get(0));
                    this.bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Bitmap getPictureBitmap(String path) {
        try {
            InputStream inputStream = new FileInputStream(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            byte[] bytes = readStream(inputStream);
            options.inSampleSize = 2;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            bytes = null;
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    public void setDisplayFinishListener(OnDisplayFinishListener listener) {
        this.displayFinishListener = listener;
    }
}