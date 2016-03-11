package com.hamzaburakhan.jdbc;

import java.util.ArrayList;
import java.util.List;

public class QueryParams {
	private List<Object> params;

	public QueryParams() {
		// TODO Auto-generated constructor stub
	params = new ArrayList<Object>();
	}
	
	public List<Object> getParams() {
		return params;
	}

	public void addParams(Object value) {
		this.params.add(value);
	}

}