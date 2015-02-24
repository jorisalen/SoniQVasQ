package parser;

import api.Api;
import api.ApiException;
import api.BandcampApi;
import api.SoundcloudApi;



public class ParserFactory {

	private static Parser parser;
	
	public static Parser createParser(String type) throws ApiException{
		
		if(type.equals("bandcamp")){
			parser = new BandcampParser();
		} else if(type.equals("soundcloud")){
			parser = new SoundcloudParser();
		} else {
			throw new ApiException("no such parser");
		}
		
		return parser;
	}
	
}

