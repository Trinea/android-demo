/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package cn.trinea.android.demo.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.trinea.android.common.util.ListUtils;

import com.jakewharton.salvage.RecyclingPagerAdapter;

/**
 * ImagePagerAdapter
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {

    private Context       context;
    private List<Integer> imageIdList;

    private int           size;

    public ImagePagerAdapter(Context context, List<Integer> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.size = ListUtils.getSize(imageIdList);
    }

    @Override
    public int getCount() {
        // Infinite loop
        return Integer.MAX_VALUE;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.imageView.setImageResource(imageIdList.get(position % size));
        return view;
    }

    private static class ViewHolder {

        ImageView imageView;
    }
}
