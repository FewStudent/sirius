package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.dao.SysUserDao;
import club.laky.sirius.ums.dao.SysUserRoleRelationDao;
import club.laky.sirius.ums.entity.SysUser;
import club.laky.sirius.ums.entity.SysUserRoleRelation;
import club.laky.sirius.ums.utils.JWTUtils;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/8 20:51
 */
@Controller
@RequestMapping("/api/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private SysUserDao userDao;
    @Autowired
    private SysUserRoleRelationDao roleRelationDao;

    /**
     * 管理员列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object queryClientList(@RequestBody String jsonBody) {
        logger.info("获取管理员列表");
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer limit = params.getInteger("limit");
        Integer page = params.getInteger("page");
        String nickname = params.getString("nickname");
        String phone = params.getString("phone");
        Integer state = params.getInteger("state");
        return userDao.queryClientList((page - 1) * limit, limit, nickname, phone, state);
    }

    /**
     * 管理员列表
     */
    @ResponseBody
    @RequestMapping("/count")
    public Integer queryClientCount(@RequestBody String jsonBody) {
        logger.info("获取管理员数量");
        JSONObject params = JSONObject.parseObject(jsonBody);
        String nickname = params.getString("nickname");
        String phone = params.getString("phone");
        Integer state = params.getInteger("state");
        return userDao.queryClientCount(nickname, phone, state);
    }

    /**
     * 用户列表
     */
    @ResponseBody
    @RequestMapping("/userList")
    public Object queryUserList(@RequestBody String jsonBody) {
        logger.info("获取用户列表");
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer limit = params.getInteger("limit");
        Integer page = params.getInteger("page");
        String nickname = params.getString("nickname");
        String phone = params.getString("phone");
        Integer state = params.getInteger("state");
        return userDao.queryUserList((page - 1) * limit, limit, nickname, phone, state);
    }

    /**
     * 用户数量
     */
    @ResponseBody
    @RequestMapping("/userCount")
    public Integer queryUserCount(@RequestBody String jsonBody) {
        logger.info("获取用户数量");
        JSONObject params = JSONObject.parseObject(jsonBody);
        String nickname = params.getString("nickname");
        String phone = params.getString("phone");
        Integer state = params.getInteger("state");
        return userDao.queryUserCount(nickname, phone, state);
    }

    /**
     * 删除用户
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer userId) {
        logger.info("-------------删除用户-------------");
        return userDao.deleteById(userId);
    }

    /**
     * 添加用户
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/regist")
    public Object regist(@RequestParam String jsonBody) {
        logger.info("-------------添加用户-------------");
        JSONObject params = JSONObject.parseObject(jsonBody);

        String account = params.getString("account");
        String nickname = params.getString("nickname");
        String password = params.getString("pwd");
        String email = params.getString("email");

        SysUser sysUser = new SysUser();
        sysUser.setSalt(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
        sysUser.setNickname(nickname);
        sysUser.setPassword(JWTUtils.md5(password));
        sysUser.setEmail(email);
        sysUser.setAccount(account);
        sysUser.setCreateTime(DateUtil.now());
        int result = userDao.insert(sysUser);
        if (result == 1) {
            SysUserRoleRelation roleRelation = new SysUserRoleRelation();
            roleRelation.setRoleId(0);
            roleRelation.setUserId(sysUser.getId());
            roleRelationDao.insert(roleRelation);
            return 1;
        }
        return 0;
    }

    /**
     * 添加管理员
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/insertManager")
    public Object insertManager(@RequestParam String jsonBody) {
        logger.info("-------------添加管理员-------------");
        JSONObject params = JSONObject.parseObject(jsonBody);
        String account = params.getString("account");
        String nickname = params.getString("nickname");
        String password = params.getString("pwd");
        String phone = params.getString("phone");
        Integer roleId = params.getInteger("roleId");
        Integer dep_id = params.getInteger("dep_id");
        Integer job_id = params.getInteger("job_id");
        SysUser sysUser = new SysUser();
        sysUser.setState(1);
        sysUser.setPassword(password);
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPhone(phone);
        sysUser.setDepId(dep_id);
        sysUser.setJobId(job_id);
        sysUser.setSalt(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
        sysUser.setCreateTime(DateUtil.now());
        int result = userDao.insert(sysUser);
        if (result == 1) {
            SysUserRoleRelation roleRelation = new SysUserRoleRelation();
            roleRelation.setRoleId(roleId);
            roleRelation.setUserId(sysUser.getId());
            roleRelationDao.insert(roleRelation);
            return 1;
        }
        return 0;

    }

    /**
     * 修改管理员
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/update")
    public Integer update(@RequestParam String jsonBody) {
        logger.info("-------------修改管理员-------------");
        JSONObject params = JSONObject.parseObject(jsonBody);

        Integer id = params.getInteger("user_id");
        String nickname = params.getString("nickname");
        String password = params.getString("pwd");
        String url = params.getString("avatar");
        Integer state = params.getInteger("state");
        SysUser sysUser = new SysUser();
        if (StringUtils.isEmpty(password)) {
            sysUser.setPassword(JWTUtils.md5(password));
        }
        sysUser.setAvatar(url);
        sysUser.setState(state);
        sysUser.setId(id);
        sysUser.setNickname(nickname);
        return userDao.update(sysUser);
    }

    /**
     * 修改用户
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public Integer updateUser(@RequestParam String jsonBody) {
        logger.info("-------------修改用户-------------");
        JSONObject params = JSONObject.parseObject(jsonBody);

        Integer id = params.getInteger("user_id");
        String nickname = params.getString("nickname");
        String password = params.getString("pwd");
        String url = params.getString("avatar");
        Integer state = params.getInteger("state");

        String phone = params.getString("phone");
        String email = params.getString("email");
        String identify = params.getString("identify");
        String birthday = params.getString("birthday");

        SysUser sysUser = new SysUser();
        if (StringUtils.isEmpty(password)) {
            sysUser.setPassword(JWTUtils.md5(password));
        }
        sysUser.setAvatar(url);
        sysUser.setId(id);
        sysUser.setNickname(nickname);
        sysUser.setPhone(phone);
        sysUser.setEmail(email);
        sysUser.setIdentify(identify);
        sysUser.setBirthday(birthday);
        sysUser.setState(state);
        return userDao.update(sysUser);
    }

    /**
     * 获取用户详情
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/detail")
    public Object detail(@RequestParam String account, @RequestParam Integer type) {
        logger.info("-------------获取用户详情-------------");
        return userDao.queryLoginUser(account, type);
    }

}
