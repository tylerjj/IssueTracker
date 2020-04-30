package backend;

import java.util.ArrayList;
import java.util.Date;

public class Issue implements IssueInterface {
	
	// Took inspiration from the following code snippet: 
	// https://stackoverflow.com/questions/17741721/getting-string-value-from-enum-in-java
	public enum Status {
	    TODO(-1),
	    IN_PROGRESS(0),
	    COMPLETE(1);

	    private final int value;

	    Status(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	    
	}
	
	public enum Priority {
		LOW(-1),
		MEDIUM(0),
		HIGH(1);
		
		private final int value;
		
		Priority(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
	}
	
	private Priority priority;
	private String name;
	private String description;
	private Date deadline;
	private Status status;
	private String ID;
	private ArrayList<String> assignees;
	private Date dateLastUpdated;
	private Date dateCreated;
	private Date dateClosed;
	

	public Issue() {
		// TODO: What is the default id?
		// TODO: What is the default description?
		// TODO: When is the default deadline?
		// TODO: What is the default status?
		// TODO: What is the default priority?
		// TODO: What is the default dateCreated
		// TODO: What is the default dateClosed?
		// TODO: Who are the default assignees?
	}
	
	
	public Issue(String JSON) {
		//TODO: Take in a string representing a JSONObject of an Issue and parse it.
		// Use this information to fill in the values of this Issue object.
		this.dateLastUpdated = new Date();
	}
	
	public Issue(Priority priority, String name, String description, Date deadline, Status status, String ID, ArrayList<String> assignees, Date lastUpdated, Date created, Date closed) {
		this.priority = priority;
		this.name = name;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
		this.ID = ID;
		this.assignees = assignees;
		this.dateLastUpdated = lastUpdated;
		this.dateCreated = created;
		this.dateClosed = closed;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		this.dateLastUpdated = new Date();
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
		this.dateLastUpdated = new Date();
		
	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public void setID(String ID) {
		this.ID = ID;
		this.dateLastUpdated = new Date();
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setStatus(Enum status) {
		this.status = (Status) status;
		this.dateLastUpdated = new Date();
	}

	@Override
	public ArrayList<String> getAssignees() {
		return assignees;
	}
	
	@Override
	public void setAssignees(ArrayList<String> assignees) {
		this.assignees = assignees;
		this.dateLastUpdated = new Date();
	}
	
	@Override
	public void addAssignee(String name) {
		assignees.add(name);
		this.dateLastUpdated = new Date();
		
	}

	@Override
	public void removeAssignee(String name) {
		assignees.remove(name);
		this.dateLastUpdated = new Date();
		
	}

	@Override
	public Priority getPriority() {
		return priority;
	}

	@Override
	public void setPriority(Enum priority) {
		this.priority = (Priority) priority;
		this.dateLastUpdated = new Date();
		
	}

	@Override
	public Date getDeadline() {
		return deadline;
	}

	@Override
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
		this.dateLastUpdated = new Date();
		
	}

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
		this.dateLastUpdated = new Date();
	}

	@Override
	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}

	@Override
	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	@Override
	public Date getDateClosed() {
		return dateClosed;
	}

	@Override
	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}
}
