package cn.trinea.android.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * ViewPager with Fragment
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-11-14
 */
public class ViewPagerDemo extends BaseFragmentActivity {

    private static int TOTAL_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.view_pager_demo);

        ViewPager vp = (ViewPager)findViewById(R.id.view_pager);
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        List<String> titleList = new ArrayList<String>();
        for (int i = 0; i < TOTAL_COUNT; i++) {
            ViewPagerFragment viewPagerFragment1 = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("upImageId", 0);
            bundle.putString("text", "Page " + i);
            viewPagerFragment1.setArguments(bundle);
            titleList.add("title " + i);
            fragmentList.add(viewPagerFragment1);
        }

        vp.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentList, titleList));

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
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (titleList.size() > position) ? titleList.get(position) : "";
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
}
