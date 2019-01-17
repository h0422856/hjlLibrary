package xb.com.refreshdemo.refresh;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xb.com.refreshdemo.R;

/**
 * [description about this class]
 *
 * @author hujiao
 * @version [RefreshDemo, 2019/01/07 18:31]
 * @copyright Copyright 2010 RD information technology Co.,ltd.. All Rights Reserved.
 */

public class RefreshAdapter extends BaseQuickAdapter<RefreshBean, BaseViewHolder> {

    public RefreshAdapter(int layoutResId, @Nullable List<RefreshBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RefreshBean item) {
        helper.setText(R.id.nickname, item.getRealName());
        Glide.with(mContext).load(item.getAvatarThubmnail()).into((ImageView) helper.getView(R.id.image));
    }
}
