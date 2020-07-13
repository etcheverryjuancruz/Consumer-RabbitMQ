package workers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class Worker {
	
	  private static final String TASK_QUEUE_NAME = "task_queue";

	  public static void main(String[] argv) throws Exception {
		    ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    final Connection connection = factory.newConnection();
		    final Channel channel = connection.createChannel();
		    
		    boolean queueSurvive = true; // the queue will survive a RabbitMQ node restart
		    channel.queueDeclare(TASK_QUEUE_NAME, queueSurvive, false, false, null); 
		    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		    channel.basicQos(1); // prefetch=1 tells RabbitMQ not to give more than one message to a worker at a time

		    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
		        String message = new String(delivery.getBody(), "UTF-8");

		        System.out.println(" [x] Received '" + message + "'");
		        try {
		            doWork(message);
		        } finally {
		            System.out.println(" [x] Done");
		            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); //positive 
		        }
		    };
		    boolean autoAck = false;
		    channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
		  }

		  private static void doWork(String task) {
		    for (char ch : task.toCharArray()) {
		        if (ch == '.') {
		            try {
		                Thread.sleep(1000);
		            } catch (InterruptedException _ignored) {
		                Thread.currentThread().interrupt();
		            }
		        }
		    }
		  }

}
