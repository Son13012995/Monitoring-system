package com.project.mqtt;

public abstract class OnReceiveMessageCallback {

    /**
     * Called when an MQTT message is received.
     *
     * @param topic   The topic from which the message was received.
     * @param payload The message payload as a string.
     */
    public final void handle(String topic, String payload) {
        if (isValidTopic(topic)) {
            System.out.println("[MQTT] Received on topic: " + topic);
            System.out.println("[MQTT] Payload: " + payload);
            onReceive(topic, payload);
        } else {
            System.err.println("[MQTT] Invalid topic received: " + topic);
        }
    }

    /**
     * Implement this method in subclasses to define custom logic.
     *
     * @param topic   The topic from which the message was received.
     * @param payload The payload of the MQTT message.
     */
    protected abstract void onReceive(String topic, String payload);

    /**
     * Optionally validate topic format.
     * You can override this if you need custom topic filtering.
     *
     * @param topic The MQTT topic.
     * @return true if the topic is valid and should be handled.
     */
    protected boolean isValidTopic(String topic) {
        return topic != null && !topic.isEmpty();
    }
}
