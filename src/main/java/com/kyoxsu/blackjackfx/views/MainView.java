package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.io.IOException;

public class MainView {

    @FXML
    void onCreateRoom(ActionEvent event)
    {
        try
        {
            Scene loadCreateRoomScene = BlackjackApplication.getInstance().loadCreateRoomScene();
            BlackjackApplication.getInstance().getStage().setScene(loadCreateRoomScene);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onJoinRoom(ActionEvent event)
    {
        try
        {
            Scene loadSearchScene = BlackjackApplication.getInstance().loadSearchScene();
            BlackjackApplication.getInstance().getStage().setScene(loadSearchScene);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLogout(ActionEvent event)
    {
        try
        {
            Scene loadLoginScene = BlackjackApplication.getInstance().loadLoginScene();
            BlackjackApplication.getInstance().getStage().setScene(loadLoginScene);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSettings(ActionEvent event)
    {
        try
        {
            Scene loadUserScene = BlackjackApplication.getInstance().loadUserScene();
            BlackjackApplication.getInstance().getStage().setScene(loadUserScene);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
