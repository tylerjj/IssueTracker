package application;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import application.IssueTable.Issue;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	private static final int WINDOW_WIDTH =1000;
	private static final int WINDOW_HEIGHT = 350;
	private static final String APP_TITLE = "IssueTracker";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();
		
		EditIssueBox issueBox = new EditIssueBox(0);
		EditProjectBox projectBox = new EditProjectBox();
		
		BorderPane root = new BorderPane();
		
		SearchBar searchbar = new SearchBar();
		VBox vbox = new VBox();
		vbox.getChildren().add(searchbar.toNode());
		
		
		Calendar calndr = Calendar.getInstance();
		calndr.set(2020,4,20,0,0,0);
		//https://www.geeksforgeeks.org/java-string-format-examples/
		String deadline = String.format("%d/%d/%d",calndr.get(Calendar.MONTH), calndr.get(Calendar.DAY_OF_MONTH),calndr.get(Calendar.YEAR));
		Date currentDate = new Date();
		
		IssueTable.Issue i1 = new IssueTable.Issue(Color.RED, "This is a mockup description. I'm going to make it extra long.",deadline, -1,"MOCKUP-ID", "Tyler Johnston", currentDate.toString());
		IssueTable.Issue i2 = new IssueTable.Issue(Color.BLUE, "Mockup Description 2",deadline,0, "MID2", "JohnstonTyler", currentDate.toGMTString());
		IssueTable.Issue i3 = new IssueTable.Issue(Color.GREEN, "In-Between",deadline,1, "MID3", "K", currentDate.toLocaleString());
		
		//Scene scene = new Scene(new Group(), 650,400);	
		
		IssueTable tableHolder = new IssueTable(750,400,new ArrayList<Issue>());
		
		//((Group)scene.getRoot()).getChildren().add(tableHolder);
		
		//tableHolder.prefWidthProperty().bind(root.widthProperty());
		//tableHolder.prefHeightProperty().bind(root.heightProperty());
		
		tableHolder.putIssueToRow(i1);
		tableHolder.putIssueToRow(i2);
		tableHolder.putIssueToRow(i3);
		
		vbox.getChildren().add(tableHolder);
		
		root.setRight(vbox);
		
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