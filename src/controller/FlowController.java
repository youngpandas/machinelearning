package controller;

import Constants.Constant;
import Utils.common.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.MLResult;
import pojo.result.JobResult;
import pojo.result.VueJobResult;
import pojo.sql.Job;
import service.DAGService;
import service.FlowService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MachineLearning
 * @description: 获取用户的工作流
 * @author: Mr.Sun
 * @create: 2019-05-13 14:19
 **/
@Controller
@RequestMapping("/flow")
public class FlowController {
    @Autowired
    public FlowService flowService;
    @Autowired
    public DAGService dagService;
    Logger log = Logger.getLogger(FlowController.class);
    public Map<String,String> config = ConfigReader.getResourceMap(Constant.freemarkerConfig);
    @ResponseBody
    @RequestMapping("/getFlows")
    public MLResult getFlows(int page,String name){
        System.out.println(name);
        List<Job> flows = flowService.getFlows(name);
        int currPage = page;
        HashMap <String,Object > map = new HashMap<>();
        int total = flows.size();
        //从第几条数据开始
        int firstIndex = (currPage - 1) * Constant.pageSize;
        //到第几条数据结束
        int lastIndex = currPage * Constant.pageSize;
        if(currPage*Constant.pageSize > total)
            lastIndex  = total;
        map.put("total",total);
        flows = flows.subList(firstIndex,lastIndex);
        map.put("flows",flows);
        return MLResult.ok(map);
    }

    @ResponseBody
    @RequestMapping("/addFlow")
    public MLResult addFlow(String name,String note){
        //得到当前时间
        String createTime = TimeUtils.getCurrentTime();
        //初始化插入对象
        Job job = new Job();
        job.setUserId(12);
        job.setCreateTime(createTime);
        job.setJobName(name);
        job.setNote(note);
        job.setJobStatus(Constant.JOB_INIT);
        dagService.JobInsert(job);

        //返回结果
        HashMap<String,Integer> resultMap = new HashMap<>();
        resultMap.put("jobId",job.getJobId());
        return MLResult.ok(resultMap);
    }
    @ResponseBody
    @RequestMapping("/removeFlow")
    public MLResult removeFlow(int jobId){
        flowService.deleteTaskById(jobId);
        flowService.deleteJobById(jobId);
        FileUtils.deleteFile(config.get("file-python")+"/job-"+jobId);
        FileUtils.deleteFile(Constant.Graph+"job-"+jobId+".json");
        return MLResult.ok();
    }

    @ResponseBody
    @RequestMapping("/batchremove")
    public MLResult batchRemove(String ids){
        String[] jobIds = ids.split(",");
        for(String id:jobIds){
            flowService.deleteTaskById(Integer.parseInt(id));
            flowService.deleteJobById(Integer.parseInt(id));
            FileUtils.deleteFile(config.get("file-python")+"/job-"+Integer.parseInt(id));
            FileUtils.deleteFile(Constant.Graph+"job-"+Integer.parseInt(id)+".json");
        }
        return MLResult.ok();
    }

    @ResponseBody
    @RequestMapping("/getJobStatus")
    public String  getStatus(int jobId){
        log.debug("beginning get job status");
        Map<String,String> params = new HashMap<>();
        log.debug("jobId: "+jobId);
        params.put("jobId",String.valueOf(jobId));
        JobResult jobRes = new JobResult();

        VueJobResult result = new VueJobResult();

        try {
            String res = HttpUtils.sendPost(Constant.train_url + "/managePlatform/requestTaskStatus/", params);
            log.debug("res: "+res);
            jobRes = JsonUtils.jsonToPojo(res, JobResult.class);
            log.debug("jobRes"+ jobRes);
            String workSpace = dagService.getJob(jobId).getPythonFolder();
            log.debug("workspace: "+workSpace);
            File file = new File(workSpace+"/tensorboard");

            if(jobRes.getStatus().equals("succeeded")){
                flowService.setJobStatus(jobId,Constant.JOB_SUCCESS);
            }
            if(jobRes.getStatus().equals("failed")){
                flowService.setJobStatus(jobId,Constant.JOB_FAILED);
            }
            if(file.exists()) {
                result.setResultType(Constant.RESULT_TYPE1);

                //to do start tensorboard
                //JavaShellUtil.CallTensorBoard(Constant.tensorboard_path);
                flowService.setResultType(jobId,Constant.RESULT_TYPE1);
                log.debug("tensorborad path exists");
            }else{
                result.setResultType(Constant.RESULT_TYPE2);
                flowService.setResultType(jobId,Constant.RESULT_TYPE2);
                log.debug("tensorborad path not exists");
            }
        }catch (Exception e){
            jobRes.setReason("访问不了服务");
            jobRes.setStatus("failed");
            jobRes.setSucceed(false);
        }

        result.setStatus(jobRes.getStatus());
        result.setSucceed(jobRes.getSucceed());
        result.setReason(jobRes.getReason());
        return JsonUtils.objectToJson(result);
    }
    @ResponseBody
    @RequestMapping("/getResult")
    public MLResult getResult(int jobId)
    {
        log.debug("result jobId: "+jobId);
        String result_log_path = config.get("file-python")+"/job-"+jobId+"/result.log";
        log.debug("result_path: "+result_log_path);
        String result = FileUtils.readFileByLines(result_log_path);
        return MLResult.ok(result);
    }

}
