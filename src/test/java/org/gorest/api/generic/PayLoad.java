package org.gorest.api.generic;

public class PayLoad {

	public static String createNewUserPayload(String name,String gender, String email) {
		String body = "{\"name\":\""+name+"\", \"gender\":\""+gender+"\", \"email\":\""+email+"\", \"status\":\"active\"}";
		return body;
	}
	
	
}
