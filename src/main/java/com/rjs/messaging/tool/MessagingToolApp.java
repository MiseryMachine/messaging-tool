package com.rjs.messaging.tool;

import com.rjs.messaging.tool.jfx.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MessagingToolApp extends Application {
    private static ConfigurableApplicationContext context;

    public MessagingToolApp() {
    }

    public static void main(String[] args) {
        context = SpringApplication.run(MessagingToolApp.class, args);

        // Start the JavaFX app
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainPanel mainPanel = context.getBean(MainPanel.class);
        Scene scene = new Scene(mainPanel);

        primaryStage.setMinHeight(360);
        primaryStage.setMinWidth(640);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.setTitle("");
        primaryStage.show();
    }
}
