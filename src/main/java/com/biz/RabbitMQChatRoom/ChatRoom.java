package com.biz.RabbitMQChatRoom;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/**
 * 聊天室的主类
 * @author JIA
 *
 */
public class ChatRoom {

	private static Scanner scanner;
	
	public static void main(String[] args) throws Exception {
		String nickname = welcome();
		System.out.println(nickname + "你好，现在你可以开始聊天了。。。");
		//先让用户可以接收信息
		new Receive(nickname).receive();
		//再不断的接收用户发送的信息
		new Publish(nickname).readySend();
		//用户输入了Q/q,从readySend方法中退出
		System.out.println("你退出了聊天室...");
		scanner.close();
		System.exit(0);
	}

	/**
	 * 欢迎语，设置昵称
	 */
	private static String welcome() {
		System.out.println("欢迎来到Rabbit聊天室...");
		System.out.println("输入q可以退出聊天室...");
		System.out.println("请先设置你的昵称...");
		while(true){
			String nickname = getScanner().nextLine();
			if (nickname.matches("[qQ]")) {
				System.out.println("你退出了聊天室...");
				System.exit(0);
			}if (StringUtils.isBlank(nickname)) {
				System.out.println("昵称不能为空，请重新输入...");
			}else{
				return nickname;
			}
		}
	}
	
	/**
	 * 获取键盘输入器
	 * @return
	 */
	public static Scanner getScanner(){
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}

}
