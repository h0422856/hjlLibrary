package xb.com.refreshdemo.roundedimage.bean;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [zhanglv-android, 2018/09/19 13:54]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class ImgBean {
    private String path;
    private String width;
    private String height;

    public ImgBean() {
    }

    public ImgBean(String path, String width, String height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
