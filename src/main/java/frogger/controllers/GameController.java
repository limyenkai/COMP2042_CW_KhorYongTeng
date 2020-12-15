package frogger.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import frogger.models.MusicPlayer;
import frogger.models.levels.Level;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController {

	private static GameController gameController_instance = null;
	
	private AnimationTimer timer;
	private int currentScene;
	private int totalScore = 0;
	private int savedScore = 0;
	private MusicPlayer musicPlayer = new MusicPlayer();
	private SceneController sceneController;
	private EndSceneController endSceneController;
	private HSSceneController hsSceneController;
	private Scene scene;
	
	private GameController() throws IOException {
		
		currentScene = 0;
		
	}
	
	public static GameController GameController() throws IOException {
		
		if (gameController_instance == null) {
			
			gameController_instance = new GameController();
			
		}
		
		return gameController_instance;
		
	}
	
	public void setSceneControllers(Scene scene, SceneController sceneController, EndSceneController endSceneController, HSSceneController hsSceneController) {
		
		this.scene = scene;
		this.sceneController = sceneController;
		this.endSceneController = endSceneController;
		this.hsSceneController = hsSceneController;
		
	}
	
	public void playMusic() {
    	
    	musicPlayer.play();
    	
    }
	
	private void nextLevel() {
		
		nextScene();
		
		if (scene.getRoot() instanceof Level) {
			
			((Level)scene.getRoot()).setScore(savedScore);
			
		}
		else {
			
			stop();
			
		}
		
	}
	
	protected void nextScene() {
		
		sceneController.changeScene(++currentScene);
		
	}
	
	protected void previousScene() {
		
		sceneController.changeScene(--currentScene);
		
	}
	
	protected void setHSList() {
		
		hsSceneController.setScoreText();
		
	}
	
	private void createTimer() {
		
		timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	
            	if (scene.getRoot() instanceof Level) {
            		
            		if(((Level)scene.getRoot()).changeScore()) {
            			
            			totalScore = savedScore + ((Level)scene.getRoot()).getPoints();
            			((Level)scene.getRoot()).setScore(totalScore);
            			
            		}
            		
            		if(((Level)scene.getRoot()).getStop()) {
            			
            			savedScore = totalScore;
            			nextLevel();
            			
            		}
            		
            	}
            	else {
            		
            		stop();
            		
            	}
            	
            }
        };
		
    }
	
	protected void start() {
	
		currentScene = 2;
		sceneController.activate(currentScene);
    	createTimer();
        timer.start();
        
    }

    private void stop() {
    	
    	endSceneController.setEndScore(savedScore);
        timer.stop();
        
    }
    
    protected void restart() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	
    	savedScore = 0;
    	totalScore = 0;
    	sceneController.resetScenes();
    	start();
    	
    }
    
}
