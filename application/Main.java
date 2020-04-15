package application;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH =1000;
	private static final int WINDOW_HEIGHT = 350;
	private static final String APP_TITLE = "Hello World!";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();
		Text projectTitle = new Text("New Issue ID 354");
		projectTitle.setFont(Font.font("Comic Sans MS", 45));
		TextField titleField = new TextField();
		titleField.setPromptText("Title");
		TextArea descriptionField = new TextArea();
		descriptionField.setPromptText("Description");
		descriptionField.setPrefRowCount(5);
		TextField AField = new TextField();
		AField.setPromptText("Assignee's Name");
		Button assigneeButton = new Button("Add");
		HBox assigneeField = new HBox();
		assigneeField.getChildren().addAll(AField, assigneeButton);
		VBox module = new VBox();
		module.getChildren().addAll(titleField, descriptionField, assigneeField);
		
		ObservableList<String> projectItems = FXCollections.observableArrayList();
		projectItems.addAll("memes", "The GUI Project", "The Belko Experiment");
		ListView projectList = new ListView(projectItems);
		
		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane dashBoard = new BorderPane();
		BorderPane root = new BorderPane();
		dashBoard.setBottom(assigneeField);
		dashBoard.setCenter(module);
		dashBoard.setTop(projectTitle);
		root.setCenter(dashBoard);
		root.setLeft(projectList);
		
		// Add the vertical box to the center of the root pane
		
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}