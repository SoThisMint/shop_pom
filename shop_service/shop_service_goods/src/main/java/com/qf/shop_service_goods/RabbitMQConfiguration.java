package com.qf.shop_service_goods;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/12 17:49
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
public class RabbitMQConfiguration {

    public static final String FANOUT_NAME = "goods_fanoutexchange";

    /**
     * 声明两个队列
     *
     * @return
     */
    @Bean
    public Queue getQueue1() {
        return new Queue("goods_queue1");
    }

    @Bean
    public Queue getQueue2() {
        return new Queue("goods_queue2");
    }

    /**
     * 声明交换机
     * @return
     */
    @Bean
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange(FANOUT_NAME);
    }

    /**
     * 将两个队列全绑定到交换机
     * @param getQueue1
     * @param getFanoutExchange
     * @return
     */
    @Bean
    public Binding getBinding1(Queue getQueue1, FanoutExchange getFanoutExchange) {
        return BindingBuilder.bind(getQueue1).to(getFanoutExchange);
    }

    @Bean
    public Binding getBinding2(Queue getQueue2, FanoutExchange getFanoutExchange) {
        return BindingBuilder.bind(getQueue2).to(getFanoutExchange);
    }


}
