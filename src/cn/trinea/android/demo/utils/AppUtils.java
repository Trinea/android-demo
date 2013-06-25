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
import cn.trinea.android.demo.R;
import cn.trinea.android.demo.SearchViewDemo;
import cn.trinea.android.demo.ServiceDemo;
import cn.trinea.android.demo.SlideOnePageGalleryDemo;
import cn.trinea.android.demo.ViewPagerDemo;
import cn.trinea.android.demo.ViewPagerMulTiFragmentDemo;

/**
 * AppUtils
 * 
 * @author Trinea 2013-5-9
 */
public class AppUtils {

    public static String PROFILE = "个人主页: ";
    public static String ABOUT   = "相关介绍见: ";

    public static void initTrineaInfo(final Activity activity, Button trineaInfoTv, Class sourClass) {
        trineaInfoTv = (Button)activity.findViewById(R.id.trineaInfo);
        final String[] result = getText(sourClass);
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

    public static String[] getText(Class sourClass) {
        String prefix = null, url = null, name = null;
        if (sourClass == SearchViewDemo.class) {
            url = "http://www.trinea.cn/android/android-searchview%E4%BB%8B%E7%BB%8D%E5%8F%8A%E6%90%9C%E7%B4%A2%E6%8F%90%E7%A4%BA%E5%AE%9E%E7%8E%B0/";
            name = "SearchView介绍及搜索提示实现";
        } else if (sourClass == ViewPagerMulTiFragmentDemo.class) {
            url = "http://www.trinea.cn/android/viewpager%E5%AE%9E%E7%8E%B0%E7%94%BB%E5%BB%8A%E4%B8%80%E5%B1%8F%E5%A4%9A%E4%B8%AAfragment%E6%95%88%E6%9E%9C/";
            name = "viewpager实现画廊效果";
        } else if (sourClass == DownloadManagerDemo.class) {
            url = "http://www.trinea.cn/android/android系统下载管理downloadmanager功能介绍及使用示例";
            name = "系统下载管理使用";
        } else if (sourClass == SlideOnePageGalleryDemo.class) {
            url = "http://www.trinea.cn/android/gallery滑动一页一个item效果/";
            name = "Gallery滑动一页(一个Item)效果";
        } else if (sourClass == ViewPagerDemo.class) {
            url = "http://www.cnblogs.com/trinea/archive/2012/11/23/2771273.html";
            name = "ViewPager、Fragment使用";
        } else if (sourClass == ServiceDemo.class) {
            url = "http://www.cnblogs.com/trinea/archive/2012/11/08/2699856.html";
            name = "Service介绍";
        } else if (sourClass == BroadcastReceiverDemo.class) {
            url = "http://www.cnblogs.com/trinea/archive/2012/11/09/2763182.html";
            name = "BroadcastReceiver介绍";
        } else if (sourClass == BorderScrollViewDemo.class) {
            url = "http://www.trinea.cn/?p=445";
            name = "BorderScrollViewDemo介绍";
        } else if (sourClass == DropDownListViewDemo.class) {
            url = "http://www.trinea.cn/?p=523";
            name = "下拉刷新及底部加载更多Listview";
        } else {
            prefix = "个人主页:";
            url = "http://www.trinea.cn";
            name = " Trinea";
        }
        String[] result = new String[] { url, getUrlInfo(prefix, url, name) };
        return result;
    }

    private static String getUrlInfo(String prefix, String url, String name) {
        return new StringBuilder().append(prefix == null ? ABOUT : prefix).append("<a href=\"").append(url)
                                  .append("\">").append(name).append("</a>").toString();
    }
}
