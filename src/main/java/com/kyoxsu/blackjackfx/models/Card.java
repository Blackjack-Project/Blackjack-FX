package com.kyoxsu.blackjackfx.models;

import com.kyoxsu.blackjackfx.helpers.SQLHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static Card getCardByShortName(String shortName)
    {
        Card card = null;
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les joueurs qui se trouvent dans le salon recherché
        String sql = "SELECT * FROM card WHERE short_name = ?";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,shortName);
            res = pstmt.executeQuery();
            // ---
            while (res.next())
            {
                String fullName = res.getString("full_name");
                int value = res.getInt("value");

                // --- Création du joueur
                card = new Card(fullName, shortName, value);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return card;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ObservableList<Card> getAllCards()
    {
        ObservableList<Card> lCards = FXCollections.observableArrayList();
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les joueurs qui se trouvent dans le salon recherché
        String sql = "SELECT * FROM card";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            res = pstmt.executeQuery();
            // ---
            while (res.next())
            {
                String fullName = res.getString("full_name");
                String shortName = res.getString("short_name");
                int value = res.getInt("value");

                // --- Création du joueur
                Card card = new Card(fullName, shortName, 0);

                // --- Ajout de la carte a la liste
                lCards.add(card);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return lCards;
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
