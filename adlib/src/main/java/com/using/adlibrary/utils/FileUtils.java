/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import com.orhanobut.logger.Logger;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.security.MessageDigest;
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
/*     */ public class FileUtils
/*     */ {
/*  38 */   public static String TAG = "FileCopyUtils";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean fileIsExists(String strFile) {
/*     */     try {
/*  50 */       File f = new File(strFile);
/*  51 */       if (!f.exists()) {
/*  52 */         return false;
/*     */       }
/*  54 */     } catch (Exception e) {
/*  55 */       return false;
/*     */     } 
/*     */     
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createFile(String path, String filename) {
/*     */     try {
/*  69 */       File file = new File(path + "/" + filename);
/*     */       
/*  71 */       if (!file.exists()) {
/*  72 */         file.createNewFile();
/*     */       }
/*  74 */     } catch (IOException ex) {
/*  75 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean fileExists(String filePath) {
/*  80 */     File file = new File(filePath);
/*  81 */     if (!file.exists()) {
/*  82 */       return false;
/*     */     }
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fileDirExists(String filePath) {
/*  90 */     File file = new File(filePath);
/*  91 */     if (!file.exists() && !file.isDirectory()) {
/*  92 */       file.mkdir();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int copy(String fromFile, String toFile) {
/*  99 */     File root = new File(fromFile);
/*     */ 
/*     */     
/* 102 */     if (!root.exists()) {
/* 103 */       return -1;
/*     */     }
/*     */     
/* 106 */     File[] currentFiles = root.listFiles();
/*     */ 
/*     */     
/* 109 */     File targetDir = new File(toFile);
/*     */     
/* 111 */     if (!targetDir.exists()) {
/* 112 */       targetDir.mkdirs();
/*     */     }
/*     */     
/* 115 */     for (int i = 0; i < currentFiles.length; i++) {
/* 116 */       if (currentFiles[i].isDirectory()) {
/*     */         
/* 118 */         copy(currentFiles[i].getPath() + "/", toFile + currentFiles[i].getName() + "/");
/*     */       }
/*     */       else {
/*     */         
/* 122 */         CopySdcardFile(currentFiles[i].getPath(), toFile + currentFiles[i].getName());
/*     */       } 
/*     */     } 
/* 125 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void deleteFileUnderFolders(File file) {
/* 130 */     if (file.isDirectory()) {
/* 131 */       File[] files = file.listFiles();
/* 132 */       for (int i = 0; i < files.length; i++) {
/* 133 */         File f = files[i];
/* 134 */         deleteFileUnderFolders(f);
/*     */       }
/*     */     
/* 137 */     } else if (file.exists()) {
/* 138 */       file.delete();
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
/*     */   public static int copySpecifyTypeFile(String type, String fromFile, String toFile) {
/* 150 */     File root = new File(fromFile);
/*     */ 
/*     */     
/* 153 */     if (!root.exists()) {
/* 154 */       return -1;
/*     */     }
/*     */     
/* 157 */     File[] currentFiles = root.listFiles();
/*     */     
/* 159 */     File targetDir = new File(toFile);
/*     */     
/* 161 */     if (!targetDir.exists()) {
/* 162 */       targetDir.mkdirs();
/*     */     }
/*     */ 
/*     */     
/* 166 */     for (int i = 0; i < currentFiles.length; i++) {
/* 167 */       if (currentFiles[i].isDirectory()) {
/*     */         
/* 169 */         copySpecifyTypeFile(type, currentFiles[i].getPath() + "/", toFile + currentFiles[i].getName() + "/");
/*     */ 
/*     */       
/*     */       }
/* 173 */       else if (currentFiles[i].getPath().contains(type)) {
/* 174 */         Logger.i("formFile ：" + currentFiles[i].getPath() + "  toFile:  " + toFile + "/" + currentFiles[i].getName(), new Object[0]);
/* 175 */         CopySdcardFile(currentFiles[i].getPath(), toFile + "/" + currentFiles[i].getName());
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int CopySdcardFile(String fromFile, String toFile) {
/*     */     try {
/* 187 */       InputStream fosfrom = new FileInputStream(fromFile);
/* 188 */       OutputStream fosto = new FileOutputStream(toFile);
/* 189 */       byte[] bt = new byte[1024];
/*     */       int c;
/* 191 */       while ((c = fosfrom.read(bt)) > 0) {
/* 192 */         fosto.write(bt, 0, c);
/*     */       }
/* 194 */       fosfrom.close();
/* 195 */       fosto.close();
/* 196 */       return 0;
/*     */     }
/* 198 */     catch (Exception ex) {
/* 199 */       return -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String readToString(String fileName) {
/* 207 */     String encoding = "UTF-8";
/* 208 */     File file = new File(fileName);
/*     */     
/* 210 */     Long filelength = Long.valueOf(file.length());
/* 211 */     byte[] filecontent = new byte[filelength.intValue()];
/*     */     try {
/* 213 */       FileInputStream in = new FileInputStream(file);
/* 214 */       in.read(filecontent);
/* 215 */       in.close();
/* 216 */     } catch (FileNotFoundException e) {
/* 217 */       return null;
/* 218 */     } catch (IOException e) {
/* 219 */       e.printStackTrace();
/*     */     } 
/*     */     try {
/* 222 */       return new String(filecontent, encoding);
/* 223 */     } catch (UnsupportedEncodingException e) {
/* 224 */       System.err.println("The OS does not support " + encoding);
/* 225 */       e.printStackTrace();
/* 226 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getFileSizes(File f) {
/* 260 */     long size = 0L;
/* 261 */     File[] flist = f.listFiles();
/* 262 */     if (flist == null) {
/* 263 */       return 0L;
/*     */     }
/* 265 */     if (flist != null) {
/* 266 */       for (int i = 0; i < flist.length; i++) {
/* 267 */         if (flist[i].isDirectory()) {
/* 268 */           size += getFileSizes(flist[i]);
/*     */         } else {
/* 270 */           size += getFileSize(flist[i]);
/*     */         } 
/*     */       } 
/*     */     }
/* 274 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getFileSize(File file) {
/* 279 */     long size = 0L;
/* 280 */     if (file.exists()) {
/* 281 */       FileInputStream fis = null;
/*     */       try {
/* 283 */         fis = new FileInputStream(file);
/* 284 */         size = fis.available();
/* 285 */       } catch (IOException e) {
/* 286 */         e.printStackTrace();
/*     */       } finally {
/*     */         try {
/* 289 */           fis.close();
/* 290 */         } catch (IOException e) {
/* 291 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 297 */     return size;
/*     */   }
/*     */   
/*     */   public static boolean deleteFile(String filePathAndName) {
/* 301 */     File file = new File(filePathAndName);
/*     */     
/* 303 */     if (file.exists() && file.isFile()) {
/* 304 */       if (file.delete()) {
/* 305 */         System.out.println("删除单个文件" + filePathAndName + "成功！");
/* 306 */         return true;
/*     */       } 
/* 308 */       System.out.println("删除单个文件" + filePathAndName + "失败！");
/* 309 */       return false;
/*     */     } 
/*     */     
/* 312 */     System.out.println("删除单个文件失败：" + filePathAndName + "不存在！");
/* 313 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Bitmap qualityCompressImage(Bitmap image) {
/* 324 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     
/* 326 */     image.compress(Bitmap.CompressFormat.PNG, 100, baos);
/* 327 */     int options = 100;
/*     */     
/* 329 */     while ((baos.toByteArray()).length / 1024 > 100) {
/*     */       
/* 331 */       baos.reset();
/*     */       
/* 333 */       options -= 10;
/*     */       
/* 335 */       image.compress(Bitmap.CompressFormat.PNG, options, baos);
/*     */     } 
/*     */     
/* 338 */     ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
/*     */     
/* 340 */     Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
/* 341 */     return bitmap;
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
/*     */   public static boolean saveImageFile(Bitmap mBitmap, String fileDirPath, String fileName) {
/* 353 */     File currentFile = new File(fileDirPath, fileName);
/* 354 */     FileOutputStream fos = null;
/*     */     try {
/* 356 */       fos = new FileOutputStream(currentFile);
/* 357 */       mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
/* 358 */       fos.flush();
/* 359 */     } catch (FileNotFoundException e) {
/* 360 */       e.printStackTrace();
/* 361 */       return false;
/* 362 */     } catch (IOException e) {
/* 363 */       e.printStackTrace();
/* 364 */       return false;
/*     */     } finally {
/*     */       try {
/* 367 */         if (fos != null) {
/* 368 */           fos.close();
/*     */         }
/* 370 */       } catch (IOException e) {
/* 371 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 374 */     return true;
/*     */   }
/*     */   
/*     */   public static String getFileMD5(File file) {
/* 378 */     String value = null;
/* 379 */     FileInputStream in = null;
/*     */     try {
/* 381 */       in = new FileInputStream(file);
/* 382 */       MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
/* 383 */       MessageDigest md5 = MessageDigest.getInstance("MD5");
/* 384 */       md5.update(byteBuffer);
/* 385 */       BigInteger bi = new BigInteger(1, md5.digest());
/* 386 */       value = bi.toString(16);
/* 387 */     } catch (Exception e) {
/* 388 */       e.printStackTrace();
/*     */     } finally {
/* 390 */       if (null != in) {
/*     */         try {
/* 392 */           in.close();
/* 393 */         } catch (IOException e) {
/* 394 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 398 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSpace(String s) {
/* 408 */     return (s == null || s.trim().length() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static File getFileByPath(String filePath) {
/* 418 */     return isSpace(filePath) ? null : new File(filePath);
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
/*     */   public static boolean writeFileFromString(String filePath, String content, boolean append) {
/* 430 */     return writeFileFromString(getFileByPath(filePath), content, append);
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
/*     */   public static boolean writeFileFromString(File file, String content, boolean append) {
/* 442 */     if (file == null || content == null) {
/* 443 */       return false;
/*     */     }
/* 445 */     if (!createOrExistsFile(file)) {
/* 446 */       return false;
/*     */     }
/* 448 */     BufferedWriter bw = null;
/*     */     try {
/* 450 */       bw = new BufferedWriter(new FileWriter(file, append));
/* 451 */       bw.write(content);
/* 452 */       return true;
/* 453 */     } catch (IOException e) {
/* 454 */       e.printStackTrace();
/* 455 */       return false;
/*     */     } finally {
/* 457 */       closeIO(new Closeable[] { bw });
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
/*     */   public static String readFile2String(String filePath, String charsetName) {
/* 469 */     return readFile2String(getFileByPath(filePath), charsetName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String readFile2String(File file, String charsetName) {
/* 480 */     if (file == null) {
/* 481 */       return null;
/*     */     }
/* 483 */     BufferedReader reader = null;
/*     */     try {
/* 485 */       StringBuilder sb = new StringBuilder();
/* 486 */       if (isSpace(charsetName)) {
/* 487 */         reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
/*     */       } else {
/* 489 */         reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
/*     */       } 
/*     */       String line;
/* 492 */       while ((line = reader.readLine()) != null) {
/* 493 */         sb.append(line).append("\r\n");
/*     */       }
/*     */       
/* 496 */       return sb.delete(sb.length() - 2, sb.length()).toString();
/* 497 */     } catch (IOException e) {
/* 498 */       e.printStackTrace();
/* 499 */       return null;
/*     */     } finally {
/* 501 */       closeIO(new Closeable[] { reader });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean createOrExistsFile(String filePath) {
/* 512 */     return createOrExistsFile(getFileByPath(filePath));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean createOrExistsFile(File file) {
/* 522 */     if (file == null) {
/* 523 */       return false;
/*     */     }
/*     */     
/* 526 */     if (file.exists()) {
/* 527 */       return file.isFile();
/*     */     }
/* 529 */     if (!createOrExistsDir(file.getParentFile())) {
/* 530 */       return false;
/*     */     }
/*     */     try {
/* 533 */       return file.createNewFile();
/* 534 */     } catch (IOException e) {
/* 535 */       e.printStackTrace();
/* 536 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean createOrExistsDir(String dirPath) {
/* 547 */     return createOrExistsDir(getFileByPath(dirPath));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean createOrExistsDir(File file) {
/* 558 */     return (file != null && (file.exists() ? file.isDirectory() : file.mkdirs()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void closeIO(Closeable... closeables) {
/* 568 */     if (closeables == null) {
/*     */       return;
/*     */     }
/* 571 */     for (Closeable closeable : closeables) {
/* 572 */       if (closeable != null)
/*     */         try {
/* 574 */           closeable.close();
/* 575 */         } catch (IOException e) {
/* 576 */           e.printStackTrace();
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\FileUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */