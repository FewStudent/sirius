package club.laky.sirius.admin.entity;

import java.io.Serializable;

/**
 * (GoodsParams)实体类
 *
 * @author lakyjapn
 * @since 2021-04-08 13:58:18
 */
public class GoodsParams implements Serializable {
    private static final long serialVersionUID = 905905717834543769L;
    
    private Integer id;
    
    private Integer goodsId;
    
    private String paramKey;
    
    private String paramValue;

    public GoodsParams(Integer id, String key, String value) {
        this.id = id;
        this.paramKey = key;
        this.paramValue = value;
    }


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

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

}