package addProductToCart;

import static io.restassured.RestAssured.given;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class AddProductToCart extends BaseClass{
	int quantity;
	int productId;
	int itemId;
	
	@Test
	public void fetchAllProducts() throws IOException {
		Response res=given().relaxedHTTPSValidation().contentType("application/json").auth().oauth2(
				jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/products/alpha");
		List<Integer> productIds=res.jsonPath().getList("data.productId");
		productId=productIds.get(0);
		System.out.println("The product id :"+productId);
		quantity=res.jsonPath().getInt("data[0].quantity");
		System.out.println("Quantity is :"+quantity);
		res.then().
		assertThat().statusCode(200);
		
		FileWriter file=new FileWriter("response.json");
		file.write(res.asPrettyString());
		file.close();
	}
	@Test(dependsOnMethods = "fetchAllProducts()")
	public void addProduct() {
		AddProductToCartPojo cart=new AddProductToCartPojo(productId,quantity);
		Response res=given().relaxedHTTPSValidation().contentType("application/json").auth()
				.oauth2(jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.body(cart)
				.pathParam("shopperId", shopperId)
				.when().post("/shoppers/{shopperId}/carts");
		 itemId = res.jsonPath().get("data.itemId");
		res.then().assertThat().statusCode(201);
	}
	@Test(dependsOnMethods = "addProduct")
	public void updateCart() {
		UpdateCartPojo update=new UpdateCartPojo(productId,quantity);
		Response res=given().relaxedHTTPSValidation().contentType("application/json").auth()
				.oauth2(jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.pathParam("shopperId", shopperId)
				.body(update)
				.pathParam("itemId", itemId)
				.when().put("/shoppers/{shopperId}/carts/{itemId}");
		res.then().assertThat().statusCode(200);
	}
	@Test(dependsOnMethods = "updateCart")
	public void deleteProduct() {
		Response res=given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.baseUri("https://www.shoppersstack.com/shopping")
				.pathParam("shopperId",shopperId)
				.pathParam("productId", productId)
				.when().delete("/shoppers/{shopperId}/carts/{productId}");
		res.then().assertThat().statusCode(200);
	}
}
