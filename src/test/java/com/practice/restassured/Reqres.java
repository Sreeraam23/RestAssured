package com.practice.restassured;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Reqres {
	RequestSpecification requestSpecification;
	Response response;
	JSONParser jsonparser;
	Object obj;
	
	public void display_response() {
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
	}
	
	public String json_parser(Object data,String param) {
		JSONObject jsonObj = (JSONObject)data;
		return (String)jsonObj.get(param);
	}
	
	public void json_array(Object data,String param,String val) {
		JSONObject jsonObj = (JSONObject)data;
		JSONArray jsonarr =(JSONArray)jsonObj.get(param);
		for(int i = 0;i<jsonarr.size();i++) {
			JSONObject exact =(JSONObject)jsonarr.get(i);
			exact.get(val);
		}
		
	}
	
	public static final String URI = "https://reqres.in";
	
	
	@Test
	public void unsuccessful_register() throws ParseException {
		RestAssured.baseURI = URI;
		requestSpecification = RestAssured
				.given()
				.body("{\r\n"
						+ "    \"email\": \"admin@test.com\"\r\n"
						+ "}");
		response = requestSpecification.request(Method.POST,"api/register");
		jsonparser =  new JSONParser();
		obj = jsonparser.parse(response.asPrettyString());
		display_response();
		Assert.assertEquals(response.statusCode(), 400);
		Assert.assertEquals(json_parser(obj, "error"), "Missing email or username");
	}

	@Test
	public void succuessful_register() throws ParseException {
		RestAssured.baseURI = URI;
		requestSpecification = RestAssured
				.given()
				.body("{\r\n"
						+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
						+ "    \"password\": \"pistol\"\r\n"
						+ "}");
				requestSpecification.contentType("application/json");
		response = requestSpecification.request(Method.POST,"api/register");
		jsonparser =  new JSONParser();
		obj = jsonparser.parse(response.asPrettyString());
		display_response();	
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(json_parser(obj, "token"),"QpwL5tke4Pnpja7X4");
	}
	
	@Test
	public void delayed_response() throws ParseException{
		RestAssured.baseURI = URI;
		requestSpecification = RestAssured
				.given()
				.param("delay", 3);
		response = requestSpecification.request(Method.GET,"/api/users");
		jsonparser =  new JSONParser();
		obj = jsonparser.parse(response.asPrettyString()); 
		display_response();
		json_array(obj,"data", "email");
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void list_user() throws ParseException {
		RestAssured.baseURI = URI;
		requestSpecification = RestAssured.given();
		response = requestSpecification.request(Method.GET,"/api/users");
		jsonparser =  new JSONParser();
		obj = jsonparser.parse(response.asPrettyString());
		display_response();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
}
