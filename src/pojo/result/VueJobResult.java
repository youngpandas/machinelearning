package pojo.result;

/**
 * @program: machinelearning
 * @description: 返回的结果类
 * @author: Mr.Sun
 * @create: 2019-11-10 15:36
 **/

public class VueJobResult extends JobResult {
    private String ResultType;

    public String getResultType() {
        return ResultType;
    }

    public void setResultType(String resultType) {
        ResultType = resultType;
    }
}
