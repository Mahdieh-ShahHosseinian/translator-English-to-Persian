package data_package;

import java.util.Arrays;

public class CleverArray {

    private int startIndex;
    private int[] idiotArray;

    public CleverArray(int size, int startIndex) {
        idiotArray = new int[size];
        this.startIndex = startIndex;
    }

    public void add(int index, int entire) {
        idiotArray[index - startIndex] = entire;
    }

    public int get(int index) {
        return idiotArray[index - startIndex];
    }

    @Override
    public String toString() {
        return Arrays.toString(idiotArray);
    }
}
