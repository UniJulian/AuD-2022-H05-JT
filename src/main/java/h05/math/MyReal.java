package h05.math;

import h05.exception.Comparison;
import h05.exception.WrongOperandException;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a real number in Racket.
 *
 * @author Nhan Huynh
 */
public final class MyReal extends MyNumber {

    /**
     * The scale of the real number for inexact numbers.
     */
    public static final int SCALE = 15;

    /**
     * The rounding mode of the real number for inexact numbers.
     */
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * The constant {@link MyNumber} 0 as a {@link MyReal}.
     */
    public static final MyNumber ZERO = new MyReal(BigDecimal.ZERO);

    /**
     * The constant {@link MyNumber} 1 as a {@link MyReal}.
     */
    public static final MyNumber ONE = new MyReal(BigDecimal.ONE);

    /**
     * The value of this real number.
     */
    private final BigDecimal value;

    /**
     * Constructs and initializes a real number with the specified value.
     *
     * @param value the value of the real number
     *
     * @throws NullPointerException if the value is null
     */
    public MyReal(BigDecimal value) {
        Objects.requireNonNull(value, "value null");
        this.value = value.setScale(SCALE, ROUNDING_MODE);
    }


    @Override
    public BigInteger toInteger() {
        return value.toBigInteger();
    }

    @Override
    public Rational toRational() {

        BigInteger a = BigInteger.valueOf((long)Math.pow(10,MyReal.SCALE));
        BigDecimal aStrich = BigDecimal.valueOf((long)Math.pow(10,MyReal.SCALE));
        BigDecimal b = value.multiply(aStrich);
        return new Rational(b.toBigInteger(),a);
    }

    @Override
    public BigDecimal toReal() {
        return value;
    }

    @Override
    public boolean isZero() {
        return this.equals(ZERO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyReal number)) {
            return false;
        }
        return value.equals(number.value);
    }

    @Override
    public MyNumber negate() {
        return new MyReal(value.negate());
    }

    @Override
    public MyNumber plus(MyNumber other) {
        return checkRealToInt(value.add(other.toReal()));
    }

    @Override
    public MyNumber minus() {
        return new MyReal(value.negate());
    }

    @Override
    public MyNumber minus(MyNumber other) {
        return checkRealToInt(value.add(other.toReal().negate()));
    }

    @Override
    public MyNumber times(MyNumber other) {
        return checkRealToInt(value.multiply(other.toReal()));
    }

    @Override
    public MyNumber divide() {
        return new MyReal(BigDecimal.ONE.divide(value,MyReal.SCALE,MyReal.ROUNDING_MODE));
    }

    @Override
    public MyNumber divide(MyNumber other) {
        if(other.isZero())
            throw new WrongOperandException(other, Comparison.DIFFERENT_FROM,new MyInteger(BigInteger.ZERO));
        return checkRealToInt(value.divide(other.toReal(),MyReal.SCALE,MyReal.ROUNDING_MODE));
    }

    @Override
    public MyNumber sqrt() {

        Comparator<BigDecimal> bd = Comparator.naturalOrder();
        int counter2 = 0;
        BigDecimal val = value;
        while( (bd.compare(val, BigDecimal.TEN) > 0 ) || (bd.compare(val, BigDecimal.ZERO) < 0 )){
            if(bd.compare(val, BigDecimal.TEN) > 0 ){
                val = val.divide(BigDecimal.TEN,MyReal.SCALE,MyReal.ROUNDING_MODE);
                counter2++;
            }
            if(bd.compare(val, BigDecimal.ZERO) < 0 ){
                val = val.multiply(BigDecimal.TEN);
                counter2--;
            }
        }
        double res = Math.pow(val.doubleValue(),0.5) *Math.pow(10,counter2*0.5);
        return checkRealToInt(new BigDecimal(res));
    }

    @Override
    public MyNumber expt(MyNumber n) {

        BigDecimal newN = n.toReal();
        Comparator<BigDecimal> bd = Comparator.naturalOrder();
        int counter2 = 0;
        BigDecimal val = value;
        while( (bd.compare(val, BigDecimal.TEN) > 0 ) || (bd.compare(val, BigDecimal.ZERO) < 0 )){
            if(bd.compare(val, BigDecimal.TEN) > 0 ){
                val = val.divide(BigDecimal.TEN,MyReal.SCALE,MyReal.ROUNDING_MODE);
                counter2++;
            }
            if(bd.compare(val, BigDecimal.ZERO) < 0 ){
                val = val.multiply(BigDecimal.TEN);
                counter2--;
            }
        }
        double res = Math.pow(val.doubleValue(),newN.doubleValue()) *Math.pow(10,counter2*newN.doubleValue());
        return checkRealToInt(new BigDecimal(res));
    }

    @Override
    public MyNumber exp() {
        double res = Math.pow(Math.E, value.doubleValue());
        return checkRealToInt(new BigDecimal(res));
    }

    @Override
    public MyNumber ln() {
        double a = Math.log10(value.doubleValue()) ;
        double b = Math.log10(Math.E);
        double res = a/b;
        return checkRealToInt(new BigDecimal(res));
    }

    @Override
    public MyNumber log(MyNumber base) {
        BigDecimal newBase = base.toReal();

        Comparator<BigDecimal> bd = Comparator.naturalOrder();
        int counter = 0;


        while( (bd.compare(newBase, BigDecimal.TEN) > 0 ) || (bd.compare(newBase, BigDecimal.ZERO) < 0 )){
            if(bd.compare(newBase, BigDecimal.TEN) > 0 ){
                newBase = newBase.divide(BigDecimal.TEN,MyReal.SCALE,MyReal.ROUNDING_MODE);
                counter++;
            }
            if(bd.compare(newBase, BigDecimal.ZERO) < 0 ){
                newBase = newBase.multiply(BigDecimal.TEN);
                counter--;
            }
        }
        int counter2 = 0;
        BigDecimal val = value;
        while( (bd.compare(val, BigDecimal.TEN) > 0 ) || (bd.compare(val, BigDecimal.ZERO) < 0 )){
            if(bd.compare(val, BigDecimal.TEN) > 0 ){
                val = val.divide(BigDecimal.TEN,MyReal.SCALE,MyReal.ROUNDING_MODE);
                counter2++;
            }
            if(bd.compare(val, BigDecimal.ZERO) < 0 ){
                val = val.multiply(BigDecimal.TEN);
                counter2--;
            }
        }
        double a = Math.log10(val.doubleValue()) ;
        a +=  counter2;
        double b = Math.log10(newBase.doubleValue());
        b += counter;
        double res =a/b;
        return checkRealToInt(new BigDecimal(res));

    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toString();
    }
}
