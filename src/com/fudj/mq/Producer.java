package com.fudj.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by portk on 2016/9/25 0025.
 */
public class Producer {
    private static final int SEND_NUMBER = 5;

    public static void main(String[] args) {
        Connection connection = null;
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE.booleanValue(), 1);

            //通过destination切换 queue和topic
            //  Destination destination = session.createQueue("FirstQueue");//Queue
            Destination destination = session.createTopic("FirstTopic");//Topic
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(1);
            sendMessage(session, producer);
            session.close();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            }

        }

    }

    public static void sendMessage(Session session, MessageProducer producer) throws Exception {
//        for(int i = 1; i <= 5; ++i) {
//            TextMessage message = session.createTextMessage(i+"");
//            System.out.println("发送消息：ActiveMq 发送的消息" + i);
//            producer.send(message);
//           // if(i%1000==0)
//            session.commit();
//        }
        TextMessage message = session.createTextMessage("大家辛苦了！");
        producer.send(message);
        session.commit();
    }
}
