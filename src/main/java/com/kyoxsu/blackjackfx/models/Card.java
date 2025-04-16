package com.kyoxsu.blackjackfx.models;

import java.util.ArrayList;
//------------------------------------------------------------------------------
/**
 * Cette classe représente une carte.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class Card
{
    private String fullName;
    private String shortName;
    private int value;

    public static final ArrayList<Card> ALL_CARDS = getAllCards();

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Card(String fullName, String shortName, int value)
    {
        this.fullName = fullName;
        this.shortName = shortName;
        this.value = value;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ArrayList<Card> getAllCards()
    {
        // On récupère via la base toutes les cartes et on les transforme en objet java
        // On renvoie une liste de carte
        return null;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getFullName()
    {
        return fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getShortName()
    {
        return shortName;
    }
    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public int getValue()
    {
        return value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
}
