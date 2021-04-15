package club.laky.sirius.oms.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (GoodsOrderList)实体类
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:56
 */
public class GoodsOrderList implements Serializable {
    private static final long serialVersionUID = 989848504819546031L;

    private Integer id;
    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品数量
     */
    private Integer goodsCount;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 商品名
     */
    @TableField(exist = false)
    private String goodsName;
    /**
     * 图片地址
     */
    @TableField(exist = false)
    private String url;
    /**
     * 单价
     */
    @TableField(exist = false)
    private BigDecimal price;

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

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}