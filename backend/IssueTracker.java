package backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * IssueTracker, main container for backend objects.
 * 
 * @author jcharapata
 *
 */
public class IssueTracker implements IssueTrackerInterface {
  private ArrayList<Project> projectList;


  /**
   * Default Constructor
   */
  public IssueTracker() {
    projectList = new ArrayList<Project>();
  }


  /**
   * Creates a new project, adds it to the list of projects
   * 
   * @param name        - the new project's name
   * @param description - the new project's description
   */
  public Project newProject() {
    projectList.add(new Project());
    return projectList.get(projectList.size() - 1);
  }

  /**
   * Returns the projectList for editing and display
   */
  public ArrayList<Project> getProjectList() {
    return this.projectList;
  }


  @Override
  public void newProject(String JSON) {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void editProject(String JSON) {
    // TODO Auto-generated method stub
    
  }


  @Override
  public Project getProject(String name) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void removeProject(String name) {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void sortProject(String field) {
    // TODO Auto-generated method stub
    
  }


  @Override
  public String serializeJSON() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public void deserializeJSON(String JSON) {
    // TODO Auto-generated method stub
    
  }



}
