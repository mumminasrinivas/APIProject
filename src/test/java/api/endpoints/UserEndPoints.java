package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.Response;


//Created for perform CRUD operations on the user API. 
public class UserEndPoints {

	public static Response createUser(User payload){
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		    .post(Routes.post_url);
		
		return response; 
	}
	
	
	public static Response readUser(){
		Response response =given()
		
		.when()
		    .get(Routes.get_url);
		
		return response; 
	}
	
	public static Response updateUser(User payload){
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		    .put(Routes.put_url);
		
		return response; 
	}
	
	public static Response deleteUser(){
		Response response =given()
		
		.when()
		    .delete(Routes.delete_url);
		
		return response; 
	}
	
}
