package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import api.Api;
import api.ApiException;
import api.ApiFactory;
import db.CouchDb;
import db.Database;
import db.DbException;
import db.DbFactory;

public class Facade {

	private DbFactory dbFac;
	private Database database;
	private CouchDb couchDb;
	
	public Facade() throws DbException{
		dbFac = new DbFactory();
		database = dbFac.createDatabase("postgresql");
		
		couchDb = new CouchDb();
	}
	
	/*
	 * 
	 * this method is the heart of the application , it accepts a user input query and
	 * return a list of records that are to be shown on the webpage
	 * 
	 * the list of records consists out of links received from different API calls 
	 * in combination with ?cached? records in our own database (depends on whether or not we
	 * are allowed to cache them)
	 *
	 * 
	 */
	public ArrayList<Record> search(String query) throws DbException{
		
		//deze methode kan beter, but it's late, et je suis tired
		Api api;
		ArrayList<Record> lijst = null;
		
		try {
			
			api = ApiFactory.createApi("soundcloud");
			api.doCall(query);
		
			api = ApiFactory.createApi("bandcamp");
			api.doCall(query);
			
			lijst = couchDb.getRecords(query);

				
			
		} catch (ApiException e) {
			
			throw new DbException(e.getMessage());
			
		}
		
		return lijst;
	}
	
	
	/*
	 * 
	 * Users can add comment to records
	 * 
	 * @input content = what the user has written as comment
	 * 		  userId  = the id of the user that has written the comment
	 * 		  recordId= the id of the record on which the user wants to comment
	 * 
	 */
	public void addComment(String content, String userId, int recordId) throws DbException{
		database.addComment(content, userId, recordId);
	}

	/*
	 * 
	 * not really sure if this method is going to be needed 
	 * (are we going to save full record?)
	 * 
	 */
	public void addRecord(Record record)throws DbException {
		database.addRecord(record);
	}
	
	/*
	 * 
	 * method for adding members pretty straight forward
	 * 
	 */
	public void addMember(String naam, String vnaam, String pw, String email, Date geb_datum)throws DbException{
		database.addMember(naam,vnaam,pw,email,geb_datum);
	}
	
	public Member getMember(String userId)throws DbException{
		return database.getMember(userId);
	}
	
	public Record getRecord(int recordId)throws DbException{
		return database.getRecord(recordId);
	}
		
	/*
	 * 
	 * returns a list of comments written for a record
	 * accepts recordId as input
	 * 
	 */
	public ArrayList<Comment> getComments(int recordId)throws DbException{
		return database.getCommentsForRecord(recordId);
	}
	
	/*
	 * 
	 * returns all the playlists for a user
	 * 
	 */
	public ArrayList<PlayList> getPlayLists(int userId)throws DbException{
		return database.getPlayListsForUser(userId);
	}
	
	public void createPlayList(int userId,String naam)throws DbException{
		database.addPlayList(naam,userId);
	}
	
	
	/*
	 * 
	 * adds an records to the users playlist
	 */
	public void addToPlayList(int recordId, int playListid)throws DbException{
		database.addRecordToPlayList(recordId, playListid);
	}
	
	
	/*
	 * 
	 * to share a playlist, one must enter another username and the playlist id of the playlist he wishes to share
	 * "sharing a playlist" just adds the given user to the playlists acceslist
	 */
	public void sharePlayList(String username, int playListId)throws DbException{
		database.sharePlayList(username, playListId);
	}
	
	public void upvote(int recordId)throws DbException{
		database.upvote(recordId);
	}
	
	public void downvote(int recordId)throws DbException{
		database.downvote(recordId);
	}
	
	public Member login(String user, String pass)throws DbException{
		return database.login(user,pass);
	}
	
	public boolean isUserAuthorized(int userId)throws DbException{
		return database.isUserAuthorized(userId);
	}
}
