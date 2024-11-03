package api.endpoints;
//internally connected with roots
//created for RUD operations
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;

public class UserEndpoints {
	
	public static Response createUser(User payload)
	{
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)//mentioned in the swagger after execute in crud as headers 
			.body(payload)
		.when()
			.post(Roots.post_url);//access variable from roots
		
		return res; 
	}
	
	public static Response readUser(String userName)
	{
		Response res=given()
			.pathParam("username", userName)
		.when()
			.get(Roots.get_url);//access variable from roots
		
		return res;  
	}
	
	public static Response updateUser(String userName,User payload)
	{
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)//mentioned in the swagger after execute in crud as headers 
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Roots.put_url);//access variable from roots
		
		return res; 
	}
	
	public static Response deleteUser(String userName)
	{
		Response res=given()
			.pathParam("username", userName)
		.when()
			.delete(Roots.delete_url);//access variable from roots
		
		return res;  
	}
	
	
	
	

}
