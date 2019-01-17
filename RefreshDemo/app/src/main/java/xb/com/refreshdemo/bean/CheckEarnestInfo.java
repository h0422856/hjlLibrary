package xb.com.refreshdemo.bean;

import java.io.Serializable;

/**
 * Created by KevenTao on 2017/4/25.
 * 诚意金提问判断首充
 */

public class CheckEarnestInfo implements Serializable {
    private String alertCode;
    private String alertMessage;

    public String getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(String alertCode) {
        this.alertCode = alertCode;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

}
