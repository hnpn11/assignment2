package tools;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.TimerTask;

import constant.Constant;
import utils.ApiRequest;

public class ScreenCaptureJob extends TimerTask {

	@Override
	public void run() {
		System.out.println("Timer task started at:" + new Date());
		ScreenCapture screenCapture = null;
		try {
			screenCapture = ScreenCapture.getInstance();
			File file = screenCapture.capture();

			if (file != null) {
				ApiRequest apiRequest = ApiRequest.getInstance();
				apiRequest.callApiToUploadImage(file, new URL(Constant.UPLOAD_FILE_URL));
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Timer task finished at:" + new Date());

	}

}
