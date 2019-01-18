package xb.com.refreshdemo.refresh;

import android.app.Activity;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.roundview.RoundLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xb.com.refreshdemo.AppUtils;
import xb.com.refreshdemo.R;
import xb.com.refreshdemo.flowlayoutview.AutoFlowLayout;
import xb.com.refreshdemo.roundedimage.RoundedImageView;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/07 18:31]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class RefreshMultiAdapter extends BaseMultiItemQuickAdapter<MultiInfo, BaseViewHolder> {
    RoundedImageView avatar;
    AutoFlowLayout player_tag;
    private LayoutInflater mLayoutInflater1;
    private Activity activity;
    RoundLinearLayout age_champion_bg;
    private String age;
    private int userAge;
    private List<MultiInfo> mDatas;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RefreshMultiAdapter(List<MultiInfo> data, Activity activity) {
        super(data);
        addItemType(MultiInfo.TYPE_SERIES, R.layout.item_practice_repast);
        addItemType(MultiInfo.TYPE_PESALE, R.layout.question_answer_lay);
        this.activity = activity;
        this.mDatas = data;
        mLayoutInflater1 = LayoutInflater.from(activity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        age = sdf.format(date);
    }

    public void setDatas(List<MultiInfo> mData) {
        DiffUtil.DiffResult diffResult = DiffUtil
                .calculateDiff(new DiffCallBack(mDatas, mData), true);
        //按照DiffeResult定义好的逻辑，更新数据
        mDatas.clear();
        mDatas.addAll(mData);
        //使用DiffResult分发给apdate热更新
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public long getItemId(int position) {
        return mDatas.get(position).getRefreshBean().getRealName().hashCode();
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiInfo item) {
        switch (item.getItemType()) {
            case MultiInfo.TYPE_PESALE:
                avatar = helper.getView(R.id.avatar);
                AppUtils.showImage(activity, avatar, item.getRefreshBean().getAvatarThubmnail(),
                        item.getRefreshBean().getGender(), item.getRefreshBean().getVerifiedStatus());
                helper.setText(R.id.champion_name, item.getRefreshBean().getRealName());
                if (item.getRefreshBean().getVerifiedType() == 1) {//学神
                    helper.setText(R.id.champion_label, mContext.getResources().getString(R.string.champion_first));
                } else if (item.getRefreshBean().getVerifiedType() == 2) {
                    helper.setText(R.id.champion_label, mContext.getResources().getString(R.string.champion_second));
                } else {
                    helper.setText(R.id.champion_label, mContext.getResources().getString(R.string.champion_third));
                }
                helper.setVisible(R.id.champion_parent_tag_bg, false);
                helper.setVisible(R.id.champion_school_ll, true);
                helper.setVisible(R.id.champion_parent_school_ll, false);
                if (!TextUtils.isEmpty(item.getRefreshBean().getCompany())) {
                    helper.setVisible(R.id.school_tv, true);
                    helper.setText(R.id.school_tv, item.getRefreshBean().getCompany());
                } else {
                    helper.setVisible(R.id.school_tv, false);
                }
                if (!TextUtils.isEmpty(item.getRefreshBean().getRelativeLabel())) {
                    helper.setVisible(R.id.is_check_line, true);
                    helper.setVisible(R.id.is_check, true);
                } else {
                    helper.setVisible(R.id.is_check_line, false);
                    helper.setVisible(R.id.is_check, false);
                }
                player_tag = helper.getView(R.id.player_tag);
                player_tag.setMaxLines(1);
                if (!TextUtils.isEmpty(item.getRefreshBean().getSignature())) {
                    AppUtils.setSpecialtyChampion(activity, player_tag,
                            item.getRefreshBean().getFeaturedLabel() +
                                    item.getRefreshBean().getSignature(), mLayoutInflater1);
                } else {
                    AppUtils.setSpecialtyChampion(activity, player_tag,
                            item.getRefreshBean().getFeaturedLabel(), mLayoutInflater1);
                }
                age_champion_bg = helper.getView(R.id.age_champion_bg);
                if (item.getRefreshBean().getGender() == 1) {
                    age_champion_bg.getDelegate().setBackgroundColor(R.color.sex_man_user_bg);
                    age_champion_bg.getDelegate().setCornerRadius(2);
                    helper.setImageResource(R.id.age_champion_sex_img, R.drawable.sex_men_img);
                } else if (item.getRefreshBean().getGender() == 2) {
                    age_champion_bg.getDelegate().setBackgroundColor(R.color.sex_woman_user_bg);
                    age_champion_bg.getDelegate().setCornerRadius(2);
                    helper.setImageResource(R.id.age_champion_sex_img, R.drawable.sex_wamon_img);
                } else {
                    age_champion_bg.getDelegate().setBackgroundColor(R.color.sex_man_user_bg);
                    age_champion_bg.getDelegate().setCornerRadius(2);
                    helper.setImageResource(R.id.age_champion_sex_img, R.drawable.sex_men_img);
                }
                if (!TextUtils.isEmpty(item.getRefreshBean().getBirthday())) {
                    helper.setVisible(R.id.age_champion, true);
                    userAge = Integer.parseInt(age) - Integer.parseInt(item.getRefreshBean().getBirthday());
                    helper.setText(R.id.age_champion, userAge + "");
                } else {
                    helper.setVisible(R.id.age_champion, false);
                }
                String specialtyArray[] = item.getRefreshBean().getTags().split(",");
                String tag = "暂未选择专业";
                if (specialtyArray.length > 0) {
                    tag = "";
                    for (int i = 0; i < specialtyArray.length; i++) {
                        if (i == 0) {
                            tag = specialtyArray[i];
                        } else {
                            tag = tag + " " + specialtyArray[i];
                        }
                    }
                }
                helper.setText(R.id.elite_lawyer_introduction, tag);
                break;
            case MultiInfo.TYPE_SERIES:
                helper.setText(R.id.nickname, item.getRefreshBean().getRealName());
                AppUtils.showImage(activity, (ImageView) helper.getView(R.id.image), item.getRefreshBean().getAvatarThubmnail(),
                        item.getRefreshBean().getGender(), item.getRefreshBean().getVerifiedStatus());
                break;
        }
    }
}
