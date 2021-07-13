package data_package;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AlphabetTree {

    // alphabet of tree
    private char alphabet;

    // tree of words
    private ConstructOptimalBST[] constructOptimalBSTs = new ConstructOptimalBST[4];

    // constructor
    public AlphabetTree(char alphabet) {

        this.alphabet = alphabet;
        for (int i = 0; i < 4; i++) {
            constructOptimalBSTs[i] = new ConstructOptimalBST();
        }
    }

    // add node
    public void addNode(Node node) {
        switch (node.getValue().getText().length()) {
            case 1, 2 -> constructOptimalBSTs[0].addNode(node);
            case 3 -> constructOptimalBSTs[1].addNode(node);
            case 4 -> constructOptimalBSTs[2].addNode(node);
            default -> constructOptimalBSTs[3].addNode(node);
        }
    }

    // tree making
    public void treeMaking() {

        for (ConstructOptimalBST constructOptimalBST : constructOptimalBSTs) {
            constructOptimalBST.findOptimalBST();
        }
    }

    // save Tree
    public void saveTree(PrintWriter printWriter) {

        for (ConstructOptimalBST constructOptimalBST : constructOptimalBSTs) {

            try {
                constructOptimalBST.saveTreeInFile(printWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //load Tree
    public void loadTree(Node node) {
        switch (node.getValue().getText().length()) {
            case 1, 2 -> constructOptimalBSTs[0].loadTreeInFile(node);
            case 3 -> constructOptimalBSTs[1].loadTreeInFile(node);
            case 4 -> constructOptimalBSTs[2].loadTreeInFile(node);
            default -> constructOptimalBSTs[3].loadTreeInFile(node);
        }
    }


    // search Node
    public String searchNode(String value) {
        switch (value.length()) {
            case 1, 2 -> {
                return constructOptimalBSTs[0].search(value);
            }
            case 3 -> {
                return constructOptimalBSTs[1].search(value);
            }
            case 4 -> {
                return constructOptimalBSTs[2].search(value);
            }
            default -> {
                return constructOptimalBSTs[3].search(value);
            }
        }
    }

    // getter
    public char getAlphabet() {
        return alphabet;
    }

    public ConstructOptimalBST[] getConstructOptimalBSTs() {
        return constructOptimalBSTs;
    }
}
