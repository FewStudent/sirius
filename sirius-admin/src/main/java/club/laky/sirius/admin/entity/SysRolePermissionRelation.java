package club.laky.sirius.admin.entity;

import java.io.Serializable;

/**
 * 角色权限关系表(SysRolePermissionRelation)实体类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:12:48
 */
public class SysRolePermissionRelation implements Serializable {
    private static final long serialVersionUID = -71439853080769110L;
    /**
    * 角色ID
    */
    private Integer roleId;
    /**
    * 权限ID
    */
    private Integer permissionId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}