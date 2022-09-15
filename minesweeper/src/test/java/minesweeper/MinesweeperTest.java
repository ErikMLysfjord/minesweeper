package minesweeper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;




public class MinesweeperTest {

    private static void checkCalc(Minesweeper calc, double... operands) {
        Assertions.assertEquals(operands.length, calc.getOperandCount(), "Wrong operand count");
        for (int i = 0; i < operands.length; i++) {
            Assertions.assertEquals(operands[i], calc.peekOperand(i), "Wrong value at #" + i + " of operand stack");
        }
    }

    @Test
    public void testCalc() {
        checkCalc(new Minesweeper());
        checkCalc(new Minesweeper(1.0), 1.0);
        checkCalc(new Minesweeper(3.14, 1.0), 1.0, 3.14);
    }

    @Test
    public void testPushOperand() {
        Minesweeper calc = new Minesweeper();
        calc.pushOperand(1.0);
        checkCalc(calc, 1.0);
        calc.pushOperand(3.14);
        checkCalc(calc, 3.14, 1.0);
    }

    @Test
    public void testPeekOperand() {
        Minesweeper calc = new Minesweeper(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.peekOperand());
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Minesweeper().peekOperand());
    }

    @Test
    public void testPeekOperandN() {
        Minesweeper calc = new Minesweeper(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.peekOperand(0));
        Assertions.assertEquals(1.0, calc.peekOperand(1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> calc.peekOperand(2));
    }

    @Test
    public void testPopOperand() {
        Minesweeper calc = new Minesweeper(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.popOperand());
        checkCalc(calc, 1.0);
        Assertions.assertEquals(1.0, calc.popOperand());
        checkCalc(calc);
    }

    @Test
    public void testPopOperand_emptyStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper().popOperand());
    }

    @Test
    public void testPerformOperation1() {
        Minesweeper calc = new Minesweeper(1.0);
        Assertions.assertEquals(-1.0, calc.performOperation(n -> -n));
        checkCalc(calc, -1.0);
    }

    @Test
    public void testPerformOperation1_emptyOperandStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper().performOperation(n -> -n));
    }


    @Test
    public void testPerformOperation2() {
        Minesweeper calc = new Minesweeper(1.0, 3.0);
        Assertions.assertEquals(-2.0, calc.performOperation((n1, n2) -> n1 - n2));
        checkCalc(calc, -2.0);
    }

    @Test
    public void testPerformOperation2_lessThanTwoOperands() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper(1.0).performOperation((n1, n2) -> n1 - n2));
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper().performOperation((n1, n2) -> n1 - n2));
    }

    @Test
    public void testSwap() {
        Minesweeper calc = new Minesweeper(1.0, 3.14);
        checkCalc(calc, 3.14, 1.0);
        calc.swap();
        checkCalc(calc, 1.0, 3.14);
        calc.swap();
        checkCalc(calc, 3.14, 1.0);
    }

    @Test
    public void testSwap_lessThanTwoOperands() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper(1.0).swap());
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper().swap());
    }

    @Test
    public void testDup() {
        Minesweeper calc = new Minesweeper(1.0, 3.14);
        Assertions.assertEquals(3.14, calc.popOperand());
        checkCalc(calc, 1.0);
        Assertions.assertEquals(1.0, calc.popOperand());
        checkCalc(calc);
    }

    @Test
    public void testDup_emptyOperandStack() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Minesweeper().dup());
    }
}
