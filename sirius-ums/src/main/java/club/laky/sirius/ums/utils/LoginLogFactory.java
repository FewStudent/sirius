package club.laky.sirius.ums.utils;

import club.laky.sirius.ums.entity.LoginLog;
import cn.hutool.core.date.DateUtil;

public class LoginLogFactory {
    public static LoginLog success(Integer userId) {
        LoginLog loginLog = new LoginLog();
        loginLog.setMsg("登录成功");
        loginLog.setUId(userId);
        loginLog.setSucceed("成功");
        loginLog.setCreateTime(DateUtil.now());
        return loginLog;
    }

    public static LoginLog error(String msg) {
        LoginLog loginLog = new LoginLog();
        loginLog.setMsg(msg);
        loginLog.setSucceed("失败");
        loginLog.setCreateTime(DateUtil.now());
        return loginLog;
    }
}
