package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.StudentDao;
import com.mvc.entity.Students;
import com.mvc.model.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public Student findById(Integer id) {

		return studentDao.findById(id);
	}

	@Override
	public List<Students> findAll() {

		return studentDao.findAll();
	}

	@Override
	public void saveOrUpdate(Student student) {
	
		if (findById(student.getId()) == null) {
			
		studentDao.save(student);
		}else {
			
			studentDao.update(student);
		}
	}



	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		studentDao.delete(id);
	}

}
