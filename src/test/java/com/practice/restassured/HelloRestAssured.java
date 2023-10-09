package com.practice.restassured;

import java.io.File;	
import java.io.InputStream;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HelloRestAssured {
	
	Response response;
	
	public String getStatus() {
		return response.asPrettyString();
	}
	
	public int getCode() {
		return response.getStatusCode();
	}
//	public static final String URI = "http://127.0.0.1:5500";
	JSONParser jsonparser = new JSONParser();
	@Test	
	public void signin() throws ParseException {
		RestAssured.baseURI = "https://demoqa.com";
		RequestSpecification requestSpecification = RestAssured
				.given()
				.contentType("application/json")
				.body("{\"userName\":\"admin\",\"password\":\"Admin1234567890!\"}");
		response = requestSpecification.request(Method.POST, "Account/v1/Login");
//		Assert.assertEquals(getCode(), 201);
		System.out.println(getStatus());
		Object obj = jsonparser.parse(response.asPrettyString());
		JSONObject jobj = (JSONObject)obj;
		String id = (String)jobj.get("userId");
		System.out.println("ID:"+id);
//		response = requestSpecification.request(Method.GET,"/api/unknown");
//		Assert.assertEquals(getCode(), 200);
//		System.out.println(getCode());
//		System.out.println(getStatus());
		
	}
	
//	@Test
//	public void getList() {
//		RestAssured.baseURI = "";
//		RequestSpecification requestSpecification1 = RestAssured
//				.given()
//				.body("{\r\n"
//						+ "    \"name\": \"morpheus\",\r\n"
//						+ "    \"job\": \"leader\"\r\n"
//						+ "}");
//		Response response = requestSpecification1.request(Method.POST,"/API/store.json/1");
//		System.out.println(response.asPrettyString());
//		System.out.println(response.statusCode());
//	}
//	@Test
//	public void first() {
//		RestAssured.baseURI = "https://demoqa.com";
//		RequestSpecification specification = RestAssured
//				.given()
//				.body("{\"userName\":\"admin\",\"password\":\"Admin1234567890!\"}");
//		response = specification.request(Method.POST,"Account/v1/Login");
//		System.out.println(getStatus());
//		System.out.println(getCode());
//		
//		
//		
//	}
	
	
}
