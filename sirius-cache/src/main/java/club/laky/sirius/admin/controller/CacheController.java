package club.laky.sirius.admin.controller;

import club.laky.sirius.admin.utils.ResultMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author 10044
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);


    @GetMapping("get")
    public Object get(@RequestParam("key") String key) {
        try {
            logger.info("----------------获取的键为：{}", key);
            Object result = key == null ? null : redisTemplate.opsForValue().get(key);
            return ResultMap.success(result);
        } catch (Exception e) {
            logger.error("获取失败,原因如下：{}", e.getMessage());
            return ResultMap.error(e.getMessage());
        }
    }

    @GetMapping("set")
    public Object set(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            logger.info("----------------添加的键为：{},值为:{}", key, value);
            redisTemplate.opsForValue().set(key, value);
            return ResultMap.success("");
        } catch (Exception e) {
            logger.error("添加失败,原因如下：{}", e.getMessage());
            return ResultMap.error(e.getMessage());
        }
    }

    @GetMapping("setWithTime")
    public Object setWithTime(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") long time) {
        try {
            logger.info("----------------添加的键为：{},值为:{},时间为：{}", key, value, time);
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return ResultMap.success("");
        } catch (Exception e) {
            logger.error("添加失败,原因如下：{}", e.getMessage());
            return ResultMap.error(e.getMessage());
        }
    }

    @GetMapping("del")
    public Object del(@RequestParam("key") String key) {
        try {
            logger.info("----------------删除的键为：{}", key);
            if (StringUtils.isEmpty(key)) {
                return ResultMap.error("键值不能为空!");
            }
            redisTemplate.delete(key);
            return ResultMap.success("");
        } catch (Exception e) {
            logger.error("获取失败,原因如下：{}", e.getMessage());
            return ResultMap.error(e.getMessage());
        }
    }

}
