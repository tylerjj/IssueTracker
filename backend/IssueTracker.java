package backend;

import java.util.Date;
import java.util.HashMap;

public class IssueTracker {
  private HashMap<Integer,Project> projectList;
  static int ID; //will be assigned to each project
  
  /**
   * Default Constructor
   */
  public IssueTracker() {
  projectList = new HashMap<Integer, Project>();
  }
  
  
  /**
   * Creates a new project, adds it to the list of projects
   * @param name - the new project's name
   * @param description - the new project's description
   */
  public void newProject(String name, String description) {
    projectList.put(ID, new Project());
    projectList.get(ID).setName(name);
    projectList.get(ID).setDescription(name);
    projectList.get(ID).setDateCreated(new Date()); //This will put the current date
    ID++;
  }
  
  
  /**
   * Return the project object from the project map specified by the ID
   * @param ID - the ID of the project
   * @return the project with the specified ID
   */
  public Project getProject(int ID) {
    return projectList.get(ID);
  } 
  
  
  
}
