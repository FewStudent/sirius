package club.laky.sirius.admin.entity;

import java.io.Serializable;

/**
 * 部门(SysDepartment)实体类
 *
 * @author lakyjapn
 * @since 2021-04-08 19:43:48
 */
public class SysDepartment implements Serializable {
    private static final long serialVersionUID = 212680228172379967L;
    
    private Integer id;
    
    private String depName;
    
    private String depDescription;
    /**
    * 部门等级，越低权限等级越高
    */
    private Integer depLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepDescription() {
        return depDescription;
    }

    public void setDepDescription(String depDescription) {
        this.depDescription = depDescription;
    }

    public Integer getDepLevel() {
        return depLevel;
    }

    public void setDepLevel(Integer depLevel) {
        this.depLevel = depLevel;
    }

}