package cn.trinea.android.demo.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.trinea.android.demo.BorderScrollViewDemo;
import cn.trinea.android.demo.BroadcastReceiverDemo;
import cn.trinea.android.demo.DownloadManagerDemo;
import cn.trinea.android.demo.DropDownListViewDemo;
import cn.trinea.android.demo.ImageCacheDemo;
import cn.trinea.android.demo.ImageSDCardCacheDemo;
import cn.trinea.android.demo.R;
import cn.trinea.android.demo.SearchViewDemo;
import cn.trinea.android.demo.ServiceDemo;
import cn.trinea.android.demo.SlideOnePageGalleryDemo;
import cn.trinea.android.demo.ViewPagerDemo;
import cn.trinea.android.demo.ViewPagerMulTiFragmentDemo;

/**
 * AppUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-9
 */
public class AppUtils {

    public static void initTrineaInfo(final Activity activity, Button trineaInfoTv, Class sourClass) {
        trineaInfoTv = (Button)activity.findViewById(R.id.trineaInfo);
        if (trineaInfoTv == null) {
            return;
        }

        final String[] result = getText(activity, sourClass);
        Spanned text = Html.fromHtml(result[1]);
        trineaInfoTv.setText(text);
        trineaInfoTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri web = Uri.parse(result[0]);
                Intent i = new Intent(Intent.ACTION_VIEW, web);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            }
        });
    }

    public static String[] getText(Activity activity, Class sourClass) {
        int prefixSrcId = R.string.description, contentSrcId;
        String url = null;
        if (sourClass == SearchViewDemo.class) {
            url = "http://www.trinea.cn/android/android-searchview%E4%BB%8B%E7%BB%8D%E5%8F%8A%E6%90%9C%E7%B4%A2%E6%8F%90%E7%A4%BA%E5%AE%9E%E7%8E%B0/";
            contentSrcId = R.string.desc_search_view;
        } else if (sourClass == ViewPagerMulTiFragmentDemo.class) {
            url = "http://www.trinea.cn/android/viewpager%E5%AE%9E%E7%8E%B0%E7%94%BB%E5%BB%8A%E4%B8%80%E5%B1%8F%E5%A4%9A%E4%B8%AAfragment%E6%95%88%E6%9E%9C/";
            contentSrcId = R.string.desc_view_pager_multi_page;
        } else if (sourClass == DownloadManagerDemo.class) {
            url = "http://www.trinea.cn/android/android系统下载管理downloadmanager功能介绍及使用示例";
            contentSrcId = R.string.desc_download_manager;
        } else if (sourClass == SlideOnePageGalleryDemo.class) {
            url = "http://www.trinea.cn/android/gallery滑动一页一个item效果/";
            contentSrcId = R.string.desc_slide_gallery;
        } else if (sourClass == ViewPagerDemo.class) {
            url = "http://www.cnblogs.com/trinea/archive/2012/11/23/2771273.html";
            contentSrcId = R.string.desc_view_pager;
        } else if (sourClass == ServiceDemo.class) {
            url = "http://www.cnblogs.com/trinea/archive/2012/11/08/2699856.html";
            contentSrcId = R.string.desc_service;
        } else if (sourClass == BroadcastReceiverDemo.class) {
            url = "http://www.cnblogs.com/trinea/archive/2012/11/09/2763182.html";
            contentSrcId = R.string.desc_broadcast_receiver;
        } else if (sourClass == BorderScrollViewDemo.class) {
            url = "http://www.trinea.cn/?p=445";
            contentSrcId = R.string.desc_border_scroll_view;
        } else if (sourClass == DropDownListViewDemo.class) {
            url = "http://www.trinea.cn/?p=523";
            contentSrcId = R.string.desc_drop_down_listview;
        } else if (sourClass == ImageCacheDemo.class) {
            url = "http://www.trinea.cn/?p=704";
            contentSrcId = R.string.desc_image_cache;
        } else if (sourClass == ImageSDCardCacheDemo.class) {
            url = "http://www.trinea.cn/?p=757";
            contentSrcId = R.string.desc_image_sdcard_cache;
        } else {
            prefixSrcId = R.string.profile;
            url = "http://www.trinea.cn";
            contentSrcId = R.string.desc_default;
        }
        String[] result = new String[] { url,
                getUrlInfo(activity.getString(prefixSrcId), url, activity.getString(contentSrcId)) };
        return result;
    }

    private static String getUrlInfo(String prefix, String url, String content) {
        return new StringBuilder().append(prefix).append("<a href=\"").append(url).append("\">").append(content)
                                  .append("</a>").toString();
    }
}
