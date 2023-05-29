package TestClassPackage;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import RequestRepositoryPackage.Post_Req_Repository;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class Post_tc_1 {
	@Test
	public static void execute() {
		String baseurl="https://reqres.in/";
		RestAssured.baseURI= baseurl;
		
		//save request body in local variable
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		//configure request body
		int statusCode=given().header("Content-Type","application/json").body(Post_Req_Repository.post_req_tc1()).when().post("/api/users")
		.then().extract().statusCode();
		System.out.println(statusCode);
		//configure response body
		String responseBody=given().header("Content-Type","application/json").body(Post_Req_Repository.post_req_tc1()).when().post("/api/users")
        .then().extract().response().asString();	
		System.out.println(responseBody);
		
		//parse the  responseBody
		JsonPath path1=new JsonPath(responseBody);
		String res_name= path1.getString("name");
		String res_job=path1.getString("job");
		String res_id=path1.getString("id");
		String res_createdAt=path1.getString("createdAt");
		
		//validate responsebody parameter
		Assert.assertEquals(statusCode,201);
		Assert.assertEquals(res_name,"morpheus");
		Assert.assertEquals(res_job,"leader");
		Assert.assertNotNull(res_id,"assertion error,id parameter is not null");
		
		//extract data from createdat parameter
		String actual_date=res_createdAt;
		System.out.println(actual_date);
		String current_date=LocalDate.now().toString();
		Assert.assertNotNull(actual_date,current_date);
		System.out.println(current_date);
		
	}
	}
