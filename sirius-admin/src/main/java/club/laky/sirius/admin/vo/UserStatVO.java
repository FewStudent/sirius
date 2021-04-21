package club.laky.sirius.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author panrulang
 * @Desrcription:
 * @date 2021/4/21 16:43
 */
@Data
public class UserStatVO implements Serializable {
    private String date;
    private Integer create_count;
    private Integer admin_count;
    private Integer client_count;
    private Double admin_create_rate;
    private Double client_create_rate;
}
