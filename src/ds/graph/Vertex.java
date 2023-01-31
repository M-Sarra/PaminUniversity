package ds.graph;

import ds.list.EduNode;

public class Vertex {
    private final int code;
    private final EduNode data;
    private final GraphList adjacency;
    private Color color;
    private Vertex next;

    public Vertex(int code, EduNode data) {
        this.code = code;
        this.data = data;
        this.adjacency = new GraphList();
        this.color = Color.WHITE;
    }

    public int getCode() {
        return code;
    }

    public GraphList getAdjacency() {
        return adjacency;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EduNode getData() {
        return data;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }

    public void addAdjacent(Vertex adjacent) {
        adjacency.insert(adjacent);
    }
}
