package translation_package;

import data_package.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    public static DataReader dataReader;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void englishToPersian() throws IOException {

        if (dataReader == null)
            dataReader = new DataReader("dictionary.txt");

        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("translator.fxml")));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(pane);
    }

    @FXML
    private void persianToEnglish() {

    }
}
