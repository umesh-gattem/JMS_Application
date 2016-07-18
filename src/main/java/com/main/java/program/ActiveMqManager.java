package com.main.java.program;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

/**
 * A class ActiveMqManager is the manager which stores all JMS objects like
 * connection factory, sessions, destination, message producer, message
 * consumer.. This is the main program where the queue will be produce or
 * consume message based on the user choice.
 * 
 * @author umesh
 *
 */
public class ActiveMqManager {

	private static Logger LOGGER = Logger.getLogger(ActiveMqManager.class);

	static ActiveMQConnectionFactory connectionFactory = null;
	static Connection connection = null;
	static Session session = null;
	static Destination destination = null;
	static MessageProducer producer = null;
	static MessageConsumer consumer = null;
	static TextMessage textMessage;

	public static void initialiseConnections() {
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			destination = session.createQueue("Queue");
		} catch (JMSException e) {
			LOGGER.info("JMS exception");
		}
	}

	public static MessageProducer getProducer(String message) {
		try {
			initialiseConnections();
			producer = session.createProducer(destination);
			textMessage = session.createTextMessage();
			textMessage.setText(message);
			producer.send(textMessage);
		} catch (JMSException e) {
			LOGGER.info("JMS exception");
		}
		return producer;
	}

	public static MessageConsumer getConsumer() {
		try {
			initialiseConnections();
			consumer = session.createConsumer(destination);
			consumer.receive();
		} catch (JMSException e) {
			LOGGER.info("JMS exception");
		}
		return consumer;

	}
}
