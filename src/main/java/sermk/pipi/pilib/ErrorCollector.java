package sermk.pipi.pilib;

/**
 * Created by echormonov on 09.01.18.
 */

public class ErrorCollector {

    public String error = "";

    public static String NO_ERROR = new String();

    static private final String SEPARATOR = " > ";
    static final String SUBJ_ERROR_OPERATION = " error operation: ";

    public String addError(final String err){
        error += SEPARATOR + err;
        return error;
    }

    public String subjError(final String TAG, final String action){
        return TAG + SUBJ_ERROR_OPERATION + action;
    }
}
