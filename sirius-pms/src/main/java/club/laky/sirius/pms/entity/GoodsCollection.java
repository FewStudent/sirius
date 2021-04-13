package club.laky.sirius.pms.entity;

import java.io.Serializable;

/**
 * (GoodsCollection)实体类
 *
 * @author lakyjpan
 * @since 2021-04-14 00:32:15
 */
public class GoodsCollection implements Serializable {
    private static final long serialVersionUID = -87443110857862725L;
    
    private Integer id;
    /**
    * 商品ID
    */
    private Integer goodsId;
    /**
    * 用户ID
    */
    private Integer uId;
    /**
    * 收藏日期
    */
    private String collectTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

}