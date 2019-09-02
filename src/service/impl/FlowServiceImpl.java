package service.impl;

import mapper.FlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.sql.Job;
import service.FlowService;

import java.util.List;

/**
 * @program: MachineLearning
 * @description: FlowService的具体实现
 * @author: Mr.Sun
 * @create: 2019-06-09 18:56
 **/
@Service("flowService")
public class FlowServiceImpl implements FlowService {
    @Autowired
    public FlowMapper mapper;
    @Override
    public List<Job> getFlows(String name) {
        return mapper.getFlows(name);
    }

    @Override
    public int deleteTaskById(int jobId) {
        return mapper.deleteTaskById(jobId);
    }

    @Override
    public int deleteJobById(int jobId) {
        return mapper.deleteJobById(jobId);
    }

}
