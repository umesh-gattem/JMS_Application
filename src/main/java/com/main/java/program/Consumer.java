package com.main.java.program;

import javax.jms.MessageConsumer;

import org.apache.log4j.Logger;

/**
 * A class consumer is the client of JMS which is used as Message Consumer which
 * consumes message from the queue.
 * 
 * @author umesh
 * 
 * @since 18-07-2016
 *
 */

public class Consumer {

	private static Logger LOGGER = Logger.getLogger(Consumer.class);

	public static void main(String[] args) {
		MessageConsumer consumer = ActiveMqManager.getConsumer();
		LOGGER.info(consumer);
	}

}