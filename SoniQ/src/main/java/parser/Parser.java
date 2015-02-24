package parser;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import domain.Record;

public interface Parser {

	public ArrayList<Record> parse(JsonObject json);
	public ArrayList<Record> parse(String json);

}
