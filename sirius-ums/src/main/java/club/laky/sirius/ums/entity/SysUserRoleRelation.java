package club.laky.sirius.ums.entity;

import java.io.Serializable;

/**
 * 用户角色关系表(SysUserRoleRelation)实体类
 *
 * @author lakyjapn
 * @since 2021-04-19 10:21:55
 */
public class SysUserRoleRelation implements Serializable {
    private static final long serialVersionUID = -89498037245769359L;
    
    private Integer roleId;
    
    private Integer userId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}