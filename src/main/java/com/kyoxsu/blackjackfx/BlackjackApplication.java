package com.kyoxsu.blackjackfx;

import com.kyoxsu.blackjackfx.binders.PlayerBinder;
import com.kyoxsu.blackjackfx.binders.RoomBinder;
import com.kyoxsu.blackjackfx.helpers.SQLHelper;
import com.kyoxsu.blackjackfx.models.Player;
import com.kyoxsu.blackjackfx.models.Room;
import com.kyoxsu.blackjackfx.views.RoomView;
import com.kyoxsu.blackjackfx.views.UserView;
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
    public static Player player = null;
    public static Room room = null;
    // ---
    private Stage stage = new Stage();
    private static BlackjackApplication application;

    @Override
    public void start(Stage stage) throws IOException
    {
        // --- Initialisation de la connexion a la base de données
        SQLHelper.getConnection();

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
    public static void main(String[] args)
    {
        launch();
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static BlackjackApplication getInstance()
    {
        return application;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Stage getStage()
    {
        return stage;
    }
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadLoginScene() throws IOException
    {
        FXMLLoader loginPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("login-view.fxml"));
        Parent root = loginPanelFxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadUserCreationScene() throws IOException
    {
        FXMLLoader userCreationPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("user-creation-view.fxml"));
        Parent root = userCreationPanelFxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadUserScene() throws IOException
    {
        FXMLLoader userPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("user-view.fxml"));
        Parent root = userPanelFxmlLoader.load();

        PlayerBinder playerBinder = new PlayerBinder(player);
        UserView userView = userPanelFxmlLoader.getController();
        userView.setPlayerBinder(playerBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadMainScene() throws IOException
    {
        FXMLLoader mainPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("main-view.fxml"));
        Parent root = mainPanelFxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadSearchScene() throws IOException
    {
        FXMLLoader searchPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("search-view.fxml"));
        Parent root = searchPanelFxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadCreateRoomScene() throws IOException
    {
        FXMLLoader createRoomPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("create-room-view.fxml"));
        Parent root = createRoomPanelFxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadRoomScene() throws IOException
    {
        FXMLLoader roomPanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("room-view.fxml"));
        Parent root = roomPanelFxmlLoader.load();

        RoomBinder roomBinder = new RoomBinder(room);
        RoomView roomView = roomPanelFxmlLoader.getController();
        roomView.setRoomBinder(roomBinder);

        Scene scene = new Scene(root);
        return scene;
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public Scene loadGameScene() throws IOException
    {
        FXMLLoader gamePanelFxmlLoader = new FXMLLoader(BlackjackApplication.class.getResource("game-view.fxml"));
        Parent root = gamePanelFxmlLoader.load();
        Scene scene = new Scene(root);
        return scene;
    }
}