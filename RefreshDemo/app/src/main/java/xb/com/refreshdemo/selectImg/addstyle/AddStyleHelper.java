package xb.com.refreshdemo.selectImg.addstyle;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Pair;

import com.blankj.utilcode.util.FileUtils;
import com.hjl.library.ui.base.Presenter;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xb.com.refreshdemo.R;
import xb.com.refreshdemo.net.config.AccountManager;
import xb.com.refreshdemo.net.config.Constant;
import xb.com.refreshdemo.roundedimage.BitmapUtil;
import xb.com.refreshdemo.roundedimage.bean.FileData;
import xb.com.refreshdemo.roundedimage.bean.ImgBean;
import xb.com.refreshdemo.selectImg.AddStyleActivity;
import xb.com.refreshdemo.selectImg.AddStyleLogic;
import xb.com.refreshdemo.selectImg.BottomDialog;
import xb.com.refreshdemo.selectImg.GlideNewEngine;

import static xb.com.refreshdemo.selectImg.AddStyleActivity.REQUEST_CODE_CAMERA_PAGE;
import static xb.com.refreshdemo.selectImg.AddStyleActivity.REQUEST_CODE_CHOOSE;


/**
 * [description about this class]
 *
 * @author hujiao
 * @version [zhanglv-android, 2018/03/26 19:40]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class AddStyleHelper implements Presenter {
    private AddStyleView addStyleView;
    private Activity activity;
    private AddStyleLogic addStyleLogic;

    public AddStyleHelper(AddStyleView addStyleView, Activity activity, AddStyleLogic addStyleLogic) {
        this.addStyleView = addStyleView;
        this.activity = activity;
        this.addStyleLogic = addStyleLogic;
    }

    private final String[] pickAvatar = {"从相册中选取", "拍照"};

    public void PickPicture(final List<String> photos, final int position) {//选择头像
        BottomDialog bottomDialog = new BottomDialog(activity, pickAvatar, new BottomDialog.onPositionClickListener() {
            @Override
            public void onClick(int position1) {
                switch (position1) {
                    case 0:
                        Matisse.from(activity)
                                .choose(MimeType.allOf())
                                .countable(true)
                                .maxSelectable(9)
                                .gridExpectedSize(300)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideNewEngine())
                                .forResult(REQUEST_CODE_CHOOSE);
                        break;
                    case 1:
                        FileUtils.deleteFile(Constant.IMAGNAME);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri outputFileUri;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            outputFileUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", FileUtils.getFileByPath(Constant.IMAGNAME));
                        } else {
                            outputFileUri = Uri.fromFile(FileUtils.getFileByPath(Constant.IMAGNAME));
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(intent, REQUEST_CODE_CAMERA_PAGE);
                        break;
                }
            }
        });
        bottomDialog.show();
    }


    //上传图片  文件服务器
    public void uploadIdCardAvatar(final String content, final List<String> photos) {
        if (photos != null && photos.size() > 0) {//有图片
            addStyleLogic.uploadImgs(photos, activity);
        } else {//没有图片 只有文字
            addStyleLogic.addPersonalShow(content, null, null);
        }
    }


    @Override
    public void onDestroy() {
        addStyleView = null;
    }
}
