package club.laky.sirius.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2021-03-20 13:38:09
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = 497432682457198769L;

    private Integer id;

    private String role;

    private String roleName;

    private String roleDetail;

    @TableField(exist = false)
    List<SysPermission> permissionList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }
}