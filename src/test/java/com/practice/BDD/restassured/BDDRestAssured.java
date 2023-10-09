package com.practice.BDD.restassured;


import  static io.restassured.RestAssured.*;

import org.testng.annotations.Test;



public class BDDRestAssured {

	@Test
	public void get_user() {
		given()
			.baseUri("https://reqres.in/")
			.header("Content-Type", "application/json")
		.when()
			.get("/api/users/2")
		.prettyPrint();

	}
	@Test
	public void create_user() {
		given()
			.baseUri("https://reqres.in/")
			.header("Content-Type","application/json")
			.body("{\r\n"
					+ "    \"name\": \"morpheus\",\r\n"
					+ "    \"job\": \"leader\"\r\n"
					+ "}");
		when()
			.post("/api/users")
			.prettyPrint();
	}
	@Test
	public void put_data() {
		given()
			.baseUri("https://reqres.in/")
			.header("Content-Type","application/json")
			.body("{\r\n"
					+ "    \"name\": \"morpheus\",\r\n"
					+ "    \"job\": \"zion resident\"\r\n"
					+ "}");
			
		when()
			.put("/api/users/2")
		.prettyPrint();
	}
	@Test
	public void delete_user(){
		given()
			.baseUri("https://reqres.in/")
			.header("Content-Type","application/json");
		when()
			.delete("/api/users/2")
		.prettyPrint();
		
	}

}
