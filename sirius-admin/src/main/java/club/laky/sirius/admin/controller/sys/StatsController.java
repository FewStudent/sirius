package club.laky.sirius.admin.controller.sys;

import club.laky.sirius.admin.dao.ImgLogDao;
import club.laky.sirius.admin.utils.LayuiVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/stats")
public class StatsController {
    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    @Resource
    private ImgLogDao dao;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/user")
    public LayuiVO userStat(Integer page, Integer limit) {
        try {
            logger.info("-------------统计用户信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(dao.queryUserStatCount());
            layData.setData(dao.queryUserStat((page - 1) * limit, limit));
            return layData;
        } catch (Exception e) {
            logger.info("统计用户信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/order")
    public LayuiVO orderStat(Integer page, Integer limit) {
        try {
            logger.info("-------------统计订单信息-------------");
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(dao.queryOrderStatCount());
            layData.setData(dao.queryOrderStat((page - 1) * limit, limit));
            return layData;
        } catch (Exception e) {
            logger.info("统计订单信息失败:{}", e.getMessage());
            return null;
        }
    }
}
