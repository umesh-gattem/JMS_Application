package com.main.java.program;

import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
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
 * @since 19-07-2016
 *
 */
public class ActiveMqManager {

	private static ActiveMqManager activeManager;

	public static ActiveMqManager getInstance() {
		if (activeManager == null) {
			activeManager = new ActiveMqManager();
		}
		return activeManager;
	}

	private Logger LOGGER = Logger.getLogger(ActiveMqManager.class);

	ActiveMQConnectionFactory connectionFactory = null;
	Connection connection = null;
	Session session = null;
	Destination destination = null;
	MessageProducer producer = null;
	MessageConsumer consumer = null;
	TextMessage textMessage;
	boolean flag = false;

	HashMap<String, MessageProducer> queueList = new HashMap<String, MessageProducer>();

	/**
	 * This method is used to initialize the objects like connectionFactory,
	 * connection, session.. These objects will be initialize only when these
	 * are null...
	 */

	public void initialiseConnections() {
		if (connectionFactory == null) {
			connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		}
		if (connection == null && session == null) {
			try {
				connection = connectionFactory.createConnection();
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				connection.start();
			} catch (JMSException e) {
				LOGGER.info("JMS exception");
			}
		}

	}

	/**
	 * This is the method used my MessageProducer to produce the message into
	 * the specified queue.
	 * 
	 * @param queueName
	 *            .It is the name of the queue.
	 * @param message
	 *            .It is the message which produces to queue.
	 */

	public void sendMessage(String queueName, String message) {
		try {
			initialiseConnections();
			producer = createProducer(queueName);
			textMessage = session.createTextMessage();
			textMessage.setText(message);
			producer.send(textMessage);
			LOGGER.info("Message produced is :" + textMessage.getText());
		} catch (JMSException e) {
			LOGGER.info("JMS exception");
		}
	}

	/**
	 * This method is used to create the producer which produces the messages to
	 * the queue.
	 * 
	 * @param queueName
	 * @return
	 */

	private MessageProducer createProducer(String queueName) {

		if (queueList.containsKey(queueName)) {
			return queueList.get(queueName);
		} else {
			try {
				Destination des = session.createQueue(queueName);
				producer = session.createProducer(des);
				queueList.put(queueName, producer);
			} catch (JMSException e) {
				LOGGER.info("JMS exception");
			}
		}
		return producer;
	}

	/**
	 * The receiveMessage method will consumes the message from the specified
	 * queue.
	 * 
	 * @param queueName..
	 *            This parameter says from where to consume message
	 */

	public void receiveMessage(String queueName) {
		try {
			initialiseConnections();
			if (queueList.containsKey(queueName)) {
				Destination des = session.createQueue(queueName);
				consumer = session.createConsumer(des);
				Message message = consumer.receive();
				TextMessage text = (TextMessage) message;
				LOGGER.info("message is received:" + text.getText());
			} else {
				LOGGER.info("Queue is not present");
			}
		} catch (JMSException e) {
			LOGGER.info("JMS exception");
		}

	}
}
