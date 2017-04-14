package com.biz.RabbitMQChatRoom;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 获取通道的类
 * @author JIA
 *
 */
public class MyChannel {
	
	private static Channel channel;
	private static String queueName;
	
	//交换器的名字
	public static String CHAT_ROOM_EXCHANGE = "chatRoom.Exchange";  
	
	/**
	 * 初始化通道
	 * @throws Exception 
	 */
	public static Channel getChannel(String queueName) throws Exception {
		if (channel == null || !queueName.equals(MyChannel.queueName)) {
			MyChannel.queueName = queueName;
			ConnectionFactory factory = new ConnectionFactory();
			Connection connection = factory.newConnection();
			channel = connection.createChannel();
			//设置该通道指向的broker里的exhange的路由方式为fanout，向所有绑定的queue里发送信息
			channel.exchangeDeclare(CHAT_ROOM_EXCHANGE, "fanout");
			//新建一个队列，不是持久化的，不是私有的，当不在使用的时候自动删除
			channel.queueDeclare(queueName, false, false, true, null);
			//将该队列绑定到exhange交换器上
			channel.queueBind(queueName, CHAT_ROOM_EXCHANGE, "");
		}
		return channel;
	}
}
