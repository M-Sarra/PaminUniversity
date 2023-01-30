package ds.tree;

import ds.list.EduNode;

public class EduTree extends Tree{

    private void insert(EduNode node) {
        TreeNode n = BST_insert(this.getRoot(), null, node);
        RB_insert(n);
    }

    private void RB_insert(TreeNode n) {
        while (!n.equals(this.getRoot()) &&
                n.getParent().getColor() == Color.RED) {
            if (n.getParent().equals(n.getParent().getParent().getLeft())) {
                TreeNode r = n.getParent().getParent().getRight();
                if (r.getColor() == Color.RED) {
                    n.getParent().setColor(Color.BLACK);
                    r.setColor(Color.BLACK);
                    n.getParent().getParent().setColor(Color.RED);
                    n = n.getParent().getParent();
                }
                else {
                    if (n.equals(n.getParent().getRight())) {
                        n = n.getParent();
                        leftRotate(n);
                    }
                    n.getParent().setColor(Color.BLACK);
                    n.getParent().getParent().setColor(Color.RED);
                    rightRotate(n.getParent().getParent());
                }
            }
            else {
                TreeNode l = n.getParent().getParent().getLeft();
                if (l.getColor() == Color.RED) {
                    n.getParent().setColor(Color.BLACK);
                    l.setColor(Color.BLACK);
                    n.getParent().getParent().setColor(Color.RED);
                    n = n.getParent().getParent();
                }
                else {
                    if (n.equals(n.getParent().getLeft())) {
                        n = n.getParent();
                        rightRotate(n);
                    }
                    n.getParent().setColor(Color.BLACK);
                    n.getParent().getParent().setColor(Color.RED);
                    leftRotate(n.getParent().getParent());
                }
            }
            this.getRoot().setColor(Color.BLACK);
        }
    }

    private void RB_delete(String name) {
        TreeNode node = delete(name);
    }
}
