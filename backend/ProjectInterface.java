package backend;

import java.util.ArrayList;
import java.util.Date;

/**
 * Filename:ProjectInterface Project: IssueTracker
 *
 * @author jcharapata
 *
 */
public interface ProjectInterface {

    /**
     * Creates a new IssueObject with a JSON object
     *
     * @param JSON:        JSON object to create the issue
     */
    public void newIssue(String JSON);

    /**
     * Edits the IssueObject with the IssueID given
     *
     * @param JSON:        JSON object of the edited issue
     * @param ID:          issueID of the issue being edited
     */
    public void editIssue(String JSON, String ID);

    /**
     * Removes an issue object from the project
     *
     * @param issueID: issueID of the issue to be removed
     */
    public void removeIssue(String ID);

    /**
     * Sorts projects in the issue list by the specified field TODO: Add supported
     * fields to sort by
     *
     * @param field the field to sort issues by
     */
    public void sortIssues(String field);

    /**
     * getter for the issueList
     *
     * @return the stored list of issues in this project
     */
    public ArrayList<Issue> getIssueList();

    
    /**
     * Setter for issueList
     * @param issueList
     */
    public void setIssueList(ArrayList<Issue> issueList);
    
    /**
     * Getter for project name
     *
     * @return name of this project object
     */
    public String getName();

    /**
     * Setter for project name
     * 
     */
    public void setName(String name);
    /**
     * Getter for project description
     *
     * @return description of this project object
     */
    public String getDescription();
    
    /**
     * Setter for Description
     */
    public void setDescription(String description);

    /**
     * Getter for project deadline Date
     *
     * @return deadline for project
     */
    public Date getDeadline();

    /**
     * Setter for project deadline Date
     *
     * @param deadline for project
     */
    public void setDeadline(Date deadline);

    /**
     * Getter for project Date created
     *
     * @return creation date for project
     */
    public Date getDateCreated();

    /**
     * Setter for project DateCreated Date
     *
     * @param DateCreated for project
     */
    public void setDateCreated(Date dateCreated);

    /**
     * Getter for project dateLastAccessed
     *
     * @return dateLastAccessed for project
     */
    public Date getDateLastAccessed();

    /**
     * Setter for project dateLastAccessed Date
     *
     * @param dateLastAccessed for project
     */
    public void setDateLastAccessed(Date dateLastAccessed);

    /**
     * Getter for project dateClosed
     *
     * @return dateClosed for project
     */
    public Date getDateClosed();

    /**
     * Setter for project dateLastAccessed Date
     *
     * @param dateClosed for project
     */
    public void setDateClosed(Date dateClosed);

    /**
     * Getter for the open boolean.
     *
     * @return open - current status of the project
     */
    public Project.Status getOpenStatus();

    /**
     * Setter for the open status
     * 
     * @param open - the open status, true if project is open
     */
    public void setOpenStatus(Project.Status open);
}