package com.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by Shyam Sundar on 04/02/21.
 */

public class TestChannelPerThread {
    public static void main(String[] args) throws Exception {
        CamelContext defaultCamelContext = new DefaultCamelContext();
        RabbitMQComponent component = new RabbitMQComponent();
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        component.setConnectionFactory(connectionFactory);
        defaultCamelContext.addComponent("amqptest", component);
        defaultCamelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:InCamelTest").threads().to("amqptest:OutCamelTestQueue?concurrentConsumers=5");
                from("amqptest:OutCamelTestQueue?concurrentConsumers=5").to("file:OutCamelTest");
            }
        });
        defaultCamelContext.start();
    }
}
