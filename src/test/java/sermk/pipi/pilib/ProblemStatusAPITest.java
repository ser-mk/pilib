package sermk.pipi.pilib;

import junit.framework.TestCase;

/**
 * Created by ser on 08.02.18.
 */
public class ProblemStatusAPITest extends TestCase {
    public void testDeletedStatus() throws Exception {

        String ret;

        ret = ProblemStatusAPI.deletedStatus("delete=");
        assertTrue(ret.isEmpty());

        ret = ProblemStatusAPI.deletedStatus("delete=a");
        assertEquals(ret,"a");
    }

}