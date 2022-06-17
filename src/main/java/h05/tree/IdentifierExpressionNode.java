package h05.tree;

import h05.exception.IllegalIdentifierExceptions;
import h05.exception.UndefinedIdentifierException;
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
     * @throws IllegalArgumentException    if the identifier name is not valid
     * @throws IllegalIdentifierExceptions if the identifier name is not valid
     * @throws NullPointerException        if the identifier name is {@code null}
     */
    public IdentifierExpressionNode(String value) {
        if(value == null)
            throw new NullPointerException();
        if(value.length() == 0)
            throw new IllegalArgumentException("empty string");

        if( !(value.matches("[a-zA-Z-]+")))
            throw new IllegalIdentifierExceptions(value);
        this.value = value;
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
        boolean isInEnumDefined = false;
        for(Identifier i:Identifier.values())
            if(i.getValue().equals(identifiers.get(value)))
                isInEnumDefined = true;

        if(isInEnumDefined)
            throw new IllegalIdentifierExceptions(value);
        if(!(identifiers.containsKey(value) ))
            throw new UndefinedIdentifierException(value);

        return identifiers.get(value);
    }

    @Override
    public ArithmeticExpressionNode clone() {
        return new IdentifierExpressionNode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
