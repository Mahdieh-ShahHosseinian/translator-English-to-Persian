package data_package;

public class Node implements Comparable<Node>{
    //right Node
    private Node right;

    //left Node
   private Node left;

    //value of Node
   private Word value;

    //constructor 
    public Node(Word value) {
        this.value = value;
    }

    //Compare
    @Override
    public int compareTo(Node o) {
        return value.getText().compareTo(o.value.getText());
    }

    //getter and setter
    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Word getValue() {
        return value;
    }

    public void setValue(Word value) {
        this.value = value;
    }


}
