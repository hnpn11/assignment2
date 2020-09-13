package namhnpassignment2.application;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

class StageReadyEvent extends ApplicationEvent {

	public StageReadyEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Stage getStage() {
		return Stage.class.cast(getSource());
	}
}
