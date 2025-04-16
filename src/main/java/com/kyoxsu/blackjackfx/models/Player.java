package com.kyoxsu.blackjackfx.models;

import com.kyoxsu.blackjackfx.helpers.SQLHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private boolean role;               // Joueur ou hôte
    private String cards;
    private double money;
    private String roomId;              // UUID du salon dans lequel il se trouve
    private int position;
    // ---
    private ArrayList<Card> lCards;     // Liste issue de la conversion de "cards"

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        // ---
        this.role = false;                  // Role à "joueur" par défaut"
        this.lCards = new ArrayList<>();
        this.roomId = null;                 // Par défaut pas de room
    }
    // Permet de récupérer / créer un joueur de A à Z manuellement (sauf les cartes)
    public Player(String username, String password, boolean role,
                  double money, String roomId, int position)
    {
        this.username = username;
        this.password = password;
        this.role = role;
        this.money = money;
        this.roomId = roomId;
        this.position = position;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ArrayList<Player> getAllPlayers(String roomId)
    {
        ArrayList<Player> lPlayers = new ArrayList<>();
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les joueurs qui se trouvent dans le salon recherché
        String sql = "SELECT * FROM room WHERE roomId = ?";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, roomId);
            // ---
            ResultSet rs = pstmt.executeQuery();
            // ---
            while (rs.next())
            {
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean role    = rs.getBoolean("role");
                double money    = rs.getDouble("money");
                String sRoomId  = rs.getString("roomId"); // A voir si on le supprime
                int position    = rs.getInt("position");

                // --- Création du joueur
                Player player = new Player(username, password, role, money, sRoomId, position);

                // --- Récupération de toutes ses cartes
                ArrayList<Card> lCards = player.getAllPlayersCard();
                player.setlCards(lCards);

                // --- Ajout du joueur à la liste des salons
                lPlayers.add(player);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return lPlayers;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public ArrayList<Card> getAllPlayersCard()
    {
        // TODO : Il faut déformater les cartes de la base

        ArrayList<Card> lCards = new ArrayList<>();

        String cards = getCards();
        cards.trim();

        String[] aCards = cards.split("/");


        return lCards;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Appelée lors que le joueur se crée un compte
    public void createPlayer()
    {
        Connection con = SQLHelper.getConnection();
        String sql = "INSERT INTO player (username, password, role, cards, money, " +
                "roomId, position) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setBoolean(3, role);
            // ---
            String cards = formatCards();
            pstmt.setString(4, cards);
            // ---
            pstmt.setDouble(5, money);
            pstmt.setString(6, roomId);
            pstmt.setInt(7, position);

            int lignesAjoutees = pstmt.executeUpdate();
            System.out.println(lignesAjoutees + " ligne(s) ajoutée(s).");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void login(String username, String password)
    {
        // TODO : Vérifier l'existance du joueur dans la base et ensuite que son
        //  mot de passe correspond

        // --- On récupère le joueur
        getPlayer(username);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Lorsque le joueur modifie ses informations
    public void savePlayer() // On sauvegarde les modifications sur le joueur
    {
        Connection con = SQLHelper.getConnection();
        String sql = "UPDATE player SET password = ?, role = ?, cards = ?, money = ?," +
                " roomId = ?, position = ? WHERE username = ?";
        try
        {   PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setBoolean(3, role);
            // ---
            String cards = formatCards();
            pstmt.setString(4, cards);
            // ---
            pstmt.setDouble(5, money);
            pstmt.setString(6, roomId);
            pstmt.setInt(7, position);

            int lignesModifiees = pstmt.executeUpdate();
            System.out.println(lignesModifiees + " ligne(s) modifiée(s).");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void getPlayer(String username) // On récupère toutes les informations du joueur depuis la base
    {
        // Méthode permettant de récupérer un joueur
        // Avec son prénom ?
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void joinRoom(Room room)
    {
        // Ajoute un joueur au salon (dans la base)
        //room.addPlayer(this);
        // TODO : Faire l'ajout dans la base
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void leaveRoom()
    {
        // Supprime le joueur du salon (dans la base)
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void createRoom(boolean visibility)
    {
        // --- Création du salon
        String name = username+"'s room";
        Room room = new Room(name, "", visibility);

        // --- On set l'id du salon
        this.roomId = room.getRoomId();

        // --- On rejoint le salon
        joinRoom(room);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void deleteRoom(Room room)
    {
        // Permet de supprimer le salon, si le joueur est l'hôte de celui-ci

        Connection con = SQLHelper.getConnection();
        String sql = "DELETE FROM room WHERE roomId = ?";
        try
        {   PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, room.getRoomId());

            int lignesModifiees = pstmt.executeUpdate();
            System.out.println(lignesModifiees + " ligne(s) modifiée(s).");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String formatCards()
    {
        String cards = "";
        for (Card card : lCards)
        {
            cards += card.getShortName()+" / ";
        }
        cards.substring(0, cards.length() - 3);
        return cards;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
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
    public boolean isRole()
    {
        return role;
    }
    public void setRole(boolean role)
    {
        this.role = role;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public ArrayList<Card> getlCards()
    {
        return lCards;
    }
    public void setlCards(ArrayList<Card> lCards)
    {
        this.lCards = lCards;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public double getMoney()
    {
        return money;
    }
    public void setMoney(double money)
    {
        this.money = money;
    }
    public void addMoney(double money)
    {
        this.money += money;
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
    public int getPosition()
    {
        return position;
    }
    public void setPosition(int position)
    {
        this.position = position;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public String getCards()
    {
        return cards;
    }
    public void setCards(String cards)
    {
        this.cards = cards;
    }
}
