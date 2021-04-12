package club.laky.sirius.client.utils;

import club.laky.sirius.client.entity.SysPermission;
import club.laky.sirius.client.entity.SysRole;
import club.laky.sirius.client.entity.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;


public class PermissionUtils {
    /**
     * 检测用户是否拥有该角色
     */
    public static boolean hashRole(SysUser user, String role) {
        List<SysRole> roleList = user.getRoleList();
        if (null == roleList || StringUtils.isEmpty(role)) {
            return false;
        }
        for (SysRole sysRole : roleList) {
            if (role.equals(sysRole.getRole())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测用户是否拥有该权限
     */
    public static boolean hasPermission(SysUser user, String path) {
        PathMatcher matcher = new AntPathMatcher();

        List<SysPermission> permissionList = user.getPermissionList();
        if (null == permissionList || StringUtils.isEmpty(path)) {
            return false;
        }
        for (SysPermission permission : permissionList) {
            if (matcher.matchStart( permission.getPermission(),path)) {
                return true;
            }
        }
        return false;
    }

}
