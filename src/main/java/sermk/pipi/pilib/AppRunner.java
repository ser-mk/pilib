package sermk.pipi.pilib;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by echormonov on 26.12.17.
 */

public class AppRunner {

    private final static String TAG = "AppRunner";

    private static final int IF_ACTIVITY_EXIST = 1;


    public static void onGameResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG,
            String.valueOf(requestCode) + " " + String.valueOf(resultCode)
        );
    }

    public static boolean run(Activity act, final String packageName, final String activityName){

        Log.v(TAG, packageName);
        PackageManager manager = act.getPackageManager();

        Intent intent = new Intent();
        intent.setClassName(packageName, activityName);
        /*
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(0);
        */
        try {
            //act.startActivityForResult(intent, IF_ACTIVITY_EXIST);
            act.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Log.w(TAG,"game can not run!(");
            return false;
        }
    }
}
