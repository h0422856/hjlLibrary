package xb.com.refreshdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hjl.library.AppDroid;
import com.hjl.library.net.RetrofitManager;
import com.hjl.library.utils.BaseContants;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import xb.com.refreshdemo.net.config.AccountManager;
import xb.com.refreshdemo.net.config.Constant;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 13:54]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class MyApplication extends AppDroid {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RetrofitManager.getInstance().initInterceptor(interceptor, null);
    }

    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();
            String credential = "";
            if (AccountManager.getInstance().getCurrentUser() != null) {
                if (!TextUtils.isEmpty(AccountManager.getInstance().getCurrentUser().getPassword())) {
                    credential = Credentials.basic(AccountManager.getInstance().getCurrentUser().getPhoneNumber(), AccountManager.getInstance().getCurrentUser().getPassword());
                } else {
                    credential = Credentials.basic(AccountManager.getInstance().getCurrentUser().getPhoneNumber(), "");
                }
            }
            Request.Builder requestBuilder = original.newBuilder()
                    .header("api-version", BaseContants.ServerVersion)//平台
                    .header("appNo", BaseContants.getAPPNo() + "")//系统版本号
                    .header("source", "android")//设备信息
                    .header("Authorization", credential);//设备信息
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };

    public static MyApplication getInstance() {
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return super.getSharedPreferences(Constant.SPNAME, Context.MODE_PRIVATE);
    }


}
