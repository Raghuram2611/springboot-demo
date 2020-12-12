package com.example.jpa.hibernate.demojpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpa.hibernate.demojpa.entity.Course;
import com.example.jpa.hibernate.demojpa.entity.Passport;
import com.example.jpa.hibernate.demojpa.entity.Student;


@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if (student.getId()==null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}

	public void deleteById(long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}

	public void someOperationToUnderstandPersistenceContext() {

		Student student = em.find(Student.class, 20001L);
		
		Passport passport = student.getPassport();
		
		passport.setNumber("E123457");
		
		student.setName("Ranga - updated");
		
		
	}
	
	public void insertHardCodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 Steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourses(course);
		course.addStudents(student);
		em.persist(student);
	}

	
	public void insertStudentAndCourse(Student student, Course course) {
		//Student student = new Student("Jack");
		//Course course = new Course("Microservices in 100 Steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourses(course);
		course.addStudents(student);
		em.persist(student);
	}
}
