// Rachael Colley 2014

package rach.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import rach.particle.HistoryParticleManager;
import rach.particle.IParticle;
import rach.particle.HistoryUrl;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HistoryParticleCanvas extends Stage implements PropertyChangeListener {

	private final String STAGE_TITLE = "History Particle Stage";
	private final double SCENE_WIDTH = 800;
	private final double SCENE_HEIGHT = 600;
	private final int START_INDEX = 0;
	private final int NUM_OF_TOP_URLS = 11;

	private BorderPane root;
	private Scene scene;
	private Canvas canvas;
	private Pane pane;

	private GraphicsContext graphicsContext;
	private static AnimationTimer timer = null;

	private int countDownTillNextHistoryParticle = 1;

	private List<IParticle> particles;
	private HistoryParticleManager historyParticleManager;

	private long timerStartedTime = 0;

	public HistoryParticleCanvas() {
		historyParticleManager = new HistoryParticleManager((int)SCENE_WIDTH,(int)SCENE_HEIGHT,START_INDEX);
		historyParticleManager.addChangeListener(this);
		initialiseMyStage();
		graphicsContext = canvas.getGraphicsContext2D();
		timer = new AnimationTimer() {

			long timeToStop = 0;
			long currentTime = 0;
			@Override
			public void handle(long now) {
				if (timerStartedTime == 0) {
					timerStartedTime = System.currentTimeMillis();
				}
				currentTime = System.currentTimeMillis();

				drawHistoryParticles();
				if (countDownTillNextHistoryParticle == 0) {
					countDownTillNextHistoryParticle = 1;
					addHistoryParticle();
				}
				countDownTillNextHistoryParticle--;

				drawDetails();
				if (currentTime >= timeToStop) {
					timeToStop = currentTime + 1000;
					//drawDetails();
				}
			}
		};
		timer.start();
	}
	private void addHistoryParticle() {
		historyParticleManager.addHistoryParticle();
	}
	private void drawHistoryParticles() {		
		graphicsContext.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		particles = historyParticleManager.updateAllParticles();
		for (IParticle p : particles) {
			Color c = Color.hsb(p.getColor()[0],p.getColor()[1],p.getColor()[2],.5);
			graphicsContext.setFill(c);
			graphicsContext.fillOval(p.getPosX(), p.getPosY(), p.getSize(), p.getSize());
		}
	}
	private void drawDetails() {
		if (particles.size() > 0) {
			IParticle ip = particles.get(particles.size()-1);
			drawParticleDate(ip);
			drawOtherStats();
			drawTopURLList();
		}
	}
	private double makeURLListXCoordinate() {
		double x = SCENE_WIDTH / 4;
		return x;
	}
	private double[] makeURLListYCoordinates() {
		double height = SCENE_HEIGHT /2;
		int numOfItemsIncludingTitle = NUM_OF_TOP_URLS;
		double heightOfEachItem = height / 11;
		double coords[] = new double[numOfItemsIncludingTitle];
		double y = (SCENE_HEIGHT / 2) + (height / 2);
		for (int i = 0; i < (numOfItemsIncludingTitle); i ++) {
			coords[i] = y;
			y -= heightOfEachItem;
		}
		return coords;
	}
	private void drawTopURLList() {
		double[] yCoords = makeURLListYCoordinates();
		double xCoord = makeURLListXCoordinate();
		List<HistoryUrl> topList = historyParticleManager.getUrlPosition();
		graphicsContext.setFill(Color.WHEAT);
		graphicsContext.setFont(new Font("Arial",20));
		graphicsContext.fillText("top 10 urls:", xCoord, yCoords[yCoords.length -1]);
		int indexCounter = 0;
		for (HistoryUrl s : topList) {
			float[] c = s.getColor();
			graphicsContext.setFill(Color.hsb(c[0],c[1],c[2]));
			graphicsContext.setFont(new Font("Arial",30));
			graphicsContext.fillText(s.getCount() + " " + s.getUrl(), xCoord, yCoords[indexCounter]);
			indexCounter ++;
		}
	}
	private void drawOtherStats() {
		double x = SCENE_WIDTH / 100;
		double y = SCENE_HEIGHT - (SCENE_HEIGHT / 20);
		drawParticleCount(x,y);
		drawUniqueURLCount(x, y + 25);
		drawTimeElapsed(x + (SCENE_WIDTH / 2),y);
	}
	private void drawParticleCount(double x, double y) {
		String currentCount = Integer.toString(historyParticleManager.getCurrentParticleCount());
		graphicsContext.setFill(Color.WHEAT);
		graphicsContext.setFont(new Font("Arial",20));
		graphicsContext.fillText("records: " + currentCount, x, y);
	}
	private void drawUniqueURLCount(double x, double y) {
		graphicsContext.setFill(Color.WHEAT);
		graphicsContext.setFont(new Font("Arial",20));
		String currentCount = Integer.toString(historyParticleManager.getUniqueURLCount());
		graphicsContext.fillText("unique urls: " + currentCount, x, y);
	}
	private void drawTimeElapsed(double x, double y) {
		DateTime dt = this.calculateDuration();
		graphicsContext.setFill(Color.WHEAT);
		graphicsContext.setFont(new Font("Arial",20));
		graphicsContext.fillText("time elapsed (animation) : " + dt.toString("mm:ss:SSS").toString(), x, y);
	}
	private void drawParticleDate(IParticle ip) {
		double x = SCENE_WIDTH / 4;
		double y = SCENE_HEIGHT / 20;
		graphicsContext.setFill(Color.WHEAT);
		graphicsContext.setFont(new Font("Arial",30));
		String currentParticleDate = ip.getStrDate();
		if (currentParticleDate == null) {
			currentParticleDate = "NULL";
		}
		graphicsContext.fillText("current date: " + currentParticleDate, x, y);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("endOfList") && evt.getNewValue().equals("true")) {		
			timer.stop();
		}
	}
	private DateTime calculateDuration() {
		DateTime dtStart = new DateTime(timerStartedTime);
		DateTime dtStop = new DateTime(System.currentTimeMillis());
		Instant iStart = new Instant(dtStart);
		long startPoint = iStart.getMillis();
		Instant iStop = new Instant(dtStop);
		long stopPoint = iStop.getMillis();
		long res = stopPoint - startPoint;
		DateTime fin = new DateTime(res);
		return fin;
	}
	private void initialiseMyStage() {
		this.setResizable(false);
		this.setTitle(STAGE_TITLE);

		root = new BorderPane();
		root.setStyle("-fx-background-color: #000000;");
		root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

		canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);

		pane = new Pane();
		pane.getChildren().add(canvas);

		root.setTop(pane);

		canvas.toFront();

		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);
		this.setScene(scene);
	}



}
