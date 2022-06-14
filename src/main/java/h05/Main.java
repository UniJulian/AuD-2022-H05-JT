package h05;

import h05.math.Rational;

import java.math.BigInteger;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        Rational rat = new Rational(new BigInteger("-10"), new BigInteger("-40"));
        System.out.println(rat.getDenominator().intValue());
        System.out.println(rat.getNumerator().intValue());
    }
}
