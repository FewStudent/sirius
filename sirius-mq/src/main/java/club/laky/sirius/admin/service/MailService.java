package club.laky.sirius.admin.service;

public interface MailService {

    /**
     * @param to    收件人邮箱
     * @param title 邮件标题
     * @param code   邮件内偶然
     */
    void sendCodeMail(String to, String title, String code);
}
