package cn.trinea.android.demo;

import java.util.Arrays;
import java.util.LinkedList;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * demo list list
 * 
 * @author Trinea 2012-6-17
 */
public class DemoList extends BaseActivity {

    public static final String    TAG      = "DemoList";

    private static final String[] mStrings = { "ImageSDCardCache Demo", "ImageCache Demo", "DropDownListView Demo",
            "onBottom onTop ScrollView Demo", "DownloadManager Demo", "SearchView Demo",
            "ViewPager Multi Fragment Demo", "Slide One Page Gallery Demo", "ViewPager Demo", "Service Demo",
            "BroadcastReceiver Demo"      };

    private static final int      total    = mStrings.length - 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.demo_list);

        ActionBar bar = getActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_HOME);

        LinkedList<String> mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

        ListView demoListView = (ListView)findViewById(R.id.simpleListView);
        demoListView.setAdapter(adapter);
        demoListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == total - 10) {
                    Intent intent = new Intent(DemoList.this, ImageSDCardCacheDemo.class);
                    startActivity(intent);
                } else if (position == total - 9) {
                    Intent intent = new Intent(DemoList.this, ImageCacheDemo.class);
                    startActivity(intent);
                } else if (position == total - 8) {
                    Intent intent = new Intent(DemoList.this, DropDownListViewDemo.class);
                    startActivity(intent);
                } else if (position == total - 7) {
                    Intent intent = new Intent(DemoList.this, BorderScrollViewDemo.class);
                    startActivity(intent);
                } else if (position == total - 6) {
                    Intent intent = new Intent(DemoList.this, DownloadManagerDemo.class);
                    startActivity(intent);
                } else if (position == total - 5) {
                    Intent intent = new Intent(DemoList.this, SearchViewDemo.class);
                    startActivity(intent);
                } else if (position == total - 4) {
                    Intent intent = new Intent(DemoList.this, ViewPagerMulTiFragmentDemo.class);
                    startActivity(intent);
                } else if (position == total - 3) {
                    Intent intent = new Intent(DemoList.this, SlideOnePageGalleryDemo.class);
                    startActivity(intent);
                } else if (position == total - 2) {
                    Intent intent = new Intent(DemoList.this, ViewPagerDemo.class);
                    startActivity(intent);
                } else if (position == total - 1) {
                    Intent intent = new Intent(DemoList.this, ServiceDemo.class);
                    startActivity(intent);
                } else if (position == total) {
                    Intent intent = new Intent(DemoList.this, BroadcastReceiverDemo.class);
                    startActivity(intent);
                }
            }
        });
        // startActivity(new Intent(getApplicationContext(), ImageCacheDemo.class));
        // startActivity(new Intent("android.intent.action.VIEW_DOWNLOADS"));
        // Intent i = new Intent(Intent.ACTION_VIEW);
        // i.setData(Uri.parse("http://122.193.23.191/down.myapp.com/android/48157/1176342/com.tencent.mm_261.apk?mkey=51cc3a56ba89d0cf&f=a821&p=.apk"));
        // startActivity(i);
        // final Context context = getApplicationContext();
        // new Thread() {
        //
        // public void run() {
        // DownloadManager.Request request = new Request(
        // Uri.parse("http://122.193.23.191/down.myapp.com/android/48157/1176342/com.tencent.mm_261.apk?mkey=51cc3a56ba89d0cf&f=a821&p=.apk"));
        // DownloadManager manager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        // manager.enqueue(request);
        //
        // Intent i = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(i);
        // }
        // }.start();
    }
}
