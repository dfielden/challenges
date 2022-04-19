package danny.work20220413;

import java.util.*;

public final class Expression {
    private final String expression;

    private Expression(String expression) {
        this.expression = expression;
    }

    public static void main(String[] args) {
        Expression e = createExpression("2 * (5 + (foo-1)) - foo");
        System.out.println(e);
        Map<String, Double> map = new HashMap<>();
        map.put("hi", 2.0);
        map.put("foo", 5.0);
        e.evaluate(map);
        List<String> list = simpleExpressionToList("5+2*4-1");
        System.out.println(evaluateSimpleExpression(list));
    }

    public String getExpression() {
        return this.expression;
    }

    public static Expression createExpression(String s) {
        return new Expression(s);
    }

    public double evaluate(Map<String, Double> map) {
        String s = removeSpaces(expression);
        s = convertVariables(map, s);
        System.out.println(s);
        List<String> bracketedGroups = separateExpressionByBrackets(Collections.singletonList(s));
        System.out.println(bracketedGroups);
        return 0.0;
    }

    @Override
    public String toString() {
        return this.expression;
    }

    private static List<String> separateExpressionByBrackets(List<String> input) {
        List<String> output = new ArrayList<>();
        for (String subExp : input) {
            for (int j = 0; j < subExp.length(); j++) {
                char c = subExp.charAt(j);
                if (c == '(' && j > 0) {
                    output.add(subExp.substring(0, j));
                    output.add(subExp.substring(j));
                    break;
                }
                if (j == subExp.length() - 1) {
                    output.add(subExp);
                }
            }
        }
        if (input.size() == output.size()) {
            // no splitting has occurred therefore there are no further bracketed groups
            // remove brackets from each item in output list
            for (int i = 0; i < output.size(); i++) {
                String s = output.get(i);
                output.set(i, removeBrackets(s));
            }
            return output;
        } else {
            // splitting has occurred therefore need to check if any further bracket groups
            return separateExpressionByBrackets(output);
        }
    }

    private static String evaluateSimpleExpression(List<String> list) {
        // 5 - 2 * 3 - 2 // -3
        List<String> newList = new ArrayList<>();
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("/")) {
                double d = divide(Double.parseDouble(list.get(i-1)), Double.parseDouble(list.get(i+1)));
                newList.addAll(list.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(list.subList(i+2, list.size()));
                return evaluateSimpleExpression(newList);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("*")) {
                double d = multiply(Double.parseDouble(list.get(i-1)), Double.parseDouble(list.get(i+1)));
                newList.addAll(list.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(list.subList(i+2, list.size()));
                return evaluateSimpleExpression(newList);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("+")) {
                double d = add(Double.parseDouble(list.get(i-1)), Double.parseDouble(list.get(i+1)));
                newList.addAll(list.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(list.subList(i+2, list.size()));
                return evaluateSimpleExpression(newList);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("-")) {
                double d = subtract(Double.parseDouble(list.get(i-1)), Double.parseDouble(list.get(i+1)));
                newList.addAll(list.subList(0, i-1));
                newList.add(String.valueOf(d));
                newList.addAll(list.subList(i+2, list.size()));
                return evaluateSimpleExpression(newList);
            }
        }
        return list.get(0);
    }

    public static List<String> simpleExpressionToList(String s) {
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
