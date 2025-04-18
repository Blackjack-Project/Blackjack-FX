package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import com.kyoxsu.blackjackfx.binders.PlayerBinder;
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

public class UserView
{
    private PlayerBinder playerBinder;
    private String oldUsername;
    private String oldPassword;
    // ---
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
        usernameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
        // ---
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
        // ---
        confirmPasswordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                errorLabel.setText("");
            }
        });
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void setPlayerBinder(PlayerBinder playerBinder)
    {
        this.playerBinder = playerBinder;

        String username = playerBinder.getUsername();
        String password = playerBinder.getPassword();

        // --- Première initialisation des informations de l'utilisateur
        usernameField.setText(username);
        passwordField.setText(password);
        confirmPasswordField.setText(password);

        // --- Sauvegarde de ces informations
        oldUsername = username;
        oldPassword = password;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @FXML
    void onSave(ActionEvent event)
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        Player player = new Player(username, password);

        // --- Vérification si le nom d'utilisateur est le même
        if (username.equals(oldUsername))
        {
            // --- Si les deux mot de passe correspondent
            if (password.equals(confirmPassword))
            {
                BlackjackApplication.player.setPassword(password);
                BlackjackApplication.player.writePlayer();
                // ---
                String message = "[OK] - Modifications sauvegardés avec succès";
                errorLabel.setStyle("-fx-text-fill: green;");
                errorLabel.setText(message);
            }
            else
            {
                String message = "[ERROR] - Veuillez saisir le même mot de passe dans les deux champs";
                errorLabel.setStyle("-fx-text-fill: red;");
                errorLabel.setText(message);
            }
        }
        else
        {
            // --- Le nom d'utilisateur n'est pas déjà prit
            boolean inDb = player.isUsernameAlreadyUsed();
            if (!inDb)
            {
                // --- Si les deux mot de passe correspondent
                if (password.equals(confirmPassword))
                {
                    BlackjackApplication.player.setUsername(username);
                    BlackjackApplication.player.setPassword(password);
                    BlackjackApplication.player.writePlayer();
                    // ---
                    String message = "[OK] - Modifications sauvegardés avec succès";
                    errorLabel.setStyle("-fx-text-fill: green;");
                    errorLabel.setText(message);
                }
                else
                {
                    String message = "[ERROR] - Veuillez saisir le même mot de passe dans les deux champs";
                    errorLabel.setStyle("-fx-text-fill: red;");
                    errorLabel.setText(message);
                }
            }
            else
            {
                String message = "[ERROR] - Le nom d'utilisateur est déjà prit";
                errorLabel.setStyle("-fx-text-fill: red;");
                errorLabel.setText(message);
            }
        }
    }
}