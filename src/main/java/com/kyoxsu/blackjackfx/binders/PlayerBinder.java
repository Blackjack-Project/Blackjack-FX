package com.kyoxsu.blackjackfx.binders;

import com.kyoxsu.blackjackfx.models.Card;
import com.kyoxsu.blackjackfx.models.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.util.Duration;
//------------------------------------------------------------------------------
/**
 * @author Kyo
 * @modif Kyo : 2025-04-17 : Création.
 */
//------------------------------------------------------------------------------
public class PlayerBinder
{
    private Player playerModel;
    // ---
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private BooleanProperty role = new SimpleBooleanProperty();
    private StringProperty cards = new SimpleStringProperty();
    private DoubleProperty money = new SimpleDoubleProperty();
    private StringProperty room = new SimpleStringProperty();
    private IntegerProperty position = new SimpleIntegerProperty();
    // ---
    private ListProperty<Card> lCards = new SimpleListProperty<>();

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public PlayerBinder(Player playerModel)
    {
        this.playerModel = playerModel;

        // --- Première actualisation des données
        refreshPlayer();

        // --- Actualisation des données en permanence
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                e ->
                {
                    refreshPlayer();
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Remonte les données du modèle vers le binder
    public void refreshPlayer()
    {
        setUsername(this.playerModel.getUsername());
        setPassword(this.playerModel.getPassword());
        setRole(this.playerModel.isRole());
        setCards(this.playerModel.getCards());
        setMoney(this.playerModel.getMoney());
        setRoom(this.playerModel.getRoom());
        setPosition(this.playerModel.getPosition());
        // ---
        setlCards(this.playerModel.getlCards());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Descend les données du binder vers le modèle
    public void writePlayer()
    {
        this.playerModel.setUsername(getUsername());
        this.playerModel.setPassword(getPassword());
        this.playerModel.setRole(isRole());
        this.playerModel.setCards(getCards()); // Attention pas bon
        this.playerModel.setMoney(getMoney());
        this.playerModel.setRoom(getRoom());
        this.playerModel.setPosition(getPosition());
        // ---
        this.playerModel.setlCards(getlCards());
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Player getPlayerModel()
    {
        return playerModel;
    }

    public void setPlayerModel(Player playerModel)
    {
        this.playerModel = playerModel;
    }

    public String getUsername()
    {
        return username.get();
    }

    public StringProperty usernameProperty()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username.set(username);
    }

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

    public boolean isRole()
    {
        return role.get();
    }

    public BooleanProperty roleProperty()
    {
        return role;
    }

    public void setRole(boolean role)
    {
        this.role.set(role);
    }

    public String getCards()
    {
        return cards.get();
    }

    public StringProperty cardsProperty()
    {
        return cards;
    }

    public void setCards(String cards)
    {
        this.cards.set(cards);
    }

    public double getMoney()
    {
        return money.get();
    }

    public DoubleProperty moneyProperty()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money.set(money);
    }

    public String getRoom()
    {
        return room.get();
    }

    public StringProperty roomProperty()
    {
        return room;
    }

    public void setRoom(String room)
    {
        this.room.set(room);
    }

    public int getPosition()
    {
        return position.get();
    }

    public IntegerProperty positionProperty()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position.set(position);
    }

    public ObservableList<Card> getlCards() {
        return lCards.get();
    }

    public ListProperty<Card> lCardsProperty() {
        return lCards;
    }

    public void setlCards(ObservableList<Card> lCards) {
        this.lCards.set(lCards);
    }
}
