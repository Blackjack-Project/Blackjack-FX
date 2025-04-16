package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.io.IOException;

public class CreateRoomView
{
    @FXML
    private Label errorLabel;

    @FXML
    private Slider playerNumberSlider;

    @FXML
    private TextField roomNameField;

    @FXML
    private PasswordField roomPasswordField;

    @FXML
    private ChoiceBox<?> visibilityChoiceBox;

    @FXML
    void onCreateRoom(ActionEvent event)
    {
        try
        {
            Scene loadRoomScene = BlackjackApplication.getInstance().loadRoomScene();
            BlackjackApplication.getInstance().getStage().setScene(loadRoomScene);
            BlackjackApplication.getInstance().getStage().setMaximized(true);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onReturn(ActionEvent event)
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

}