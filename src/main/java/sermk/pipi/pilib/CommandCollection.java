package sermk.pipi.pilib;

import android.content.Intent;

/**
 * Created by echormonov on 10.01.18.
 */

public class CommandCollection {
    public static final String ACTION_RECIVER_SET_SETTINGS = "PI.SETTINGS.SET";
    public static final String ACTION_RECIVER_SAVE_SETTINGS = "PI.SETTINGS.SAVE";
    public static final String ACTION_RECIVER_INSTALL_PACKAGE = "INSTALL_PACKAGE";
    public static final String ACTION_RECIVER_REMOVE_PACKAGE = "REMOVE_PACKAGE";
    public static final String ACTION_RECIVER_SET_PI_STATUS ="SET_PI_STATUS";

    public static final String ACTION_RECIVER_PIBALL_GET_SETTINGS ="PIBALL_GET_SETTINGS";
    public static final String ACTION_RECIVER_PIBALL_SET_SETTINGS ="PIBALL_SET_SETTINGS";

    public static final String FIELD_RECIVER_DATA_TEXT = Intent.EXTRA_TEXT;
    public static final String FIELD_RECIVER_ATTACHED_BYTES = Intent.EXTRA_INITIAL_INTENTS;
}
