package xb.com.refreshdemo.net;


import com.hjl.library.config.AppConfig;

/**
 * Created by KevenTao on 2017/4/17.
 */

public class UrlConstant {

    public static String getBaseUrl() {
        switch (AppConfig.APP_MODE) {
            case AppConfig.BEIJING_TEST_MODE:
                return "http://testapi.91lvdou.com/v1/";
            case AppConfig.APPSTORE_MODE:
                return "https://api.zhanglv.mobi/v1/";//5.1.0之后使用这个地址
            default:
                return "";
        }
    }

    public final static String BASE_URL = "http://testapi.91lvdou.com/v1/";
//    public final static String BASE_URL = "http://192.168.100.169:8081/v1/";
}

