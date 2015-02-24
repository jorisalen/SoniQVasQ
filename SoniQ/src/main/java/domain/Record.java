package domain;

import java.io.Serializable;

public class Record implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4814096485912006171L;
	private long recordId; // id of string?
	private String link,title;
	private int upvotes = 0,downvotes = 0,duration;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Record(String link){
		setLink(link);
	}
	
	public void upvote(){
		upvotes++;
	}
	
	public void downvote(){
		downvotes++;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long id) {
		this.recordId = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}
	
	@Override
	public String toString(){
		return "recordId: " + recordId + " link: " + link + " upvotes: " + upvotes + " downvotes: " + downvotes;
	}
	
}
