package com.kyoxsu.blackjackfx.helpers;

import com.kyoxsu.blackjackfx.models.Card;
import com.kyoxsu.blackjackfx.models.Deck;
import com.kyoxsu.blackjackfx.models.Player;
import com.kyoxsu.blackjackfx.models.Room;

import java.util.ArrayList;
//------------------------------------------------------------------------------
/**
 * Cette classe permet de réaliser les différents tests sur les objets élaborés.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class TestsHelper
{
    public static void main(String[] args)
    {
        Player player = new Player("Kyo", "1234");
        player.createPlayer();

        player.setUsername("oyK");
        player.setPassword("4321");

        player.savePlayer();

        player.getPlayer("");

        //----------------------------------------------------------------------

        ArrayList<Card> lCards = Card.getAllCards();
        System.out.println(lCards.get(0));

        //----------------------------------------------------------------------

        Deck deck = new Deck();
        deck.shuffle(7); // Riffle shuffle - mélange à l'américaine
        deck.draw();

        //----------------------------------------------------------------------

        //Room room = new Room("", "", "");
    }
}
