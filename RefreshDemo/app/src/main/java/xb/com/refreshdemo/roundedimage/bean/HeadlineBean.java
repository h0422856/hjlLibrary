package xb.com.refreshdemo.roundedimage.bean;

/**
 * Created by KevenTao on 2017/4/10.
 */

public class HeadlineBean {
    private String title;
    private String content;

    public HeadlineBean() {
    }


    public HeadlineBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public HeadlineBean(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HeadlineBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }





}
