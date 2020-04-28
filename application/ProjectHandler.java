package application;

import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import backend.Project;
import javafx.stage.Stage;

/**
 * Handles project related actions such as adding a project, switching issueLists, etc.
 * @author jcharapata
 *
 */
public class ProjectHandler {
  Sidebar sidebar;
  ArrayList<Project> projects;
  IssueHandler issueHandler;
  Stage currentStage;

  /**
   * Constructor for project handler. Takes issueHandler as this will affect issues as well as projects.
   * @param projects
   * @param currentStage
   * @param issueHandler
   */
  public ProjectHandler(ArrayList<Project> projects, Stage currentStage, IssueHandler issueHandler) {
    this.projects = projects;
    this.currentStage = currentStage;
    this.issueHandler = issueHandler;
    constructSidebar();
    sidebar.setProjectNames(projects);
  }

  /**
   * Caller for the sidebar class, setup the button and list events for it.
   */
  private void constructSidebar() {
    sidebar = new Sidebar();
    sidebar.getProjectList().setOnMouseClicked(e->changeIssues());
    sidebar.getNewProjectButton().setOnMouseClicked(e->createNewProject());

  }
  
  /**
   * Refreshes issues in the issueTable upon changing projects
   */
  private void changeIssues() {
    System.out.println(sidebar.getProjectList().getSelectionModel().getSelectedItem());
    issueHandler.setIssues(searchProject(sidebar.getProjectList().getSelectionModel().getSelectedItem()).getIssueList());
    issueHandler.respringIssueTable();
  }
  
  /**
   * fetches project from the project list. returns null and breaks if it is broken.
   * @param projectName
   * @return - project with the given projectName
   */
  private Project searchProject(String projectName) {
    for(Project project: projects) {
      if(project.getName() != null && project.getName().equals(projectName)) {
        return project;
      }
    }
    return null;
  }
  
  /**
   * Launches new project box, creates a new project to be edited
   */
  private void createNewProject() {
    Project newProject = new Project();
    ProjectBox newProjectBox = new ProjectBox(null, sidebar);
    
    newProjectBox.getBoxStage().setOnCloseRequest(e->saveDataAction(newProjectBox));
    newProjectBox.getBoxStage().setOnHidden(e->saveDataAction(newProjectBox));
    
    
  }
  
  /**
   * Saves data upon a saved project from the ProjectBox
   * @param newProjectBox
   */
  private void saveDataAction(ProjectBox newProjectBox) {
    //Ensures nothing gets changed if Box is canceled or 
    if(newProjectBox.getProjectSaved()) {
      projects.add(newProjectBox.getProject());
      sidebar.setProjectNames(projects);
    }
  }
  
}
