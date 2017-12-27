package sermk.pipi.pilib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by echormonov on 26.12.17.
 */

public class PiBind implements ServiceConnection {

    private final String TAG = this.getClass().getName();
/*
    private Messenger incomingMessanger = new Messenger(new IncomingHandler());
    private Messenger bindMessanger = new Messenger(new DefaultHandler());
    */
    private boolean isBound = false;
    private Pinterface mIRemoteService;

    private static String packageName =  "sermk.pipi.game";
    private static String className =  "sermk.pipi.game.PIService";




    public PiBind(Activity act) {
        Log.v(TAG,"start bind");
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        final boolean ret = act.bindService(intent, this, Context.BIND_AUTO_CREATE);
        Log.v(TAG, "bind to service " + packageName + " " + className + " is " + ret);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.v(TAG,"onServiceConnected " + service.toString());
        mIRemoteService = Pinterface.Stub.asInterface(service);
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.v(TAG,"onServiceDisconnected");
        mIRemoteService = null;
        isBound = false;
    }

    public void release(Activity act){
        act.unbindService(this);
    }
/*
    private class IncomingHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            int what = msg.what;
            Log.v(TAG,"IncomingHandler " + String.valueOf(what) + " arg1: " + String.valueOf(msg.arg1));
        }
    }

    private class DefaultHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg){Log.v(TAG,"defaultHandler!!!!!!!!! ");}
    }

    private ServiceConnection bindConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.v(TAG,"onServiceConnected " + service.toString());
            bindMessanger = new Messenger(service);
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.v(TAG,"onServiceDisconnected");
            bindMessanger = new Messenger(new DefaultHandler());
            isBound = false;
        }
    };
    */
}
