package com.main.java.program;

import javax.jms.MessageProducer;

import org.apache.log4j.Logger;

/**
 * A class producer is the Client of JMS which is used as Message producer which
 * produces the messages into the queue.
 * 
 * @author umesh
 * 
 * @since 18-07-2016
 *
 */
public class Producer {
	
	private static Logger LOGGER = Logger.getLogger(Producer.class);

	public static void main(String[] args) {
		MessageProducer producer = ActiveMqManager.getProducer("Razorthink");
		LOGGER.info(producer);
	}
}
