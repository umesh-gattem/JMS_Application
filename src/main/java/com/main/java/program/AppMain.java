package com.main.java.program;

/**
 * This class is defined to produce or consume messages from the queue by
 * calling sendMessage and receiveMessage methods from ActiveMqManager.
 * 
 * @author umesh
 *
 * @since 19-07-2016
 *
 */

public class AppMain {

	public static void main(String[] args) {

		ActiveMqManager.getInstance().sendMessage("Sample Queue", "razorthink");
		ActiveMqManager.getInstance().receiveMessage("Sample Queue");
		ActiveMqManager.getInstance().sendMessage("Queue", "Company");
	}
}
