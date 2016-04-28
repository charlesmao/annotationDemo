package com.imooc.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.imooc.myannotation.Column;
import com.imooc.myannotation.Table;

public class Filter {
	
	public static String getSql(Object obj) {
		@SuppressWarnings("unchecked")
		Class<Object> clazz = (Class<Object>) obj.getClass();
		
		//查询语句初始化
		StringBuffer sb = new StringBuffer();
		
		if (clazz.isAnnotationPresent(Table.class)) {
			//获取表名
			Table table = clazz.getAnnotation(Table.class);
			String tableName = table.value();
			if (tableName == null || tableName.trim().equals("")) {
				//如果注解值为空，取类名作为表名,首字段小写
				String simpleName = clazz.getSimpleName();
				tableName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
			}
			
			
			sb.append("select * from ").append(tableName).append(" where 1 = 1");
			
			
			//获取所有属性
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				String columnName = "";
				for (Field field : fields) {
					try {
						if (field.isAnnotationPresent(Column.class)) {
							Column column = field.getAnnotation(Column.class);
							//获取列名
							columnName = column.value();
							if (columnName == null || columnName.trim().equals("")) {
								//如果注解值为空，取属性名作为表名
								columnName = field.getName();
							}
							
							PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
							//获取get方法
							Method getMethod = pd.getReadMethod();
							//执行get方法获取属性值
							Object value = getMethod.invoke(obj);
							
							//拼接sql
							if (value instanceof String) {
								String columnValue = (String)value;
								if (columnValue == null || columnValue.trim().equals("")) {
									continue;
								}
								
								sb.append(" and ").append(columnName);
								
								if (((String) value).contains(",")) {
									
									sb.append(" in (");
									
									String[] values = columnValue.split(",");
									String tempValue = "";
									for (String v : values) {
										tempValue += "'" + v + "',";
									}
									tempValue = tempValue.substring(0, tempValue.length()-1);
									
									sb.append(tempValue).append(")");
								} else {
									sb.append(" = ").append(columnValue);
								}
							} else if (value instanceof Integer) {
								Integer columnValue = (Integer)value;
								if (columnValue == null || columnValue == 0) {
									continue;
								}
								
								sb.append(" and ").append(columnName).append(" = ").append(columnValue);
							}
							
						} else {
							System.out.println("field: " + field.getName() + " is not a column...");
						}
						
					} catch (IntrospectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
		} else {
			System.out.println("please add annotation : @Table");
			sb.append(clazz.getName() + " is not a Table mapping...");
		}
		
		return sb.toString();
		
	}
	
}
