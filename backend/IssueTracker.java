package backend;

/**
 * 
 * @author Mingrui Leng
 *
 */
public interface IssueTracker {
	
	public void newProject(String JSON);
	
	public void editProject(String JSON);
	
	public void removeProject(String name);
	
	public void sortProject(String field);
	
	public String serializeJSON();
	
	public void deserializeJSON(String JSON);
	
}
