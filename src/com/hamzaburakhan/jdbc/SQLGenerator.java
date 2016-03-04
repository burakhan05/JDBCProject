package com.hamzaburakhan.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class SQLGenerator {

	public static String insertSQL(Object record) throws Exception{
		String table = null;
		Annotation classAnnotation = record.getClass().getDeclaredAnnotation(Table.class);
		if (classAnnotation instanceof Table) {
			table = ((Table) classAnnotation).value();

		}
		if (table == null) {
			throw new Exception("Not found table annotation.");
		}
		
		StringBuilder query = new StringBuilder();	
		query.append("INSERT INTO ");
		query.append(table);
		query.append(" (");
		
		Field[] fields = record.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Annotation fieldAnnotation = fields[i].getAnnotation(RealName.class);
			if (fieldAnnotation instanceof RealName) {
				query.append(((RealName) fieldAnnotation).value());
			} else if (fieldAnnotation == null) {
				query.append(fields[i].getName());
			}

			if (i < fields.length - 1) {
				query.append(", ");
			} else {
				query.append(") ");
			}
		}
		query.append("VALUES ");
		query.append(paramText(fields.length));

		return query.toString();
	}
	
	private static String paramText(int paramCount) {
		String str = "(";
		for (int i = 0; i < paramCount; i++) {
			str += "?";

			if (i < paramCount - 1) {
				str += ", ";
			} else {
				str += ")";
			}

		}
		return str;
	}

}
