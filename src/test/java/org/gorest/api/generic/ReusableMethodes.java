package org.gorest.api.generic;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ReusableMethodes {
	
	public static JsonPath stringToJSON (Response res) {
		String jsonString = res.asString();
		JsonPath json = new JsonPath(jsonString);
		return json;
	}
	
	public static String jsonToString(Response res) {
		String jsonString = res.asString();
		return jsonString;
	}
	
	public static List<String> getlistKeyValue(Response res,String value) {
		String jsonString = res.asString();
		JsonPath json = new JsonPath(jsonString);
		List<String> list = json.get(value);
		return list;
	}

}
