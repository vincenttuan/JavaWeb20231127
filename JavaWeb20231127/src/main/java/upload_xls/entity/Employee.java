package upload_xls.entity;

import java.util.Date;

public class Employee {
	private Integer id;
	private String name;
	private String sex;
	private Integer salary;
	private Integer age;
	private Date createtime;
	
	public Employee() {
		
	}
	
	public Employee(String name, String sex, Integer salary, Integer age) {
		this.name = name;
		this.sex = sex;
		this.salary = salary;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
}
