package com.kyoxsu.blackjackfx.models;

import java.util.ArrayList;
//------------------------------------------------------------------------------
/**
 * Cette classe représente un joueur.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class Player
{
    private String username;
    private String password;
    private boolean role;
    private ArrayList<String> lCards;
    private double money;
    private String roomId; // UUID
    private int position;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Player(String username, String password, boolean role, ArrayList<String> lCards,
                  double money, String roomId, int position)
    {
        this.username = username;
        this.password = password;
        this.role = role;
        this.lCards = lCards;
        this.money = money;
        this.roomId = roomId;
        this.position = position;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void createPlayer()
    {
        // Méthode permettant de créer le joueur dans la base
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void updatePlayer()
    {
        // Méthode permettant de mettre à jour les informations du joueur dans la base
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void getPlayer()
    {
        // Méthode permettant de récupérer un joueur
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void joinRoom()
    {
        // Ajoute un joueur au salon (dans la base)
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void leaveRoom()
    {
        // Supprime le joueur du salon (dans la base)
    }
}
