package club.laky.sirius.oms.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

/**
 * (GoodsOrder)实体类
 *
 * @author lakyjpan
 * @since 2021-04-15 22:08:36
 */
public class GoodsOrder implements Serializable {
    private static final long serialVersionUID = 380463261152901138L;

    private Integer id;
    /**
     * 订单流水号
     */
    private String orderNum;
    /**
     * 用户ID
     */
    private Integer uId;
    /**
     * 订单生成时间
     */
    private String createTime;
    /**
     * 状态码 0 订单发起 1 发货中 2 确认收货 3 订单结束 4 订单取消
     */
    private Integer state;
    /**
     * 收货地址
     */
    private Integer addressId;

    /**
     * 收货地址
     */
    @TableField(exist = false)
    private String addressName;

    /**
     * 用户昵称
     */
    @TableField(exist = false)
    private String nickname;

    /**
     * 商品列表
     */
    @TableField(exist = false)
    private List<GoodsOrderList> goodsList;

    @TableField(exist = false)
    private String email;

    @TableField(exist = false)
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public List<GoodsOrderList> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsOrderList> goodsList) {
        this.goodsList = goodsList;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}