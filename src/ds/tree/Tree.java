package ds.tree;

import ds.list.EduNode;
import models.EduObject;

public class Tree {
    private TreeNode root;
    private boolean fixing;

    public Tree() {
        fixing = false;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public boolean isFixing() {
        return fixing;
    }

    public void setFixing(boolean fixing) {
        this.fixing = fixing;
    }

    protected void leftRotate(TreeNode n) {
        TreeNode r = n.getRight();
        n.setRight(r.getLeft());
        if (r.getLeft() != null)
            r.getLeft().setParent(n);
        r.setParent(n.getParent());
        if (n.getParent() == null)
            this.root = r;
        else {
            if (n.getParent().getLeft().equals(n))
                n.getParent().setLeft(r);
            else
                n.getParent().setRight(r);
        }
        r.setLeft(n);
        n.setParent(r);
    }

    protected void rightRotate(TreeNode n) {
        TreeNode l = n.getLeft();
        n.setLeft(l.getRight());
        if (l.getRight() != null)
            l.getRight().setParent(n);
        l.setParent(n.getParent());
        if (n.getParent() != null)
            this.root = l;
        else {
            if (n.getParent().getRight().equals(n))
                n.getParent().setRight(l);
            else
                n.getParent().setLeft(l);
        }
        l.setRight(n);
        n.setParent(l);
    }

    protected TreeNode BST_insert(TreeNode root, TreeNode parent, EduNode n) {
        if (root == null) {
            TreeNode treeNode = new TreeNode(n, parent,
                    ((EduObject) n.getElement()).getName());
            if (this.root == null) this.root = treeNode;
            return treeNode;
        }
        if (((EduObject) n.getElement()).getName().compareTo(root.getName()) < 0)
            return BST_insert(root.getLeft(), root,  n);
        else if (((EduObject) n.getElement()).getName().compareTo(root.getName()) > 0)
            return BST_insert(root.getRight(), root, n);
        return null;
    }

    protected TreeNode search(TreeNode root, String str) {
        if (root != null) {
            if (root.getName().equals(str))
                return root;
            else if (str.compareTo(root.getName()) < 0)
                search(root.getLeft(), str);
            else return search(root.getRight(), str);
        }
        return null;
    }

    private TreeNode findMinimum(TreeNode n) {
        if (n.getLeft() == null)
            return n;
        return findMinimum(n.getLeft());
    }

    private TreeNode findSuccessor(TreeNode n) {
        if (n.getRight() != null)
            return findMinimum(n.getRight());
        TreeNode p = n.getParent();
        while (p != null && n.equals(p.getRight())) {
            n = p;
            p = p.getParent();
        }
        return p;
    }

    protected TreeNode delete(String name) {
        TreeNode n = search(this.getRoot(), name);
        TreeNode deletedNode;
        TreeNode node;
        if (n.getLeft() == null || n.getRight() == null)
            deletedNode = n;
        else deletedNode = findSuccessor(n);
        if (deletedNode.getLeft() != null)
            node = deletedNode.getLeft();
        else node = deletedNode.getRight();
        if (node != null)
            node.setParent(deletedNode.getParent());
        if (deletedNode.getParent() == null)
            this.setRoot(node);
        else {
            if (deletedNode.equals(deletedNode.getParent().getLeft()))
                deletedNode.getParent().setLeft(node);
            else deletedNode.getParent().setRight(node);
        }
        if (!deletedNode.equals(n)) {
            n.setName(deletedNode.getName());
            n.setEduData(deletedNode.getEduData());
        }
        if (deletedNode.getColor() == Color.BLACK && node != null)
            fixing = true;
        return node;
    }
}
