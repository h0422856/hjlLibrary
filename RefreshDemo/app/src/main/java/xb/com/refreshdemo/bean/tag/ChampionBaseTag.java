package xb.com.refreshdemo.bean.tag;

import java.io.Serializable;
import java.util.List;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [XBApp, 2018/11/27 18:21]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class ChampionBaseTag implements Serializable {
    private List<ChampionBaseSecondTag> classifyTagMainPageList;

    public List<ChampionBaseSecondTag> getClassifyTagMainPageList() {
        return classifyTagMainPageList;
    }

    public void setClassifyTagMainPageList(List<ChampionBaseSecondTag> classifyTagMainPageList) {
        this.classifyTagMainPageList = classifyTagMainPageList;
    }
}
