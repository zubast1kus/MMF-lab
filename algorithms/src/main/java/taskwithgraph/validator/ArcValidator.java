package taskwithgraph.validator;

import taskwithgraph.graph.Arc;
import taskwithgraph.graph.Vertex;

public class ArcValidator {
    public static boolean isVertexSame(Vertex v1, Vertex v2){
        if (v1.getName() == v2.getName()){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isArcSame(Arc a1,Arc a2){
        if (a1.getV1().getName() == a2.getV1().getName() && a1.getV2().getName() == a2.getV2().getName()){
            return true;
        }
        if (a1.getV1().getName() == a2.getV2().getName() && a1.getV2().getName() == a2.getV1().getName()){
            return true;
        }

        return false;
    }
}
