package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
import javafx.stage.Stage;

public class IssueBox {
  private Issue issue;
  private Text issueTitle;
  private ComboBox<Issue.Priority> priorityComboBox;
  private TextField titleField;
  private TextArea descriptionField;
  private ComboBox<Issue.Status> statusComboBox;
  private TextField idField;
  private TextField assigneeField;
  private Label assigneesLabel;
  private Button assigneeButton;
  private Button saveButton;
  private Button cancelButton;
  private HBox buttonField;
  private HBox assigneeBox;
  private HBox bottomField;
  //private DatePicker startDatePicker;
  private DatePicker deadlineDatePicker;
  //private DatePicker creationDatePicker;
  //private DatePicker closeDatePicker;
  private VBox body;
  private BorderPane frame;
  private Stage editingStage;
  private Scene editingScene;


  class SaveHandler implements EventHandler<ActionEvent> {
    private Button saveButton;

    SaveHandler(Button saveButton) {
      this.saveButton = saveButton;
    }

    @Override
    public void handle(ActionEvent arg0) {
      
      boolean missingFields = false;
      Issue.Priority priority= priorityComboBox.getValue();
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
      if (deadlineDatePicker.getValue()==null) {
    	  missingFields = true;
      } else {
    	  //https://mkyong.com/java8/java-8-convert-date-to-localdate-and-localdatetime/
    	  deadline = Date.from(deadlineDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
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
     //https://beginnersbook.com/2015/05/java-string-to-arraylist-conversion/
      ArrayList<String> assignees = new ArrayList<String>(); 
      if (assigneesValue != null && !assigneesValue.isBlank()) {
    	  String[] str = assigneesValue.split("\n");
    	  for (int i = 0; i < str.length; i++) {
    		  if (i>0) {
    			  assignees.add(str[i]);
    		  }
    	  }
      } 
      
      
      if (missingFields) {
    	  Alert alert = new Alert(AlertType.WARNING, "Required Fields Are Missing");
    	  alert.showAndWait().filter(r->r==ButtonType.OK);
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
      editingStage.fireEvent(saveDataEvent);
      editingStage.close();
    }

  }
  
//https://stackoverflow.com/questions/27416758/how-to-emit-and-handle-custom-events
static class SaveDataEvent extends Event {
	
	  public static final EventType<SaveDataEvent> SAVE_PRESSED = new EventType<>(Event.ANY, "SAVE_PRESSED");

	  public SaveDataEvent() {
		  super(SAVE_PRESSED);
	  }
}

  /**
   * Constructor for project box, subject to refactoring. TODO:Make interact with project object.
   * 
   * To create a new issue, simply pass in a new IssueObject
   */
  public IssueBox(Issue issue, Stage newStage) {
    this.issue = issue;
    editingStage = newStage;
    
    if (issue == null) {
		issueTitle = new Text("New Issue");
    	titleField = new TextField();
        descriptionField = new TextArea();
        deadlineDatePicker = new DatePicker();
        idField = new TextField();
        assigneesLabel = new Label("Assignees: \n");
    } else {
    	issueTitle = new Text("Edit Issue");
    	titleField = new TextField(issue.getName());
    	descriptionField = new TextArea(issue.getDescription());
    	//https://mkyong.com/java8/java-8-convert-date-to-localdate-and-localdatetime/
    	LocalDate localDeadline = issue.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	deadlineDatePicker = new DatePicker(localDeadline);
    	idField = new TextField(issue.getID());
    	//https://www.dotnetperls.com/convert-arraylist-string-java
    	assigneesLabel = new Label("Assignees: \n"+String.join("\n", issue.getAssignees())); 	
    }
    // IssueBox Title
    issueTitle.setFont(Font.font("Calibri", 45));
    
    // Priority Field
	priorityComboBox = new ComboBox<Issue.Priority>(FXCollections
			.observableArrayList(Issue.Priority.LOW, Issue.Priority.MEDIUM, Issue.Priority.HIGH));
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
    statusComboBox = new ComboBox<Issue.Status>(FXCollections
			.observableArrayList(Issue.Status.TODO, Issue.Status.IN_PROGRESS, Issue.Status.COMPLETE));
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
    assigneeButton = new Button("Add");
    
    assigneeBox = new HBox();
    assigneeBox.getChildren().addAll(assigneeField, assigneeButton, assigneesLabel);
    
    cancelButton = new Button("Cancel");
    saveButton = new Button("Save");
    
    buttonField = new HBox();
    buttonField.getChildren().addAll(cancelButton, saveButton);
    buttonField.setSpacing(10);
    
    bottomField = new HBox();
    bottomField.getChildren().add(buttonField);
    bottomField.setSpacing(80);
    
    // build body//
    body = new VBox();
    body.getChildren().addAll(priorityComboBox, titleField, descriptionField, deadlineDatePicker, statusComboBox, idField, assigneeBox);

    // build frame//
    frame = new BorderPane();
    frame.setBottom(bottomField);
    frame.setCenter(body);
    frame.setTop(issueTitle);

    // setup and handle events
	cancelButton.setOnAction(event -> editingStage.close()); 
	SaveHandler saveHandler = new SaveHandler(saveButton); 
	saveButton.setOnAction(saveHandler);
  }
  
  public void show() {
	    editingScene = new Scene(frame, 700, 350);
	    editingStage.setTitle("New Issue");
	    editingStage.setScene(editingScene);
	    editingStage.show();
  }
  public void canceButtonAction(ActionEvent e) {
	  Alert alert = new Alert(AlertType.CONFIRMATION, "If you cancel, you will lose all changes to this issue.");
		//https://stackoverflow.com/questions/44101426/javafx-alert-box-on-button-click/44101484
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get()==ButtonType.OK) {
			editingStage.close();
		}
  }
  
//  public void constructNewIssueBox() {
//	  
//  }
//  public void constructEditIssueBox() {
//    // Top heading//
//    if (issue.getName() == null) {
//      issueTitle = new Text("New Issue");
//    } else {
//      issueTitle = new Text(issue.getName());
//    }
//
//    issueTitle.setFont(Font.font("Calibri", 45));
//
//    // TitleBox//
//    if (issue.getName() == null) {
//      titleField = new TextField();
//    } else {
//      titleField = new TextField(issue.getName());
//    }
//    titleField.setPromptText("Title");
//    titleField.setMaxWidth(650);
//
//    // Description Box//
//    if (issue.getDescription() == null) {
//      descriptionField = new TextArea();
//    } else {
//      descriptionField = new TextArea(issue.getDescription());
//    }
//    descriptionField.setPromptText("Description");
//    descriptionField.setPrefRowCount(5);
//    descriptionField.setMaxWidth(650);
//
//    // Assignee Field, Cancel and Save Buttons//
//    assigneeBox = new TextField();
//    assigneeBox.setPromptText("Assignee's Name");
//    assigneeButton = new Button("Add");
//
//    cancelButton = new Button("Cancel");
//    saveButton = new Button("Save");
//
//    assigneeField = new HBox();
//    assigneeField.getChildren().addAll(assigneeBox, assigneeButton);
//
//    buttonField = new HBox();
//    buttonField.getChildren().addAll(cancelButton, saveButton);
//    buttonField.setSpacing(10);
//
//    bottomField = new HBox();
//    bottomField.getChildren().addAll(assigneeField, buttonField);
//    bottomField.setSpacing(80);
//
//    // Date Boxes//
//    if (issue.getDateCreated() == null) {
//      startDatePicker = new DatePicker();
//    } else {
//      // https://www.baeldung.com/java-date-to-localdate-and-localdatetime
//      // Needed to preset the date boxes.
//      startDatePicker = new DatePicker(
//          issue.getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//    }
//    startDatePicker.setPromptText("Start Date");
//
//    if (issue.getDeadline() == null) {
//      deadlineDatePicker = new DatePicker();
//    } else {
//      deadlineDatePicker = new DatePicker(
//          issue.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//    }
//    deadlineDatePicker.setPromptText("Deadline Date");
//
//    if (issue.getDateCreated() == null) {
//      creationDatePicker = new DatePicker();
//    } else {
//      creationDatePicker = new DatePicker(
//          issue.getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//    }
//    creationDatePicker.setPromptText("Creation Date");
//
//    if (issue.getDateClosed() == null) {
//      closeDatePicker = new DatePicker();
//    } else {
//      closeDatePicker = new DatePicker(
//          issue.getDateClosed().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//    }
//    closeDatePicker.setPromptText("Close Date");
//
//
//
//    // build body//
//    body = new VBox();
//    body.getChildren().addAll(titleField, descriptionField, startDatePicker, deadlineDatePicker,
//        creationDatePicker, closeDatePicker);
//
//    // build frame//
//    frame = new BorderPane();
//    frame.setBottom(bottomField);
//    frame.setCenter(body);
//    frame.setTop(issueTitle);
//
//    // display new stage
////    editingStage = new Stage();
//    editingScene = new Scene(frame, 700, 350);
//    editingStage.setTitle("New Issue");
//    editingStage.setScene(editingScene);
//    editingStage.show();
//
//    // setup and handle events
//
//	cancelButton.setOnAction(event -> editingScene.hide()); 
//	SaveHandler saveHandler = new SaveHandler(saveButton); 
//	saveButton.setOnAction(saveHandler);
//   
//    
//    // saveButton.setOnAction(event -> editingStage.hide());
//    // saveButton.setOnAction(event -> issue.setName(titleField.getCharacters().toString()));
//    // saveButton.setOnAction(event ->
//    // issue.setDescription(descriptionField.getParagraphs().toString())); //TODO: remove brackets
//    //
//  }
  
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
