package com.pulsemusic.websocket.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.pulsemusic.websocket.model.Message;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MessageController {
	

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @MessageMapping("/send")
    public void send(Message message, Principal principal) {
        log.info(String.format("Received message [%s] on `/app/chat` message mapping!", message.toString()));
        LocalDateTime timestamp = LocalDateTime.now();
        System.out.println("getting the principal object in message controller " + principal.getName());
        Message textMessage = new Message(message.getFrom(), message.getMessage(), timestamp, message.getChannel());
        simpMessagingTemplate.convertAndSend("/topic/"+message.getChannel(),   textMessage);
       
    }
    
	@MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
		log.error(String.format("Error handling message: [%s]", exception.getMessage()));
        return exception.getMessage();
    }
    
	@SubscribeMapping("/{id}")
	public Message onSubscribe(@DestinationVariable("id") String id) {
	    Message subscribeMessage = new Message("venkata", "Subscription message from " + id, LocalDateTime.now(), id);
	    return subscribeMessage;
	}
	
	
    
}
