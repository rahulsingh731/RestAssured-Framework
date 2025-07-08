package endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.UserPOJO;
import endpoints.Routes;

public class Userendpoints {

	// Perform CRUD operations
	public static Response createUser(UserPOJO payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_user);

		return response;
	}

	public static Response delUser(String username) {

		Response response = given().pathParam("username",username).when()
				.delete(Routes.delete_user);

		return response;
	}
	
	public static Response readUser(String username) {

		Response response = given().pathParam("username", username).when()
				.get(Routes.get_user);

		return response;
	}
	
	public static Response updateUser(String username,UserPOJO payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", username).body(payload).when()
				.put(Routes.update_user);

		return response;
	}

}
