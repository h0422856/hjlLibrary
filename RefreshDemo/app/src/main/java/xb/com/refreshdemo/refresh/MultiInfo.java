package xb.com.refreshdemo.refresh;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by KevenTao on 2017/5/3.
 */

public class MultiInfo implements MultiItemEntity, Serializable {

    public static final int TYPE_SERIES = 1;
    public static final int TYPE_PESALE = 2;
    private int itemType;

    private RefreshBean refreshBean;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public RefreshBean getRefreshBean() {
        return refreshBean;
    }

    public void setRefreshBean(RefreshBean refreshBean) {
        this.refreshBean = refreshBean;
    }



}
