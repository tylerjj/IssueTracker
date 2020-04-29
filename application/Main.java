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

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 350;
	private static final String APP_TITLE = "IssueTracker";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		Calendar calndr = Calendar.getInstance();
		calndr.set(2020, 4, 20, 0, 0, 0);
		// https://www.geeksforgeeks.org/java-string-format-examples/
		String deadline = String.format("%d/%d/%d", calndr.get(Calendar.MONTH),
				calndr.get(Calendar.DAY_OF_MONTH), calndr.get(Calendar.YEAR));
		Date currentDate = new Date();
//    
		ArrayList<String> assignees1 = new ArrayList<String>();
		assignees1.add("Tyler Johnston");
		assignees1.add("Mingrui Leng");
		Issue i1 = new Issue(Issue.Priority.MEDIUM, "Name of Issue1", "This is a mockup issue description.  I'm going to make it extra long.", new Date(), Issue.Status.TODO, "i1", assignees1, new Date(), new Date(), null);

		ArrayList<String> assignees2 = new ArrayList<String>();
		assignees2.add("Martin Diges");
		Issue i2 = new Issue(Issue.Priority.HIGH, "Name of Issue2", "Mockup Description 2", new Date(deadline),Issue.Status.IN_PROGRESS, "i2", assignees2, new Date(), new Date(), null);
		
		ArrayList<String> assignees3 = new ArrayList<String>();
		assignees3.add("Tyler Johnston");
		assignees3.add("Alec Lowry");
		assignees3.add("James Charapata");
		assignees3.add("Mingrui Leng");
		assignees3.add("Martin Diges");
		Issue i3 = new Issue(Issue.Priority.LOW, "Name of Issue3", "Mockup Desc 3", new Date(deadline), Issue.Status.COMPLETE, "i3", assignees3, new Date(), new Date(), new Date());
		
		//Testing for IssueHandler
		ArrayList<Issue> issues = new ArrayList<Issue>();
		issues.add(i1); issues.add(i2); issues.add(i3);
		System.out.println(issues.toString());
		Stage testStage = new Stage();
		IssueHandler issueSection = new IssueHandler(issues, testStage);
				
		//Testing for the ProjectHandler
		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Issue> issues2 = new ArrayList<Issue>();
		issues2.add(i3); issues2.add(i1); issues2.add(i2);
		Project firstProject = new Project(issues, "First Project", "For the memes", new Date(), new Date(), new Date(), new Date(), Project.Status.OPEN);
		Project secondProject = new Project(issues2, "Second Project", "For the memes", new Date(), new Date(), new Date(), new Date(), Project.Status.OPEN);
		
		
		projects.add(firstProject);
		projects.add(secondProject);
		
		ProjectHandler projectHandler = new ProjectHandler(projects, testStage);
		
		BorderPane dashBoard = new BorderPane();
		dashBoard.setCenter(projectHandler.projectDataView);
		dashBoard.setLeft(projectHandler.sidebar.toNode());
		
		Scene testScene = new Scene(dashBoard);
		testStage.setScene(testScene);
		testStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}