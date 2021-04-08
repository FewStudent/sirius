package club.laky.sirius.pms.entity;

import java.io.Serializable;

/**
 * (GoodsType)实体类
 *
 * @author lakyjapn
 * @since 2021-04-08 20:04:18
 */
public class GoodsType implements Serializable {
    private static final long serialVersionUID = -88233379704820836L;
    
    private Integer id;
    /**
    * 类型名
    */
    private String typeName;
    /**
    * 详细类型
    */
    private String typeDetail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

}