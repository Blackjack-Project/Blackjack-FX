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
    private int maxPlayer;
    // ---
    private ObservableList<Player> lPlayers;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Room(String name, String password, boolean visibility, int maxPlayer)
    {
        this.name = name;
        this.password = password;
        this.visibility = visibility;               // true -> public / false -> privée
        // ---
        this.roomId = UUID.randomUUID().toString(); // Création d'un id par défaut
        this.state = true;                          // En attente de joueurs
        this.maxPlayer = maxPlayer;
        this.lPlayers = FXCollections.observableArrayList();

        // --- Première actualisation des données
        refreshRoom();

        // --- Actualisation des données en permanence
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                e ->
                {
                    //System.out.println(lPlayers.size());
                    refreshRoom();
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isRoomInDb()
    {
        boolean inDb = false;
        Connection con = SQLHelper.getConnection();

        // --- Vérification de l'existence du joueur
        String sql = "SELECT * FROM room WHERE roomid = ?";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, roomId);
            ResultSet res = pstmt.executeQuery();

            // --- Si on a un résultat, on dit que c'est vrai
            if (res.next())
            {
                inDb = true;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return inDb;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void writeRoom()
    {
        Connection con = SQLHelper.getConnection();

        // --- Vérification de l'existence du joueur
        String sql = "";
        String cards = null;
        boolean inDb = isRoomInDb();
        // ---
        if (!inDb)
        {
            sql = "INSERT INTO room (roomid, name, state, visibility, password, maxPlayer) VALUES (?, ?, ?, ?, ?, ?)";
        }
        else
        {
            sql = "UPDATE player SET name = ?, state = ?, visibility = ?, password = ?, maxPlayer = ?"
                    +" WHERE roomid = ?";
        }
        // ---
        PreparedStatement pstmt = null;
        try
        {
            pstmt = con.prepareStatement(sql);

            // TODO : Voir si je ne peux pas mieux calculer que ça
            if (!inDb)
            {
                pstmt.setString(1, getRoomId());
                pstmt.setString(2, getName());
                pstmt.setBoolean(3, isState());
                pstmt.setBoolean(4, isVisibility());
                pstmt.setString(5, getPassword());
                pstmt.setInt(6, getMaxPlayer());
            }
            else
            {
                pstmt.setString(1, getName());
                pstmt.setBoolean(2, isState());
                pstmt.setBoolean(3, isVisibility());
                pstmt.setString(4, getPassword());
                pstmt.setInt(5, getMaxPlayer());
                pstmt.setString(6, getRoomId());
            }

            int lignesAjoutees = pstmt.executeUpdate();
            System.out.println(lignesAjoutees + " ligne(s) ajoutée(s).");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, null);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void refreshRoom()
    {
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les joueurs qui se trouvent dans le salon recherché
        String sql = "SELECT * FROM room WHERE roomid = ?";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, roomId);
            // ---
            res = pstmt.executeQuery();
            // ---
            while (res.next())
            {
                String name         = res.getString("name");
                boolean state       = res.getBoolean("state");
                boolean visibility  = res.getBoolean("visibility");
                String password     = res.getString("password");
                int maxPlayer       = res.getInt("maxPlayer");
                // ---
                setName(name);
                setState(state);
                setVisibility(visibility);
                setPassword(password);
                setMaxPlayer(maxPlayer);

                // --- Récupération de tous les joueurs
                ObservableList<Player> lPlayers = Player.getAllPlayers(getRoomId());
                setlPlayers(lPlayers);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ArrayList<Room> getAllRooms() // Affichage de la liste des salons
    {
        ArrayList<Room> lRooms = new ArrayList<>();
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les salles qui sont en attente de joueurs et qui sont public
        String sql = "SELECT * FROM room WHERE state = 1 AND visibility = 1";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            res = pstmt.executeQuery();
            // ---
            while (res.next())
            {
                String roomId       = res.getString("roomId");
                String name         = res.getString("name");
                boolean state       = res.getBoolean("state");
                boolean visibility  = res.getBoolean("visibility");
                String password     = res.getString("password");
                int maxPlayer       = res.getInt("maxPlayer");

                // --- Création du salon
                Room room = new Room(name, password, visibility, maxPlayer);
                room.setRoomId(roomId);
                room.setState(state);
                room.setMaxPlayer(maxPlayer);

                // --- Récupération de tous les joueurs présents dans le salon
                ObservableList<Player> lPlayers = Player.getAllPlayers(room.getRoomId());
                room.setlPlayers(lPlayers);

                // --- Ajout du salon à la liste des salons
                lRooms.add(room);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return lRooms;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static Room getRoomById(String roomId) // Affichage de la liste des salons
    {
        Room room = null;
        Connection con = SQLHelper.getConnection();

        PreparedStatement pstmt = null;
        ResultSet res = null;

        // --- On ne récupère que les salles qui sont en attente de joueurs et qui sont public
        String sql = "SELECT * FROM room WHERE roomid = ?"; // TODO : Vérifier si cela fait bien des 0
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, roomId);
            res = pstmt.executeQuery();
            // ---
            if (res.next())
            {
                String name         = res.getString("name");
                boolean state       = res.getBoolean("state");
                boolean visibility  = res.getBoolean("visibility");
                String password     = res.getString("password");
                int maxPlayer       = res.getInt("maxPlayer");

                // --- Création du salon
                room = new Room(name, password, visibility, maxPlayer);
                room.setRoomId(roomId);
                room.setState(state);
                room.setMaxPlayer(maxPlayer);

                // --- Récupération de tous les joueurs présents dans le salon
                ObservableList<Player> lPlayers = Player.getAllPlayers(room.getRoomId());
                room.setlPlayers(lPlayers);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return room;
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
    public ObservableList<Player> getlPlayers()
    {
        return lPlayers;
    }
    public void setlPlayers(ObservableList<Player> lPlayers)
    {
        this.lPlayers = lPlayers;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public int getMaxPlayer()
    {
        return maxPlayer;
    }
    public void setMaxPlayer(int maxPlayer)
    {
        this.maxPlayer = maxPlayer;
    }
}
