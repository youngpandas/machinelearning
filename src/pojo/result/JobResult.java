package pojo.result;

/**
 * @program: MachineLearning
 * @description: 训练代码平台的结果类
 * @author: Mr.Sun
 * @create: 2019-06-13 22:45
 **/

public class JobResult {
    private Boolean succeed;
    private String status;
    private String reason;

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "JobResult{" +
                "succeed=" + succeed +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
