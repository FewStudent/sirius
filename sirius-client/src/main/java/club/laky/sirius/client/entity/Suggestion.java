package club.laky.sirius.client.entity;

import java.io.Serializable;

/**
 * (Suggestion)实体类
 *
 * @author lakyjpan
 * @since 2021-04-22 21:55:09
 */
public class Suggestion implements Serializable {
    private static final long serialVersionUID = -27355366400906425L;
    
    private Integer id;
    
    private Integer userId;
    
    private String title;
    
    private String content;
    
    private String createTime;
    
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}