package Utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: MachineLearning
 * @description: 时间操作工具
 * @author: Mr.Sun
 * @create: 2019-06-09 19:57
 **/

public class TimeUtils {
    public static String getCurrentTime (){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currentTime = df.format(new Date());// new Date()为获取当前系统时间
        return currentTime;
    }
}
