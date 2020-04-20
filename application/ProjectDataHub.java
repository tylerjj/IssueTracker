/**
 * 
 * ProjectDataHub.java created by tyler on Windows 10 PC in IssueTracker
 * 
 * Author: Tyler Johnston (tjohnston@cs.wisc.edu) Date: @date
 * 
 * Course: CS400 Semester: Spring 2020 Lecture: 002
 * 
 * IDE: Eclipse IDE for Java Developers Version: 2019-12 (4.14.0) Build id:
 * 20191212-1212
 * 
 * Device: tyler-PC OS: Windows 10 Pro Version: 1809 OS Build: 17763.914
 * 
 * List Collaborators: Name, email@wisc.edu, lecture number
 * 
 * Other Credits: describe other sources (websites or people)
 * 
 * Known Bugs: describe known unresolved bugs here
 */
package application;

import java.util.Date;

import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * ProjectDataHub - TODO Describe purpose of this user defined type
 * 
 * @author tyler johnston (2020)
 *
 */
public class ProjectDataHub extends BorderPane {

	String description;
	String title;
	boolean state;
	Date deadline;

	// ProjectTitle {Description} Edit
	// Open/Closed {Description} Remove

	VBox titleStateDeadlineBox;
	VBox descriptionBox;
	// ScrollPane descriptionPane;
	TitledPane descriptionPane;
	VBox editAndRemoveBox;

	Label titleLabel;
	Label stateLabel;
	Label deadlineLabel;

	Button editButton;
	Button removeButton;

	TextArea descriptionField;

	public ProjectDataHub(String title, String description, boolean state,
			Date deadline) {
		this.title = title;
		this.description = description;
		this.state = state;

		titleLabel = new Label(title);
		titleLabel.setFont(new Font("Arial", 25));
		titleLabel.setPadding(new Insets(10, 10, 10, 0));

		if (state == true) {
			stateLabel = new Label("State: Open");
		} else {
			stateLabel = new Label("State: Closed");
		}
		stateLabel.setPadding(new Insets(10, 10, 10, 0));

		deadlineLabel = new Label(deadline.toString());
		deadlineLabel.setPadding(new Insets(20, 10, 10, 0));

		titleStateDeadlineBox = new VBox(titleLabel, stateLabel, deadlineLabel);

		descriptionField = new TextArea(description);
		descriptionField.editableProperty().set(false);
		descriptionField.setEditable(false);

		// descriptionPane = new ScrollPane(descriptionField);
		// descriptionPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		// descriptionPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		descriptionPane = new TitledPane("Description", descriptionField);
		descriptionBox = new VBox(descriptionPane);

		descriptionField.setPrefHeight(descriptionPane.getPrefHeight());
		descriptionField.setPrefWidth(descriptionPane.getPrefWidth());
		descriptionField.setWrapText(true);

		editButton = new Button("Edit Project");
		removeButton = new Button("Remove Project");

		editAndRemoveBox = new VBox(editButton, removeButton);

		this.setLeft(titleStateDeadlineBox);
		this.setRight(editAndRemoveBox);
		this.setCenter(descriptionBox);
		this.setPadding(new Insets(10, 10, 20, 0));
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @return the titleAndStateBox
	 */
	public VBox getTitleAndStateBox() {
		return titleStateDeadlineBox;
	}

	/**
	 * @param titleAndStateBox the titleAndStateBox to set
	 */
	public void setTitleAndStateBox(VBox titleAndStateBox) {
		this.titleStateDeadlineBox = titleAndStateBox;
	}

	/**
	 * @return the descriptionBox
	 */
	public VBox getDescriptionBox() {
		return descriptionBox;
	}

	/**
	 * @param descriptionBox the descriptionBox to set
	 */
	public void setDescriptionBox(VBox descriptionBox) {
		this.descriptionBox = descriptionBox;
	}

	/**
	 * @return the descriptionPane
	 */
	public TitledPane getDescriptionPane() {
		return descriptionPane;
	}

	/**
	 * @param descriptionPane the descriptionPane to set
	 */
	public void setDescriptionPane(TitledPane descriptionPane) {
		this.descriptionPane = descriptionPane;
	}

	/**
	 * @return the editAndRemoveBox
	 */
	public VBox getEditAndRemoveBox() {
		return editAndRemoveBox;
	}

	/**
	 * @param editAndRemoveBox the editAndRemoveBox to set
	 */
	public void setEditAndRemoveBox(VBox editAndRemoveBox) {
		this.editAndRemoveBox = editAndRemoveBox;
	}

	/**
	 * @return the titleLabel
	 */
	public Label getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @param titleLabel the titleLabel to set
	 */
	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	/**
	 * @return the stateLabel
	 */
	public Label getStateLabel() {
		return stateLabel;
	}

	/**
	 * @param stateLabel the stateLabel to set
	 */
	public void setStateLabel(Label stateLabel) {
		this.stateLabel = stateLabel;
	}

	/**
	 * @return the editButton
	 */
	public Button getEditButton() {
		return editButton;
	}

	/**
	 * @param editButton the editButton to set
	 */
	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}

	/**
	 * @return the removeButton
	 */
	public Button getRemoveButton() {
		return removeButton;
	}

	/**
	 * @param removeButton the removeButton to set
	 */
	public void setRemoveButton(Button removeButton) {
		this.removeButton = removeButton;
	}

	/**
	 * @return the descriptionField
	 */
	public TextArea getDescriptionField() {
		return descriptionField;
	}

	/**
	 * @param descriptionField the descriptionField to set
	 */
	public void setDescriptionField(TextArea descriptionField) {
		this.descriptionField = descriptionField;
	}

}
