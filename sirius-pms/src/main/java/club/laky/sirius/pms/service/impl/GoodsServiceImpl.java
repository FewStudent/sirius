package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.dao.GoodsDao;
import club.laky.sirius.pms.dao.GoodsParamsDao;
import club.laky.sirius.pms.entity.Goods;
import club.laky.sirius.pms.entity.GoodsParams;
import club.laky.sirius.pms.service.GoodsService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * (Goods)表服务实现类
 *
 * @author makejava
 * @since 2021-04-08 11:55:03
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private GoodsParamsDao goodsParamsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Goods queryById(Integer id) {
        return this.goodsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Goods> queryAllByLimit(int offset, int limit) {
        return this.goodsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    @Override
    public Goods insert(Goods goods) {
        this.goodsDao.insert(goods);
        return goods;
    }

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    @Override
    public Goods update(Goods goods) {
        this.goodsDao.update(goods);
        return this.queryById(goods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsDao.deleteById(id) > 0;
    }

    @Override
    public Integer queryGoodsListCount(String goodsName, Integer brandId, Integer typeId, Integer state) {
        return this.goodsDao.queryGoodsListCount(goodsName, brandId, typeId, state);
    }

    @Override
    public List queryGoodsList(Integer offset, Integer limit, String goodsName, Integer brandId, Integer typeId, Integer state) {
        return this.goodsDao.queryGoodsList(offset, limit, goodsName, brandId, typeId, state);
    }

    @Override
    public WebResult insertGoods(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        String goodsName = params.getString("goodsName");
        String description = params.getString("description");
        String imgs = params.getString("imgs");
        Integer state = params.getInteger("state");
        Integer brandId = params.getInteger("brandId");
        Integer typeId = params.getInteger("typeId");
        Integer count = params.getInteger("count");
        BigDecimal price = params.getBigDecimal("price");

        Goods goods = new Goods(goodsName, description, imgs, state, brandId, typeId, count, price);

        int result = goodsDao.insert(goods);
        if (result == 0) {
            return WebResult.error("添加失败！");
        }
        JSONArray array = params.getJSONArray("specifications");
        List<GoodsParams> paramsList = new ArrayList<>();

        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                JSONObject specification = (JSONObject) array.get(i);
                GoodsParams goodsParams = new GoodsParams(goods.getId(), specification.getString("key"), specification.getString("value"));
                paramsList.add(goodsParams);
            }
            goodsParamsDao.insertBatch(paramsList);
        }
        return WebResult.success(goods);
    }

    @Override
    public WebResult saveGoods(String jsonBody) {
        JSONObject params = JSONObject.parseObject(jsonBody);
        String goodsName = params.getString("goodsName");
        String description = params.getString("description");
        String imgs = params.getString("imgs");
        Integer state = params.getInteger("state");
        Integer brandId = params.getInteger("brandId");
        Integer typeId = params.getInteger("typeId");
        Integer count = params.getInteger("count");
        BigDecimal price = params.getBigDecimal("price");
        Integer goodsId = params.getInteger("goodsId");

        Goods goods = new Goods(goodsName, description, imgs, state, brandId, typeId, count, price);
        goods.setId(goodsId);

        int result = goodsDao.update(goods);
        if (result == 0) {
            return WebResult.error("保存失败！");
        }
        JSONArray array = params.getJSONArray("specifications");
        List<GoodsParams> paramsList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JSONObject specification = (JSONObject) array.get(i);
            GoodsParams goodsParams = new GoodsParams(goods.getId(), specification.getString("key"), specification.getString("value"));
            paramsList.add(goodsParams);
        }
        goodsParamsDao.deleteBatch(goodsId);
        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                JSONObject specification = (JSONObject) array.get(i);
                GoodsParams goodsParams = new GoodsParams(goods.getId(), specification.getString("key"), specification.getString("value"));
                paramsList.add(goodsParams);
            }
            goodsParamsDao.insertBatch(paramsList);
        }
        return WebResult.success(goods);
    }
}