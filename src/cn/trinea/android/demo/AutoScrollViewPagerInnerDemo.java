package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import cn.trinea.android.common.util.ListUtils;

/**
 * AutoScrollViewPagerInnerDemo
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-22
 */
public class AutoScrollViewPagerInnerDemo extends BaseFragmentActivity {

    private static int         TOTAL_COUNT              = 3;
    public final static String ACTION_FRAGMENT_SELECTED = "cn.trinea.android.demo.ACTION_FRAGMENT_SELECTED";
    public static final String EXTRA_SELECTED_POSITION  = "selected_position";
    public static final String EXTRA_INDEX              = "index";
    public static final String EXTRA_TITLE              = "title";

    static final int           DEFAULT_INDEX            = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.view_pager_demo);

        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        List<String> titleList = new ArrayList<String>();
        for (int i = 0; i < TOTAL_COUNT; i++) {
            ImagePagerFragment viewPagerFragment1 = new ImagePagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(EXTRA_INDEX, i);
            viewPagerFragment1.setArguments(bundle);
            titleList.add(EXTRA_TITLE + i);
            fragmentList.add(viewPagerFragment1);
        }

        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentList, titleList));
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setCurrentItem(DEFAULT_INDEX);
    }

    /**
     * adapter
     * 
     * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-11-15
     */
    class myPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        private List<String>   titleList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
            super(fm);
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int index) {
            return ListUtils.getSize(fragmentList) == 0 ? null : fragmentList.get(index);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (titleList.size() > position) ? titleList.get(position) : "";
        }

        @Override
        public int getCount() {
            return ListUtils.getSize(fragmentList);
        }
    }

    /**
     * send broadcast that selected fragment have changed, to tell child ViewPager to start or stop auto scroll
     * 
     * @param position
     */
    private void sendSelectedBroadcast(int position) {
        Intent i = new Intent(ACTION_FRAGMENT_SELECTED);
        i.putExtra(EXTRA_SELECTED_POSITION, position);
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            sendSelectedBroadcast(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }
}
