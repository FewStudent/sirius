package club.laky.sirius.pms.entity;

import java.io.Serializable;

/**
 * 品牌商表(GoodsBrand)实体类
 *
 * @author lakyjpan
 * @since 2021-04-08 21:45:09
 */
public class GoodsBrand implements Serializable {
    private static final long serialVersionUID = 218076624541195721L;
    
    private Integer id;
    /**
    * 品牌商名称
    */
    private String name;
    /**
    * 品牌商简介
    */
    private String desc;
    /**
    * 品牌商页的品牌商图片
    */
    private String picUrl;
    /**
    * 展示序号
    */
    private Integer sortOrder;
    /**
    * 创建时间
    */
    private String addTime;
    /**
    * 更新时间
    */
    private String updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}