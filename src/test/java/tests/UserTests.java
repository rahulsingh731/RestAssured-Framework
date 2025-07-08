package tests;

import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import endpoints.Userendpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.UserPOJO;

public class UserTests {
	
	Faker faker;
	UserPOJO user;
	public Logger log;
	JsonPath jp;
	private static final Logger logger = LogManager.getLogger(UserTests.class);
	
	@BeforeClass
	public void SetupData() {
		
		
		logger = LogManager.getLogger(this.getClass());
		
		faker = new Faker();
		user = new UserPOJO();
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().emailAddress());
		user.setPassword(faker.internet().password(8, 16));
		user.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void postUser() {
		Response response = Userendpoints.createUser(user);
		response.then().log().body().statusCode(200);
		
		jp = new JsonPath(response.asString());
		
//		System.out.println("Messsage: " + jp.getString("message"));
		
		
	}
	
	@Test(priority=2,dependsOnMethods = "postUser")
	public void getUser() {
		Response response  =  Userendpoints.readUser(this.user.getUsername());
		
		UserPOJO responseParsed = response.as(UserPOJO.class);
		
		Assert.assertEquals(responseParsed.getUsername(), user.getUsername(), "Username does not match");
		Assert.assertEquals(responseParsed.getEmail(), user.getEmail(), "Email does not match");
		Assert.assertEquals(responseParsed.getFirstName(), user.getFirstName(), "First name does not match");
		Assert.assertEquals(responseParsed.getLastName(), user.getLastName(), "Last name does not match");
	}
	
	@Test(priority=3)
	public void UpdateUserDetails() {
		user.setEmail(faker.internet().emailAddress());
		Response response = Userendpoints.updateUser(this.user.getUsername(),user);
		
		response.then().log().all().assertThat().statusCode(200);
	
	}
	@Test(priority=4,dependsOnMethods = "UpdateUserDetails")
	public void deleteUserDetails() {
		
		Response response = Userendpoints.delUser(this.user.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
