package com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.entity.Students;
import com.mvc.model.Student;
import com.mvc.service.StudentService;
import com.mvc.validator.StudentFormValidator;

@RestController
@CrossOrigin
public class StudentController {
	private final Logger logger = LoggerFactory.getLogger(StudentController.class);

	private StudentService studentService;

	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		logger.debug("index()");
		return new ModelAndView("redirect:/students");
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Students> showAllStudents() {

		List<Students> students = studentService.findAll();
		return students;

	}

	@RequestMapping(value = "/delete-with-id", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public void delete(@RequestBody Integer id) {

		logger.debug("deleteStudent() : {}", id);

		studentService.delete(id);

	}

	@RequestMapping(value = "/saveOrUpdateStudent", method = RequestMethod.POST)
	public void saveOrUpdateStudent(@RequestBody Student student) {

		logger.debug("saveOrUpdateStudent() : {}", student);
		studentService.saveOrUpdate(student);
	}

	@RequestMapping(value = "/students/add", method = RequestMethod.GET)
	public ModelAndView showAddStudentForm() {

		logger.debug("showAddStudentForm()");

		Student student = new Student();
		student.setFirstName("");
		student.setLastName("");
		student.setAddress("");

		ModelAndView mav = new ModelAndView("students/studentform");
		mav.addObject("studentForm", student);

		return mav;

	}

	@RequestMapping(value = "/students/{id}/update", method = RequestMethod.GET)
	public ModelAndView showUpdateStudentForm(@PathVariable("id") int id) {

		logger.debug("showUpdateStudentForm() : {}", id);

		Student dto = studentService.findById(id);

		ModelAndView mav = new ModelAndView("students/studentform");
		mav.addObject("studentForm", dto);

		return mav;
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public ModelAndView showStudent(@PathVariable("id") int id) {

		logger.debug("showStudent() id: {}", id);

		ModelAndView mav = new ModelAndView("students/show");
		Student student = studentService.findById(id);
		if (student == null) {
			mav.addObject("css", "danger");
			mav.addObject("msg", "Student not found");
		}
		mav.addObject("student", student);
		return mav;

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("student/show");
		model.addObject("msg", "student not found");

		return model;

	}
}
