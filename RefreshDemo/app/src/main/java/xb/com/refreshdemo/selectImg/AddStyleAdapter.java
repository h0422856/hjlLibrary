package xb.com.refreshdemo.selectImg;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xb.com.refreshdemo.AppUtils;
import xb.com.refreshdemo.R;
import xb.com.refreshdemo.refresh.MultiInfo;
import xb.com.refreshdemo.roundedimage.RoundedImageView;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/18 11:58]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class AddStyleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    RoundedImageView photo_view;
    RelativeLayout photo_view_ll;

    public AddStyleAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return getData().size() < 6 ? getData().size() + 1 : getData().size();
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int draweeWidth = (ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(60)) / 3;
        photo_view = helper.getView(R.id.photo_view);
        photo_view_ll = helper.getView(R.id.photo_view_ll);
        photo_view_ll.setLayoutParams(new RelativeLayout.LayoutParams(draweeWidth, draweeWidth));
        if (getData().size() > 0 && helper.getLayoutPosition() < getData().size()) {
            helper.setVisible(R.id.delect_view, true);
            AppUtils.showImage(mContext, photo_view, item);
        } else {
            helper.setVisible(R.id.delect_view, false);
            photo_view.setImageResource(R.drawable.add_style_img);
        }
        helper.addOnClickListener(R.id.delect_view)
                .addOnClickListener(R.id.photo_view);
    }
}
