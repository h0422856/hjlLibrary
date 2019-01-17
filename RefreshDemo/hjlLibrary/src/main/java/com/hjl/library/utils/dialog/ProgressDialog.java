package com.hjl.library.utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hjl.library.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * @author yanyi
 */
public class ProgressDialog extends Dialog {
    private Activity mContext;
    private TextView dialog_text;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    public ProgressDialog(Context context, String messageStr) {
        this(context, messageStr, true);
    }

    /**
     * 是否可以取消
     *
     * @param context
     * @param messageStr
     * @param cancelable
     */
    public ProgressDialog(Context context, String messageStr, final boolean cancelable) {
        super(context, R.style.dialog);
        mContext = (Activity) context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.lx_progressdialog, null);
        avLoadingIndicatorView = view.findViewById(R.id.avi);
        avLoadingIndicatorView.setIndicator("BallClipRotatePulse");
        dialog_text = view.findViewById(R.id.dialog_text);
        dialog_text.setText(messageStr);
        setContentView(view);
        setCancelable(cancelable);
    }

    @Override
    public void show() {
        if (mContext != null && !mContext.isFinishing()) {
            super.show();
        }
    }

    public void showMessage(String str) {
        dialog_text.setText(str);
    }
}
