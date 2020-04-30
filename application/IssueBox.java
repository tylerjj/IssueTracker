package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import backend.Issue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Bugs: 
 * 
 * Creating a New Issue with the same ID as a pre-existing issue
 * overwrites the pre-existing issue in the table and in the issue list. 
 * 
 * Add Assignees needs to be implemented. 
 * 
 * Remove Assignees needs to be implemented.
 * 
 * At the moment, all fields are treated as required by the SaveHandler.
 * This needs to be refactored depending on the particular field.
 * 
 * The aesthetics of the IssueBox components
 * could use some tlc.
 * 
 */
public class IssueBox {
	/**
	 * The issue object for this IssueBox.
	 * 
	 * If IssueBox is being used for a New Issue, 
	 * 		this issue will remain null until all of its values can be set
	 * 		from IssueBox field input.
	 * 
	 * Otherwise, if IssueBox is being used for an Edit Issue, 
	 * 		then the values of this object will be used to populate the fields
	 * 		of this IssueBox.
	 */
	private Issue issue;
	
	/**
	 * The title of this IssueBox.
	 * 
	 * Either "New Issue" or "Edit Issue"
	 */
	private Text issueTitle;
	
	/**
	 * REQUIRED INPUT FROM USER
	 * Creates a drop-down menu for the user to select this issue's
	 * priority.
	 * 
	 * 		Priority options include: LOW, MEDIUM, and HIGH
	 */
	private ComboBox<Issue.Priority> priorityComboBox;
	
	/**
	 * REQUIRED INPUT FROM USER
	 * This could be renamed to nameField for consistency.
	 * This field allows the user to input the name for this issue.
	 */
	private TextField titleField;
	
	/**
	 * NOT REQUIRED
	 * This field allows the user to input the description for this
	 * issue.
	 */
	private TextArea descriptionField;
	
	/**
	 * REQUIRED INPUT FROM USER
	 * Creates a drop-down menu for the user to select this issue's
	 * status.
	 * 
	 * 		Status options include: TODO, IN_PROGRESS, and COMPLETE
	 */
	private ComboBox<Issue.Status> statusComboBox;
	
	/**
	 * REQUIRED INPUT FROM USER
	 * This field allows the user to input the unique ID for this 
	 * issue. 
	 * 		
	 * EDGE CASE: User inputs an ID already belonging to another
	 * issue.  
	 */
	private TextField idField;
	
	/**
	 * NOT REQUIRED
	 * This field allows the user to input the names of assignees
	 * to which this issue will be assigned. 
	 * 
	 * TODO: We didn't create a way to remove assignees. 
	 */
	private TextField assigneeField;
	
	/**
	 * This is the object used to display the assignees that the
	 * user has already added.
	 */
	private Label assigneesLabel;
	
	/**
	 * This is the button utilized to trigger the addition of the 
	 * text value from assigneeField into assigneesLabel.
	 * 
	 * TODO If there's time, add some functionality to handle 
	 * duplicates.
	 */
	private Button addAssigneeButton;
	
	/**
	 * This is the horizontal box that will contain all of the 
	 * assignee-data related GUI components.
	 */
	private HBox assigneeBox;
	
	/**
	 * REQUIRED INPUT FROM USER
	 * This is the utility used to get deadline date from the
	 * user.
	 * 
	 * It stores the date as a LocalDate, which requires some
	 * conversion from our get/set deadline functions in the
	 * Issue Object.
	 */
	private DatePicker deadlineDatePicker;
	
	/**
	 * On press, If all the required fields have some valid data 
	 * inside them, our SaveHandler class will use that data to 
	 * populate/update our Issue object and close the IssueBox,
	 * returning to the dialog's previous stage. 
	 * 
	 * If all the required fields have not been filled in, then
	 * a warning will display, letting the user know they have
	 * not filled in all the required fields. 
	 */
	private Button saveButton;
	
	/**
	 * A confirmation alert will display asking the user 
	 * if they want to cancel all changes to the Issue object. 
	 * 
	 * If so, then they are returned to the dialog's previous
	 * stage.
	 * Otherwise, the IssueBox window remains open.
	 */
	private Button cancelButton;
	
	/**
	 * This is a layout  manager for the save/cancel buttons.
	 */
	private HBox buttonBox;
	
	/**
	 * TODO: This is a remnant from earlier code. If we just 
	 * used buttonBox as the container for the buttons and
	 * deleted bottomBox, nothing would change.
	 */
	private HBox bottomBox;
	
	/**
	 * Outermost layout manager for all of our components.
	 * Contains only the frame.
	 */
	private VBox body;
	
	/**
	 * The layout manager that nearly all components will 
	 * be added to.
	 */
	private BorderPane frame;

	/**
	 * This stage is a modal dialog. It's previous stage
	 * is specified in IssueHandler.java
	 */
	private Stage dialogStage;
	
	/**
	 * This is just the scene that will be created and 
	 * plugged into the dialogStage.
	 */
	private Scene theScene;

	/**
	 * 
	 * SaveHandler - The SaveHandler is an EventHandler that
	 * manages a saveButton clickEvent. The SaveHandler event 
	 * handles storing IssueBox field information into
	 * an Issue object upon saveButton being clicked.
	 * 
	 * @author james charapata (initial creation) 
	 * tyler johnston (final implementation) 
	 *
	 */
	class SaveHandler implements EventHandler<ActionEvent> {
		private Button saveButton;

		SaveHandler(Button saveButton) {
			this.saveButton = saveButton;
		}

		@Override
		public void handle(ActionEvent arg0) {

			boolean missingFields = false;
			Issue.Priority priority = priorityComboBox.getValue();
			if (priority == null) {
				missingFields = true;
			}
			String name = titleField.getText();
			if (name == null || name.isBlank()) {
				missingFields = true;
			}
			String description = descriptionField.getText();
			if (description == null) {
				description = new String();
			}

			Date deadline = new Date();
			if (deadlineDatePicker.getValue() == null) {
				missingFields = true;
			} else {
				// https://mkyong.com/java8/java-8-convert-date-to-localdate-and-localdatetime/
				deadline = Date.from(deadlineDatePicker.getValue()
						.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}

			Issue.Status status = statusComboBox.getValue();
			if (status == null) {
				missingFields = true;
			}

			String id = idField.getText();
			if (id == null || id.isBlank()) {
				missingFields = true;
			}

			String assigneesValue = assigneesLabel.getText();
			// https://beginnersbook.com/2015/05/java-string-to-arraylist-conversion/
			ArrayList<String> assignees = new ArrayList<String>();
			if (assigneesValue != null && !assigneesValue.isBlank()) {
				String[] str = assigneesValue.split("\n");
				for (int i = 0; i < str.length; i++) {
					if (i > 0) {
						assignees.add(str[i]);
					}
				}
			}

			if (missingFields) {
				Alert alert = new Alert(AlertType.WARNING,
						"Required Fields Are Missing");
				alert.showAndWait().filter(r -> r == ButtonType.OK);
				return;
			}

			issue = new Issue();
			issue.setPriority(priority);
			issue.setName(name);
			issue.setDescription(description);
			issue.setDeadline(deadline);
			issue.setStatus(status);
			issue.setID(id);
			issue.setAssignees(assignees);
			issue.setDateLastUpdated(new Date());
			issue.setDateCreated(new Date());
			issue.setDateClosed(null);

			Event saveDataEvent = new SaveDataEvent();
			dialogStage.fireEvent(saveDataEvent);
			dialogStage.close();
		}

	}

//https://stackoverflow.com/questions/27416758/how-to-emit-and-handle-custom-events
	static class SaveDataEvent extends Event {

		public static final EventType<SaveDataEvent> SAVE_PRESSED = new EventType<>(
				Event.ANY, "SAVE_PRESSED");

		public SaveDataEvent() {
			super(SAVE_PRESSED);
		}
	}

	/**
	 * Constructor for project box, subject to refactoring. TODO:Make interact
	 * with project object.
	 * 
	 * To create a new issue, simply pass in a new IssueObject
	 */
	public IssueBox(Issue issue, Stage newStage) {
		this.issue = issue;
		dialogStage = newStage;

		if (issue == null) {
			issueTitle = new Text("New Issue");
			titleField = new TextField();
			descriptionField = new TextArea();
			deadlineDatePicker = new DatePicker();
			idField = new TextField();
			assigneesLabel = new Label("Assignees: ");
		} else {
			issueTitle = new Text("Edit Issue");
			titleField = new TextField(issue.getName());
			descriptionField = new TextArea(issue.getDescription());
			// https://mkyong.com/java8/java-8-convert-date-to-localdate-and-localdatetime/
			LocalDate localDeadline = issue.getDeadline().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			deadlineDatePicker = new DatePicker(localDeadline);
			idField = new TextField(issue.getID());
			// https://www.dotnetperls.com/convert-arraylist-string-java
			assigneesLabel = new Label(
					"Assignees: \n" + String.join("\n", issue.getAssignees()));
		}
		// IssueBox Title
		issueTitle.setFont(Font.font("Calibri", 45));

		// Priority Field
		priorityComboBox = new ComboBox<Issue.Priority>(
				FXCollections.observableArrayList(Issue.Priority.LOW,
						Issue.Priority.MEDIUM, Issue.Priority.HIGH));
		if (issue == null) {
			priorityComboBox.setPromptText("Issue Priority");
		} else {
			priorityComboBox.setValue(issue.getPriority());
		}

		// Name field
		titleField.setPromptText("Title");
		titleField.setMaxWidth(650);

		// Description field
		descriptionField.setPromptText("Description");
		descriptionField.setPrefRowCount(5);
		descriptionField.setMaxWidth(650);

		// Deadline field
		deadlineDatePicker.setPromptText("Deadline Date");

		// Status field
		statusComboBox = new ComboBox<Issue.Status>(
				FXCollections.observableArrayList(Issue.Status.TODO,
						Issue.Status.IN_PROGRESS, Issue.Status.COMPLETE));
		if (issue == null) {
			statusComboBox.setPromptText("Issue Status");
		} else {
			statusComboBox.setValue(issue.getStatus());
		}
		// ID field
		idField.setPromptText("Issue Reference ID");
		idField.setMaxWidth(50);

		// Assignee field and button
		assigneeField = new TextField();
		assigneeField.setPromptText("Assignee's Name");
		addAssigneeButton = new Button("Add");
		addAssigneeButton.setOnAction(e->addAssigneeButtonAction(e));
		assigneeBox = new HBox();
		assigneeBox.getChildren().addAll(assigneeField, addAssigneeButton,
				assigneesLabel);

		cancelButton = new Button("Cancel");
		saveButton = new Button("Save");

		buttonBox = new HBox();
		buttonBox.getChildren().addAll(cancelButton, saveButton);
		buttonBox.setSpacing(10);

		bottomBox = new HBox();
		bottomBox.getChildren().add(buttonBox);
		bottomBox.setSpacing(80);

		// build body//
		body = new VBox();
		body.getChildren().addAll(priorityComboBox, titleField,
				descriptionField, deadlineDatePicker, statusComboBox, idField,
				assigneeBox);

		// build frame//
		frame = new BorderPane();
		frame.setBottom(bottomBox);
		frame.setCenter(body);
		frame.setTop(issueTitle);

		// setup and handle events
		cancelButton.setOnAction(event -> dialogStage.close());
		SaveHandler saveHandler = new SaveHandler(saveButton);
		saveButton.setOnAction(saveHandler);
	}

	public void show() {
		theScene = new Scene(frame, 700, 350);
		dialogStage.setTitle("New Issue");
		dialogStage.setScene(theScene);
		dialogStage.show();
	}

	private void addAssigneeButtonAction(ActionEvent e) {
		String assignee = assigneeField.getText();
		if (assignee == null || assignee.isBlank() || assignee.isEmpty()) {
			return;
		}
		assigneeField.setText("");
		String assignees  = assigneesLabel.getText();
		if (!assignees.isEmpty()) {
			assignees = assignees + "\n" + assignee;
		}
		assigneesLabel.setText(assignees);
	}
	
	public void canceButtonAction(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION,
				"If you cancel, you will lose all changes to this issue.");
		// https://stackoverflow.com/questions/44101426/javafx-alert-box-on-button-click/44101484
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			dialogStage.close();
		}
	}

	public Issue getIssue() {
		return issue;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}
}
