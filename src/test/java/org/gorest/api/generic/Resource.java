package org.gorest.api.generic;

public class Resource {
	
	public static String getUserListResource() {
		String resource = "public/v1/users";
		return resource;
	}
	
	public static String postUserListResource() {
		String resource = "public/v1/users";
		return resource;
	}

	public static String putUserListResource() {
		String resource = "public/v1/users";
		return resource;
	}
	
	public static String deleteUserListResource(String id) {
		String resource = "public/v1/users/"+id+"";
		return resource;
	}
	
	
}
