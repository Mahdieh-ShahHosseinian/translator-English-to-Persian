package data_package;
import java.util.ArrayList;
public class OptimalBSTFinder {

    // number of nodes
    private final int numberOfNode;

    // ArrayList of probability of words that exist
    private final ArrayList<Integer> existingWordsProbabilities;

    // ArrayList of probability of words that doesn't exit
    private int notExistingWordsProbabilities;

    // constructor
    OptimalBSTFinder(int numberOfNode, ArrayList<Integer> existingWordsProbabilities) {

        this.numberOfNode = numberOfNode;
        this.existingWordsProbabilities = existingWordsProbabilities;
        setProbabilitiesNotExistWord();
    }

    // find optimal BST O(N^3)
    public CleverArray[] findBST() {

        // array for all costs of make tree
        CleverArray[] costs = new CleverArray[numberOfNode + 1];
        for (int i = 0; i < numberOfNode + 1; i++) {
            costs[i] = new CleverArray(numberOfNode - i + 1, i - 1);
        }

        // array for all weights of make tree
        int weights = notExistingWordsProbabilities;

        // array for all roots of make tree
        CleverArray[] roots = new CleverArray[numberOfNode];
        for (int i = 0; i < numberOfNode; i++) {
            roots[i] = new CleverArray(numberOfNode - i, i);
        }

        // filling the main diameter
        for (int i = 1; i < numberOfNode + 1; i++) {
            costs[i].add(i - 1, notExistingWordsProbabilities);
        }
        // filling the other diameters
        for (int l = 1; l < numberOfNode; l++) {

            for (int i = 1; i < numberOfNode - l + 1; i++) {

                int j = i + l - 1;
                costs[i].add(j, Integer.MAX_VALUE);
                weights = weights + existingWordsProbabilities.get(j) + notExistingWordsProbabilities;

                //find the optimal costs and roots
                for (int r = i; r <= j; r++) {

                    int temp = costs[i].get(r - 1) + costs[r + 1].get(j) + weights;

                    if (temp < costs[i].get(j)) {
                        costs[i].add(j, temp);
                        roots[i].add(j, r);
                    }
                }
            }
        }
//        for (CleverArray cost : costs) {
//            System.out.println(cost);
//        }
//        System.out.println();
//        for (CleverArray cleverArray : roots) {
//            System.out.println(cleverArray);
//        }
        return roots;
    }

    //setter
    public void setProbabilitiesNotExistWord() {

        for (int i = 0; i < numberOfNode; i++)
            notExistingWordsProbabilities += existingWordsProbabilities.get(i);
        notExistingWordsProbabilities = (10000000 - notExistingWordsProbabilities) / (numberOfNode + 1);
    }
}
