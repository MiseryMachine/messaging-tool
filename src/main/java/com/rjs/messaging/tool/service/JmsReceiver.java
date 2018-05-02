package com.rjs.messaging.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("jmsReciever")
public class JmsReceiver {
    private MessageEventFactory messageEventFactory;

    @Autowired
    public JmsReceiver(MessageEventFactory messageEventFactory) {
        this.messageEventFactory = messageEventFactory;
    }

    @JmsListener(destination = "*", containerFactory = "appConnectionFactory")
    public void messageRescieved(Map<String, Object> message) {
        messageEventFactory.fireMessageReceivedEvent(message);
    }
}
