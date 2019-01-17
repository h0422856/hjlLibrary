package com.hjl.library.utils;

/**
 * Created by peter on 2016/9/1.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjl.library.AppDroid;
import com.hjl.library.net.RetrofitManager;
import com.hjl.library.net.retrofit.bean.InfoResult;
import com.hjl.library.net.retrofit.single.Constants;
import com.hjl.library.net.retrofit.single.UrlAPI;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 * 需要在Application中注册，为了要在程序启动器就监控整个程序。
 */
public class CrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
//    //用于格式化日期,作为日志文件名的一部分
//    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",Locale.getDefault());

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            } finally {

            }
            AppDroid.getInstance().onTerminate();
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //收集设备参数信息
        collectDeviceInfo(mContext);
        //保存日志文件
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        //获取手机所有类型信息
        //getDeclaredFields是获取所有申明信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
        try {
            //这里获取的是手机imei信息,我写在另一个类里了，参见上篇关于TelephonyManager的博文
//            infos.put("imei", BaseUtils.getInfo(mContext));
            //通过WifiManager获取手机MAC地址 只有手机开启wifi才能获取到mac地址
//            infos.put("mac", BaseUtils.getMacAddress(mContext));
            //这个获取的是手机屏幕信息，在另一个类里，就不po文了
//            infos.put("screen", Constant.ScreenHeight+"*"+Constant.ScreenWith);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {

//        String mobilemsg = "";
//        //遍历HashMap
//        for (Map.Entry<String, String> entry : infos.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            mobilemsg += key + "=" + value + "\r\n";
//        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String errormsg = writer.toString();
        Log.e(TAG, errormsg);
//        HttpUtils http = new HttpUtils();
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("mobilestr",mobilemsg);
//        params.addBodyParameter("frmsys","andro");
//        params.addBodyParameter("errorstr",errormsg);
//        http.send(HttpRequest.HttpMethod.POST, "需要传送的地址", params,null);
        sendCrashLog2Sever(errormsg);
        return null;
    }

    /**
     * 将捕获的导致崩溃的错误信息发送给服务器
     */
    private void sendCrashLog2Sever(String logsInfo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("appVersion", AppUtils.getAppVersionName());
        params.put("deviceModel", DeviceUtils.getModel());
        params.put("logsInfo", logsInfo);
        params.put("rcId", 0);
        params.put("systemVersion", DeviceUtils.getSDKVersionName());
        params.put("systerm", 1);
        String json = GsonUtils.toJson(params);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        RetrofitManager.getInstance().
                getRetrofit(Constants.Base_url)
                .create(UrlAPI.class)
                .postSendCrashLogSever(body)
                //添加以下两段代码 异步线程
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoResult<Object>>() {
                    @Override
                    public void accept(InfoResult<Object> objectInfoResult) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        InfoResult infoResult = (InfoResult) (Object) throwable;
                        if (infoResult.isSuccess()) {
                            ToastUtils.showShort(infoResult.getData() + "===>" + infoResult.getCode());
                        } else {
                            ToastUtils.showShort(infoResult.getData() + "===>"
                                    + infoResult.getCode() + "===>" + infoResult.getErrmsg());
                        }
                    }
                });
    }
}