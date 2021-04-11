package club.laky.sirius.ums.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Value("${server.port}")
    private String port;

    @ResponseBody
    @RequestMapping("/test/get")
    public Object test(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("service", "ums-service");
        result.put("address",request.getLocalAddr());
        result.put("port",port);
        return result;

    }
}
