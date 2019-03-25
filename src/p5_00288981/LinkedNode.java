package p5_00288981;

public class LinkedNode<E> {

    protected E data;
    protected LinkedNode<E> left;
    protected LinkedNode<E> right;
    protected LinkedNode<E> parent;

    public LinkedNode() {
        this.data = null;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public LinkedNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public LinkedNode(E data, LinkedNode<E> parent) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    public LinkedNode(E data, LinkedNode<E> left, LinkedNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = null;

    }

    public LinkedNode(E data, LinkedNode<E> left, LinkedNode<E> right, LinkedNode<E> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;

    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public LinkedNode<E> getLeft() {
        return left;
    }

    public void setLeft(LinkedNode<E> left) {
        this.left = left;
    }

    public LinkedNode<E> getRight() {
        return right;
    }

    public void setRight(LinkedNode<E> right) {
        this.right = right;
    }

    public LinkedNode<E> getParent() {
        return parent;
    }

    public void setParent(LinkedNode<E> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

}
