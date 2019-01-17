package xb.com.refreshdemo.bean.banner;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by KevenTao on 2017/4/17.
 */

public class BannerInfo implements Serializable {
    private static final long serialVersionUID = 6838445424861282012L;
    private int totalCount;

    private int pageNum;

    private long pageTime;

    private ArrayList<Banner> bannerList;

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

    public ArrayList<Banner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<Banner> bannerList) {
        this.bannerList = bannerList;
    }

}
