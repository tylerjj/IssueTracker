package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import backend.Issue;
import backend.Project;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
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
	private ArrayList<Project> projects;
	private Stage stage;
	private IssueHandler issueView;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 350;
	private static final String APP_TITLE = "IssueTracker";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		// Default init vals
		this.projects = new ArrayList<Project>();
		this.stage = new Stage();
		this.issueView = new IssueHandler(new ArrayList<Issue>(), this.stage);

		ProjectHandler projectHandler = new ProjectHandler(projects, stage, issueView);

		BorderPane dashBoard = new BorderPane();
		dashBoard.setCenter(issueView.container);
		dashBoard.setLeft(projectHandler.sidebar.toNode());

		Scene testScene = new Scene(dashBoard);
		stage.setScene(testScene);
		stage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
