module com.kyoxsu.blackjackfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jshell;
    requires java.net.http;


    opens com.kyoxsu.blackjackfx to javafx.fxml;
    exports com.kyoxsu.blackjackfx;
    exports com.kyoxsu.blackjackfx.views;
    opens com.kyoxsu.blackjackfx.views to javafx.fxml;
}