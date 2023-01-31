package ds.graph;

public class Graph {
    private final int size;
    private final Vertex[] vertices;
    private int num;

    public Graph(int size) {
        this.size = size;
        this.vertices = new Vertex[size];
        this.num = 0;
    }

    public int getSize() {
        return size;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void addVertex(Vertex v) {
        this.vertices[num] = v;
        num++;
    }

    public Vertex findVertex(int code) {
        for (Vertex vertex : vertices) {
            if (vertex.getCode() == code)
                return vertex;
        }
        return null;
    }

    public boolean hasPath(Vertex v, int code) {
        for (Vertex u : vertices) {
            u.setColor(Color.WHITE);
        }
        return DFS_visit(v, code);
    }

    private boolean DFS_visit(Vertex v, int code) {
        v.setColor(Color.GRAY);
        Vertex u = v.getAdjacency().getFirst();
        for (int i = 0; i < v.getAdjacency().getSize(); i++) {
            if (u.getCode() == code)
                return true;
            else if (u.getColor() == Color.WHITE) {
                if (DFS_visit(u, code))
                    return true;
            }
            u = u.getNext();
        }
        v.setColor(Color.BLACK);
        return false;
    }

    public GraphList getComponent(int code) {
        Vertex v = findVertex(code);
        for (Vertex u : vertices) {
            u.setColor(Color.WHITE);
        }
        GraphList graphList = new GraphList();
        return DFS_visit(v, graphList);
    }

    private GraphList DFS_visit(Vertex v, GraphList graphList) {
        v.setColor(Color.GRAY);
        Vertex u = v.getAdjacency().getFirst();
        for (int i = 0; i < v.getAdjacency().getSize(); i++) {
            if (u.getColor() == Color.WHITE) {
                graphList.insert(u);
                graphList = DFS_visit(u, graphList);
            }
            u = u.getNext();
        }
        v.setColor(Color.BLACK);
        return graphList;
    }

}
