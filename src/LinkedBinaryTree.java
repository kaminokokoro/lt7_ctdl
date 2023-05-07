import java.util.ArrayList;

public class LinkedBinaryTree<E> implements BinaryTreeInterface<E> {
    protected static class Node<E> implements Comparable<Node<E>> {
        private E element; // an element stored at this node
        private Node<E> parent; // a reference to the parent node (if any)
        private Node<E> left; // a reference to the left child
        private Node<E> right; // a reference to the right child
        // Constructs a node with the given element and neighbors. ∗/
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild){
// Sinh viên hoàn thiện phương thức
            this.element = e;
            this.parent = above;
            this.left = leftChild;
            this.right = rightChild;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node<E> o) {
            return this.element.hashCode() - o.element.hashCode();
        }
    }
    protected Node<E> root = null; // root of the tree
    private int size = 0; // number of nodes in the tree
    public LinkedBinaryTree() { // constructs an empty binary tree
    }

    //update methods
    public Node<E> addRoot(E element) {
        Node node = new Node(element, null, null, null);
        root= node;
        size++;
        return node;

// Add element to root of an empty tree
    }
    public Node<E> addLeft(Node p, E element) {
// Add element to left child node of p if empty
        Node node = new Node(element, p, null, null);
        p.left = node;
        size++;
        return node;
    }
    public Node<E> addRight(Node p, E element) {
// Add element to right child node of p if empty
        Node node = new Node(element, p, null, null);
        p.right = node;
        size++;
        return node;
    }
    public void set(Node p, E element) {
// set element to node p
        p.element = element;
    }

    @Override
    public E root() {
        return root.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public String toString() {
        inorderTraversal(root);
        return "LinkedBinaryTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }
    public void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.element + " ");
        inorderTraversal(node.right);
    }



    public boolean checkForBST(Node<E> node) {
        E[] result = inOrder();
        for(int i = 0; i < result.length-1; i++){
            if(result[i].hashCode() > result[i+1].hashCode()){
                return false;
            }
        }
        return true;
    }

    public int calculateSize(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + calculateSize(root.left) + calculateSize(root.right);
    }

    public E[] inOrder() {
        size= calculateSize(root);
        E[] result = (E[]) new Object[size];
        Node<E> current = root;
        int i = 0;
        while (current != null) {
            if (current.left == null) {
                result[i++] = current.element;
                current = current.right;
            } else {
                Node<E> predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    result[i++] = current.element;
                    current = current.right;
                }
            }
        }
        return result;
    }

    public ArrayList<E> binaryTreeToArray(ArrayList<E> arrayList,Node node) {
        if (node == null) {
            return arrayList;
        }
        binaryTreeToArray(arrayList,node.left);
        arrayList.add((E) node.element);
        binaryTreeToArray(arrayList,node.right);
        return arrayList;
    }
    public void binaryTreeToBST(){
        ArrayList<E> arrayList = new ArrayList<>();
        binaryTreeToArray(arrayList,root);
        arrayList.sort((o1, o2) -> o1.hashCode() - o2.hashCode());
        ArrayListToBinaryTree(arrayList,root);

    }

    public void ArrayListToBinaryTree(ArrayList<E> arrayList,Node<E> current){
        if (current == null) {
            return ;
        }
        ArrayListToBinaryTree(arrayList,current.left);
        current.element = arrayList.get(0);
        arrayList.remove(0);
        ArrayListToBinaryTree(arrayList,current.right);
    }
    public void PreorderTraversalToBST(ArrayList<E> arrayList, Node<E> current) {
        if (arrayList.size() == 0) {
            return ;
        }
        current.setElement(arrayList.get(0));
        arrayList.remove(0);
        if(arrayList.size() == 0){
            return;
        }
        if(current.getElement().hashCode() > arrayList.get(0).hashCode()){
            current.setLeft(new LinkedBinaryTree.Node<>(null, null, null, null));
            PreorderTraversalToBST(arrayList, current.getLeft());
        }
        if (arrayList.size() == 0) {
            return;
        }
        if (current.getElement().hashCode() < arrayList.get(0).hashCode()) {
            current.setRight(new LinkedBinaryTree.Node<>(null, null, null, null));
            PreorderTraversalToBST(arrayList, current.getRight());
        }

    }

}
