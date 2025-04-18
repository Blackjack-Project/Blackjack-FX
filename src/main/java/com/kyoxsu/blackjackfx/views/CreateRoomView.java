package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import com.kyoxsu.blackjackfx.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateRoomView
{
    private static boolean isRoomPublic;
    // ---
    @FXML
    private ToggleGroup RadioGroup;
    @FXML
    private Label errorLabel;
    @FXML
    private Slider playerNumberSlider;
    @FXML
    private TextField roomNameField;
    @FXML
    private PasswordField roomPasswordField;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void initialize()
    {
        isRoomPublic = true;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @FXML
    void onPrivateSelected(ActionEvent event)
    {
        isRoomPublic = false;
        roomPasswordField.setDisable(false);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @FXML
    void onPublicSelected(ActionEvent event)
    {
        isRoomPublic = true;
        roomPasswordField.setDisable(true);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @FXML
    void onCreateRoom(ActionEvent event)
    {
        // --- Passage du role du joueur à "hôte"
        BlackjackApplication.player.setRole(true);

        // --- On définie l'hôte comme le premier joueur de la partie
        BlackjackApplication.player.setPosition(1);

        // --- Création de la room & sauvegarde du joueur
        String password = roomPasswordField.getText();
        int maxPlayer = (int) playerNumberSlider.getValue();
        BlackjackApplication.player.createRoom(password, isRoomPublic, maxPlayer);

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

}