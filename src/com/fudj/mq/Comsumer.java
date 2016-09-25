package com.fudj.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by portk on 2016/9/25 0025.
 */
public class Comsumer {
    private static final int SEND_NUMBER = 5;

    public static void main(String[] args) {
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
            //producer.setDeliveryMode(1);
//            getMessage(session,consumer);
//            session.close();
//            session.commit();
        } catch (Exception var15) {
            var15.printStackTrace();
        }
// finally {
//            try {
//                if(null != connection) {
//                    connection.close();
//                }
//            } catch (Throwable var14) {
//
//            }
//
//        }

    }

    public static void getMessage(Session session, MessageConsumer consumer) throws Exception {
        boolean i = true;
        int l = 0;
        while (i) {
            l++;
            TextMessage message = (TextMessage) consumer.receive(10000);
            if (message == null)
                i = false;
            System.out.println("收到消息：ActiveMq 发送的消息" + message.getText());
            if (l == 10000) {
                session.commit();
                l = 0;
            }
        }

    }
}
