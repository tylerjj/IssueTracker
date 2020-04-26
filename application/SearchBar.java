/**
 * Filename: SearchBar.java Project: IssueTracker Authors: Martin Diges
 * mdiges@wisc.edu
 * 
 * Represents a search bar as shown in the IssueTracker specifications
 */

package application;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/*
 * The SearchBar class is a representation of the search bar which will appear
 * in the IssueTracker program and includes methods to modify a number of the
 * search bar's components
 * 
 * @author Martin Diges
 */
public class SearchBar {

	private HBox searchBar;
	private Label currentProject_Label;
	private Label searchBy_Label;
	private TextField searchBar_TextField;
	private ChoiceBox<String> searchBy_ChoiceBox;
	private Button newIssue_Button;
	private Button editIssue_Button;
	private Button removeIssue_Button;

	// Creates a new, very basic SearchBar object
	public SearchBar() {
		constructBasicSearchBar();
	}

	// Creates a new SearchBar object and assigns it a maximum width and height
	public SearchBar(double maxWidth, double maxHeight) {
		constructBasicSearchBar();
		setMaxSize(maxWidth, maxHeight);
	}

	// Constructs a new searchBar with minimal constraints
	private void constructBasicSearchBar() {
		searchBar = new HBox();
		currentProject_Label = new Label("Search Issues in ");
		searchBy_Label = new Label("Search by ");
		searchBar_TextField = new TextField();
		setPrompt("Search Issues here");
		searchBy_ChoiceBox = new ChoiceBox<String>(FXCollections
				.observableArrayList("Issue Name", "Issue ID", "Assignee"));
		searchBy_ChoiceBox.setValue("Issue Name");
		newIssue_Button = new Button("New Issue");
		editIssue_Button = new Button("Edit Issue");
		removeIssue_Button = new Button("Remove Issue");
		searchBar.getChildren().addAll(currentProject_Label,
				searchBar_TextField, searchBy_Label, searchBy_ChoiceBox,
				newIssue_Button, editIssue_Button, removeIssue_Button);
		
		Issue testIssue = new Issue();
        testIssue.setName("James is a dork");
        testIssue.setDescription("Big meme");
        EditIssueBox issueBox  = new EditIssueBox(testIssue);
        newIssue_Button.setOnAction(newIssue -> issueBox.constructEditBox());
	}

	// Sets the maximum dimensions for the search bar
	public void setMaxSize(double maxWidth, double maxHeight) {
		searchBar.setMaxSize(maxWidth, maxHeight);
	}

	// Sets the preferred dimensions of the search bar
	public void setPrefSize(double prefWidth, double prefHeight) {
		searchBar.setPrefSize(prefWidth, prefHeight);
	}

	// Changes the project name being displayed on currentProject_Label
	public void setProjectLabel(String newProject) {
		currentProject_Label.setText("Search Issues in " + newProject);
	}

	// Changes the project name being displayed on currentProject_Label and
	// allows
	// the user to format its text
	public void setProjectLabel(String newProject, int textSize,
			String textFont, String color) {
		currentProject_Label.setStyle("-fx-font: " + textSize + " " + textFont
				+ "; -fx-base: " + color);
		currentProject_Label.setText("Search Issues in " + newProject);
	}

	// Sets a new prompt for searchBar_TextField
	public void setPrompt(String prompt) {
		searchBar_TextField.setPromptText(prompt);
	}

	// Sets the spacing between searchBar's horizontal components
	public void setSpacing(double spacing) {
		searchBar.setSpacing(spacing);
	}

	// Sets padding on all sides of the search bar
	public void setPadding(int top, int right, int bottom, int left) {
		searchBar.setPadding(new Insets(top, right, bottom, left));
	}

	// Assigns a new event to the New Issue button
	public void setNewIssueButtonEvent(Event e) {
		// TODO Not yet implemented
	}

	// Sets a new custom size for the New Issue button
	public void setNewIssueButtonSize(double width, double height) {
		newIssue_Button.setPrefSize(width, height);
	}

	// Sets a new font and size for the text and new color for the New Issue
	// Button
	public void setNewIssueButtonStyle(int textSize, String textFont,
			String color) {
		newIssue_Button.setStyle("-fx-font: " + textSize + " " + textFont
				+ "; -fx-base: " + color);
	}

	// Returns a Node representation of the SearchBar object
	public Node toNode() {
		return searchBar;
	}

}
