package danny.work20220413;

import java.util.*;

public final class Expression {
    private final String expression;
    final static char[] operatorArr = new char[]{'/', '*', '+', '-'};;

    private Expression(String expression) {
        this.expression = expression;
    }

    public static void main(String[] args) {
//        Expression e = createExpression("hi * (5 + (foo-1)) - foo");
        Expression e = createExpression("6/-2+ (10--2)");

        Map<String, Double> map = new HashMap<>();
        map.put("hi", 2.0);
        map.put("foo", 5.0);
        e.evaluate(map);
    }

    public String getExpression() {
        return this.expression;
    }

    public static Expression createExpression(String s) {
        return new Expression(s);
    }

    public double evaluate(Map<String, Double> map) {
        System.out.println("Expression: " + expression);
        String s = removeSpaces(expression);
        s = convertVariablesToDouble(map, s);
        System.out.println("Converted variables: " + s);
        s = evaluateBracketedGroups(s);
        System.out.println("Brackets evaluated: " + s);
        s = evaluateSimpleExpression(s);
        System.out.println("Result: " + s);
        return Double.parseDouble(s);
    }

    @Override
    public String toString() {
        return this.expression;
    }

    /**
     Iterates over a String expression that may contain bracketed groups. When a bracketed group containing no nested
     brackets is found, the bracketed group is evaluated substituted with the result of the evaluation. Iteration
     continues until no brackets are found at which point the expression is returned.
     */
    private static String evaluateBracketedGroups(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                for (int j = i + 1; j < expression.length(); j++) {
                    char c1 = expression.charAt(j);
                    // If there is nested bracketed expression inside the current bracketed group, break and
                    // continue the outer loop to reach the inner expression.
                    if (c1 == '(') {
                        break;
                    }
                    // We have found the closing bracket of the sub-expression.
                    if (c1 == ')') {
                        String subExpression = expression.substring(i, j+1);
                        // Remove brackets from sub-expression.
                        subExpression = removeBrackets(subExpression);
                        // Evaluate the sub-expression.
                        String evaluation = evaluateSimpleExpression(subExpression);
                        // substitute original bracketed component with the evaluation output.
                        String result = expression.substring(0, i);
                        result += evaluation;
                        result += expression.substring(j + 1);
                        // Recursively check the result until no more brackets are found.
                        return evaluateBracketedGroups(result);
                    }
                }
            }
        }
        // We have reached the end of the expression without finding any brackets so return the expression.
        return expression;

    }

    /**
     Takes a simple arithmetic expression, containing no brackets, and iterates through it, splitting it at each
     arithmetic operator, adding both the components either side of the operator and the operator to a list which
     is returned.
     For example the expression "6*2-32" will be converted to the following String list: ["6", "*", "2", "-", "32"].
     */
    private static List<String> simpleExpressionToList(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (isArithmeticOperator(s.charAt(i)) && i > 0) {
                list.add(sb.toString());
                list.add(String.valueOf(s.charAt(i)));
                sb.setLength(0);
                // Check whether multiplying or dividing by a negative number.
                if (isArithmeticOperator(s.charAt(i+1))) {
                    sb.append(s.charAt(i+1));
                    i++;
                }
            } else {
                sb.append(s.charAt(i));
            }
        }
        list.add(sb.toString());
        return list;
    }

    /**
     Takes a list of String items and iterates through the list, concatenating each component and returning the
     concatenated String.
     For example the String list ["6", "*", "2", "-", "32"] will be converted to the String "6*2-32".
     */
    private static String listToSimpleExpression(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     Takes a list representation of a simple arithmetic, non-bracketed, expression (e.g. as returned by the method
     simpleExpressionToList(String s) and returns a String representation of the evaluation.
     */
    private static String evaluateSimpleExpression(String input) {
        System.out.println("Evaluating simple expression: " + input);
        input = maybeRemoveDoubleSumOperator(input);
        System.out.println("After check for double operator: " + input);
        List<String> inputAsList = simpleExpressionToList(input);
        System.out.println(inputAsList);
        List<String> newList = new ArrayList<>();

        if (inputAsList.size() == 1) {
            return listToSimpleExpression(inputAsList);
        }

        // iterate over the expression for each operator in BIDMAS order
        for (char c : operatorArr) {
            for (int j = 0; j < inputAsList.size(); j++) {
                // Only want to be true for negative operators, NOT negative numbers
                if (inputAsList.get(j).charAt(0) == c && inputAsList.get(j).length() == 1) {
                    double d = performArithmeticOperation(c, Double.parseDouble(inputAsList.get(j - 1)), Double.parseDouble(inputAsList.get(j + 1)));
                    newList.addAll(inputAsList.subList(0, j - 1));
                    newList.add(String.valueOf(d));
                    newList.addAll(inputAsList.subList(j + 2, inputAsList.size()));
                    return evaluateSimpleExpression(listToSimpleExpression(newList));
                }
            }
        }
        return input;
    }


    /**
     Returns true if the supplied char is an arithmetic operator (+, -, *, /), otherwise returns false.
     */
    private static boolean isArithmeticOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static String convertVariablesToDouble(Map<String, Double> variables, String s) {
        for (String key : variables.keySet()) {
            if (s.contains(key)) {
                s = s.replaceAll(key, String.valueOf(variables.get(key)));
            }
        }
        return s;
    }

    private static String removeSpaces(String s) {
        return s.replaceAll("\\s+","");
    }

    private static String removeBrackets(String s) {
        return s.replaceAll("[()]","");
    }

    /**
     Iterates through the passed-in String. If two sum arithmetic operators (+ or -) are found adjacent to each other, they are
     substituted for a single arithmetic operator (+- to -. -- to +). If no double operator is found, the passed-in
     String is returned unaltered.
     */
    private static String maybeRemoveDoubleSumOperator(String expression) {
        if (expression.length() < 2) {
            return expression;
        }
        for (int i = 0; i < expression.length() - 2; i++) {
            char left = expression.charAt(i);
            char right = expression.charAt(i + 1);

            if (left == '-' && right == '-') {
                String expressionBuilder = expression.substring(0, i) + "+" + expression.substring(i + 2);
                // check for any further double arithmetic operators.
                return maybeRemoveDoubleSumOperator(expressionBuilder);
            } else if (left == '+' && right == '-') {
                // check for any further double arithmetic operators.
                String expressionBuilder = expression.substring(0, i) + "-" + expression.substring(i + 2);
                return maybeRemoveDoubleSumOperator(expressionBuilder);
            }
        }
        return expression;
    }

    private static double performArithmeticOperation(char operator, double a, double b) {
        if (!isArithmeticOperator(operator)) {
            throw new IllegalArgumentException("Must pass in a valid arithmetic operator (/, *, +, -)");
        } else if (operator == '/') {
            return a / b;
        } else if (operator == '*') {
            return a * b;
        } else if (operator == '+') {
            return a + b;
        } else {
            return a - b;
        }
    }
}
