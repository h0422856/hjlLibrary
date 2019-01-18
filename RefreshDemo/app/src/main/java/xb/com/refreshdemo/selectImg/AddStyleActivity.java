package xb.com.refreshdemo.selectImg;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjl.library.ui.BaseActivity;
import com.soundcloud.android.crop.Crop;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import xb.com.refreshdemo.R;
import xb.com.refreshdemo.bean.User;
import xb.com.refreshdemo.net.config.AccountManager;
import xb.com.refreshdemo.net.config.Constant;
import xb.com.refreshdemo.roundedimage.BitmapUtil;
import xb.com.refreshdemo.roundedimage.bean.FileData;
import xb.com.refreshdemo.roundedimage.bean.ImgBean;
import xb.com.refreshdemo.selectImg.addstyle.AddStyleHelper;
import xb.com.refreshdemo.selectImg.addstyle.AddStyleView;
import xb.com.refreshdemo.util.UriUtils;


/**
 * 发布风采
 */
public class AddStyleActivity extends BaseActivity implements AddStyleView {
    //选取照片
    public static final int REQUEST_CODE_CAMERA_PAGE = 8080;
    //添加个人风采图片选择回调
    public static final int REQUEST_CODE_CHOOSE = 308;
    @BindView(R.id.add_user_info_style_hide)
    EditText add_user_info_style_hide;
    @BindView(R.id.add_style_recyclerview)
    RecyclerView add_style_recyclerview;
    private AddStyleAdapter addStyleAdapter;
    private List<String> photos = new ArrayList<>();
    private int position1;
    private AddStyleHelper addStyleHelper;

    private AddStyleLogic addStyleLogic;

    @Override
    protected void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_add_style);
    }

    @Override
    public void initView() {
        initTitleBar(R.id.bar, R.drawable.back_icon, getResources().getString(R.string.add_user_style_submit),
                R.string.add_user_style_title);
        addStyleLogic = findLogic(new AddStyleLogic(this));
    }

    @Override
    public void initData() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        photos.add("");
        addStyleAdapter = new AddStyleAdapter(R.layout.activity_add_style_item, photos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AddStyleActivity.this, 3);
        add_style_recyclerview.setLayoutManager(gridLayoutManager);
        add_style_recyclerview.setItemAnimator(new DefaultItemAnimator());
        add_style_recyclerview.setAdapter(addStyleAdapter);
        addStyleHelper = new AddStyleHelper(this, AddStyleActivity.this, addStyleLogic);
        addStyleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                position1 = position;
                switch (view.getId()) {
                    case R.id.photo_view:
                        if (photos.size() > 9) {
                            ToastUtils.showShort("最多只能选择9张图片");
                        } else {
                            PermissionUtils.permission(PermissionConstants.CALENDAR,
                                    PermissionConstants.STORAGE).rationale(new PermissionUtils.OnRationaleListener() {
                                @Override
                                public void rationale(ShouldRequest shouldRequest) {

                                }
                            }).callback(new PermissionUtils.FullCallback() {
                                @Override
                                public void onGranted(List<String> permissionsGranted) {
                                    addStyleHelper.PickPicture(photos, position1);
                                    LogUtils.d(permissionsGranted);
                                }

                                @Override
                                public void onDenied(List<String> permissionsDeniedForever,
                                                     List<String> permissionsDenied) {
                                    if (!permissionsDeniedForever.isEmpty()) {
                                        ToastUtils.showShort("访问存储卡权限失败");
                                    }
                                    LogUtils.d(permissionsDeniedForever, permissionsDenied);
                                }
                            }).theme(new PermissionUtils.ThemeCallback() {
                                @Override
                                public void onActivityCreate(Activity activity) {
                                    ScreenUtils.setFullScreen(activity);
                                }
                            }).request();

//                            checkWriteStorageLocationPermission();
                        }
                        break;
                    case R.id.delect_view:
                        photos.remove(position);
                        addStyleAdapter.notifyItemRemoved(position);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_left:
                finish();
                break;
            case R.id.tb_right_text:
                photos.remove(0);
                if (TextUtils.isEmpty(add_user_info_style_hide.getText().toString()) && photos.size() == 0) {
                    ToastUtils.showShort("发布内容不能为空");
                } else {
                    showProgressDialog();
                    addStyleHelper.uploadIdCardAvatar(add_user_info_style_hide.getText().toString(), photos);
                }
                break;
        }
    }

    @Override
    protected void onSuccess(int requestId, Object response, String responseCode) {
        super.onSuccess(requestId, response, responseCode);
        if (requestId == R.id.filed_uploadimgs_id) {
            dismissProgressDialog();
            ToastUtils.showShort("上传成功");
            List<ImgBean> list = new ArrayList<>();
            for (int i = 0; i < photos.size(); i++) {
                if (photos.get(i) != null) {
                    ImgBean imgBean = BitmapUtil.compressWithLs(photos.get(i), AddStyleActivity.this);
                    assert imgBean != null;
                    list.add(imgBean);
                }
            }
            addStyleLogic.addPersonalShow(add_user_info_style_hide.getText().toString(), (ArrayList<FileData>) response, list);
        } else if (requestId == R.id.post_add_personal_show_id) {
            dismissProgressDialog();
            ToastUtils.showShort("发布成功");
        }
    }

    @Override
    protected void onFailure(int requestId, Object response, String responseCode, String errmsg) {
        super.onFailure(requestId, response, responseCode, errmsg);
        if (requestId == R.id.filed_uploadimgs_id) {
            dismissProgressDialog();
            ToastUtils.showShort("上传失败");
        } else if (requestId == R.id.post_add_personal_show_id) {
            dismissProgressDialog();
            ToastUtils.showShort("发布失败");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA_PAGE && resultCode == RESULT_OK) {
            beginCrop(Uri.fromFile(new File(Constant.IMAGNAME)));
        } else if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            handleCrop(data);
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uriList = Matisse.obtainResult(data);
            if (uriList.size() == 1) {
                photos.add(getRealFilePath(AddStyleActivity.this, uriList.get(0)));
            } else {
                List<String> list = new ArrayList<>();
                for (Uri uri : uriList) {
                    list.add(getRealFilePath(AddStyleActivity.this, uri));
                }
                photos.addAll(list);
            }
            addStyleAdapter.notifyDataSetChanged();
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(FileUtils.getFileByPath(Constant.IMAGEPATH + System.currentTimeMillis() + ".png"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(Intent result) {
        Uri uri = Crop.getOutput(result);
        String newUserAvatarPath = UriUtils.getPath(this, uri);
        photos.add(newUserAvatarPath);
        addStyleAdapter.notifyDataSetChanged();
    }

    //
    public String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    public void addPersonalShow() {
        ToastUtils.showShort("发布成功");
        Intent intent = new Intent();
        setResult(102, intent);
        finish();
    }


    @Override
    public void errorRequest(String msg) {
        dismissProgressDialog();
        ToastUtils.showShort(msg);
    }
}
