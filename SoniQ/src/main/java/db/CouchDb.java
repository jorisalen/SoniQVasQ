package db;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import api.ApiCaller;
import api.ApiException;
import parser.Parser;
import parser.ParserFactory;
import domain.Record;

public class CouchDb {


	private Parser parser;
	private ApiCaller caller = new ApiCaller();
	
	public ArrayList<Record> getRecords(String query) throws DbException{
		String response;
		ArrayList<Record> records;
		try {
			parser = ParserFactory.createParser("soundcloud");
			 try {
				response = caller.doGet("http://193.191.187.13:11123/sofa/_search?q=artist=" + URLEncoder.encode(query,"UTF-8"));
			} catch (IOException e ) {
				throw new DbException(e.getMessage());
			}

		} catch (ApiException e) {
			throw new DbException(e.getMessage());
		}
		records = parser.parse(response);
		
		return records;
	}

	
	

}
