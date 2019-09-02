package pojo;

import pojo.Dag.Edge;
import pojo.Dag.Node;

import java.util.List;
import java.util.Map;

/**
 * @program: MachineLearning
 * @description: DAG
 * @author: Mr.Sun
 * @create: 2019-05-05 14:13
 **/

public class DagGraph {
    private List<Edge> edges;           //图的边
    private List<Node> nodes;           //图的节点
    private Map<Integer,List<Integer>> map; //图的邻接矩阵

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Map<Integer, List<Integer>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, List<Integer>> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "DagGraph{" +
                "edges=" + edges +
                ", nodes=" + nodes +
                ", map=" + map +
                '}';
    }
}
