package Utils.codemaker;

import Utils.common.JavaShellUtil;

/**
 * @program: MachineLearning
 * @description: 生成python代码所需要的环境
 * @author: Mr.Sun
 * @create: 2019-05-13 14:57
 **/

public class RequirementUtil {
    /** 
    * @Description: 根据python文件的路径利用pipreqs命令生成相应的python文件的依赖
    * @Param: [pythonPath, command] 
    * @return: java.lang.String 
    * @Author: Mr.Sun 
    * @Date: 2019/5/13 
    */ 
    public String getRequirement(String pythonPath,String command){
        JavaShellUtil.callShell(pythonPath);
        return pythonPath+"/requirements.txt";
    }


}
