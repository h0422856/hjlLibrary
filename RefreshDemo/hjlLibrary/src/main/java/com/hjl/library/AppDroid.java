package com.hjl.library;

import android.app.Application;

import com.hjl.library.utils.App;
import com.hjl.library.utils.slideback.ActivityHelper;


/**
 * @author zhuhf
 * @version [AndroidLibrary, 2018-03-06]
 */
public class AppDroid extends Application {
    private static AppDroid instance;

    private static ActivityHelper mActivityHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        App.init(this);
        //左滑删除初始化保存工具类
        mActivityHelper = new ActivityHelper();
        registerActivityLifecycleCallbacks(mActivityHelper);
    }

    public static AppDroid getInstance() {
        return instance;
    }

    public static ActivityHelper getActivityHelper() {
        return mActivityHelper;
    }

}
