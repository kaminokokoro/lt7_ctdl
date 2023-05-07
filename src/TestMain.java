import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        LinkedBinaryTree.Node<String> root = tree.addRoot("5");
        LinkedBinaryTree.Node n1=tree.addLeft(root, "3");
        LinkedBinaryTree.Node n2=tree.addRight(root, "7");
        LinkedBinaryTree.Node n3=tree.addLeft(n1, "2");
        LinkedBinaryTree.Node n4=tree.addRight(n1, "4");
        LinkedBinaryTree.Node n5=tree.addLeft(n2, "6");
        LinkedBinaryTree.Node n6=tree.addRight(n2, "8");
        LinkedBinaryTree.Node n7=tree.addLeft(n3, "1");
        LinkedBinaryTree.Node n8=tree.addRight(n6, "9");
        LinkedBinaryTree.Node n9=tree.addRight(n8, "10");
        System.out.println("tree:");
        tree.inorderTraversal(root);
        System.out.println();
        if (tree.checkForBST(root)) {
            System.out.println("This is a BST");
        } else {
            System.out.println("This is not a BST");
        }

        LinkedBinaryTree<String> tree2 = new LinkedBinaryTree<>();
        LinkedBinaryTree.Node<String> root2 = tree2.addRoot("5");
        LinkedBinaryTree.Node n21=tree2.addLeft(root2, "9");
        LinkedBinaryTree.Node n22=tree2.addRight(root2, "7");
        LinkedBinaryTree.Node n23=tree2.addLeft(n21, "20");
        LinkedBinaryTree.Node n24=tree2.addRight(n21, "4");
        LinkedBinaryTree.Node n25=tree2.addLeft(n22, "6");
        LinkedBinaryTree.Node n26=tree2.addRight(n22, "1");
        System.out.println("tree2:");
        tree2.inorderTraversal(root2);
        System.out.println();
        if (tree2.checkForBST(root2)) {
            System.out.println("This is a BST");
        } else {
            System.out.println("This is not a BST");
        }



        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        bst.add(6);
        bst.add(8);
        bst.add(1);
        bst.add(9);
        bst.add(10);
        System.out.print("bst:");
        bst.printTree();
        System.out.println("Median: "+bst.findMedian());

        System.out.println("Count nodes in range 3-7: "+bst.countNodesInRange(3,7));
        System.out.println("3th smallest element: "+bst.KthSmallest(3));
        System.out.println("3th largest element: "+bst.KthLargest(3));
        System.out.println("smallest element: "+bst.findMinimum(bst.root));
        System.out.println("Successor of 5:"+bst.findSuccessor(5));
        System.out.println("Predecessor and Successor of 5:\n"+bst.findPredecessorAndSuccessor(5));
        System.out.println("Closest element to 5: "+bst.closestNeighbor(5));
        System.out.println("ceil of 5: "+bst.ceiling(5));
        System.out.println("search 8:"+bst.search(8));
        System.out.println("search 11:"+bst.search(11));
        System.out.println("add 11:");
        bst.add(11);
        bst.printTree();
        System.out.println("change 11 to 12:");
        bst.changeOfKey(11,12);
        bst.printTree();

        System.out.println("delete 5");
        bst.delete(bst.root, 5);
        bst.printTree();

        System.out.println("delete all nodes greater than 7");
        bst.deleteNodesGreaterThanK(bst.root, 7);
        bst.printTree();

        System.out.println("Binary Tree to BST:");
        System.out.println("tree:");
        tree2.inorderTraversal(root2);
        System.out.println();
        System.out.println("tree to BST:");
        tree2.binaryTreeToBST();
        tree2.inorderTraversal(root2);
        System.out.println();

        System.out.println("Preorder Traversal and BST:");
        Integer[] pre = {2,4,3};
        System.out.println("Preorder: ");
        for (int i=0; i<pre.length; i++) {
            System.out.print(pre[i]+" ");
        }
        System.out.println();
        System.out.println("BST: "+bst.PreorderTraversalAndBST(pre));

        pre = new Integer[]{2, 4, 1};
        System.out.println("Preorder: ");
        for (int i=0; i<pre.length; i++) {
            System.out.print(pre[i]+" ");
        }
        System.out.println();
        System.out.println("BST: "+bst.PreorderTraversalAndBST(pre));

    }


}
