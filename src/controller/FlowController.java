package controller;

import Constants.Constant;
import Utils.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.MLResult;
import pojo.result.JobResult;
import pojo.sql.Job;
import service.DAGService;
import service.FlowService;

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


        HashMap<String,Integer> resultMap = new HashMap<>();
        resultMap.put("jobId",job.getJobId());
        return MLResult.ok(resultMap);
    }
    @ResponseBody
    @RequestMapping("/removeFlow")
    public MLResult removeFlow(int jobId){
        flowService.deleteTaskById(jobId);
        flowService.deleteJobById(jobId);
        return MLResult.ok();
    }

    @ResponseBody
    @RequestMapping("/batchremove")
    public MLResult batchRemove(String ids){
        String[] jobIds = ids.split(",");
        for(String id:jobIds){
            flowService.deleteTaskById(Integer.parseInt(id));
            flowService.deleteJobById(Integer.parseInt(id));
        }
        return MLResult.ok();
    }

    @ResponseBody
    @RequestMapping("/getJobStatus")
    public String  getStatus(int jobId){
        System.out.println(jobId);
        Map<String,String> params = new HashMap<>();
        params.put("jobId",String.valueOf(jobId));
        JobResult jobRes = new JobResult();
        jobRes.setSucceed(true);
        jobRes.setReason("");
        jobRes.setStatus(200);
        try {
            //res = HttpUtils.sendPost(Constant.train_url + "/managePlatform/requestTaskStatus/", params);
            //String workSpace = dagService.getJob(jobId).getPythonFolder();
            JavaShellUtil.CallTensorBoard(Constant.tensorboard_path);
        }catch (Exception e){

        }//return JsonUtils.objectToJson(jobRes) ;
        return JsonUtils.objectToJson(jobRes);
    }
    @ResponseBody
    @RequestMapping("/getResult")
    public MLResult getResult(int jobId)
    {
        System.out.println(jobId);
        String result = FileUtils.readFileByLines(Constant.log_url);
        return MLResult.ok(result);
    }

}
