package com.rjs.messaging.tool.service;

import java.util.Map;

public interface MessageEventListener {
    void messageReceived(Map<String, Object> message);
}
