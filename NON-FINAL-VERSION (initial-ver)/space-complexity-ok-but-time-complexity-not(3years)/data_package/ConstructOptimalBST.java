package data_package;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConstructOptimalBST {

    // Save tree
    private PrintWriter printWriter;

    // Load tree
    private Scanner fileScanner;

    // Time of creating the tree
    long createTime;

    //root of BST
    private Node root;

    // all roots
    CleverArray[] roots;

    // Array List of All Nodes
    private ArrayList<Node> ArrayListOfNodes;

    // constructor
    public ConstructOptimalBST(CleverArray[] roots, ArrayList<Node> arrayListOfNodes) {

        this.roots = roots;
        this.ArrayListOfNodes = arrayListOfNodes;

        createTree(1, arrayListOfNodes.size() - 1, 0);
    }

    public ConstructOptimalBST() {
    }

    // function for create tree
    public void createTree(int i, int j, int last) {

        if (i == j + 1) return;
        if (last == 0) {
            root = ArrayListOfNodes.get(roots[i].get(j));
        } else if (j < last) {
            ArrayListOfNodes.get(last).setLeft(ArrayListOfNodes.get(roots[i].get(j)));
        } else {
            ArrayListOfNodes.get(last).setRight(ArrayListOfNodes.get(roots[i].get(j)));
        }

        createTree(i, roots[i].get(j) - 1, roots[i].get(j));
        createTree(roots[i].get(j) + 1, j, roots[i].get(j));
    }

    //function for search word in tree
    public String search(String key) {

        Node node = searchKey(root, key);
        if (node == null)
            return "؟";
        else
            return node.getValue().getTranslation();
    }

    Node searchKey(Node node, String value) {

        if (node == null || node.getValue().getText().compareTo(value) == 0)
            return node;
        if (value.compareTo(node.getValue().getText()) < 0)
            return searchKey(node.getLeft(), value);
        return searchKey(node.getRight(), value);
    }

    // Save the tree in file to use later =/
    public void saveTreeInFile(File file, long time) throws IOException {

        printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));
        printWriter.println(time);
        preOrderSaver(root);

        if (printWriter != null) printWriter.close();
    }

    // Save DFS
    private void preOrderSaver(Node node) throws IOException {

        if (node == null) return;
        printWriter.println(node.getValue().getText() + " " + node.getValue().getTranslation() + " " +
                node.getValue().getProbability());
        preOrderSaver(node.getLeft());
        preOrderSaver(node.getRight());
    }

    // Load the tree in file to use later =\
    public void loadTreeInFile(File file) throws IOException {

        fileScanner = new Scanner(new BufferedReader(new FileReader(file)));
        createTime = Long.parseLong(fileScanner.next());

        root = new Node(new Word(fileScanner.next(), fileScanner.next(), fileScanner.nextDouble()));

        while (fileScanner.hasNext()) {
            preOrderLoader(root, new Node(new Word(fileScanner.next(), fileScanner.next(), fileScanner.nextDouble())));
        }
    }

    // Load DFS
    private void preOrderLoader(Node root, Node node) {

        if (root.compareTo(node) > 0) {
            if (root.getLeft() != null) preOrderLoader(root.getLeft(), node);
            else root.setLeft(node);
        } else {
            if (root.getRight() != null) preOrderLoader(root.getRight(), node);
            else root.setRight(node);
        }
    }
}
