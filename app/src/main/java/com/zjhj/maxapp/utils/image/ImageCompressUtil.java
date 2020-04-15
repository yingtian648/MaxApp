package com.zjhj.maxapp.utils.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.util.Log;
import com.zjhj.maxapp.utils.PathUtil;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * 图片压缩工具
 */
public class ImageCompressUtil {
    private final static String TAG = ImageCompressUtil.class.getSimpleName();


    /**
     * 保存临时照片【对外方法】
     *
     * @param nativeFilePath
     * @param maxSize        压缩后的图片大小
     * @return bitmap
     */
    public static File getCompressFile(String nativeFilePath, long maxSize) throws Exception {
        File file = new File(nativeFilePath);
        if (!file.exists()) {
            return null;
        }
        if (file.length() < maxSize) {//控制压缩后的文件大小
            return copyFile(nativeFilePath, getFileTempPath());
        }
        //获取压缩bitmap
        Bitmap bm = getZipBitmap(nativeFilePath, 640, 480, maxSize);
        ExifInterface exifInterface = new ExifInterface(nativeFilePath);
        return saveBitmap(bm, getFileTempPath(), exifInterface, maxSize);
    }

    /**
     * 保存临时照片【对外方法】
     *
     * @param nativeFilePath
     * @param mazSize        压缩后的图片大小
     */
    public static Bitmap getCompressBitmap(String nativeFilePath, long mazSize) throws Exception {
        File file = new File(nativeFilePath);
        if (!file.exists()) {
            return null;
        }
        if (file.length() < mazSize) {//控制压缩后的文件大小
            return BitmapFactory.decodeFile(nativeFilePath);
        }
        //获取压缩bitmap
        return getZipBitmap(nativeFilePath, 640, 480, mazSize);
    }

    //获取临时文件路径
    private static String getFileTempPath() {
        String path = PathUtil.Companion.getPicTempPath() + "/a12.jpg";
        File temp = new File(path);
        if (!temp.exists()) {
            temp.getParentFile().mkdirs();
            try {
                temp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    //计算图片大小
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //返回压缩图片文件的压缩bitmap
    private static Bitmap getZipBitmap(String filePath, int reqWidth, int reqHeight, long mazSize) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inSampleSize = 3;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    //保存并返回图片文件
    private static File saveBitmap(Bitmap bm, String filePath, ExifInterface exifInterface, long maxSize) throws Exception {
        File file = new File(filePath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            Log.e(TAG, "已经保存");
            try {
                if (exifInterface != null) {
                    MyImageHeaderParser.copyExif(exifInterface, filePath);
                }
            } catch (Exception e) {
                Log.e(TAG, "获取文件信息失败" + e.toString());
            }
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception("没有读取SD卡图片权限");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("压缩图片异常");
        }
    }

    /**
     * 删除临时图片
     *
     * @param filePath
     */
    public static void delTempFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 删除临时图片
     *
     * @param files
     */
    public static void delMapFiles(Map<String, File> files) {
        if (!files.isEmpty()) {
            Set<String> fileSet = files.keySet();
            for (String key : fileSet) {
                if (files.get(key).exists()) {
                    files.get(key).delete();
                }
            }
        }
    }

    /**
     * 复制文件
     *
     * @param copyFilePath 源路径
     * @param tagPath      目标路径
     * @return
     * @throws
     */
    public static File copyFile(String copyFilePath, String tagPath) throws Exception {

        File copyFile = new File(copyFilePath);
        File destinationFile = new File(tagPath);

        FileInputStream is = null;
        FileOutputStream out = null;
        if (copyFile.exists()) {
            try {
                is = new FileInputStream(copyFile);
                if (!destinationFile.exists()) {
                    destinationFile.createNewFile();
                }
                out = new FileOutputStream(destinationFile);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    out.write(buffer, 0, count);
                }
                is.close();
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new Exception("文件已不存在!");

            } catch (IOException e) {

                e.printStackTrace();
                throw new Exception("复制文件异常");
            }

        }
        return destinationFile;
    }
}
