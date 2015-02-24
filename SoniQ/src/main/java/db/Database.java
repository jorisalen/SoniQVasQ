package db;

import java.util.ArrayList;
import java.util.Date;

import domain.Comment;
import domain.Member;
import domain.PlayList;
import domain.Record;

public interface Database {

	
	public void addMember(String naam, String vnaam, String pw, String email, Date geb_datum) throws DbException ;
	public void addComment(String content, String userId, int recordId) throws DbException;
	public void addRecord(Record record) throws DbException;
	
	
	public Member getMember(String id) throws DbException;
	public Comment getComment(int id) throws DbException;
	public Record getRecord(int id) throws DbException;
	
	public void addPlayList(String userId,int playlistId) throws DbException;
	public void sharePlayList(String username, int playListId) throws DbException;
	
	public ArrayList<PlayList> getPlayListsForUser(int userId) throws DbException;

	public ArrayList<Comment> getCommentsForRecord(int recordId) throws DbException;
	public void addRecordToPlayList(int recordId, int playlistId) throws DbException;
	public ArrayList<Record> getRecords() throws DbException;
	public Member login(String email, String pass) throws DbException;
	public boolean isUserAuthorized(int userId) throws DbException;
	public void upvote(int recordId) throws DbException;
	public void downvote(int recordId) throws DbException;

	// geen zin in de rest te doen
	
	
}
