package cn.trinea.android.demo;

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
                                          Toast.makeText(context, "Service disconnect", Toast.LENGTH_SHORT).show();
                                      }

                                      /**
                                       * Called when a connection to the Service has been established,
                                       */
                                      @Override
                                      public void onServiceConnected(ComponentName name, IBinder service) {
                                          myService = ((MyBinder)service).getService();
                                          Toast.makeText(context, "Service Connect", Toast.LENGTH_SHORT).show();
                                      }
                                  };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.service_demo);

        myServiceIntent = new Intent(this, MyService.class);
        myAIDLServiceIntent = new Intent(ServiceDemo.this, MyAIDLService.class);
        myIntentServiceIntent = new Intent(ServiceDemo.this, MyIntentService.class);
        myIntentService2Intent = new Intent(ServiceDemo.this, MyIntentService.class);

        startServiceBtn = (Button)findViewById(R.id.start_general_service);
        startServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myServiceIntent);
            }
        });

        stopServiceBtn = (Button)findViewById(R.id.stop_general_service);
        stopServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                stopService(myServiceIntent);
            }
        });

        startIntentServiceBtn = (Button)findViewById(R.id.start_intent_service);
        startIntentServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myIntentServiceIntent);
                startService(myIntentService2Intent);
            }
        });

        boundServiceBtn = (Button)findViewById(R.id.bound_service);
        boundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                bindService(myServiceIntent, con, Context.BIND_AUTO_CREATE);
            }
        });

        operateBoundServiceBtn = (Button)findViewById(R.id.operate_bound_service);
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

        getBoundServiceProBtn = (Button)findViewById(R.id.get_bound_service_pro);
        getBoundServiceProBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    ToastUtils.show(context, R.string.operate_value_tip, Integer.toString(myService.getCount()));
                } else {
                    ToastUtils.show(context, R.string.bind_service_tip, Toast.LENGTH_SHORT);
                }
            }
        });

        unboundServiceBtn = (Button)findViewById(R.id.unbound_service);
        unboundServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    unbindService(con);
                    myService = null;
                }
            }
        });

        startAIDLServiceBtn = (Button)findViewById(R.id.start_aidl_service);
        startAIDLServiceBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(myAIDLServiceIntent);
            }
        });

        stopAIDLServiceBtn = (Button)findViewById(R.id.stop_aidl_service);
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
