package translation_package;

import data_package.Node;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShowTreeController implements Initializable {

    // button for back to last page
    @FXML
    public Button backButton;

    // tree view
    @FXML
    private TreeView<String> treeView;

    // draw tree for show
    public void drawTree() {

        TreeItem<String> treeItem = new TreeItem<>();
        treeItem.setValue("Show tree: ");

        for (int i = 0; i < 26; i++) {
            TreeItem<String> treeItem0 = new TreeItem<>(String.valueOf(TranslatorController.dataReader.getAlphabetTrees()[i].getAlphabet()));
            for (int j = 0; j < 4; j++) {

                if (j == 0) {

                    TreeItem<String> treeItem1 = new TreeItem<>("<= 2");
                    if (TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot() != null) {
                        TreeItem<String> treeItem2 = new TreeItem<>(TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getText() + " [" + TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getTranslation() + "]");
                        treeItem1.getChildren().add(treeItem2);
                        addItems(treeItem2, TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot());

                    }
                    treeItem0.getChildren().add(treeItem1);

                } else if (j == 1) {

                    TreeItem<String> treeItem1 = new TreeItem<>("== 3");
                    if (TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot() != null) {
                        TreeItem<String> treeItem2 = new TreeItem<>(TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getText() + " [" + TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getTranslation() + "]");
                        treeItem1.getChildren().add(treeItem2);
                        addItems(treeItem2, TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot());

                    }
                    treeItem0.getChildren().add(treeItem1);

                } else if (j == 2) {

                    TreeItem<String> treeItem1 = new TreeItem<>("== 4");
                    if (TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot() != null) {

                        TreeItem<String> treeItem2 = new TreeItem<>(TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getText() + " [" + TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getTranslation() + "]");
                        treeItem1.getChildren().add(treeItem2);
                        addItems(treeItem2, TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot());


                    }
                    treeItem0.getChildren().add(treeItem1);
                } else {

                    TreeItem<String> treeItem1 = new TreeItem<>(">= 5");
                    if (TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot() != null) {

                        TreeItem<String> treeItem2 = new TreeItem<>(TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getText() + " [" + TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot()
                                .getValue().getTranslation() + "]");
                        treeItem1.getChildren().add(treeItem2);
                        addItems(treeItem2, TranslatorController.dataReader.getAlphabetTrees()[i].getConstructOptimalBSTs()[j].getRoot());


                    }
                    treeItem0.getChildren().add(treeItem1);
                }
            }
            treeItem.getChildren().add(treeItem0);
        }
        this.treeView.setRoot(treeItem);
    }

    // add item to tree item
    private void addItems(TreeItem<String> currentTree, Node currentNode) {

        if (currentNode.getLeft() != null) {
            TreeItem<String> leftItem = new TreeItem<>(currentNode.getLeft().getValue().getText()
                    + " [" + currentNode.getLeft().getValue().getTranslation() + "]");
            currentTree.getChildren().add(leftItem);
            addItems(leftItem, currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            TreeItem<String> rightItem = new TreeItem<>(currentNode.getRight().getValue().getText()
                    + " [" + currentNode.getRight().getValue().getTranslation() + "]");
            currentTree.getChildren().add(rightItem);
            addItems(rightItem, currentNode.getRight());
        }
    }

    // On action for back button
    @FXML
    public void backToTranslator() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("translator.fxml")));
        Main.scene.setRoot(root);
    }

    // function for call draw tree
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawTree();
    }
}