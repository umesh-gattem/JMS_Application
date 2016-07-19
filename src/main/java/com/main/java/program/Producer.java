package com.main.java.program;

/**
 * A class producer is the Client of JMS which is used as Message producer which
 * produces the messages into the queue.
 * 
 * This class sends the queueName and message to ActiveMqManager class to
 * produce that message into queue.
 * 
 * @author umesh
 * 
 * @since 18-07-2016
 *
 */
public class Producer {

	public static void main(String[] args) {

		ActiveMqManager.getInstance().sendMessage("QueueOne", "razorthink");
	}
}
