package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import pojo.sql.Job;
import pojo.sql.Task;

public interface DagMapper {
   //@Select("select modelPath from model where modelName=#{arg0}")
    String getModelPath (String model);
    //@Insert("insert into Task values(0,#{arg0},#{arg1},#{arg2},#{arg3})")
    int TaskInset(Task task);
    //@Insert("insert into Job values(0,#{arg0},#{arg1})")
    int JobInsert(Job job);

    String getPythonPath (int jobId,int nodeId);

    int getNewId();

    int submitJob(Job job);

    Job getJob (int jobId);
}
