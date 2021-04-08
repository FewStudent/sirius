package club.laky.sirius.pms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (GoodsParams)实体类
 *
 * @author lakyjapn
 * @since 2021-04-08 13:58:18
 */
@Data
public class GoodsParams implements Serializable {
    private static final long serialVersionUID = 905905717834543769L;
    
    private Integer id;
    
    private Integer goodsId;
    
    private String paramKey;
    
    private String paramValue;

    public GoodsParams(Integer goodsId, String key, String value) {
        this.goodsId = goodsId;
        this.paramKey = key;
        this.paramValue = value;
    }


}