package api.test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class UserTest {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUpData() 
	{
		faker=new Faker();//data created
		userPayload=new User();//pass in POJO class
		
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("****** Creating user ***********");
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****** User is created ***********");

	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("****** Reading user info ***********");

		
		Response response=UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("****** User info is displayed ***********");

		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("****** Updating user***********");

		
		//update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		
		//response.then().log().body().statusCode(200);//chai assertion
		
		Assert.assertEquals(response.getStatusCode(),200);//testng assertion
		
		//Checking data after updation (just we need to retrieve the data)
//		Response responseAfterUpdate=UserEndpoints.readUser(this.userPayload.getUsername());
//		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

		logger.info("****** User is updated ***********");

	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("****** Deleting user ***********");

		Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("****** User is deleted ***********");

	}
}
