package com.kyoxsu.blackjackfx.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
//------------------------------------------------------------------------------
/**
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class HelloController
{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick()
    {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}