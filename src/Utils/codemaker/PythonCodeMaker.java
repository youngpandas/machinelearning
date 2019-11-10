package Utils.codemaker;


import Utils.common.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;


import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class PythonCodeMaker {

    private static Configuration cfg;

    /**
    * @Description:渲染模板文件
    * @Param: [map, config, fileName] map: 模板需要的参数
    *                                 config:freemarker配置项
    *                                 fileName:模板名称
    * @return: java.lang.String
    * @Author: Mr.Sun
    * @Date: 2019/5/22
    */
    public static String  RunTemplate(Map<String,String> map,
                                   Map<String,String> config,String fileName){
        // 初始化FreeMarker配置
        // 创建一个Configuration实例
        cfg = new Configuration();
        // 设置FreeMarker的模版文件位置
        String RealPath = "";
        try {
            cfg.setDirectoryForTemplateLoading(new File(config.get("templates-path")));
            Template template = cfg.getTemplate(fileName);
            String RealName = fileName.substring(0,fileName.lastIndexOf("."))+".py";
            String savePath = config.get("file-python")+"/job-"+map.get("jobId");
            FileUtils.createFileFloder(savePath);
            RealPath = savePath+"/"+RealName;
            Writer out = new OutputStreamWriter(new FileOutputStream(RealPath), config.get("SYSTEM_ENCODING"));
            template.process(map, out);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(RealPath);
        return RealPath;
    }

    /**
     * 获取输出文件的名字
     * @return
     */
    private String getFileName(String beanName, String suffix) {
        return beanName + suffix + ".py";
    }

//    public static void main(String[] args) {
//        Map<String,String> config = ConfigReader.getResourceMap("/freemarker.properties");
//
//        //System.out.print(config.get("templates-path"));
//        Map<String,String> map = new HashMap<>();
//        map.put("data_folder","/down");
//        map.put("outfile_folder","/out");
//        PythonCodeMaker.RunTemplate(map,config,
//                "SplitDataSet.ftl");
//
//    }
}
