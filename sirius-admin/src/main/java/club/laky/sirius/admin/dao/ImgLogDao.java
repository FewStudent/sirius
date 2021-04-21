package club.laky.sirius.admin.dao;

import club.laky.sirius.admin.entity.ImgLog;
import club.laky.sirius.admin.vo.OrderStatVO;
import club.laky.sirius.admin.vo.UserStatVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 图片上传记录(ImgLog)表数据库访问层
 *
 * @author lakyjapn
 * @since 2021-04-19 14:40:55
 */
public interface ImgLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImgLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ImgLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imgLog 实例对象
     * @return 对象列表
     */
    List<ImgLog> queryAll(ImgLog imgLog);

    /**
     * 新增数据
     *
     * @param imgLog 实例对象
     * @return 影响行数
     */
    int insert(ImgLog imgLog);

    /**
     * 修改数据
     *
     * @param imgLog 实例对象
     * @return 影响行数
     */
    int update(ImgLog imgLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Delete("delete from img_log")
    Integer deleteAll();

    @Select("select count(*) from img_log")
    Integer queryListCount();

    List<ImgLog> queryList(Integer offset, Integer limit);

    //这四个是忒儿的 TODO
    List<UserStatVO> queryUserStat(@Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer queryUserStatCount();

    List<OrderStatVO> queryOrderStat(@Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer queryOrderStatCount();

}