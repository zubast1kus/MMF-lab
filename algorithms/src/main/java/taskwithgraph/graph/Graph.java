package taskwithgraph.graph;

import taskwithgraph.validator.ArcValidator;
import taskwithgraph.validator.GraphValidator;

import java.util.*;

public class Graph {
    //Список вершин
    private Vertex[] V;
    //Список ребер
    private Arc[] E;
    //Массив для выбора имен вершинам
    private final char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O'};

    public Graph(Graph graph) {
        this.V = graph.getV();
        this.E = graph.getE();
    }

    //Построение графа на основе матрицы смежности (n*n - размер матрицы)
    public Graph(int[][] adjacencyMatrix, int n) {
        if (GraphValidator.isAdjacencyMatrixValid(adjacencyMatrix)) {
            V = new Vertex[n];
            E = new Arc[0];

            for (int i = 0; i < n; i++) {
                V[i] = new Vertex(LETTERS[i]);
            }

            //цикл по строкам
            for (int i = 0; i < n; i++) {
                //цикл по столбцам
                for (int j = 0; j < n; j++) {
                    //если в матрице смежности элемент = 1, значит вершины на позициях i,j смежны
                    if (adjacencyMatrix[i][j] == 1) {
                        boolean flag = false;

                        //проверяем, не содержится ли уже это ребро в списке ребер графа
                        for (int k = 0; k < E.length; k++) {
                            //если ребра совпали
                            if (ArcValidator.isArcSame(E[k], new Arc(V[i], V[j]))) {
                                flag = true;
                                break;
                            }
                        }

                        //если ребра не совпали
                        if (!flag) {
                            E = Arrays.copyOf(E, E.length + 1);
                            E[E.length - 1] = new Arc(V[i], V[j]);
                        }
                    }
                }
            }
        }
    }

    //Построение графа на основе матрицы инцедентности (n - число ребер, k - число вершин)
    public Graph(int[][] incidenceMatrix, int n, int k) {
        V = new Vertex[k];
        E = new Arc[0];

        for (int i = 0; i < k; i++) {
            V[i] = new Vertex(LETTERS[i]);
        }

        //цикл по столбцам
        for (int i = 0; i < k; i++) {
            Vertex v1 = null;
            Vertex v2 = null;
            //цикл по строкам
            for (int j = 0; j < n; j++) {
                if (incidenceMatrix[i][j] == 1) {
                    if (v1 == null) {
                        v1 = V[j];
                    } else {
                        v2 = V[j];
                        break;
                    }
                }
            }

            E = Arrays.copyOf(E, E.length + 1);
            E[E.length - 1] = new Arc(v1, v2);
        }
    }

    //Построение графа на основе списка смежности (количество строк в adjacencyList - количество вершин)
    public Graph(int[][] adjacencyList) {
        V = new Vertex[adjacencyList.length];
        E = new Arc[0];

        for (int i = 0; i < V.length; i++) {
            V[i] = new Vertex(LETTERS[i]);
        }

        //цикл по строкам
        for (int i = 0; i < V.length; i++) {
            //цикл по столбцам
            for (int j = 1; j < adjacencyList[i].length; j++) {
                boolean flag = false;
                //проверяем, не содержится ли уже это ребро в списке ребер графа
                for (int k = 0; k < E.length; k++) {
                    //если ребра совпали
                    if (ArcValidator.isArcSame(E[k], new Arc(V[i], V[adjacencyList[i][j] - 1]))) {
                        flag = true;
                        break;
                    }
                }

                //если ребра не совпали
                if (!flag) {
                    E = Arrays.copyOf(E, E.length + 1);
                    E[E.length - 1] = new Arc(V[i], V[adjacencyList[i][j] - 1]);
                }
            }
        }
    }

    //Построение графа на основе списка ребер
    public Graph(Arc[] arcs) {
        E = arcs;
        V = new Vertex[0];
        for (int i = 0; i < E.length; i++) {
            boolean flag1 = false;
            boolean flag2 = false;

            for (int j = 0; j < V.length; j++) {
                if (V[j].getName() == E[i].getV1().getName()) {
                    flag1 = true;
                }
                if (V[j].getName() == E[i].getV2().getName()) {
                    flag2 = true;
                }
            }

            if (!flag1) {
                V = Arrays.copyOf(V, V.length + 1);
                V[V.length - 1] = E[i].getV1();
            }
            if (!flag2) {
                V = Arrays.copyOf(V, V.length + 1);
                V[V.length - 1] = E[i].getV2();
            }
        }
    }

    //Добавление вершины
    public void addVertex(Vertex v) {
        V = Arrays.copyOf(V, V.length + 1);
        V[V.length - 1] = v;
    }

    //Удаление вершины
    public void deleteVertex(Vertex v) {
        Vertex[] tmp = new Vertex[0];

        for (int i = 0; i < V.length; i++) {
            if (!ArcValidator.isVertexSame(V[i], v)) {
                tmp = Arrays.copyOf(tmp, tmp.length + 1);
                tmp[tmp.length - 1] = V[i];
            }
        }

        V = tmp;

        Arc[] tmp2 = new Arc[0];

        for (int i = 0; i < E.length; i++) {
            if (!(ArcValidator.isVertexSame(v, E[i].getV1()) || ArcValidator.isVertexSame(v, E[i].getV2()))) {
                tmp2 = Arrays.copyOf(tmp2, tmp2.length + 1);
                tmp2[tmp2.length - 1] = E[i];
            }
        }

        E = tmp2;
    }

    //Добавление ребра
    public void addArc(Arc a) {
        boolean flag = false;

        for (int i = 0; i < E.length; i++) {
            if (ArcValidator.isArcSame(a, E[i])) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            E = Arrays.copyOf(E, E.length + 1);
            E[E.length - 1] = a;
        }
    }

    //Удаление ребра
    public void deleteArc(Arc a) {
        Arc[] tmp2 = new Arc[0];

        for (int i = 0; i < E.length; i++) {
            if (!ArcValidator.isArcSame(E[i], a)) {
                tmp2 = Arrays.copyOf(tmp2, tmp2.length + 1);
                tmp2[tmp2.length - 1] = E[i];
            }
        }

        E = tmp2;
    }

    public Graph(Vertex[] v, Arc[] e) {
        V = v;
        E = e;
    }

    public void setV(Vertex[] v) {
        V = v;
    }

    public void setE(Arc[] e) {
        E = e;
    }

    public Vertex[] getV() {
        return V;
    }

    public Arc[] getE() {
        return E;
    }

    public void printGraph() {
        System.out.print("Vertex's: ");
        for (int i = 0; i < V.length; i++) {
            System.out.print(V[i].getName() + ";");
        }
        System.out.println();

        System.out.print("Arc's: ");
        for (int i = 0; i < E.length; i++) {
            System.out.print("(" + E[i].getV1().getName() + "," + E[i].getV2().getName() + ");");
        }
        System.out.println();
    }

    //Проверка, являются ли вершины смежными
    public boolean isVertexAdjacency(Vertex v1, Vertex v2) {
        for (int i = 0; i < E.length; i++) {
            if (ArcValidator.isArcSame(new Arc(v1, v2), E[i])) {
                return true;
            }
        }
        return false;
    }

    public int[][] writeAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[V.length][V.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    if (isVertexAdjacency(V[i], V[j])) {
                        adjacencyMatrix[i][j] = 1;
                    } else {
                        adjacencyMatrix[i][j] = 0;
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    public int[][] writeIncidenceMatrix() {
        int[][] incidenceMatrix = new int[V.length][E.length];

        //циел по стобцам
        for (int i = 0; i < incidenceMatrix[0].length; i++) {
            Vertex v1 = E[i].getV1();
            Vertex v2 = E[i].getV2();
            //цикл по строкам
            for (int j = 0; j < incidenceMatrix.length; j++) {
                if (ArcValidator.isVertexSame(V[j], v1) || ArcValidator.isVertexSame(V[j], v2)) {
                    incidenceMatrix[j][i] = 1;
                } else {
                    incidenceMatrix[j][i] = 0;
                }
            }
        }

        return incidenceMatrix;
    }

    public int[][] writeAdjacencyList() {
        int[][] adjacencyList = new int[V.length][0];

        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = Arrays.copyOf(adjacencyList[i], adjacencyList[i].length + 1);
            adjacencyList[i][0] = i + 1;

            for (int j = 0; j < V.length; j++) {
                if (i != j && isVertexAdjacency(V[i], V[j])) {
                    adjacencyList[i] = Arrays.copyOf(adjacencyList[i], adjacencyList[i].length + 1);
                    adjacencyList[i][adjacencyList[i].length - 1] = j + 1;
                }
            }
        }

        return adjacencyList;
    }

    public Arc[] writeArcs() {
        return E;
    }

    public int[] bfs() {
        Vertex[] queue = new Vertex[0];
        int[]isVisited = new int[V.length];
        Arrays.fill(isVisited,0);

        queue = Arrays.copyOf(queue, queue.length + 1);
        queue[queue.length - 1] = V[0];
        isVisited[0] = 1;

        while (queue.length != 0) {
            for (int i = 0; i < V.length; i++) {
                if (!ArcValidator.isVertexSame(queue[0],V[i]) && isVertexAdjacency(queue[0], V[i]) && isVisited[i]!=1) {
                    queue = Arrays.copyOf(queue, queue.length + 1);
                    queue[queue.length - 1] = V[i];
                    isVisited[i] = 1;
                }
            }

            Vertex[] tmp = new Vertex[queue.length - 1];

            for (int i = 1; i < queue.length; i++) {
                tmp[i-1] = queue[i];
            }

            queue = tmp;

            for (int i = 0; i < queue.length; i++) {
                System.out.print(queue[i].getName());
            }
            System.out.println();
        }

        return isVisited;
    }
}
