// Rachael Colley 2014

package rach.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import rach.particle.HistoryBlobManager;
import rach.particle.Particle;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HistoryBlobCanvas extends Stage implements PropertyChangeListener {

	private final String STAGE_TITLE = "History Blob Stage";
	private final double SCENE_WIDTH = 800;
	private final double SCENE_HEIGHT = 600;

	private FlowPane root;
	private Scene scene;
	private Canvas canvas;

	private GraphicsContext graphicsContext;
	private static AnimationTimer timer = null;

	private int countDownTillNextHistoryBlob = 1;

	private int buffer = 0;

	private List<Particle> particles;
	private HistoryBlobManager hbm;



	public HistoryBlobCanvas() {
		hbm = new HistoryBlobManager((int)SCENE_WIDTH,(int)SCENE_HEIGHT,0);
		hbm.addChangeListener(this);
		initialiseMyStage();
		graphicsContext = canvas.getGraphicsContext2D();
		timer = new AnimationTimer() {
			long timeToStop = 0;
			@Override
			public void handle(long now) {
				//				if (timeToStop == 0) {
				//					timeToStop = now + 100000;
				//				}
				//				if (now > timeToStop) return;

				graphicsContext.setFill(Color.rgb(0, 0, 0, 0.2));
				graphicsContext.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);

				drawHistoryBlobs();

				if (countDownTillNextHistoryBlob == 0) {
					countDownTillNextHistoryBlob = 1;
					addHistoryBlob();
				}
				countDownTillNextHistoryBlob--;
			}
		};
		timer.start();
	}
	private void initialiseMyStage() {

		this.setResizable(false);
		this.setTitle(STAGE_TITLE);

		root = new FlowPane();
		root.setStyle("-fx-background-color: #336699;");
		root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

		canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				print(arg0.getX() + " " + arg0.getY());
			}
		});

		root.getChildren().add(canvas);
		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);
		this.setScene(scene);
	}

	private void addHistoryBlob() {
		hbm.addHistoryParticle();
	}

	private void drawHistoryBlobs() {
		particles = hbm.updateAllParticles();
		int count = 0;
		for (Particle p : particles) {
			
			graphicsContext.setFill(Color.BLACK);
			graphicsContext.fillRect(90, 40, 600, 40);
			
			graphicsContext.setFill(Color.hsb(p.getColor()[0],p.getColor()[1],p.getColor()[2],.5));
			graphicsContext.fillOval(p.getPosX(), p.getPosY(), p.getSize(), p.getSize());
			
			graphicsContext.setFont(new Font("Arial", 30));
			graphicsContext.fillText(this.hbm.getParticleURL(count, p), 110, 70);
			count ++;
		}
	}

	private void print(Object value) {
		System.out.println(value);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("endOfList") && evt.getNewValue().equals("true")) {		
			timer.stop();
		}
	}



}
