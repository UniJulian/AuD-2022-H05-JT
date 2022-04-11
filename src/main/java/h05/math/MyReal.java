package h05.math;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
     * @throws NullPointerException if the value is null
     */
    public MyReal(BigDecimal value) {
        Objects.requireNonNull(value, "value null");
        this.value = value.setScale(SCALE, ROUNDING_MODE);
    }

    /**
     * Rounds the number down.
     *
     * @return the rounded number
     */
    private BigDecimal round() {
        int sign = value.signum();
        BigDecimal rounded = value.abs();
        return sign == -1 ? rounded.negate() : rounded;
    }

    @Override
    public BigInteger toInteger() {
        return round().toBigInteger();
    }

    @Override
    public Rational toRational() {
        throw new RuntimeException("H1.2 not implemented"); // TODO: remove if H1.2 implemented
    }

    @Override
    public BigDecimal toReal() {
        throw new RuntimeException("H1.2 not implemented"); // TODO: remove if H1.2 implemented
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
        throw new RuntimeException("H2.1 - not implemented"); // TODO: H2.1 - remove if implemented
    }

    @Override
    public MyNumber minus(MyNumber other) {
        throw new RuntimeException("H2.1 - not implemented"); // TODO: H2.1 - remove if implemented
    }

    @Override
    public MyNumber times(MyNumber other) {
        return checkRealToInt(value.multiply(other.toReal()));
    }

    @Override
    public MyNumber divide() {
        throw new RuntimeException("H2.1 - not implemented"); // TODO: H2.1 - remove if implemented
    }

    @Override
    public MyNumber divide(MyNumber other) {
        throw new RuntimeException("H2.1 - not implemented"); // TODO: H2.1 - remove if implemented
    }

    @Override
    public MyNumber sqrt() {
        throw new RuntimeException("H2.2 - not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public MyNumber expt(MyNumber n) {
        throw new RuntimeException("H2.2 - not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public MyNumber exp() {
        throw new RuntimeException("H2.2 - not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public MyNumber ln() {
        throw new RuntimeException("H2.2 - not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public MyNumber log(MyNumber base) {
        throw new RuntimeException("H2.2 - not implemented"); // TODO: H2.2 - remove if implemented
    }

    @Override
    public String toString() {
        return value.stripTrailingZeros().toString();
    }
}
