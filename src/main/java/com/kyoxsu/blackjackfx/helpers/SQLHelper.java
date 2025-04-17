package com.kyoxsu.blackjackfx.helpers;

import java.sql.*;
//------------------------------------------------------------------------------
/**
 * Cette classe permet de simplifier la manipulation SQL
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class SQLHelper
{
    private static final String URL = "jdbc:mysql://localhost:3306/blackjack";
    private static final String USER = "root";
    private static final String PASSWORD = "Kyo100705tete*";

    private static Connection con;

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static Connection getConnection()
    {
        Connection con;
        // ---
        if (SQLHelper.con == null)
        {
            try
            {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
            if (con != null)
            {
                System.out.println("Connexion établie avec succès");
            }
            con = con;
        }
        else
        {
            con = SQLHelper.con;
        }
        return con;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void close(PreparedStatement ptsmt, ResultSet res)
    {
        try
        {
            if (ptsmt != null) ptsmt.close();
            if (res != null) res.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
