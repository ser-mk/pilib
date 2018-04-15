package sermk.pipi.pilib;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by echormonov on 15.01.18.
 */

public class ProblemStatusAPI {

    public enum Type { TEMPORARY , PERSISTENT }
    private static final String DELETED = "delete";

    private static final String SEPARATOR = "=";
    public static final String NO_DELETED = new String();
    //private static final byte[] EMPTY_BYTES = new byte[1];

    public static void setStatus(Context context, final String problem){

        final String TAG_CONTEXT = context.getClass().getName();

        Intent intent = new Intent(CommandCollection.ACTION_RECIVER_SET_PI_STATUS);

        Log.w(TAG_CONTEXT, "set status " + problem);
        intent.putExtra(NameFieldCollection.FIELD_RECIVER_DATA_TEXT,problem);
        context.sendBroadcast(intent);
    }

    public static void clearStatus(Context context, final String problem){
        setStatus(context, DELETED + SEPARATOR + problem);
    }

    public static String deletedStatus(final String problem){
        if(!problem.startsWith(DELETED)){
            return NO_DELETED;
        }
        final int pos_pr = DELETED.length() + SEPARATOR.length();
        String pr = problem.substring(pos_pr);
        return pr;
    }


}
