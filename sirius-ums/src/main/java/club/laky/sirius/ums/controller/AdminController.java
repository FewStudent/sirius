package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.dao.SysUserDao;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/manager")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private SysUserDao userDao;


    /**
     * 管理员列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object queryAdminList(@RequestBody String jsonBody) {
        logger.info("获取管理员列表");
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer limit = params.getInteger("limit");
        Integer page = params.getInteger("page");
        String nickname = params.getString("nickname");
        Integer departmentId = params.getInteger("departmentId");
        Integer jobId = params.getInteger("jobId");
        Integer state = params.getInteger("state");
        return userDao.queryAdminList((page - 1) * limit, limit, nickname, departmentId, jobId, state);
    }

    /**
     * 管理员列表
     */
    @ResponseBody
    @RequestMapping("/count")
    public Integer queryAdminCount(@RequestBody String jsonBody) {
        logger.info("获取管理员数量");
        JSONObject params = JSONObject.parseObject(jsonBody);
        String nickname = params.getString("nickname");
        Integer departmentId = params.getInteger("departmentId");
        Integer jobId = params.getInteger("jobId");
        Integer state = params.getInteger("state");
        return userDao.queryAdminListCount(nickname, departmentId, jobId, state);
    }
}
