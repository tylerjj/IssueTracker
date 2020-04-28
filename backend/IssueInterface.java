/**
 * Filename: IssueInterface Project: IssueTracker
 */

package backend;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Mingrui Leng
 *
 */
public interface IssueInterface {

	/**
	 * Get the name
	 * 
	 * @return name
	 */
	public String getName();

	/**
	 * Set the name
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Get the description
	 * 
	 * @return description
	 */
	public String getDescription();

	/**
	 * Set the description
	 * 
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * Get the ID
	 * 
	 * @return ID
	 */
	public String getID();

	/**
	 * Set the ID
	 * 
	 * @param ID
	 */
	public void setID(String ID);

	/**
	 * Get the status
	 * 
	 * @return status
	 */
	public Enum getStatus();

	/**
	 * Set the status
	 * 
	 * @param status
	 */
	public void setStatus(Enum status);

	/**
	 * Get the assignees
	 * 
	 * @return assignees
	 */
	public ArrayList<String> getAssignees();

	
	/**
	 * Set the assignees
	 * 
	 * @param assignees
	 */
	public void setAssignees(ArrayList<String> assignees);
	
	
	/**
	 * Add an assignee
	 * 
	 * @param name
	 */
	public void addAssignee(String name);

	/**
	 * Remove an assignee
	 * 
	 * @param name
	 */
	public void removeAssignee(String name);

	/**
	 * Get the Priority
	 * 
	 * @return priority
	 */
	public Enum getPriority();

	/**
	 * Set the priority
	 * 
	 * @param priority
	 */
	public void setPriority(Enum priority);

	/**
	 * Get the deadline
	 * 
	 * @return deadline
	 */
	public Date getDeadline();

	/**
	 * Set the deadline
	 *
	 * @param deadline
	 */
	public void setDeadline(Date deadline);

	/**
	 * Get the date created
	 *
	 * @return dateCreated
	 */
	public Date getDateCreated();

	/**
	 * Set the date created
	 *
	 * @param dateCreated
	 */
	public void setDateCreated(Date dateCreated);

	/**
	 * Get the date last updated
	 *
	 * @return dateLastAccessed
	 */
	public Date getDateLastUpdated();

	/**
	 * Set the date last updated
	 *
	 * @param dateLastUpdated
	 */
	public void setDateLastUpdated(Date dateLastUpdated);

	/**
	 * Get the date closed
	 *
	 * @return dateClosed
	 */
	public Date getDateClosed();

	/**
	 * Set the dateClosed
	 *
	 * @param dateClosed
	 */
	public void setDateClosed(Date dateClosed);

}
