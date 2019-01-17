package xb.com.refreshdemo.refresh;


import java.io.Serializable;
import java.util.List;

import xb.com.refreshdemo.refresh.RefreshBean;

/**
 * Created by KevenTao on 2017/5/3.
 */

public class ChampionListInfo implements Serializable {
    List<RefreshBean> lawyerList;


    public List<RefreshBean> getLawyerList() {
        return lawyerList;
    }

    public void setLawyerList(List<RefreshBean> lawyerList) {
        this.lawyerList = lawyerList;
    }
}
