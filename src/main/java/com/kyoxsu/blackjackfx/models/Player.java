package com.kyoxsu.blackjackfx.models;

import com.kyoxsu.blackjackfx.BlackjackApplication;
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
    private int id;
    private String username;
    private String password;
    private boolean role;                   // Joueur ou hôte
    private String cards;
    private double money;
    private String room;                    // UUID du salon dans lequel il se trouve
    private int position;
    // ---
    private ObservableList<Card> lCards;    // Liste issue de la conversion de "cards"

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        // ---
        this.role = false;                                  // Role à "joueur" par défaut"
        this.lCards = FXCollections.observableArrayList();  // Pas de cartes
        this.room = null;                                   // Par défaut pas de room
        this.position = -1;                                 // Pas de position -> car pas dans un salon
        this.money = 0.0d;                                  // Pas d'argent car vient d'être créé

        // --- Première actualisation des données
        refreshPlayer();

        // --- Actualisation des données en permanence
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                e ->
                {
                    refreshPlayer();
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isPlayerInDb()
    {
        boolean inDb = false;
        Connection con = SQLHelper.getConnection();

        // --- Vérification de l'existence du joueur
        String sql = "SELECT * FROM player WHERE id = ?";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            res = pstmt.executeQuery();

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
        SQLHelper.close(pstmt, res);
        return inDb;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public boolean isUsernameAlreadyUsed()
    {
        boolean isUsernameAlreadyUsed = false;
        Connection con = SQLHelper.getConnection();

        // --- Vérification de l'existence du joueur
        String sql = "SELECT * FROM player WHERE username = ?";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            res = pstmt.executeQuery();

            // --- Si on a un résultat, on dit que c'est vrai
            if (res.next())
            {
                isUsernameAlreadyUsed = true;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return isUsernameAlreadyUsed;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static Player login(String username, String password)
    {
        Player player = null;
        Connection con = SQLHelper.getConnection();

        // --- Vérification de l'existence du joueur
        String sql = "SELECT * FROM player WHERE username = ?";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            res = pstmt.executeQuery();

            // --- Si on a un résultat, on dit que c'est vrai
            if (res.next())
            {
                String dbUsername = res.getString("username");
                String dbPassword = res.getString("password");
                // ---
                int id          = res.getInt("id");
                boolean role    = res.getBoolean("role");
                double money    = res.getDouble("money");
                String room     = res.getString("room");
                int position    = res.getInt("position");
                String cards    = res.getString("cards");
                // ---
                if (password.equals(dbPassword))
                {
                    player = new Player(dbUsername, dbPassword);
                    player.setId(id);
                    player.setRole(role);
                    player.setMoney(money);
                    player.setRoom(room);
                    player.setPosition(position);
                    // ---
                    player.setCards(cards);
                    player.decodeCards();
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return player;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    // Méthode pour copier les informations username & password
    public void copy(Player player)
    {
        // --- Mise à jour des informations
        setUsername(player.getUsername());
        setPassword(player.getPassword());

        // TODO : Le problème vient d'ici 1234 devient 12345 (l'ancien mdp)

        // --- Sauvegarde des informations modifiées
        writePlayer();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void writePlayer()
    {
        Connection con = SQLHelper.getConnection();

        // --- Vérification de l'existence du joueur
        String sql = "";
        String cards = null;
        boolean inDb = isPlayerInDb();
        // ---
        if (!inDb)
        {
            sql = "INSERT INTO player (username, password, role, cards, money, " +
                    "room, position) VALUES (?, ?, ?, ?, ?, ?, ?)";
        }
        else
        {
            sql = "UPDATE player SET username = ?, password = ?, role = ?, cards = ?, money = ?," +
                    " room = ?, position = ? WHERE id = ?";

            // --- A la création le player n'a pas de cards
            encodeCards();
        }
        // ---
        PreparedStatement pstmt = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setBoolean(3, role);
            // ---
            pstmt.setString(4, cards);
            // ---
            pstmt.setDouble(5, money);
            pstmt.setString(6, room);
            pstmt.setInt(7, position);

            if (inDb)
            {
                pstmt.setInt(8, id);
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
    public void refreshPlayer()
    {
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les joueurs qui se trouvent dans le salon recherché
        String sql = "SELECT * FROM player WHERE username = ?";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            // ---
            res = pstmt.executeQuery();
            // ---
            while (res.next())
            {
                String username = res.getString("username");
                String password = res.getString("password");
                boolean role    = res.getBoolean("role");
                double money    = res.getDouble("money");
                String room     = res.getString("room");
                int position    = res.getInt("position");
                String cards    = res.getString("cards");
                // ---
                setUsername(username);
                setPassword(password);
                setRole(role);
                setMoney(money);
                setRoom(room);
                setPosition(position);

                // --- On actualise d'abord la chaîne contenant toutes les cartes
                setCards(cards);
                decodeCards();
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
    public void joinRoom(Room room)
    {
        // --- Récupération de l'id de la room
        String roomId = room.getRoomId();
        setRoom(roomId);
        setPosition(room.getlPlayers().size()+1);
        writePlayer();

        BlackjackApplication.room = room;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void leaveRoom()
    {
        // --- Réinitialisation de l'id de la room
        setRoom(null);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void createRoom(String name, String password, boolean visibility, int maxPlayer)
    {
        // --- Création du salon
        Room room = new Room(name, password, visibility, maxPlayer);
        room.writeRoom();

        // --- On rejoint le salon
        joinRoom(room);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void deleteRoom(Room room)
    {
        Connection con = SQLHelper.getConnection();
        String sql = "DELETE FROM room WHERE roomId = ?";
        PreparedStatement pstmt = null;
        try
        {   pstmt = con.prepareStatement(sql);
            pstmt.setString(1, room.getRoomId());

            int lignesModifiees = pstmt.executeUpdate();
            System.out.println(lignesModifiees + " ligne(s) modifiée(s).");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, null);
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static ObservableList<Player> getAllPlayers(String roomId)
    {
        ObservableList<Player> lPlayers = FXCollections.observableArrayList();
        Connection con = SQLHelper.getConnection();

        // --- On ne récupère que les joueurs qui se trouvent dans le salon recherché
        String sql = "SELECT * FROM player WHERE room = ?";
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
                String username = res.getString("username");
                String password = res.getString("password");
                boolean role    = res.getBoolean("role");
                double money    = res.getDouble("money");
                String room     = res.getString("room"); // A voir si on le supprime
                int position    = res.getInt("position");

                // --- Création du joueur
                Player player = new Player(username, password);
                player.setRole(role);
                player.setMoney(money);
                player.setRoom(room);
                player.setPosition(position);

                // --- Récupération de toutes ses cartes
                player.decodeCards();

                // --- Ajout du joueur à la liste des salons
                lPlayers.add(player);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        SQLHelper.close(pstmt, res);
        return lPlayers;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void encodeCards()
    {
        // --- Récupération de toutes les cartes
        String cards = null;
        // ---
        if (lCards.size() > 0)
        {
            for (Card card : lCards)
            {
                cards += card.getShortName()+" / ";
            }
            cards.substring(0, cards.length() - 3);
        }
        this.cards = cards;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public void decodeCards()
    {
        ObservableList<Card> lCards = FXCollections.observableArrayList();

        // --- récupération de la chaîne de caractère représentant la main d'un joueur
        String cards = getCards();

        // --- Traitement de cette chaîne
        if (cards != null && cards.length() > 0)
        {
            cards.trim();
            String[] aCards = cards.split("/");

            for (String sCard : aCards)
            {
                // TODO : Créer une méthode dans card permettant de récupérer une carte
                // Via son shortName

                Card card = null;
                lCards.add(card);
            }
        }

        // --- On actualise la liste du joueur
        setlCards(lCards);
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
    public String getRoom()
    {
        return room;
    }
    public void setRoom(String room)
    {
        this.room = room;
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
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
}
