package com.rjs.messaging.tool.jfx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjs.messaging.tool.service.JmsPublisher;
import com.rjs.messaging.tool.service.MessageEventFactory;
import com.rjs.messaging.tool.service.MessageEventListener;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MainPanel extends GridPane implements MessageEventListener {
    private JmsPublisher jmsPublisher;

    private TextField destinationTextField = new TextField();
    private TextArea messageTextArea = new TextArea();
    private TextArea receivedTextArea = new TextArea();

    @Autowired
    public MainPanel(MessageEventFactory messageEventFactory, JmsPublisher jmsPublisher) {
        this.jmsPublisher = jmsPublisher;

        messageEventFactory.addMessageEventLiener(this);
        initComponents();
    }

    @Override
    public void messageReceived(Map<String, Object> message) {
//        System.out.println("Main panel message received: " + message);
        receivedTextArea.setText(message.toString());
    }

    private void initComponents() {
        Label destLabel = new Label("Destination:");
        destLabel.setLabelFor(destinationTextField);

        Button publishButton = new Button("Send");
        publishButton.setOnAction(e -> {
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                Map<String, Object> message = objectMapper.readValue(messageTextArea.getText(), new TypeReference<LinkedHashMap<String,Object>>() {});

                jmsPublisher.sendMessage(destinationTextField.getText(), message);
            }
            catch (IOException ex) {
                Logger.getLogger("ObjectMapper").log(Level.SEVERE, "Error parsing JSON message:", ex);
            }
        });

        Label messageLabel = new Label("Message Sent:");
        messageLabel.setLabelFor(messageTextArea);

        Label receivedLabel = new Label("Message Received:");
        receivedLabel.setLabelFor(receivedTextArea);

        addRow(0, destLabel, destinationTextField, publishButton);
        addRow(1, messageLabel, messageTextArea);
        addRow(2, receivedLabel, receivedTextArea);

        setMinSize(640, 360);
    }
}
