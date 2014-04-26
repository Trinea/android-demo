package cn.trinea.android.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * BroadcastReceiver Demo, include general broadcast, local broadcast, ordered broadcast,sticky broadcast
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-9-20
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
    private LocalBroadcastReceiver                 localReceiver;
    private StickyBroadcastReceiver                stickyReceiver;

    private Button                                 sendGeneralBtn;
    private Button                                 sendLocalBtn;
    private Button                                 sendOrderedBtn;
    private Button                                 sendStickyBtn;

    private TextView                               generalMsg;
    private TextView                               localMsg;
    private TextView                               orderedMsg;
    private TextView                               stickyMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.broadcast_receiver_demo);

        initView();
    }

    private void initView() {
        generalMsg = (TextView)findViewById(R.id.general_broadcast_msg);
        localMsg = (TextView)findViewById(R.id.local_broadcast_msg);
        orderedMsg = (TextView)findViewById(R.id.ordered_broadcast_msg);
        stickyMsg = (TextView)findViewById(R.id.sticky_broadcast_msg);

        sendGeneralBtn = (Button)findViewById(R.id.send_general_broadcast);
        sendGeneralBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_GENERAL_SEND);
                i.putExtra(MSG_KEY, getString(R.string.general_broadcast_msg));
                sendBroadcast(i);
            }
        });

        sendLocalBtn = (Button)findViewById(R.id.send_local_broadcast);
        sendLocalBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_LOCAL_SEND);
                i.putExtra(MSG_KEY, getString(R.string.local_broadcast_msg));
                LocalBroadcastManager.getInstance(context).sendBroadcast(i);
            }
        });

        sendOrderedBtn = (Button)findViewById(R.id.send_ordered_broadcast);
        sendOrderedBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_ORDERED_SEND);
                i.putExtra(MSG_KEY, getString(R.string.ordered_broadcast_msg));
                sendOrderedBroadcast(i, null, new OrderedBroadcastReceiverResultReceiver(), null, Activity.RESULT_OK,
                        null, null);
            }
        });

        sendStickyBtn = (Button)findViewById(R.id.send_sticky_broadcast);
        sendStickyBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(ACTION_STICKY_SEND);
                i.putExtra(MSG_KEY, getString(R.string.sticky_broadcast_msg));
                sendStickyBroadcast(i);
                int waitTime = 2000;
                stickyMsg.setText(String.format(getString(R.string.sticky_broadcast_wait_tip), waitTime));
                // receiver broadcast after broadcast send 2 seconds
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isStickyRegister = true;
                        registerReceiver(stickyReceiver, new IntentFilter(ACTION_STICKY_SEND));
                    }
                }, waitTime);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        generalReceiver = new MyBroadcastReceiver();
        localReceiver = new LocalBroadcastReceiver();
        orderedReceiverHigh = new OrderedBroadcastReceiverPriorityHigh();
        orderedReceiverMed = new OrderedBroadcastReceiverPriorityMedium();
        orderedReceiverLow = new OrderedBroadcastReceiverPriorityLow();
        stickyReceiver = new StickyBroadcastReceiver();

        registerReceiver(generalReceiver, new IntentFilter(ACTION_GENERAL_SEND));
        LocalBroadcastManager.getInstance(context).registerReceiver(localReceiver, new IntentFilter(ACTION_LOCAL_SEND));

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

    @Override
    public void onStop() {
        unregisterReceiver(generalReceiver);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(localReceiver);

        unregisterReceiver(orderedReceiverHigh);
        unregisterReceiver(orderedReceiverMed);
        unregisterReceiver(orderedReceiverLow);
        if (isStickyRegister) {
            unregisterReceiver(stickyReceiver);
        }

        super.onStop();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            generalMsg.setText(intent.getStringExtra(MSG_KEY));
        }
    }

    public class StickyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            stickyMsg.setText(intent.getStringExtra(MSG_KEY));
        }
    }

    public class LocalBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            localMsg.setText(intent.getStringExtra(MSG_KEY));
        }
    }

    public class OrderedBroadcastReceiverPriorityHigh extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            orderedMsg.setText(String.format(getString(R.string.ordered_broadcast_high_tip),
                    intent.getStringExtra(MSG_KEY)));
            // modify broadcast content
            getResultExtras(true).putString(RUSULT_MSG_KEY, "High");
        }
    }

    public class OrderedBroadcastReceiverPriorityMedium extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isCancel = false;
            if (isCancel) {
                orderedMsg.setText(orderedMsg.getText()
                        + "\r\n"
                        + String.format(getString(R.string.ordered_broadcast_medium_cancel_tip),
                                intent.getStringExtra(MSG_KEY), getResultExtras(true).getString(RUSULT_MSG_KEY)));
                // calcel broadcast
                abortBroadcast();
            } else {
                orderedMsg.setText(orderedMsg.getText()
                        + "\r\n"
                        + String.format(getString(R.string.ordered_broadcast_medium_tip),
                                intent.getStringExtra(MSG_KEY), getResultExtras(true).getString(RUSULT_MSG_KEY)));
                getResultExtras(true).putString(RUSULT_MSG_KEY, "Medium");
            }
        }
    }

    public class OrderedBroadcastReceiverPriorityLow extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            orderedMsg.setText(orderedMsg.getText()
                    + "\r\n"
                    + String.format(getString(R.string.ordered_broadcast_low_tip), intent.getStringExtra(MSG_KEY),
                            getResultExtras(true).getString(RUSULT_MSG_KEY)));
            getResultExtras(true).putString(RUSULT_MSG_KEY, "Low");
        }
    }

    public class OrderedBroadcastReceiverResultReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            orderedMsg.setText(orderedMsg.getText()
                    + "\r\n"
                    + String.format(getString(R.string.ordered_broadcast_tip), intent.getStringExtra(MSG_KEY),
                            getResultExtras(true).getString(RUSULT_MSG_KEY)));
        }
    }
}
