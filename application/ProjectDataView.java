/**
 * 
 * ProjectDataView.java created by tyler on Windows 10 PC in IssueTracker
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

import java.util.ArrayList;
import java.util.Date;

import backend.Issue;
import backend.Project;
import backend.Project.Status;
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
import javafx.stage.Stage;

/**
 * ProjectDataView - TODO Describe purpose of this user defined type
 * 
 * @author tyler johnston (2020)
 *
 */
public class ProjectDataView extends BorderPane {

	Stage currentStage;
	
	Project project;
	
	ArrayList<Issue> issues;
	String title;
	String description;
	Date deadline;
	Date dateCreated;
	Date dateClosed;
	Status state;
	
	IssueHandler issueHandler;
	
	VBox titleStateDateBox;
	VBox descriptionBox;
	TitledPane descriptionPane;
	VBox editAndRemoveBox;

	Label titleLabel;
	Label stateLabel;
	Label deadlineLabel;
	
	Label dateCreatedLabel;
	Label dateClosedLabel;
	
	Button editButton;
	Button removeButton;

	TextArea descriptionField;
	
	
	
	public ProjectDataView(Project project, Stage currentStage) {
		this.currentStage = currentStage;
		setProject(project);
	}
	
	/**
	 * @param project the project to be set
	 */
	public void setProject(Project project) {
		if (project == null) {
			this.setLeft(null);
			this.setRight(null);
			this.setBottom(null);
			Label emptyViewLabel = new Label("Select or create a project");
			emptyViewLabel.setPadding(new Insets(40,60,40,60));
			this.setCenter(emptyViewLabel);
		} else {
				this.project = project;
				issues = project.getIssueList();
				title = project.getName();
				description = project.getDescription();
				state = project.getOpenStatus();
				deadline = project.getDeadline();
				dateCreated = project.getDateCreated();
				dateClosed = project.getDateClosed();
				issueHandler = new IssueHandler(issues, currentStage);
				constructProjectDataHub();
		}
	}

	private void constructProjectDataHub() {
		
		titleLabel = new Label(title);
		titleLabel.setFont(new Font("Arial", 25));
		titleLabel.setPadding(new Insets(10, 10, 10, 0));

		stateLabel = new Label("State: "+state.toString());
		stateLabel.setPadding(new Insets(10, 10, 10, 0));

		deadlineLabel = new Label("Deadline: \n"+deadline.toString());
		deadlineLabel.setPadding(new Insets(20, 10, 10, 0));

		dateCreatedLabel = new Label("Date Created: \n"+dateCreated.toString());
		dateCreatedLabel.setPadding(new Insets(20, 10, 10, 0));
		
		/* Wasn't updating when a project's state was set to closed, so removing it for now.*/
//		if (state == state.CLOSED && dateClosed != null) {
//			dateClosedLabel = new Label("Date Closed: \n"+dateClosed.toString());
//		} else {
//			dateClosedLabel = new Label("Date Closed: N/A");
//		}
//		dateClosedLabel.setPadding(new Insets(20, 10, 10, 0));
		
		titleStateDateBox = new VBox(titleLabel, stateLabel, deadlineLabel, dateCreatedLabel);
		titleStateDateBox.setPadding(new Insets(0,0,0,10));
		descriptionField = new TextArea(description);
		descriptionField.editableProperty().set(false);
		descriptionField.setEditable(false);
		descriptionField.setPrefHeight(titleStateDateBox.getPrefHeight());

		descriptionPane = new TitledPane("Description", descriptionField);
		descriptionBox = new VBox(descriptionPane);

		descriptionField.setPrefHeight(descriptionPane.getPrefHeight());
		descriptionField.setPrefWidth(descriptionPane.getPrefWidth());
		descriptionField.setWrapText(true);

		editButton = new Button("Edit Project");
		HBox editButtonBox = new HBox(editButton);
		editButtonBox.setPadding(new Insets(20, 10, 10, 20));
		removeButton = new Button("Remove Project");
		HBox removeButtonBox = new HBox(removeButton);
		removeButtonBox.setPadding(new Insets(20, 10, 10, 20));
		editAndRemoveBox = new VBox(editButtonBox, removeButtonBox);
		//editAndRemoveBox.setPadding(new Insets(20, 10, 10, 20));
		this.setLeft(titleStateDateBox);
		this.setRight(editAndRemoveBox);
		this.setCenter(descriptionBox);
		this.setBottom(issueHandler.getContainer());
		this.setPadding(new Insets(10, 10, 20, 0));
	}
	
	/**
	 * @return the project
	 */
	
	public Project getProject() {
		return project;
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
	public Status isState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Status state) {
		this.state = state;
	}

	/**
	 * @return the titleAndStateBox
	 */
	public VBox getTitleAndStateBox() {
		return titleStateDateBox;
	}

	/**
	 * @param titleAndStateBox the titleAndStateBox to set
	 */
	public void setTitleAndStateBox(VBox titleAndStateBox) {
		this.titleStateDateBox = titleAndStateBox;
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
