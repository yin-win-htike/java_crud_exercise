package com.mvc.service;

import java.util.List;

import com.mvc.entity.Students;
import com.mvc.model.Student;

public interface StudentService {
	Student findById(Integer id);

	List<Students> findAll();

	void saveOrUpdate(Student student);

	void delete(Integer id);
}
