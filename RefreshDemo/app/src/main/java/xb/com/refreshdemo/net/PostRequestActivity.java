package xb.com.refreshdemo.net;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjl.library.ui.BaseActivity;
import com.hjl.library.ui.base.Presenter;

import butterknife.BindView;
import butterknife.OnClick;
import xb.com.refreshdemo.R;
import xb.com.refreshdemo.bean.User;
import xb.com.refreshdemo.net.config.AccountManager;
import xb.com.refreshdemo.net.logic.ModuleALogic;
import xb.com.refreshdemo.refresh.RefreshOneActivity;
import xb.com.refreshdemo.refresh.RefreshTwoActivity;
import xb.com.refreshdemo.selectImg.AddStyleActivity;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 16:41]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class PostRequestActivity extends BaseActivity {

    private ModuleALogic moduleALogic;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.btn1_clear)
    Button btn1_clear;
    @BindView(R.id.btn1_post)
    Button btn1_post;
    @BindView(R.id.btn2_post)
    Button btn2_post;
    @BindView(R.id.btn3_get)
    Button btn3_get;
    @BindView(R.id.btn4_get)
    Button btn4_get;
    @BindView(R.id.btn5_get)
    Button btn5_get;
    @BindView(R.id.btn6_delete)
    Button btn6_delete;
    @BindView(R.id.btn7_patch)
    Button btn7_patch;
    @BindView(R.id.btn8_file_upload)
    Button btn8_file_upload;

    User user;

    @Override
    protected void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_post);
    }

    @Override
    public void initView() {
        moduleALogic = findLogic(new ModuleALogic(this));
        user = AccountManager.getInstance().getCurrentUser();
    }


    @Override
    public void initData() {
        initTitleBar(R.id.bar, 0, 0, R.string.app_name);
    }

    @OnClick(R.id.btn1_clear)
    public void btn1_clear(View view) {
        if (AccountManager.getInstance().getCurrentUser() != null) {
            AccountManager.getInstance().setCurrentUser(null);
            ToastUtils.showShort("清除成功");
        } else {
            ToastUtils.showShort("清除失败");
        }
    }

    @OnClick(R.id.btn1_post)
    public void btn1_post(View view) {
        showProgressDialog();
        moduleALogic.categoryList("18550935712", "123456");
    }


    @OnClick(R.id.btn2_post)
    public void btn2_post(View view) {
        User user = AccountManager.getInstance().getCurrentUser();
        showProgressDialog();
        moduleALogic.updatePassWord(
                user.getPhoneNumber(), user.getPassword(), "111111", user.getuId());
    }

    @OnClick(R.id.btn3_get)
    public void btn3_get(View view) {
//        showProgressDialog();
//                moduleALogic.updatePassWord(
//                        user.getPhoneNumber(), user.getPassword(), "111111", user.getuId());
    }

    @OnClick(R.id.btn4_get)
    public void btn4_get(View view) {
        showProgressDialog();
        moduleALogic.getCommonMessage(user.getuId());
    }

    @OnClick(R.id.btn5_get)
    public void btn5_get(View view) {
        showProgressDialog();
        moduleALogic.bannerList(user.getuId());
    }

    @OnClick(R.id.btn6_delete)
    public void btn6_delete(View view) {
        showProgressDialog();
        moduleALogic.deleteUnreadNum(user.getuId());
    }

    @OnClick(R.id.btn7_patch)
    public void btn7_patch(View view) {
        showProgressDialog();
        moduleALogic.patchUpdateAuth(user.getuId());
    }

    @OnClick(R.id.btn8_file_upload)
    public void btn8_file_upload(View view) {
        ActivityUtils.startActivity(AddStyleActivity.class);
    }

    @Override
    protected void onSuccess(int requestId, Object response, String responseCode) {
        super.onSuccess(requestId, response, responseCode);
        if (requestId == R.id.login_id) {
            dismissProgressDialog();
            text1.setText(response.toString());
            User user = (User) response;
            user.setPassword("123456");
            AccountManager.getInstance().setCurrentUser(user);
            ToastUtils.showShort("保存成功");
        } else if (requestId == R.id.update_password_id) {
            dismissProgressDialog();
            text1.setText("update_password_id===>Success" + response.toString());
        } else if (requestId == R.id.get_common_message_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Success" + response.toString());
        } else if (requestId == R.id.get_banner_list_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Success" + response.toString());
        } else if (requestId == R.id.delete_unread_num_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Success===>" + response.toString());
        } else if (requestId == R.id.patch_updateauth_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Success===>" + response.toString());
        }
    }

    @Override
    protected void onFailure(int requestId, Object response, String responseCode, String errmsg) {
        super.onFailure(requestId, response, responseCode, errmsg);
        if (requestId == R.id.login_id) {
            dismissProgressDialog();
            text1.setText(errmsg);
            ToastUtils.showShort("保存失败");
        } else if (requestId == R.id.update_password_id) {
            dismissProgressDialog();
            text1.setText("update_password_id===>Failure" + errmsg);
        } else if (requestId == R.id.get_common_message_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Failure" + errmsg);
        } else if (requestId == R.id.get_banner_list_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Failure" + errmsg);
        } else if (requestId == R.id.delete_unread_num_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Failure" + errmsg);
        } else if (requestId == R.id.patch_updateauth_id) {
            dismissProgressDialog();
            text1.setText("get_common_message_id===>Failure" + errmsg);
        }
    }

}
