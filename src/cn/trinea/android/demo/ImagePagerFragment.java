package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.trinea.android.demo.adapter.ImagePagerAdapter;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * ImagePagerFragment
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class ImagePagerFragment extends Fragment {

    private AutoScrollViewPager viewPager;
    private TextView            indexText;

    private List<Integer>       imageIdList;

    public ImagePagerFragment(){
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auto_scroll_view_pager_inner_fragment_demo, container, false);

        viewPager = (AutoScrollViewPager)v.findViewById(R.id.view_pager);
        indexText = (TextView)v.findViewById(R.id.view_pager_index);

        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.banner1);
        imageIdList.add(R.drawable.banner2);
        imageIdList.add(R.drawable.banner3);
        imageIdList.add(R.drawable.banner4);
        viewPager.setAdapter(new ImagePagerAdapter(getActivity().getApplicationContext(), imageIdList));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        indexText.setText(new StringBuilder().append("1/").append(imageIdList.size()));

        Bundle bundle = getArguments();
        int index = 0;
        if (bundle != null) {
            index = bundle.getInt("index");
        }

        if (index == 1) {
            viewPager.setInterval(2000);
            viewPager.startAutoScroll();
            viewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_CYCLE);
        }
        return v;
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Log.i("image", "touch:" + position);
            indexText.setText(new StringBuilder().append(position + 1).append("/").append(imageIdList.size()));
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}
