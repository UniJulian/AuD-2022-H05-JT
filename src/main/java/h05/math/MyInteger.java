package h05.math;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents an integer in Racket.
 *
 * @author Nhan Huynh
 */
public final class MyInteger extends MyNumber {

    /**
     * The constant {@link MyNumber} 0 as a {@link MyInteger}.
     */
    public static final MyNumber ZERO = new MyInteger(BigInteger.ZERO);

    /**
     * The constant {@link MyNumber} 1 as a {@link MyInteger}.
     */
    public static final MyNumber ONE = new MyInteger(BigInteger.ONE);

    /**
     * The value of the integer.
     */
    private final BigInteger value;

    /**
     * Constructs and initializes an integer with the specified value.
     *
     * @param value the value of the real number
     * @throws NullPointerException if the value is null
     */
    public MyInteger(BigInteger value) {
        this.value = Objects.requireNonNull(value, "value null");
    }

    @Override
    public BigInteger toInteger() {
        return value;
    }

    @Override
    public Rational toRational() {
        return new Rational(value,BigInteger.ONE);
    }

    @Override
    public BigDecimal toReal() {
        BigDecimal newValue = new BigDecimal(value);
        BigDecimal a = BigDecimal.valueOf((long)Math.pow(10,MyReal.SCALE));
        BigDecimal aStrich = BigDecimal.valueOf((long)Math.pow(10,MyReal.SCALE));
        BigDecimal b = newValue.multiply(aStrich);
        return b.divide(a,MyReal.SCALE,MyReal.ROUNDING_MODE);
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
        if (!(o instanceof MyInteger number)) {
            return false;
        }
        return value.equals(number.value);
    }

    @Override
    public MyNumber negate() {
        return new MyInteger(value.negate());
    }

    @Override
    public MyNumber plus(MyNumber other) {
        if (other instanceof MyInteger) {
            return new MyInteger(value.add((other.toInteger())));
        }
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().add(other.toReal()));
        }
        return checkRationalToInt(other.toRational().plus(value));
    }

    @Override
    public MyNumber minus() {
        return new MyInteger(value.negate());
    }

    @Override
    public MyNumber minus(MyNumber other) {
        if (other instanceof MyInteger) {
            return new MyInteger(value.add((other.toInteger().negate())));
        }
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().add(other.toReal().negate()));
        }
        return checkRationalToInt(other.toRational().negate().plus(value));
    }

    @Override
    public MyNumber times(MyNumber other) {
        if (other instanceof MyInteger) {
            return new MyInteger(value.multiply(other.toInteger()));
        }
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().multiply(other.toReal()));
        }
        return checkRationalToInt(other.toRational().times(value));
    }

    @Override
    public MyNumber divide() {
        return new MyRational(new Rational(BigInteger.ONE,value));
    }

    @Override
    public MyNumber divide(MyNumber other) {
        if (other instanceof MyInteger) {
            return new MyInteger(value.divide(other.toInteger()));
        }
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().divide(other.toReal(),MyReal.SCALE,MyReal.ROUNDING_MODE));
        }
        return checkRationalToInt(other.toRational().times(BigInteger.ONE.divide(value)));
    }

    @Override
    public MyNumber sqrt() {

        Comparator<BigDecimal> bd = Comparator.naturalOrder();
        int counter2 = 0;
        BigDecimal val = new BigDecimal(this.value);
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
        double res = Math.round(Math.pow(val.doubleValue(),0.5) *Math.pow(10,counter2*0.5));
        return new MyInteger(BigInteger.valueOf((long)res));
    }

    @Override
    public MyNumber expt(MyNumber n) {

        BigDecimal newN = n.toReal();
        Comparator<BigDecimal> bd = Comparator.naturalOrder();
        int counter2 = 0;
        BigDecimal val = new BigDecimal(this.value);
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
        double res = Math.round(Math.pow(val.doubleValue(),newN.doubleValue()) *Math.pow(10,counter2*newN.doubleValue()));
        return new MyInteger(BigInteger.valueOf((long)res));
    }

    @Override
    public MyNumber exp() {
        BigDecimal val = new BigDecimal(this.value);
        double res = Math.pow(Math.E,val.doubleValue());
        return new MyReal(BigDecimal.valueOf(res));
    }

    @Override
    public MyNumber ln() {
        BigDecimal val = new BigDecimal(this.value);
        double a = Math.log10(val.doubleValue()) ;
        double b = Math.log10(Math.E);
        double res = a/b;
        return new MyReal(BigDecimal.valueOf(res));
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
        BigDecimal val = new BigDecimal(this.value);
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
        double res = Math.round(a/b);
        return new MyInteger(BigInteger.valueOf((long) res));

    }

    @Override
    public String toString() {
        return value.toString();
    }
}
