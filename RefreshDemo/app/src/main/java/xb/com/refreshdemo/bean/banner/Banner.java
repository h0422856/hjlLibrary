package xb.com.refreshdemo.bean.banner;

import java.io.Serializable;

/**
 * Created by KevenTao on 2017/4/17.
 */
public class Banner implements Serializable {
    private static final long serialVersionUID = 6868444004861342012L;
    private int bId;

    private String coverUrl;
    private String fitCoverUrl;
    private String actionUrl;

    private String lawyerName;

    private int type;

    private int position;
    private int model;
    private long lawyerId1;
    private long lawyerId2;
    private int lawyerId3;
    private String showUserType;
    private long startDate;
    private long endDate;
    private int status;
    private int sortNo;
    private long create_uid;
    private long updated;


    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getFitCoverUrl() {
        return fitCoverUrl;
    }

    public void setFitCoverUrl(String fitCoverUrl) {
        this.fitCoverUrl = fitCoverUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getLawyerName() {
        return lawyerName;
    }

    public void setLawyerName(String lawyerName) {
        this.lawyerName = lawyerName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public long getLawyerId1() {
        return lawyerId1;
    }

    public void setLawyerId1(long lawyerId1) {
        this.lawyerId1 = lawyerId1;
    }

    public long getLawyerId2() {
        return lawyerId2;
    }

    public void setLawyerId2(long lawyerId2) {
        this.lawyerId2 = lawyerId2;
    }

    public int getLawyerId3() {
        return lawyerId3;
    }

    public void setLawyerId3(int lawyerId3) {
        this.lawyerId3 = lawyerId3;
    }

    public String getShowUserType() {
        return showUserType;
    }

    public void setShowUserType(String showUserType) {
        this.showUserType = showUserType;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public long getCreate_uid() {
        return create_uid;
    }

    public void setCreate_uid(long create_uid) {
        this.create_uid = create_uid;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }
}
