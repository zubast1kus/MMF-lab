package taskwithgraph.graph;

import taskwithgraph.validator.ArcValidator;

public class Arc {
    //Вершина 1
    private Vertex v1;
    //Вершина 2
    private Vertex v2;

    public Arc(Vertex v1, Vertex v2) {
        //Проверяем, что вершины не совпадают
        if (!ArcValidator.isVertexSame(v1,v2)) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public void setV1(Vertex v1) {
        //Проверяем, что вершины не совпадают
        if (!ArcValidator.isVertexSame(v1,this.v2)) {
            this.v1 = v1;
        }
    }

    public void setV2(Vertex v2) {
        //Проверяем, что вершины не совпадают
        if (!ArcValidator.isVertexSame(this.v1,v2)) {
            this.v2 = v2;
        }
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }
}
