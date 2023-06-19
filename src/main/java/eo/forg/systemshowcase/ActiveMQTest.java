package eo.forg.systemshowcase;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQTest {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:8082");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("myQueue");

        MessageProducer producer = session.createProducer(queue);
        TextMessage message = session.createTextMessage("Hello, ActiveMQ!");
        producer.send(message);

        MessageConsumer consumer = session.createConsumer(queue);
        Message receivedMessage = consumer.receive(1000);
        if (receivedMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) receivedMessage;
            System.out.println("Received message: " + textMessage.getText());
        }

        session.close();
        connection.close();
    }
}
