package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginView
{
    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void onCreateUser(ActionEvent event)
    {
        try
        {
            Scene loadUserCreationScene = BlackjackApplication.getInstance().loadUserCreationScene();
            BlackjackApplication.getInstance().getStage().setScene(loadUserCreationScene);
            BlackjackApplication.getInstance().getStage().setMaximized(true);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLogin(ActionEvent event)
    {
        // TODO : Tester si les infos du joueurs sont correctes
        // -> Utilisation de la méthode de login

        // --- Changement de fenêtre
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

