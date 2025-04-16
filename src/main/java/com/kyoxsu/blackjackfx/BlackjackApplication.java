package com.kyoxsu.blackjackfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
//------------------------------------------------------------------------------
/**
 * C'est le point d'entrée de l'application.
 * Elle contient notamment toutes les méthodes nécéssaires au basculement entre
 * les différentes scenes.
 *
 * @author Kyo
 * @modif Kyo : 2025-04-15 : Création.
 */
//------------------------------------------------------------------------------
public class BlackjackApplication extends Application
{
    private Stage stage = new Stage();
    private static BlackjackApplication application;
    //private static EntityBinder entityBinder;

    @Override
    public void start(Stage stage) throws IOException
    {
        // TODO : Compléter le reste pour l'architechture MVVM
        // --- Construction du MVVM
        //EntityModel entityModel = new EntityModel();
        //EntityBinder entityBinder = new EntityBinder(entityModel);
        //this.entityBinder = entityBinder;

        application = this;
        Scene scene = loadLoginScene();
        this.stage.setTitle("-- Three Layers Project --");
        this.stage.setScene(scene);
        this.stage.setMaximized(true);
        this.stage.show();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        launch();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static BlackjackApplication getInstance() {
        return application;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Stage getStage() {
        return stage;
    }
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadLoginScene() throws IOException
    {
        FXMLLoader loginPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("login-view.fxml"));
        Parent root = loginPanelFxmlLoader.load();

        //LoginView loginView = loginPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadUserCreationScene() throws IOException
    {
        FXMLLoader userCreationPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("user-creation-view.fxml"));
        Parent root = userCreationPanelFxmlLoader.load();

        //LoginView loginView = userCreationPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadUserScene() throws IOException
    {
        FXMLLoader userPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("user-view.fxml"));
        Parent root = userPanelFxmlLoader.load();

        //LoginView loginView = userPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadMainScene() throws IOException
    {
        FXMLLoader mainPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("main-view.fxml"));
        Parent root = mainPanelFxmlLoader.load();

        //LoginView loginView = mainPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadSearchScene() throws IOException
    {
        FXMLLoader searchPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("search-view.fxml"));
        Parent root = searchPanelFxmlLoader.load();

        //LoginView loginView = searchPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadCreateRoomScene() throws IOException
    {
        FXMLLoader createRoomPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("create-room-view.fxml"));
        Parent root = createRoomPanelFxmlLoader.load();

        //LoginView loginView = createRoomPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadRoomScene() throws IOException
    {
        FXMLLoader roomPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("room-view.fxml"));
        Parent root = roomPanelFxmlLoader.load();

        //LoginView loginView = roomPanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadGameScene() throws IOException
    {
        FXMLLoader gamePanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("game-view.fxml"));
        Parent root = gamePanelFxmlLoader.load();

        //LoginView loginView = gamePanelFxmlLoader.getController();
        //loginView.setEntityBinder(entityBinder);

        Scene scene = new Scene(root);
        return scene;
    }
}