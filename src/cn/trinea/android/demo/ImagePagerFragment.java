package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.trinea.android.demo.adapter.ImagePagerAdapter;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * ImagePagerFragment
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerFragment extends Fragment {

    private Context               context;
    private AutoScrollViewPager   viewPager;
    private CirclePageIndicator   indicator;

    private int                   index;
    private ScrollControlReceiver scrollControlReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();

        View v = inflater.inflate(R.layout.auto_scroll_view_pager_inner_fragment_demo, container, false);
        viewPager = (AutoScrollViewPager)v.findViewById(R.id.view_pager);
        indicator = (CirclePageIndicator)v.findViewById(R.id.indicator);

        List<Integer> imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.banner1);
        imageIdList.add(R.drawable.banner2);
        imageIdList.add(R.drawable.banner3);
        imageIdList.add(R.drawable.banner4);
        viewPager.setAdapter(new ImagePagerAdapter(context, imageIdList));
        indicator.setViewPager(viewPager);

        viewPager.setInterval(2000);
        viewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_TO_PARENT);

        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(AutoScrollViewPagerInnerDemo.EXTRA_INDEX);
            if (index == AutoScrollViewPagerInnerDemo.DEFAULT_INDEX) {
                viewPager.startAutoScroll();
            }
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        // receive broadcast that selected fragment have changed, to start or stop auto scroll ViewPager
        scrollControlReceiver = new ScrollControlReceiver();
        LocalBroadcastManager.getInstance(context).registerReceiver(scrollControlReceiver,
                new IntentFilter(AutoScrollViewPagerInnerDemo.ACTION_FRAGMENT_SELECTED));
    }

    @Override
    public void onStop() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(scrollControlReceiver);
        super.onStop();
    }

    /**
     * ScrollControlReceiver
     * 
     * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-4-26
     */
    private class ScrollControlReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int selectedPosition = intent.getIntExtra(AutoScrollViewPagerInnerDemo.EXTRA_SELECTED_POSITION, 0);
                if (index == selectedPosition) {
                    viewPager.startAutoScroll();
                } else {
                    viewPager.stopAutoScroll();
                }
            }
        }
    }
}
