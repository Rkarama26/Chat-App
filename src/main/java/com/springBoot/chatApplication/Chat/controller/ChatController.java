package com.springBoot.chatApplication.Chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		return chatMessage;
	}
	//this will allow us to make connection b/w user and WebSocket
	public ChatMessage addUser(@Payload ChatMessage chatMessage, 
			SimpMessageHeaderAccessor headerAccessor ) {
	   	// add username om web socket session 
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
