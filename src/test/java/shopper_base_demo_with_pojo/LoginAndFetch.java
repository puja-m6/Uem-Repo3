package shopper_base_demo_with_pojo;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LoginAndFetch extends BaseClass {
	
	
	@Test
	public void loginTest() {
		ShopperLoginPojo data=new ShopperLoginPojo("mishrapuja460@gmail.com","Puja@1610","SHOPPER");
		Response res=given().contentType("application/json").relaxedHTTPSValidation()
				.body(data)
				.when().post("https://www.shoppersstack.com/shopping/users/login");
		res.then().log().all();
		System.out.println(shopperId+" "+jwtToken);
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
