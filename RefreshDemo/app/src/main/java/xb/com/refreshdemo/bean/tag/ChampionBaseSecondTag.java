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

public class ChampionBaseSecondTag implements Serializable {

    private ChampionTag classifyTag;
    private List<ChampionTag> classifyTagList;

    public ChampionTag getClassifyTag() {
        return classifyTag;
    }

    public void setClassifyTag(ChampionTag classifyTag) {
        this.classifyTag = classifyTag;
    }

    public List<ChampionTag> getClassifyTagList() {
        return classifyTagList;
    }

    public void setClassifyTagList(List<ChampionTag> classifyTagList) {
        this.classifyTagList = classifyTagList;
    }
}
