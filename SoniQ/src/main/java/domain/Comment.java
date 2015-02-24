package domain;

import java.sql.Date;


public class Comment {

	private String content;
	private String userId;
	private int commentId;
	private int recordId;
	private String naam;
	private Date timestamp;
	
	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	//private int upvotes,downvotes;
		
	public Comment(String content,String userId,int recordId){
		setContent(content);
		setUserId(userId);
		setRecordId(recordId);
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String toString(){
		return  "reactie: " + content  + " userId: " + userId + " commentId: " + commentId + "recordId: " + recordId;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
}
