package org.gorest.api.tests;

import org.gorest.api.generic.Resource;
import org.gorest.api.generic.ReusableMethodes;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;
public class GetUserListTest extends BaseTest { 

	@Test
	public void getUserList(){
		Response res = given().header("Authorization",prop.getProperty("HEADER")).
				       when().get(Resource.getUserListResource()).
				       then().statusCode(200).and().contentType(ContentType.JSON).log().all().
				       extract().response();
		JsonPath js = ReusableMethodes.stringToJSON(res);
		List<String> list = ReusableMethodes.getlistKeyValue(res, "data.name");
		for(int i=0;i<list.size();i++) {
			System.out.println(js.get("data["+i+"].name"));
		}
	}
	
}
