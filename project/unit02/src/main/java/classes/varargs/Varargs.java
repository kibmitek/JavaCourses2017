package classes.varargs;

/**
 * Created by Dmitrii on 2/16/2017.
 */
public class Varargs {
    public static void varargs(int ... ints){
        System.out.println("varargs(int ... ints)");
    }
    public static void varargs(Integer[] ...integers){
        System.out.println("varargs(Integer[] ...integers)");
    }
}
