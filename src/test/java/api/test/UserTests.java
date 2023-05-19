package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	 
	User userPayload; 
	public Logger logger; 
	
	
	@BeforeClass
	public void setup() {
		
		userPayload = new User(); 
		
		userPayload.setName("morpheus");
		userPayload.setJob("leader");
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("************ Creating User **************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		logger.info("************ User is created **************");
		
	}
	
	@Test(priority=2)
	public void testGetUser() {
		logger.info("************ Get User info **************");
		
		Response response = UserEndPoints.readUser();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User info is Displayed **************");
		
	}
	
	
	@Test(priority=3)
	public void testUpdateUser() {
		logger.info("************ Update User info **************");
		
		//updating data of job using payload 
		userPayload.setJob("zion resident");
		
		Response response = UserEndPoints.updateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		//checking data after updation 
		Response responseAfterupdate = UserEndPoints.updateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		logger.info("************ User info Updated**************");
		
	}
	@Test(priority=4)
	public void testDeleteeUser() {
		logger.info("************ Delete User**************");
		Response response = UserEndPoints.deleteUser();
		Assert.assertEquals(response.getStatusCode(), 204);
		logger.info("************ User deleted **************");
	}
}
