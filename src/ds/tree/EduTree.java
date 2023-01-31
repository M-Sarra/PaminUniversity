package ds.tree;

import ds.list.EduNode;

public class EduTree extends Tree{

    public EduTree() {
        super();
    }

    public void insert(EduNode node) {
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
        }
        this.getRoot().setColor(Color.BLACK);
    }

    public EduNode RB_Search(String name) {
        TreeNode node = this.search(this.getRoot(), name);
        if (node != null) return node.getEduData();
        else return null;
    }

    public void RB_delete(String name) {
        TreeNode n = delete(name);
        if (!this.isFixing()) return;
        while (!n.equals(this.getRoot()) && n.getColor() == Color.BLACK) {
            if (n.getParent().getLeft().equals(n))
                n = fixLeft(n);
            else n = fixRight(n);
            n.setColor(Color.BLACK);
        }
        this.setFixing(false);
    }

    private TreeNode fixLeft(TreeNode n) {
        TreeNode sibling = n.getParent().getRight();
        if (sibling.getColor() == Color.RED) {
            sibling.setColor(Color.BLACK);
            n.getParent().setColor(Color.RED);
            this.leftRotate(n.getParent());
            sibling = n.getParent().getRight();
        }
        if (sibling.getLeft().getColor() == Color.BLACK &&
                sibling.getRight().getColor() == Color.BLACK) {
            sibling.setColor(Color.RED);
            n = n.getParent();
        }
        else {
            if (sibling.getRight().getColor() == Color.BLACK) {
                sibling.getLeft().setColor(Color.BLACK);
                sibling.setColor(Color.RED);
                this.rightRotate(sibling);
                sibling = n.getParent().getRight();
            }
            sibling.setColor(n.getParent().getColor());
            n.getParent().setColor(Color.BLACK);
            sibling.getRight().setColor(Color.BLACK);
            this.leftRotate(n.getParent());
            n = this.getRoot();
        }
        return n;
    }

    private TreeNode fixRight(TreeNode n) {
        TreeNode sibling = n.getParent().getLeft();
        if (sibling.getColor() == Color.RED) {
            sibling.setColor(Color.BLACK);
            n.getParent().setColor(Color.RED);
            this.rightRotate(n.getParent());
            sibling = n.getParent().getLeft();
        }
        if (sibling.getLeft().getColor() == Color.BLACK &&
                sibling.getRight().getColor() == Color.BLACK) {
            sibling.setColor(Color.RED);
            n = n.getParent();
        }
        else {
            if (sibling.getLeft().getColor() == Color.BLACK) {
                sibling.getRight().setColor(Color.BLACK);
                sibling.setColor(Color.RED);
                this.leftRotate(sibling);
                sibling = n.getParent().getLeft();
            }
            sibling.setColor(n.getParent().getColor());
            n.getParent().setColor(Color.BLACK);
            sibling.getLeft().setColor(Color.BLACK);
            this.rightRotate(n.getParent());
            n = this.getRoot();
        }
        return n;
    }
}
