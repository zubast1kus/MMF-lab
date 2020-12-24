package taskWithGraph.main;

import taskWithGraph.graph.Graph;
import taskWithGraph.printer.Printer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JPanel {
    public static Graph graph;
    public static int[][] matrix = new int[0][0];

    public Main() {
        JFrame jframe = new JFrame("Graphics");

        jframe.setLocation(750, 150);
        jframe.setMinimumSize(new Dimension(600, 600));
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jframe.getContentPane().add(this);

        jframe.pack();
        jframe.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] coordsX = new int[matrix.length];
        int[] coordsY = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            coordsX[i] = new Random().nextInt(300);
            coordsY[i] = new Random().nextInt(300);
        }

        for (int i = 0; i < matrix.length; i++) {
            g.setColor(Color.BLACK);
            g.drawString(Character.toString(graph.getV()[i].getName()),coordsX[i]+20,coordsY[i]+20);
            g.fillOval(coordsX[i], coordsY[i], 10, 10);
        }

        g.setColor(Color.RED);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    g.drawLine(coordsX[i] + 5, coordsY[i] + 5, coordsX[j] + 5, coordsY[j] + 5);
                }
            }
        }
    }

    public static void main(String[] args) {
        Main htd = new Main();

        htd.repaint();

        int[][] adj = {
                {0,1,0,0,1,0},
                {1,0,1,0,1,0},
                {0,1,0,1,0,0},
                {0,0,1,0,1,1},
                {1,1,0,1,0,0},
                {0,0,0,1,0,0}
        };

        Graph graph1 = new Graph(adj, adj.length);
        graph1.printGraph();
        Printer.printMatrix(graph1.writeIncidenceMatrix());

        System.out.println("\nAdjacency list:");
        Printer.printMatrix(graph1.writeAdjacencyList());

        System.out.println("\nEdges list:");
        Printer.printEdgesArray(graph1.writeArcs());

        Main.graph = graph1;
        Main.matrix = graph1.writeAdjacencyMatrix();

        System.out.println("Breadth First Search:");
        Printer.printMatrix(graph1.bfs());
    }
}
