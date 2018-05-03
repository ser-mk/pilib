package sermk.pipi.pilib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import java.util.Date;

/**
 * Created by ser on 04.04.18.
 */

public class WatchConnectionMClient {

    private final String TAG= this.getClass().getName();
    private final long TIMEOUT;
    private static long lastSuccessConnectionDate = 0;
    private static long  TimeFineUnsuccessableConnection = 0;

    private static final String NAME_FILE = "WatchConnectionMClient";
    private static final String NAME_VAR = "lastSuccessConnectionDate";

    private static long getLastSuccessConnectionDate(Context context){
        final SharedPreferences settings = context.
            getSharedPreferences(NAME_FILE,Context.MODE_PRIVATE);
        return settings.getLong(NAME_VAR,0);
    }

    private static void setLastSuccessConnectionDate(Context context){
        final SharedPreferences settings = context.
            getSharedPreferences(NAME_FILE,Context.MODE_PRIVATE);
        settings.edit().putLong(NAME_VAR,new Date().getTime()).apply();
    }

    public WatchConnectionMClient(Context context, long TIMEOUT, long TimeFine) {
        this.TIMEOUT = TIMEOUT;
        TimeFineUnsuccessableConnection = TimeFine;
        lastSuccessConnectionDate = getLastSuccessConnectionDate(context);
    }

    public void initReciverForTest(Context context){
        final WatchMCConnectionReciever br = new WatchMCConnectionReciever();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(CommandCollection.ACTION_RECIEVER_MCLIENT_CONNECTION_RESULT);
        context.registerReceiver(br, filter);
    }

    public boolean checkTimeout(){
        final long time = new Date().getTime();
        final long delta = time - lastSuccessConnectionDate;

        return delta > TIMEOUT;
    }
    
    static final public String SUCCES = "SUCCES";

    public static void sendMCConnectionResult(Context context, String result){
        Intent intent = new Intent(CommandCollection.ACTION_RECIEVER_MCLIENT_CONNECTION_RESULT);
        intent.putExtra(Intent.EXTRA_TEXT, result);
        context.sendBroadcast(intent);
    }

    public static class WatchMCConnectionReciever extends BroadcastReceiver{
        private String TAG = this.getClass().getName();

        @Override
        public void onReceive(Context context, Intent intent) {

            final UniversalReciver.ReciverVarible rv
                    = UniversalReciver.parseIntent(intent, TAG);

            if(!CommandCollection.ACTION_RECIEVER_MCLIENT_CONNECTION_RESULT.equals(rv.action)){
                MClient.sendMessage(context,
                        ErrorCollector.subjError(TAG,rv.action), "undefined action");
                return;
            }

            if(rv.content.equals(SUCCES)) {
                lastSuccessConnectionDate = new Date().getTime();
                setLastSuccessConnectionDate(context);
            } else {
                lastSuccessConnectionDate -= TimeFineUnsuccessableConnection;
            }
        }
    }
}
