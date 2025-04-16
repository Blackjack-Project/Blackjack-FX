package com.kyoxsu.blackjackfx.models;

import com.kyoxsu.blackjackfx.helpers.SQLHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
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
    public Room(String name, String password, boolean visibility)
    {
        this.name = name;
        this.password = password;
        this.visibility = visibility;               // true -> privée / false -> public
        // ---
        this.roomId = UUID.randomUUID().toString(); // Création d'un id par défaut
        this.state = true;                          // En attente de joueurs
        this.lPlayers = new ArrayList<>();
    }
    // Permet de récupérer / créer un salon de A à Z manuellement (sauf la liste des joueurs)
    public Room(String roomId, String name, boolean state, boolean visibility,
                String password)
    {
        this.roomId = roomId;
        this.name = name;
        this.state = state;
        this.visibility = visibility;
        this.password = password;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ArrayList<Room> getAllRooms() // Affichage de la liste des salons
    {
        ArrayList<Room> lRooms = new ArrayList<>();
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les salles qui sont en attente de joueurs et qui sont public
        String sql = "SELECT * FROM room WHERE state = 0 AND visibilite = 0"; // TODO : Vérifier si cela fait bien des 0
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            // ---
            while (rs.next())
            {
                String roomId       = rs.getString("roomId");
                String name         = rs.getString("name");
                boolean state       = rs.getBoolean("state");
                boolean visibility  = rs.getBoolean("visibility");
                String password     = rs.getString("password");

                // --- Création du salon
                Room room = new Room(roomId, name, state, visibility, password);

                // --- Récupération de tous les joueurs présents dans le salon
                ArrayList<Player> lPlayers = Player.getAllPlayers(room.getRoomId());
                room.setlPlayers(lPlayers);

                // --- Ajout du salon à la liste des salons
                lRooms.add(room);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return lRooms;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void updateRoom()
    {
        // Méthode permettant de récupérer les dernières modifications du salon
        // (Depuis la base)
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getRoomId()
    {
        return roomId;
    }
    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isState()
    {
        return state;
    }
    public void setState(boolean state)
    {
        this.state = state;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isVisibility()
    {
        return visibility;
    }
    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public ArrayList<Player> getlPlayers()
    {
        return lPlayers;
    }
    public void setlPlayers(ArrayList<Player> lPlayers)
    {
        this.lPlayers = lPlayers;
    }
}
