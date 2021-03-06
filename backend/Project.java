package backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import backend.Issue.Priority;
import backend.Issue.Status;

/**
 * 
 * @author Mingrui Leng
 *
 */
public class Project implements ProjectInterface {

  private ArrayList<Issue> issueList;
  private String name;
  private String description;
  private Date deadline;
  private Date dateCreated;
  private Date dateLastAccessed;
  private Date dateClosed;
  private Status open;
  
  
  
  public enum Status {
    OPEN(false),
    CLOSED(true);

    private final boolean value;

    private Status(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
    
}
	public Project() {
		name = new String();
		description = new String();
		deadline = new Date();
		dateCreated = new Date();
		dateLastAccessed = new Date();
		dateClosed = null;
		issueList = new ArrayList<Issue>();
		open = Status.OPEN;
	}

	public Project(String JSON) {
		// TODO: add implementation
	}

	public Project(ArrayList<Issue> issueList, String name, String description, Date deadline, Date dateCreated, Date dateLastAccessed, Date dateClosed, Status open) {
		this.issueList = issueList;
		this.name = name;
		this.description = description;
		this.deadline = deadline;
		this.dateCreated = dateCreated;
		this.dateLastAccessed = dateLastAccessed;
		this.dateClosed = dateClosed;
		this.open = open;
	}

	@Override
	public void newIssue(String JSON) {
		Issue newIssue = new Issue(JSON);
		issueList.add(newIssue);
	}

	@Override
	public void editIssue(String JSON, String ID) {
		for (Issue issue : issueList) {
			if (issue.getID().equals(ID)) {
				issue = new Issue(JSON);
			}
		}
	}

	@Override
	public void removeIssue(String issueID) {
		for (Issue issue : issueList) {
			if (issue.getID().equals(issueID)) {
				issueList.remove(issue);
			}
		}
	}

	@Override
	public void sortIssues(String field) {

		switch (field) {
		case "priority":
			issueList.sort(Comparator.comparing(Issue::getPriority));
			break;
		case "name":
			issueList.sort(Comparator.comparing(Issue::getName));
			break;
		case "description":
			issueList.sort(Comparator.comparing(Issue::getDescription));
			break;
		case "deadline":
			issueList.sort(Comparator.comparing(Issue::getDeadline));
			break;
		case "status":
			issueList.sort(Comparator.comparing(Issue::getStatus));
			break;
		case "ID":
			issueList.sort(Comparator.comparing(Issue::getID));
			break;
		case "assignees":
			// TODO: assignees is not comparable
			break;
		case "dateLastUpdated":
			issueList.sort(Comparator.comparing(Issue::getDateLastUpdated));
			break;
		case "dateCreated":
			issueList.sort(Comparator.comparing(Issue::getDateCreated));
			break;
		case "dateClosed":
			issueList.sort(Comparator.comparing(Issue::getDateClosed));
			break;
		}

	}

	@Override
	public ArrayList<Issue> getIssueList() {
		return issueList;
	}
	
    @Override
	public void setIssueList(ArrayList<Issue> issueList) {
	  this.issueList = issueList;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
	  this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String description) {
	  this.description = description;
	}

	@Override
	public Date getDeadline() {
		return deadline;
	}

	@Override
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public Date getDateLastAccessed() {
		return dateLastAccessed;
	}

	@Override
	public void setDateLastAccessed(Date dateLastAccessed) {
		this.dateLastAccessed = dateLastAccessed;
	}

	@Override
	public Date getDateClosed() {
		return dateClosed;
	}

	@Override
	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	@Override
	public Status getOpenStatus() {
		return open;
	}

	@Override
	public void setOpenStatus(Status open) {
		this.open = open;
	}

	

}
