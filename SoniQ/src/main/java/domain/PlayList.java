package domain;

import java.util.ArrayList;

public class PlayList {

	
	private String naam;
	private int max = 50 ,playListId = 0;
	
	/*
	 *  accesList is an array of user id's that have acces to this playlist
	 *  the first user in the list is the owner of the playlist  
	 */
	private ArrayList<Integer> accesList;
	
	// records is an array of record id's
	private ArrayList<Integer> records;
	
	public PlayList(int userId,String naam){
		accesList = new ArrayList<Integer>();
		records = new ArrayList<Integer>();

		accesList.add((Integer) userId);
		
		setNaam(naam);
	}
	
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public ArrayList<Integer> getAccesList() {
		return accesList;
	}
	public void setAccesList(ArrayList<Integer> accesList) {
		this.accesList = accesList;
	}

	public void addUser(int userId){
		accesList.add((Integer)userId);
	}
	
	public void addRecord(int recordId){
		records.add((Integer)recordId);
	}

	public int getPlayListId() {
		return playListId;
	}

	public void setPlayListId(int playListId) {
		this.playListId = playListId;
	}
	
	//TODO remove methods
	
	
	public String toString(){
		String result = "";
		result += "PlaylistId: " + playListId + " naam: " + naam;
		result += " users with acces: ";
		for (int i = 0; i < accesList.size(); i++) {
			result += " " + accesList.get(i);
		}
		
		result += " records in playlist: ";
		for (int i = 0; i < records.size(); i++) {
			result += " " + records.get(i);
		}
		return result;
	}
	
}
