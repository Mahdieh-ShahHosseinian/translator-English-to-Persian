package data_package;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DataReader {

    // The file for saving the dictionary tree
    private File saveDictionaryTree = null;

    // AlphabetTree
    private AlphabetTree[] alphabetTrees = new AlphabetTree[26];

    // Save tree
    private PrintWriter printWriter;

    // Load tree
    private Scanner fileScanner;

    //time of making Tree
    private long time;

    // Constructor
    public DataReader(String url) throws IOException {

        int alphabet = 97;
        for (int i = 0; i < 26; i++) {
            alphabetTrees[i] = new AlphabetTree((char) alphabet);
            alphabet++;
        }
        readData(url);
    }

    // read data of file
    public void readData(String url) throws IOException {

        saveDictionaryTree = new File("saveDictionaryTree.txt");

        if (saveDictionaryTree.length() == 0) {

            long timeA = System.currentTimeMillis();

            Scanner scanner;
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(url)));
                while (scanner.hasNext()) {

                    Node newNode = new Node(new Word(scanner.next(), scanner.next(), scanner.nextDouble()));

                    int index = newNode.getValue().getText().charAt(0) - 97;

                    alphabetTrees[index].addNode(newNode);
                }
                Multithreading[] multithreadings = new Multithreading[26];
                for (int i = 0; i < 26; i++) {

                    multithreadings[i] = new Multithreading(alphabetTrees[i]);

                    multithreadings[i].start();
                }
                for (int i = 0; i < 26; i++) {
                    try {
                        multithreadings[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                long timeB = System.currentTimeMillis();

                printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(saveDictionaryTree)));

                time = timeB - timeA;

                printWriter.println(time);

                for (int i = 0; i < 26; i++) {

                    printWriter.println(i);

                    alphabetTrees[i].saveTree(printWriter);
                }
                if (printWriter != null) printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            fileScanner = new Scanner(new BufferedReader(new FileReader(saveDictionaryTree)));

            time = fileScanner.nextLong();
            int index = 0;
            while (fileScanner.hasNext()) {
                String input = fileScanner.next();
                try {
                    index = Integer.parseInt(input);
                } catch (Exception e) {
                    alphabetTrees[index].loadTree(new Node(new Word(input, fileScanner.next(), fileScanner.nextDouble())));
                }
            }
        }
    }

    //search node
    public String searchNode(String value) {
        int index = value.charAt(0) - 97;
        return alphabetTrees[index].searchNode(value);
    }

    // getter
    public long getTime() {
        return time;
    }

    public AlphabetTree[] getAlphabetTrees() {
        return alphabetTrees;
    }
}
