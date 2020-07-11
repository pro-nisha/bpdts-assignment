
import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class UserApiTest {

	@Test
	public void shouldHitUsersEndPointSuccessfully() {
		 given().
		    when().
		        get("http://bpdts-test-app-v2.herokuapp.com/users").
		    then().
		        assertThat().statusCode(200);
	}
	
	@Test
	public void shouldBeJasonContent() {
		 given().
		    when().
		        get("http://bpdts-test-app-v2.herokuapp.com/users").
		    then()
		    .contentType(ContentType.JSON);

	}
	@Test
	public void shouldFindUserByUserId() {
		// Get Response Body 
		Response response = given().
		 pathParam("id", 1).
		    when().
		        get("http://bpdts-test-app-v2.herokuapp.com/user/{id}");
		// Get JSON Representation from Response Body 
	    JsonPath jsonPath = response.jsonPath();
	    System.out.println( response.body().jsonPath().get("latitude"));
	    System.out.println( response.body().jsonPath().get("longitude"));
	    Assert.assertEquals(jsonPath.get("id"),1);	
	    Assert.assertEquals(jsonPath.get("first_name"),"Maurise");	
	    Assert.assertEquals(jsonPath.get("last_name"),"Shieldon");	
	    Assert.assertEquals(jsonPath.get("email"),"mshieldon0@squidoo.com");	
	    Assert.assertEquals(jsonPath.get("ip_address"),"192.57.232.111");
	   Assert.assertEquals(jsonPath.get("latitude"),34.003136);
	    Assert.assertEquals(jsonPath.get("longitude"),-117.7228641); 
	    Assert.assertEquals(jsonPath.get("city"),"Kax"); 


	}
	
	@Test
	public void shouldReturn404ResponseCodeIdUserNotFound() {
		// Get Response Body 
		Response response = given().
		 pathParam("id", 999999).
		    when().
		        get("http://bpdts-test-app-v2.herokuapp.com/user/{id}");
	    Assert.assertEquals(response.getStatusCode(),404); 


	}
	
	@Test
	public void shouldFindUserByUserByCity() {
		// Get Response Body 
		Response response = given().
		 pathParam("city", "Kax").
		    when().
		        get("http://bpdts-test-app-v2.herokuapp.com/city/{city}/users");
		// Get JSON Representation from Response Body 
	    JsonPath jsonPath = response.jsonPath();
	    Assert.assertEquals(jsonPath.get("id[0]"),1);	
	    Assert.assertEquals(jsonPath.get("first_name[0]"),"Maurise");	
	    Assert.assertEquals(jsonPath.get("last_name[0]"),"Shieldon");	
	    Assert.assertEquals(jsonPath.get("email[0]"),"mshieldon0@squidoo.com");	
	    Assert.assertEquals(jsonPath.get("ip_address[0]"),"192.57.232.111");
	    //Assert.assertEquals(jsonPath.get("latitude[0]"),34.003135);
	   //   Assert.assertEquals(jsonPath.get("longitude[0]"),-117.7228641); 

	    Assert.assertEquals(jsonPath.get("id[1]"),854);	
	    Assert.assertEquals(jsonPath.get("first_name[1]"),"Nelly");	
	    Assert.assertEquals(jsonPath.get("last_name[1]"),"Thurley");	
	    Assert.assertEquals(jsonPath.get("email[1]"),"nthurleynp@joomla.org");	
	    Assert.assertEquals(jsonPath.get("ip_address[1]"),"46.72.120.66");
	    //Assert.assertEquals(jsonPath.get("latitude[0]"),34.003135);
	   //   Assert.assertEquals(jsonPath.get("longitude[0]"),-117.7228641); 

	}
	
}
