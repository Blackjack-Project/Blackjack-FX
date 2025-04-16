package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserView {

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void onReturn(ActionEvent event)
    {
        try
        {
            Scene loadMainScene = BlackjackApplication.getInstance().loadMainScene();
            BlackjackApplication.getInstance().getStage().setScene(loadMainScene);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSave(ActionEvent event)
    {

    }

}