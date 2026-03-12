package shopperStack;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
//import io.restassured.RestAssured;

public class ShopperLoginTest {
	@Test
	public void loginTest() {
		HashMap<String,Object> map = new HashMap<>();
		map.put("email", "mishrapuja460@gmail.com");
		map.put("password", "Puja@1610");
		map.put("role", "SHOPPER");
	
		// RestAssured.
		given().contentType("application/json").relaxedHTTPSValidation()
//		.body("{\r\n"
//				+ "  \"email\": \"mishrapuja460@gmail.com\",\r\n"
//				+ "  \"password\": \"Puja@1610\",\r\n"
//				+ "  \"role\": \"SHOPPER\"\r\n"
//				+ "}")

				.body(map)

				.when().post("https://www.shoppersstack.com/shopping/users/login")

				.then().assertThat().statusCode(200).log().all();
	}

	@Test
	public void fetchData() {
		given().relaxedHTTPSValidation().contentType("application/json").auth().oauth2(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaXNocmFwdWphNDYwQGdtYWlsLmNvbSBTSE9QUEVSIiwiZXhwIjoxNzczMjM4MTMyLCJpYXQiOjE3NzMyMDIxMzJ9.xdVKMoQUd728fa_AEm39UlveW7QIn-u-eZb62RbE4js")
				.pathParam("shopperID", "363318")

				.when().get("https://www.shoppersstack.com/shopping/shoppers/{shopperID}").then().assertThat()
				.statusCode(200).log().all();
	}
}
