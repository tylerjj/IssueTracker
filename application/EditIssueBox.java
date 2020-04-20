package application;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditIssueBox {
  private Text issueTitle;
  private TextField titleField;
  private TextArea descriptionField;
  private TextField assigneeBox;
  private Button assigneeButton;
  private Button saveButton;
  private Button cancelButton;
  private HBox buttonField;
  private HBox assigneeField;
  private HBox bottomField;
  private DatePicker startDatePicker;
  private DatePicker deadlineDatePicker;
  private DatePicker creationDatePicker;
  private DatePicker closeDatePicker;
  private VBox body;
  private BorderPane frame;
  private Stage editingStage;
  private Scene editingScene;
  
  /**
   * Constructor for project box, subject to refactoring. TODO:Make interact with project object.
   */
  public EditIssueBox(int id){
    constructEditBox();
  }
  
  private void constructEditBox() {
  //Top heading//
    issueTitle = new Text("New Issue");
    issueTitle.setFont(Font.font("Calibri", 45));
    
    //TitleBox//
    titleField = new TextField();
    titleField.setPromptText("Title");
    titleField.setMaxWidth(650);
    
    //Description Box//
    descriptionField = new TextArea();
    descriptionField.setPromptText("Description");
    descriptionField.setPrefRowCount(5);
    descriptionField.setMaxWidth(650);
  
    //Assignee Field, Cancel and Save Buttons//
    assigneeBox = new TextField();
    assigneeBox.setPromptText("Assignee's Name");  
    assigneeButton = new Button("Add");
    
    cancelButton = new Button("Cancel");
    saveButton = new Button("Save");
    
    assigneeField = new HBox();
    assigneeField.getChildren().addAll(assigneeBox, assigneeButton);
    
    buttonField = new HBox();
    buttonField.getChildren().addAll(cancelButton, saveButton);
    buttonField.setSpacing(10);
    
    bottomField = new HBox();
    bottomField.getChildren().addAll(assigneeField, buttonField);
    bottomField.setSpacing(80);
    
    //Date Boxes//
    startDatePicker = new DatePicker();
    startDatePicker.setPromptText("Start Date");
    deadlineDatePicker = new DatePicker();
    deadlineDatePicker.setPromptText("Deadline Date");
    creationDatePicker = new DatePicker();
    creationDatePicker.setPromptText("Creation Date");
    closeDatePicker = new DatePicker();
    closeDatePicker.setPromptText("Close Date");
    
    //build body//
    body = new VBox();
    body.getChildren().addAll(titleField, descriptionField, startDatePicker, deadlineDatePicker, creationDatePicker ,closeDatePicker);
    
    //build frame//
    frame = new BorderPane();
    frame.setBottom(bottomField);
    frame.setCenter(body);
    frame.setTop(issueTitle);
    
    //display new stage
    editingStage = new Stage();
    editingScene = new Scene(frame, 700, 350);
    editingStage.setTitle("New Project");
    editingStage.setScene(editingScene);
    editingStage.show(); 
  }
  
}
