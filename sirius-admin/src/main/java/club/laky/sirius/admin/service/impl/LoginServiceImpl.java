package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.feign.FeignClientService;
import club.laky.sirius.admin.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private FeignClientService permissionService;

    @Override
    public Object login(String jsonBody) {
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        String account = jsonObject.getString("account");
        String pwd = jsonObject.getString("pwd");
        return permissionService.login(account, pwd, 0);
    }

    @Override
    public Object logout(String token) {
        return permissionService.logout(token);
    }
}
