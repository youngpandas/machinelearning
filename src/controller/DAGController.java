package controller;

import Constants.Constant;
import Utils.common.*;
import Utils.codemaker.DAGUtils;
import Utils.codemaker.PythonCodeMaker;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.DagGraph;
import pojo.MLResult;
import pojo.sql.Job;
import pojo.sql.Task;
import service.DAGService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dag")
public class DAGController {
    @Autowired
    public DAGService dagService;
    public Map<String,String> config = ConfigReader.getResourceMap(Constant.freemarkerConfig);
    /** 
    * @Description:根据json数据得到python文件
    * @Param: [map] 
    * @return: java.lang.String 
    * @Author: Mr.Sun 
    * @Date: 2019/5/2 
    */
    @ResponseBody
    @RequestMapping("/genaTask" )
    public MLResult genaTask(@RequestBody Map<String,String> map){
        //得到模型的模板名称
        String modelPath = dagService.getModelPath(map.get("model"));
        //python文件位置
        String pythonPath = PythonCodeMaker.RunTemplate(map,config,modelPath);
        System.out.println(map.get("jobId"));

        //初始化task
        int jobId = Integer.parseInt(map.get("jobId"));
        int nodeId = Integer.parseInt(map.get("nodeId"));
        Task task = new Task();
        task.setJobId(jobId);
        task.setNodeId(nodeId);
        task.setTaskParam(JsonUtils.objectToJson(map));
        task.setModelName(map.get("model"));
        task.setPythonPath(pythonPath);
        //插入task
        dagService.InsertTask(task);
        return MLResult.ok();
    }
    /** 
    * @Description:进行python文件渲染时的数据检查
    * @Param: [map] 
    * @return: pojo.MLResult 
    * @Author: Mr.Sun 
    * @Date: 2019/5/23 
    */ 
    @ResponseBody
    @RequestMapping("/dataCheck" )
    public MLResult DataCheck(@RequestBody Map<String,String> map){
        //将前端传来的数据进行转发请求
        String data_path = map.get("data_path");
        System.out.println(data_path);
        Map<String,String> params = new HashMap<>();
        params.put("path_name",data_path);
        String result = HttpUtils.sendPost(Constant.data_Url,params);
        System.out.println(result);
        if(JsonUtils.JsonToTree(result).get("success").toString().equals("true")){
            //初始化task
            int jobId = Integer.parseInt(map.get("jobId"));
            int nodeId = Integer.parseInt(map.get("nodeId"));
            Task task = new Task();
            task.setJobId(jobId);
            task.setNodeId(nodeId);
            task.setTaskParam(JsonUtils.objectToJson(map));
            task.setModelName(map.get("model"));
            task.setPythonPath("not exist");
            //插入task
            dagService.InsertTask(task);
            return MLResult.ok();
        }else{
            return MLResult.build(400,JsonUtils.JsonToTree(result).get("msg").toString());
        }
    }
    @ResponseBody
    @RequestMapping(value = "/getNewId",method = RequestMethod.POST)
    public MLResult getNewId (@RequestBody Map<String,String> map){
        int jobId = dagService.getNewId();
        HashMap<String,Integer> resultMap = new HashMap<>();
        resultMap.put("jobId",jobId);
        return MLResult.ok(resultMap);
    }
    /** 
    * @Description: 根据提交的Json保存json到文件系统，然后保存位置路径等元数据信息到mysql数据库
    * @Param: [graph] json数据
    * @return: java.lang.String 
    * @Author: Mr.Sun 
    * @Date: 2019/5/7 
    */ 
    @ResponseBody
    @RequestMapping(value = "/genaDag")
    public MLResult genaDag(@RequestBody String  params){
        //System.out.println(graph);
        //保存dag的信息
        System.out.println(params);
        JsonNode rootNode = JsonUtils.JsonToTree(params);
        JsonNode edges = rootNode.get("jobId");
        int jobId = edges.intValue();


        String graphPath = DAGUtils.DagSave(params);

        //初始化job信息
        Job job = new Job();
        job.setGraphPath(graphPath);
        String pythonFolder =config.get("file-python");
        job.setPythonFolder(pythonFolder);
        job.setUpdateTime(TimeUtils.getCurrentTime());
        job.setJobId(jobId);
        job.setJobStatus(Constant.JOB_RUNNING);
        //生成requirements.txt文件
        String result = JavaShellUtil.callShell(pythonFolder);
        //更新数据库表中的dag信息
        dagService.submitJob(job);
        //初始化Dag
        DagGraph dagGraph = DAGUtils.getDag(params);
        //得到Dag的计算顺序
        List<Integer> list = DAGUtils.OrderOfCompute(dagGraph);
        //生成运行脚本main.sh
        String shellContent="";
        for (int nodeId:list){
            if(nodeId !=1){
                String pythonPath = dagService.getPythonPath(jobId,nodeId);
                System.out.println(pythonPath);
                String pythonName = pythonPath.substring(pythonPath.lastIndexOf("/"));
                shellContent = shellContent+"python"+"   "+"/data"+pythonName+"\n";
            }
        }
        String savePath = config.get("file-python");
        String shellPath = savePath+"/main.sh";
        FileUtils.WriteToFile(shellPath,shellContent);
        //调用计算服务
        Map<String,String> computeMap = new HashMap<>();

        computeMap.put("jobId",String.valueOf(jobId));
        computeMap.put("codePath","/home/hadoop/data/tensorflow/test2");
        computeMap.put("startShellName","main.sh");

        String res = HttpUtils.sendPost(Constant.train_url+"/managePlatform/requestRunTask/",computeMap);

        System.out.println(res);
        //封装必要的数据
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("shellPath",shellPath);
        resultMap.put("savePath",savePath);
        resultMap.put("jobId",jobId);


        return MLResult.ok(resultMap);

    }

    @ResponseBody
    @RequestMapping(value = "/getJob")
    public String  getJob (int jobId){
        Job job = dagService.getJob(jobId);
        String graph = FileUtils.readFileByLines(job.getGraphPath());
        System.out.println(graph);
        return graph;
    }
}
