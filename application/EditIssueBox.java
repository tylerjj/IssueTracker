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
  BorderPane node;
  int issueID;
  
  /**
   * Constructor takes an issue as an arg, display everything, and change anything changed by the user.
   * @param issueID :TODO: This is temp, replace with issue class when ready.
   */
  public EditIssueBox(int issueID) {
    constructIssueBox();
    this.issueID = issueID;
  }
  
  /**
   * This method will construct the issue box based on the Issue object's fields.
   */
  private void constructIssueBox() {
    Text projectTitle = new Text("New Issue                       ID:" + issueID);
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
    DatePicker startDatePicker = new DatePicker();
    startDatePicker.setPromptText("Start Date");
    DatePicker deadlineDatePicker = new DatePicker();
    deadlineDatePicker.setPromptText("Deadline Date");
    DatePicker creationDatePicker = new DatePicker();
    creationDatePicker.setPromptText("Creation Date");
    DatePicker closeDatePicker = new DatePicker();
    closeDatePicker.setPromptText("Close Date");
    
    module.getChildren().addAll(titleField, descriptionField, startDatePicker, deadlineDatePicker, creationDatePicker ,closeDatePicker, assigneeField);
    node = new BorderPane();
    node.setBottom(assigneeField);
    node.setCenter(module);
    node.setTop(projectTitle);
    Stage newStage = new Stage();
    Scene newScene = new Scene(node, 700, 350);
    newStage.setTitle("New Issue");
    newStage.setScene(newScene);
    newStage.show(); 
  }
  
}
