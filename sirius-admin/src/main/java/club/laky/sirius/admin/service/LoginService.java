package club.laky.sirius.admin.service;


public interface LoginService {

    Object login(String jsonBody);

    Object logout(String token);
}
