public class BinarySearchTree<E> implements BinaryTreeInterface<E> {
    class Node<E> implements Comparable<E> {

        E data;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }


        @Override
        public int compareTo(E o) {
            return data.hashCode() - o.hashCode();
        }
    }

    Node<E> root;
    int size = 0;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public E root() {
        return root.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }
        Node<E> current = root;
        while (true) {
            if (current.data.equals(data)) {
                return;
            }
            if (current.data.hashCode() > data.hashCode()) {
                if (current.left == null) {
                    current.left = newNode;
                    newNode.parent = current;
                    size++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    newNode.parent = current;
                    size++;
                    return;
                }
                current = current.right;
            }
        }
    }

    public E[] inOrder() {
        E[] result = (E[]) new Object[size];
        Node<E> current = root;
        int i = 0;
        while (current != null) {
            if (current.left == null) {
                result[i++] = current.data;
                current = current.right;
            } else {
                Node<E> pre = current.left;
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                } else {
                    pre.right = null;
                    result[i++] = current.data;
                    current = current.right;
                }
            }
        }
        return result;
    }


    public void printTree() {
        E[] result = inOrder();
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public E findMedian() {
        E[] result = inOrder();
        if (result.length % 2 == 1) return result[result.length / 2];
        double k = (double) ((int) result[result.length / 2] + (int) result[result.length / 2 - 1]) / 2;
        return (E) (Double) k;
    }

    public boolean search(E data) {
        Node<E> current = root;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            if (current.data.hashCode() > data.hashCode()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public E findMaximum() {
        Node<E> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public E findSuccessor(E data) {
        E[] result = inOrder();
        for (int i = 0; i < result.length; i++) {
            if (result[i].equals(data)) {
                return result[i + 1];
            }
        }
        return null;
    }

    public String findPredecessorAndSuccessor(E data) {
        E[] result = inOrder();
        for (int i = 0; i < result.length; i++) {
            if (result[i].equals(data)) {
                return "Predecessor:" + result[i - 1] + "\nSuccessor: " + result[i + 1];
            }
        }
        return null;
    }

    public E closestNeighbor(E data) {
        E[] result = inOrder();
        E neighbor = null;
        for (int i = 0; i < result.length; i++) {
            if (result[i].hashCode() > data.hashCode()) {
                return neighbor;
            }
            neighbor = result[i];
        }
        return null;
    }

    public E ceiling(E data) {
        E[] result = inOrder();
        for (int i = 0; i < result.length; i++) {
            if (result[i].hashCode() >= data.hashCode()) {
                return result[i];
            }
        }
        return null;
    }


    public Node delete(Node root, E data) {
        if (root == null) {
            return null;
        }
        if (root.data.hashCode() > data.hashCode()) {
            Node temp = delete(root.left, data);
            if (temp != null) {
                root.left = temp;
                temp.parent = root;
            } else {
                root.left = null;
            }
        }
        if (root.data.hashCode() < data.hashCode()) {
            Node temp = delete(root.right, data);
            if (temp != null) {
                root.right = temp;
                temp.parent = root;
            } else {
                root.right = null;
            }

        }
        if(root.data.hashCode() == data.hashCode()){
            if (root.left == null) {
                size--;
                return root.right;
            }
            if (root.right == null) {
                size--;
                return root.left;
            } else {
                root.data = findMinimum(root.right);
                root.right = delete(root.right, (E) root.data);
            }

        }
        return root;
    }

    public E findMinimum(Node start) {
        Node<E> current = start;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    public Node deleteNodesGreaterThanK(Node root, E k) {
        if (root == null) {
            size = calculateSize(this.root);
            return root;
        }
        if (root.data.hashCode() >= k.hashCode()) {
            root.left = deleteNodesGreaterThanK(root.left, k);
        }
        if (root.data.hashCode() == k.hashCode()) {
            if (root.right != null) {
                Node<E> current = root.right;
                int i = 0;
                while (current != null) {
                    if (current.left == null) {
                        i++;
                        current = current.right;
                    } else {
                        Node<E> pre = current.left;
                        while (pre.right != null && pre.right != current) {
                            pre = pre.right;
                        }
                        if (pre.right == null) {
                            pre.right = current;
                            current = current.left;
                        } else {
                            pre.right = null;
                            i++;
                            current = current.right;
                        }
                    }
                }
                size -= i;
                root.right = null;
            }

            if (root.parent != null && root.parent.left == root) {
                this.root = root;
            }
        } else {
            root.right = deleteNodesGreaterThanK(root.right, k);
        }
        return root;
    }

    public int calculateSize(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + calculateSize(root.left) + calculateSize(root.right);
    }

    public int countNodesInRange(E k1, E k2) {
        E[] result = inOrder();
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i].hashCode() >= k1.hashCode() && result[i].hashCode() <= k2.hashCode()) {
                count++;
            }
        }
        return count;
    }

    public E KthSmallest(int k) {
        E[] result = inOrder();
        return result[k - 1];
    }

    public E KthLargest(int k) {
        E[] result = inOrder();
        return result[result.length - k];
    }

    public void changeOfKey(E data1, E data2) {
        delete(this.root, data1);
        add(data2);
    }

}
