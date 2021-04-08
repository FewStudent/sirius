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
}
