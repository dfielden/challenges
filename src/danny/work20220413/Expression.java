package danny.work20220413;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Expression {
    public static void main(String[] args) {
        Expression e = createExpression("2 * (5 + (foo-1))");
        System.out.println(e);
        String exp = removeSpaces(e.getExpression());
        List<String> expToList = new ArrayList<>();
        expToList.add(exp);
        List<String> bracketGroups = splitItemsAtFirstBracket(expToList);
        while (true) {
            if (bracketGroups.size() == expToList.size()) {
                break;
            } else {
                expToList = bracketGroups;
                bracketGroups = splitItemsAtFirstBracket(expToList);
            }
        }
        System.out.println(bracketGroups);
    }
    private final String expression;

    private Expression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }

    public static Expression createExpression(String s) {
        return new Expression(s);
    }

    public double evaluate(Map<String, Double> map) {
        return 0.0;
    }

    private static List<String> splitItemsAtFirstBracket(List<String> input) {
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
        return output;
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private List<String> convertVariables(Map<String, Double> variables, List<String> splitExpression) {
        for (int i = 0; i < splitExpression.size(); i++) {

        }
        return splitExpression;
    }

    static String[] groupBrackets(String s) {
        //String[] bracketGroups = s.split('(');
        return new String[2];
    }

    static String removeSpaces(String s) {
        return s.replaceAll("\\s+","");
    }

    @Override
    public String toString() {
        return this.expression;
    }
}
