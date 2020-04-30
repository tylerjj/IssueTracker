package application;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.event.ChangeListener;
import backend.Project;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Handles project related actions such as adding a project, switching
 * issueLists, etc.
 * 
 * @author jcharapata
 *
 */
public class ProjectHandler {
	Sidebar sidebar;
	ArrayList<Project> projects;
	ProjectDataView projectDataView;
	Stage currentStage;

	/**
	 * Constructor for project handler. Takes issueHandler as this will affect
	 * issues as well as projects.
	 * 
	 * @param projects
	 * @param currentStage
	 * @param issueHandler
	 */
	public ProjectHandler(ArrayList<Project> projects, Stage currentStage) {
		this.projects = projects;
		this.currentStage = currentStage;
		if (projects.size() > 0) {
			this.projectDataView = new ProjectDataView(projects.get(0),
					this.currentStage);
			this.projectDataView.getRemoveButton()
					.setOnAction(e -> removeProjectButtonAction());
			this.projectDataView.getEditButton().setOnAction(e->editProjectButtonAction());
		} else {
			this.projectDataView = new ProjectDataView(null, currentStage);
		}

		constructSidebar();

		sidebar.setProjectNames(projects);
	}

	/**
	 * Caller for the sidebar class, setup the button and list events for it.
	 */
	private void constructSidebar() {
		sidebar = new Sidebar();
		sidebar.setProjectNames(projects);
		sidebar.getProjectList().setOnMouseClicked(e -> changeProjects());

		sidebar.getNewProjectButton()
				.setOnMouseClicked(e -> createNewProject());

	}

	private void updateProjectDataView(Project p) {
		projectDataView.setProject(p);
		projectDataView.getRemoveButton()
				.setOnAction(e -> removeProjectButtonAction());
		projectDataView.getEditButton().setOnAction(e->editProjectButtonAction());

	}

	/**
	 * Updates the ProjectDataView when a project is selected on the sidebar.
	 */
	private void changeProjects() {
		Project p = searchProject(
				sidebar.getProjectList().getSelectionModel().getSelectedItem());
		updateProjectDataView(p);
	}

	/**
	 * fetches project from the project list. returns null and breaks if it is
	 * broken.
	 * 
	 * @param projectName
	 * @return - project with the given projectName
	 */
	private Project searchProject(String projectName) {
		for (Project project : projects) {
			if (project.getName() != null
					&& project.getName().equals(projectName)) {
				return project;
			}
		}
		return null;
	}

	/**
	 * Launches new project box, creates a new project to be edited
	 */
	private void createNewProject() 
	{
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(currentStage);
		
		ProjectBox newProjectBox = new ProjectBox(null, sidebar,dialog);
		newProjectBox.show();
		
		dialog.addEventHandler(ProjectBox.SaveDataEvent.SAVE_PRESSED, event -> saveDataAction(newProjectBox));
	}

	/**
	 * Saves data upon a saved project from the ProjectBox
	 * 
	 * @param newProjectBox
	 */
	private void saveDataAction(ProjectBox newProjectBox) {
		// Ensures the project is only saved if the save/submit button is
		// pressed.
		if (newProjectBox.getProjectSaved()) {
			
			Project p = newProjectBox.getProject();
			
			if (sidebar.getProjectList().getItems().contains(p.getName())){
				int index = sidebar.getProjectList().getItems().indexOf(p.getName());
				projects.remove(index);
				projects.add(index, p);
				//projects.removeIf(project->project.getName().equals(p.getName()));
			}	else {
				
				// Stores the newly saved project in our list of projects.
				projects.add(p);
			}
			
			// Sets the project names to be displayed in our sidebar.
			sidebar.setProjectNames(projects);
			
			// Sets the ProjectDataView to the saved project. 
			updateProjectDataView(p);
		}
	}

	/**
	 * This helper is called whenever Remove Project is clicked.
	 */
	private void removeProjectButtonAction() {

		// Throw a confirmation alert to verify that the user wants to remove
		// this project.
		Alert alert = new Alert(AlertType.CONFIRMATION,
				"Remove this project from the IssueTracker?");

		// https://stackoverflow.com/questions/44101426/javafx-alert-box-on-button-click/44101484

		// Wait for and get the result from the alert.
		Optional<ButtonType> result = alert.showAndWait();

		// If the user confirms they want to remove the project:
		if (result.get() == ButtonType.OK) {

			// Remove the project from the list of projects
			projects.removeIf(project -> project.getName()
					.equals(projectDataView.getProject().getName()));

			// Update the sidebar.
			sidebar.setProjectNames(projects);

			// Update the ProjectDataView
			if (projects.size() == 0) {
				projectDataView.setProject(null);
			} else {
				projectDataView.setProject(projects.get(0));
			}
		}
	}

	private void editProjectButtonAction() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(currentStage);
		
		ProjectBox newProjectBox = new ProjectBox(projectDataView.getProject(), sidebar,dialog);
		newProjectBox.show();
		
		dialog.addEventHandler(ProjectBox.SaveDataEvent.SAVE_PRESSED, event -> saveDataAction(newProjectBox));
	}
}
