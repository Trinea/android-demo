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

    private static final String[] mStrings = {"AutoScrollViewPager Demo", "HttpCache Demo", "ImageCache Demo",
            "ImageSDCardCache Demo", "DropDownListView Demo", "onBottom onTop ScrollView Demo", "DownloadManager Demo",
            "SearchView Demo", "ViewPager Multi Fragment Demo", "Slide One Page Gallery Demo", "ViewPager Demo",
            "Service Demo", "BroadcastReceiver Demo"};

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
                if (position == total - 12) {
                    startActivity(AutoScrollViewPagerDemo.class);
                } else if (position == total - 11) {
                    startActivity(HttpCacheDemo.class);
                } else if (position == total - 10) {
                    startActivity(ImageCacheDemo.class);
                } else if (position == total - 9) {
                    startActivity(ImageSDCardCacheDemo.class);
                } else if (position == total - 8) {
                    startActivity(DropDownListViewDemo.class);
                } else if (position == total - 7) {
                    startActivity(BorderScrollViewDemo.class);
                } else if (position == total - 6) {
                    startActivity(DownloadManagerDemo.class);
                } else if (position == total - 5) {
                    startActivity(SearchViewDemo.class);
                } else if (position == total - 4) {
                    startActivity(ViewPagerMulTiFragmentDemo.class);
                } else if (position == total - 3) {
                    startActivity(SlideOnePageGalleryDemo.class);
                } else if (position == total - 2) {
                    startActivity(ViewPagerDemo.class);
                } else if (position == total - 1) {
                    startActivity(ServiceDemo.class);
                } else if (position == total) {
                    startActivity(BroadcastReceiverDemo.class);
                }
            }
        });
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
