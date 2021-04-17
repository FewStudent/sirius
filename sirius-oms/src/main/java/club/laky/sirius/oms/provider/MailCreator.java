package club.laky.sirius.oms.provider;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MailCreator {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);

        //id + 时间戳 全局唯一  用于ack保证唯一一条消息,这边做测试写死一个。但是在做补偿策略的时候，必须保证这是全局唯一的消息
        CorrelationData correlationData = new CorrelationData("1234567890");
        rabbitTemplate.convertAndSend("test.test.exchange", "test.test.queue", msg, correlationData);
    }
}

