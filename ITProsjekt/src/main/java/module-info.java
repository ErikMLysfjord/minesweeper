module vebjorbl.calc {
    requires javafx.controls;
    requires javafx.fxml;

    opens vebjorbl.calc to javafx.graphics, javafx.fxml;
}
