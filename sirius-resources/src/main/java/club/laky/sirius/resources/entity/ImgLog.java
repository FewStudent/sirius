package club.laky.sirius.resources.entity;

import java.io.Serializable;

/**
 * 图片上传记录(ImgLog)实体类
 *
 * @author makejava
 * @since 2021-03-10 23:07:16
 */
public class ImgLog implements Serializable {
    private static final long serialVersionUID = 946868060489472051L;

    private Integer id;

    private String imgUrl;

    private Integer uId;

    private String createDate;
    /**
     * 用户类型 1 管理员 2 客户端用户
     */
    private Integer userType;


    public ImgLog() {

    }

    public ImgLog(String newFileName, Integer userId, Integer type, String now) {
        this.createDate = now;
        this.uId = userId;
        this.userType = type;
        this.imgUrl = newFileName;
    }

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