package xb.com.refreshdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.hjl.library.GlideApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import xb.com.refreshdemo.flowlayoutview.AutoFlowLayout;

/**
 * Created by KevenTao on 2017/4/17.
 */

public class AppUtils {

    public static void showImage(Context context, ImageView imageView, String url, int gender, int verifiedStatus) {
        int drawableResource = R.drawable.avatar_user_male;
        if (gender == 1) {
            if (verifiedStatus == 2) {
                drawableResource = R.drawable.avatar_lawyer_male;
            } else {
                drawableResource = R.drawable.avatar_user_male;
            }
        } else if (gender == 2) {
            if (verifiedStatus == 2) {
                drawableResource = R.drawable.avatar_lawyer_female;
            } else {
                drawableResource = R.drawable.avatar_user_female;
            }
        } else {
            drawableResource = R.drawable.avatar_user_male;
        }
        GlideApp.with(context).load(url).
                placeholder(drawableResource).
                skipMemoryCache(false).
                dontAnimate().
                into(imageView);
    }

    public static void showImage(Context context, ImageView imageView, String url) {
        int drawableResource = R.drawable.pic_loading;
        GlideApp.with(context).load(url).
                placeholder(drawableResource).
                skipMemoryCache(false).
                dontAnimate().
                into(imageView);
    }

    /**
     * @param flSpecialty
     * @param specialty
     */
    public static void setSpecialtyChampion(Activity activity, AutoFlowLayout flSpecialty, String specialty,
                                            LayoutInflater mLayoutInflater) {
        if (TextUtils.isEmpty(specialty)) {
            Random random = new Random(2);
            random.nextInt(2);
            if (random.nextInt(2) == 1) {
                specialty = "名校学霸";
            } else {
                specialty = "考试达人";
            }
        }
        flSpecialty.removeAllViews();
        String specialtyArray[] = specialty.split(",");
        if (specialtyArray.length > 0) {
            for (int i = 0; i < specialtyArray.length; i++) {
                View itemView = mLayoutInflater.inflate(R.layout.sub_item, null);
                TextView tvAttrTag = (TextView) itemView.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setBackgroundColor(activity.getResources().getColor(R.color.user_personal_tag_bg));
                tvAttrTag.setTextSize(12);
                tvAttrTag.setTextColor(activity.getResources().getColor(R.color.white));
                tvAttrTag.setText(specialtyArray[i]);
                flSpecialty.addView(itemView);
            }
        } else {
            View itemView = mLayoutInflater.inflate(R.layout.sub_item, null);
            TextView tvAttrTag = (TextView) itemView.findViewById(R.id.tv_attr_tag);
            tvAttrTag.setBackgroundColor(activity.getResources().getColor(R.color.user_personal_tag_bg));
            tvAttrTag.setTextSize(12);
            tvAttrTag.setTextColor(activity.getResources().getColor(R.color.white));
            tvAttrTag.setText(specialty);
            flSpecialty.addView(itemView);
        }

    }

    /***
     * 方法一对集合进行深拷贝 注意需要对泛型类进行序列化(实现Serializable)
     *
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            out.close();
            inStream.close();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
