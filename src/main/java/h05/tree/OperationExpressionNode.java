package h05.tree;

import h05.math.MyNumber;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

/**
 * This class represents an operation arithmetic expression node. An operation expression node
 * contains and operator followed by
 * <it>n</it> operands depending on its arity.
 *
 * <p>Example:
 * <ul>
 *     <li>Operator node with the operation "+" and the operands 2, 3, 4/li>
 *     <li>Racket notation: (+ 2 3 4)</li>
 * </ul>
 *
 * <pre>{@code
 *    ListItem<ArithmeticExpressionNode> operands = new ListItem<>();
 *    operands.key = new LiteralExpressionNode(new MyInteger(2));
 *    operands.next = new ListItem<>();
 *    operands.next.key = new LiteralExpressionNode(new MyInteger(3));
 *    operands.next.next = new ListItem<>();
 *    operands.next.next.key = new LiteralExpressionNode(new MyInteger(4));
 *    OperationExpressionNode node = new OperationExpressionNode(Operator.ADD, operands);
 * }</pre>
 *
 * @author Nhan Huynh
 * @see Operator
 * @see ListItem
 */
public class OperationExpressionNode implements ArithmeticExpressionNode {

    /**
     * The operator of this node.
     */
    private final Operator operator;

    /**
     * The operands of this node.
     */
    private final @Nullable ListItem<ArithmeticExpressionNode> operands;

    /**
     * Contracts and initializes an operation expression node with the given operator and operands.
     *
     * @param operator the operator of this node
     * @param operands the operands of this node
     *
     * @throws NullPointerException if the operator is {@code null}
     */
    public OperationExpressionNode(Operator operator, @Nullable ListItem<ArithmeticExpressionNode> operands) {
        Objects.requireNonNull(operator, "operator null");
        throw new RuntimeException("H3.3 - not implemented"); // TODO: H3.3 - remove if implemented
    }

    /**
     * Returns the operator of this node.
     *
     * @return the operator of this node
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Returns the operands of this node.
     *
     * @return the operands of this node
     */
    public ListItem<ArithmeticExpressionNode> getOperands() {
        return operands;
    }

    @Override
    public MyNumber evaluate(Map<String, MyNumber> identifiers) {
        throw new RuntimeException("H3.3 - not implemented"); // TODO: H3.3 - remove if implemented
    }

    @Override
    public boolean isOperand() {
        return false;
    }

    @Override
    public boolean isOperation() {
        return true;
    }

    @Override
    public ArithmeticExpressionNode clone() {
        throw new RuntimeException("H3.3 - not implemented"); // TODO: H3.3 - remove if implemented
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LEFT_BRACKET);
        sb.append(operator);
        for (ListItem<ArithmeticExpressionNode> node = operands; node != null; node = node.next) {
            sb.append(" ").append(node.key);
        }
        sb.append(RIGHT_BRACKET);
        return sb.toString();
    }
}
