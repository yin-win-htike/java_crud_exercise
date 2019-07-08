package com.mvc.dao;

import java.util.List;

import com.mvc.entity.Students;
import com.mvc.model.Student;

public interface StudentDao {
	Student findById(Integer id);

	List<Students> findAll();

	void save(Student student);
	
	void update(Student student);

	void delete(Integer id);

}
