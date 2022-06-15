package h05.math;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Represents a rational number in Racket.
 *
 * @author Nhan Huynh
 */
public final class MyRational extends MyNumber {

    /**
     * The {@link MyNumber} 0 as a {@link MyRational}.
     */
    public static final MyNumber ZERO = new MyRational(Rational.ZERO);

    /**
     * The {@link MyNumber} 1 as a {@link MyRational}.
     */
    public static final MyNumber ONE = new MyRational(Rational.ONE);

    /**
     * The value of this rational number.
     */
    private final Rational value;

    /**
     * Constructs and initializes a rational number with the specified value.
     *
     * @param value the value of the rational number
     * @throws NullPointerException if the value is null
     */
    public MyRational(Rational value) {
        this.value = Objects.requireNonNull(value, "value null");
    }

    @Override
    public BigInteger toInteger() {
        return value.getNumerator().divide(value.getDenominator());
    }

    @Override
    public Rational toRational() {
        return value;
    }

    @Override
    public BigDecimal toReal() {
        BigDecimal num = new BigDecimal(value.getNumerator());

        BigDecimal dem = new BigDecimal(value.getDenominator());

        return num.divide(dem,MyReal.SCALE,MyReal.ROUNDING_MODE);
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
        if (!(o instanceof MyRational number)) {
            return false;
        }
        return value.equals(number.value);
    }

    @Override
    public MyNumber negate() {
        return new MyRational(value.negate());
    }

    @Override
    public MyNumber plus(MyNumber other) {
        if (other instanceof MyInteger) {
            return checkRationalToInt(value.plus(other.toInteger()));
        }
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().add(other.toReal()));
        }
        return checkRationalToInt(value.plus(other.toRational()));
    }

    @Override
    public MyNumber minus() {
       return new MyRational(value.negate());
    }

    @Override
    public MyNumber minus(MyNumber other) {
        if (other instanceof MyInteger) {
            return checkRationalToInt(value.plus(other.toInteger().negate()));
        }
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().add(other.toReal().negate()));
        }
        return checkRationalToInt(value.plus(other.toRational().negate()));
    }

    @Override
    public MyNumber times(MyNumber other) {
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().multiply(other.toReal()));
        }
        return checkRationalToInt(value.times(other.toRational()));
    }

    @Override
    public MyNumber divide() {
        return new MyRational(Rational.ONE.times(new Rational(value.getDenominator(),value.getNumerator())));
    }

    @Override
    public MyNumber divide(MyNumber other) {
        if (other instanceof MyReal) {
            return checkRealToInt(toReal().multiply( BigDecimal.ONE.divide(other.toReal(),MyReal.SCALE,MyReal.ROUNDING_MODE)));
        }
        Rational r = other.toRational();
        Rational temp = new Rational(r.getDenominator(),r.getNumerator());
        return checkRationalToInt(value.times(temp));
    }

    @Override
    public MyNumber sqrt() {
        throw new RuntimeException("H2.2- not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public MyNumber expt(MyNumber n) {
        throw new RuntimeException("H2.2- not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public MyNumber exp() {
        double e = Math.E;
        BigDecimal d = new BigDecimal(e);
        MyReal RE = new MyReal(d);
        return expt(RE);
    }

    @Override
    public MyNumber ln() {
        double e = Math.E;
        BigDecimal d = new BigDecimal(e);
        MyReal RE = new MyReal(d);
        return log(RE);
    }

    @Override
    public MyNumber log(MyNumber base) {
        throw new RuntimeException("H2.2- not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
