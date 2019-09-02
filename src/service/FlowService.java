package service;


import pojo.sql.Job;

import java.util.List;

/**
 * @program: MachineLearning
 * @description: 关于增删改查工作流的服务类
 * @author: Mr.Sun
 * @create: 2019-06-09 18:55
 **/

public interface FlowService {
    List<Job> getFlows (String name);
    int deleteTaskById( int jobId);
    int deleteJobById( int jobId);
}
