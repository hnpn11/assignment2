package config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import controller.ChatController;
import model.ChatMessage;
import response.UserInfo;

public class WebSocketConnect {
	public static StompSession stompSession;
	private static WebSocketConnect webSocketConnect = null;
	private String URL = "ws://localhost:8081/chat";
	
	private static Logger logger = LoggerFactory.getLogger(WebSocketConnect.class);

	private WebSocketConnect() {
		super();
	}

	public static WebSocketConnect getInstance() {
		if (null == webSocketConnect) {
			webSocketConnect = new WebSocketConnect();
		}
		return webSocketConnect;
	}

	public void connect(ChatController chatController) {
		List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());

        SockJsClient sockjsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockjsClient);

        // StringMessageConverter
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSessionHandler sessionHandler = new WebSocketStompSessionHandler(chatController);
        ListenableFuture<StompSession> sessionListenableFuture = stompClient.connect(URL, sessionHandler);

		try {
			stompSession = sessionListenableFuture.completable().get();
			logger.info("New session established : " + stompSession.getSessionId());
			ChatMessage msg = new ChatMessage();
			msg.setContent(UserInfo.getInstance().getUsername() + " is connected");
			msg.setType(ChatMessage.MessageType.CHAT);
			msg.setSender("Notification");
			stompSession.send("/app/chat.addUser", msg);
			logger.info("Message sent to websocket server");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
