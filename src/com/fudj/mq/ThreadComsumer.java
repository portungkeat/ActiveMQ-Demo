package com.fudj.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

/**
 * Created by portk on 2016/9/25 0025.
 */
public class ThreadComsumer implements Runnable {
    @Override
    public void run() {
        Connection connection;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE.booleanValue(), 1);
            //通过destination切换 queue和topic
            //  Destination destination = session.createQueue("FirstQueue");//Queue
            Destination destination = session.createTopic("FirstTopic");//Topic
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MsgListener());
        } catch (Exception var15) {
            var15.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadComsumer threadComsumer = new ThreadComsumer();
        new Thread(threadComsumer).start();
        new Thread(threadComsumer).start();
        new Thread(threadComsumer).start();
        new Thread(threadComsumer).start();
        new Thread(threadComsumer).start();
        new Thread(threadComsumer).start();
    }
}
