package api.endpoints;
//internally connected with roots
//created for RUD operations
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;

//Designing endpoints to get data from properties file
public class UserEndpoints2 {
	
	//getting URL's from properties file
	//load properties file using resource bundle class
	static ResourceBundle getURL()
	{
		ResourceBundle roots=ResourceBundle.getBundle("roots");
		return roots;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)//mentioned in the swagger after execute in crud as headers 
			.body(payload)
		.when()
			.post(post_url);//access variable from roots
		
		return res; 
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		
		Response res=given()
			.pathParam("username", userName)
		.when()
			.get(get_url);//access variable from roots
		
		return res;  
	}
	
	public static Response updateUser(String userName,User payload)
	{
		String update_url=getURL().getString("update_url");

		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)//mentioned in the swagger after execute in crud as headers 
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(update_url);//access variable from roots
		
		return res; 
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");

		
		Response res=given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url);//access variable from roots
		
		return res;  
	}
	
	
	
	

}
