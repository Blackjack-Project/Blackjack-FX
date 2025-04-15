package com.kyoxsu.blackjackfx.models;

import java.util.ArrayList;
//------------------------------------------------------------------------------
/**
 * Cette classe représente un salon.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class Room
{
    private String roomId; // UUID
    private String name;
    private boolean state;
    private boolean visibility;
    private String password;
    private ArrayList<Player> lPlayers;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Room(String roomId, String name, boolean state, boolean visibility,
                String password)
    {
        this.roomId = roomId;
        this.name = name;
        this.state = state;
        this.visibility = visibility;
        this.password = password;
        this.lPlayers = new ArrayList<>();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void createRoom()
    {
        // Permet de créer un salon
        // Changement du statut du joueur en "hôte" -> true
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Room getRoom(String roomId)
    {
        // Permet de récupérer le salon en question
        return null;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void deleteRoom()
    {
        // Permet de supprimer un salon (dans la base de données)
    }
}
