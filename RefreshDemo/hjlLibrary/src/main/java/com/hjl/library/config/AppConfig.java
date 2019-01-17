package com.hjl.library.config;

/**
 * Created by KevenTao on 2017/4/10.
 */

public class AppConfig {
    //外测环境 北京测试
    public static final int BEIJING_TEST_MODE = 2000;
    //上线的市场环境
    public static final int APPSTORE_MODE = 3000;

    public static final int APP_MODE = APPSTORE_MODE;

    public static final boolean IS_DEBUG = (APP_MODE != APPSTORE_MODE);
}
