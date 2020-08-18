Feature: Validating Employee API's
@AddEmployee
Scenario Outline: Verify if Employee is being Succesfully added, retrieved and updated using AddPlaceAPI
	Given Add Employee Payload with "<name>"  <salary> <age>
	When user calls "AddEmployeeAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "success"
	And verify name created maps to "<name>" using "getEmployeeAPI"
	And update name to "<nameToBeUpdate>" <ageToBeUpdate> using "updateEmployeeAPI"
	
Examples:
	|name 	 | salary |age| nameToBeUpdate | ageToBeUpdate |
	|Tiger Nixon  | 10000  |25 | xyz 		|26|