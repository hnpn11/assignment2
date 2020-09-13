package namhnpassignment2.application;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import tools.ScreenCaptureJob;

@SpringBootApplication
public class FxApplication {

	public static void main(String[] args) {
		TimerTask screenCaptureJob = new ScreenCaptureJob();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(screenCaptureJob, 0, 30 * 1000);
		
		Application.launch(MainApplication.class, args);
	}

}
