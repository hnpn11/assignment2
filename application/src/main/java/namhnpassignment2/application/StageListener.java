package namhnpassignment2.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
	private final String APPLICATION_TITLE;
	private final Resource fxml;

	public StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
			@Value("classpath:/LoginScreen.fxml") Resource fxml) {
		this.APPLICATION_TITLE = applicationTitle;
		this.fxml = fxml;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		// TODO Auto-generated method stub

		try {
			Stage stage = event.getStage();
			Parent loginScreen = FXMLLoader.load(fxml.getURL());

			stage.setTitle(APPLICATION_TITLE);
			stage.setScene(new Scene(loginScreen));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
