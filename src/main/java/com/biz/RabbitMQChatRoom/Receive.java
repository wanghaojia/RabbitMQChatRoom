package com.biz.RabbitMQChatRoom;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 消息的接收类
 * @author JIA
 *
 */
public class Receive {

	private String queueName;
	
	public Receive(String queueName){
		this.queueName = queueName;
	}
	
	/**
	 * 开始接受消息
	 * @throws Exception
	 */
	public void receive() throws Exception{
		Channel channel = MyChannel.getChannel(queueName);
		//设置接受到消息之后的回调
		Consumer consume = new DefaultConsumer(channel){
				@Override
				public void handleDelivery(java.lang.String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					System.out.println(new String(body,"UTF-8"));
				}
		};
		//设置chanel的消费者，会自动确认ACK
		channel.basicConsume(queueName,true,consume);
	}
}
