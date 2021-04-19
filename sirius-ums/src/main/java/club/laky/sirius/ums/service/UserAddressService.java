package club.laky.sirius.ums.service;

import club.laky.sirius.ums.entity.UserAddress;
import club.laky.sirius.ums.utils.WebResult;

import java.util.List;

/**
 * 用户收货地址(UserAddress)表服务接口
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:38
 */
public interface UserAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserAddress queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserAddress> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userAddress 实例对象
     * @return 实例对象
     */
    UserAddress insert(UserAddress userAddress);

    /**
     * 修改数据
     *
     * @param userAddress 实例对象
     * @return 实例对象
     */
    UserAddress update(UserAddress userAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Object updateAddress(String jsonBody);

    Object delete(Integer addressId);

    Object insertAddress(String jsonBody);

    WebResult adminAddressList(String nickname, Integer page, Integer limit);

    Integer adminAddressCount(String nickname);

    Object userAddressList(Integer userId);

    WebResult addressDetail(Integer id);
}