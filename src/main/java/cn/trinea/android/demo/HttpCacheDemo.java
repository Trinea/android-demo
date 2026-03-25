package cn.trinea.android.demo;

import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.trinea.android.common.entity.HttpResponse;
import cn.trinea.android.common.service.HttpCache;
import cn.trinea.android.common.service.HttpCache.HttpCacheListener;
import cn.trinea.android.common.util.CacheManager;
import cn.trinea.android.common.util.StringUtils;

/**
 * HttpCacheDemo
 * 
 * @author <a href="http://www.trinea.cn/android/android-http-cache/" target="_blank">Trinea</a> 2013-11-18
 */
public class HttpCacheDemo extends BaseActivity {

    public static final String TAG_CACHE = "http_cache";

    private EditText           httpUrlET;
    private Button             httpGetBT;
    private TextView           httpGetContentTV;
    private TextView           httpCacheInfoTV;

    private HttpCache          httpCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.http_cache_demo);

        // get the singleton instance of HttpCache
        httpCache = CacheManager.getHttpCache(context);
        // or create a new HttpCache, like this:
        // httpCache = new HttpCache(context);
        httpUrlET = (EditText)findViewById(R.id.http_cache_url);
        httpGetBT = (Button)findViewById(R.id.http_cache_get);
        httpGetContentTV = (TextView)findViewById(R.id.http_cache_content);
        httpCacheInfoTV = (TextView)findViewById(R.id.http_cache_info);
        httpGetBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = httpUrlET.getText().toString();
                url = StringUtils.isEmpty(url) ? httpUrlET.getHint().toString() : url;
                httpCache.httpGet(url, new HttpCacheListener() {

                    protected void onPreGet() {
                        httpCacheInfoTV.setText("");
                        httpGetContentTV.setText("wating…");
                        httpGetBT.setEnabled(false);
                    }

                    protected void onPostGet(HttpResponse httpResponse, boolean isInCache) {
                        if (httpResponse != null) {
                            StringBuilder sb = new StringBuilder(256);
                            sb.append("is in cache: ").append(isInCache).append("\r\n");
                            if (isInCache) {
                                sb.append("expires: ").append(new Date(httpResponse.getExpiredTime()).toGMTString())
                                        .append("\r\n");
                            }
                            httpCacheInfoTV.setText(sb.toString());
                            httpGetContentTV.setText(httpResponse.getResponseBody());
                        } else {
                            httpCacheInfoTV.setText("");
                            httpGetContentTV.setText("response is null.");
                        }
                        httpGetBT.setEnabled(true);
                    }
                });
            }
        });
    }
}
