package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.dao.SysUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private SysUserDao userDao;


    /**
     * 管理员列表
     */
    @ResponseBody
    @RequestMapping("/manager/list")
    public Object queryAdminList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                 @RequestParam("nickname") String nickname, @RequestParam("departmentId") Integer departmentId,
                                 @RequestParam("jobId") Integer jobId, @RequestParam("state") Integer state) {
        logger.info("获取管理员列表");
        return userDao.queryAdminList((page - 1) * limit, limit, nickname, departmentId, jobId, state);
    }

    /**
     * 管理员列表
     */
    @ResponseBody
    @RequestMapping("/manager/count")
    public Integer queryAdminCount(@RequestParam("nickname") String nickname,
                                   @RequestParam("departmentId") Integer departmentId,
                                   @RequestParam("jobId") Integer jobId, @RequestParam("state") Integer state) {
        logger.info("获取管理员数量");
        return userDao.queryAdminListCount(nickname, departmentId, jobId, state);
    }
}
