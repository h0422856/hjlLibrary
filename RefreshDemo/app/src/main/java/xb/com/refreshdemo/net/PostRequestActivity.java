package xb.com.refreshdemo.net;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hjl.library.ui.BaseActivity;

import xb.com.refreshdemo.R;
import xb.com.refreshdemo.bean.User;
import xb.com.refreshdemo.net.config.AccountManager;
import xb.com.refreshdemo.net.logic.ModuleALogic;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 16:41]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class PostRequestActivity extends BaseActivity {

    private TextView text1;
    private Button btn1_clear, btn1_post, btn2_post,
            btn3_get, btn4_get, btn5_get, btn6_delete, btn7_patch;
    private ModuleALogic moduleALogic;

    @Override
    protected void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_post);
    }

    @Override
    public void initView() {
        text1 = findViewById(R.id.text1);
        btn1_clear = findViewById(R.id.btn1_clear);
        btn1_post = findViewById(R.id.btn1_post);
        btn2_post = findViewById(R.id.btn2_post);
        btn3_get = findViewById(R.id.btn3_get);
        btn4_get = findViewById(R.id.btn4_get);
        btn5_get = findViewById(R.id.btn5_get);
        btn6_delete = findViewById(R.id.btn6_delete);
        btn7_patch = findViewById(R.id.btn7_patch);
        moduleALogic = findLogic(new ModuleALogic(this));
    }

    @Override
    public void initListener() {
        btn1_clear.setOnClickListener(this);
        btn1_post.setOnClickListener(this);
        btn2_post.setOnClickListener(this);
        btn3_get.setOnClickListener(this);
        btn4_get.setOnClickListener(this);
        btn5_get.setOnClickListener(this);
        btn6_delete.setOnClickListener(this);
        btn7_patch.setOnClickListener(this);
    }

    @Override
    public void initData() {
        initTitleBar(R.id.bar, 0, 0, R.string.app_name);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        User user = AccountManager.getInstance().getCurrentUser();
        switch (v.getId()) {
            case R.id.btn1_clear:
                if (AccountManager.getInstance().getCurrentUser() != null) {
                    AccountManager.getInstance().setCurrentUser(null);
                    ToastUtils.showShort("清除成功");
                } else {
                    ToastUtils.showShort("清除失败");
                }
                break;
            case R.id.btn1_post:
                showProgressDialog();
                moduleALogic.categoryList("18550935712", "123456");
                break;
            case R.id.btn2_post:
                showProgressDialog();
                moduleALogic.updatePassWord(
                        user.getPhoneNumber(), user.getPassword(), "111111", user.getuId());
                break;
//            case R.id.btn3_get:
//                showProgressDialog();
//                moduleALogic.updatePassWord(
//                        user.getPhoneNumber(), user.getPassword(), "111111", user.getuId());
//                break;
            case R.id.btn4_get:
                showProgressDialog();
                moduleALogic.getCommonMessage(user.getuId());
            case R.id.btn5_get:
                showProgressDialog();
                moduleALogic.bannerList(user.getuId());
                break;
            case R.id.btn6_delete:
                showProgressDialog();
                moduleALogic.deleteUnreadNum(user.getuId());
                break;
            case R.id.btn7_patch:
                showProgressDialog();
                moduleALogic.patchUpdateAuth(user.getuId());
                break;
        }
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
