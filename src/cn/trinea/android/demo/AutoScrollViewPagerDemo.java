package cn.trinea.android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * AutoScrollViewPagerDemo
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-22
 */
public class AutoScrollViewPagerDemo extends BaseActivity {

    private Button singleVieaPager;
    private Button innerVieaPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.auto_scroll_view_pager_demo);

        singleVieaPager = (Button)findViewById(R.id.auto_scroll_view_pager_single);
        innerVieaPager = (Button)findViewById(R.id.auto_scroll_view_pager_inner);

        singleVieaPager.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AutoScrollViewPagerSingleDemo.class));

            }
        });
        innerVieaPager.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AutoScrollViewPagerInnerDemo.class));

            }
        });
    }
}
