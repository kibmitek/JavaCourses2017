package classes.varargs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dmitrii on 2/16/2017.
 */
public class VarargsTest {
    @Test
    public void varargs() throws Exception {
        Integer[] is = {1,2,3,4,5,6,7};
        Varargs.varargs(is);
    }

}