package parser;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import domain.Record;

public class BandcampParser implements Parser {

	public ArrayList<Record> parse(JsonObject json) {
		
		JsonElement lijst = json.get("bandcamp");
		JsonArray array = lijst.getAsJsonArray();
		
		ArrayList<Record> records = new ArrayList<Record>();
		
		for (int i = 0; i < array.size(); i++) {
			JsonObject object = array.get(i).getAsJsonObject();
			if(object.get("stream_url") != null){
				
				String link = object.get("stream_url").toString();
				
				String duration = object.get("duration").toString();
				double du = Double.parseDouble(duration);
				System.out.println(du);
				String title = object.get("title").toString();

				link = link.substring(1, link.length()-1);
				
				Record r = new Record(link);
				
				r.setTitle(title);
				r.setRecordId(Long.parseLong(object.get("id").toString()));
				r.setDuration((int)du*1000);
				
				records.add(r);
				
			}
		}
		return records;
	}

	public ArrayList<Record> parse(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
