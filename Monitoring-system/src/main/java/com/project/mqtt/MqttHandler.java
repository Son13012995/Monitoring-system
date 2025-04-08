package com.project.mqtt;

import org.eclipse.paho.client.mqttv3.*;

/**
 * Singleton MQTT handler for connecting to an MQTT broker, subscribing to topics,
 * and handling messages using Eclipse Paho MQTT client.
 *
 * <p>This class provides a simple interface to connect, disconnect, and listen to topics.</p>
 *
 * Example usage:
 * <pre>{@code
 * MqttHandler mqtt = MqttHandler.getInstance("tcp://localhost:1883", "clientId");
 * mqtt.connect();
 * mqtt.listenToTopic("iot/devices", (topic, payload) -> {
 *     System.out.println("Received: " + payload);
 * });
 * }</pre>
 *
 * @author
 */
public class MqttHandler {

    /** Singleton instance */
    private static MqttHandler instance;

    /** MQTT broker URL, e.g., tcp://localhost:1883 */
    private final String brokerUrl;

    /** Unique client ID */
    private final String clientId;

    /** MQTT client instance */
    private MqttClient mqttClient;

    /**
     * Private constructor to enforce singleton pattern.
     *
     * @param brokerUrl the MQTT broker URL
     * @param clientId  a unique ID for the MQTT client
     */
    private MqttHandler(String brokerUrl, String clientId) {
        this.brokerUrl = brokerUrl;
        this.clientId = clientId;
    }

    /**
     * Returns the singleton instance of {@code MqttHandler}.
     *
     * @param brokerUrl the MQTT broker URL
     * @param clientId  a unique client ID
     * @return the singleton instance
     */
    public static MqttHandler getInstance(String brokerUrl, String clientId) {
        if (instance == null) {
            synchronized (MqttHandler.class) {
                if (instance == null) {
                    instance = new MqttHandler(brokerUrl, clientId);
                }
            }
        }
        return instance;
    }

    /**
     * Connects the MQTT client to the broker with automatic reconnect and clean session options.
     *
     * @throws MqttException if the client fails to connect
     */
    public void connect() throws MqttException {
        if (mqttClient == null || !mqttClient.isConnected()) {
            mqttClient = new MqttClient(brokerUrl, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            mqttClient.connect(options);
            System.out.println("[MQTT] Connected to " + brokerUrl);
        }
    }

    /**
     * Subscribes to the given MQTT topic and invokes the callback when a message is received.
     *
     * @param topic    the topic to subscribe to
     * @param callback the callback handler for received messages
     * @throws MqttException if subscription fails
     */
    public void listenToTopic(String topic, OnReceiveMessageCallback callback) throws MqttException {
        mqttClient.subscribe(topic, (receivedTopic, message) -> {
            String payload = new String(message.getPayload());
            callback.handle(receivedTopic, payload);
            callback.onReceive(receivedTopic, payload); // Optional duplicate method support
        });
    }

    /**
     * Disconnects the MQTT client from the broker.
     *
     * @throws MqttException if the client fails to disconnect
     */
    public void disconnect() throws MqttException {
        if (mqttClient != null && mqttClient.isConnected()) {
            mqttClient.disconnect();
            System.out.println("[MQTT] Disconnected");
        }
    }
}
