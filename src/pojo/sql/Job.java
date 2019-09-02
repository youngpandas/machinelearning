package pojo.sql;

/**
 * @program: MachineLearning
 * @description: 数据库中的工作流的映射
 * @author: Mr.Sun
 * @create: 2019-05-13 21:19
 **/

public class Job {
    private int jobId;
    private String GraphPath;
    private int userId;
    private String pythonFolder;
    private String jobName;
    private String updateTime;
    private String createTime;
    private String Note;
    private int jobStatus;

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int status) {
        this.jobStatus = status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getGraphPath() {
        return GraphPath;
    }

    public void setGraphPath(String graphPath) {
        GraphPath = graphPath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPythonFolder() {
        return pythonFolder;
    }

    public void setPythonFolder(String pythonFolder) {
        this.pythonFolder = pythonFolder;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", GraphPath='" + GraphPath + '\'' +
                ", userId=" + userId +
                ", pythonFolder='" + pythonFolder + '\'' +
                '}';
    }

}
