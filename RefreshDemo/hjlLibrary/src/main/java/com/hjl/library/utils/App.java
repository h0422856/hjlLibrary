package com.hjl.library.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.hjl.library.net.RetrofitManager;

/**
 * @author hiphonezhu@gmail.com
 * @version [AndroidLibrary, 2018-3-6]
 */
public class App {
    static App sInstance;
    static Context appContext;
    static UIStateHelper uiStateHelper;
    int visibleActivityCount = 0;


    public static void init(Application application) {
        sInstance = new App();
        appContext = application.getApplicationContext();
        uiStateHelper = new UIStateHelper();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                uiStateHelper.addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                sInstance.visibleActivityCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                sInstance.visibleActivityCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                uiStateHelper.removeActivity(activity);
            }
        });
        /**
         * 如果使用CA机构的证书，最低也需要适配 Android 4.x 对 TLS1.1、TLS1.2 的支持（默认 Android 20+ 才支持）。
         * 当然，如果服务器最低支持 TLS1.0，则可以不需要做任何适配。
         */
    }

    public static App getInstance() {
        if (sInstance == null) {
            throw new RuntimeException("must call init() first");
        }
        return sInstance;
    }

    /**
     * App 是否在后台
     *
     * @return true：后台
     */
    public boolean isInBackground() {
        return visibleActivityCount == 0;
    }

    public Context getApplicationContext() {
        return appContext;
    }

    public UIStateHelper getUiStateHelper() {
        return uiStateHelper;
    }

    /**
     * 退出程序
     *
     * @param context
     */
    public void exist(Context context) {
        uiStateHelper.finishAll();
    }
}
