package org.gorest.api.tests;

import org.gorest.api.generic.Resource;
import org.gorest.api.generic.ReusableMethodes;
import org.gorest.api.utility.Xls_Reader;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {
	
	@Test
	public void deleteuser() {
		Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir") + "/src/test/resources/UserData.xlsx");
		String sheet = "User";
		int rowCount = reader.getRowCount(sheet);
		for(int i=2;i<=rowCount;i++) {
            String id = reader.getCellData(sheet, "ID", i);
			Response res = given().header("Authorization",prop.getProperty("HEADER")).
					       header("content-type","application/json").
					       when().delete(Resource.deleteUserListResource(id)).
					       then().log().all().
					       extract().response();

			JsonPath js = ReusableMethodes.stringToJSON(res);
			
			if(res.statusCode() ==204) {
				reader.setCellData(sheet, "Response2", i, id + " Deleted Succesdully");
				System.out.println(id + " Deleted Succesdully");
			}else {
				String val = js.get("data.message");
				reader.setCellData(sheet, "Response2", i, val);
				System.out.println(ReusableMethodes.jsonToString(res));
			}
			
		}
		
	}

}
