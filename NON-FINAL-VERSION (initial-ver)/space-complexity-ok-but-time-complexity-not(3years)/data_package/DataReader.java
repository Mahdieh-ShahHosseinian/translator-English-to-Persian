package data_package;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DataReader {

    // The file for saving the dictionary tree
    private File saveDictionaryTree = null;

    // construct Optimal BST
    private ConstructOptimalBST constructOptimalBST;

    // optimal BST Finder
    private OptimalBSTFinder optimalBSTFinder;

    // Array List of All Nodes
    private final ArrayList<Node> ArrayListOfNodes = new ArrayList<>();

    //ArrayList of probability of words that exist
    private final ArrayList<Integer> existingWordsProbabilities = new ArrayList<>();

    // Constructor
    public DataReader(String url) throws IOException {
        readData(url);
    }

    // read data of file
    public void readData(String url) throws IOException {

        saveDictionaryTree = new File(System.getProperty("user.home") + "\\Documents\\saveDictionaryTree.txt");

        if (saveDictionaryTree.length() == 0) {

            long timeA = System.currentTimeMillis();

            Scanner scanner;
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(url)));
                ArrayListOfNodes.add(new Node(new Word("#", "#", 0)));
                while (scanner.hasNext()) {

                    Node newNode = new Node(new Word(scanner.next(), scanner.next(), scanner.nextDouble()));
                    ArrayListOfNodes.add(newNode);
                }

                Collections.sort(ArrayListOfNodes);

                existingWordsProbabilities.add(0);
                for (Node arrayListOfNode : ArrayListOfNodes) {
                    existingWordsProbabilities.add(arrayListOfNode.getValue().getProbability());
                }
                this.optimalBSTFinder = new OptimalBSTFinder(ArrayListOfNodes.size(), existingWordsProbabilities);

                constructOptimalBST();
                long timeB = System.currentTimeMillis();
                constructOptimalBST.saveTreeInFile(saveDictionaryTree, timeB - timeA);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            constructOptimalBST = new ConstructOptimalBST();
            constructOptimalBST.loadTreeInFile(saveDictionaryTree);
        }
    }

    // construct Optimal BST
    void constructOptimalBST() {
        this.constructOptimalBST = new ConstructOptimalBST(optimalBSTFinder.findBST(), ArrayListOfNodes);
    }

    // getter and setter
    public ConstructOptimalBST getConstructOptimalBST() {
        return constructOptimalBST;
    }
}
