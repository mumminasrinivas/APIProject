package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import api.payload.User;
public class DataDrivenTests {
	
	
	@Test(priority = 1, dataProvider ="Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String Name, String Job, String UpdatedJob) {
		
		User userPayload = new User();
		userPayload.setName(Name);
		userPayload.setJob(Job);
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		
	}

	@Test(priority = 2, dataProvider ="Data", dataProviderClass = DataProviders.class)
	public void testUpdateUser(String Name, String Job, String UpdatedJob) {
		
		User userPayload = new User();
		userPayload.setName(Name);
		userPayload.setJob(UpdatedJob);
		
		Response response = UserEndPoints.updateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		//checking data after updation 
		Response responseAfterupdate = UserEndPoints.updateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		
	}
	

	@Test(priority = 4, dataProvider ="Name", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String Name) {
		
		Response response = UserEndPoints.deleteUser();
		Assert.assertEquals(response.getStatusCode(), 204);
		
}
	
}
