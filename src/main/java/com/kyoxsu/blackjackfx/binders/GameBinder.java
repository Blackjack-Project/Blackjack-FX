package com.kyoxsu.blackjackfx.binders;

import com.kyoxsu.blackjackfx.models.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.util.Duration;

public class GameBinder
{
    private Game gameModel;
    // ---
    public IntegerProperty round;
    public Deck gameDeck;
    public Room gameRoom;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public GameBinder(Game gameModel)
    {
        this.gameModel = gameModel;

        // --- Première actualisation des données
        refreshGame();

        // --- Actualisation des données en permanence
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                e ->
                {
                    refreshGame();
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Remonte les données du modèle vers le binder
    public void refreshGame()
    {

    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Descend les données du binder vers le modèle
    public void writeGame()
    {

    }

}
