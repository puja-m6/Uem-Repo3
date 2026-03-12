package basePackage;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class BaseClass {
	public static String shopperId;
	public static String jwtToken;
	public static int productId;
	@BeforeClass
	public void login() {
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
}
