package tools;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

public final class ScreenCapture {
	private Robot robot;
	private BufferedImage bufferedImage;
	private Rectangle screenFrame;
	private static ScreenCapture screenCapture = null;

	private ScreenCapture() throws AWTException {
		super();
		this.robot = new Robot();
		this.screenFrame = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public Rectangle getScreenFrame() {
		return screenFrame;
	}

	public void setScreenFrame(Rectangle screenFrame) {
		this.screenFrame = screenFrame;
	}

	public static ScreenCapture getInstance() throws AWTException {
		if (null == screenCapture) {
			screenCapture = new ScreenCapture();
		}
		return screenCapture;
	}

	public File capture() throws IOException {
		this.bufferedImage = this.robot.createScreenCapture(this.screenFrame);
		File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator
				+ "namhnp-assignment2" + File.separator + "screen-capture"
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss")) + ".png");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		if (ImageIO.write(bufferedImage, "png", file)) {
			return file;
		} else {
			return null;
		}
	}
}
