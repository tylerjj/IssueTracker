/**
 * 
 * IssueTable.java created by tyler on Windows 10 PC in aTeam_mockup_IssuesTables
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

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * IssueTable - TODO Describe purpose of this user defined type
 * @author tyler johnston (2020)
 *
 */
public class IssueTable extends ScrollPane {

	private TableView table;
	enum Status {TODO, IN_PROGRESS, COMPLETE};
	
	public static class Issue {
		private Paint priority;
		private String description;
		private String deadline;
		Status status;
		private String ID;
		private String assignee;
		private String lastUpdated;
		
		public Issue(Paint priority, String description, String deadline, int status, String ID, String assignee, String lastUpdated){
			this.priority = priority;
			this.description = description;
			this.deadline = deadline;
			this.ID = ID;
			if (status < 0) {
				this.status = Status.TODO;
			} else if (status > 0) {
				this.status = Status.COMPLETE;
			} else {
				this.status = Status.IN_PROGRESS;
			}
			this.assignee = assignee;
			this.lastUpdated = lastUpdated;
		}
		
		public Rectangle getPriority() {
			//https://www.tutorialspoint.com/javafx/2dshapes_rectangle.htm
			Rectangle priorityColor = new Rectangle();
			priorityColor.setWidth(10);
			priorityColor.setHeight(10);
			priorityColor.setFill(priority);
			return priorityColor;
		}
		
		public Label getDescription() {
			Label descriptionLabel = new Label(description);
			return descriptionLabel;
		}
		
		public Label getDeadline() {
			Label deadlineLabel = new Label(deadline);
			return deadlineLabel;
		}
		
		public Label getStatus() {
			Label statusLabel = new Label(status.toString());
			return statusLabel;
		}
		public Label getID() {
			Label idLabel = new Label(ID);
			return idLabel;
		}
		
		public Label getAssignee() {
			Label assigneeLabel = new Label(assignee);
			return assigneeLabel;
		}
		
		public Label getLastUpdated() {
			Label lastUpdatedLabel = new Label(lastUpdated);
			return lastUpdatedLabel;
		}
	}
	
	IssueTable(double prefWidth, double prefHeight, ArrayList<Issue> issues){
		
		table = new TableView();
		
		table.setEditable(false);
		table.setPrefHeight(prefHeight);
		table.setPrefWidth(prefWidth);
		//this.setPrefHeight(prefHeight);
		//this.setPrefWidth(prefWidth);
		TableColumn<Rectangle, Issue> priorityColumn = new TableColumn<>("Priority");
		priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
	
		TableColumn<Label, Issue> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		TableColumn<Label, Issue> deadlineColumn = new TableColumn("Deadline");
		deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		
		TableColumn<Label, Issue> statusColumn = new TableColumn("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
		
		TableColumn<Label, Issue> idColumn = new TableColumn("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		
		TableColumn<Label, Issue> assigneeColumn = new TableColumn("Assignee");
		assigneeColumn.setCellValueFactory(new PropertyValueFactory<>("assignee"));
		
		TableColumn<Label, Issue> lastUpdatedColumn = new TableColumn("Last Updated");
		lastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));
	
		table.getColumns().addAll(priorityColumn, descriptionColumn, deadlineColumn, statusColumn, idColumn, assigneeColumn, lastUpdatedColumn);
	
		fillTable(issues);
		//https://stackoverflow.com/questions/26713162/javafx-disable-horizontal-scrollbar-of-tableview
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//https://stackoverflow.com/questions/26365309/javafx-scrollpane-method-getchildren-is-not-visible
		this.setContent(table);
		
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	}

	public void putIssueToRow(Issue issue) {
		table.getItems().add(issue);
	}
	
	public void removeIssueFromRow(Issue issue) {
		table.getItems().remove(issue);
	}
	
	private void fillTable(ArrayList<Issue> issues) {
		for (int i = 0; i < issues.size(); i++) {
			putIssueToRow(issues.get(i));
		}
	}
	
	public TableView getTable() {
		return table;
	}
}
