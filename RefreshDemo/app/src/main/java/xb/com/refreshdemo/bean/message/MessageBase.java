package xb.com.refreshdemo.bean.message;


import java.io.Serializable;
import java.util.List;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [zhanglv-android, 2018/03/21 15:48]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class MessageBase implements Serializable {
    private int totalCount;

    private int pageNum;
    private long pageTime;
    private List<MessageModel> messageList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageTime() {
        return pageTime;
    }

    public void setPageTime(long pageTime) {
        this.pageTime = pageTime;
    }

    public List<MessageModel> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageModel> messageList) {
        this.messageList = messageList;
    }
}
