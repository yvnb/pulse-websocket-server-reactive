package com.pulsemusic.websocket.event;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class SessionConnectEventListener implements ApplicationListener<SessionConnectEvent>{

	@Override
	public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
		
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());

		System.out.println("getting the native header from connect event ***" + headerAccessor);
	}

}
