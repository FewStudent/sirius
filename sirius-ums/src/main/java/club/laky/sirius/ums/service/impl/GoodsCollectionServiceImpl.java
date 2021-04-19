package club.laky.sirius.ums.service.impl;

import club.laky.sirius.ums.entity.GoodsCollection;
import club.laky.sirius.ums.dao.GoodsCollectionDao;
import club.laky.sirius.ums.service.GoodsCollectionService;
import club.laky.sirius.ums.utils.WebResult;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GoodsCollection)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:33
 */
@Service("goodsCollectionService")
public class GoodsCollectionServiceImpl implements GoodsCollectionService {
    @Resource
    private GoodsCollectionDao goodsCollectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsCollection queryById(Integer id) {
        return this.goodsCollectionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsCollection> queryAllByLimit(int offset, int limit) {
        return this.goodsCollectionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsCollection 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsCollection insert(GoodsCollection goodsCollection) {
        this.goodsCollectionDao.insert(goodsCollection);
        return goodsCollection;
    }

    /**
     * 修改数据
     *
     * @param goodsCollection 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsCollection update(GoodsCollection goodsCollection) {
        this.goodsCollectionDao.update(goodsCollection);
        return this.queryById(goodsCollection.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsCollectionDao.deleteById(id) > 0;
    }

    @Override
    public WebResult collections(Integer userId) {
        return WebResult.success(this.goodsCollectionDao.collections(userId));
    }

    @Override
    public WebResult deleteCollection(Integer id) {
        if (this.goodsCollectionDao.deleteById(id) == 0) {
            return WebResult.error("删除失败");
        }
        return WebResult.success("删除成功");
    }

    @Override
    public WebResult insertCollection(String jsonBody) {
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        GoodsCollection goodsCollection = new GoodsCollection();
        goodsCollection.setCollectTime(DateUtil.now());
        goodsCollection.setGoodsId(jsonObject.getInteger("goodsId"));
        goodsCollection.setUId(jsonObject.getInteger("userId"));
        if (goodsCollectionDao.insert(goodsCollection) == 0) {
            return WebResult.error("收藏失败");
        }
        return WebResult.error("收藏成功!");
    }

    @Override
    public WebResult adminCollectionList(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        String nickname = params.getString("nickname");
        Integer page = params.getInteger("page");
        Integer limit = params.getInteger("limit");

        return WebResult.success(goodsCollectionDao.adminCollectionList(nickname, (page - 1) * limit, limit));
    }

    @Override
    public Integer adminCollectionCount(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        String nickname = params.getString("nickname");
        return goodsCollectionDao.adminCollectionCount(nickname);
    }
}