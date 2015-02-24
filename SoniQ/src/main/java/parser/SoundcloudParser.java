package parser;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import domain.Record;

public class SoundcloudParser implements Parser {

	public ArrayList<Record> parse(JsonObject json) {

		JsonElement lijst = json.get("soundcloud");
		JsonArray array = lijst.getAsJsonArray();

		ArrayList<Record> records = new ArrayList<Record>();

		for (int i = 0; i < array.size(); i++) {
			JsonObject object = array.get(i).getAsJsonObject();
			if (object.get("stream_url") != null) {

				String link = object.get("stream_url").toString();

				String duration = object.get("duration").toString();
				double du = Double.parseDouble(duration);
				System.out.println(du);
				String title = object.get("title").toString();

				link = link.substring(1, link.length() - 1);

				Record r = new Record(link);

				r.setTitle(title);
				r.setRecordId(Long.parseLong(object.get("id").toString()));
				r.setDuration((int) du * 1000);

				records.add(r);

			}
		}
		return records;
	}

	public ArrayList<Record> parse(String json) {

		JsonParser parser = new JsonParser();
		JsonElement jo = parser.parse(json);
		JsonObject object = jo.getAsJsonObject();

		JsonObject ja = object.getAsJsonObject("hits");

		JsonArray array = ja.getAsJsonArray("hits");

		ArrayList<Record> records = new ArrayList<Record>();
		for (JsonElement e : array) {
			JsonObject ob = e.getAsJsonObject();

			JsonObject o = ob.get("_source").getAsJsonObject();
			String link = o.get("stream_url").toString();

			link = link.substring(0,link.length()-1);
			
			link += "?client_id=6371e86ce0b447f34f8ed56e5c2a7832";
			
			
			Record r = new Record(link);
			r.setTitle((o.get("title").toString()));
			r.setRecordId(Long.parseLong(o.get("id").toString()));
			r.setDuration((int) Integer
					.parseInt(o.get("duration").toString()));
			records.add(r);
		}

		return records;
	}

}
