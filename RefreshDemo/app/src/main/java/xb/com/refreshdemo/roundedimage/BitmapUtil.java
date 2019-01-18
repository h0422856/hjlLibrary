/*
 * Copyright 2014 LianXi. All rights reserved.
 * LianXi PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * @BitmapUtil.java - 2014年5月20日
 */

package xb.com.refreshdemo.roundedimage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.blankj.utilcode.util.ImageUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;
import java.util.UUID;

import xb.com.refreshdemo.roundedimage.bean.ImgBean;

/**
 * BitmapUtil
 *
 * @author OCEAN
 */
public class BitmapUtil {

    private static final int MAX_HARDWARE_PIXS = 2048;

    private static final int DEFAULT_MEMORY_CACHE_SIZE = 10 * 1024 * 1024;// 默认是10m内存的缓存

    private static final int maxCacheSize = (int) Math.min(DEFAULT_MEMORY_CACHE_SIZE, Runtime.getRuntime().maxMemory() / 5);


    public static Options getFileOptions(File f) throws Exception {
        // First decode with inJustDecodeBounds=true to check dimensions
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(f.getPath(), options);
        return options;
    }

    /**
     * @param f
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws Exception
     */
    public static Bitmap decodeFile(File f, int reqWidth, int reqHeight) throws Exception {
        // First decode with inJustDecodeBounds=true to check dimensions
        final Options options = getFileOptions(f);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(f.getPath(), options);
    }

    public static Bitmap getCompressImageFile(File f, int outwidth, int outHeight) throws FileNotFoundException, OutOfMemoryError {
        // First decode with inJustDecodeBounds=true to check dimensions
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(f.getPath(), options);

        options.outWidth = outwidth;
        options.outHeight = outHeight;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(f.getPath(), options);
    }

    public static Bitmap decodeFile(Context context, byte[] data, int maxSize) throws OutOfMemoryError {
        // First decode with inJustDecodeBounds=true to check dimensions
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, maxSize, maxSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /**
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            if (isBigPic(width, height)) {
                if (width > height) {
                    inSampleSize = (int) Math.ceil((float) width / (float) reqWidth);
                } else {
                    inSampleSize = (int) Math.ceil((float) height / (float) reqHeight);
                }
            } else {
                return inSampleSize;
            }

        }
        return inSampleSize;
    }

    @SuppressLint({"FloatMath", "FloatMath"})
    public static Bitmap reSizeBitmap(String localPath, int maxWith, int maxHeight) {
        Bitmap bitmap = null;
        try {
            final Options options = new Options();
            options.inScaled = false;
            options.inPreferredConfig = Config.RGB_565;
            options.inDither = false;
            options.inJustDecodeBounds = true;
            // remove magic string we add in GalleryBrowser
            if (localPath.startsWith("file://")) {
                localPath = localPath.replace("file://", "");
            }
            bitmap = BitmapFactory.decodeFile(localPath, options);
            if (options.outHeight == -1 || options.outWidth == -1) {
                ExifInterface exifInterface = new ExifInterface(localPath);
                int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的高度
                int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的宽度
                options.outWidth = width;
                options.outHeight = height;
            }
            int width = options.outWidth;
            int height = options.outHeight;
            int ratio = 1;
            if (width > 0 && height / width > 3) {
                ratio = getRatio(width, height, 1024, false);
            } else {
                maxHeight = maxHeight > 1024 ? 1024 : maxHeight;
                maxWith = maxWith > 1024 ? 1024 : maxWith;
                int ratioX = 1;
                int ratioY = 1;
                if (width > maxWith) {
                    ratioX = (int) Math.ceil((float) width / maxWith);
                }
                if (height > maxHeight) {
                    ratioY = (int) Math.ceil((float) height / maxHeight);
                }
                ratio = Math.min(ratioX, ratioY);
            }

            options.inDither = false;
            options.inJustDecodeBounds = false;
            options.inSampleSize = ratio;
            bitmap = BitmapFactory.decodeFile(localPath, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            requtstLowMemory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * @param sourceFile
     * @return
     * @throws IOException
     */
    public static Bitmap adjustThePhotoOrientation(File sourceFile) throws IOException {
        Bitmap bitmap = null;
        ExifInterface exifInterface = new ExifInterface(sourceFile.getPath());
        int tag = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        int degree = 0;
        if (tag == ExifInterface.ORIENTATION_ROTATE_90) {
            degree = 90;
        } else if (tag == ExifInterface.ORIENTATION_ROTATE_180) {
            degree = 180;
        } else if (tag == ExifInterface.ORIENTATION_ROTATE_270) {
            degree = 270;
        }
        if (degree != 0) {
            Matrix m = new Matrix();
            bitmap = BitmapUtil.reSizeBitmap(sourceFile.getPath(), 1024, 1024);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            m.setRotate(degree);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);
        }
        return bitmap;
    }

    public static void requtstLowMemory() {
        //PhotoLoader.getInstance().requestLowMemory();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getRatio(int width, int height, int maxWith, boolean supportHardwareAccelerated) {
        int ratio = 1;
        if (width > maxWith) {
            ratio = (int) Math.ceil((float) width / maxWith);
        }
        while (true) {
            float tHeight = (float) height / ratio;
            float tWidth = (float) width / ratio;
            float size = tHeight * tWidth * 4;
            if (size < maxCacheSize * 0.8) {
                break;
            }
            ratio++;
        }
        while (supportHardwareAccelerated) {
            float tHeight = (float) height / ratio;
            if (tHeight < MAX_HARDWARE_PIXS) {
                ratio = nextPowerOf2(ratio);
                break;
            }
            ratio++;
        }
        return ratio;
    }

    private static int nextPowerOf2(int n) {
        n -= 1;
        n |= n >>> 16;
        n |= n >>> 8;
        n |= n >>> 4;
        n |= n >>> 2;
        n |= n >>> 1;
        return n + 1;
    }

    public static Bitmap getSqureBitmap(Bitmap srcBmp) {
        if (srcBmp.getWidth() >= srcBmp.getHeight()) {
            return Bitmap.createBitmap(srcBmp, srcBmp.getWidth() / 2 - srcBmp.getHeight() / 2, 0, srcBmp.getHeight(), srcBmp.getHeight());
        } else {
            return Bitmap.createBitmap(srcBmp, 0, srcBmp.getHeight() / 2 - srcBmp.getWidth() / 2, srcBmp.getWidth(), srcBmp.getWidth());
        }
    }

    public static Bitmap getCircleBitmap(Bitmap bitmapimg) {
        bitmapimg = getSqureBitmap(bitmapimg);

        Bitmap output = Bitmap.createBitmap(bitmapimg.getWidth(), bitmapimg.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmapimg.getWidth(), bitmapimg.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmapimg.getWidth() / 2, bitmapimg.getHeight() / 2, bitmapimg.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmapimg, rect, rect, paint);
        return output;
    }

    /**
     * 将彩色图转换为灰度图
     *
     * @param img 位图
     * @return 返回转换好的位图
     */
    public static Bitmap convertGreyImg(Bitmap img) {
        int width = img.getWidth();         //获取位图的宽
        int height = img.getHeight();       //获取位图的高

        int[] pixels = new int[width * height]; //通过位图的大小创建像素点数组

        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);
        result.setPixels(pixels, 0, width, 0, 0, width, height);
        return result;
    }


//    public static Bitmap blur(Bitmap bkg) {
//        long startMs = System.currentTimeMillis();
//        float scaleFactor = 2;
//        float radius = 20;
//        Bitmap overlay = Bitmap.createBitmap((int) (bkg.getWidth() / scaleFactor),
//                (int) (bkg.getHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(overlay);
//        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
//        Paint paint = new Paint();
//        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
//        canvas.drawBitmap(bkg, 0, 0, paint);
//
//        try {
//            FastBlur.doBlur(overlay, (int) radius, true);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            overlay = null;
//        }
//        LogUtil.d("blur", "blur time:" + (System.currentTimeMillis() - startMs) + "ms");
//        return overlay;
//    }


    /**
     * //生成水印的过程。其实分为三个环节：第一，载入原始图片;第二，载入水印图片;第三，保存新的图片。
     * 　　* create the bitmap from a byte array
     * <p/>
     * 　　*
     * <p/>
     * 　　* @param src the bitmap object you want proecss
     * <p/>
     * 　　* @param watermark the water mark above the src
     * <p/>
     * 　　* @return return a bitmap object ,if paramter's length is 0,return null
     * <p/>
     */
    public static Bitmap createWatermarkBitmap(Bitmap src, Bitmap watermark) {

        if (src == null) {

            return null;

        }

        int w = src.getWidth();

        int h = src.getHeight();

        int ww = watermark.getWidth();

        int wh = watermark.getHeight();
        Bitmap newb = null;
        try {
            newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        } catch (OutOfMemoryError error) {
            if (newb != null) {
                if (!newb.isRecycled()) {
                    newb.recycle();
                    System.gc();
                    newb = null;
                    newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
                }
            }
        }


        Canvas cv = new Canvas(newb);

        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src

        //cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// 在src的右下角画入水印
        cv.drawBitmap(watermark, 10, 10, null);
        cv.save();// 保存
        cv.restore();// 存储
        return newb;

    }

    //质量压缩方法：压缩size单位kb
    public static Bitmap compressImage(Bitmap image, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 75, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        if (baos.toByteArray().length / 1024 > size) {  //循环判断如果压缩后图片是否大于250kb,大于继续压缩
            int options = (size * 1024 * 100) / baos.toByteArray().length;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中 ???
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    //使用Bitmap加Matrix来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);

        return resizedBitmap;
    }

    /**
     * 判断是否是长图 ,true, 需要压缩；false，不需要压缩
     *
     * @param with
     * @param height
     * @return
     */
    public static Boolean isBigPic(int with, int height) {
        if (with > height) {
            // return needCompress(with, height, AndroidUtil.getDeviceWidth());
        } else {
            //return needCompress(height, with,AccountManager.getDeviceWidth());
        }
        return false;
    }

    private static final float longLongImgThreshold = 5;

    private static final float longLongImgNeedCompressThreshold = 1.5f;

    /**
     * 是否需要压缩
     *
     * @param longSide   长边
     * @param shortSide  短边
     * @param deviceSide 长边对于的设备长度
     * @return true, 需要压缩；false，不需要压缩
     */
    private static boolean needCompress(long longSide, long shortSide, long deviceSide) {
        if (shortSide != 0 && (longSide / shortSide > longLongImgThreshold)) {
            // 长图/宽图，超过设备对于长度的1.5倍的话压缩，否则上传原图
            if ((longSide / (shortSide * 1.0f)) > longLongImgNeedCompressThreshold) {
                return true;
            } else {
                return false;
            }
        } else {
            // 非长度/宽图，正常压缩
            return true;
        }
    }


    public static Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }


    public static void saveBitmap(Bitmap bm, String path) {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                        : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;

    }

    public static void insertImageToSystemGallery(Context context, String filePath, Bitmap bitmap) {
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "", "");
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }


    /**
     * 压缩图片 Listener 方式
     */
    public static ImgBean compressWithLs(String photo, Context context) {
        Bitmap bm = getSmallBitmap(photo);
        if (bm == null) {
            return null;
        } else {
            String path = saveBitmap(bm);
            String wg[] = getimage(photo).split(",");
            return new ImgBean(path, wg[0], wg[1]);
        }
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static String getimage(String srcPath) {
        Options newOpts = new Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 400f;// 这里设置高度为800f
        float ww = 400f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;
        return width + "," + height;// 压缩好比例大小后再进行质量压缩
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize1(options, 1000, 1000);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize1(Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 保存bitmap到本地
     *
     * @param mBitmap
     * @return
     */
    public static String saveBitmap(Bitmap mBitmap) {
        String savePath = getPath();
        File filePic;
        try {
            filePic = new File(savePath + generateFileName() + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }

    private static String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

}
