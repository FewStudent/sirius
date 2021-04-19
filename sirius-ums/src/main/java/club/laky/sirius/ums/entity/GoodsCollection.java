package club.laky.sirius.ums.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * (GoodsCollection)实体类
 *
 * @author lakyjapn
 * @since 2021-04-19 18:12:33
 */
public class GoodsCollection implements Serializable {
    private static final long serialVersionUID = -41698866691618953L;

    private Integer id;
    /**
     * 商品ID
     */
    private Integer goodsId;
    @TableField(exist = false)
    private String goodsName;
    @TableField(exist = false)
    private String nickname;
    /**
     * 用户ID
     */
    private Integer uId;
    /**
     * 收藏日期
     */
    private String collectTime;


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

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
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
}