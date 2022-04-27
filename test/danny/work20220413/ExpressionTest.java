package danny.work20220413;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressionTest {

    private static Map<String, Double> testMap() {
        Map<String, Double> map = new HashMap<>();
        map.put("worm", 2.0);
        map.put("wormy", 3.0);
        map.put("egg", -2.0);
        return map;
    }

    @Test
    public void constantPositive() {
        Expression e = Expression.createExpression("3");
        assertEquals(3, e.evaluate(), 0.01);
        assertEquals(3, e.evaluate(testMap()), 0.01);
    }

    @Test
    public void constantNegative() {
        Expression e = Expression.createExpression("-3");
        assertEquals(-3, e.evaluate(), 0.01);
        assertEquals(-3, e.evaluate(testMap()), 0.01);
    }

    @Test
    public void constantDoubleNegative() {
        Expression e = Expression.createExpression("--3");
        assertEquals(3, e.evaluate(), 0.01);
        assertEquals(3, e.evaluate(testMap()), 0.01);
    }

    @Test
    public void constantBracket() {
        Expression e = Expression.createExpression("(3)");
        assertEquals(3, e.evaluate(), 0.01);
        assertEquals(3, e.evaluate(testMap()), 0.01);
    }

    @Test
    public void constantNegativeBracket() {
        Expression e = Expression.createExpression("(-3)");
        assertEquals(-3, e.evaluate(), 0.01);
        assertEquals(-3, e.evaluate(testMap()), 0.01);
    }

    @Test
    public void constantDoubleNegativeBracket() {
        Expression e1 = Expression.createExpression("(--3)");
        assertEquals(3, e1.evaluate(), 0.01);
        assertEquals(3, e1.evaluate(testMap()), 0.01);

        Expression e2 = Expression.createExpression("-(-3)");
        assertEquals(3, e2.evaluate(), 0.01);
        assertEquals(3, e2.evaluate(testMap()), 0.01);
    }

    @Test
    public void singleVariable() {
        Expression e1 = Expression.createExpression("worm");
        Expression e2 = Expression.createExpression("(worm)");
        Expression e3 = Expression.createExpression("-worm");
        Expression e4 = Expression.createExpression("-(worm)");
        Expression e5 = Expression.createExpression("- ( worm )");

        assertEquals(2, e1.evaluate(testMap()), 0.01);
        assertEquals(2, e2.evaluate(testMap()), 0.01);
        assertEquals(-2, e3.evaluate(testMap()), 0.01);
        assertEquals(-2, e4.evaluate(testMap()), 0.01);
        assertEquals(-2, e5.evaluate(testMap()), 0.01);
    }

    @Test
    public void addConstantAndVariable() {
        Expression e1 = Expression.createExpression("10 + worm");
        assertEquals(12, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void subtractConstantAndVariable() {
        Expression e1 = Expression.createExpression("10 - worm");
        assertEquals(8, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void multiplyConstantAndVariable() {
        Expression e1 = Expression.createExpression("10 * worm");
        assertEquals(20, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void divideConstantAndVariable() {
        Expression e1 = Expression.createExpression("10 / worm");
        assertEquals(5, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void similarVariableNames() {
        Expression e1 = Expression.createExpression("worm+wormy");
        Expression e2 = Expression.createExpression("wormy+worm");
        assertEquals(5, e1.evaluate(testMap()), 0.01);
        assertEquals(5, e2.evaluate(testMap()), 0.01);
    }

    @Test
    public void correctOperatorOrder() {
        Expression e1 = Expression.createExpression("5-10/2*4+1");
        // 5 - 20 + 1 = -14, NOT -16 - when only +/- do not do bidmas but just to in order
        assertEquals(-14, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void correctOperatorOrderBrackets() {
        Expression e1 = Expression.createExpression("(5-10)/2*(4+1)");
        assertEquals(-12.5, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void complexExpression() {
        // 5+ (2-(-2-1)* 3)
        Expression e1 = Expression.createExpression("5+ (worm-(egg-1)* wormy)");
        assertEquals(16, e1.evaluate(testMap()), 0.01);
    }

    @Test
    public void longExpression() {
        // 5+ (2-(-2-1)* 3)
        Expression e1 = Expression.createExpression("1-2+3*4/5/(1*2-3/4)+7-2--5");
        assertEquals(10.92, e1.evaluate(testMap()), 0.01);
    }

}