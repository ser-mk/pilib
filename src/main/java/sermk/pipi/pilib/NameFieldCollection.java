package sermk.pipi.pilib;

import android.content.Intent;

/**
 * Created by ser on 07.04.18.
 */

public class NameFieldCollection {

    static public final String FIELD_JSON_SETTINGS = "FIELD_JSON_SETTINGS";
    static public final String SHARED_PREF_NAME_FILE = "SHARED_PREF_NAME_FILE";

    public static final String FIELD_RECIVER_SUBJECT = Intent.EXTRA_SUBJECT;
    public static final String FIELD_RECIVER_DATA_TEXT = Intent.EXTRA_TEXT;
    public static final String FIELD_RECIVER_ATTACHED_BYTES = Intent.EXTRA_INITIAL_INTENTS;
    public static final String FIELD_RECIVER_ATTACHED_FILENAMES = Intent.EXTRA_STREAM;

    public static final String[] DEFAULT_WHITE_LIST_APPS = new String[]{
            "sermk.pipi.mclient",
            "com.afwsamples.testdpc",
            "sermk.pipi.pilauncher",
            "ser.pipi.piball",
    };

    public static String errSaveSettings(boolean success){
        return success ? ErrorCollector.NO_ERROR : "error save settings";
    }
}
