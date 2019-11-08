package com.first;

public class FirstCrudVo {
	private String name;
	private String id;
	private String idList;
	private String sex;

	public FirstCrudVo() {

	}

	public FirstCrudVo(String id) {
		super();
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
