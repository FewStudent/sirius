package club.laky.sirius.admin.entity;

import java.io.Serializable;

/**
 * 图片上传记录(ImgLog)实体类
 *
 * @author lakyjapn
 * @since 2021-04-19 14:40:55
 */
public class ImgLog implements Serializable {
    private static final long serialVersionUID = 599681301792781366L;
    
    private Integer id;
    /**
    * 图片地址
    */
    private String imgUrl;
    /**
    * 上传用户ID
    */
    private Integer uId;
    /**
    * 创建时间
    */
    private String createDate;
    /**
    * 用户类型 1 管理员 2 客户端用户
    */
    private Integer userType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}