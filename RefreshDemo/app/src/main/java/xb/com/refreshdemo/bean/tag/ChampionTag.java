package xb.com.refreshdemo.bean.tag;


import java.io.Serializable;

/**
 * [description about this class]
 * 学霸专用tag标签
 *
 * @author hujiao
 * @version [XBApp, 2018/11/27 18:21]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class ChampionTag implements Serializable {

    private int tagId;
    private String tagName;
    private String tagImage;
    private String tagOrder;
    private int level;
    private String tip;
    private String fontColor;
    private String backgroundColor;
    private String customerService;
    private boolean isSelect;
    private int secondTagId;//传其他时  传二级标签id

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagImage() {
        String tag_image = "";
        if (tagImage.startsWith("https")) {
            tag_image = tagImage.replace("https", "http");
        } else {
            tag_image = tagImage;
        }
        return tagImage;
    }

    public void setTagImage(String tagImage) {
        this.tagImage = tagImage;
    }

    public String getTagOrder() {
        return tagOrder;
    }

    public void setTagOrder(String tagOrder) {
        this.tagOrder = tagOrder;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getSecondTagId() {
        return secondTagId;
    }

    public void setSecondTagId(int secondTagId) {
        this.secondTagId = secondTagId;
    }
}
