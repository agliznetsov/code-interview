package techiedelight;

public class Node<T> {
    public T data;        // data field
    public Node left, right;    // pointer to the left and right child

    public Node() {
    }

    public Node(T data) {
        this.data = data;
        this.left = this.right = null;
    }

    public Node(T data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
