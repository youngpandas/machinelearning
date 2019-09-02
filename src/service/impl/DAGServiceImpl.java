package service.impl;

import mapper.DagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.sql.Job;
import pojo.sql.Task;
import service.DAGService;

/**
 * @program: MachineLearning
 * @description: DAGService的实现类
 * @author: Mr.Sun
 * @create: 2019-05-02 22:08
 **/
@Service("DagService")
public class DAGServiceImpl implements DAGService {
    @Autowired
    public DagMapper mapper;

    @Override
    public String getModelPath(String model) {
        return mapper.getModelPath(model);
    }

    @Override
    public int InsertTask(Task task) {
        return mapper.TaskInset(task);
    }

    @Override
    public int JobInsert(Job job) {
        return mapper.JobInsert(job);
    }

    @Override
    public String getPythonPath(int jobId, int nodeId) {
        return mapper.getPythonPath(jobId,nodeId);
    }

    @Override
    public int getNewId() {
        return mapper.getNewId();
    }

    @Override
    public int submitJob(Job job) {
        return mapper.submitJob(job);
    }

    @Override
    public Job getJob(int jobId) {
        return mapper.getJob(jobId);
    }


}
