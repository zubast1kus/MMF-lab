package taskWithGraph.graph;

public class Vertex {
    //Имя вершины
    private char name;

    public Vertex(char name) {
        this.name = name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }
}
