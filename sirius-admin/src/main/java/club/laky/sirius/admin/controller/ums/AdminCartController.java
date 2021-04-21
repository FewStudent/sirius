package club.laky.sirius.admin.controller.ums;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/19 16:43
 */
@Controller
@RequestMapping("/admin/cart")
public class AdminCartController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCartController.class);

    @Autowired
    private FeignClientService clientService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String nickname) {
        try {
            logger.info("-------------查询所有购物车信息信息-------------");

            JSONObject object = new JSONObject();
            object.put("page", page);
            object.put("limit", limit);
            object.put("nickname", nickname);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(clientService.cartCount(object.toJSONString()));
            layData.setData((List) clientService.cartList(object.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有购物车信息信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 购物车信息删除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        try {
            logger.info("-------------购物车信息删除-------------");
            return clientService.deleteCart(id);
        } catch (Exception e) {
            logger.info("购物车信息删除失败:{}", e.getMessage());
            return WebResult.error("购物车信息删除失败");
        }
    }

}
