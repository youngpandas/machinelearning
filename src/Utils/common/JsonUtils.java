package Utils.common;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.result.JobResult;


/**
 * 淘淘商城自定义响应结构
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }

    public static JsonNode JsonToTree(String json){
        JsonNode rootNode=null;
        try{
            rootNode = MAPPER.readTree(json);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rootNode;
    }
    public static String getStringByKey(String key,String jsonString){
        JsonNode rootNode = JsonToTree(jsonString);
        return rootNode.get(key).toString();
    }
    public static void main(String[] args) {
//        List<UserInfo> list = new ArrayList<>();
//        list.add(new UserInfo(1,"zzs","123"));
//
//        System.out.print(JsonUtils.objectToJson(MLResult.ok(list)));

//        String res = "{\"succeed\":true,\"status\":\"succeeded\",\"reason\":\"\"}";
//
//        JobResult job = JsonUtils.jsonToPojo(res,JobResult.class);
//        System.out.println(job);
    }

}
