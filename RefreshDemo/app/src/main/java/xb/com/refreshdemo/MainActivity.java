package xb.com.refreshdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.hjl.library.ui.BaseActivity;

import xb.com.refreshdemo.net.PostRequestActivity;
import xb.com.refreshdemo.refresh.RefreshOneActivity;
import xb.com.refreshdemo.refresh.RefreshTwoActivity;

public class MainActivity extends BaseActivity {
    private Button btn1, btn2, btn3;


    @Override
    protected void onCreate() {
        super.onCreate();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
    }


    @Override
    public void initListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void initData() {
        initTitleBar(R.id.bar, 0, 0, R.string.app_name);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        switch (v.getId()) {
            case R.id.btn1:
                intent = new Intent(MainActivity.this, RefreshOneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, RefreshTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(MainActivity.this, PostRequestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
