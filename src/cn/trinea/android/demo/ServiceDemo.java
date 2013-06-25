/*
 * Copyright 2012 Trinea.cn All right reserved. This software is the
 * confidential and proprietary information of Trinea.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Trinea.cn.
 */
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
import cn.trinea.android.demo.MyService.MyBinder;

/**
 * ServiceDemo，包括start普通服务、绑定普通服务、Intent Service
 * 
 * @author Trinea 2013-5-9
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
                                       * 服务所在进程被kill或是crash时系统调用，而不是unbindService时调用
                                       */
                                      @Override
                                      public void onServiceDisconnected(ComponentName name) {
                                          Toast.makeText(getApplicationContext(), "Service disconnect",
                                                         Toast.LENGTH_SHORT).show();
                                      }

                                      /**
                                       * 服务连接时调用，若已经连接不进行调用
                                       */
                                      @Override
                                      public void onServiceConnected(ComponentName name, IBinder service) {
                                          myService = ((MyBinder)service).getService();
                                          Toast.makeText(getApplicationContext(), "Service Connect", Toast.LENGTH_SHORT)
                                               .show();
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
                    Toast.makeText(getApplicationContext(), "增加成功，当前值为：" + myService.increaseCount(),
                                   Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "请先绑定服务。", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getBoundServiceProBtn = (Button)findViewById(R.id.getBoundServicePro);
        getBoundServiceProBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (myService != null) {
                    Toast.makeText(getApplicationContext(), "Service count:" + myService.getCount(), Toast.LENGTH_SHORT)
                         .show();
                } else {
                    Toast.makeText(getApplicationContext(), "请先绑定服务。", Toast.LENGTH_SHORT).show();
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
