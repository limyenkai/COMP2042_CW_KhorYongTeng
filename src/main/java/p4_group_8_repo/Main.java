package p4_group_8_repo;

import java.io.File;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		HighScores highScores = new HighScores();
		
		StartSceneController startSceneController = new StartSceneController();
		EndSceneController endSceneController = new EndSceneController();
		HSSceneController hsSceneController= new HSSceneController(highScores);
		GameController gameController = new GameController(primaryStage, startSceneController, endSceneController, hsSceneController);
		
		
		startSceneController.setGameController(gameController);
		endSceneController.setGameController(gameController);
		endSceneController.setHighScores(highScores);
	}
	
	
}
