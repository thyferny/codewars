import java.util.*;

/**
 * Created by thyferny on 2017/3/14.
 */
public class TreePrinter {

    private static void printTree(List<TreeNode> nodes) {
        if (nodes.isEmpty())
            return;
        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                System.out.print(" ");
                if(node.children!=null){
                    newNodes.addAll(node.children);
                }
            }
        }
        System.out.println("");
        printTree(newNodes);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode aa = new TreeNode(3);
        TreeNode bb = new TreeNode(4);
        TreeNode bba = new TreeNode(5);
        TreeNode bbb = new TreeNode(6);
        TreeNode aaa = new TreeNode(7);
        root.addChild(a);
        root.addChild(b);

        a.addChild(aa);
//        a.rightNode=ab;
//        b.leftNode=ba;
        b.addChild(bb);
        aa.addChild(aaa);
        bb.addChild(bba);
        bb.addChild(bbb);

        printTree(Collections.singletonList(root));
    }
}

class TreeNode {
    List<TreeNode> children=null;
    int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public void addChild(TreeNode child) {
        if(child!=null){
            if(children==null){
                children = new ArrayList<>();
            }
            children.add(child);
        }
    }
}
