package basePackage;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ProductViewActions extends BaseClass{
	@Test
	public void fetchAllProducts() {
		Response res=given().relaxedHTTPSValidation().contentType("application/json").auth().oauth2(
				jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/products/alpha");
		List<Integer> productIds=res.jsonPath().getList("data.productId");
		productId=productIds.get(0);
		System.out.println("The product id :"+productId);
		//res.prettyPrint();
	}
}
