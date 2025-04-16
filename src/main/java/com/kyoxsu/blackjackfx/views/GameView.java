package com.kyoxsu.blackjackfx.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.kyoxsu.blackjackfx.BlackjackApplication;
import java.io.IOException;
import javafx.scene.Scene;

public class GameView {

    @FXML
    private Label bankGameLabel;

    @FXML
    private TextField betField;

    @FXML
    private TextArea gameLog;

    @FXML
    private TextField playerGameField;

    @FXML
    private ListView<?> playerList;

    @FXML
    private Label playerTurnLabel;

    @FXML
    private TextField soldField;

    @FXML
    private Label totalBetField;

    @FXML
    void onAssure(ActionEvent event) {

    }

    @FXML
    void onDistribute(ActionEvent event) {

    }

    @FXML
    void onDouble(ActionEvent event) {

    }

    @FXML
    void onLeave(ActionEvent event)
    {
        try
        {
            Scene loadMainScene = BlackjackApplication.getInstance().loadMainScene();
            BlackjackApplication.getInstance().getStage().setScene(loadMainScene);
            BlackjackApplication.getInstance().getStage().setMaximized(true);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSplit(ActionEvent event) {

    }

    @FXML
    void onValidate(ActionEvent event) {

    }

}