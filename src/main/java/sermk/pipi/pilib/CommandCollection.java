package sermk.pipi.pilib;

import android.content.Intent;

/**
 * Created by echormonov on 10.01.18.
 */

public class CommandCollection {
    public static final String ACTION_RECIVER_PILAUNCHER_SET_SETTINGS = "ACTION_RECIVER_PILAUNCHER_SET_SETTINGS";
    public static final String ACTION_RECIVER_PILAUNCHER_SAVE_SETTINGS = "ACTION_RECIVER_PILAUNCHER_SAVE_SETTINGS";
    public static final String ACTION_RECIVER_INSTALL_PACKAGE = "INSTALL_PACKAGE";
    public static final String ACTION_RECIVER_REMOVE_PACKAGE = "REMOVE_PACKAGE";
    public static final String ACTION_RECIVER_SET_PI_STATUS ="SET_PI_STATUS";

    public static final String ACTION_RECIVER_PIBALL_GET_SETTINGS ="PIBALL_GET_SETTINGS";
    public static final String ACTION_RECIVER_PIBALL_SET_SETTINGS ="PIBALL_SET_SETTINGS";

    public static final String ACTION_RECIVER_FOR_ALL_QUERY_SETTINGS ="ACTION_RECIVER_FOR_ALL_QUERY_SETTINGS";
    public static final String ACTION_RECIVER_MCLIENT_SET_AND_SAVE_SETTINGS ="ACTION_RECIVER_MCLIENT_SET_AND_SAVE_SETTINGS";

    public static final String ACTION_RECIEVER_MCLIENT_CONNECTION_RESULT = "ACTION_RECIEVER_MCLIENT_CONNECTION_RESULT";

    public static final String FIELD_RECIVER_DATA_TEXT = Intent.EXTRA_TEXT;
    public static final String FIELD_RECIVER_ATTACHED_BYTES = Intent.EXTRA_INITIAL_INTENTS;
}
