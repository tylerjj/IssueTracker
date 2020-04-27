package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import backend.Issue;
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

		//IssueBox issueBox = new IssueBox(0);
		//EditProjectBox projectBox = new EditProjectBox();

		BorderPane root = new BorderPane();

		IssueOptionBar searchbar = new IssueOptionBar();
		searchbar.setSpacing(10);
		searchbar.setPrefSize(1200, 50);
		searchbar.setPadding(30, 15, 30, 15);
		searchbar.setNewIssueButtonStyle(14, "arial", "#CC88FF");

		VBox vbox = new VBox();

		String projectTitle = new String("Mockup Project");
		String projectDescription = new String(
				"This is a mockup project description. \nI wonder what the textarea will do if I just keep going and going like the energizer bunny.");
		Boolean projectOpen = true;
		Date projectDeadline = new Date();
		ProjectDataHub projectDataHub = new ProjectDataHub(projectTitle,
				projectDescription, projectOpen, projectDeadline);

		vbox.getChildren().add(projectDataHub);

		vbox.getChildren().add(searchbar.toNode());

		Calendar calndr = Calendar.getInstance();
		calndr.set(2020, 4, 20, 0, 0, 0);
		// https://www.geeksforgeeks.org/java-string-format-examples/
		String deadline = String.format("%d/%d/%d", calndr.get(Calendar.MONTH),
				calndr.get(Calendar.DAY_OF_MONTH), calndr.get(Calendar.YEAR));
		Date currentDate = new Date();
    
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
		
		IssueTable tableHolder = new IssueTable(vbox.getPrefWidth(), vbox.getPrefWidth(), new ArrayList<Issue>());
		
		// ((Group)scene.getRoot()).getChildren().add(tableHolder);

		// tableHolder.prefWidthProperty().bind(root.widthProperty());
		
		tableHolder.putIssueToRow(i1); tableHolder.putIssueToRow(i2);
		tableHolder.putIssueToRow(i3);
		  
		vbox.getChildren().add(tableHolder.getTable());
		  
		projectDataHub.setPrefWidth(tableHolder.getTable().getPrefWidth());
		projectDataHub.setPrefHeight(tableHolder.getTable().getPrefHeight());

		vbox.setPadding(new Insets(0, 20, 0, 20));
		root.setCenter(vbox);

		Sidebar sidebar = new Sidebar();
		root.setLeft(sidebar.toNode());
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
		//Testing for IssueHandler
		ArrayList<Issue> issues = new ArrayList<Issue>();
		issues.add(i1); issues.add(i2); issues.add(i3);
		System.out.println(issues.toString());
		Stage testStage = new Stage();
		IssueHandler issueSection = new IssueHandler(issues, testStage);
		Scene testScene = new Scene(issueSection.container);
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