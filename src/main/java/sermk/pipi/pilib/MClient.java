package sermk.pipi.pilib;

import android.app.Activity;
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
    static String NAME_MCS_SERVICE(){return "sermk.pipi.mclient.MTransmitterService";}
    final static String NAME_MC_ACTIVITY = "sermk.pipi.mclient.LoginActivity";

    private static Intent tempIntent(){
        Intent intent = new Intent();
        intent.setClassName(NAME_MC_PACKAGE(), NAME_MCS_SERVICE());
        return intent;
    }

    public static boolean runMC(Activity activity){
        if (!AppRunner.run(activity,NAME_MC_PACKAGE(),NAME_MC_ACTIVITY)){
            return false;
        }
        return true;
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
        intent.putExtra(NameFieldCollection.FIELD_RECIVER_SUBJECT, subject);
        intent.putExtra(NameFieldCollection.FIELD_RECIVER_DATA_TEXT, data + getVersionInfo(context));
        intent.putExtra(NameFieldCollection.FIELD_RECIVER_ATTACHED_FILENAMES, EMPTY_FILENAMES);
        intent.putExtra(NameFieldCollection.FIELD_RECIVER_ATTACHED_BYTES, attached_data);

        final ComponentName c = context.startService(intent);
        if(c == null){
            Log.w(TAG, "sent FAILED \n subj :" + subject + "\n" + data);
            return false;
        }

        Log.v(TAG, "sent success \n subj :" + subject + "\n" + data);
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
