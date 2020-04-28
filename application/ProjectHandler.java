package application;

import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import backend.Project;
import javafx.stage.Stage;

/**
 * Will handle project related actions
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
  }

  private void constructSidebar() {
    sidebar = new Sidebar();
    sidebar.setProjectNames(projects);
    sidebar.getProjectList().setOnMouseClicked(e->changeIssues());
    sidebar.getNewProjectButton().setOnMouseClicked(e->createNewProject());

  }
  
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
      if(project.getName().equals(projectName)) {
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
    ProjectBox newProjectBox = new ProjectBox();
  }
  
}
