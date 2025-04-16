package com.kyoxsu.blackjackfx.views;

import com.kyoxsu.blackjackfx.BlackjackApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class RoomView {

    @FXML
    private ListView<?> playerList;

    @FXML
    private Label playerSoldLabel;

    @FXML
    private Label roomInfoLabel;

    @FXML
    private Label roomTitleLabel;

    @FXML
    void onLeave(ActionEvent event)
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

    @FXML
    void onReady(ActionEvent event)
    {
        // TODO : Faire les différents calculs pour savoir quand la partie se lance
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