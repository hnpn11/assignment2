package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import constant.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import response.ApiResponse;
import response.UserInfo;
import utils.ApiRequest;

public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	@FXML
	private TextField tfUsername;

	@FXML
	private TextField tfPassword;

	@FXML
	private Label lbLoginStatus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbLoginStatus.setText("");
	}

	@FXML
	public void login(ActionEvent event) throws IOException {
		String username = tfUsername.getText();
		String password = tfPassword.getText();

		ApiRequest apiRequest = ApiRequest.getInstance();
		ApiResponse apiResponse = apiRequest.callPostApi(new User(username, password).toString(),
				new URL(Constant.LOGIN_URL));

		if (apiResponse.getStatus() == HttpURLConnection.HTTP_OK) {
			UserInfo.getInstance()
					.setUsername(new Gson().fromJson(apiResponse.getResponseString(), UserInfo.class).getUsername());
			UserInfo.getInstance()
					.setToken(new Gson().fromJson(apiResponse.getResponseString(), UserInfo.class).getToken());

			Parent chatScreen = FXMLLoader.load(getClass().getClassLoader().getResource("ChatScreen.fxml"));
			Scene chatScene = new Scene(chatScreen);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(chatScene);
			window.show();
		} else {
			lbLoginStatus.setText("Login Failed");
		}
	}

}
