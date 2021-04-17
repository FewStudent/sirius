package club.laky.sirius.admin.service;

public interface MailService {

    /**
     * @param to      收件人邮箱
     * @param title   邮件标题
     * @param content 邮件内容
     */
    void sendCodeMail(String to, String title, String content);

}
