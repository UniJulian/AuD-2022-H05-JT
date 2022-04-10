package h05.math;

import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Represents a rational number (fraction). The fraction is stored as a numerator and denominator
 * and the sign will be stored in the numerator.
 *
 * @author Nhan Huynh
 */
public final class Rational {

    /**
     * The constant 0 as a {@link Rational}.
     */
    public static final Rational ZERO = new Rational(BigInteger.ZERO, BigInteger.ONE);

    /**
     * The constant 1 as a {@link Rational}.
     */
    public static final Rational ONE = new Rational(BigInteger.ONE, BigInteger.ONE);

    /**
     * The numerator of this rational number.
     */
    private final BigInteger numerator;

    /**
     * The denominator of this rational number.
     */
    private final BigInteger denominator;

    /**
     * Constructs and initializes a rational number with the specified numerator and denominator.
     *
     * @param numerator   the numerator of the rational number
     * @param denominator the denominator of the rational number
     *
     * @throws ArithmeticException if the denominator is zero
     */
    public Rational(BigInteger numerator, BigInteger denominator) {
        Objects.requireNonNull(numerator, "numerator null");
        Objects.requireNonNull(denominator, "denominator null");
        if (denominator.signum() == 0) {
            throw new ArithmeticException("Division by zero");
        }

        throw new RuntimeException("H1.1 not implemented"); // TODO: remove if H1.1 implemented
    }

    /**
     * Returns the numerator of this rational number.
     *
     * @return the numerator of this rational number
     */
    public BigInteger getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator of this rational number.
     *
     * @return the denominator of this rational number
     */
    public BigInteger getDenominator() {
        return denominator;
    }

    /**
     * Returns a rational whose value is {@code (-this)}.
     *
     * @return {@code -this}
     */
    public Rational negate() {
        return new Rational(numerator.negate(), denominator);
    }

    /**
     * Returns the sum of this rational number and the integer number.
     *
     * @param other the integer number to add
     *
     * @return the sum of this rational number and the integer number
     */
    public Rational plus(BigInteger other) {
        return new Rational(numerator.add(denominator.multiply(other)), denominator);
    }

    /**
     * Returns the sum of this rational number and the rational number.
     *
     * @param other the rational number to add
     *
     * @return the sum of this rational number and the rational number
     */
    public Rational plus(Rational other) {
        return new Rational(
            numerator.multiply(other.denominator).add(denominator.multiply(other.numerator)),
            denominator.multiply(other.denominator)
        );
    }

    /**
     * Returns the product of this rational number and the integer number.
     *
     * @param other the integer number to multiply
     *
     * @return the product of this rational number and the integer number
     */
    public Rational times(BigInteger other) {
        return new Rational(numerator.multiply(other), denominator);
    }

    /**
     * Returns the product of this rational number and the rational number.
     *
     * @param other the rational number to multiply
     *
     * @return the product of this rational number and the rational number
     */
    public Rational times(Rational other) {
        return new Rational(
            numerator.multiply(other.numerator),
            denominator.multiply(other.denominator)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rational number)) {
            return false;
        }
        return numerator.equals(number.numerator) && denominator.equals(number.denominator);
    }

    @Override
    public String toString() {
        if (numerator.signum() == -1) {
            return String.format("-%s/%s", numerator.negate(), denominator);
        }
        return String.format("%s/%s", numerator, denominator);
    }
}
