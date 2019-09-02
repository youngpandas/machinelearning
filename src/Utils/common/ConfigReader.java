package Utils.common;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;



public class ConfigReader {

    /** 
    * @Description:
    * @Param: [fileName] 
    * @return: java.util.Map<java.lang.String,java.lang.String> 
    * @Author: Mr.Sun 
    * @Date: 2019/5/2 
    */ 
    public   Map<String,String> getProps (String fileName) {
        Map<String,String> map = new HashMap<>();
        try {
            Properties prop = new Properties();
            //读取属性文件a.properties
            InputStream in = this.getClass().getResourceAsStream(fileName);
            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                String key = it.next();
                map.put(key,prop.getProperty(key));
                System.out.println(key + ":" + prop.getProperty(key));
            }
            in.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }



    public static Map<String,String> getResourceMap(String fileName){
        ConfigReader config = new ConfigReader();
        return config.getProps(fileName);
    }
//    public static void main(String[] args) {
//        new ConfigReader().getProps();
//    }
}
