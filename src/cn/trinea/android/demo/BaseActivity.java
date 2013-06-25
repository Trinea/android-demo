package cn.trinea.android.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import cn.trinea.android.demo.utils.AppUtils;

/**
 * BaseActivity
 * 
 * @author Trinea 2013-6-1
 */
public class BaseActivity extends Activity {

    private Button trineaInfoTv;

    protected void onCreate(Bundle savedInstanceState, int layoutResID) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID);

        AppUtils.initTrineaInfo(this, trineaInfoTv, getClass());

        ActionBar bar = getActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return false;
    }
}
