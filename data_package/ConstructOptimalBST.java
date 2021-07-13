package data_package;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ConstructOptimalBST {

    // Arraylist of node
    ArrayList<Node> arrayListOfNodes = new ArrayList<>();

    //ArrayList of probability of words that exist
    private final ArrayList<Integer> existingWordsProbabilities = new ArrayList<>();

    //optimal BST Finder
    OptimalBSTFinder optimalBSTFinder;

    // Save tree
    private PrintWriter printWriter;

    // Time of creating the tree
    long createTime;

    //root of BST
    private Node root;

    // all roots
    CleverArray[] roots;

    // add node
    public void addNode(Node node) {

        arrayListOfNodes.add(node);
    }

    // find optimalBST
    public void findOptimalBST() {

        Collections.sort(arrayListOfNodes);

        for (Node node : arrayListOfNodes) {
            existingWordsProbabilities.add(node.getValue().getProbability());
        }

        optimalBSTFinder = new OptimalBSTFinder(arrayListOfNodes.size(), existingWordsProbabilities);

        roots = optimalBSTFinder.findBST();

        createTree(1, arrayListOfNodes.size() - 1, 0);
    }

    // constructor
    public ConstructOptimalBST() {
        arrayListOfNodes.add(new Node(new Word("#", "#", 0)));
        existingWordsProbabilities.add(0);
    }

    // function for create tree
    private void createTree(int i, int j, int last) {

        if (i == j + 1) return;
        if (last == 0) {
            root = arrayListOfNodes.get(roots[i].get(j));
        } else if (j < last) {
            arrayListOfNodes.get(last).setLeft(arrayListOfNodes.get(roots[i].get(j)));
        } else {
            arrayListOfNodes.get(last).setRight(arrayListOfNodes.get(roots[i].get(j)));
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
    public void saveTreeInFile(PrintWriter printWriter) throws IOException {

        this.printWriter = printWriter;
        preOrderSaver(root);
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
    public void loadTreeInFile(Node node) {

        if (root == null) {
            root = new Node(new Word(node.getValue().getText(), node.getValue().getTranslation(), node.getValue().getProbability()));
        } else {
            preOrderLoader(root, node);
        }
    }

    // Load DFS
    private void preOrderLoader(Node root, Node node) {

        if (node.getValue().getText().compareTo(root.getValue().getText()) < 0) {
            if (root.getLeft() != null) {
                preOrderLoader(root.getLeft(), node);
            } else {
                root.setLeft(node);
            }
        } else if (node.getValue().getText().compareTo(root.getValue().getText()) > 0) {
            if (root.getRight() != null) {
                preOrderLoader(root.getRight(), node);
            } else {
                root.setRight(node);
            }
        }
    }

    // getter
    public Node getRoot() {
        return root;
    }
}
