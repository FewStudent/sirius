package club.laky.sirius.ums.service.impl;

import club.laky.sirius.ums.entity.UserAddress;
import club.laky.sirius.ums.dao.UserAddressDao;
import club.laky.sirius.ums.service.UserAddressService;
import club.laky.sirius.ums.utils.WebResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户收货地址(UserAddress)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:39
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    private UserAddressDao userAddressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserAddress queryById(Integer id) {
        return this.userAddressDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserAddress> queryAllByLimit(int offset, int limit) {
        return this.userAddressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userAddress 实例对象
     * @return 实例对象
     */
    @Override
    public UserAddress insert(UserAddress userAddress) {
        this.userAddressDao.insert(userAddress);
        return userAddress;
    }

    /**
     * 修改数据
     *
     * @param userAddress 实例对象
     * @return 实例对象
     */
    @Override
    public UserAddress update(UserAddress userAddress) {
        this.userAddressDao.update(userAddress);
        return this.queryById(userAddress.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userAddressDao.deleteById(id) > 0;
    }

    @Override
    public Object updateAddress(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer id = params.getInteger("id");
        Integer u_id = params.getInteger("user_id");
        String detail = params.getString("detail");
        String address = params.getString("address");
        Integer state = params.getInteger("state");
        String name = params.getString("name");
        String phone = params.getString("phone");

        UserAddress userAddress = new UserAddress();
        userAddress.setIsDefault(state);
        userAddress.setId(id);
        userAddress.setUAddress(address);
        userAddress.setUId(u_id);
        userAddress.setUDetail(detail);
        userAddress.setUName(name);
        userAddress.setUPhone(phone);
        if (userAddressDao.update(userAddress) == 1) {
            return WebResult.success("修改成功!");
        }
        return WebResult.error("修改失败");
    }

    @Override
    public Object delete(Integer addressId) {
        int result = this.userAddressDao.deleteById(addressId);
        if (result == 0) {
            return WebResult.error("删除失败!");
        }
        return WebResult.success("删除成功!");
    }

    @Override
    public Object insertAddress(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer u_id = params.getInteger("user_id");
        String detail = params.getString("detail");
        String address = params.getString("address");
        String name = params.getString("name");
        String phone = params.getString("phone");

        UserAddress userAddress = new UserAddress();
        userAddress.setIsDefault(0);
        userAddress.setUAddress(address);
        userAddress.setUId(u_id);
        userAddress.setUDetail(detail);
        userAddress.setUName(name);
        userAddress.setUPhone(phone);
        if (userAddressDao.insert(userAddress) == 1) {
            return WebResult.success("添加成功!");
        }
        return WebResult.error("添加失败");
    }

    @Override
    public WebResult adminAddressList(String nickname, Integer offset, Integer limit) {
        if ("ALL-SELECT".equals(nickname)) {
            nickname = null;
        }
        List<UserAddress> addressList = this.userAddressDao.adminAddressList(nickname, offset, limit);
        return WebResult.success(addressList);
    }

    @Override
    public Integer adminAddressCount(String nickname) {
        return this.userAddressDao.adminAddressCount(nickname);
    }

    @Override
    public Object userAddressList(Integer userId) {
        if (userId == null) {
            return WebResult.error("获取失败,用户ID为空!");
        }
        List<UserAddress> addressList = this.userAddressDao.userAddressList(userId);
        return WebResult.success(addressList);
    }

    @Override
    public WebResult addressDetail(Integer id) {
        return WebResult.success(userAddressDao.queryById(id));
    }
}