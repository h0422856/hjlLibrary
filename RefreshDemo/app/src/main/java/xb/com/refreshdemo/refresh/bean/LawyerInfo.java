package xb.com.refreshdemo.refresh.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KevenTao on 2017/5/3.
 */

public class LawyerInfo implements Serializable {
    private static final long serialVersionUID = 6868444404861282012L;
    private int totalCount;

    private int pageNum;
    private long pageTime;
    List<LawyerDetail> lawyerList;

    public List<LawyerDetail> getLawyerList() {
        return lawyerList;
    }

    public void setLawyerList(List<LawyerDetail> lawyerList) {
        this.lawyerList = lawyerList;
    }

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
}
