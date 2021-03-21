package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.dao.SysUserDao;
import club.laky.sirius.ums.entity.SysUser;
import club.laky.sirius.ums.feign.FeignCacheService;
import club.laky.sirius.ums.utils.JWTUtils;
import club.laky.sirius.ums.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
            SysUser user = userDao.queryLoginUser(account);
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
            //缓存
            String token = JWTUtils.getJWT(user);
            user.setToken(token);
            cacheService.set(token, JSONObject.toJSON(user).toString());
            logger.info("--------------------用户{}登录成功！----------------",account);
            return WebResult.success(user);
        } catch (Exception e) {
            logger.error("登录失败:{}", e.getMessage());
            e.printStackTrace();
            return WebResult.error(e.getMessage());
        }
    }


}
