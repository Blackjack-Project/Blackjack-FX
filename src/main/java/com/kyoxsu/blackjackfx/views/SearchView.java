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
        roomID.setText(room.getRoomId());
    }

    @FXML
    void onJoinRoom(ActionEvent event)
    {
        // --- Vérification qu'une room à bien été
        if (roomID != null && roomID.getText().length() > 0)
        {
            Room room = Room.getRoomById(roomID.getText());

            // --- Vérification de la taille du salon
            int sizeRoom = room.getlPlayers().size()-1;
            int maxPlayer = room.getMaxPlayer();
            if (maxPlayer - sizeRoom > 0)
            {
                // --- Vérification de la visibilité du salon
                boolean roomIsVisible = room.isVisibility();
                if (!roomIsVisible)
                {
                    String password = roomPassword.getText();

                    if (password != null && password.length() > 0)
                    {
                        BlackjackApplication.room = room;
                        BlackjackApplication.player.joinRoom(BlackjackApplication.room);

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
                    else
                    {
                        String message = "[ERROR] - Ce salon est privé, veuillez saisir un mot de passe";
                        errorLabel.setText(message);
                    }
                }
                else
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
            }
            else
            {
                String message = "[ERROR] - Ce salon est déjà plein";
                errorLabel.setText(message);
            }
        }
        else
        {
            String message = "[ERROR] - Veuillez selectionner un salon en cliquant ou en saisissant l'id de celle-ci";
            errorLabel.setText(message);
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
