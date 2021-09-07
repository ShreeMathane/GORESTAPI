package org.gorest.api.tests;

import org.gorest.api.generic.PayLoad;
import org.gorest.api.generic.Resource;
import org.gorest.api.generic.ReusableMethodes;
import org.gorest.api.utility.Xls_Reader;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateNewUserTest extends BaseTest {

	@Test
	public void createNewUser() {
		Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir") + "/src/test/resources/UserData.xlsx");
		String sheetName = "User";
		int rowCount = reader.getRowCount(sheetName);
		for (int i = 2; i <= rowCount; i++) {
			String name = reader.getCellData(sheetName, "Name", i);
			String gender = reader.getCellData(sheetName, "Gender", i);
			String email = reader.getCellData(sheetName, "Email", i);

			Response res = given().body(PayLoad.createNewUserPayload(name, gender, email))
					.header("Authorization",prop.getProperty("HEADER")).header("content-type","application/json").log().body().when()
					.post(Resource.postUserListResource()).then().assertThat().contentType(ContentType.JSON).and().log()
					.body().extract().response();

			JsonPath js = ReusableMethodes.stringToJSON(res);
			if (res.statusCode() == 422) {
				String val = js.get("data[0].message");
				String val2 = js.get("data[0].field");
				reader.setCellData(sheetName, "Response", i, val2+" "+val);
			} else {
				int val= js.get("data.id");
				reader.setCellData(sheetName, "ID", i,String.valueOf(val));
				reader.setCellData(sheetName, "Response", i,"ID has generated");
			}
		}
	}
}
