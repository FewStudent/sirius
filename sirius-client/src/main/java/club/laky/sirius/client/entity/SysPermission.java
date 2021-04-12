package club.laky.sirius.client.entity;

import java.io.Serializable;

/**
 * 权限表(SysPermission)实体类
 *
 * @author makejava
 * @since 2021-03-20 13:38:30
 */
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 876656266197492372L;
    
    private Integer id;
    
    private String permission;
    
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}