package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import com.kyoxsu.blackjackfx.models.Player;
import com.kyoxsu.blackjackfx.models.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchView
{
    @FXML
    private Label errorLabel;

    @FXML
    private TextField roomID;

    @FXML
    private ListView<Room> roomList;

    @FXML
    private TextField roomPassword;

    public void initialize()
    {
        roomList.getSelectionModel().selectedItemProperty().addListener((obs, oldRoom, newRoom) -> {
            if (newRoom != null)
            {
                setRoom(newRoom);
            }
        });

        roomList.setCellFactory(param -> new ListCell<>()
        {
            @Override
            protected void updateItem(Room room, boolean empty)
            {
                super.updateItem(room, empty);
                if (empty || room == null)
                {
                    setText(null);
                }
                else
                {
                    setText(room.getName());
                }
            }
        });

        // --- Récupération de toutes les rooms existantes
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                e ->
                {
                    ArrayList<Room> lRooms = Room.getAllRooms();
                    roomList.getItems().setAll(lRooms);
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setRoom(Room room)
    {
        BlackjackApplication.room = room;
        roomID.setText(room.getRoomId());
    }

    @FXML
    void onJoinRoom(ActionEvent event)
    {
        // TODO : Vérifier que le user à soit cliquer sur le bouton, soit qu'il a saisie
        // un id

        // TODO : Faire en sorte pouvoir se connecter via un id aussi

        // Il faut récupérer la room avec son id
        BlackjackApplication.player.joinRoom(BlackjackApplication.room);

        // Il faut attribuer la position du joueur en vérifiant combien de personnes
        // sont déjà présentes

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
