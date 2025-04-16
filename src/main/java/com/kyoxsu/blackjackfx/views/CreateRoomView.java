package com.kyoxsu.blackjackfx.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class CreateRoomView {

    @FXML
    private Label errorLabel;

    @FXML
    private Slider playerNumberSlider;

    @FXML
    private TextField roomNameField;

    @FXML
    private PasswordField roomPasswordField;

    @FXML
    private ChoiceBox<?> visibilityChoiceBox;

    @FXML
    void onCreateRoom(ActionEvent event) {

    }

    @FXML
    void onReturn(ActionEvent event) {

    }

}