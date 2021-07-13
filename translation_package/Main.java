package translation_package;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Wednesday June/9/2021
 *
 * @author Mahsa Karimi
 * @author Farkhondeh Arzi
 * @author Mahdieh ShahHosseinian
 */

public class Main extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("translator.fxml")));
        scene = new Scene(root);
        stage.setTitle("Translator");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
