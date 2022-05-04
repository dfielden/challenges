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

    static final class DivExpr extends Expr {
        private final ConstExpr expr1;
        private final ConstExpr expr2;

        DivExpr(ConstExpr expr1, ConstExpr expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        double eval() {
            return expr1.eval() / expr2.eval();
        }
    }

    static final class MultExpr extends Expr {
        private final ConstExpr expr1;
        private final ConstExpr expr2;

        MultExpr(ConstExpr expr1, ConstExpr expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        double eval() {
            return expr1.eval() * expr2.eval();
        }
    }

    static final class AddExpr extends Expr {
        private final ConstExpr expr1;
        private final ConstExpr expr2;

        AddExpr(ConstExpr expr1, ConstExpr expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        double eval() {
            return expr1.eval() + expr2.eval();
        }
    }

    static final class SubExpr extends Expr {
        private final ConstExpr expr1;
        private final ConstExpr expr2;

        SubExpr(ConstExpr expr1, ConstExpr expr2) {
            this.expr1 = expr1;
            this.expr2 = expr2;
        }

        @Override
        double eval() {
            return expr1.eval() - expr2.eval();
        }
    }
}
