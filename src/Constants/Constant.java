package Constants;

/**
 * @program: MachineLearning
 * @description: 系统平台的常量
 * @author: Mr.Sun
 * @create: 2019-05-02 17:55
 **/

public class Constant {
    /**
     * 配置文件常量
     */
    //freemarker文件相对路径
    public static final String freemarkerConfig = "/freemarker.properties";
    //计算图的存储路径
    //public static final String Graph = "/Users/sunjack/Graph/";

    public static final String Graph = "/home/hadoop/webapp/Graph/";
    public static final String not_python_type = "cosData";

    public static final String data_Url = "http://115.154.137.56:5001/path/dataload";

    public static final String train_url = "http://115.154.137.56:12346";

    public static final String tensorboard_path = "/home/hadoop/tensorboard/ten.sh";
    //public static final String log_url = "/Users/sunjack/Desktop/error.log";
    public static final int pageSize = 20;
    public static final int JOB_INIT=0;
    public static final int JOB_RUNNING = 1;
    public static final int JOB_SUCCESS = 2;
    public static final int JOB_FAILED = 3;

    public static final String RESULT_TYPE1 = "tensorboard";
    public static final String RESULT_TYPE2 = "log";
}
