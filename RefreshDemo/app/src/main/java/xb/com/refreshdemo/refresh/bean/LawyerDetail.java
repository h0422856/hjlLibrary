package xb.com.refreshdemo.refresh.bean;

import java.io.Serializable;
import java.util.List;

import xb.com.refreshdemo.bean.User;

/**
 * Created by KevenTao on 2017/5/3.
 */

public class LawyerDetail implements Serializable {
    private static final long serialVersionUID = 6868444404861282012L;
    public static final int TYPE_BANNER = 0;
    public static final int TYPE_LAWYER_LIST = 1;
    public static final int TYPE_EMPTY = 2;//律师列表为空
    public static final int TYPE_CHAMPION_LIST = 3;
    private User lawyer;
    private int type;


    public User getLawyer() {
        return lawyer;
    }

    public void setLawyer(User lawyer) {
        this.lawyer = lawyer;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
