package cn.trinea.android.demo;

import java.util.Arrays;
import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * demo list
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-6-17
 */
public class MainActivity extends BaseActivity {

    public static final String    TAG      = "DemoList";

    private static final String[] mStrings = { "HttpCache Demo", "ImageSDCardCache Demo", "ImageCache Demo",
            "DropDownListView Demo", "onBottom onTop ScrollView Demo", "DownloadManager Demo", "SearchView Demo",
            "ViewPager Multi Fragment Demo", "Slide One Page Gallery Demo", "ViewPager Demo", "Service Demo",
            "BroadcastReceiver Demo"      };

    private static final int      total    = mStrings.length - 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.main);

        LinkedList<String> mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

        ListView demoListView = (ListView)findViewById(R.id.simple_list_view);
        demoListView.setAdapter(adapter);
        demoListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == total - 11) {
                    Intent intent = new Intent(MainActivity.this, HttpCacheDemo.class);
                    startActivity(intent);
                } else if (position == total - 10) {
                    Intent intent = new Intent(MainActivity.this, ImageSDCardCacheDemo.class);
                    startActivity(intent);
                } else if (position == total - 9) {
                    Intent intent = new Intent(MainActivity.this, ImageCacheDemo.class);
                    startActivity(intent);
                } else if (position == total - 8) {
                    Intent intent = new Intent(MainActivity.this, DropDownListViewDemo.class);
                    startActivity(intent);
                } else if (position == total - 7) {
                    Intent intent = new Intent(MainActivity.this, BorderScrollViewDemo.class);
                    startActivity(intent);
                } else if (position == total - 6) {
                    Intent intent = new Intent(MainActivity.this, DownloadManagerDemo.class);
                    startActivity(intent);
                } else if (position == total - 5) {
                    Intent intent = new Intent(MainActivity.this, SearchViewDemo.class);
                    startActivity(intent);
                } else if (position == total - 4) {
                    Intent intent = new Intent(MainActivity.this, ViewPagerMulTiFragmentDemo.class);
                    startActivity(intent);
                } else if (position == total - 3) {
                    Intent intent = new Intent(MainActivity.this, SlideOnePageGalleryDemo.class);
                    startActivity(intent);
                } else if (position == total - 2) {
                    Intent intent = new Intent(MainActivity.this, ViewPagerDemo.class);
                    startActivity(intent);
                } else if (position == total - 1) {
                    Intent intent = new Intent(MainActivity.this, ServiceDemo.class);
                    startActivity(intent);
                } else if (position == total) {
                    Intent intent = new Intent(MainActivity.this, BroadcastReceiverDemo.class);
                    startActivity(intent);
                }
            }
        });
    }
}
