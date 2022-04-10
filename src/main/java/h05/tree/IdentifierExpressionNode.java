package h05.tree;

import h05.exception.IllegalIdentifierExceptions;
import h05.math.MyNumber;

import java.util.Map;

/**
 * This class represents an identifier operand arithmetic expression node. An identifier operand is
 * a variable name.
 *
 * <p>Example:
 * <ul>
 *     <li>Identifier node with the identifier a</li>
 *     <li>Racket notation: (define a 2) - constant identifier a with the value 2</li>
 * </ul>
 *
 * <pre>{@code
 *    IdentifierExpressionNode node = new IdentifierExpressionNode("a");
 * }</pre>
 *
 * @author Nhan Huynh
 */
public class IdentifierExpressionNode extends OperandExpressionNode {
    /**
     * The identifier name.
     */
    private final String value;

    /**
     * Constructs and initializes an identifier expression node with the given value.
     *
     * @param value the identifier name
     *
     * @throws IllegalArgumentException    if the identifier name is not valid
     * @throws IllegalIdentifierExceptions if the identifier name is not valid
     * @throws NullPointerException        if the identifier name is {@code null}
     */
    public IdentifierExpressionNode(String value) {
        throw new RuntimeException("H3.2 - not implemented"); // TODO: H3.2 - remove if implemented
    }

    /**
     * Returns the identifier name.
     *
     * @return the identifier name
     */
    public String getValue() {
        return value;
    }

    @Override
    public MyNumber evaluate(Map<String, MyNumber> identifiers) {
        throw new RuntimeException("H3.2 - not implemented"); // TODO: H3.2 - remove if implemented
    }

    @Override
    public ArithmeticExpressionNode clone() {
        throw new RuntimeException("H3.2 - not implemented"); // TODO: H3.2 - remove if implemented
    }

    @Override
    public String toString() {
        return value;
    }
}
