package club.laky.sirius.ums.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2021-03-20 11:43:11
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -80543655455980401L;

    private Integer id;
    /**
     * 用户名
     */
    @JsonProperty("username")
    private String account;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 加密盐
     */
    @JsonIgnore
    private String salt;

    @JsonIgnore
    @TableField(exist = false, value = "permissionList")
    List<SysPermission> permissionList;

    @JsonIgnore
    @TableField(exist = false, value = "roleList")
    List<SysRole> roleList;

    @TableField(exist = false)
    private String token;


    /**
     * 部门ID
     */
    private Integer depId;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String department;
    /**
     * 职务ID
     */
    private Integer jobId;

    /**
     * 岗位名称
     */
    @TableField(exist = false)
    private String job;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮件
     */
    private String email;
    /**
     * 生日
     */
    private Object birthday;
    /**
     * 身份证
     */
    private String identify;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 注册时间
     */
    private String createTime;

    /**
     * 角色身份
     */
    @TableField(exist = false)
    private String role;


}