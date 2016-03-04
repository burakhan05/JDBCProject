package com.hamzaburakhan.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;

public class RsGetter {
	
	public static <T> Object getObject(ResultSet resultset, Class<T> dataObjectType) {

		T object = null;
		try {
			object = dataObjectType.newInstance();
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				Annotation annotation = field.getDeclaredAnnotation(RealName.class);
				if (annotation instanceof RealName) {
					RealName nameAnnotation = (RealName) annotation;
					field.setAccessible(true);
					Object rsObj =resultset.getObject(nameAnnotation.value());
					field.set(object,rsObj);
				} else if (annotation == null) {
					field.setAccessible(true);
					Object rsObj =resultset.getObject(field.getName());
					field.set(object,rsObj);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}
	
}
