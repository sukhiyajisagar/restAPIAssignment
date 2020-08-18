package resources;

import pojo.AddEmployee;
import pojo.UpdateEmployee;

public class TestDataBuild {

	public AddEmployee addEmployeePayLoad(String name, int salary, int age) {
		AddEmployee p = new AddEmployee();
		p.setName(name);
		p.setSalary(salary);
		p.setAge(age);
		return p;
	}

	public UpdateEmployee updateEmployee(String name, int age) {
		UpdateEmployee q = new UpdateEmployee();
		q.setName(name);
		q.setAge(age);
		return q;
	}
}
