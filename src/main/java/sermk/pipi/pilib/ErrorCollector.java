package sermk.pipi.pilib;

import java.util.Arrays;

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

    public void clear(){error = "";}

    public static String subjError(final String TAG, final String action){
        return TAG + SUBJ_ERROR_OPERATION + action;
    }

    public static String getStackTraceString(Throwable e){
        //return Arrays.toString(e.getStackTrace());
        String trace = e.toString() + "\n";
        for (StackTraceElement e1 : e.getStackTrace()) {
            trace += "\t at " + e1.toString() + "\n";
        }
        return trace;
    }
}
