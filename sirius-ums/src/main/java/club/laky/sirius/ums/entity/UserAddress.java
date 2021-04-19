package club.laky.sirius.ums.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * 用户收货地址(UserAddress)实体类
 *
 * @author lakyjpan
 * @since 2021-04-18 22:10:38
 */
public class UserAddress implements Serializable {
    private static final long serialVersionUID = -15988849814256808L;

    private Integer id;
    /**
     * 用户ID
     */
    private Integer uId;

    @TableField(exist = false)
    private String nickname;
    /**
     * 地址
     */
    private String uAddress;
    /**
     * 详细地址
     */
    private String uDetail;
    /**
     * 电话
     */
    private String uPhone;
    /**
     * 收货人姓名
     */
    private String uName;
    /**
     * 是否为默认地址
     */
    private Integer isDefault;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUAddress() {
        return uAddress;
    }

    public void setUAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getUDetail() {
        return uDetail;
    }

    public void setUDetail(String uDetail) {
        this.uDetail = uDetail;
    }

    public String getUPhone() {
        return uPhone;
    }

    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}