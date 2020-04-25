/**
 * 
 * IssueTable.java created by tyler on Windows 10 PC in
 * aTeam_mockup_IssuesTables
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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * IssueTable - TODO Describe purpose of this user defined type
 * 
 * @author tyler johnston (2020)
 *
 */
public class IssueTable extends TableView {

	private TableView table;

	public static class IssueCell {

		Issue.Priority priority;
		private String name;
		private String description;
		private Date deadline;
		Issue.Status status;
		private String ID;
		private ArrayList<String> assignees;
		private Date dateLastUpdated;
		private Date dateCreated;
		private Date dateClosed;
		
		public IssueCell(Issue issue) {
			
			
			priority = issue.getPriority();
			name = issue.getName();
			description = issue.getDescription();
			deadline = issue.getDeadline();
			status = issue.getStatus();
			ID = issue.getID();
			assignees = issue.getAssignees();
			dateLastUpdated = issue.getDateLastUpdated();
			dateCreated = issue.getDateCreated();
			dateClosed = issue.getDateClosed();
			
		}

		public Rectangle getPriority() {
			
			Paint priorityColor;
			
			// TODO: I think this could probably be cleaned up with a switch.
			if (priority.toString()=="LOW") {
				priorityColor = Color.GREEN;
			} else if (priority.toString()=="MEDIUM") {
				priorityColor = Color.YELLOW;
			} else if (priority.toString()=="HIGH") {
				priorityColor = Color.RED;
			} else {
				priorityColor = Color.BLACK;
			} 
			
			// https://www.tutorialspoint.com/javafx/2dshapes_rectangle.htm
			Rectangle priorityColorBox = new Rectangle();
			priorityColorBox.setWidth(10);
			priorityColorBox.setHeight(10);
			priorityColorBox.setFill(priorityColor);
			return priorityColorBox;
		}

		public Label getName() {
			Label nameLabel = new Label(name);
			return nameLabel;
		}
		
		public Label getDescription() {
			Label descriptionLabel = new Label(description);
			return descriptionLabel;
		}

		public Label getDeadline() {
			String deadlineString = deadline.toString();
			Label deadlineLabel = new Label(deadlineString);
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

		public Label getAssignees() {
			String assigneesString = new String();
			for (int i = 0; i < assignees.size();i++) {
				assigneesString = assigneesString + assignees.get(i);
				if (i+1 < assignees.size()) {
					assigneesString = assigneesString + "\n";
				}
			}
			Label assigneesLabel = new Label(assigneesString);
			return assigneesLabel;
		}

		public Label getDateLastUpdated() {
			String dateLastUpdatedString = dateLastUpdated.toString();
			Label dateLastUpdatedLabel = new Label(dateLastUpdatedString);
			return dateLastUpdatedLabel;
		}
		
		public Label getDateCreated() {
			String dateCreatedString = dateCreated.toString();
			Label dateCreatedLabel = new Label(dateCreatedString);
			return dateCreatedLabel;
		}
		
		public Label getDateClosed() {
			Label dateClosedLabel;
			if (dateClosed == null) {
				dateClosedLabel = new Label("N/A");
			} else {
				String dateClosedString = new String(dateClosed.toString());
				dateClosedLabel = new Label(dateClosedString);
			}
			return dateClosedLabel;
		}
	}

	IssueTable(double prefWidth, double prefHeight, ArrayList<Issue> issues) {

		table = new TableView();

		table.setEditable(false);
		// table.setPrefHeight(prefHeight);
		// table.setPrefWidth(prefWidth);

		// this.setPrefHeight(prefHeight);
		// this.setPrefWidth(prefWidth);

		ArrayList<IssueCell> issueCells = new ArrayList<IssueCell>();
		for (Issue issue : issues ) {
			issueCells.add(new IssueCell(issue));
		}
		
		TableColumn<Rectangle, IssueCell> priorityColumn = new TableColumn<>(
				"Priority");
		priorityColumn
				.setCellValueFactory(new PropertyValueFactory<>("priority"));

		TableColumn<Label, IssueCell> nameColumn = new TableColumn<>(
				"Name");
		nameColumn
				.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Label, IssueCell> descriptionColumn = new TableColumn<>(
				"Description");
		descriptionColumn
				.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Label, IssueCell> deadlineColumn = new TableColumn("Deadline");
		deadlineColumn
				.setCellValueFactory(new PropertyValueFactory<>("deadline"));

		TableColumn<Label, IssueCell> statusColumn = new TableColumn("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));

		TableColumn<Label, IssueCell> idColumn = new TableColumn("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Label, IssueCell> assigneesColumn = new TableColumn("Assignees");
		assigneesColumn
				.setCellValueFactory(new PropertyValueFactory<>("assignees"));

		TableColumn<Label, IssueCell> dateLastUpdatedColumn = new TableColumn(
				"Last Updated");
		dateLastUpdatedColumn
				.setCellValueFactory(new PropertyValueFactory<>("dateLastUpdated"));

		TableColumn<Label, IssueCell> dateCreatedColumn = new TableColumn(
				"Date Created");
		dateCreatedColumn
				.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
		
		TableColumn<Label, IssueCell> dateClosedColumn = new TableColumn(
				"Date Closed");
		dateClosedColumn
				.setCellValueFactory(new PropertyValueFactory<>("dateClosed"));
		
		
		table.getColumns().addAll(priorityColumn, nameColumn, descriptionColumn,
				deadlineColumn, statusColumn, idColumn, assigneesColumn,
				dateLastUpdatedColumn, dateCreatedColumn, dateClosedColumn);

		fillTable(issueCells);
		// https://stackoverflow.com/questions/26713162/javafx-disable-horizontal-scrollbar-of-tableview
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	public void putIssueToRow(IssueCell issue) {
		table.getItems().add(issue);
	}

	public void putIssueToRow(Issue issue) {
		table.getItems().add(new IssueCell(issue));
	}
	
	public void removeIssueFromRow(IssueCell issue) {
		table.getItems().remove(issue);
	}

	public void removeIssueFromRow(Issue issue) {
		table.getItems().remove(new IssueCell(issue));
	}
	
	private void fillTable(ArrayList<IssueCell> issues) {
		for (int i = 0; i < issues.size(); i++) {
			putIssueToRow(issues.get(i));
		}
	}

	public TableView getTable() {
		return table;
	}
}
