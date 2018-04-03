package sermk.pipi.pilib;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by ser on 03.04.18.
 */

public class ReciverSkeleton {

    public static class ReciverVarible{
        public ErrorCollector EC;
        public String action;
        public String content;
        public byte[] array;
    }

    static public ReciverVarible parseIntent(Intent intent, String TAG){
        ErrorCollector EC = new ErrorCollector();
        Log.v(TAG, "inent: " + intent.toString());

        String action = "";
        try{
            action = intent.getAction().trim();
        } catch (Exception e){
            action = "wrong action!";
            Log.w(TAG, "action is not exist!");
        }
        Log.v(TAG, action);

        String content = "";
        try{
            content = intent.getStringExtra(Intent.EXTRA_TEXT);
            content.isEmpty();
        } catch (Exception e){
            content = "wrong content!";
            Log.w(TAG, "content is not exist!");
        }
        Log.v(TAG, content);

        byte[] array = new byte[0];
        try{
            array = intent.getByteArrayExtra(Intent.EXTRA_INITIAL_INTENTS);
            array.hashCode();
        } catch (Exception e){
            array = "wrong byte array !".getBytes();
            Log.w(TAG, "attached data absent!");
        }

        final ReciverVarible rv = new ReciverVarible();

        rv.EC = EC;
        rv.action = action;
        rv.content = content;
        rv.array = array;

        return rv;
    }
}
