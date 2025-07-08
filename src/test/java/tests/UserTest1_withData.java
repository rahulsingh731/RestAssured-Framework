package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import endpoints.Userendpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.UserPOJO;
import utilities.DataProviders;

import utilities.utilityReader;

public class UserTest1_withData {
    private UserPOJO user;

    @Test(priority = 1, dataProvider = "userdata", dataProviderClass = DataProviders.class)
    public void postUser(String id, String username, String firstname, String lastname, String email, String password, String phone, String userStatus) {
        user = new UserPOJO();
        try {
            user.setId(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + id, e);
        }
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        try {
            user.setUserStatus(Integer.parseInt(userStatus));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid UserStatus format: " + userStatus, e);
        }

        System.out.println("User Email: " + user.getEmail());
        
        Response response = Userendpoints.createUser(user);
		response.then().log().body().statusCode(200);
        
        
    }
}
	
//	@Test(priority=2,dependsOnMethods = "postUser")
//	public void getUser() {
//		Response response  =  Userendpoints.readUser(this.user.getUsername());
//		
//		UserPOJO responseParsed = response.as(UserPOJO.class);
//		
//		Assert.assertEquals(responseParsed.getUsername(), user.getUsername(), "Username does not match");
//		Assert.assertEquals(responseParsed.getEmail(), user.getEmail(), "Email does not match");
//		Assert.assertEquals(responseParsed.getFirstName(), user.getFirstName(), "First name does not match");
//		Assert.assertEquals(responseParsed.getLastName(), user.getLastName(), "Last name does not match");
//	}
//	
//	@Test(priority=3)
//	public void UpdateUserDetails() {
//		user.setEmail(faker.internet().emailAddress());
//		Response response = Userendpoints.updateUser(this.user.getUsername(),user);
//		
//		response.then().log().all().assertThat().statusCode(200);
//	
//	}
//	@Test(priority=4,dependsOnMethods = "UpdateUserDetails")
//	public void deleteUserDetails() {
//		
//		Response response = Userendpoints.delUser(this.user.getUsername());
//		Assert.assertEquals(response.getStatusCode(), 200);
//	}


