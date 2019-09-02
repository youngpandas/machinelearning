package Test;

import java.util.List;

import Utils.common.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import pojo.Dag.Edge;
import pojo.Dag.Node;


public class test {
////	@org.junit.Test
////	public  void t() throws Exception{
////		Reader reader = Resources.getResourceAsReader("Mybatis.cfg.xml");
////		SqlSessionFactory ssf = 
////				new SqlSessionFactoryBuilder().build(reader);
////		SqlSession session = ssf.openSession(true);
////		UserMapper mapper = session.getMapper(UserMapper.class);
////		UserInfo info = mapper.queryOne("zs", "123456");
////		int n = mapper.save(info);
////		System.out.println(n);
////		System.out.println(info);
//	//}
//	@org.junit.Test
//	public void t() throws Exception{
//		ApplicationContext aContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//		UserService service = aContext.getBean("userservice",UserService.class);
//		System.out.println(service.queryOne("zs", "123456"));
	//}
public static void main(String[] args) {
    String json = "{\"edges\":[{ \"dst_input_idx\": 0, \"dst_node_id\": 1, \"id\": 2, \"src_node_id\": 0, \"src_output_idx\": 0 }, \n" +
            "{ \"dst_input_idx\": 0, \"dst_node_id\": 4, \"id\": 5, \"src_node_id\": 3, \"src_output_idx\": 0 }, \n" +
            "{ \"dst_input_idx\": 0, \"dst_node_id\": 7, \"id\": 8, \"src_node_id\": 6, \"src_output_idx\": 0 }]," +
            "\"nodes\":[{ \"id\": 0, \"in_ports\": [], \"name\": \"X_SUB_PD0_V01\", \"out_ports\": [0, 1, 2, 3, 4], \"pos_x\": 295, \"pos_y\": 205, \"type\": \"Variable\" }]}";
    JsonNode rootNode = JsonUtils.JsonToTree(json);
    JsonNode edges = rootNode.get("edges");
    String edgesJson = edges.toString();
    System.out.println(edgesJson);
    JsonNode nodes = rootNode.get("nodes");
    String nodesJson = nodes.toString();
    System.out.println(nodesJson);
    List<Edge> e= (List<Edge>)JsonUtils.jsonToList(edgesJson,Edge.class);
    System.out.print(e.get(0).toString());
    List<Node> n= (List<Node>)JsonUtils.jsonToList(nodesJson, Node.class);
    System.out.println(n.get(0).toString());

    }
}
