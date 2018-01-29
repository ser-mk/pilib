package sermk.pipi.pilib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by echormonov on 15.01.18.
 */

public class StatusSender {

    public enum Type { TEMPORARY , PERSISTENT }
    public static final String DELETED = "delete";

    private static final String SEPARATOR = "=";
    private static final byte[] EMPTY_BYTES = new byte[1];

    static void setStatus(Context context,final Type type,
                             final String key,final String value){

        final String TAG_CONTEXT = context.getClass().getName();

        Intent intent = new Intent(CommandCollection.ACTION_RECIVER_SET_PI_STATUS);
        final String text = key + SEPARATOR + value;
        intent.putExtra(CommandCollection.FIELD_RECIVER_DATA_TEXT,text);
        if(Type.PERSISTENT.equals(type)) {
            Log.v(TAG_CONTEXT, "set PERSISTENT status" )
            intent.putExtra(CommandCollection.FIELD_RECIVER_ATTACHED_BYTES, EMPTY_BYTES);
        }
        context.sendBroadcast(intent);
    }
}
