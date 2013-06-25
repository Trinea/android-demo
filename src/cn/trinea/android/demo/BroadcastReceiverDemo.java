/*
 * Copyright 2012 Trinea.cn All right reserved. This software is the
 * confidential and proprietary information of Trinea.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Trinea.cn.
 */
package cn.trinea.android.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * BroadcastReceiver Demo，包括普通广播、本地广播、有序广播、粘性广播
 * 
 * @author Trinea 2012-9-20
 */
public class BroadcastReceiverDemo extends BaseActivity {

    private final static String                    ACTION_GENERAL_SEND = "cn.trinea.android.demo.BroadcastReceiverDemo.sendGeneralBroadcast";
    private final static String                    ACTION_LOCAL_SEND   = "cn.trinea.android.demo.BroadcastReceiverDemo.sendLocalBroadcast";
    private final static String                    ACTION_ORDERED_SEND = "cn.trinea.android.demo.BroadcastReceiverDemo.sendOrderedBroadcast";
    private final static String                    ACTION_STICKY_SEND  = "cn.trinea.android.demo.BroadcastReceiverDemo.sendStickyBroadcast";

    private final static String                    MSG_KEY             = "msg";
    private final static String                    RUSULT_MSG_KEY      = "resultMsg";
    private boolean                                isStickyRegister    = false;

    private MyBroadcastReceiver                    generalReceiver;
    private OrderedBroadcastReceiverPriorityHigh   orderedReceiverHigh;
    private OrderedBroadcastReceiverPriorityMedium orderedReceiverMed;
    private OrderedBroadcastReceiverPriorityLow    orderedReceiverLow;
    private MyBroadcastReceiver                    stickyReceiver;

    private Button                                 sendGeneralBtn;
    private Button                                 sendLocalBtn;
    private Button                                 sendOrderedBtn;
    private Button                                 sendStickyBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.broadcast_receiver_demo);

        generalReceiver = new MyBroadcastReceiver();
        orderedReceiverHigh = new OrderedBroadcastReceiverPriorityHigh();
        orderedReceiverMed = new OrderedBroadcastReceiverPriorityMedium();
        orderedReceiverLow = new OrderedBroadcastReceiverPriorityLow();
        stickyReceiver = new MyBroadcastReceiver();

        sendGeneralBtn = (Button)findViewById(R.id.sendGeneralBroadcast);
        sendGeneralBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_GENERAL_SEND);
                i.putExtra(MSG_KEY, "普通广播告知好声音马上开始了啦");
                sendBroadcast(i);
            }
        });

        sendLocalBtn = (Button)findViewById(R.id.sendLocalBroadcast);
        sendLocalBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_LOCAL_SEND);
                i.putExtra(MSG_KEY, "Local广播告知好声音马上开始了啦");
            }
        });

        sendOrderedBtn = (Button)findViewById(R.id.sendOrderedBroadcast);
        sendOrderedBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_ORDERED_SEND);
                i.putExtra(MSG_KEY, "有序广播告知好声音马上开始了啦");
                // 发送本地广播，设置最终接受广播的Receiver
                sendOrderedBroadcast(i, null, new OrderedBroadcastReceiverResultReceiver(), null, Activity.RESULT_OK,
                                     null, null);
            }
        });

        sendStickyBtn = (Button)findViewById(R.id.sendStickyBroadcast);
        sendStickyBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_STICKY_SEND);
                i.putExtra(MSG_KEY, "Sticky广播告知好声音马上开始了啦");
                sendStickyBroadcast(i);
                // 广播发送两秒后才注册Receiver
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isStickyRegister = true;
                        registerReceiver(stickyReceiver, new IntentFilter(ACTION_STICKY_SEND));
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(generalReceiver);
        unregisterReceiver(orderedReceiverHigh);
        unregisterReceiver(orderedReceiverMed);
        unregisterReceiver(orderedReceiverLow);
        if (isStickyRegister) {
            unregisterReceiver(stickyReceiver);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(generalReceiver, new IntentFilter(ACTION_GENERAL_SEND));

        IntentFilter high = new IntentFilter(ACTION_ORDERED_SEND);
        high.setPriority(100);
        IntentFilter med = new IntentFilter(ACTION_ORDERED_SEND);
        med.setPriority(-1);
        IntentFilter low = new IntentFilter(ACTION_ORDERED_SEND);
        low.setPriority(-100);
        registerReceiver(orderedReceiverHigh, high);
        registerReceiver(orderedReceiverMed, med);
        registerReceiver(orderedReceiverLow, low);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra(MSG_KEY), Toast.LENGTH_SHORT).show();
        }
    }

    public class OrderedBroadcastReceiverPriorityHigh extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "优先级最高的接收者接收到广播，内容为：" + intent.getStringExtra(MSG_KEY), Toast.LENGTH_SHORT).show();
            // 修改广播结果
            getResultExtras(true).putString(RUSULT_MSG_KEY, "High");
        }
    }

    public class OrderedBroadcastReceiverPriorityMedium extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,
                           "优先级中等的接收者接收到广播，内容为：" + intent.getStringExtra(MSG_KEY) + "，上一个接收者为："
                                   + getResultExtras(true).getString(RUSULT_MSG_KEY), Toast.LENGTH_SHORT).show();
            getResultExtras(true).putString(RUSULT_MSG_KEY, "Medium");
            // 取消广播
            abortBroadcast();
        }
    }

    public class OrderedBroadcastReceiverPriorityLow extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,
                           "优先级最低的接收者接收到广播，内容为：" + intent.getStringExtra(MSG_KEY) + "，上一个接收者为："
                                   + getResultExtras(true).getString(RUSULT_MSG_KEY), Toast.LENGTH_SHORT).show();
            getResultExtras(true).putString(RUSULT_MSG_KEY, "Low");
        }
    }

    public class OrderedBroadcastReceiverResultReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,
                           "结果接收者接收到广播，内容为：" + intent.getStringExtra(MSG_KEY) + "，上一个接收者为："
                                   + getResultExtras(true).getString(RUSULT_MSG_KEY), Toast.LENGTH_SHORT).show();
        }
    }
}
