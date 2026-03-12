package shopperStack;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class JsonPathTest {
	String shopperId,jwtToken;
	@Test
	public void loginTest() {
		HashMap<String,Object> map = new HashMap<>();
		map.put("email", "mishrapuja460@gmail.com");
		map.put("password", "Puja@1610");
		map.put("role", "SHOPPER");
		Response res=given().contentType("application/json").relaxedHTTPSValidation()
				.body(map)
				.when().post("https://www.shoppersstack.com/shopping/users/login");
		shopperId=res.jsonPath().getString("data.userId");
		System.out.println("Shopper Id is "+shopperId);
		jwtToken=res.jsonPath().getString("data.jwtToken");
		System.out.println("Bearer Token "+jwtToken);
		res.then().log().all();
	}
	@Test(dependsOnMethods = "loginTest")
	public void fetchData() {
		Response res=given().relaxedHTTPSValidation().contentType("application/json").auth().oauth2(
				jwtToken)
				.pathParam("shopperID", shopperId)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/shoppers/{shopperID}");
		System.out.println(res.prettyPrint());
	}

}
