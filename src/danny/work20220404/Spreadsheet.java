package danny.work20220404;

import java.util.Map;
import java.util.HashMap;

public final class Spreadsheet {

    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.set(1, "A", "5"); // 5
        spreadsheet.set(2, "A", "=10 + A1"); // 15
        spreadsheet.set(3, "A", "=50"); // 50
        spreadsheet.set(4, "A", "=A3 + A2"); // 65
        spreadsheet.set(5, "A", "=A4 + A1"); // 70

        System.out.println(spreadsheet.getComputed(1, "A"));
        System.out.println(spreadsheet.getComputed(2, "A"));
        System.out.println(spreadsheet.getComputed(3, "A"));
        System.out.println(spreadsheet.getComputed(4, "A"));
        System.out.println(spreadsheet.getComputed(5, "A"));
        System.out.println(spreadsheet.getComputed(6, "A"));

    }

    private final Map<Integer, Map<String, String>> rows = new HashMap<>();

    public void set(int row, String column, String value) {
        Map<String, String> cols = rows.get(row);
        if (cols == null && value.equals("")) {
            return;
        }
        if (cols == null) {
            cols = new HashMap<>();
            rows.put(row, cols);
        }
        cols.put(column, value);
    }

    public String getRaw(int row, String column) {
        Map<String, String> cols = rows.get(row);
        if (cols == null) {
            return "";
        }
        String result = cols.get(column);
        if (result == null) {
            return "";
        }
        return result;
    }

    public String getComputed(int row, String column) {
        String rawValue = getRaw(row, column);
        if (rawValue.length() == 0) {
            return "";
        }
        if (rawValue.charAt(0) == '=') {
            // Needs computation
            String expression = rawValue.substring(1);
            // Remove spaces
            expression = expression.replaceAll("\\s+","");

            if (expression.contains("+")) {
                // Is it an addition of two other terms e.g. "1.3 + A2"?
                String[] arr = expression.split("\\+");
                // Calculate value of each component separately.
                String calcLeft = computeSingleExpr(arr[0]);
                String calcRight = computeSingleExpr(arr[1]);
                // Sum the double values of the two results.
                double combinedResult = Double.parseDouble(calcLeft) + Double.parseDouble(calcRight);
                return Double.toString(combinedResult);
            } else {
                return computeSingleExpr(expression);
            }
        } else {
            // Does not need computation
            return rawValue;
        }
    }

    /** Resolves the actual double value of a single token, like "1.5" or "A4". */
    private String computeSingleExpr(String expression) {
        try {
            // Is it a simple value e.g. "1.3"?
            Double.parseDouble(expression);
            return expression;
        } catch (NumberFormatException e) {
            // Is it a simple reference e.g. "A2"?
            String[] addressComponents = getCoordinatesFromAddress(expression);
            return getComputed(Integer.parseInt(addressComponents[0]), addressComponents[1]);
        }
    }

    /** Turns a cell reference like "A50" into an array like ["A", "50"]. */
    private String[] getCoordinatesFromAddress(String address) {
        // Returns a String array of length two. Array[0] is the row number. Array[1] is the Column letter.
        StringBuilder sb = new StringBuilder();
        int pointer = 0;
        while (address.charAt(pointer) >= 'A' && address.charAt(pointer) <= 'Z') {
            sb.append(address.charAt(pointer));
            pointer += 1;
        }
        String[] result = new String[2];
        result[0] = address.substring(pointer);
        result[1] = sb.toString();
        return result;
    }

}
