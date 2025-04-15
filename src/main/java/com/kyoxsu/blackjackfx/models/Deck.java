package com.kyoxsu.blackjackfx.models;

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
    private ArrayList<Card> lCards;

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
    public void draw()
    {
        // Méthode pour piocher des cartes.
        // le paquet doit diminuer

        // Voir si je prends la première ?
    }
}
