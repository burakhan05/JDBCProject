package com.hamzaburakhan.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;

public class RsGetter {

	public static <T> Object getObject(ResultSet resultset, Class<T> dataObjectType) {

		T object = null;
		try {
			object = dataObjectType.newInstance();
			String tablename = null;
			Annotation classAnnotation = object.getClass().getDeclaredAnnotation(Table.class);
			if (classAnnotation instanceof Table) {
				tablename = ((Table) classAnnotation).value();

			}
			if (tablename == null) {
				throw new Exception("Not found table annotation.");
			}
			
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				Annotation annotation = field.getDeclaredAnnotation(RealName.class);
				if (annotation instanceof RealName) {
					RealName nameAnnotation = (RealName) annotation;
					field.setAccessible(true);
					Object rsObj = resultset.getObject(tablename+"."+nameAnnotation.value());
					field.set(object, rsObj);
				} else if ((annotation = field.getDeclaredAnnotation(Embeded.class)) instanceof Embeded) {
					field.setAccessible(true);
					Object embeded =  getObject(resultset, field.getType());
					field.set(object, embeded);
				} else if (annotation == null) {

					field.setAccessible(true);
					Object rsObj = resultset.getObject(field.getName());
					field.set(object, rsObj);

				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}

}
