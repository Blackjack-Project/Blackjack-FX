module com.kyoxsu.blackjackfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kyoxsu.blackjackfx to javafx.fxml;
    exports com.kyoxsu.blackjackfx;
}