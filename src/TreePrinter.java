import java.util.*;

/**
 * Created by thyferny on 2017/3/14.
 */
public class TreePrinter {

    private static void printTree(List<BinaryTreeNode> nodes) {
        if (nodes.isEmpty())
            return;
        List<BinaryTreeNode> newNodes = new ArrayList<BinaryTreeNode>();
        for (BinaryTreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                System.out.print(" ");
                newNodes.add(node.leftNode);
                newNodes.add(node.rightNode);
            }
        }
        System.out.println("");
        printTree(newNodes);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(0);
        BinaryTreeNode a = new BinaryTreeNode(1);
        BinaryTreeNode b = new BinaryTreeNode(2);
        BinaryTreeNode aa = new BinaryTreeNode(3);
        BinaryTreeNode bb = new BinaryTreeNode(4);
        BinaryTreeNode bba = new BinaryTreeNode(5);
        BinaryTreeNode bbb = new BinaryTreeNode(6);
        BinaryTreeNode aaa = new BinaryTreeNode(7);
        root.leftNode=a;
        root.rightNode=b;

        a.leftNode=aa;
//        a.rightNode=ab;
//        b.leftNode=ba;
        b.rightNode=bb;
        aa.leftNode=aaa;
        bb.leftNode = bba;
        bb.rightNode=bbb;

        printTree(Collections.singletonList(root));
    }
}

class BinaryTreeNode{
    BinaryTreeNode leftNode=null;
    BinaryTreeNode rightNode=null;
    int value;

    public BinaryTreeNode(int value) {
        this.value = value;
    }
}
