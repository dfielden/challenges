package danny.work20220503;

public class ExpressionTree {

    static abstract class Expr {
        abstract double eval();
    }

    static final class ConstExpr extends Expr {
        private final double num;

        ConstExpr(double num) {
            this.num = num;
        }

        @Override
        double eval() {
            return num;
        }
    }

    static final class MathExpr extends Expr {
        private final ConstExpr expr1;
        private final ConstExpr expr2;
        private final char operator;

        MathExpr(ConstExpr expr1, ConstExpr expr2, char operator) {
            this.expr1 = expr1;
            this.expr2 = expr2;

            if (!(operator == '/' || operator == '*' || operator == '+' || operator == '-')) {
                throw new IllegalArgumentException("MathExpr must be created with a valid operator (/, *, +, -).");
            }
            this.operator = operator;
        }

        @Override
        double eval() {
            if (operator == '/') {
                return expr1.eval() / expr2.eval();
            }
            if (operator == '*') {
                return expr1.eval() * expr2.eval();
            }
            if (operator == '+') {
                return expr1.eval() + expr2.eval();
            }
            if (operator == '-') {
                return expr1.eval() - expr2.eval();
            }
            throw new IllegalStateException("Cannot evaluate. MathExpr created with invalid operator.");
        }
    }

}
