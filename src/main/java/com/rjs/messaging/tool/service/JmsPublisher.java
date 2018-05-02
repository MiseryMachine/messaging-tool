package com.rjs.messaging.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JmsPublisher {
    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String destination, Map<String, Object> message) {
        jmsTemplate.convertAndSend(destination, message);
    }
}
