package com.kyoxsu.blackjackfx.models;

//------------------------------------------------------------------------------

import com.kyoxsu.blackjackfx.BlackjackApplication;

import java.util.ArrayList;

/**
 * Cette classe représente le déroulement d'une partie de blackjack.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class Game
{
    // TODO : Faire un listing des différentes phases de la partie
    public int round;
    public Player currentPlayer;
    public Deck gameDeck;
    public Room gameRoom;
    public ArrayList<Card> bankGame = new ArrayList<>();
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Game()
    {
        this.round = 0;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void startGame()
    {
        this.gameDeck.shuffle(6);
        for (int i = 0; i < 2; i++) {
            for (Player player:
                    this.gameRoom.getlPlayers()) {
                player.getlCards().add(this.gameDeck.draw());
            }
            this.bankGame.add(this.gameDeck.draw());
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void nextRound()
    {
        this.round++;
        if (this.round==this.gameRoom.getlPlayers().size()){
            this.round = 0;
        }
        this.currentPlayer = this.gameRoom.getlPlayers().get(this.round);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void distribute()
    {
        if (BlackjackApplication.player.getId() == this.currentPlayer.getId()){
            this.currentPlayer.getlCards().add(this.gameDeck.draw());
        }
        this.nextRound();
    }

    public void bet(int bet){
        /*if (BlackjackApplication.player.getId() == this.currentPlayer.getId()){
            this.currentPlayer.
        }*/
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(Deck gameDeck) {
        this.gameDeck = gameDeck;
    }

    public Room getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(Room gameRoom) {
        this.gameRoom = gameRoom;
    }
}
