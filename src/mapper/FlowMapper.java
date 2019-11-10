package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.sql.Job;

import java.util.List;

public interface FlowMapper {
    List<Job> getFlows(@Param("jobName") String name);
    int deleteTaskById(@Param("jobId") int jobId);
    int deleteJobById(@Param("jobId") int jobId);
    int setResultType(@Param("jobId")int jobId,@Param("type")String type);
    int setJobStatus(@Param("jobId")int jobId,@Param("status")int status);
}
