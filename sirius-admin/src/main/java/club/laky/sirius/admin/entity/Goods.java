package club.laky.sirius.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (Goods)实体类
 *
 * @author lakyjpan
 * @since 2021-04-08 11:55:01
 */
@Data
public class Goods implements Serializable {
    private static final long serialVersionUID = -41136435923151626L;

    private Integer id;

    private String goodsName;

    private Integer typeId;

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
    private BigDecimal price;
    /**
     * 是否上架中
     */
    private Integer state;

    /**
     * 品牌ID
     */
    private Integer brandId;

    @TableField(exist = false)
    private String brandName;

    @TableField(exist = false)
    private String typeName;

    public Goods(String goodsName, String description, String imgs, Integer state, Integer brandId, Integer typeId, Integer count, BigDecimal price) {
        this.goodsName = goodsName;
        this.description = description;
        this.imgs = imgs;
        this.state = state;
        this.brandId = brandId;
        this.typeId = typeId;
        this.count = count;
        this.price = price;
    }
}