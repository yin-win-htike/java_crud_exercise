package com.mvc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mvc.model.Student;
import com.mvc.service.StudentService;
@Component
public class StudentFormValidator implements Validator{
	@Autowired
	StudentService studentService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		/*Student student =(Student) target;
		ValidationUtils.rejectIfEmpty(errors, "firstName", "NotEmpty.studentForm.firstName");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty.studentForm.lastName");*/
	}



}
