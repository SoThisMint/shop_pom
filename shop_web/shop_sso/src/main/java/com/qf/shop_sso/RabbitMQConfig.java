package com.qf.shop_sso;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/18 19:18
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue getQueue() {
        return new Queue("email_queue");
    }
}
