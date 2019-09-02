package pojo.sql;

/**
 * @program: MachineLearning
 * @description: 关于Task的数据库映射
 * @author: Mr.Sun
 * @create: 2019-05-19 16:11
 **/

public class Task {
    private int taskId;
    private String taskParam;
    private int jobId;
    private int nodeId;
    private String PythonPath;
    private String modelName;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getPythonPath() {
        return PythonPath;
    }

    public void setPythonPath(String pythonPath) {
        PythonPath = pythonPath;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskParam='" + taskParam + '\'' +
                ", jobId=" + jobId +
                ", nodeId=" + nodeId +
                ", PythonPath='" + PythonPath + '\'' +
                ", modelName='" + modelName + '\'' +
                '}';
    }
}
