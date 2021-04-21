package club.laky.sirius.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderStatVO implements Serializable {
    private String date;
    private Integer create_count;
    private Integer start_count;
    private Integer send_count;
    private Integer take_count;
    private Integer end_count;
    private Integer cancel_count;
}
