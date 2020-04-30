package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import backend.Project.Status;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
	
	private static final String DATA_DIR = "data/";
	private static final String LOCAL_DB = DATA_DIR + "localdb.json";
	private static final String DEMO_DB = DATA_DIR + "demodb.json";
	
	public static Boolean DEMO = false;
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		// Default init vals
		Main.projects = new ArrayList<Project>();
		Main.stage = new Stage();

		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(Main.stage);
		
		Label instructionLabel = new Label(
				new String("Hello Instructor/TA, please choose from the following:\n"
				+ "Open Local: On first run, the IssueTracker will be fresh without any\n"
				+ "pre-existing data. Any changes you make will be stored and used to\n"
				+ "initialize Local on future runs.\n"
				+ "\n"
				+ "Open Demo: On first run, the IssueTracker will be pre-loaded for the \n"
				+ "purposes of demonstration. Any changes you make will be stored and\n"
				+ "used to initialize Demo on future runs.\n"));
		instructionLabel.setFont(new Font("arial", 15));
		instructionLabel.setPadding(new Insets(10,10,10,10));
		
		Button loadDemoButton = new Button("Load Demo");
		loadDemoButton.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED, CornerRadii.EMPTY, Insets.EMPTY)));
		loadDemoButton.setOnAction(e->{
			try {
				loadDemoButtonEvent(dialog);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		Button loadLocalButton = new Button("Load Local");
		loadLocalButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		loadLocalButton.setOnAction(e->{
			try {
				loadLocalButtonEvent(dialog);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		
		HBox buttonBox = new HBox(loadDemoButton, loadLocalButton);
		VBox dialogFrame = new VBox(instructionLabel, buttonBox);
		Scene dialogScene = new Scene(dialogFrame);
		dialog.setScene(dialogScene);
		dialog.show();
	}
	
	public void loadDemoButtonEvent(Stage dialog) throws Exception {
		DEMO = true;
		dialog.close();
		setupIssueTracker();
	}
	
	public void loadLocalButtonEvent(Stage dialog) throws Exception {
		DEMO = false;
		dialog.close();
		setupIssueTracker();
	}
	
	public void setupIssueTracker() throws Exception {
		if (!DEMO) {
			File f = new File(LOCAL_DB);
			if(f.exists() && !f.isDirectory()) { 
				BufferedReader br = new BufferedReader(new FileReader(LOCAL_DB));     
				if (br.readLine() != null) {
					loadDB(LOCAL_DB);
				}			
			}
		} else {
			File f = new File(DEMO_DB);
			if(f.exists() && !f.isDirectory()) { 
				BufferedReader br = new BufferedReader(new FileReader(DEMO_DB));     
				if (br.readLine() != null) {
					loadDB(DEMO_DB);
				}			
			}
		}
		
		
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
	 * Reads data for new session.
	 * 
	 * Pass DEMO_DB for sample data
	 * Pass LOCAL_DB for local persistence
	 * 
	 * presentation purposes
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws java.text.ParseException 
	 */
	public static void loadDB(String DB) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
		System.out.println("Loading " + DB);
		JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(DB));
		Integer p_index = 0;
		while (jo.containsKey(p_index.toString())) {
			Map p_map = (Map) jo.get(p_index.toString());
			
			System.out.println("    Building project: " + p_map.get("name"));
			Project p = new Project();
			p.setName(p_map.get("name").toString());
			p.setDescription(p_map.get("description").toString());
			p.setDeadline(DATE_FORMAT.parse(p_map.get("deadline").toString()));
			p.setDateCreated(DATE_FORMAT.parse(p_map.get("dateCreated").toString()));
			p.setDateLastAccessed(DATE_FORMAT.parse(p_map.get("dateLastAccessed").toString()));
			if (p_map.get("dateClosed").toString().contentEquals("")) p.setDateClosed(new Date());	// FIXME - Probably wrong default val
			else p.setDateClosed(new Date());
			if (p_map.get("status").equals("OPEN")) p.setOpenStatus(Project.Status.OPEN);
			else p.setOpenStatus(Project.Status.CLOSED);
			
			ArrayList<Issue> issueList = new ArrayList<Issue>();
			Integer i_index = 0;
			while (((Map) p_map.get("issueList")).containsKey(i_index.toString())) {
				Map i_map = (Map) ((Map) p_map.get("issueList")).get(i_index.toString());

				Issue issue = new Issue();
				issue.setName(i_map.get("name").toString());
				issue.setDescription(i_map.get("description").toString());
				String priority = i_map.get("priority").toString();
				if (priority.contentEquals("LOW")) issue.setPriority(Issue.Priority.LOW);
				else if (priority.contentEquals("MEDIUM")) issue.setPriority(Issue.Priority.MEDIUM);
				else issue.setPriority(Issue.Priority.HIGH);
				issue.setID(i_map.get("ID").toString());
				String status = i_map.get("status").toString();
				if (status.contentEquals("TODO")) issue.setStatus(Issue.Status.TODO);
				else if (status.contentEquals("IN_PROGRESS")) issue.setStatus(Issue.Status.IN_PROGRESS);
				else issue.setStatus(Issue.Status.COMPLETE);
				issue.setDeadline(DATE_FORMAT.parse(i_map.get("deadline").toString()));
				issue.setDateCreated(DATE_FORMAT.parse(i_map.get("dateCreated").toString()));
				issue.setDateLastUpdated(DATE_FORMAT.parse(i_map.get("dateLastUpdated").toString()));
				if (i_map.get("dateClosed").toString().contentEquals("")) p.setDateClosed(null);
				else issue.setDateClosed(DATE_FORMAT.parse(i_map.get("dateClosed").toString()));
				ArrayList<String> assignees = new ArrayList<String>();
				JSONArray jsArray= (JSONArray) i_map.get("assignees");
				System.out.println(jsArray);
				for (Object a : jsArray) {
					assignees.add(a.toString());
				}
				issue.setAssignees(assignees);

				issueList.add(issue);
				i_index++;
			}
			p.setIssueList(issueList);

			projects.add(p);
			p_index++;
		}
		System.out.println("Built Projects:");
		for (Project p: projects) {
			System.out.println(p.getName());
		}
		
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
		JSONObject jo = new JSONObject();
		int p_index = 0;
		for (Project p : projects) {
			Map m = new LinkedHashMap();
			m.put("name", p.getName());
			m.put("description", p.getDescription());
			m.put("deadline", DATE_FORMAT.format(p.getDeadline()));
			m.put("dateCreated", DATE_FORMAT.format(p.getDateCreated()));
			m.put("dateLastAccessed", DATE_FORMAT.format(p.getDateLastAccessed()));
			if (p.getDateClosed() == null) m.put("dateClosed", "");
			else m.put("dateClosed", DATE_FORMAT.format(p.getDateClosed()));
			m.put("status", p.getOpenStatus().toString());
			Map issueList = new LinkedHashMap();
			int i_index = 0;
			for (Issue i : p.getIssueList()) {
				Map issueMap = new LinkedHashMap();
				issueMap.put("name", i.getName());
				issueMap.put("description", i.getDescription());
				issueMap.put("priority", i.getPriority().toString());
				issueMap.put("ID", i.getID());
				issueMap.put("status", i.getStatus().toString());
				issueMap.put("deadline", DATE_FORMAT.format(i.getDeadline()));
				issueMap.put("dateCreated", DATE_FORMAT.format(i.getDateCreated()));
				issueMap.put("dateLastUpdated", DATE_FORMAT.format(i.getDateLastUpdated()));
				if (i.getDateClosed() != null) issueMap.put("dateClosed", DATE_FORMAT.format(i.getDateClosed()));
				else issueMap.put("dateClosed", "");
				JSONArray assignees = new JSONArray();
				for (String assignee: i.getAssignees()) {
					assignees.add(assignee);
				}
				issueMap.put("assignees", assignees);
				issueList.put(i_index, issueMap);
				i_index++;
			}
			m.put("issueList", issueList);
			jo.put(p_index, m);
			p_index++;
		}
		String db;
		if (DEMO) {
			db = DEMO_DB;
		} else {
			db = LOCAL_DB;
		}
		System.out.println("Saving to "+db);
		PrintWriter pw = new PrintWriter(db);
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
