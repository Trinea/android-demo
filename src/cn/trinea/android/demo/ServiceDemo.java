package cn.trinea.android.demo;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.trinea.android.common.util.ToastUtils;
import cn.trinea.android.demo.MyService.MyBinder;

/**
 * ServiceDemo, incluse start service, bind service and intent service
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-9
 */
public class ServiceDemo extends BaseActivity {

    private MyService         myService;
    private Intent            myServiceIntent;
    private Intent            myAIDLServiceIntent;
    private Intent            myIntentServiceIntent;
    private Intent            myIntentService2Intent;

    private Button            startServiceBtn;
    private Button            stopServiceBtn;
    private Button            startIntentServiceBtn;

    private Button            boundServiceBtn;
    private Button            operateBoundServiceBtn;
    private Button            getBoundServiceProBtn;
    private Button            unboundServiceBtn;

    private Button            startAIDLServiceBtn;
    private Button            stopAIDLServiceBtn;

    private ServiceConnection con = new ServiceConnection() {

                                      /**
                                       * Called when a connection to the Service has been lost
                                       */
                                      @Override
                                      public void onServiceDisconnected(ComponentName name) {
                                          Toast.makeText(getApplicationContext(), "Service disconnect",
                                                         Toast.LENGTH_SHORT).show();
                                      }

                                      /**
                                       * Called when a connection to the Service has been established,
                                       */
                                      @Override
                                      public void onServiceConnected(ComponentName name, IBinder service) {
                                          myService = ((MyBinder)service).getService();
                                          Toast.makeText(getApplicationContext(), "Service Connect", Toast.LENGTH_SHORT).show();
                                      }
                                  };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.service_demo);

        ActionBar bar = getActionBar();
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);

        myServiceIntent = new Intent(this, MyService.class);
        myAIDLServiceIntent = new Intent(ServiceDemo.this, MyAIDLService.class);
        myIntentServiceIntent = new Intent(ServiceDemo.this, MyIntentService.class);
        myIntentService2Intent = new Intent(ServiceDemo.this, MyIntentService.class);

        startServiceBtn = (Button)findViewById(R.id.startGeneralService);
        startServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myServiceIntent);
            }
        });

        stopServiceBtn = (Button)findViewById(R.id.stopGeneralService);
        stopServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                stopService(myServiceIntent);
            }
        });

        startIntentServiceBtn = (Button)findViewById(R.id.startIntentService);
        startIntentServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myIntentServiceIntent);
                startService(myIntentService2Intent);
            }
        });

        boundServiceBtn = (Button)findViewById(R.id.boundService);
        boundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                bindService(myServiceIntent, con, Context.BIND_AUTO_CREATE);
            }
        });

        operateBoundServiceBtn = (Button)findViewById(R.id.operateBoundService);
        operateBoundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    ToastUtils.show(context, R.string.operate_value_success,
                                    Integer.toString(myService.increaseCount()));
                } else {
                    ToastUtils.show(context, R.string.bind_service_tip, Toast.LENGTH_SHORT);
                }
            }
        });

        getBoundServiceProBtn = (Button)findViewById(R.id.getBoundServicePro);
        getBoundServiceProBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    ToastUtils.show(context, R.string.operate_value_tip, Integer.toString(myService.increaseCount()));
                } else {
                    ToastUtils.show(context, R.string.bind_service_tip, Toast.LENGTH_SHORT);
                }
            }
        });

        unboundServiceBtn = (Button)findViewById(R.id.unboundService);
        unboundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    unbindService(con);
                    myService = null;
                }
            }
        });

        startAIDLServiceBtn = (Button)findViewById(R.id.startAIDLService);
        startAIDLServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myAIDLServiceIntent);
            }
        });

        stopAIDLServiceBtn = (Button)findViewById(R.id.stopAIDLService);
        stopAIDLServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                stopService(myAIDLServiceIntent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
