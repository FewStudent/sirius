package club.laky.sirius.admin.service.impl;

import club.laky.sirius.admin.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendCodeMail(String to, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            /*StringBuilder builder = new StringBuilder("<h1>您好，您在Sirius小商城注册的验证码为<em>")
                    .append(content)
                    .append("</em></h1><br>");*/


            helper.setText(content, false);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
