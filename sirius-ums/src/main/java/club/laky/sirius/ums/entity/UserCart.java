package club.laky.sirius.ums.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * 用户购物车表(UserCart)实体类
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:53
 */
public class UserCart implements Serializable {
    private static final long serialVersionUID = 636435460222613135L;

    private Integer id;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 商品数量
     */
    private Integer count;
    /**
     * 用户ID
     */
    private Integer uId;
    @TableField(exist = false)
    private String goodsName;
    @TableField(exist = false)
    private String nickname;
    @TableField(exist = false)
    private String url;


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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUId() {
        return uId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}