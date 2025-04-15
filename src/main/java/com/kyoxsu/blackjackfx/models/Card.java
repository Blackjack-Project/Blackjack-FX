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

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ArrayList<Card> getAllCards()
    {
        ArrayList<Card> lCards = new ArrayList<>();

        // Permet de récupérer toutes les cartes de la base de données
        // On crée toutes les cartes

        return null;
    }
}
