package controller;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import config.WebSocketConnect;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.ChatMessage;
import model.ChatMessage.MessageType;
import response.UserInfo;

public class ChatController implements Initializable {
	private UserInfo userInfo = UserInfo.getInstance();
	
	@FXML
	AnchorPane anchorPanel;

	@FXML
	private TextArea taMessageList;

	@FXML
	private TextField tfInputMessage;

	private static StringBuilder listMessages = new StringBuilder();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WebSocketConnect.getInstance().connect(this);
	}
	
	@FXML
	public void send(ActionEvent event) throws AWTException, IOException {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setContent(tfInputMessage.getText());
		chatMessage.setSender(userInfo.getUsername());
		chatMessage.setType(MessageType.CHAT);

		// send message to server
		WebSocketConnect.stompSession.send("/app/chat.addUser", chatMessage);
		tfInputMessage.setText("");
	}

	public void getMessage(ChatMessage msg) {
		Platform.runLater(() -> {
			listMessages.append(msg.getSender() + ": " + msg.getContent() + "\n");
			taMessageList.setText(listMessages.toString());
			taMessageList.setScrollTop(Double.MAX_VALUE);
		});
	}

}
