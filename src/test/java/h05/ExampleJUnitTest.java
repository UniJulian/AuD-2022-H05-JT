package h05;

import h05.math.Rational;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class ExampleJUnitTest {

    @Test
    public void testAddition() {
        Rational rat = new Rational(new BigInteger("015"), new BigInteger("-3"));
        System.out.println(rat.getNumerator().intValue());
        System.out.println(rat.getDenominator().intValue());

    }
}
