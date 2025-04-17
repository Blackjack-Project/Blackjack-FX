package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import com.kyoxsu.blackjackfx.models.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.http.WebSocket;

public class LoginView
{
    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void initialize()
    {
        usernameField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
        // ---
        passwordField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @FXML
    void onLogin(ActionEvent event)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // --- Vérification de l'existence & du mot de passe
        Player player = Player.login(username, password);

        // --- Si le joueur à bien été trouvé
        if (player != null)
        {
            BlackjackApplication.player = player;

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
        else
        {
            // --- Si on ne trouve pas d'utilisateur ou/et que le mot de passe est incorrect
            String message = "[ERROR] - Utilisateur introuvable et/ou mot de passe incorrect";
            errorLabel.setText(message);
        }
    }
}

