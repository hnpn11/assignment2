package namhnpassignment2.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception {
		 ApplicationContextInitializer<GenericApplicationContext> initializer = genericApplicationContext -> {
	            genericApplicationContext.registerBean(Application.class, () -> MainApplication.this);
	            genericApplicationContext.registerBean(Parameters.class, this::getParameters);
	            genericApplicationContext.registerBean(HostServices.class, this::getHostServices);
	        };

	        this.context = new SpringApplicationBuilder().sources(FxApplication.class)
	                .initializers(initializer).run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.context.publishEvent(new StageReadyEvent(primaryStage));
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		this.context.close();
		Platform.exit();
	}
}
