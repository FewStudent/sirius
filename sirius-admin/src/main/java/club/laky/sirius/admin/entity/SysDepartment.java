package club.laky.sirius.admin.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 部门(SysDepartment)实体类
 *
 * @author lakyjapn
 * @since 2021-04-08 19:43:48
 */
@Data
@ToString
public class SysDepartment implements Serializable {
    private static final long serialVersionUID = 212680228172379967L;

    private Integer id;

    private String depName;

    private String depDescription;
    /**
     * 部门等级，越低权限等级越高
     */
    private Integer depLevel;

}