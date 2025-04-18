package com.kyoxsu.blackjackfx.models;

import com.kyoxsu.blackjackfx.helpers.SQLHelper;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
//------------------------------------------------------------------------------
/**
 * Cette classe représente le jeu de carte complet durant la partie.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class Deck
{
    private ObservableList<Card> lCards;
    private String sCards; // La liste de toutes les cartes en string

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Deck()
    {
        this.lCards = lCards;

        // --- Remplissage du deck 6 fois, car 6 paquets pour jouer au blackjack
        for (int i = 0; i<6; i++)
        {
            lCards.addAll(Card.getAllCards());
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void shuffle(int times)
    {
        // --- On mélange autant de fois que spécifié
        for (int i = 0; i<times; i++)
        {
            Collections.shuffle(lCards);
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Card draw()
    {
        Card card = lCards.get(0);
        lCards.remove(0);
        return card;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void encodeDeck()
    {
        String deckInString = "";
        for (Card card : lCards)
        {
            deckInString += card.getShortName()+" / ";
        }
        deckInString.substring(0, deckInString.length()-3);

        setsCards(deckInString);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void decodeDeck()
    {
        ObservableList<Card> lCards = FXCollections.observableArrayList();

        String sCards = getsCards();
        sCards.trim();
        String[] aCards = sCards.split("/");

        for (String sCard : aCards)
        {
            Card card = Card.getCardByShortName(sCard);
            lCards.add(card);
        }

        setlCards(lCards);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public ObservableList<Card> getlCards()
    {
        return lCards;
    }
    public void setlCards(ObservableList<Card> lCards)
    {
        this.lCards = lCards;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getsCards()
    {
        return sCards;
    }
    public void setsCards(String sCards)
    {
        this.sCards = sCards;
    }
}
