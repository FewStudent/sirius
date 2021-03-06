package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.constant.CacheKey;
import club.laky.sirius.pms.entity.GoodsType;
import club.laky.sirius.pms.dao.GoodsTypeDao;
import club.laky.sirius.pms.feign.FeignCacheService;
import club.laky.sirius.pms.service.GoodsTypeService;
import club.laky.sirius.pms.utils.WebResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (GoodsType)表服务实现类
 *
 * @author lakyjapn
 * @since 2021-04-08 20:04:18
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Resource
    private FeignCacheService cacheService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsType queryById(Integer id) {
        return this.goodsTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsType> queryAllByLimit(int offset, int limit) {
        return this.goodsTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsType 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsType insert(GoodsType goodsType) {
        cacheService.del(CacheKey.GOODS_TYPES);
        this.goodsTypeDao.insert(goodsType);
        return goodsType;
    }

    /**
     * 修改数据
     *
     * @param goodsType 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsType update(GoodsType goodsType) {
        cacheService.del(CacheKey.GOODS_TYPES);
        this.goodsTypeDao.update(goodsType);
        return this.queryById(goodsType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        cacheService.del(CacheKey.GOODS_TYPES);
        return this.goodsTypeDao.deleteById(id) > 0;
    }

    @Override
    public WebResult save(String jsonBody) {
        cacheService.del(CacheKey.GOODS_TYPES);
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer id = params.getInteger("id");
        String typeName = params.getString("typeName");
        String typeDetail = params.getString("typeDetail");
        GoodsType goodsType = new GoodsType();

        goodsType.setTypeName(typeName);
        goodsType.setTypeDetail(typeDetail);
        //添加
        if (id == null) {
            int result = this.goodsTypeDao.insert(goodsType);
            if (result == 0) {
                return WebResult.error("添加失败");
            }
            return WebResult.success("添加成功");
        } else {
            goodsType.setId(id);
            int result = this.goodsTypeDao.update(goodsType);
            if (result == 0) {
                return WebResult.error("修改失败");
            }
            return WebResult.success("修改成功");
        }
    }

    @Override
    public List<GoodsType> queryTypeList(Integer offset, Integer limit, String typeName) {
        return this.goodsTypeDao.queryTypeList(offset, limit, typeName);
    }

    @Override
    public int queryTypeListCount(String typeName) {
        return this.goodsTypeDao.queryTypeListCount(typeName);
    }

    @Override
    public WebResult allType() {
        Map<String, Object> cache = (Map<String, Object>) cacheService.get(CacheKey.GOODS_TYPES);
        if ("true".equals(cache.get("status"))) {
            //获取缓存成功
            String json = (String) cache.get("data");
            List<GoodsType> goodsTypes = JSON.parseArray(json, GoodsType.class);
            return WebResult.success(goodsTypes);
        } else {
            //缓存获取失败,从数据库获取并缓存
            List<GoodsType> goodsList = this.goodsTypeDao.queryAll(null);
            String json = JSON.toJSONString(goodsList);
            cacheService.set(CacheKey.GOODS_TYPES, json);
            return WebResult.success(goodsList);
        }
    }

    @Override
    public Object detail(Integer id) {
        return this.goodsTypeDao.queryById(id);
    }
}