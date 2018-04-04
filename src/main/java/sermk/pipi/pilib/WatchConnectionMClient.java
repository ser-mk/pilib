package sermk.pipi.pilib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    public WatchConnectionMClient(long TIMEOUT, long TimeFine) {
        this.TIMEOUT = TIMEOUT;
        TimeFineUnsuccessableConnection = TimeFine;
    }

    public void init(Context context){
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

    static void markConnectionsResult(boolean succes){
        if(succes) {
            lastSuccessConnectionDate = new Date().getTime();
        } else {
            lastSuccessConnectionDate -= TimeFineUnsuccessableConnection;
        }
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

            markConnectionsResult(rv.content.equals(SUCCES));
        }
    }
}
