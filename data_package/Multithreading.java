package data_package;

public class Multithreading extends Thread {

    // alphabet Tree
    AlphabetTree alphabetTree;

    // Constructor
    public Multithreading(AlphabetTree alphabetTree) {

        this.alphabetTree = alphabetTree;
    }

    public void run() {

        alphabetTree.treeMaking();
    }
}
