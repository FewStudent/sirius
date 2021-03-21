package club.laky.sirius.admin.utils;


import java.util.HashMap;
import java.util.Map;

public class ResultMap {

    public static Object success(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "请求成功");
        result.put("data", data);
        result.put("status", true);
        return result;
    }

    public static Object error(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", msg);
        result.put("data", "");
        result.put("status", false);
        return result;
    }
}
