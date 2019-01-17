package xb.com.refreshdemo.net.base;

import com.hjl.library.net.logic.BaseLogic;

import xb.com.refreshdemo.Url;
import xb.com.refreshdemo.net.UrlConstant;
import xb.com.refreshdemo.net.config.AccountManager;

/**
 * @author zhuhf
 * @version [AndroidLibrary, 2018-03-07]
 */
public class DemoBaseLogic extends BaseLogic {
    /**
     * 构造函数
     *
     * @param subscriber 最终订阅者
     */
    public DemoBaseLogic(Object subscriber) {
        super(subscriber);
    }

    @Override
    protected String getBaseUrl() {
        return UrlConstant.BASE_URL;
    }

}
