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

public class UserCreationView {

    @FXML
    private PasswordField confirmPasswordField;

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
        // ---
        confirmPasswordField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
    }

    @FXML
    void onCreateUser(ActionEvent event)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword= confirmPasswordField.getText();

        Player player = new Player(username, password);

        // --- Si l'utilisateur n'existe pas encore / le nom d'utilisateur n'est pas déjà prit
        boolean inDb = player.isPlayerInDb();
        if (!inDb)
        {
            // --- Si les deux mot de passe correspondent
            if (password.equals(confirmPassword))
            {
                player.writePlayer();

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
            else
            {
                String message = "[ERROR] - Veuillez saisir le même mot de passe dans les deux champs";
                errorLabel.setText(message);
            }
        }
        else
        {
            String message = "[ERROR] - Le nom d'utilisateur est déjà prit";
            errorLabel.setText(message);
        }
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