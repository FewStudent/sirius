package club.laky.sirius.resources.service;

import club.laky.sirius.resources.entity.ImgLog;

import java.util.List;

/**
 * 图片上传记录(ImgLog)表服务接口
 *
 * @author makejava
 * @since 2021-03-10 23:07:19
 */
public interface ImgLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImgLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ImgLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param imgLog 实例对象
     * @return 实例对象
     */
    ImgLog insert(ImgLog imgLog);

    /**
     * 修改数据
     *
     * @param imgLog 实例对象
     * @return 实例对象
     */
    ImgLog update(ImgLog imgLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 是否存在该用户
     *
     * @param userId 用户ID
     * @param type   用户类型
     * @return 是否存在
     */
    boolean hasUser(Integer userId, Integer type);

}