package club.laky.sirius.oms.entity;

import java.io.Serializable;

/**
 * (Goods)实体类
 *
 * @author lakyjpan
 * @since 2021-04-16 00:14:50
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 961311925817869634L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 商品名
    */
    private String goodsName;
    /**
    * 类型ID
    */
    private Integer typeId;
    /**
    * 商品描述
    */
    private String description;
    /**
    * 图片
    */
    private String imgs;
    /**
    * 剩余数量
    */
    private Integer count;
    /**
    * 单价
    */
    private Double price;
    /**
    * 是否上架中
    */
    private Integer state;
    /**
    * 品牌ID
    */
    private Integer brandId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

}