package club.laky.sirius.pms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * (GoodsComment)实体类
 *
 * @author lakyjpan
 * @since 2021-04-14 00:31:01
 */
@Data
public class GoodsComment implements Serializable {
    private static final long serialVersionUID = -85791174465717492L;

    private Integer id;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论者
     */
    private Integer uId;
    /**
     * 评论时间
     */
    private String createTime;
    /**
     * 客服回复
     */
    private String reply;
    /**
     * 回复时间
     */
    private String replyTime;
    /**
     * 回复人ID
     */
    private Integer replyBy;

    @TableField(exist = false)
    private String replier;

    @TableField(exist = false)
    private String nickName;

}