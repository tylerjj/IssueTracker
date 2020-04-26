package application;

import javafx.event.ActionEvent;
import java.time.ZoneId;
import javafx.event.EventHandler;
import javafx.event.Event;
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
  private Issue issue;
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


  class SaveHandler implements EventHandler<ActionEvent> {
    private Button saveButton;

    SaveHandler(Button saveButton) {
      this.saveButton = saveButton;
    }

    @Override
    public void handle(ActionEvent arg0) {
      editingStage.hide();
      issue.setName(titleField.getCharacters().toString());
    }

  }

  /**
   * Constructor for project box, subject to refactoring. TODO:Make interact with project object.
   * 
   * To create a new issue, simply pass in a new IssueObject
   */
  public EditIssueBox(Issue issue) {
    this.issue = issue;
  }

  public void constructEditBox() {
    // Top heading//
    if (issue.getName() == null) {
      issueTitle = new Text("New Issue");
    } else {
      issueTitle = new Text(issue.getName());
    }

    issueTitle.setFont(Font.font("Calibri", 45));

    // TitleBox//
    if (issue.getName() == null) {
      titleField = new TextField();
    } else {
      titleField = new TextField(issue.getName());
    }
    titleField.setPromptText("Title");
    titleField.setMaxWidth(650);

    // Description Box//
    if (issue.getDescription() == null) {
      descriptionField = new TextArea();
    } else {
      descriptionField = new TextArea(issue.getDescription());
    }
    descriptionField.setPromptText("Description");
    descriptionField.setPrefRowCount(5);
    descriptionField.setMaxWidth(650);

    // Assignee Field, Cancel and Save Buttons//
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

    // Date Boxes//
    if (issue.getDateCreated() == null) {
      startDatePicker = new DatePicker();
    } else {
      // https://www.baeldung.com/java-date-to-localdate-and-localdatetime
      // Needed to preset the date boxes.
      startDatePicker = new DatePicker(
          issue.getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    startDatePicker.setPromptText("Start Date");

    if (issue.getDeadline() == null) {
      deadlineDatePicker = new DatePicker();
    } else {
      deadlineDatePicker = new DatePicker(
          issue.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    deadlineDatePicker.setPromptText("Deadline Date");

    if (issue.getDateCreated() == null) {
      creationDatePicker = new DatePicker();
    } else {
      creationDatePicker = new DatePicker(
          issue.getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    creationDatePicker.setPromptText("Creation Date");

    if (issue.getDateClosed() == null) {
      closeDatePicker = new DatePicker();
    } else {
      closeDatePicker = new DatePicker(
          issue.getDateClosed().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    closeDatePicker.setPromptText("Close Date");



    // build body//
    body = new VBox();
    body.getChildren().addAll(titleField, descriptionField, startDatePicker, deadlineDatePicker,
        creationDatePicker, closeDatePicker);

    // build frame//
    frame = new BorderPane();
    frame.setBottom(bottomField);
    frame.setCenter(body);
    frame.setTop(issueTitle);

    // display new stage
    editingStage = new Stage();
    editingScene = new Scene(frame, 700, 350);
    editingStage.setTitle("New Issue");
    editingStage.setScene(editingScene);
    editingStage.show();

    // setup and handle events

    cancelButton.setOnAction(event -> editingStage.hide());
    SaveHandler saveHandler = new SaveHandler(saveButton);
    saveButton.setOnAction(saveHandler);
    // saveButton.setOnAction(event -> editingStage.hide());
    // saveButton.setOnAction(event -> issue.setName(titleField.getCharacters().toString()));
    // saveButton.setOnAction(event ->
    // issue.setDescription(descriptionField.getParagraphs().toString())); //TODO: remove brackets
    //
  }

}
