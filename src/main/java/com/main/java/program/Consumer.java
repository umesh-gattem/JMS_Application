package com.main.java.program;

/**
 * A class consumer is the client of JMS which is used as Message Consumer which
 * consumes message from the queue.
 * 
 * This class sends the queueName to ActiveMqManager to consume the message from
 * the queue.
 * 
 * @author umesh
 * 
 * @since 18-07-2016
 *
 */

public class Consumer {

	public static void main(String[] args) {
		ActiveMqManager.getInstance().receiveMessage("QueueOne");
	}

}