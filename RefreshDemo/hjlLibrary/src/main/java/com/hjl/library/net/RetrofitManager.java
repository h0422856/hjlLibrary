package com.hjl.library.net;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.hjl.library.net.retrofit.GsonConverterFactoryPlus;
import com.hjl.library.net.retrofit.ProgressResponseInterceptor;
import com.hjl.library.utils.App;
import com.hjl.library.utils.BaseContants;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Manager
 * <p>
 * 支持Https, 需要使用之前初始化(在App初始化的地方设置, 例如Application#onCreate方法中):
 * RetrofitManager.getInstance().initCertificates(InputStream... cers);
 * 后续设置无效
 *
 * @author hiphonezhu@gmail.com
 * @version [AndroidLibrary, 2018-3-6]
 */

public class RetrofitManager {
    /**
     * Retrofit cache pool, key is 'baseUrl'
     */
    Map<String, Retrofit> retrofitPool = new HashMap<>();

    /**
     * single instance
     */
    static RetrofitManager sInstance;

    /**
     * default client
     */
    OkHttpClient client;

    Interceptor networkInterceptor;

    Interceptor applicationInterceptor;

    X509TrustManager trustManager;
    SSLSocketFactory sslFactory;

    /**
     * Private constructor
     */
    private RetrofitManager() {
    }

    /**
     * Single instance
     *
     * @return
     */
    public static RetrofitManager getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitManager.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * Return Retrofit by baseUrl
     *
     * @param baseUrl
     * @return
     */
    public synchronized Retrofit getRetrofit(String baseUrl) {
        if (client == null) {
            client = buildClient();
        }
        Retrofit retrofit = retrofitPool.get(baseUrl);
        if (retrofitPool.get(baseUrl) == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
//                    .addConverterFactory(GsonConverterFactoryPlus.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofit = builder.build();
            retrofitPool.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    /**
     * 设置拦截器
     * [需要在Application onCreate中初始化]
     *
     * @param applicationInterceptor
     * @param networkInterceptor
     */
    public void initInterceptor(Interceptor applicationInterceptor, Interceptor networkInterceptor) {
        this.applicationInterceptor = applicationInterceptor;
        this.networkInterceptor = networkInterceptor;
    }

//    /**
//     * 初始化 https
//     * [需要在Application onCreate中初始化]
//     */
//    public void initHttps() {
//        try {
//            trustManager = SSLUtils.getDefaultX509TrustManager();
//            sslFactory = SSLUtils.build(trustManager);
//
//            client = buildClient();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 设置Https证书
//     * [需要在Application onCreate中初始化]
//     *
//     * @param cers 包含公钥的cer证书
//     */
//    public void initHttps(InputStream... cers) {
//        try {
//            trustManager = SSLUtils.getX509TrustManager(cers);
//            sslFactory = SSLUtils.build(trustManager);
//
//            client = buildClient();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 设置Https证书(双向认证)
//     * [需要在Application onCreate中初始化]
//     *
//     * @param bks  jks转化之后的bks格式证书(转化方式: https://sourceforge.net/projects/portecle/files/latest/download?source=files下载portecle-1.9.zip)
//     * @param pwd  证书的秘钥
//     * @param cers 包含公钥的cer证书
//     */
//    public void initHttps(InputStream bks, String pwd, InputStream... cers) {
//        try {
//            trustManager = SSLUtils.getX509TrustManager(cers);
//            sslFactory = SSLUtils.build(bks, pwd, trustManager);
//
//            client = buildClient();
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Custom OkHttpClient
     *
     * @return
     */
    private OkHttpClient buildClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (!TextUtils.isEmpty(message)
                        && message.startsWith("{")
                        && message.endsWith("}")) {
                    LogUtils.json(message);
                } else if (!TextUtils.isEmpty(message)) {
                    LogUtils.d(message);
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File cacheDir = null;
        try {
            cacheDir = getDiskCacheDir(App.getInstance().getApplicationContext(), "Retrofit-Cache");
        } catch (Exception ex) {
            LogUtils.e(ex, null, "");
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                // no proxy
                .proxy(Proxy.NO_PROXY)
                // log interceptor
                .addInterceptor(loggingInterceptor)
//                .addNetworkInterceptor(loggingInterceptor)
                // retry when connect failure
                .retryOnConnectionFailure(true)
                // connect timeout 10s
                .connectTimeout(10, TimeUnit.SECONDS)
                // read timeout 20s
                .readTimeout(20, TimeUnit.SECONDS)
                // write timeout 20s
                .writeTimeout(20, TimeUnit.SECONDS)
                // Besides cache setting, we also need cache support(it usually controlled by server),
                // but, it can also be controlled by client with http header(just like "Cache-Control:public,max-age=120").
                .cache(cacheDir != null ? new Cache(cacheDir, 10 * 1024 * 1024) : null);
        if (sslFactory != null) {
            builder.sslSocketFactory(sslFactory, trustManager);
        }
        if (networkInterceptor != null) {
            builder.addNetworkInterceptor(networkInterceptor);
        }
        builder.addInterceptor(new ProgressResponseInterceptor());
        if (applicationInterceptor != null) {
            builder.addInterceptor(applicationInterceptor);
        }
        return builder.build();
    }

    /**
     * 获得磁盘缓存目录 [PS：应用卸载后会被自动删除]
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            File dir = context.getApplicationContext().getExternalCacheDir();
            if (dir == null) {
                dir = context.getApplicationContext().getCacheDir();
            }
            cachePath = dir.getPath();
        } else {
            File dir = context.getApplicationContext().getCacheDir();
            if (dir == null) {
                return null;
            }
            cachePath = dir.getPath();
        }
        File dir;
        if (TextUtils.isEmpty(uniqueName)) {
            dir = new File(cachePath);
        } else {
            dir = new File(cachePath + File.separator + uniqueName);
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
