package xb.com.refreshdemo.selectImg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ObjectUtils;

import java.util.ArrayList;

import xb.com.refreshdemo.R;

import static com.blankj.utilcode.util.ObjectUtils.isNotEmpty;

public class BottomDialog extends Dialog implements OnItemClickListener,
        View.OnClickListener {

    private Context context;
    // private Window window;
    // private WindowManager windowManager;
    private String[] itemList;
    private View outsideView;
    private ListView list_funitem;
    private TextView btn_cansel;
    public onPositionClickListener positionClickListener;

    private Animation animationShow;
    private Animation animationDismiss;

    private boolean hasTitle = false;

    private boolean hideCancle;

    public BottomDialog(Context context, String[] itemList,
                        onPositionClickListener positionClickListener) {
        super(context, R.style.transparentFrameWindowStyle);
        this.context = context;
        this.itemList = itemList;
        this.positionClickListener = positionClickListener;
        initSetting();
        setView();
    }

    public BottomDialog(Context context, String[] itemList,
                        onPositionClickListener positionClickListener, boolean hideCancle) {
        super(context, R.style.transparentFrameWindowStyle);
        this.context = context;
        this.itemList = itemList;
        this.positionClickListener = positionClickListener;
        this.hideCancle = hideCancle;
        initSetting();
        setView();
    }

    public BottomDialog(Context context, String[] itemList, int height,
                        onPositionClickListener positionClickListener) {
        super(context, R.style.transparentFrameWindowStyle);
        this.context = context;
        this.itemList = itemList;
        this.positionClickListener = positionClickListener;
        initSetting();
        setView();
        //setListViewHeight(context, height);
    }

    public BottomDialog(Context context, final ArrayList<FunItem> funItemList) {
        super(context, R.style.transparentFrameWindowStyle);
        this.context = context;
        if (funItemList != null && !funItemList.isEmpty()) {
            this.itemList = new String[funItemList.size()];
            for (int i = 0; i < funItemList.size(); i++) {
                if (funItemList.get(i) != null
                        && ObjectUtils.isNotEmpty(funItemList.get(i).getText())) {
                    itemList[i] = funItemList.get(i).getText();
                }
            }
        } else {
            this.itemList = new String[0];
        }
        initSetting();
        setView();
        this.positionClickListener = new onPositionClickListener() {

            @Override
            public void onClick(int position) {
                if (funItemList != null && position >= 0
                        && position < funItemList.size()) {
                    funItemList.get(position).getListener().onclick();
                }
            }
        };
    }

    /**
     * 设置listView的高度
     */
    public BottomDialog(Context context,
                        final ArrayList<FunItem> funItemList, int listHeigth) {
        super(context, R.style.transparentFrameWindowStyle);
        this.context = context;
        if (funItemList != null && !funItemList.isEmpty()) {
            this.itemList = new String[funItemList.size()];
            for (int i = 0; i < funItemList.size(); i++) {
                if (funItemList.get(i) != null
                        && ObjectUtils.isNotEmpty(funItemList.get(i).getText())) {
                    itemList[i] = funItemList.get(i).getText();
                }
            }
        } else {
            this.itemList = new String[0];
        }
        initSetting();
        setView();
        //setListViewHeight(context, listHeigth);
        this.positionClickListener = new onPositionClickListener() {

            @Override
            public void onClick(int position) {
                if (funItemList != null && position >= 0
                        && position < funItemList.size()) {
                    funItemList.get(position).getListener().onclick();
                }
            }
        };
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }

    private void initSetting() {

//        Window window = ((Activity) context).getWindow();
//        // window.setWindowAnimations(R.style.main_menu_animstyle);
//        WindowManager windowManager = ((Activity) context).getWindowManager();
//
//        DisplayMetrics dm = new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(dm);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.height = dm.heightPixels;
//        lp.width = dm.widthPixels;
//        window.setAttributes(lp);
//        this.onWindowAttributesChanged(lp);

        animationShow = AnimationUtils.loadAnimation(getContext(),
                R.anim.settingswindow_in_anim);
        animationDismiss = AnimationUtils.loadAnimation(getContext(),
                R.anim.settingswindow_out_anim);

        this.setCanceledOnTouchOutside(true);
    }

    public void setOnPositionClickListener(onPositionClickListener lis) {
        this.positionClickListener = lis;
    }

    public void setButtonMargin(int left, int right) {
        LinearLayout.LayoutParams paramTest = (LinearLayout.LayoutParams) btn_cansel.getLayoutParams();
        paramTest.leftMargin = left;
        paramTest.rightMargin = right;
        btn_cansel.setLayoutParams(paramTest);
    }

    private View baseView;

    private void setView() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        baseView = inflater.inflate(R.layout.public_bottom_dialog, null);
        setContentView(baseView);

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = display.getWidth(); //设置宽度
        lp.height = display.getHeight() - BarUtils.getStatusBarHeight() + ConvertUtils.dp2px(5);
        this.getWindow().setAttributes(lp);
        outsideView = findViewById(R.id.view);
        outsideView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return false;
            }
        });

        list_funitem = (ListView) findViewById(R.id.list_funitem);
        list_funitem.setOnItemClickListener(this);
        list_funitem.setAdapter(new itemListAdapter());
        btn_cansel = (TextView) findViewById(R.id.btn_cansel);
        if (hideCancle) {
            btn_cansel.setVisibility(View.GONE);
        } else {
            btn_cansel.setOnClickListener(this);
        }

    }

    public void setListViewHeight(Context context, int heigth) {
        if (heigth != 0) {
            LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(
                    heigth));
            lpm.setMargins(0, 0, 0, ConvertUtils.dp2px(5));
            list_funitem.setLayoutParams(lpm);
        }
    }

    @Override
    public void show() {
        super.show();
        baseView.startAnimation(animationShow);
    }

    ;

    public void show(String[] newItemList) {
        itemList = newItemList;
        show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        baseView.startAnimation(animationDismiss);
    }

    class itemListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.length;
        }

        @Override
        public Object getItem(int position) {
            return itemList[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(
                    R.layout.public_listitem_bottom_dialog, null);

            TextView button = (TextView) convertView
                    .findViewById(R.id.btn_gundan);

//			Log.e("TEST", "positionpositionposition " + position);
            button.setText(itemList[position]);
            if (TextUtils.isEmpty(itemList[position])) {
                convertView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            } else {
                convertView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
//			if (position == 0) {
//				if (hasTitle) {
//					button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//					button.setTextColor(context.getResources().getColor(
//							R.color.black));
//				} else {
//					button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//					button.setTextColor(context.getResources().getColor(
//							R.color.public_bottom_menu));
//				}
//				if (itemList.length == 1) {
//					button.setBackgroundResource(R.drawable.dialog_style_cancle);
//				} else {
//					button.setBackgroundResource(R.drawable.dialog_style_up);
//
//				}
//			} else if (position > 0 && position < itemList.length - 1) {
//				button.setBackgroundResource(R.drawable.dialog_style_middle);
//			} else {
//				button.setBackgroundResource(R.drawable.dialog_style_down);
//			}

            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long arg3) {
        position = position - list_funitem.getHeaderViewsCount();
        if (!hasTitle || position != 0) {
            dismiss();
        }
        positionClickListener.onClick(position);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cansel)
            dismiss();
    }

    public interface onPositionClickListener {

        void onClick(int position);

    }

}
