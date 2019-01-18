package xb.com.refreshdemo.selectImg;

import android.app.Activity;
import android.util.Pair;

import com.hjl.library.net.logic.ApiPost;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import xb.com.refreshdemo.R;
import xb.com.refreshdemo.net.api.NetApi;
import xb.com.refreshdemo.net.base.DemoBaseLogic;
import xb.com.refreshdemo.net.config.AccountManager;
import xb.com.refreshdemo.roundedimage.BitmapUtil;
import xb.com.refreshdemo.roundedimage.bean.FileData;
import xb.com.refreshdemo.roundedimage.bean.ImgBean;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/18 14:44]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class AddStyleLogic extends DemoBaseLogic {
    private NetApi api;

    /**
     * 构造函数
     *
     * @param subscriber 最终订阅者
     */
    public AddStyleLogic(Object subscriber) {
        super(subscriber);
        api = create(NetApi.class);
    }


    public void uploadImgs(List<String> photos, Activity activity) {
        Map<String, RequestBody> listPair = new HashMap<>();
        for (int i = 0; i < photos.size(); i++) {
            if (photos.get(i) != null) {
                ImgBean imgBean = BitmapUtil.compressWithLs(photos.get(i), activity);
                assert imgBean != null;
                listPair.put("file\"; filename=\"" + new File(imgBean.getPath()).getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), new File(imgBean.getPath())));
            }
        }
        sendRequest(api.uploadImgs(getBaseUrl() + "file/?fileType=image", listPair), R.id.filed_uploadimgs_id);
    }

    public void addPersonalShow(String content, ArrayList<FileData> fileDatas, List<ImgBean> list) {
        Map<String, Object> params = new HashMap<>();
        params.put("uId", AccountManager.getInstance().getCurrentUser().getuId());
        params.put("content", content);
        if (fileDatas != null && fileDatas.size() > 0) {
            String imgs = fileDatas.get(0).getUri() + "?"
                    + list.get(0).getWidth() + "&" +
                    list.get(0).getHeight();
            for (int i = 1; i < fileDatas.size(); i++) {
                imgs = imgs + "," + fileDatas.get(i).getUri() + "?"
                        + list.get(i).getWidth() + "&" +
                        list.get(i).getHeight();
            }
            params.put("picture", imgs);
        }
        sendRequest(api.addPersonalShow(params), R.id.post_add_personal_show_id);
    }

}
