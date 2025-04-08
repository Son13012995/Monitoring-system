package com.project;

import com.project.mqtt.MqttHandler;
import com.project.mqtt.OnReceiveMessageCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
public class MonitoringSystemApplication {

    public static void main(String[] args) {
        String brokerUrl = "tcp://127.0.0.1:1883";
        String envUrl = System.getenv("MQTT_BROKER_URL");
        if (envUrl != null && !envUrl.isEmpty()) {
            brokerUrl = envUrl;
        }

        MqttHandler mqtt = MqttHandler.getInstance(brokerUrl, "EAMS_Client");

        try {
            mqtt.connect();

            mqtt.listenToTopic("iot/smart_outlets/+/data", new OnReceiveMessageCallback() {
                @Override
                protected void onReceive(String topic, String payload) {
                    System.out.println("Received topic: " + topic + " payload: " + payload); // Should replace with adding log
//                    Payload is a jsonObject
//                    {
//                        "outlet_id": string,
//                        "power": float,
//                        "timestamp": string
//                    }
                    // Implement day du lieu tu payload vao log table

                }
            });
        } catch (MqttException e) {
            System.out.println("[MQTT] MqttException: " + e.getMessage());
            e.printStackTrace();
        }

        SpringApplication.run(MonitoringSystemApplication.class, args);
    }

}
