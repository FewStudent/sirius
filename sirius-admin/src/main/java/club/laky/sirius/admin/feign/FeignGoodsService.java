package club.laky.sirius.admin.feign;

import club.laky.sirius.admin.utils.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author panrulang
 */
@FeignClient(name = "pms-service")
public interface FeignGoodsService {

    @RequestMapping(value = "/api/goods/save", method = RequestMethod.GET)
    WebResult goodsSave(String jsonBody);

    @RequestMapping(value = "/api/goods/add", method = RequestMethod.GET)
    WebResult goodsInsert(String jsonBody);

    @RequestMapping(value = "/api/goods/list", method = RequestMethod.GET)
    WebResult queryGoodsList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goods/listCount", method = RequestMethod.GET)
    int queryGoodsListCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsBrand/listCount", method = RequestMethod.GET)
    int queryBrandListCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsBrand/list", method = RequestMethod.GET)
    WebResult queryBrandList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsBrand/delete", method = RequestMethod.GET)
    WebResult brandDelete(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsBrand/save", method = RequestMethod.GET)
    WebResult brandSave(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsBrand/allBrand", method = RequestMethod.GET)
    WebResult allBrand();

    @RequestMapping(value = "/api/goodsType/allType", method = RequestMethod.GET)
    WebResult allType();

    @RequestMapping(value = "/api/goodsType/save", method = RequestMethod.GET)
    Object typeSave(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsType/listCount", method = RequestMethod.GET)
    int queryTypeListCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsType/list", method = RequestMethod.GET)
    WebResult queryTypeList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goodsType/delete", method = RequestMethod.GET)
    Object typeDelete(@RequestBody String jsonBody);
}
