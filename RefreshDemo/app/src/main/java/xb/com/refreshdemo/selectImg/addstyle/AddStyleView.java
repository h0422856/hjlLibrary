package xb.com.refreshdemo.selectImg.addstyle;


/**
 * [description about this class]
 * 标签列表
 *
 * @author hujiao
 * @version [zhanglv-android, 2018/03/26 19:40]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public interface AddStyleView {

    // 上传图片给服务器
    void addPersonalShow();

    //  接口调用失败 统一处理
    void errorRequest(String msg);
}
