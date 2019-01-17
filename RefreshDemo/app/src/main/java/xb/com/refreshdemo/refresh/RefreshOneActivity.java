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

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/16 15:49]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class RefreshOneActivity extends BaseActivity{
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RefreshMultiAdapter refreshAdapter;


    private ChampionListInfo championListInfo;
    private ChampionListInfo championListInfo1;
    private List<MultiInfo> multiInfos = new ArrayList<>();
    private List<MultiInfo> multiInfos1 = new ArrayList<>();

    MultiInfo multiInfo = new MultiInfo();

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
        multiInfos.clear();
        championListInfo = GsonUtils.fromJson(Url.gsonStr, ChampionListInfo.class);
        championListInfo1 = GsonUtils.fromJson(Url.str1, ChampionListInfo.class);
        for (int i = 0; i < championListInfo.getLawyerList().size(); i++) {
            MultiInfo multiInfo = new MultiInfo();
            multiInfo.setItemType(MultiInfo.TYPE_PESALE);
            multiInfo.setRefreshBean(championListInfo.getLawyerList().get(i));
            multiInfos.add(multiInfo);
        }
        for (int i = 0; i < championListInfo1.getLawyerList().size(); i++) {
            MultiInfo multiInfo = new MultiInfo();
            multiInfo.setItemType(MultiInfo.TYPE_SERIES);
            multiInfo.setRefreshBean(championListInfo1.getLawyerList().get(i));
            multiInfos.add(multiInfo);
        }
        Collections.shuffle(multiInfos);
        assert championListInfo != null;
        refreshAdapter = new RefreshMultiAdapter(multiInfos, RefreshOneActivity.this);
        recyclerView.setAdapter(refreshAdapter);
        refreshAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        refreshLayout.resetNoMoreData();//setNoMoreData(false);//恢复上拉状态
                        multiInfos1.clear();
                        multiInfos1 = AppUtils.depCopy(multiInfos);
                        multiInfos1.get(0).getRefreshBean().setRealName("哒哒哒哒哒哒");
                        refreshAdapter.setDatas(multiInfos1);

                    }
                }, 110);
            }

            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        refreshAdapter.addData(GsonUtils.fromJson(UrlConstant.gsonStr, ChampionListInfo.class).getLawyerList());
//                        refreshLayout.finishLoadMore();

                    }
                }, 100);
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }


}

