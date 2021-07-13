package translation_package;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class TranslatorController {

    @FXML
    TextArea enterTextArea;

    @FXML
    TextArea translationTextArea;

    @FXML
    Label showTranslationTime;

    @FXML
    Button openButton;

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

    public void translateOrder() {

        long timeA = System.currentTimeMillis();

        translationTextArea.clear();

        String[] text = enterTextArea.getText().split("\\W+");
        for (String s : text)
            translationTextArea.appendText(MenuController.dataReader.getConstructOptimalBST().search(s.toLowerCase()) + " ");

        long timeB = System.currentTimeMillis();
        showTranslationTime.setText("Translated in " + (timeB - timeA) + "ms");
    }
}
