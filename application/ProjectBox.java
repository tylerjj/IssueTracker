package application;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import application.IssueBox.SaveDataEvent;
import backend.Issue;
import backend.Project;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectBox {
  private Project project;
  private ComboBox<Project.Status> openStatusBox;
  private Text projectTitle;
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
  private SaveDataEvent saveDataEvent;


  //from Tyler's IssueBox Class
  static class SaveDataEvent extends Event {

    public static final EventType<SaveDataEvent> SAVE_PRESSED = new EventType<>(
            Event.ANY, "SAVE_PRESSED");

    public SaveDataEvent() {
        super(SAVE_PRESSED);
    }
}
  
  class SaveHandler implements EventHandler<ActionEvent> {
    private Button saveButton;

    SaveHandler(Button saveButton) {
      this.saveButton = saveButton;
    }

    @Override
    public void handle(ActionEvent arg0) {

      boolean missingFields = false;

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
        deadline = Date
            .from(deadlineDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
      }
      
      Project.Status status = openStatusBox.getValue();
      if (status == null) {
        missingFields = true;
      }


      if (missingFields) {
        Alert alert = new Alert(AlertType.WARNING, "Required Fields Are Missing");
        alert.showAndWait().filter(r -> r == ButtonType.OK);
        return;
      } else {
        project.setName(name);
        project.setDescription(description);
        project.setDeadline(deadline);
        project.setDateCreated(new Date());
        project.setOpenStatus(status);
      }
      saveDataEvent = new SaveDataEvent();
    }

  }

  /**
   * Constructor for project box, subject to refactoring. TODO:Make interact with project object.
   */
  public ProjectBox(Project project) {
    this.project = project;
    constructEditBox();
  }

  private void constructEditBox() {

    if (project == null) {
      // Top heading//
      projectTitle = new Text("New Project");
      projectTitle.setFont(Font.font("Calibri", 45));

      // TitleBox//
      titleField = new TextField();
      titleField.setPromptText("Title");
      titleField.setMaxWidth(650);

      // Description Box//
      descriptionField = new TextArea();
      descriptionField.setPromptText("Description");
      descriptionField.setPrefRowCount(5);
      descriptionField.setMaxWidth(650);

      // Date Boxes//
      deadlineDatePicker = new DatePicker();
      deadlineDatePicker.setPromptText("Deadline Date");

      openStatusBox = new ComboBox<Project.Status>(
          FXCollections.observableArrayList(Project.Status.OPEN, Project.Status.CLOSED));
      openStatusBox.setPromptText("Issue Priority");

      project = new Project();
    } else {
      projectTitle = new Text("Edit Project");
      projectTitle.setFont(Font.font("Calibri", 45));

      // TitleBox//
      titleField = new TextField();
      titleField.setPromptText(project.getName());
      titleField.setMaxWidth(650);

      // Description Box//
      descriptionField = new TextArea(project.getDescription());
      descriptionField.setPromptText("Description");
      descriptionField.setPrefRowCount(5);
      descriptionField.setMaxWidth(650);

      // Date Boxes//
      deadlineDatePicker = new DatePicker(
          project.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
      deadlineDatePicker.setPromptText("Deadline Date");
      
      ComboBox<Project.Status> openStatusBox = new ComboBox<Project.Status>(
          FXCollections.observableArrayList(Project.Status.OPEN, Project.Status.CLOSED));
      openStatusBox.setValue(project.getOpenStatus());
    }


    cancelButton = new Button("Cancel");
    saveButton = new Button("Save");
    SaveHandler saveHandler = new SaveHandler(saveButton);
    saveButton.setOnAction(saveHandler);
    
    buttonField = new HBox();
    buttonField.getChildren().addAll(cancelButton, saveButton);
    buttonField.setSpacing(10);

    // build body//
    body = new VBox();
    body.getChildren().addAll(titleField, descriptionField, deadlineDatePicker, openStatusBox);

    // build frame//
    frame = new BorderPane();
    frame.setBottom(buttonField);
    frame.setCenter(body);
    frame.setTop(projectTitle);

    // display new stage
    editingStage = new Stage();
    editingScene = new Scene(frame, 700, 350);
    editingStage.setTitle("New Project");
    editingStage.setScene(editingScene);
    editingStage.show();
  }

  public Project getProject() {
    return project;
  }
  public Button getSaveButton() {
    return saveButton;
  }
  public SaveDataEvent getSaveDataEvent() {
    return saveDataEvent;
  }
}