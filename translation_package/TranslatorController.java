package translation_package;

import data_package.DataReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TranslatorController {

    public static DataReader dataReader;

    @FXML
    TextArea enterTextArea;

    @FXML
    TextArea translationTextArea;

    @FXML
    Label showTranslationTime;

    @FXML
    Label findBSTTime;

    @FXML
    Button openButton;

    // On action for open button -> choose a txt file
    @FXML
    void openOrder() throws FileNotFoundException {

        enterTextArea.clear();

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TEXT Files", "*.txt"));

        File file = fileChooser.showOpenDialog(openButton.getScene().getWindow());

        if (file != null) {
            Scanner s = new Scanner(file).useDelimiter("\\\\r?\\\\n");
            while (s.hasNext()) enterTextArea.appendText(s.next());
            s.close();
        }
    }

    // On action for translate button
    @FXML
    public void translateOrder() throws IOException {

        if (dataReader == null)
            dataReader = new DataReader("dictionary.txt");

        if (!enterTextArea.getText().equals("")) {

            findBSTTime.setText("Time of finding optimal BST: " + dataReader.getTime() + "ms");

            long timeA = System.currentTimeMillis();

            translationTextArea.clear();

            String[] text = enterTextArea.getText().split("\\W+");
            for (String s : text)
                translationTextArea.appendText(dataReader.searchNode(s.toLowerCase()) + " ");

            long timeB = System.currentTimeMillis();
            showTranslationTime.setText("Translated in " + (timeB - timeA) + "ms");
        }
    }

    // function for go to show tree page
    @FXML
    public void showTree() throws IOException {

        if (dataReader == null)
            dataReader = new DataReader("dictionary.txt");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("show_tree.fxml")));
        Main.scene.setRoot(root);
    }
}
