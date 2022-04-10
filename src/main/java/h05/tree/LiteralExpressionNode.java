package h05.tree;

import h05.math.MyNumber;

import java.util.Map;
import java.util.Objects;

/**
 * This class represents a literal operand arithmetic expression node. A literal operand is a {@link
 * MyNumber}.
 *
 * <p>Example:
 * <ul>
 *     <li>Literal node with the number 2.5/li>
 * </ul>
 *
 * <pre>{@code
 *    LiteralExpressionNode node = new LiteralExpressionNode(new MyReal(2.5));
 * }</pre>
 *
 * @author Nhan Huynh
 */
public class LiteralExpressionNode extends OperandExpressionNode {

    /**
     * The literal operand.
     */
    private final MyNumber value;

    /**
     * Constructs and initializes a literal operand arithmetic expression node with the given
     * value.
     *
     * @param value the literal operand
     *
     * @throws NullPointerException if the value is {@code null}
     */
    public LiteralExpressionNode(MyNumber value) {
        Objects.requireNonNull(value, "value null");
        this.value = value;
    }

    /**
     * Returns the literal operand.
     *
     * @return the literal operand
     */
    public MyNumber getValue() {
        return value;
    }

    @Override
    public MyNumber evaluate(Map<String, MyNumber> identifiers) {
        throw new RuntimeException("H3.1 - not implemented"); // TODO: H3.1 - remove if implemented
    }

    @Override
    public ArithmeticExpressionNode clone() {
        throw new RuntimeException("H3.1 - not implemented"); // TODO: H3.1 - remove if implemented
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
