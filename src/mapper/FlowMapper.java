package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.sql.Job;

import java.util.List;

public interface FlowMapper {
    List<Job> getFlows(@Param("jobName") String name);
    int deleteTaskById(@Param("jobId") int jobId);
    int deleteJobById(@Param("jobId") int jobId);
}
