package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String emp_id;

	@Given("Add Employee Payload with {string}  {int} {int}")
	public void add_Employee_Payload_with(String name, int salary, int age) throws IOException {
		// Write code here that turns the phrase above into concrete actions

		res = given().spec(requestSpecification()).log().all().body(data.addEmployeePayLoad(name, salary, age));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource(), emp_id);
		else if (method.equalsIgnoreCase("PUT"))
			response = res.when().put(resourceAPI.getResource(), emp_id);

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}

	@Then("verify name created maps to {string} using {string}")
	public void verify_emp_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		emp_id = getJsonPath(response, "data.id");
		res = given().spec(requestSpecification()).log().all();
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "data.employee_name");
		assertEquals(actualName, expectedName);
	}

	@Given("update name to {string} {int} using {string}")
	public void update_Employee_Payload_with(String updatedname, int updatedage, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions

		res = given().spec(requestSpecification()).log().all().body(data.updateEmployee(updatedname, updatedage));
		user_calls_with_http_request(resource, "PUT");
	}

}
