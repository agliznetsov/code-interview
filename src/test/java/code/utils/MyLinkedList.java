
package code.utils;


import org.junit.jupiter.api.Test;

public class MyLinkedList<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public Node<E> getFirst() {
        return first;
    }

    public Node<E> getLast() {
        return last;
    }

    private Node<E> linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        return newNode;
    }

    private Node<E> linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return newNode;
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    public void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    /**
     * Unlinks non-null last node l.
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    public E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        size--;
        return element;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NullPointerException();
        return unlinkFirst(f);
    }

    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NullPointerException();
        return unlinkLast(l);
    }

    public Node<E> addFirst(E e) {
        return linkFirst(e);
    }

    public Node<E> addLast(E e) {
        return linkLast(e);
    }

    public int size() {
        return size;
    }

    public void print() {
        System.out.print("[");
        Node node = first;
        while (node != null) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println("]");
    }

    public static class Node<E> {
        public final E item;
        public Node<E> next;
        public Node<E> prev;

        public Node(E item) {
            this.item = item;
        }

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


    @Test
    void test() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        Node<Integer> node = list.addLast(10);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.print();
    }


}
