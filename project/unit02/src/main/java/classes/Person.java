package classes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Dmitrii on 2/15/2017.
 */
@ToString(exclude = {"age"})
@Value
public class Person {
    String firstName;
    String lastName;
    int age;

}


