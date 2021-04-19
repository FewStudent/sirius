package club.laky.sirius.admin.feign;

import club.laky.sirius.admin.utils.WebResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author panrulang
 */
@FeignClient(name = "pms-service")
public interface FeignGoodsService {

    //----------- 商品信息
    @RequestMapping(value = "/api/goods/save", method = RequestMethod.GET)
    WebResult goodsSave(String jsonBody);

    @RequestMapping(value = "/api/goods/add", method = RequestMethod.GET)
    WebResult goodsInsert(String jsonBody);

    @RequestMapping(value = "/api/goods/offShelf", method = RequestMethod.GET)
    Object down(@RequestParam Integer goodsId);

    @RequestMapping(value = "/api/goods/delete", method = RequestMethod.GET)
    Object delete(@RequestParam Integer goodsId);

    @RequestMapping(value = "/api/goods/onSale", method = RequestMethod.GET)
    Object on(@RequestParam Integer goodsId);

    @RequestMapping(value = "/api/goods/detail", method = RequestMethod.GET)
    Object goodsDetail(@RequestParam Integer goodsId);

    @RequestMapping(value = "/api/goods/list", method = RequestMethod.GET)
    WebResult queryGoodsList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/goods/listCount", method = RequestMethod.GET)
    int queryGoodsListCount(@RequestBody String jsonBody);

    //------------ 商品品牌信息
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

    //------------ 商品类型信息
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

    @RequestMapping(value = "/api/goods/allSelect", method = RequestMethod.GET)
    Object allSelect();

    //------------ 商品评论信息
    @RequestMapping(value = "/api/comment/commentCount", method = RequestMethod.GET)
    Integer commentCount(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/comment/commentList", method = RequestMethod.GET)
    WebResult commentList(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/comment/deleteComment", method = RequestMethod.GET)
    Object deleteComment(@RequestParam Integer id);

    @RequestMapping(value = "/api/comment/reply", method = RequestMethod.GET)
    Object reply(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/comment/insertComment", method = RequestMethod.GET)
    Object insertComment(@RequestBody String jsonBody);

    @RequestMapping(value = "/api/comment/getCommentByGoodsId", method = RequestMethod.GET)
    Object getCommentByGoodsId(@RequestParam Integer goodsId);

}
