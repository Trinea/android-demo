package cn.trinea.android.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * MyService
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-9
 */
public class MyService extends Service {

    private int      count;
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service onCreate", Toast.LENGTH_SHORT).show();
        count = 0;
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service onBind", Toast.LENGTH_SHORT).show();
        return myBinder;
    }

    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Service onUnbind", Toast.LENGTH_SHORT).show();
        return false;
    }

    public int getCount() {
        return count;
    }

    public int increaseCount() {
        return ++count;
    }

    public int decreaseCount() {
        return --count;
    }

    public class MyBinder extends Binder {

        MyService getService() {
            return MyService.this;
        }
    }
}
