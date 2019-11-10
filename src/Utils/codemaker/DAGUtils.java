package Utils.codemaker;

import Constants.Constant;
import Utils.common.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import pojo.DagGraph;
import pojo.Dag.Edge;
import pojo.Dag.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @program: MachineLearning
 * @description: 根据用户提交的json字符串进行DAG的提取
 * @author: Mr.Sun
 * @create: 2019-05-05 13:42
 **/

public class DAGUtils {
    private static final Logger log = Logger.getLogger(DAGUtils.class);
    /** 
    * @Description: 根据json文件初始化Dag(边，节点，邻接矩阵)
    * @Param: [GraphJson] 
    * @return: pojo.DagGraph 
    * @Author: Mr.Sun 
    * @Date: 2019/5/7 
    */ 
    public static DagGraph getDag(String GraphJson){
        JsonNode rootNode = JsonUtils.JsonToTree(GraphJson);
        //解析edges
        JsonNode edges = rootNode.get("edges");
        String edgesJson = edges.toString();
        log.debug("edges:" + edgesJson);

        //解析nodes
        JsonNode nodes = rootNode.get("nodes");
        String nodesJson = nodes.toString();
        log.debug("nodes: "+nodesJson);

        DagGraph graph = new DagGraph();
        List<Edge> e= (List<Edge>)JsonUtils.jsonToList(edgesJson,Edge.class);
        List<Node> n= (List<Node>)JsonUtils.jsonToList(nodesJson, Node.class);
        graph.setEdges(e);
        graph.setNodes(n);
        Map<Integer,List<Integer>>  graphMap= new HashMap<>();
        for(int i=0;i<n.size();i++){
            Node node = n.get(i);
            List<Integer> nlist = new ArrayList<>();
            for(int j=0;j<e.size();j++){
                if(e.get(j).getSrc_node_id()==node.getId()){
                    nlist.add(e.get(j).getDst_node_id());
                }
            }
            graphMap.put(node.getId(),nlist);
        }
        graph.setMap(graphMap);
        log.debug("graph: "+JsonUtils.objectToJson(graphMap));
        return graph;
    }
    /** 
    * @Description: 保存图的json文件
    * @Param: [GraphJson] 
    * @return: java.lang.String 
    * @Author: Mr.Sun 
    * @Date: 2019/5/7 
    */ 
    public static String  DagSave(String GraphJson,int jobId){
        String graphPath = Constant.Graph+"job-"+jobId+".json";
        log.debug("graphPath:"+graphPath);
        File f = new File(graphPath);
        try{
            if(!f.exists()){
            f.createNewFile();
            }
        FileOutputStream out = new FileOutputStream(f);
        out.write(GraphJson.getBytes());
        out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return graphPath;
    }

    /** 
    * @Description: 图的广度遍历，得到计算任务的顺序
    * @Param: [graph] 
    * @return: java.util.List<java.lang.Integer> 
    * @Author: Mr.Sun 
    * @Date: 2019/5/8 
    */ 
    public static List<Integer> OrderOfCompute(DagGraph graph){
        Map<Integer,Integer> visited = new HashMap<>();
        Map<Integer,List<Integer>> graphMap= graph.getMap();
        Iterator iterator= graphMap.entrySet().iterator();
        while (iterator.hasNext()){
        Map.Entry entry = (Map.Entry) iterator.next();
        Object key = entry.getKey();
        visited.put((Integer) key,0);
        }
        System.out.println(JsonUtils.objectToJson(visited));
        List<Integer> list = new ArrayList<>();
        GraphVisited(graph,1,visited,list);
        System.out.println(JsonUtils.objectToJson(visited));
        Iterator iterVisited= graphMap.entrySet().iterator();
        while(iterVisited.hasNext()){
            Map.Entry entry = (Map.Entry) iterVisited.next();
            Integer key = (Integer) entry.getKey();
            if(visited.get(key)==0){
                System.out.println(key);
                GraphVisited(graph,(Integer) key,visited,list);
            }
        }
        return list;
    }


    public static void  GraphVisited (DagGraph graph,int root,Map<Integer,
            Integer> visited,List<Integer> list){
        Map<Integer,List<Integer>> graphMap= graph.getMap();
        Queue<Integer> queue = new LinkedList<>();
        int next;
        queue.add(root);
        while (!queue.isEmpty()){
            next = queue.remove();
            list.add(next);
            visited.put(next,1);
            if(graphMap.get(next).size()!=0){
                for(Integer n:graphMap.get(next)){
                    queue.add(n);
                }
            }
        }
    }
//    public static void main(String[] args) {
//        DagSave("{\"nodes\":\"1\"}");
//    }
public static void main(String[] args) {
        JsonNode jsonNode = JsonUtils.JsonToTree("{\"nodes\":\"1\",\"status\":200," +
                "\"code\":200}");
        System.out.println(jsonNode.get("code").toString());
}
}
