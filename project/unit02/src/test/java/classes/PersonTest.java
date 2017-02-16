package classes;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * Created by Dmitrii on 2/16/2017.
 */
public class PersonTest {
    @Test
    public void toStringTest() throws Exception {

        final Person person = new Person("John", "Doe", 33);
        System.out.println(person.toString());

        System.out.println(Modifier.isFinal(Person.class.getDeclaredField("age").getModifiers()));

    }

}