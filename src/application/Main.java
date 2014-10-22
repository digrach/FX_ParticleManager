package application;
	
import rach.canvas.HistoryParticleCanvas;
import rach.canvas.ParticleCanvas;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private BorderPane root;
	private Scene scene;
	private VBox vb;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = new BorderPane();
			scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			addComponentsToStage();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	public void addComponentsToStage() {
		vb = new VBox();
		vb.getChildren().add(addParticleCanvasLauncher());
		vb.getChildren().add(addHistoryBlobCanvasLauncher());
		root.setTop(vb);
	}
	private Button addParticleCanvasLauncher() {
		Button button = new Button();
		button.setText("Particle Canvas");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final ParticleCanvas s = new ParticleCanvas();
				s.show();
			}
		});
		return button;
	}
	private Button addHistoryBlobCanvasLauncher() {
		Button button = new Button();
		button.setText("History Blob Canvas");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final HistoryParticleCanvas hbc = new HistoryParticleCanvas();
				hbc.show();
			}
		});
		return button;
	}
}
