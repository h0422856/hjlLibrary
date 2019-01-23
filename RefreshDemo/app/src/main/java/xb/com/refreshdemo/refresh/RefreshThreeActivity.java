package xb.com.refreshdemo.refresh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjl.library.ui.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xb.com.refreshdemo.AppUtils;
import xb.com.refreshdemo.R;
import xb.com.refreshdemo.Url;
import xb.com.refreshdemo.refresh.bean.LawyerDetail;
import xb.com.refreshdemo.refresh.bean.LawyerInfo;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 15:49]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class RefreshThreeActivity extends BaseActivity {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RefreshAdapter refreshAdapter;

    private RefreshLogic refreshLogic;

    private List<LawyerDetail> lawyerDetails;

    int pageNum = 1;
    long pageTime = 0;
    private boolean isRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshone);
        initTitleBar(R.id.bar, 0, 0, R.string.app_name);
        refreshLayout = findViewById(R.id.refreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLogic = findLogic(new RefreshLogic(this));
        lawyerDetails = new ArrayList<>();
        pageNum = 1;
        pageTime = 0;
        isRefresh = true;
        refreshLogic.getLawyerList(pageNum, pageTime);
        refreshAdapter = new RefreshAdapter(R.layout.question_answer_lay, lawyerDetails, RefreshThreeActivity.this);
        recyclerView.setAdapter(refreshAdapter);
        refreshAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                showProgressDialog();
                pageNum = 1;
                pageTime = 0;
                isRefresh = true;
                refreshLogic.getLawyerList(pageNum, pageTime);
            }

            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                pageNum++;
                isRefresh = false;
                refreshLogic.getLawyerList(pageNum, pageTime);
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onSuccess(int requestId, Object response, String responseCode) {
        super.onSuccess(requestId, response, responseCode);
        if (requestId == R.id.get_lawyer_list_id) {
            dismissProgressDialog();
            LawyerInfo lawyerInfo = (LawyerInfo) response;
            pageTime = lawyerInfo.getPageTime();
            refreshAdapter.addData(lawyerInfo.getLawyerList());
            if (isRefresh) {
                refreshLayout.resetNoMoreData();//setNoMoreData(false);//恢复上拉状态
            }
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    protected void onFailure(int requestId, Object response, String responseCode, String errmsg) {
        super.onFailure(requestId, response, responseCode, errmsg);
        if (requestId == R.id.get_lawyer_list_id) {
            dismissProgressDialog();
            if (!isRefresh) {
                pageNum--;
            }
        }
    }

}

