package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SearchView {

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<?> roomList;

    @FXML
    private TextField roomPassword;

    @FXML
    void onJoinRoom(ActionEvent event)
    {
        // TODO : Faire le système pour rejoindre la room
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
