package org.example;

import com.rabbitmq.client.*;

public class Consumer {


    public static void main(String[] argv) throws Exception {
        Channel channel = ConnectionManager.getConnection().createChannel();

        channel.queueDeclare(Config.DEFAULT_QUEUE, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        CancelCallback cancelCallback = s -> {
            System.out.println(s);
        };

        channel.basicConsume(Config.DEFAULT_QUEUE, true, deliverCallback, cancelCallback);

    }
}

