package club.laky.sirius.admin.entity;

import java.io.Serializable;

/**
 * 岗位(SysJob)实体类
 *
 * @author lakyjapn
 * @since 2021-04-08 19:44:03
 */
public class SysJob implements Serializable {
    private static final long serialVersionUID = 656858032061324434L;
    
    private Integer id;
    
    private String jobName;
    
    private String jobDescription;
    
    private Integer jobLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

}