package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import com.kyoxsu.blackjackfx.binders.RoomBinder;
import com.kyoxsu.blackjackfx.models.Player;
import com.kyoxsu.blackjackfx.models.Room;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import java.io.IOException;

public class RoomView
{
    private RoomBinder roomBinder;
    // ---
    @FXML
    private ListView<Player> playerList;
    @FXML
    private Label playerSoldLabel;
    @FXML
    private Label roomInfoLabel;
    @FXML
    private Label roomTitleLabel;

    public void setRoomBinder(RoomBinder roomBinder)
    {
        playerList.itemsProperty().bind(roomBinder.lPlayersProperty());

        playerList.setCellFactory(param -> new ListCell<>()
        {
            @Override
            protected void updateItem(Player player, boolean empty)
            {
                super.updateItem(player, empty);
                if (empty || player == null)
                {
                    setText(null);
                }
                else
                {
                    setText(player.getUsername());
                }
            }
        });
    }

    @FXML
    void onLeave(ActionEvent event)
    {
        // --- On supprime l'id de la room dans le joueur
        BlackjackApplication.player.leaveRoom();

        // --- On réinitialise le role de joueur à "joueur"
        BlackjackApplication.player.setRole(false);

        // --- On réinitialise la position du joueur
        BlackjackApplication.player.setPosition(-1);

        // --- Seul l'hote peut supprimer le salon
        boolean isHost = BlackjackApplication.player.isRole();
        if (isHost)
        {
            Room room = BlackjackApplication.room;
            ObservableList<Player> lPlayers= room.getlPlayers();
            for (Player player : lPlayers)
            {
                player.setRoom(null);
                player.writePlayer();
            }

            // --- Suppresion de la room
            BlackjackApplication.player.deleteRoom(BlackjackApplication.room);
        }

        // --- On sauvegarde les informations du joueur
        BlackjackApplication.player.writePlayer();

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

    @FXML
    void onReady(ActionEvent event)
    {
        // TODO : Passer le statut du joueur à ready

        // TODO : Ajouter le verouillage du salon si le player est l'hôte

        try
        {
            Scene loadGameScene = BlackjackApplication.getInstance().loadGameScene();
            BlackjackApplication.getInstance().getStage().setScene(loadGameScene);
            BlackjackApplication.getInstance().getStage().setMaximized(true);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}