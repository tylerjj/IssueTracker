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
public class IssueOptionBar {

	HBox optionBar;
	Label searchIssuesLabel;
	Label searchByLabel;
	TextField searchBarTextField;
	ChoiceBox<String> searchByChoiceBox;
	Button newIssueButton;
	Button editIssueButton;
	Button removeIssueButton;

	// Creates a new, very basic SearchBar object
	public IssueOptionBar() {
		createIssueOptionBar();
	}

	// Creates a new SearchBar object and assigns it a maximum width and height
	public IssueOptionBar(double maxWidth, double maxHeight) {
		createIssueOptionBar();
		setMaxSize(maxWidth, maxHeight);
	}

	// Constructs a new optionBar with minimal constraints
	private void createIssueOptionBar() {
		optionBar = new HBox();
		/* Unimplemented Feature: Search/Sort */
		
		//searchIssuesLabel = new Label("Search Issues in ");
		//searchIssuesLabel.setWrapText(true);
		
		//searchByLabel = new Label("Search by ");
		//searchByLabel.setWrapText(true);
		
		//searchBarTextField = new TextField();
		//setPrompt("Search Issues here");
		
		//searchByChoiceBox = new ChoiceBox<String>(FXCollections
		//		.observableArrayList("Issue Name", "Issue ID", "Assignee"));
		//searchByChoiceBox.setValue("Issue Name");
		
		newIssueButton = new Button("New Issue");
		editIssueButton = new Button("Edit Issue");
		removeIssueButton = new Button("Remove Issue");
		
		/* Use this when Search/Sort are implemented. */
		//optionBar.getChildren().addAll(searchIssuesLabel,
		//		searchBarTextField, searchByLabel, searchByChoiceBox,
		//		newIssueButton, editIssueButton, removeIssueButton);
		
		/* Temp until Search/Sort are implemented. */
		optionBar.getChildren().addAll(newIssueButton, editIssueButton, removeIssueButton);
	}

	// Sets the maximum dimensions for the search bar
	public void setMaxSize(double maxWidth, double maxHeight) {
		optionBar.setMaxSize(maxWidth, maxHeight);
	}

	// Sets the preferred dimensions of the search bar
	public void setPrefSize(double prefWidth, double prefHeight) {
		optionBar.setPrefSize(prefWidth, prefHeight);
	}

	// Changes the project name being displayed on currentProject_Label
	public void setProjectLabel(String newProject) {
		searchIssuesLabel.setText("Search Issues in " + newProject);
	}

	// Changes the project name being displayed on currentProject_Label and
	// allows
	// the user to format its text
	public void setProjectLabel(String newProject, int textSize,
			String textFont, String color) {
		searchIssuesLabel.setStyle("-fx-font: " + textSize + " " + textFont
				+ "; -fx-base: " + color);
		searchIssuesLabel.setText("Search Issues in " + newProject);
	}

	// Sets a new prompt for searchBar_TextField
	public void setPrompt(String prompt) {
		searchBarTextField.setPromptText(prompt);
	}

	// Sets the spacing between optionBar's horizontal components
	public void setSpacing(double spacing) {
		optionBar.setSpacing(spacing);
	}

	// Sets padding on all sides of the search bar
	public void setPadding(int top, int right, int bottom, int left) {
		optionBar.setPadding(new Insets(top, right, bottom, left));
	}

	// Assigns a new event to the New Issue button
	public void setNewIssueButtonEvent(Event e) {
		// TODO Not yet implemented
	}

	// Sets a new custom size for the New Issue button
	public void setNewIssueButtonSize(double width, double height) {
		newIssueButton.setPrefSize(width, height);
	}

	// Sets a new font and size for the text and new color for the New Issue
	// Button
	public void setNewIssueButtonStyle(int textSize, String textFont,
			String color) {
		newIssueButton.setStyle("-fx-font: " + textSize + " " + textFont
				+ "; -fx-base: " + color);
	}

	// Returns a Node representation of the SearchBar object
	public Node toNode() {
		return optionBar;
	}

	/**
	 * @return the optionBar
	 */
	public HBox getSearchBar() {
		return optionBar;
	}

	/**
	 * @param optionBar the optionBar to set
	 */
	public void setSearchBar(HBox searchBar) {
		this.optionBar = searchBar;
	}

	/**
	 * @return the searchIssuesLabel
	 */
	public Label getSearchIssuesLabel() {
		return searchIssuesLabel;
	}

	/**
	 * @param searchIssuesLabel the searchIssuesLabel to set
	 */
	public void setSearchIssuesLabel(Label searchIssuesLabel) {
		this.searchIssuesLabel = searchIssuesLabel;
	}

	/**
	 * @return the searchByLabel
	 */
	public Label getSearchByLabel() {
		return searchByLabel;
	}

	/**
	 * @param searchByLabel the searchByLabel to set
	 */
	public void setSearchByLabel(Label searchByLabel) {
		this.searchByLabel = searchByLabel;
	}

	/**
	 * @return the searchBarTextField
	 */
	public TextField getSearchBarTextField() {
		return searchBarTextField;
	}

	/**
	 * @param searchBarTextField the searchBarTextField to set
	 */
	public void setSearchBarTextField(TextField searchBarTextField) {
		this.searchBarTextField = searchBarTextField;
	}

	/**
	 * @return the searchByChoiceBox
	 */
	public ChoiceBox<String> getSearchByChoiceBox() {
		return searchByChoiceBox;
	}

	/**
	 * @param searchByChoiceBox the searchByChoiceBox to set
	 */
	public void setSearchByChoiceBox(ChoiceBox<String> searchByChoiceBox) {
		this.searchByChoiceBox = searchByChoiceBox;
	}

	/**
	 * @return the newIssueButton
	 */
	public Button getNewIssueButton() {
		return newIssueButton;
	}

	/**
	 * @param newIssueButton the newIssueButton to set
	 */
	public void setNewIssueButton(Button newIssueButton) {
		this.newIssueButton = newIssueButton;
	}

	/**
	 * @return the editIssueButton
	 */
	public Button getEditIssueButton() {
		return editIssueButton;
	}

	/**
	 * @param editIssueButton the editIssueButton to set
	 */
	public void setEditIssueButton(Button editIssueButton) {
		this.editIssueButton = editIssueButton;
	}

	/**
	 * @return the removeIssueButton
	 */
	public Button getRemoveIssueButton() {
		return removeIssueButton;
	}

	/**
	 * @param removeIssueButton the removeIssueButton to set
	 */
	public void setRemoveIssueButton(Button removeIssueButton) {
		this.removeIssueButton = removeIssueButton;
	}

}
