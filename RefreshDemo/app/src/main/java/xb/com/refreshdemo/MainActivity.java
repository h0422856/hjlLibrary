package xb.com.refreshdemo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.hjl.library.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import xb.com.refreshdemo.net.PostRequestActivity;
import xb.com.refreshdemo.refresh.RefreshOneActivity;
import xb.com.refreshdemo.refresh.RefreshThreeActivity;
import xb.com.refreshdemo.refresh.RefreshTwoActivity;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        initTitleBar(R.id.bar, 0, 0, R.string.app_name);
    }

    @OnClick(R.id.btn1)
    public void btn1Onlick(View view) {
        ActivityUtils.startActivity(RefreshOneActivity.class);
    }

    @OnClick(R.id.btn1)
    public void btn2Onlick(View view) {
        ActivityUtils.startActivity(RefreshTwoActivity.class);
    }

    @OnClick(R.id.btn1)
    public void btn3Onlick(View view) {
        ActivityUtils.startActivity(PostRequestActivity.class);
    }

    @OnClick(R.id.btn4)
    public void btn4Onlick(View view) {
        ActivityUtils.startActivity(RefreshThreeActivity.class);
    }

}
