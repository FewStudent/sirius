package club.laky.sirius.ums.controller;

import club.laky.sirius.ums.entity.UserAddress;
import club.laky.sirius.ums.service.UserAddressService;
import club.laky.sirius.ums.service.UserCartService;
import club.laky.sirius.ums.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户收货地址(UserAddress)表控制层
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:40
 */
@RestController
@RequestMapping("/api/address")
public class UserAddressController {
    /**
     * 服务对象
     */
    @Resource
    private UserAddressService userAddressService;

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserAddress selectOne(Integer id) {
        return this.userAddressService.queryById(id);
    }

    /**
     * 获取用户的收货地址
     */
    @ResponseBody
    @RequestMapping("userAddressList")
    public Object userAddressList(@RequestParam Integer userId) {
        try {
            logger.info("-------------获取用户的收货地址-------------");
            return userAddressService.userAddressList(userId);
        } catch (Exception e) {
            logger.error("获取用户的收货地址失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 收货地址数量
     */
    @ResponseBody
    @RequestMapping("adminAddressCount")
    public Integer adminAddressCount(@RequestParam String nickname) {
        try {
            logger.info("-------------获取用户的收货地址-------------");
            return userAddressService.adminAddressCount(nickname);
        } catch (Exception e) {
            logger.error("获取用户的收货地址失败：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 管理所有用户地址
     */
    @ResponseBody
    @RequestMapping("adminAddressList")
    public WebResult adminAddressList(@RequestParam String nickname, @RequestParam Integer page, @RequestParam Integer limit) {
        try {
            logger.info("-------------管理所有用户地址-------------");
            return userAddressService.adminAddressList(nickname, page, limit);
        } catch (Exception e) {
            logger.error("管理所有用户地址失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 修改用户地址
     */
    @ResponseBody
    @RequestMapping("updateAddress")
    public Object updateAddress(@RequestBody String jsonBody) {
        try {
            logger.info("-------------修改用户地址-------------");
            return userAddressService.updateAddress(jsonBody);
        } catch (Exception e) {
            logger.error("修改用户地址失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("deleteAddress")
    public Object deleteAddress(@RequestBody Integer addressId) {
        try {
            logger.info("-------------删除-------------");
            return userAddressService.delete(addressId);
        } catch (Exception e) {
            logger.error("删除失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 添加用户地址
     */
    @ResponseBody
    @RequestMapping("insert")
    public Object insert(@RequestBody String jsonBody) {
        try {
            logger.info("-------------添加用户地址-------------");
            return userAddressService.insertAddress(jsonBody);
        } catch (Exception e) {
            logger.error("添加用户地址失败：" + e.getMessage());
            return WebResult.error(e.getMessage());
        }
    }

    /**
     * 收货地址详情
     *
     * @author panrulang
     */
    @ResponseBody
    @RequestMapping("/addressDetail")
    public Object addressDetail(@RequestParam Integer id) {
        try {
            logger.info("-------------收货地址详情-------------");
            return userAddressService.addressDetail(id);
        } catch (Exception e) {
            logger.info("收货地址详情失败:{}", e.getMessage());
            return WebResult.error("收货地址详情失败");
        }
    }
}