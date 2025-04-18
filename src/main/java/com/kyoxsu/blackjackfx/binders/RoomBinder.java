package com.kyoxsu.blackjackfx.binders;

import com.kyoxsu.blackjackfx.models.Player;
import com.kyoxsu.blackjackfx.models.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.util.Duration;
//------------------------------------------------------------------------------
/**
 *
 */
//------------------------------------------------------------------------------
public class RoomBinder
{
    private Room roomModel;
    // ---
    private StringProperty roomId = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private BooleanProperty state = new SimpleBooleanProperty();
    private BooleanProperty visibility = new SimpleBooleanProperty();
    private StringProperty password = new SimpleStringProperty();
    private IntegerProperty maxPlayer = new SimpleIntegerProperty();
    // ---
    private ListProperty<Player> lPlayers = new SimpleListProperty<>();

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public RoomBinder(Room roomModel)
    {
        this.roomModel = roomModel;

        // --- Première actualisation des données
        refreshRoom();

        // --- Actualisation des données en permanence
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                e ->
                {
                    refreshRoom();
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Remonte les données du modèle vers le binder
    public void refreshRoom()
    {
        setRoomId(this.roomModel.getRoomId());
        setName(this.roomModel.getName());
        setState(this.roomModel.isState());
        setVisibility(this.roomModel.isVisibility());
        setPassword(this.roomModel.getPassword());
        setMaxPlayer(this.roomModel.getMaxPlayer());

        setlPlayers(this.roomModel.getlPlayers());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Descend les données du binder vers le modèle
    public void writeRoom()
    {
        this.roomModel.setRoomId(getRoomId());
        this.roomModel.setName(getName());
        this.roomModel.setState(isState());
        this.roomModel.setVisibility(isVisibility());
        this.roomModel.setPassword(getPassword());
        this.roomModel.setMaxPlayer(getMaxPlayer());

        this.roomModel.setlPlayers(getlPlayers());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Room getRoomModel()
    {
        return roomModel;
    }
    public void setRoomModel(Room roomModel)
    {
        this.roomModel = roomModel;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getRoomId()
    {
        return roomId.get();
    }
    public StringProperty roomIdProperty()
    {
        return roomId;
    }
    public void setRoomId(String roomId)
    {
        this.roomId.set(roomId);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getName()
    {
        return name.get();
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name.set(name);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isState()
    {
        return state.get();
    }
    public BooleanProperty stateProperty()
    {
        return state;
    }
    public void setState(boolean state)
    {
        this.state.set(state);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isVisibility()
    {
        return visibility.get();
    }
    public BooleanProperty visibilityProperty()
    {
        return visibility;
    }
    public void setVisibility(boolean visibility)
    {
        this.visibility.set(visibility);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getPassword()
    {
        return password.get();
    }
    public StringProperty passwordProperty()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password.set(password);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public ObservableList<Player> getlPlayers()
    {
        return lPlayers.get();
    }
    public ListProperty<Player> lPlayersProperty()
    {
        return lPlayers;
    }
    public void setlPlayers(ObservableList<Player> lPlayers)
    {
        this.lPlayers.set(lPlayers);
    }

    public int getMaxPlayer() {
        return maxPlayer.get();
    }

    public IntegerProperty maxPlayerProperty() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer.set(maxPlayer);
    }
}
