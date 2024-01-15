package techiedelight;

public class Node {
    public String data;        // data field
    public Node left, right;    // pointer to the left and right child

    public Node() {
    }

    public Node(String data) {
        this.data = data;
        this.left = this.right = null;
    }

    public Node(String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
