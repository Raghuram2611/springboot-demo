package com.example.jpa.hibernate.demojpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpa.hibernate.demojpa.DemojpaApplication;
import com.example.jpa.hibernate.demojpa.entity.Course;
import com.example.jpa.hibernate.demojpa.entity.Passport;
import com.example.jpa.hibernate.demojpa.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}
	
	@Test
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndStudentDetails() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("passport -> {}", passport.getStudent());
	}

	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}
}
