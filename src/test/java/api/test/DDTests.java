package api.test;
import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
	
	//create multiple users by sending post multiple times
	@Test(priority=1,dataProvider="Data",dataProviderClass=Dataproviders.class)
	public void testPostUser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
	
		
		Response response=UserEndpoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=Dataproviders.class)
	public void testDeleteuserByName(String userName)
	{
		Response response=UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
}
