package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	private static ArrayList<Project> projects;
	private static Stage stage;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 350;
	private static final String APP_TITLE = "IssueTracker";
	
	private static final String LOCAL_DB = "data/localdb.json";
	private static final String DEMO_DB = "data/demodb.json";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		// Default init vals
		Main.projects = new ArrayList<Project>();
		Main.stage = new Stage();
		
//		loadLocalDB();

		ProjectHandler projectHandler = new ProjectHandler(projects, stage);

		BorderPane dashBoard = new BorderPane();
		dashBoard.setCenter(projectHandler.projectDataView);
		dashBoard.setLeft(projectHandler.sidebar.toNode());

		Scene testScene = new Scene(dashBoard);
		stage.setScene(testScene);
		stage.show();
	}
	
	/**
	 * Wipes local data and starts new, fresh session
	 */
	public static void newLocalSession() {
		System.out.println("Created new local session");
	}
	
	/**
	 * Reads sample data from ./data/demodb.json for 
	 * presentation purposes
	 */
	public static void loadDemoDB() {
		System.out.println("Loading demodb.json");
	}
	
	/**
	 * Reads data from ./data/localdb.json to start a session
	 */
	public static void loadLocalDB() {
		System.out.println("Loading localdb.json");

		ArrayList<Issue> issueList = new ArrayList<Issue>();
		Project p1 = new Project();
		p1.setName("Project 01");
		p1.setDescription("KDFKJDSFKJDSF");
		p1.setOpenStatus(Project.Status.OPEN);
		p1.setDeadline(null);
		p1.setDateCreated(null);
		p1.setDateLastAccessed(null);
		p1.setDateClosed(null);
		
		projects.add(p1);

	}
	
	/**
	 * Cleanup method to write the contents of
	 * Main.projects to localdb.json
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void saveLocalSession() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Saving to localdb.json");
		JSONObject jo = new JSONObject();
		for (Project p : projects) {
			Map m = new LinkedHashMap();
			m.put("name", p.getName());
			m.put("description", p.getDescription());
			m.put("deadline", p.getDeadline().toString()); // TODO
			m.put("dateCreated", p.getDateCreated().toString());
			m.put("dateLastAccessed", p.getDateLastAccessed().toString());
//			m.put("dateClosed", p.getDateClosed().toString()); // Throws np
			m.put("status", p.getOpenStatus().toString());
			Map issueList = new LinkedHashMap();
			for (Issue i : p.getIssueList()) {
				Map issueMap = new LinkedHashMap();
				issueMap.put("name", i.getName());
				issueMap.put("description", i.getDescription());
				issueMap.put("priority", i.getPriority().toString());
				issueMap.put("ID", i.getID());
				issueMap.put("status", i.getStatus().toString());
				issueMap.put("deadline", i.getDeadline().toString());
				issueMap.put("dateCreated", i.getDateCreated().toString());
				issueMap.put("dateLastUpdated", i.getDateLastUpdated().toString());
//				issueMap.put("dateClosed", i.getDateClosed().toString()); // np
				JSONArray assignees = new JSONArray();
				for (String assignee: i.getAssignees()) {
					assignees.add(assignee);
				}
				issueMap.put("assignees", assignees);
				issueList.put(i.getName(), issueMap);
			}
			m.put("issueList", issueList);
			jo.put(p.getName(), m);
		}
		
		System.out.println(jo.toJSONString());
		PrintWriter pw = new PrintWriter(LOCAL_DB);
		pw.write(jo.toJSONString());
		pw.flush();
		pw.close();
	}

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		launch(args);
		saveLocalSession();
	}
}
