package cn.trinea.android.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;
import android.widget.Toast;
import cn.trinea.android.common.view.BorderScrollView;
import cn.trinea.android.common.view.BorderScrollView.OnBorderListener;

/**
 * BorderScrollViewDemo
 * 
 * @author <a href="http://www.trinea.cn/android/on-bottom-load-more-scrollview/" target="_blank">Trinea</a> 2013-5-27
 */
public class BorderScrollViewDemo extends BaseActivity {

    private BorderScrollView borderScrollView;
    private TextView         textView1;
    private TextView         textView2;

    private Context          context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.border_scroll_view_demo);

        context = getApplicationContext();

        borderScrollView = (BorderScrollView)findViewById(R.id.scroll_view);
        borderScrollView.setOnBorderListener(new OnBorderListener() {

            @Override
            public void onTop() {
                // may be done multi times, u should control it
                Toast.makeText(context, "has reached top", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBottom() {
                // may be done multi times, u should control it
                Toast.makeText(context, "has reached bottom", Toast.LENGTH_SHORT).show();
            }
        });
        textView1 = (TextView)findViewById(R.id.text1);
        textView2 = (TextView)findViewById(R.id.text2);

        Display display = getWindowManager().getDefaultDisplay();
        textView1.setHeight(display.getHeight() / 2);
        textView2.setHeight(display.getHeight() / 2);
    }
}
