package com.boot.bean;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
// BeanUtils methods
public class BeanUtilsOfSpring {
	
	/*-
	 * + BeanUtils.copyProperties(source, target, TargetObject.class); 
	 * 	 -> Chỉ sao chép thuộc tính của lớp TargetObject
	 * 
	 * + BeanUtils.copyProperties(source, target, ignoreProperties[]);
	 * 	 -> 
	 */
	public void copyProperties() {

	}
	
	public static void getPropertyDescriptors() {
        // Lấy danh sách các PropertyDescriptor cho đối tượng
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(BeanUtilsOfSpring.class);
        // Duyệt qua danh sách các PropertyDescriptor
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            System.out.println("Property: " + propertyName);
            // Kiểm tra xem thuộc tính có getter không
            if (propertyDescriptor.getReadMethod() != null) {
                // Thực hiện các hoạt động liên quan đến getter
            }
            // Kiểm tra xem thuộc tính có setter không
            if (propertyDescriptor.getWriteMethod() != null) {
                // Thực hiện các hoạt động liên quan đến setter
            }
        }
    }

}
