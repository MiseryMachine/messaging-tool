package com.rjs.messaging.tool.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class LoggingMessageEventListener implements MessageEventListener {
    private static final Logger LOGGER = Logger.getLogger(LoggingMessageEventListener.class.getName());

    public LoggingMessageEventListener() {
    }

    @Override
    public void messageReceived(Map<String, Object> message) {
        LOGGER.info("Message  received:\n" + message);
    }
}
