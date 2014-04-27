package cn.trinea.android.demo.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.trinea.android.demo.AutoScrollViewPagerDemo;
import cn.trinea.android.demo.AutoScrollViewPagerInnerDemo;
import cn.trinea.android.demo.BorderScrollViewDemo;
import cn.trinea.android.demo.BroadcastReceiverDemo;
import cn.trinea.android.demo.DownloadManagerDemo;
import cn.trinea.android.demo.DropDownListViewDemo;
import cn.trinea.android.demo.HttpCacheDemo;
import cn.trinea.android.demo.ImageCacheDemo;
import cn.trinea.android.demo.ImageSDCardCacheDemo;
import cn.trinea.android.demo.MainActivity;
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

    public static void init(Activity activity) {
        initTrineaInfo(activity);
        initActionBar(activity);
    }

    public static void urlOpen(Context context, String url) {
        Uri uriUrl = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, uriUrl);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    private static void initActionBar(final Activity activity) {
        if (activity == null) {
            return;
        }

        ActionBar bar = activity.getActionBar();
        if (activity instanceof MainActivity) {
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM
                    | ActionBar.DISPLAY_SHOW_HOME);
        } else {
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP
                    | ActionBar.DISPLAY_SHOW_CUSTOM);
        }
    }

    private static void initTrineaInfo(final Activity activity) {
        if (activity == null) {
            return;
        }

        Button trineaInfoTv = (Button)activity.findViewById(R.id.trinea_info);
        final String[] result = getText(activity);
        if (result == null) {
            return;
        }

        Spanned text = Html.fromHtml(result[1]);
        trineaInfoTv.setText(text);
        trineaInfoTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                urlOpen(activity, result[0]);
            }
        });
    }

    private static String[] getText(Activity activity) {
        if (activity == null) {
            return null;
        }

        int prefixSrcId = R.string.description, contentSrcId;
        String url = null;
        Class<?> sourClass = activity.getClass();
        if (sourClass == SearchViewDemo.class) {
            url = "http://www.trinea.cn/android/android-searchview-and-search-tips-impl/";
            contentSrcId = R.string.desc_search_view;
        } else if (sourClass == ViewPagerMulTiFragmentDemo.class) {
            url = "http://www.trinea.cn/android/viewpager-multi-fragment-effect/";
            contentSrcId = R.string.desc_view_pager_multi_page;
        } else if (sourClass == DownloadManagerDemo.class) {
            url = "http://www.trinea.cn/android/android-downloadmanager/";
            contentSrcId = R.string.desc_download_manager;
        } else if (sourClass == SlideOnePageGalleryDemo.class) {
            url = "http://www.trinea.cn/android/gallery-scroll-one-page/";
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
            url = "http://www.trinea.cn/android/on-bottom-load-more-scrollview/";
            contentSrcId = R.string.desc_border_scroll_view;
        } else if (sourClass == DropDownListViewDemo.class) {
            url = "http://www.trinea.cn/android/dropdown-to-refresh-and-bottom-load-more-listview/";
            contentSrcId = R.string.desc_drop_down_listview;
        } else if (sourClass == ImageCacheDemo.class) {
            url = "http://www.trinea.cn/android/android-imagecache/";
            contentSrcId = R.string.desc_image_cache;
        } else if (sourClass == ImageSDCardCacheDemo.class) {
            url = "http://www.trinea.cn/android/android-imagesdcardcache/";
            contentSrcId = R.string.desc_image_sdcard_cache;
        } else if (sourClass == HttpCacheDemo.class) {
            url = "http://www.trinea.cn/android/android-http-cache/";
            contentSrcId = R.string.desc_http_cache;
        } else if (sourClass == AutoScrollViewPagerInnerDemo.class) {
            url = "http://www.trinea.cn/android/auto-scroll-view-pager/";
            contentSrcId = R.string.desc_auto_scroll_view_pager_inner;
        } else if (sourClass == AutoScrollViewPagerDemo.class) {
            url = "http://www.trinea.cn/android/auto-scroll-view-pager/";
            contentSrcId = R.string.desc_auto_scroll_view_pager;
        } else {
            prefixSrcId = R.string.profile;
            url = "http://www.trinea.cn";
            contentSrcId = R.string.desc_default;
        }
        String[] result = new String[] {url,
                getUrlInfo(activity.getString(prefixSrcId), url, activity.getString(contentSrcId))};
        return result;
    }

    private static String getUrlInfo(String prefix, String url, String content) {
        return new StringBuilder().append(prefix).append("<a href=\"").append(url).append("\">").append(content)
                .append("</a>").toString();
    }
}
