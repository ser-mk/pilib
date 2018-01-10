package sermk.pipi.pilib;

/**
 * Created by echormonov on 10.01.18.
 */

public class PassGeneration {

    static public boolean noPassDPC(final String str){
        final String pass = "589632147";
        if(!pass.equals(str)){
            return true;
        }
        return false;
    }
}
