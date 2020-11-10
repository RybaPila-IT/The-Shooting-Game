/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package The.Duck.Game;

import FXMLControlers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainMenuManager manager = new MainMenuManager(primaryStage);
    }
}
