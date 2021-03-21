package club.laky.sirius.admin.utils;

import club.laky.sirius.admin.entity.SysPermission;
import club.laky.sirius.admin.entity.SysRole;
import club.laky.sirius.admin.entity.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

public class PermissionUtils {
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

    public static boolean hasPermission(SysUser user, String path) {
        PathMatcher matcher = new AntPathMatcher();

        List<SysPermission> permissionList = user.getPermissionList();
        if (null == permissionList || StringUtils.isEmpty(path)) {
            return false;
        }
        for (SysPermission permission : permissionList) {
            if (matcher.matchStart(path, permission.getPermission())) {
                return true;
            }
        }
        return false;
    }
}
