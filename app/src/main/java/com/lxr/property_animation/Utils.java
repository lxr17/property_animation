package com.lxr.property_animation;

import android.content.Context;

/**
 * Created by lanxingren on 2019-08-06.
 */
public class Utils {

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕的高度
     *
     * @return
     */
    public static int getScreenHeidth(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
