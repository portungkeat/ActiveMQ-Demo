package com.fudj.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by portk on 2016/9/25 0025.
 */
public class MsgListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
