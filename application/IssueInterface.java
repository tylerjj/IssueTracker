/**
 * Filename: IssueInterface
 * Project: IssueTracker
 */

package application;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Mingrui Leng
 *
 */
public interface IssueInterface {

	/**
	 * Create a new Issue object with a name and description.
	 * 
	 * @param name        name of the Issue
	 * @param description description of the Issue
	 */
	public void Issue(String name, String description);

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
	public int getPriority();

	/**
	 * Set the priority
	 * 
	 * @param priority
	 */
	public void setPriority(int priority);

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
	 * Get the date last accessed
	 *
	 * @return dateLastAccessed
	 */
	public Date getDateLastAccessed();

	/**
	 * Set the date last accessed
	 *
	 * @param dateLastAccessed
	 */
	public void setDateLastAccessed(Date dateLastAccessed);

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
