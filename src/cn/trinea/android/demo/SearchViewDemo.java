package cn.trinea.android.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.TextView;

/**
 * SearchViewDemo
 * 
 * @author <a href="http://www.trinea.cn/android/android-searchview-and-search-tips-impl/" target="_blank">Trinea</a>
 *         2013-5-9
 */
public class SearchViewDemo extends BaseActivity {

    private SearchView               searchView;
    private MyHandler                handler;

    // schedule executor
    private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);
    private String                   currentSearchTip;

    private TextView                 searchInfo;

    private InputMethodManager       inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.search_view_demo);

        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        handler = new MyHandler();
        searchInfo = (TextView)findViewById(R.id.search_info);

        // set title style
        ActionBar bar = getActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        setTitle(" ");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customActionBarView = inflater.inflate(R.layout.search_view_demo_title, null);

        searchView = (SearchView)customActionBarView.findViewById(R.id.search_view);
        searchView.setVisibility(View.VISIBLE);
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(false);
        if (Build.VERSION.SDK_INT >= 14) {
            // when edittest is empty, don't show cancal button
            searchView.onActionViewExpanded();
        }
        searchView.setOnCloseListener(new OnCloseListener() {

            @Override
            public boolean onClose() {
                // to avoid click x button and the edittext hidden
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String query) {
                searchInfo.setText("search submit result");
                hideSoftInput();
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                if (newText != null && newText.length() > 0) {
                    currentSearchTip = newText;
                    showSearchTip(newText);
                }
                return true;
            }
        });
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        bar.setCustomView(customActionBarView, params);

        // show keyboard
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void showSearchTip(String newText) {
        // excute after 500ms, and when excute, judge current search tip and newText
        schedule(new SearchTipThread(newText), 500);
    }

    class SearchTipThread implements Runnable {

        String newText;

        public SearchTipThread(String newText) {
            this.newText = newText;
        }

        public void run() {
            // keep only one thread to load current search tip, u can get data from network here
            if (newText != null && newText.equals(currentSearchTip)) {
                handler.sendMessage(handler.obtainMessage(1, newText + " search tip"));
            }
        }
    }

    public ScheduledFuture<?> schedule(Runnable command, long delayTimeMills) {
        return scheduledExecutor.schedule(command, delayTimeMills, TimeUnit.MILLISECONDS);
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    searchInfo.setText((String)msg.obj);
                    break;
            }
        }
    }

    /**
     * hide soft input
     */
    private void hideSoftInput() {
        if (inputMethodManager != null) {
            View v = SearchViewDemo.this.getCurrentFocus();
            if (v == null) {
                return;
            }

            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            searchView.clearFocus();
        }
    }

}
