package com.hypertech.employees.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
@Component
public class BeanCopyUtils {
	/**
	 * Copies properties from one object to another
	 * @param source
	 * @destination
	 * @return
	 */
	public static void copyNonNullProperties(Object source, Object destination, String... ignoreProperties){
		Set<String> ignorePropertiesSet = getNullPropertyNames(source);
		for(String propertyName: ignoreProperties) {
			ignorePropertiesSet.add(propertyName);
		}
		BeanUtils.copyProperties(source, destination, ignorePropertiesSet.toArray(new String[0]));
	}

	/**
	 * Returns an array of null properties of an object
	 * @return
	 */
	public static Set<String> getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<String>();
		for(java.beans.PropertyDescriptor pd : pds) {
			//check if value of this property is null then add it to the collection
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}
		return emptyNames;
	}
}
