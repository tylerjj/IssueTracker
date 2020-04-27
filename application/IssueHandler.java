/**
 * 
 * IssueHandler.java created by tyler on Windows 10 PC in IssueTracker
 * 
 * Author: 	 Tyler Johnston (tjohnston@cs.wisc.edu)
 * Date: 	 @date
 * 
 * Course: 	 CS400
 * Semester: Spring 2020
 * Lecture:  002
 * 
 * IDE: Eclipse IDE for Java Developers
 * Version:  2019-12 (4.14.0)
 * Build id: 20191212-1212
 * 
 * Device: 	 tyler-PC
 * OS: 	 	 Windows 10 Pro
 * Version:  1809
 * OS Build: 17763.914
 * 
 * List Collaborators: Name, email@wisc.edu, lecture number
 * 
 * Other Credits: describe other sources (websites or people)
 * 
 * Known Bugs: describe known unresolved bugs here
 */
package application;

import java.util.ArrayList;
import java.util.Optional;

import application.IssueTable.IssueCell;
import backend.Issue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * IssueHandler - This will be the class that handles both the GUI aspects of 
 * 				  the issues for a given Project, and the event handling of 
 *				  buttons related to those issues.
 * @author tyler johnston (2020)
 *
 */
public class IssueHandler {

	VBox container = new VBox();
	IssueOptionBar issueOptions;
	IssueTable issueTable;
	IssueTable.IssueCell selectedRow;
	ArrayList<Issue> issues;
	Stage currentStage;
	
	IssueHandler(ArrayList<Issue> issues, Stage currentStage) {
		
		this.issues = issues;
		this.currentStage = currentStage; 
		
		issueOptions = new IssueOptionBar();
		issueOptions.setSpacing(10);;
		issueOptions.setPrefSize(1200, 50);
		issueOptions.setPadding(30, 15, 30, 15);
		
		issueTable = new IssueTable(container.getPrefWidth(), container.getPrefHeight(), this.issues);
		issueTable.getTable().setOnMouseClicked(e->issueTableClickAction(e));
				
		container.getChildren().addAll(issueOptions.toNode(), issueTable.getTable());
		
		issueOptions.getNewIssueButton().setOnAction(e->newIssueAction(e));
		
		issueOptions.getEditIssueButton().setOnAction(e->editIssueAction(e));
		issueOptions.getEditIssueButton().setDisable(true);
		
		issueOptions.getRemoveIssueButton().setOnAction(e->removeIssueAction(e));
		issueOptions.getRemoveIssueButton().setDisable(true);
		
		issueOptions.getSearchBarTextField().setOnAction(e->searchIssueAction(e));
	}
	
	public void constructIssueTable() {
		issueTable = new IssueTable(container.getPrefWidth(), container.getPrefHeight(), this.issues);
		issueTable.getTable().setOnMouseClicked(e->issueTableClickAction(e));
	}
	private void setCellsUnselected() {
		selectedRow = null;
		issueOptions.getRemoveIssueButton().setDisable(true);
		issueOptions.getEditIssueButton().setDisable(true);
	}
	public void issueTableClickAction(MouseEvent e) {
		selectedRow = (IssueCell) issueTable.getTable().getSelectionModel().getSelectedItem();
		if (selectedRow != null){
			issueOptions.getEditIssueButton().setDisable(false);
			issueOptions.getRemoveIssueButton().setDisable(false);
		}
	}
	
	public void newIssueAction(ActionEvent e) {
		
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(currentStage);
		
		IssueBox newIssueBox = new IssueBox(null, dialog);
		newIssueBox.show();
		
		dialog.addEventHandler(IssueBox.SaveDataEvent.SAVE_PRESSED, event -> newIssueSubmitted(event, newIssueBox.getIssue()));
		setCellsUnselected();
	}

	public void newIssueSubmitted(Event e, Issue issue) {
		issues.removeIf(n -> (n.getID().equals(issue.getID())));
		issues.add(issue);
		container.getChildren().remove(issueTable.getTable());
		constructIssueTable();
		container.getChildren().add(issueTable.getTable());
	}
	
	
	public void editIssueAction(ActionEvent e) {
		Issue issue = selectedRow.getIssue();
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(currentStage);
		
		IssueBox newIssueBox = new IssueBox(issue, dialog);
		newIssueBox.show();
		
		dialog.addEventHandler(IssueBox.SaveDataEvent.SAVE_PRESSED, event -> newIssueSubmitted(event, newIssueBox.getIssue()));
		setCellsUnselected();
	}
	
	public void removeIssueAction(ActionEvent e) {
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "Remove Selected Issue?");
		//https://stackoverflow.com/questions/44101426/javafx-alert-box-on-button-click/44101484
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get()==ButtonType.OK) {
			issueTable.removeIssueFromRow(selectedRow);
		}
		setCellsUnselected();
	}
	
	public void searchIssueAction(ActionEvent e) {
		//TODO
	}
	
	/**
	 * @return the container
	 */
	public VBox getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(VBox container) {
		this.container = container;
	}

	/**
	 * @return the issueOptions
	 */
	public IssueOptionBar getIssueOptions() {
		return issueOptions;
	}

	/**
	 * @param issueOptions the issueOptions to set
	 */
	public void setIssueOptions(IssueOptionBar issueOptions) {
		this.issueOptions = issueOptions;
	}

	/**
	 * @return the issueTable
	 */
	public IssueTable getIssueTable() {
		return issueTable;
	}

	/**
	 * @param issueTable the issueTable to set
	 */
	public void setIssueTable(IssueTable issueTable) {
		this.issueTable = issueTable;
	}

	/**
	 * @return the issues
	 */
	public ArrayList<Issue> getIssues() {
		return issues;
	}

	/**
	 * @param issues the issues to set
	 */
	public void setIssues(ArrayList<Issue> issues) {
		this.issues = issues;
	}
}
