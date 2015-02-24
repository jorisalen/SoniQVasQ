package domain;

import java.util.Date;

public class Member {

	
	private  String naam, voornaam, password, email;
	//hardcoded, maar normaal genereert db dit
	private String userId = "";
	private Date geb_datum;
	private boolean isAdmin = false;
	
	public Member(String naam, String vnaam, String pw, String email, Date geb_datum){
		setNaam(naam);
		setVoornaam(vnaam);
		setPassword(pw);
		setEmail(email);
		setGeb_datum(geb_datum);
	}
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getGeb_datum() {
		return geb_datum;
	}
	public void setGeb_datum(Date geb_datum) {
		this.geb_datum = geb_datum;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString(){
		return "naam: " + naam + " id: " + userId;
	}

}
