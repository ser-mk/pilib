package sermk.pipi.pilib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


/**
 * Created by echormonov on 09.01.18.
 */

public class MClient {

    private static final String TAG = "MClient";

    static String NAME_MC_PACKAGE(){return "sermk.pipi.mclient";}
    static String NAME_MCS_SERVICE(){return "sermk.pipi.mlib.MTransmitterService";}

    private static Intent tempIntent(){
        Intent intent = new Intent();
        intent.setClassName(NAME_MC_PACKAGE(), NAME_MCS_SERVICE());
        return intent;
    }

    public static final byte[] EMPTY_BYTES = new byte[0];
    static final String[] EMPTY_FILENAMES = new String[0];
    public static final String EMPTY_DATA = new String();

    public static boolean sendMessage(Context context, final String subject,
                                      final String data){
        return sendMessage(context,subject,data,EMPTY_BYTES);
    }

    public static boolean sendMessage(Context context, final String subject,
                                      final String data,
                                      final byte[] attached_data){
        Intent intent = tempIntent();
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, data + getVersionInfo(context));
        intent.putExtra(Intent.EXTRA_STREAM, EMPTY_FILENAMES);
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, attached_data);

        final ComponentName c = context.startService(intent);
        if(c == null){
            Log.w(TAG, "sent FAILED!");
            return false;
        } else {
            Log.v(TAG, "sent success");
        }
        return true;
    }

    //get the current version number and name
    private static String getVersionInfo(Context context) {
        String versionName = "error";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                context.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "\r\n" + "app: " + context.getApplicationContext().getPackageName() + " version: " + versionName + " code: " + String.valueOf(versionCode);
    }
}
