package club.laky.sirius.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * 用户角色关系表(SysUserRoleRelation)实体类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:30
 */
public class SysUserRoleRelation implements Serializable {
    private static final long serialVersionUID = -16739906743809499L;
    /**
     * 角色ID
     */
    private Integer roleId;

    @TableField(exist = false)
    private String roleName;
    /**
     * 用户ID
     */
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}