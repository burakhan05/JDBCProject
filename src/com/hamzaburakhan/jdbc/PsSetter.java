package com.hamzaburakhan.jdbc;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PsSetter {

	public static void setValues(PreparedStatement ps, QueryParams params) throws SQLException {
		int index = 1;
		if (params.getParams() == null) {
			return;
		}
		for (Object param : params.getParams()) {
			ps.setObject(index, param);
			index++;
		}
	}

	public static void setIntserValues(PreparedStatement ps, Object record) throws Exception {
		Field[] fields = record.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			ps.setObject((i+1), fields[i].get(record));
		}

	}

}
