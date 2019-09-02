package service;


import org.springframework.stereotype.Service;
import pojo.sql.Job;
import pojo.sql.Task;


@Service
public interface DAGService {
    String getModelPath (String model);
    int InsertTask(Task task);
    int JobInsert(Job job);
    String getPythonPath(int jobId,int nodeId);
    int getNewId();
    int submitJob(Job job);
    Job getJob(int jobId);
}
