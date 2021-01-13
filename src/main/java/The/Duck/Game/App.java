/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package The.Duck.Game;

import The.Duck.Game.GameManagers.MainMenuManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainMenuManager manager = new MainMenuManager();
        manager.showMainMenu();
    }
}
