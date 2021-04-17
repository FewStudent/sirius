package club.laky.sirius.admin.consumer;

import club.laky.sirius.admin.service.MailService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 邮件发送服务 同时又是MQ的消息消费者
 */
@Component
public class CodeMailSender {
    //@Autowired
    //private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailService service;

    private static final Logger logger = LoggerFactory.getLogger(CodeMailSender.class);


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "test.test.queue", durable = "true"),
            exchange = @Exchange(value = "test.test.exchange", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "test.test.queue"
    ))
    @RabbitHandler
    //具体对象的签收
    public void process(@Payload String msg, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        JSONObject object = JSON.parseObject(msg);
        String email = object.getString("email");
        String title = object.getString("title");
        String content = object.getString("content");

        logger.info(msg);
        if(!StringUtils.isEmpty(email)){
            service.sendCodeMail(email,title,content);
        }
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("DELIVERY_TAG:" + deliveryTag);
        System.out.println(headers);
        //手工签收
        channel.basicAck(deliveryTag,false);
    }
}
