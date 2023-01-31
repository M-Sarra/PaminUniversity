package ds.graph;

public class GraphList {
    private int size;
    private Vertex first;

    public GraphList() {
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public Vertex getFirst() {
        if (this.size > 0) return this.first;
        else throw new Error("list is empty!");
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void insert(Vertex n) {
        n.setNext(this.first);
        this.first = n;
        this.size++;
    }
}
