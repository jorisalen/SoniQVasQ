package api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.lightcouch.CouchDbClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SoundcloudApi extends ApiCaller implements Api{
	
	private CouchDbClient dbClient1 = new CouchDbClient("couchdb.properties");
	
	public SoundcloudApi(){
		super();
	}

	public void doCall(String query) throws ApiException {
		String url,response;
		
		try {
			url = "https://api.soundcloud.com/tracks.json?q="
					+ URLEncoder.encode(query, "UTF-8")
					+ "&client_id=6371e86ce0b447f34f8ed56e5c2a7832";
		} catch (UnsupportedEncodingException e) {
			throw new ApiException(e.getMessage());
		}
		 try {
			response = doGet(url);
		} catch (IOException e) {
			throw new ApiException(e.getMessage());
		}
		 /*
		  * 
		  * this must be here because the python script for bandcamps als does the call and writes to the database
		  * so we are forced to mimic that behaviour here
		  */
		 
		 saveResponse(response);
	}
	public void getPlaylistSoundcloud() throws ApiException {
		String url,response;
		
		url = "http://api.soundcloud.com/users/me/playlists.json?oauth_token=1-71896-774845-e9a034a383d03e29f";
		 try {
			response = doGet(url);
		} catch (IOException e) {
			throw new ApiException(e.getMessage());
		}
		 /*
		  * 
		  * this must be here because the python script for bandcamps als does the call and writes to the database
		  * so we are forced to mimic that behaviour here
		  */
		 JsonParser jsonParser = new JsonParser();
			JsonElement jo = jsonParser.parse(response);
			JsonArray ja =  (JsonArray) jo;
			JsonObject ja2 = (JsonObject) ja.get(0);
			ja = (JsonArray) ja2.get("tracks");
			saveResponse(ja.toString());
	}
	
	
	private void saveResponse(String response) throws ApiException{
		try{
			
			
			JsonParser jsonParser = new JsonParser();
            JsonElement jo = jsonParser.parse(response);
            JsonArray array = (JsonArray) jo;

            for (JsonElement e : array) {
                    JsonObject json = e.getAsJsonObject();
                    String key = json.get("stream_url").getAsString();
                    json.addProperty("_id", key);
                    json.addProperty("type", "sc");
                    dbClient1.save((JsonObject) e);
            }
            
		} catch(Exception e){
			
		}
	}

}
