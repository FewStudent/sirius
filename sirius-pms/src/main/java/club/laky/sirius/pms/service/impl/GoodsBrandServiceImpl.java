package club.laky.sirius.pms.service.impl;

import club.laky.sirius.pms.constant.CacheKey;
import club.laky.sirius.pms.entity.GoodsBrand;
import club.laky.sirius.pms.dao.GoodsBrandDao;
import club.laky.sirius.pms.feign.FeignCacheService;
import club.laky.sirius.pms.service.GoodsBrandService;
import club.laky.sirius.pms.utils.WebResult;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 品牌商表(GoodsBrand)表服务实现类
 *
 * @author lakyjpan
 * @since 2021-04-08 21:45:09
 */
@Service("goodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
    @Resource
    private GoodsBrandDao goodsBrandDao;
    @Resource
    private FeignCacheService cacheService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsBrand queryById(Integer id) {
        return this.goodsBrandDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<GoodsBrand> queryAllByLimit(int offset, int limit) {
        return this.goodsBrandDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goodsBrand 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsBrand insert(GoodsBrand goodsBrand) {
        cacheService.del(CacheKey.GOODS_BRAND);
        this.goodsBrandDao.insert(goodsBrand);
        return goodsBrand;
    }

    /**
     * 修改数据
     *
     * @param goodsBrand 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsBrand update(GoodsBrand goodsBrand) {
        cacheService.del(CacheKey.GOODS_BRAND);
        this.goodsBrandDao.update(goodsBrand);
        return this.queryById(goodsBrand.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {

        cacheService.del(CacheKey.GOODS_BRAND);
        return this.goodsBrandDao.deleteById(id) > 0;
    }

    @Override
    public int queryBrandListCount(String brandName) {
        return this.goodsBrandDao.queryBrandListCount(brandName);
    }

    @Override
    public List<GoodsBrand> queryBrandList(Integer offset, Integer limit, String brandName) {
        return this.goodsBrandDao.queryBrandList(offset, limit, brandName);
    }

    @Override
    public WebResult save(String jsonBody) {
        cacheService.del(CacheKey.GOODS_BRAND);
        JSONObject params = JSONObject.parseObject(jsonBody);
        Integer id = params.getInteger("id");
        String name = params.getString("name");
        String desc = params.getString("desc");
        Integer sort = params.getInteger("sort");
        GoodsBrand goodsBrand = new GoodsBrand();

        goodsBrand.setName(name);
        goodsBrand.setDesc(desc);
        goodsBrand.setSortOrder(sort);
        //添加
        if (id == null) {
            goodsBrand.setAddTime(DateUtil.now());
            int result = this.goodsBrandDao.insert(goodsBrand);
            if (result == 0) {
                return WebResult.error("添加失败");
            }
            return WebResult.success("添加成功");
        } else {
            goodsBrand.setId(id);
            goodsBrand.setUpdateTime(DateUtil.now());
            int result = this.goodsBrandDao.update(goodsBrand);
            if (result == 0) {
                return WebResult.error("修改失败");
            }
            return WebResult.success("修改成功");
        }
    }

    @Override
    public WebResult allBrand() {
        Map<String, Object> cache = (Map<String, Object>) cacheService.get(CacheKey.GOODS_BRAND);
        if ("true".equals(cache.get("status"))) {
            //获取缓存成功
            String json = (String) cache.get("data");
            List<GoodsBrand> goodsBrands = JSON.parseArray(json, GoodsBrand.class);
            return WebResult.success(goodsBrands);
        } else {
            //缓存获取失败,从数据库获取并缓存
            List<GoodsBrand> brandList = this.goodsBrandDao.queryAll(null);
            String json = JSON.toJSONString(brandList);
            cacheService.set(CacheKey.GOODS_BRAND, json);
            return WebResult.success(brandList);
        }
    }

    @Override
    public Object detail(Integer id) {
        return this.goodsBrandDao.queryById(id);
    }
}