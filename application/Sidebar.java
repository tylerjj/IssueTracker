/**
 * Filename: Sidebar.java Project: IssueTracker Authors: Martin Diges mdiges@wisc.edu
 * 
 * Represents the sidebar as shown in the IssueTracker specifications
 */

package application;

import java.util.ArrayList;
import java.util.List;
import backend.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/*
 * The Sidebar class is a representation of the sidebar which will appear in the IssueTracker
 * program and will allow the user to choose which project to look within
 * 
 * @author Martin Diges
 */
public class Sidebar {

  private VBox sidebar;
  private HBox logoHolder;
  private ImageView logoViewer;
  private Label logoTitle_Label;
  private ListView<String> projectList;
  private ObservableList<String> observableProjectNames;
  private Button newProject_Button;
  final String SIDEBAR_COLOR_DEFAULT = "#EEAAFF";
  final String LOGOHOLDER_COLOR_DEFAULT = "#CC88FF";
  final int SIDEBAR_DEFAULT_WIDTH = 300;

  // Consructs a new Sidebar object
  public Sidebar() {
    constructSidebar();
  }

  // Constructs a new Sidebar object with specified maximum dimensions
  public Sidebar(double maxWidth, double maxHeight) {
    constructSidebar();
    setMaxSize(maxWidth, maxHeight);
  }

  // Constructs a new sidebar with some default properties
  private void constructSidebar() {
    sidebar = new VBox();
    setMaxWidth(SIDEBAR_DEFAULT_WIDTH);
    setSidebarColor(SIDEBAR_COLOR_DEFAULT);

    logoHolder = new HBox();
    setLogoHolderColor(LOGOHOLDER_COLOR_DEFAULT);
    logoHolder.setSpacing(10);
    setLogoImage("IssueTrackerLogo.png", 64, 64);

    logoTitle_Label = new Label();
    setLogoText("IssueTracker", 30, "arial", "#FFFFFF");
    logoHolder.getChildren().addAll(logoViewer, logoTitle_Label);

    newProject_Button = new Button("New Project");
    setNewProjectButtonStyle(14, "arial", "#CC88FF");

    projectList = new ListView<String>();
    setProjectListColor(SIDEBAR_COLOR_DEFAULT);

    setSpacing(10);
    setPadding(10, 10, 10, 10);
    sidebar.getChildren().addAll(logoHolder, newProject_Button, projectList);
    
    
  }

  // Returns a Node representation of the Sidebar object
  public Node toNode() {
    return sidebar;
  }

  // Changes the project names that are displayed on the project list
  public void setProjectNames(ArrayList<Project> projects) {
    List<String> tempList = new ArrayList<String>();
    //Fill list with names
    for (int i = 0; i < projects.size(); i++) {
      tempList.add(projects.get(i).getName());
    }
    observableProjectNames = FXCollections.observableList(tempList);//Now observable
    projectList.setItems(observableProjectNames);
  }

  // Sets max dimensions of sidebar
  public void setMaxSize(double maxWidth, double maxHeight) {
    sidebar.setMaxSize(maxWidth, maxHeight);
  }

  // Sets max width of sidebar
  public void setMaxWidth(double maxWidth) {
    sidebar.setMaxWidth(maxWidth);
  }

  // Sets the background color for the sidebar. Color formatted in hex, e.g.
  // "#0088ff"
  public void setSidebarColor(String color) {
    sidebar.setBackground(
        new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY)));
  }

  // Sets the background color for the sidebar's project list. Color formatted
  // in
  // hex, e.g. "#0088ff"
  public void setProjectListColor(String color) {
    projectList.setBackground(
        new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY)));
  }

  // Sets the background color for the logo holder. Color formatted in hex,
  // e.g.
  // "#0088ff"
  public void setLogoHolderColor(String color) {
    logoHolder.setBackground(
        new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY)));
  }

  // Sets a new logo image with a specified height and width
  public void setLogoImage(String filePath, double width, double height) {
    Image logo_Image = new Image(filePath);
    logoViewer = new ImageView(logo_Image);
    logoViewer.setFitHeight(width);
    logoViewer.setFitWidth(height);
  }

  // Sets logo test and formats it as specified by the user
  public void setLogoText(String newTitle, int textSize, String textFont, String color) {
    logoTitle_Label.setStyle("-fx-font: " + textSize + " " + textFont + "; -fx-base: " + color);
    logoTitle_Label.setText(newTitle);
  }

  // Sets a new custom size for the New Project button
  public void setNewProjectButtonSize(double width, double height) {
    newProject_Button.setPrefSize(width, height);
  }

  // Sets a new font and size for the text and new color for the New Project
  // Button
  public void setNewProjectButtonStyle(int textSize, String textFont, String color) {
    newProject_Button.setStyle("-fx-font: " + textSize + " " + textFont + "; -fx-base: " + color);
  }

  // Changes the vertical spacing between UI elements in the sidebar
  public void setSpacing(double spacing) {
    sidebar.setSpacing(spacing);
  }

  // Sets padding for the logo box and buttons, but not the project list
  public void setPadding(int top, int right, int bottom, int left) {
    logoHolder.setPadding(new Insets(top, right, bottom, left));
    // newProject_Button.setPadding(new Insets(top, right, bottom, left));
  }

  /**
   * Fills the project text list using a passed in ArrayList of projects
   */
  public void fillProjectList(ArrayList<Project> projects) {
    for (int i = 0; i < projects.size(); i++) {
      observableProjectNames.add(projects.get(i).getName());
    }

  }
  
  /**
   * Getter for new project button
   */
  public Button getNewProjectButton() {
    return newProject_Button;
  }
  
  /**
   * Getter for list view object, used for changing issue views
   */
  public ListView<String> getProjectList() {
    return projectList;
  }
}
