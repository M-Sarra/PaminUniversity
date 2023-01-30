package ds.tree;

import ds.list.EduNode;

public class TreeNode {
    private Color color;
    private EduNode eduData;
    private String name;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
        this.color = Color.RED;
    }

    public TreeNode(EduNode eduData, String name) {
        this.eduData = eduData;
        this.name = name;
        this.color = Color.RED;
    }

    public TreeNode(EduNode eduData, TreeNode parent, String name) {
        this.eduData = eduData;
        this.parent = parent;
        this.name = name;
        this.color = Color.RED;
    }

    public TreeNode(EduNode eduData, TreeNode parent, TreeNode left, TreeNode right) {
        this.eduData = eduData;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.color = Color.RED;
    }

    public EduNode getEduData() {
        return eduData;
    }

    public void setEduData(EduNode eduData) {
        this.eduData = eduData;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
