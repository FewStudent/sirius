package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.utils.LayuiVO;
import club.laky.sirius.admin.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/admin/address")
public class AdminAddressController {

    private static final Logger logger = LoggerFactory.getLogger(AdminAddressController.class);

    @Autowired
    private FeignClientService clientService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit, String nickname) {
        try {
            logger.info("-------------查询所有收货地址信息-------------");

            JSONObject object = new JSONObject();
            object.put("page",page);
            object.put("limit",limit);
            object.put("nickname",nickname);

            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(clientService.adminAddressCount(object.toJSONString()));
            layData.setData((List) clientService.adminAddressList(object.toJSONString()).getData());
            return layData;
        } catch (Exception e) {
            logger.info("查询所有收货地址信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 收货地址删除
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        try {
            logger.info("-------------收货地址删除-------------");
            return clientService.deleteAddress(id);
        } catch (Exception e) {
            logger.info("收货地址删除失败:{}", e.getMessage());
            return WebResult.error("收货地址删除失败");
        }
    }

    /**
     * 收货地址修改
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody String jsonBody) {
        try {
            logger.info("-------------收货地址修改-------------");
            return clientService.updateAddress(jsonBody);
        } catch (Exception e) {
            logger.info("收货地址修改失败:{}", e.getMessage());
            return WebResult.error("收货地址修改失败");
        }
    }

    /**
     * 收货地址详情
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/detail")
    public Object detail(@RequestParam Integer id) {
        try {
            logger.info("-------------收货地址详情-------------");
            return clientService.addressDetail(id);
        } catch (Exception e) {
            logger.info("收货地址详情失败:{}", e.getMessage());
            return WebResult.error("收货地址详情失败");
        }
    }
}
