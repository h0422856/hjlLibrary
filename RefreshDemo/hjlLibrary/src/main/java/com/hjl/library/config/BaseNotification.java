package com.hjl.library.config;

/**
 * Created by peter on 2016/12/14.
 */
public class BaseNotification {
    public static final int NOTIFY_MESSAGE = 1;

    public static final int LOGIN_NOTIFY_MESSAGE = 2;

    public static final int NOTIFY_TOPIC_REFRESH = 3;

    public static final int NOTIFY_KANPING_REFRESH = 4;

    public static final int NOTIFY_LOGIN_STATE_CHANGED = 5;//用户切换身份 发送广播刷新ui

    public static final int NOTIFY_USER_DATA_CHANGED = 6;

    public static final int NOTIFY_ANONYMOUSE_USER_DATA_CHANGED = 7;

    public static final int NOTIFY_NEW_NOTIFICATION_COUNT = 8; //通知主页tab 消息数更新

    public static final int NOTIFY_NEW_CONSULT_COUNT = 9;

    public static final int NOTIFY_PAY_SUCCESS = 10;

    public static final int NOTIFY_SHARE_SUCCESS_WEIXIN = 11;
    public static final int NOTIFY_SHARE_SUCCESS_FRIENDS = 12;
    public static final int NOTIFY_SHARE_SUCCESS_QQ = 13;
    public static final int NOTIFY_SHARE_SUCCESS_WEIBO = 14;

    public static final int NOTIFY_FREEZE_NOTIFICATION_COUNT = 15;

    public static final int NOTIFY_WEBVIEW_DATA_RELOAD = 16;

    public static final int NOTIFY_CONSULT_CONTENT = 17;

    public static final int NOTIFY_CONSULT_URL = 18;

    public static final int NOTIFY_CHANGE_TO_MEDIA_FRAG = 19;

    public static final int NOTIFY_FINISH_PLAY = 20;

    public static final int NOTIFY_LOGIN_OTHER = 21;


    public static final int NOTIFY_LIVE_START = 23;

    public static final int NOTIFY_PAY_WX_SUCCESS = 31;

    public static final int NOTIFY_FOCUS_STATUS_CHANGED = 24;

    public static final int NOTIFY_HAS_NEW_VERSION = 25;

    public static final int NOTIFY_CHANGE_TO_MY_CONSULT = 26;

    public static final int NOTIFY_DELETE_VIDEO = 27;//律师个人页删除自己的视频

    public static final int NOTIFY_DELETE_OR_MODIFY_MYCONSULT = 28;

    public static final int NOTIFY_CONSULTER_ACCEPT = 29;
    public static final int NOTIFY_LOWYER_ACCEPT = 30;
    public static final int NOTIFY_CONSULT_LIVE_START = 32;

    public static final int NOTIFY_AUTH_SUCCESS = 33;
    public static final int NOTIFY_NEW_ISSUE_COUNT = 34;//接到推送 调取咨询未读数 通知主页刷新咨询列表
    public static final int NOTIFY_UNCONNECT_DISONLINE = 35;

    public static final int NOTIFY_REFRESH_NOTIFY_FRAG = 36;//刷新消息界面
    public static final int NOTIFY_REFRESH_CONTENT_ITEM_FRAG = 37;//刷新内容页

    public static final int NOTIFY_WEBVIEW_INTENT_HOMEPAGE = 38;//跳转到首页

    public static final int NOTIFY_QUESTION_DETAIL_FLASH = 39;//律师回答完问题 关闭直播时  通知提问详情页刷新数据 (6.0.0 新加 刷新群组界面)

    public static final int NOTIFY_QUESTION_LIST_FLASH = 40;//发布提问之后  通知提问列表页刷新数据

    public static final int NOTIFY_ERROR_MESSAGE_COUNT = 41; //用户点击 消息的时候 通知页面刷新 拿到未接直联未读数

    public static final int NOTIFY_MESSAGEFRAGMENT = 42; //收到未接直联广播 刷新 Notificationfragment
    //主页跳转到视频详情页 详情页 删除视频 通知主页刷新
    //用户观看互动直播关闭后 退出互动直播 通知主页刷新
    public static final int NOTIFY_REFRESH_MAINFRAGMENT = 43;

    public static final int NOTIFY_REFRESH_MAINFRAGMENT_MESSAGE = 44; //用户和主播在退出互动直播后 刷新下界面 更新下消息未读数

    public static final int NOTIFY_CALL_HEART = 45;//一对一直联时  接受心跳

    public static final int NOTIFY_REFRESH_NEW_MESSAGE = 46;//刷新新消息界面

    public static final int NOTIFY_REFRESH_MESSAGE_FREE = 47;//刷新律师身份的免费咨询列表(弃用)

    public static final int NOTIFY_REFRESH_MESSAGE_CHAT = 48;//刷新一对一消息界面

    public static final int UPDATE_MAIN_UNREADMSG = 49;//更新了一下主页的消息未读数

    public static final int NOTIFY_QUS_COUNT = 50; //通知主页tab 提问数红点更新

    public static final int NOTIFY_PAY_WX_ERROR = 51;//微信支付取消或失败

    public static final int NOTIFY_IS_MINE = 52;//用户收到待付款推送 刷新个人中心

    //websocket
    public static final int NOTIFY_IM_MESSAGE = 53;

    public static final int NOTIFY_TO_SERCIVE = 54;//发送消息给socket

    public static final int NOTIFY_OTHER_LOGIN = 55;//账号异常登陆 通知主页弹框 进行登陆

    public static final int NOTIFY_TELL_MEMBER = 56;//主播发送socket 给用户端通知 有直联消息

    public static final int NOTIFY_JOIN_HDLIVE = 57;//用户端点击接受 通知主播端 进入直播间

    public static final int NOTIFY_REFUSE_HDLIVE = 58;//用户端点击拒绝 通知主播端 让主播关闭当前直播

    public static final int NOTIFY_OPEN_OR_CLOSE_BLUR = 59;//通知另一端 开启模糊

    public static final int NOTIFY_CALL_MSG = 60;//直联内msg

    public static final String CALLJUMPLOGIN = "call_jump_login";//直联出错跳转到登陆页
    private int type;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BaseNotification() {

    }

    public BaseNotification(int type) {
        this.type = type;
    }

    public BaseNotification(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
