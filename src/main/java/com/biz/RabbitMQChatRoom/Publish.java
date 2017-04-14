package com.biz.RabbitMQChatRoom;

import org.apache.commons.lang3.StringUtils;

/**
 * 消息的发送类
 * @author JIA
 *
 */
public class Publish {
	
	private String queueName;
	
	public Publish(String queueName){
		this.queueName = queueName;
	}
	
	/**
	 * 随时准备发送消息
	 * @throws Exception 
	 */
	public void readySend() throws Exception {
		while(true){
			String message = ChatRoom.getScanner().nextLine();
			if (StringUtils.isBlank(message)) {
				System.out.println("请输入内容...");
			}else if (message.matches("[qQ]")) {
				break;
			}else{
				send(message);
			}
		}
	}
	
	
	/**
	 * 发送消息
	 * @param message	消息
	 * @throws Exception
	 */
	private void send(String message) throws Exception{
		//向指定的exhange交换机里发送信息，按照fanout的路由规则路由到所有的queue里
		MyChannel.getChannel(queueName).basicPublish(MyChannel.CHAT_ROOM_EXCHANGE, "", null, (queueName+"\t--->\t"+message).getBytes());
	}
}
