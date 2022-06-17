package h05.tree;

import h05.exception.*;
import h05.math.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class is used to parse an expression and build a tree out of it.
 *
 * @author Nhan Huynh
 */
public final class ExpressionTreeHandler {

    /**
     * Don't let anyone instantiate this class.
     */
    private ExpressionTreeHandler() {
    }

    /**
     * Builds an arithmetic expression tree from a string recursively.
     *
     * @param expression the string representation of the arithmetic expression to parse
     * @return the root node of the arithmetic expression tree
     * @throws BadOperationException        if the iterator has no more tokens
     * @throws ParenthesesMismatchException if the parentheses are mismatched
     * @throws UndefinedOperatorException   if the operator is not defined
     */
    public static ArithmeticExpressionNode buildRecursively(Iterator<String> expression) {
        if(!expression.hasNext())
            throw new BadOperationException("No expression");
        String firstExp = expression.next();
        if(isNumber(firstExp))
            return new LiteralExpressionNode(StringToNumber(firstExp));

        ArithmeticExpressionNode root = null;
        ListItem<ArithmeticExpressionNode> rootList = new ListItem<>();
        ListItem<ArithmeticExpressionNode> rootListBackup = null;
        if(firstExp.equals("(")){
            String nextExp = expression.next();
            if(isNumber(nextExp))
                throw new BadOperationException("wieso ist da eine Zahl .mp4");
            if(isOperator(nextExp))
                rootListBackup = rootList;
                rootList = recursiveHelperTwo(expression, rootList);

                root =new OperationExpressionNode(StringToOperator(nextExp),rootListBackup);
        }


        return root;
    }

    private static boolean isNumber(String expression){

        if(expression.matches("[-+]?[0-9]+"))     //Integer
            return true;
        if(expression.matches("[-+]?[0-9]+\\.[0-9]+"))      //Integer
            return true;
        if(expression.matches("[-+]?[0-9]+\\/[-+]?[0-9]+"))  //Integer
            return true;
        return false;
    }

    private static MyNumber StringToNumber(String expression){

        if(expression.matches("[-+]?[0-9]+"))     //Integer
            return new MyInteger(new BigInteger(expression));
        if(expression.matches("[-+]?[0-9]+\\.[0-9]+"))      //Real
            return  new MyReal(new BigDecimal(expression));
        if(expression.matches("[-+]?[0-9]+\\/[-+]?[0-9]+")){        //Rational

            String[] arr = expression.split("\\/");
            BigInteger num = new BigInteger(arr[0]);
            BigInteger denom = new BigInteger(arr[1]);

            return  new MyRational(new Rational(num,denom));
        }
        throw new IllegalIdentifierExceptions(expression);
    }
    private static boolean isOperator(String operator) {
        for(Operator i:Operator.values())
            if(i.getSymbol().equals(operator))
                return true;
        throw new UndefinedOperatorException(operator);
    }
    private static Operator StringToOperator(String operator) {
        for(Operator i:Operator.values())
            if(i.getSymbol().equals(operator))
                return i;
        throw new UndefinedOperatorException(operator);
    }
    private static ArithmeticExpressionNode recursiveHelperOne(Iterator<String> expression) {

        String s = expression.next();
        Operator op = StringToOperator(s);
        ListItem<ArithmeticExpressionNode> rootList = new ListItem<>();
        ListItem<ArithmeticExpressionNode> rootListBackup = rootList;
        recursiveHelperTwo(expression, rootList);
        return new OperationExpressionNode(op,rootListBackup);
    }
    private static ListItem<ArithmeticExpressionNode> recursiveHelperTwo(Iterator<String> expression,  ListItem<ArithmeticExpressionNode> rootList) {


        String nextExp = expression.next();
        while(expression.hasNext()) {
            if (nextExp.equals(")")){
                return null;
            }
            if (nextExp.equals("(")) {
                rootList.key = recursiveHelperOne(expression);
                rootList.next = new ListItem<>();
                rootList = rootList.next;
            }
            while (isNumber(nextExp)) {
                rootList.key = new LiteralExpressionNode(StringToNumber(nextExp));
                nextExp = expression.next();

                if(isNumber(nextExp)){
                    rootList.next = new ListItem<>();
                    rootList = rootList.next;
                }
            }
        }
        return null;
    }


    /**
     * Builds an arithmetic expression tree from a string iteratively.
     *
     * @param expression the string representation of the arithmetic expression to parse
     * @return the root node of the arithmetic expression tree
     * @throws BadOperationException        if the iterator has no more tokens
     * @throws ParenthesesMismatchException if the parentheses are mismatched
     * @throws UndefinedOperatorException   if the operator is not defined
     */
    public static ArithmeticExpressionNode buildIteratively(Iterator<String> expression) {
        throw new RuntimeException("H4.2 - not implemented"); // TODO: H4.2 - remove if implemented
    }

    /**
     * Reconstructs the string representation of the arithmetic expression tree.
     *
     * @param root the root node of the arithmetic expression tree
     * @return the string representation of the arithmetic expression tree
     */
    public static List<String> reconstruct(ArithmeticExpressionNode root) {
        throw new RuntimeException("H4.3 - not implemented"); // TODO: H4.3 - remove if implemented
    }
}
