package com.hjl.library.utils.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjl.library.R;


/**
 * Created by KevenTao on 2017/4/12.
 */

public class TitleBar extends RelativeLayout {

    public ImageView left;
    public ImageView right;
    public TextView titleTV;
    public TextView rightTitle;
    public LinearLayout action_bar_rl;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_titlebar, this);

        left = (ImageView) findViewById(R.id.tb_left);
        right = (ImageView) findViewById(R.id.tb_right);
        titleTV = (TextView) findViewById(R.id.tb_tv);
        rightTitle = (TextView) findViewById(R.id.tb_right_text);
        action_bar_rl = (LinearLayout) findViewById(R.id.action_bar_rl);
    }

    public void setTitle(int title) {
        titleTV.setText(getResources().getString(title));
    }

    public void setTitle(String title) {
        titleTV.setText(title);
    }

    public void setLeftBtn(int leftim) {
        left.setImageResource(leftim);
    }

    public void setRightBtn(int rightim) {
        right.setImageResource(rightim);
    }
}
