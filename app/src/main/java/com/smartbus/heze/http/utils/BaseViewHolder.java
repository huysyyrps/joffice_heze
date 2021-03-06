package com.smartbus.heze.http.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smartbus.heze.http.views.RichTextView;

/**
 * Created by Administrator on 2019/4/12.
 * 通用adapter的viewholder
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews; //用来存储控件
    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    /**
     * 提供一个获取ViewHolder的方法
     */
    public static BaseViewHolder getRecyclerHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(context, itemView);
        return viewHolder;
    }

    /**
     * 获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 给TextView设置setText方法
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 给TextView设置setText方法
     */
    public BaseViewHolder setText1(int viewId, String text) {
        RichTextView tv = getView(viewId);
        tv.setHtml(text, 500);
        return this;
    }

    /**
     * 给ImageView设置setImageResource方法
     */
    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 给Radionbutton设置隐藏控件方法
     */
    public BaseViewHolder setGone(int viewId) {
        RadioButton rb = getView(viewId);
        rb.setVisibility(View.GONE);
        return this;
    }

    /**
     * 给TextView设置隐藏控件方法
     */
    public BaseViewHolder setGoneTextView(int viewId) {
        TextView tv = getView(viewId);
        tv.setVisibility(View.GONE);
        return this;
    }

    /**
     * 给LinnerLayout设置隐藏控件方法
     */
    public BaseViewHolder setGoneLinner(int viewId) {
        LinearLayout linerLayout = getView(viewId);
        linerLayout.setVisibility(View.GONE);
        return this;
    }

    /**
     * 给LinnerLayout设置显示控件方法
     */
    public BaseViewHolder setVisionLinner(int viewId) {
        LinearLayout linerLayout = getView(viewId);
        linerLayout.setVisibility(View.VISIBLE);
        return this;
    }


    /**
     * 给Radionbutton设置cgeck方法
     */
    public BaseViewHolder setCheck(int viewId) {
        RadioButton rb = getView(viewId);
        rb.setChecked(true);
        return this;
    }

    /**
     * 添加点击事件
     */
    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
