package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] argv) throws Exception {
        Channel channel = ConnectionManager.getConnection().createChannel();

        channel.queueDeclare(Config.DEFAULT_QUEUE, false, false, false, null);
        String message = "Hello World!";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish("", Config.DEFAULT_QUEUE, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }


        channel.close();
        channel.getConnection().close();

    }
}

