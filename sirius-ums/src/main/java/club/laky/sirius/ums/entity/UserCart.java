package club.laky.sirius.ums.entity;

import java.io.Serializable;

/**
 * 用户购物车表(UserCart)实体类
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:59
 */
public class UserCart implements Serializable {
    private static final long serialVersionUID = -58930080248215922L;
    
    private Integer id;
    /**
    * 商品ID
    */
    private Integer goodsId;
    /**
    * 商品数量
    */
    private Integer count;
    /**
    * 用户ID
    */
    private Integer uId;


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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

}