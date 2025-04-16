package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserCreationView {

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void onCreateUser(ActionEvent event)
    {
        // TODO : Système de création d'un utilisateur
    }

    @FXML
    void onReturn(ActionEvent event)
    {
        try
        {
            Scene loadLoginScene = BlackjackApplication.getInstance().loadLoginScene();
            BlackjackApplication.getInstance().getStage().setScene(loadLoginScene);
            BlackjackApplication.getInstance().getStage().setMaximized(true);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}