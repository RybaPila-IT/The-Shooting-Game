package The.Duck.Game;

import FXMLControlers.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenuManager {

    private static final String mainMenuFXMLPath = "/MainMenu.fxml";
    private static final String theShootGame = "The Shoot Game";

    private Stage mainMenuStage;
    private Scene mainMenuScene;
    private MainMenuController controller;
    private GameBoardManager boardManager;

    private void setQuitButton() {
        controller.getQuitButton().setOnMouseClicked(MouseEvent -> mainMenuStage.close());
    }

    private void setNewGameButton() {
        controller.getNewGameButton().setOnMouseClicked(MouseEvent -> boardManager.StartGameBoard());
    }

    public MainMenuManager(Stage primaryStage) {

        mainMenuStage = primaryStage;
        mainMenuStage.setTitle(theShootGame);
        mainMenuStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(mainMenuFXMLPath));

        try {

            Parent root = loader.load();
            controller = loader.getController();
            mainMenuScene = new Scene(root);
            mainMenuStage.setScene(mainMenuScene);
            boardManager = new GameBoardManager(mainMenuStage);
            mainMenuStage.show();


            setQuitButton();
            setNewGameButton();

        } catch (IOException e) {

            System.err.println("Error has occured in MainMenuManager. Unable to load FXML file.");
            System.err.println(e.toString());

        }

    }


}
