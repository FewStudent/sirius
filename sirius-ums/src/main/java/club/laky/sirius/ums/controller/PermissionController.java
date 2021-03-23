package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.dao.SysUserDao;
import club.laky.sirius.ums.entity.SysUser;
import club.laky.sirius.ums.feign.FeignCacheService;
import club.laky.sirius.ums.utils.JWTUtils;
import club.laky.sirius.ums.utils.PermissionUtils;
import club.laky.sirius.ums.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author prl
 * @Desrcription:
 * @date 2021/3/20 11:56
 */
@Controller
@RequestMapping("/api")
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Resource
    private SysUserDao userDao;
    @Resource
    private FeignCacheService cacheService;

    /**
     * 登录
     *
     * @param account 账号
     * @param pwd     未加密的密码
     * @param type    登录类型 0 后台 1 会员
     */
    @ResponseBody
    @RequestMapping("/login")
    public Object login(String account, String pwd, int type) {
        try {
            logger.info("--------------------用户登录开始！----------------");
            SysUser user = userDao.queryLoginUser(account, type);
            if (user == null) {
                logger.error("-------------------登录失败:该账号不存在------------------");
                return WebResult.error("该账号不存在");
            }
            System.out.println(JWTUtils.md5(pwd));
            if (!JWTUtils.md5(pwd).equals(user.getPassword())) {
                logger.error("-------------------登录失败:密码错误------------------");
                return WebResult.error("密码错误");
            }
            if (user.getState() == 0) {
                logger.error("-------------------登录失败:账号冻结------------------");
                return WebResult.error("账号冻结!");
            }
            boolean flag = true;
            if (type == 0) {
                flag = PermissionUtils.hashRole(user, "client");
            } else {
                flag = !PermissionUtils.hashRole(user, "client");
            }
            if (!flag) {
                logger.error("登录失败,非本系统用户!");
                return WebResult.error("登录失败,非本系统用户");
            }

            //缓存
            String token = JWTUtils.getJWT(user);
            user.setToken(token);
            //令牌缓存一天
            cacheService.setWithTime(token, JSONObject.toJSON(user).toString(),86400);
            logger.info("--------------------用户{}登录成功！----------------", account);
            return WebResult.success(user);
        } catch (Exception e) {
            logger.error("登录失败:{}", e.getMessage());
            e.printStackTrace();
            return WebResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("logout")
    public Object logout(@RequestBody String jsonBody) {
        String token = JSONObject.parseObject(jsonBody).getString("token");
        logger.info("注销账号token：{}", token);
        if (StringUtils.isEmpty(token)) {
            logger.info("注销失败,token不能为空");
            return WebResult.error("注销失败,token不能为空!");
        } else {
            try {
                cacheService.del(token);
                return WebResult.success("注销成功!");
            } catch (Exception e) {
                logger.error("注销失败：{}", e.getMessage());
                return WebResult.error("注销失败：" + e.getMessage());
            }
        }
    }

}
