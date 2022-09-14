package vebjorbl.calc;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;

public class CalcController {

    private Calc calc;

    public CalcController() {
        calc = new Calc(0.0, 0.0, 0.0);
    }

    public Calc getCalc() {
        return calc;
    }

    public void setCalc(Calc calc) {
        this.calc = calc;
        updateOperandsView();
    }

    @FXML
    private ListView<Double> operandsView;

    @FXML
    private Label operandView;

    @FXML
    void initialize() {
        updateOperandsView();
    }

    private void updateOperandsView() {
        List<Double> operands = operandsView.getItems();
        operands.clear();
        int elementCount = Math.min(calc.getOperandCount(), 3);
        for (int i = 0; i < elementCount; i++) {
            operands.add(calc.peekOperand(elementCount - i - 1));
        }
    }

    private String getOperandString() {
        return operandView.getText();
    }

    private boolean hasOperand() {
        return ! getOperandString().isBlank();
    }

    private double getOperand() {
        return Double.valueOf(operandView.getText());
    }
    
    private void setOperand(String operandString) {
        operandView.setText(operandString);
    }

    @FXML
    void handleEnter() {
        if (hasOperand()) {
            calc.pushOperand(getOperand());
        } else {
            calc.dup();
        }
        setOperand("");
        updateOperandsView();
    }

    private void appendToOperand(String s) {
        setOperand(getOperandString().concat(s));
        updateOperandsView();
    }

    @FXML
    void handleDigit(ActionEvent ae) {
        if (ae.getSource() instanceof Labeled l) {
            appendToOperand(l.getText());
        }
    }

    @FXML
    void handlePoint() {
        var operandString = getOperandString();
        String input = new String("");
        if (operandString.contains(".")) {
            input = operandString.substring(0, operandString.indexOf(".")+1);
        } else {
            input = operandString.concat(".");
        }
        setOperand(input);
        updateOperandsView();
    }

    @FXML
    void handleClear() {
        setOperand("");
        updateOperandsView();
    }

    @FXML
    void handleSwap() {
        // TODO clear operand
        calc.swap();
        updateOperandsView();
    }

    private void performOperation(UnaryOperator<Double> op) {
        // TODO
        calc.performOperation(op);
        setOperand("");
        updateOperandsView();
    }

    private void performOperation(boolean swap, BinaryOperator<Double> op) {
        if (hasOperand()) {
            // TODO push operand first
            calc.pushOperand(this.getOperand());
        }
        // TODO perform operation, but swap first if needed
        if (swap){
            calc.swap();
        }
        calc.performOperation(op);
        setOperand("");
        updateOperandsView();
    }

    @FXML
    void handleOpAdd() {
        // TODO
        performOperation(true, (d1,d2) -> d1+d2);
    }

    @FXML
    void handleOpSub() {
        // TODO
        performOperation(false, (d1,d2) -> d1-d2);
    }

    @FXML
    void handleOpMult() {
        // TODO
        performOperation(false, (d1,d2) -> d1*d2);
    }

    @FXML
    void handleOpSquar() {
        performOperation(operation -> Math.sqrt(operation.doubleValue()));
    }

    @FXML
    void handleOpDivid() {
        boolean swap = (calc.peekOperand() == 0);
        performOperation(swap, (d1, d2) -> d1 / d2);
    }

    @FXML
    void handleOpPi() {
        // Makes sure that one cant have several points
        performOperation(operation -> Math.PI);
    }

}
