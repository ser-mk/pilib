package sermk.pipi.pilib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by echormonov on 26.12.17.
 */

public class PiBind implements ServiceConnection {

    private final String TAG = this.getClass().getName();

    private enum BindState { UNDEFINED, BINDED, UNBINED, REMOTE_EXEPTION }
    private BindState state = BindState.UNDEFINED;
    private Pinterface mIRemoteService;

    private static String packageName =  "sermk.pipi.pilauncher";
    private static String className =  "sermk.pipi.pilauncher.PIService";

    public PiBind(Activity act) {
        Log.v(TAG,"start bind");
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        final boolean ret = act.bindService(intent, this, Context.BIND_AUTO_CREATE);
        Log.v(TAG, "bind to service " + packageName + " " + className + " is " + ret);
    }

    public static final int CONNECTED_PROBLEM = -1;
    public static final int POSITION_UNDEFINED = -2;

    public static final int POSITION_MIN = 0;
    public static final int POSITION_MAX = 255;

    public int getPosition(){
        if(state == BindState.BINDED && mIRemoteService != null){
            try {
                return mIRemoteService.getPosition();
            } catch (RemoteException e) {
                e.printStackTrace();
                state = BindState.REMOTE_EXEPTION;
            }
        }
        if(state == BindState.UNDEFINED){
            return POSITION_UNDEFINED;
        }

        return CONNECTED_PROBLEM;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.v(TAG,"onServiceConnected " + service.toString());
        mIRemoteService = Pinterface.Stub.asInterface(service);
        state = BindState.BINDED;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.v(TAG,"onServiceDisconnected");
        mIRemoteService = null;
        state = BindState.UNBINED;
    }

    public void release(Activity act){
        Log.v(TAG, "unbind " + packageName + " " + className + " service");
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
