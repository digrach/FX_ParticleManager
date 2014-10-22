// Rachael Colley 2014

package rach.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import rach.particle.HistoryParticle;
import rach.particle.HistoryParticleManager;
import rach.particle.IParticle;
import rach.particle.Particle;
import rach.particle.UrlRanker;
import rach.particle.HistoryUrl;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HistoryParticleCanvas extends Stage implements PropertyChangeListener {

	private final String STAGE_TITLE = "History Particle Stage";
	private final double SCENE_WIDTH = 800;
	private final double SCENE_HEIGHT = 600;
	private final int BUFFER = 0;

	private BorderPane root;
	private Scene scene;
	private Canvas canvas0;
	private Canvas canvas1;
	private Pane pane;

	private GraphicsContext graphicsContext;
	private static AnimationTimer timer = null;

	private int countDownTillNextHistoryParticle = 1;

	private List<IParticle> particles;
	private HistoryParticleManager historyParticleManager;

	public HistoryParticleCanvas() {
		historyParticleManager = new HistoryParticleManager((int)SCENE_WIDTH,(int)SCENE_HEIGHT,20000);
		historyParticleManager.addChangeListener(this);
		initialiseMyStage();
		
		graphicsContext = canvas1.getGraphicsContext2D();

		timer = new AnimationTimer() {
			long timeToStop = 0;
			@Override
			public void handle(long now) {
				//				if (timeToStop == 0) {
				//					timeToStop = now + 100000;
				//				}
				//				if (now > timeToStop) return;
				drawHistoryParticles();
				if (countDownTillNextHistoryParticle == 0) {
					countDownTillNextHistoryParticle = 1;
					addHistoryParticle();
				}
				countDownTillNextHistoryParticle--;
			}
		};
		timer.start();
	}
	private void initialiseMyStage() {
		this.setResizable(false);
		this.setTitle(STAGE_TITLE);

		root = new BorderPane();
		//root.setStyle("-fx-background-color: #336699;");
		root.setStyle("-fx-background-color: #000000;");
		//root.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);

		canvas1 = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
		//canvas1.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		pane = new Pane();
		pane.getChildren().add(canvas1);

		root.setTop(pane);

		canvas1.toFront();

		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);
		this.setScene(scene);
	}
	private void addHistoryParticle() {
		historyParticleManager.addHistoryParticle();
	}
	private void drawHistoryParticles() {		
		graphicsContext.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
		IParticle ip = null;
		particles = historyParticleManager.updateAllParticles();
		for (IParticle p : particles) {
			ip = p;
			Color c = Color.hsb(p.getColor()[0],p.getColor()[1],p.getColor()[2],.5);
			graphicsContext.setFill(c);
			graphicsContext.fillOval(p.getPosX(), p.getPosY(), p.getSize(), p.getSize());
		}
		if (particles.size() > 0) {
			drawParticleURL(ip);
			drawParticleCount();
			drawTopList();
		}
	}
	private void drawTopList() {
		List<HistoryUrl> topList = historyParticleManager.getUrlPosition();
		double drawx = 110;
		double drawy = 500;
		for (HistoryUrl s : topList) {
			float[] c = s.getColor();
			graphicsContext.setFill(Color.hsb(c[0],c[1],c[2]));
			graphicsContext.setFont(new Font("Arial",30));
			graphicsContext.fillText(s.getCount() + " " + s.getUrl(), drawx, drawy);
			drawy -= 30;
		}
	}
	private void drawParticleCount() {
		String currentCount = Integer.toString(historyParticleManager.getCurrentParticleCount());
		graphicsContext.fillText(currentCount, 100, 50);
	}
	private void drawParticleURL(IParticle ip) {
			graphicsContext.setFill(Color.WHITE);
			graphicsContext.setFont(new Font("Arial",30));
			String currentParticleDate = ip.getStrDate();
			if (currentParticleDate == null) {
				currentParticleDate = "NULL";
			}
			graphicsContext.fillText(currentParticleDate, 100, 100);
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










//public class HistoryBlobCanvas extends Stage implements PropertyChangeListener {
//
//	private final String STAGE_TITLE = "History Blob Stage";
//	private final double SCENE_WIDTH = 800;
//	private final double SCENE_HEIGHT = 600;
//
//	private BorderPane root;
//	private Scene scene;
//	private Canvas canvas0;
//	private Canvas canvas1;
//	
//	private Pane pane;
//
//
//	private GraphicsContext gc0;
//	private GraphicsContext gc1;
//
//	private static AnimationTimer timer = null;
//
//	private int countDownTillNextHistoryBlob = 1;
//
//	private int buffer = 0;
//
//	private List<IParticle> particles;
//	private HistoryBlobManager hbm;
//
//	private String lastUrl = "";
//
//	private Map<String,Integer> urlMap;
//
//	//private UrlRanker urlRanker = new UrlRanker();
//
//
//
//	public HistoryBlobCanvas() {
//		hbm = new HistoryBlobManager((int)SCENE_WIDTH,(int)SCENE_HEIGHT,0);
//		hbm.addChangeListener(this);
//		initialiseMyStage();
//		gc0 = canvas0.getGraphicsContext2D();
//		gc1 = canvas0.getGraphicsContext2D();
//
//		timer = new AnimationTimer() {
//			long timeToStop = 0;
//			@Override
//			public void handle(long now) {
//				//				if (timeToStop == 0) {
//				//					timeToStop = now + 100000;
//				//				}
//				//				if (now > timeToStop) return;
//
//				//gc0.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//				//gc1.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//				
//				//gc0.setFill(Color.rgb(0, 0, 0, 0.2));
//				//gc0.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//				
//				//gc1.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//				//gc1.restore();
////				gc1.setFill(Color.rgb(0, 0, 0, 0.2));
////				gc1.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//
//				drawHistoryBlobs();
//
//				if (countDownTillNextHistoryBlob == 0) {
//					countDownTillNextHistoryBlob = 1;
//					addHistoryBlob();
//				}
//				countDownTillNextHistoryBlob--;
//				
//				//System.out.println("tick");
//			}
//			
//		};
//		timer.start();
//	}
//	private void initialiseMyStage() {
//
//		this.setResizable(false);
//		this.setTitle(STAGE_TITLE);
//
//		root = new BorderPane();
//		//root.setStyle("-fx-background-color: #336699;");
//		root.setStyle("-fx-background-color: #000000;");
//		//root.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
//		root.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
//
//		canvas0 = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
//		canvas0.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
//		canvas1 = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);
//		canvas1.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
//
//		
//		canvas0.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent arg0) {
//				print(arg0.getX() + " " + arg0.getY());
//			}
//		});
//
//		//root.getChildren().add(canvas);
//		
//		pane = new Pane();
//		pane.getChildren().add(canvas0);
//		pane.getChildren().add(canvas1);
//		
//		root.setTop(pane);
//		
//		canvas1.toFront();
//		
//		scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PINK);
//		//scene.setFill(null);
//		this.setScene(scene);
//	}
//
//	private void addHistoryBlob() {
//		hbm.addHistoryParticle();
//	}
//
//	private void drawHistoryBlobs() {
//		urlMap = new HashMap<String,Integer>();
//
//		particles = hbm.updateAllParticles();
//		for (IParticle p : particles) {
//
//			Color c = Color.hsb(p.getColor()[0],p.getColor()[1],p.getColor()[2],.5);
//
//			String currentUrl = p.getUrl();
//			UrlRender u = hbm.getUrlCount(currentUrl);
//
////			gc1.setFill(Color.rgb(0, 0, 0, 0.2));
////			gc1.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
////			gc1.setFill(Color.rgb(255, 255, 255, 1));
////			gc1.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//			//gc1.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//			//gc1.restore();
//			List<String> topList = hbm.getUrlPosition(u);
//			double drawx = 110;
//			double drawy = 70;
//			for (String s : topList) {
//				gc1.setFill(c);
//				gc1.setFont(new Font("Arial",30));
//				gc1.fillText(s, drawx, drawy);
//				drawy += 20;
//				//gc1.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
////				gc1.setFill(Color.rgb(0, 0, 0, 0.2));
////				gc1.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//				
//			}
//			
//			gc0.setFill(Color.rgb(0, 0, 0, 0.2));
//			gc0.fillRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//			
//			gc0.setFill(c);
//			gc0.fillOval(p.getPosX(), p.getPosY(), p.getSize(), p.getSize());
//			
//			
//			//gc1.restore();
//			//gc1.clearRect(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
//
//
//			//			int dispCount = 0;
//			//			if (u != null) {
//			//				dispCount = u.getCount();
//			//			}
//			//			
//			//			String displayUrl = dispCount + " " + currentUrl;
//			//			graphicsContext.setFill(c);
//			//			graphicsContext.setFont(new Font("Arial",30));
//			//			graphicsContext.fillText(displayUrl, 110, 70);
//
//		}
//	}
//
//	//	public void testSort(String s, int i) {
//	//		List<String> l = new ArrayList<String>();
//	//		
//	//	}
//	//	private void checkUrlMap(String s) {
//	//		if (!urlMap.containsKey(s)) {
//	//			urlMap.put(s, 1);
//	//		} else {
//	//			int currentCount = urlMap.get(s);
//	//			urlMap.put(s, currentCount);
//	//		}
//	//	}
//	private void print(Object value) {
//		System.out.println(value);
//	}
//	@Override
//	public void propertyChange(PropertyChangeEvent evt) {
//		if (evt.getPropertyName().equals("endOfList") && evt.getNewValue().equals("true")) {		
//			timer.stop();
//		}
//	}
//
//
//
//}