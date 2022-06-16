package h05.tree;

import h05.exception.WrongNumberOfOperandsException;
import h05.math.MyNumber;
import h05.math.MyReal;
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
     * @throws NullPointerException if the operator is {@code null}
     */
    public OperationExpressionNode(Operator operator, @Nullable ListItem<ArithmeticExpressionNode> operands) {
        Objects.requireNonNull(operator, "operator null");
        int belowBorder = 0;
        int upperBorder = 0;
        if(operator.equals(Operator.SUB) ||operator.equals(Operator.ADD) ||operator.equals(Operator.MUL) ||operator.equals(Operator.DIV) )
            upperBorder = Integer.MAX_VALUE;
        if(operator.equals(Operator.SUB) ||operator.equals(Operator.DIV))
            belowBorder = 1;
        if(operator.equals(Operator.EXP) || operator.equals(Operator.LN) || operator.equals(Operator.SQRT))
            belowBorder = upperBorder = 1;
        if(operator.equals(Operator.EXPT) || operator.equals(Operator.LOG))
            belowBorder = upperBorder = 2;

        int len = 0;
        ListItem<ArithmeticExpressionNode> workOperands = operands;
        while(workOperands!= null){
            len++;
            workOperands = workOperands.next;
        }
       if(len < belowBorder || len > upperBorder)
            throw new WrongNumberOfOperandsException(len,belowBorder,upperBorder);


        this.operator = operator;
        this.operands = operands;
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
        ListItem<ArithmeticExpressionNode> workOperands = operands;
        assert workOperands != null;
        MyNumber num = workOperands.key.evaluate(identifiers);
        workOperands = workOperands.next;
        String opera = operator.getSymbol();



        if(workOperands == null){
            if(opera.equals("-"))
                num = num.minus();
            if(opera.equals("/"))
                num = num.divide();
            if(opera.equals("exp"))
                num = num.exp();
            if(opera.equals("ln"))
                num = num.ln();
            if(opera.equals("sqrt"))
                num = num.sqrt();
        }
        while(workOperands!= null){
            if(opera.equals("+"))
                num = num.plus(workOperands.key.evaluate(identifiers));
            if(opera.equals("-"))
                num = num.minus(workOperands.key.evaluate(identifiers));
            if(opera.equals("*"))
                num = num.times(workOperands.key.evaluate(identifiers));
            if(opera.equals("/"))
                num = num.divide(workOperands.key.evaluate(identifiers));
            if(opera.equals("expt"))
                num = num.expt(workOperands.key.evaluate(identifiers));
            if(opera.equals("log"))
                num = num.log(workOperands.key.evaluate(identifiers));
            workOperands = workOperands.next;
        }
        return num;
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
        ListItem<ArithmeticExpressionNode>workOperands = operands;
        ListItem<ArithmeticExpressionNode> newOperands = new ListItem<>();
        ListItem<ArithmeticExpressionNode> newOperandsResult = newOperands;

        while(workOperands != null){
            newOperands.key = workOperands.key;
            newOperands.next = new ListItem<>();
            newOperands = newOperands.next;
            workOperands = workOperands.next;
        }
        return new OperationExpressionNode(operator,newOperandsResult);
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
