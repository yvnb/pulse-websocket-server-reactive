package com.pulsemusic.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {


	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		registry.enableStompBrokerRelay("/topic/", "/queue/")
				// .setUserDestinationBroadcast("/topic/unresolved.user.dest")
				// .setUserRegistryBroadcast("/topic/registry.broadcast")
				.setRelayHost("localhost").setRelayPort(61613).setSystemLogin("guest").setSystemPasscode("guest");

		// registry.enableSimpleBroker("/topic");
		registry.setPreservePublishOrder(true);
		registry.setApplicationDestinationPrefixes("/app","/topic/");

	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/*
		 * This configures a STOMP (Simple Text Oriented Messaging Protocol) endpoint
		 * for our websocket to be hosted on
		 */
		registry.addEndpoint("/websocket");
		/*
		 * This configures an endpoint with a fallback for SockJS in case the client (an
		 * old browser) doesn't support WebSockets natively
		 */
		registry.addEndpoint("/sockjs").withSockJS();
	}

}
