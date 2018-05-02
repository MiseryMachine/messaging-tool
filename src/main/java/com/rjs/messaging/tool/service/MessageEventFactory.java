package com.rjs.messaging.tool.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service("messageEventFactory")
public class MessageEventFactory {
    private Set<MessageEventListener> messageEventListeners = new HashSet<>();

    public MessageEventFactory() {
    }

    public void fireMessageReceivedEvent(Map<String, Object> message) {
        new Thread(() -> messageEventListeners.forEach(l -> l.messageReceived(message)))
        .start();
    }

    public void addMessageEventLiener(MessageEventListener listener) {
        messageEventListeners.add(listener);
    }

    public void removeMessageEventListener(MessageEventListener listener) {
        messageEventListeners.remove(listener);
    }
}
