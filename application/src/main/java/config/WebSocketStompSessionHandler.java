package config;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import controller.ChatController;
import model.ChatMessage;

public class WebSocketStompSessionHandler implements StompSessionHandler {
	private Logger logger = LoggerFactory.getLogger(WebSocketStompSessionHandler.class);
	private ChatController chatController;

	public WebSocketStompSessionHandler(ChatController chatController) {
		// TODO Auto-generated constructor stub
		this.chatController = chatController;
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		// TODO Auto-generated method stub
		return ChatMessage.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		ChatMessage msg = (ChatMessage) payload;
        logger.info("Received : " + msg.getContent() + " from : " + msg.getSender());
        chatController.getMessage(msg);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		logger.info("New session established : " + session.getSessionId());
		session.subscribe("/topic/public", this);
		logger.info("Subscribed to /topic/public");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
			Throwable exception) {
		logger.error("Got an exception", exception);
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		// TODO Auto-generated method stub
	}

}
