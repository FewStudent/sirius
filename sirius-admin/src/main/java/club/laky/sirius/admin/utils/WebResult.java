package club.laky.sirius.admin.utils;

/**
 * @author prl
 * @Desrcription:
 * @date 2021/3/20 16:24
 */
public class WebResult {

    private String code;
    private String msg;
    private Object data;
    private boolean status;

    public static WebResult expire() {
        WebResult webResult = new WebResult();
        webResult.setCode("-1");
        webResult.setMsg("Token已过期");
        webResult.setStatus(false);
        return webResult;
    }


    public static WebResult unLogin() {
        WebResult webResult = new WebResult();
        webResult.setCode("-1");
        webResult.setMsg("没有登录");
        webResult.setStatus(false);
        return webResult;
    }


    public static WebResult unAuth() {
        WebResult webResult = new WebResult();
        webResult.setCode("1");
        webResult.setMsg("没有权限");
        webResult.setStatus(false);
        return webResult;
    }


    public static WebResult error(String msg) {
        WebResult webResult = new WebResult();
        webResult.setCode("1");
        webResult.setMsg(msg);
        webResult.setStatus(false);
        return webResult;
    }


    public static WebResult success(Object data) {
        WebResult webResult = new WebResult();
        webResult.setCode("0");
        webResult.setMsg("请求成功");
        webResult.setStatus(true);
        webResult.setData(data);
        return webResult;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "'code'='" + code + '\'' +
                ", 'msg'='" + msg + '\'' +
                ", 'data'=" + data +
                ", 'status'=" + status +
                '}';
    }
}
