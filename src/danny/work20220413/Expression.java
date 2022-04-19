package danny.work20220413;

import java.util.*;

public final class Expression {
    private final String expression;

    private Expression(String expression) {
        this.expression = expression;
    }

    public static void main(String[] args) {
        Expression e = createExpression("2 * (5 + (foo-1)) - foo");
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
        s = convertVariables(map, s);
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

    private static String evaluateBracketedGroups(String expression) {
        //List<String> output = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                for (int j = i + 1; j < expression.length(); j++) {
                    char c1 = expression.charAt(j);
                    // if there is an inner bracketed expression, break and continue outer loop to reach the inner expression
                    if (c1 == '(') {
                        break;
                    }
                    // we have found the closing bracket of the sub-expression
                    if (c1 == ')') {
                        String subExpression = expression.substring(i, j+1);
                        // remove brackets.
                        subExpression = removeBrackets(subExpression);
                        // evaluate simple expression list.
                        String evaluation = evaluateSimpleExpression(subExpression);
                        // substitute original bracketed component with the evaluation.
                        String result = expression.substring(0, i);
                        result += evaluation;
                        result += expression.substring(j + 1);
                        // recursively check the result until no more brackets are found.
                        return evaluateBracketedGroups(result);
                    }
                }
            }
        }
        // We have reached the end of the expression without finding any brackets so return the expression.
        return expression;

    }

    /**
     Takes a simple arithmetic expression, containing no brackets, iterates through the expression adding each number
     component or arithmetic operator to a List<String> which is returned.
     */
    private static List<String> simpleExpressionToList(String s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (isOperator(s.charAt(i))) {
                list.add(sb.toString());
                list.add(String.valueOf(s.charAt(i)));
                sb.setLength(0);
            } else {
                sb.append(s.charAt(i));
            }
        }
        list.add(sb.toString());
        return list;
    }

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
        List<String> inputAsList = simpleExpressionToList(input);
        List<String> newList = new ArrayList<>();
        //String evaluatedInput = "";
        //System.out.println(list);

        for (int i = 0; i < inputAsList.size(); i++) {
            if (inputAsList.get(i).equals("/")) {
                double d = divide(Double.parseDouble(inputAsList.get(i-1)), Double.parseDouble(inputAsList.get(i+1)));
//                evaluatedInput += input.substring(0, i-1);
//                evaluatedInput += String.valueOf(d);
//                evaluatedInput += input.substring(i+2, inputAsList.size());
//                return evaluateSimpleExpression(evaluatedInput);
                newList.addAll(inputAsList.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(inputAsList.subList(i+2, inputAsList.size()));
                return evaluateSimpleExpression(listToSimpleExpression(newList));
            }
        }
        for (int i = 0; i < inputAsList.size(); i++) {
            if (inputAsList.get(i).equals("*")) {
                double d = multiply(Double.parseDouble(inputAsList.get(i-1)), Double.parseDouble(inputAsList.get(i+1)));
                newList.addAll(inputAsList.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(inputAsList.subList(i+2, inputAsList.size()));
                return evaluateSimpleExpression(listToSimpleExpression(newList));
            }
        }
        for (int i = 0; i < inputAsList.size(); i++) {
            if (inputAsList.get(i).equals("+")) {
                double d = add(Double.parseDouble(inputAsList.get(i-1)), Double.parseDouble(inputAsList.get(i+1)));
                newList.addAll(inputAsList.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(inputAsList.subList(i+2, inputAsList.size()));
                return evaluateSimpleExpression(listToSimpleExpression(newList));
            }
        }
        for (int i = 0; i < inputAsList.size(); i++) {
            if (inputAsList.get(i).equals("-")) {
                double d = subtract(Double.parseDouble(inputAsList.get(i-1)), Double.parseDouble(inputAsList.get(i+1)));
                newList.addAll(inputAsList.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(inputAsList.subList(i+2, inputAsList.size()));
                return evaluateSimpleExpression(listToSimpleExpression(newList));
            }
        }
        System.out.println("Evaluation finished - returning: " + input);
        return input;
    }


    /**
     Returns true if the supplied char is an arithmetic operator (+, -, *, /), otherwise returns false.
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static String convertVariables(Map<String, Double> variables, String s) {
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

    private static double divide(double a, double b) {
        return a / b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

}
