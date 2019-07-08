package com.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mvc.entity.Students;
import com.mvc.entity.StudentsExample;
import com.mvc.mapper.StudentsMapper;
import com.mvc.model.Student;
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate JdbcTemplate;
	@Autowired
	StudentsMapper studentsMapper;
	@Override
	public Student findById(Integer id) {
		
		String sql = "select * from students where id=" + id;
		List<Student> students = JdbcTemplate.query(sql, new StudentMapper());
		if (students.isEmpty()) {
			return null;
		} else {
			
			return students.get(0);
		}
		
		
	}
	class StudentMapper implements RowMapper<Student> {

		public Student mapRow(ResultSet rs, int arg1) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setAddress(rs.getString("address"));
		
			return student;
		}
	}
	@Override
	public List<Students> findAll() {
		// TODO Auto-generated method stub
				StudentsExample example = new StudentsExample();
				List<Students> studentList = studentsMapper.selectByExample(example);
				return studentList;
	}

	@Override
	public void save(Student student) {
		Students record = new Students();
		record.setFirstName(student.getFirstName());
		record.setLastName(student.getLastName());
		record.setAddress(student.getAddress());
		studentsMapper.insertSelective(record);
		
	}

	@Override
	public void update(Student student) {
		Students record = new Students();
		record.setFirstName(student.getFirstName());
		record.setLastName(student.getLastName());
		record.setAddress(student.getAddress());
		StudentsExample example = new StudentsExample();
		example.createCriteria().andIdEqualTo(student.getId());
		studentsMapper.updateByExampleSelective(record, example);
	}

	@Override
	public void delete(Integer id) {
		StudentsExample example = new StudentsExample();
		example.createCriteria().andIdEqualTo(id);
		studentsMapper.deleteByExample(example);
		
	}
	
}