package db;

import java.util.ArrayList;
import java.util.Date;

import domain.Comment;
import domain.Member;
import domain.PlayList;
import domain.Record;

public class GeheugenDb implements Database{

	private ArrayList<Record> records;
	private ArrayList<Comment> comments;
	private ArrayList<Member> members;
	private ArrayList<PlayList> playlist;
	
	public GeheugenDb(){
		records = new  ArrayList<Record>();
		comments = new  ArrayList<Comment>();
		members= new  ArrayList<Member>();
		playlist = new  ArrayList<PlayList>();
		Record r = new Record("https://jeweeetzelleeeeuf.be");
		r.upvote();
		records.add(r);
		records.add(new Record("https://soundcloud.com/baaskat-beats/baaskat-x-joagymshoe-een-uur-in-de-morgen-stbb"));
		records.add(new Record("https://www.youtube.com/watch?v=BpqOWO6ctsg"));
		records.add(new Record("http://onedirectionusa.bandcamp.com/"));
		
		comments.add(new Comment("dikke plaat","1",0));
		comments.add(new Comment("om van te tsjoeperen","1",0));

		Member m = new Member("Vd", "Wouter", "password", "wikke404@msn.com", new Date());
		m.setUserId("100");
		
		PlayList plist = new PlayList(0,"boeber baaskat & vasQ");
		PlayList plist2 = new PlayList(0,"Kabout plop, zwaaien met die zwans");

		plist.addRecord(0);
		
		playlist.add(plist);
		playlist.add(plist2);

		members.add(m);
	}

	public void addPlayList(int userId,String naam){
		PlayList pl = new PlayList(userId,naam);
		pl.setPlayListId(playlist.size());
		playlist.add(pl);
	}
	
	public void addRecord(Record record){
		records.add(record);
	}
	
	public void addMember(String naam, String vnaam, String pw, String email, Date geb_datum){
		Member mem = new Member(naam,vnaam,pw,email,geb_datum);
		mem.setUserId("" + members.size());
		members.add(mem);
	}
	
	public void addComment(String content, String userId, int recordId) {
		comments.add(new Comment(content, userId,recordId));
		// als comment toevevoegd word, current time genereren (doen in db)
	}
	

	public Comment getComment(int commentId){
		for(int i = 0 ; i < comments.size(); i ++ ){
			if(comments.get(i).getCommentId() == commentId){
				return comments.get(i);
			}
		}
		return null;
	}
	
	public Member getMember(String userId){
		for(int i = 0 ; i < members.size(); i ++ ){
			if(members.get(i).getUserId().equals(userId)){
				return members.get(i);
			}
		}
		return null;
	}
	
	public Record getRecord(int recordId){
		for(int i = 0 ; i < records.size(); i ++ ){
			if(records.get(i).getRecordId() == recordId){
				return records.get(i);
			}
		}
		return null;
	}

	public ArrayList<Comment> getCommentsForRecord(int recordId) {
		ArrayList<Comment> com = new ArrayList<Comment>();
		
		for(int i = 0 ; i < comments.size(); i ++){
			if(comments.get(i).getRecordId() == recordId){
				com.add(comments.get(i));
			}
		}
		
		return com;
	}

	public void addRecordToPlayList(int recordId, int playlistId) {

		for (int i = 0; i < playlist.size(); i++) {
			if(playlist.get(i).getPlayListId() == playlistId){
				playlist.get(i).addRecord(recordId);
			}
		}
	}

	public ArrayList<PlayList> getPlayListsForUser(int userId) {
		
		ArrayList<PlayList> list = new ArrayList<PlayList>();
		
		for (int i = 0; i < playlist.size(); i++) {
			
			if( playlist.get(i).getAccesList().contains((Integer) userId)){
				list.add(playlist.get(i));
			}
		}
		return list;
	}

	public ArrayList<Record> getRecords() {
		return records;
	}

	public Member login(String email, String pass) {
		for (int i = 0; i < members.size(); i++) {
			if(members.get(i).getEmail().equals(email) && members.get(i).getPassword().equals(pass)){
				return members.get(i);
			}
		}
		return null;
	}

	public boolean isUserAuthorized(String userId) {
		return getMember(userId).isAdmin();
	}

	public void upvote(int recordId) {
		getRecord(recordId).upvote();
		
	}

	public void downvote(int recordId) {
		getRecord(recordId).downvote();
	}
	
	private int getUserId(String name){
//		for (int i = 0; i < members.size(); i++) {
//			if(members.get(i).getNaam().equals(name)){
//				return members.get(i).getUserId();
//			}
//		}
		return 0;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see db.Database#sharePlayList(java.lang.String, int)
	 * 
	 * first get the acces list for a certain playlist
	 * then get the right user id because the frontend gives a userName String
	 */
	public void sharePlayList(String username, int playListId) {
		for (int i = 0; i < playlist.size(); i++) {
			if(playlist.get(i).getPlayListId() == playListId){
				playlist.get(i).getAccesList().add(getUserId(username));
			}
		}
		
	}

	public boolean isUserAuthorized(int userId) throws DbException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPlayList(String userId, int playlistId) throws DbException {
		// TODO Auto-generated method stub
		
	}




	
	
	
}
