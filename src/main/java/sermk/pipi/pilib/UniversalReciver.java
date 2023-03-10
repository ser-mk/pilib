package sermk.pipi.pilib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.net.InetAddress;

/**
 * Created by ser on 03.04.18.
 */

public class UniversalReciver {

    public static class ReciverVarible{
        public String action;
        public String content;
        public byte[] array;
        public Uri uri;
    }

    static public ReciverVarible parseIntent(Intent intent, String TAG){
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

        Uri uri = Uri.EMPTY;
        try {
            uri =  Uri.parse(intent.getStringExtra(Intent.EXTRA_STREAM));
            uri.toString();
        } catch (Exception e) {
            uri = Uri.EMPTY;
        }

        final ReciverVarible rv = new ReciverVarible();

        rv.action = action;
        rv.content = content;
        rv.array = array;
        rv.uri = uri;

        return rv;
    }
}
