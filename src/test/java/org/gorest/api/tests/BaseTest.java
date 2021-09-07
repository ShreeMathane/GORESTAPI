package org.gorest.api.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseTest {
	public Properties prop;
	
	
	public BaseTest() {
		try{
			prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\org\\gorest\\api\\generic\\env.properties");
			prop.load(file);
			RestAssured.baseURI=prop.getProperty("HOST");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
